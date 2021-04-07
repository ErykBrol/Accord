import React from 'react';
import { StyleSheet, Text, View, Image } from 'react-native';
import { Button, Input, Toast } from 'native-base';
import ColourView from '../components/ColourView';
import Endpoints from '../constants/Endpoints';
import Keywords from '../constants/Keywords';

export default class RoomCreationScreen extends React.Component {
  static navigationOptions = ({ navigation, screenProps }) => ({
    title: 'RoomCreationScreen',
    header: null
  })

  constructor(props) {
    super(props);

    this.state = {
        topic: '',
        time: '',
        created: false,
        passedChecks: true,
        showToast: false,
    };
  }

  createRoom() {
    this.props.screenProps.stompClient.send(Endpoints.CREATE,
         {}, JSON.stringify({ 'topic': this.state.topic, 'duration': this.state.time }));
    this.props.screenProps.setRole(Keywords.CREATOR);
    this.setState({ created: true });
  }

  componentDidUpdate(){
    if (this.state.created && this.props.screenProps.roomCode != ''){
        this.props.screenProps.stompClient.subscribe(
            '/topic/room/' + this.props.screenProps.roomCode + "/status", this.props.screenProps.socketHandler);
        this.props.navigation.navigate('Waiting');
        this.setState({ created: false });
    } 
  }

  checkErrors(){
    let c1 = this.numericInputCheck(this.state.time);
    let c2 = this.emptyFieldsCheck();
    let c3 = this.emptyFieldsCheck();

    if (c1 && c2 && c3) {
        this.createRoom();
    }
  }

  emptyFieldsCheck() {
      if (this.state.topic == '') {
        Toast.show({
            text: "Cannot have empty topic!",
            position: 'bottom',
            type: 'danger',
            duration: 3000,
          });
          return false;
      } else if (this.state.time == '') {
        Toast.show({
            text: "Cannot have empty time limit!",
            position: 'bottom',
            type: 'danger',
            duration: 3000,
          });
          return false;
      }
      return true;
  }

  numericInputCheck(text){
    let newText = '';
    let numbers = '0123456789';

    for (var i = 0; i < text.length; i++) {
        if (numbers.indexOf(text[i]) > -1) { // If numbers is in the text string
            continue;
        } else {
            this.setState({ passedChecks: false });
            this.setState({ time: '' });
            Toast.show({
                text: "Only whole numbers allowed for submission time!",
                position: 'bottom',
                type: 'danger',
                duration: 3000,
              });
            return false;
        }
    }

    if (Number.parseInt(text, 10) > 300 || Number.parseInt(text, 10) <= 0) {
        this.setState({ passedChecks: false });
        Toast.show({
            text: "Time limit must be greater than 0s and less than 300s (5min)!",
            position: 'bottom',
            type: 'danger',
            duration: 3000,
          });
        return false;
    }
    return true;
}

  render() {
    const { navigate } = this.props.navigation;
    return (
        <View style={styles.background}>
            <ColourView />
            <View style={{flex: 0.2}}/>
            <View style={styles.fieldsContainer}>
            <Text style={styles.text}>Enter a topic:</Text>
            <View style={{flex: 0.02}} />
            <View style={styles.inputContainer} >
                <Input
                    style={styles.inputBox}
                    value={this.state.topic}
                    placeholder='ex. Food'
                    onChangeText={(text) => this.setState({ topic: text })}
                    returnKeyType='send'
                    onSubmitEditing={()=>false}
                />
            </View>
            <View style={{flex: 0.15}} />
            <Text style={styles.text}>Enter a time limit (s):</Text>
            <View style={{flex: 0.02}} />
            <View style={styles.inputContainer} >
                <Input
                    style={styles.inputBox}
                    placeholder='ex. 30'
                    onChangeText={(text) => this.setState({ time: text })}
                    value={this.state.time}
                    returnKeyType='send'
                    onSubmitEditing={()=>false}
                />
            </View>
            </View>
            <View style={{flex: 0.2}} />
            <View style={{flex: 0.3, flexDirection: 'row'}}>
                <Button onPress={() => this.checkErrors()} style={styles.button}>
                    <Text style={styles.text}>Create Room</Text>
                </Button>
            </View>
        </View>
    );
  }
}

const styles = StyleSheet.create({
    background: {
        flex: 1,
        alignItems: 'center',        
    },

    text: {
        fontSize: 24,
        color: 'white',
    }, 

    fieldsContainer: {
        flex: 0.7,
        backgroundColor: '#404040',
        justifyContent: 'center',
        alignItems: 'center',
        padding: 20, // Not sure if this scales like flex
    },

    inputContainer: {
        flex: 0.15, 
        backgroundColor: 'white', 
        flexDirection: 'row',
        alignItems: 'center',
    },
    
    inputBox: {
        flex: 0.5,
        backgroundColor: 'white',
        flexDirection: 'row',
        textAlign: 'center',
        fontSize: 18,
        
    }, 

    button: {
        flex: 0.5,
        justifyContent: 'center',
        backgroundColor: '#404040',
    }
});