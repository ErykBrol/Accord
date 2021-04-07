import React from 'react';
import { StyleSheet, Text, View, Image } from 'react-native';
import {Input, Button, Toast } from 'native-base';
import ColourView from '../components/ColourView';
import Endpoints from '../constants/Endpoints';
import Keywords from '../constants/Keywords';

export default class HomeScreen extends React.Component {
  static navigationOptions = ({ navigation, screenProps }) => ({
    title: 'HomeScreen',
    header: null
  })

  constructor(props) {
    super(props);

    this.state = {
        roomCode: '',
        showToast: false,
        showed: false,
    };
  }

  componentWillReceiveProps(nextProps){
    if (nextProps.screenProps.roomExists == true && nextProps.screenProps.role == Keywords.JOINER) {
      this.props.navigation.navigate('Waiting');
    } else if (nextProps.screenProps.failedJoinRoom == true && this.state.showed == false) {
      this.setState({ showed: true });
      Toast.show({
        text: "Invalid room code!",
        position: 'bottom',
        type: 'danger',
        duration: 3000,
      });
      this.props.screenProps.setFail();
    }
  }
  
  joinRoom = () => { 
    this.setState({ showed: false });
    this.props.screenProps.stompClient.subscribe(
      '/topic/room/' + this.state.roomCode + "/status", this.props.screenProps.socketHandler);
    this.props.screenProps.stompClient.send(Endpoints.JOIN, {}, JSON.stringify({'roomCode': this.state.roomCode }));
    this.props.screenProps.setRole(Keywords.JOINER);
    this.props.screenProps.setRoomCode(this.state.roomCode);
    
  }
  createRoom = () => {
     this.props.navigation.navigate('RoomCreation');
  }

  render() {
    const { navigate } = this.props.navigation;
    return (
      <View style={styles.background}>
        <ColourView />
        <View style={{flex: 0.15}} />
        <View style={styles.logo} >
          <Image source={require('../assets/accord_logo.gif')} style={{flex: 0.75}} resizeMode='contain'/>
        </View>

        <View style={{flex: 0.2}} />
        <View style={{flex: 0.1, flexDirection: 'row'}}>
          <Input
            style={{flex: 0.5, textAlign:'center',  backgroundColor:'white', borderWidth:1, borderColor: 'gray'}}
            value={this.state.roomCode}
            placeholder='Room Code'
            onChangeText={(text) => this.setState({ roomCode: text })}
            returnKeyType='send'
            onSubmitEditing={()=>false}
          />
        </View>       
        <View style={{flex: 0.02}} />

        <View style={{flex: 0.07, flexDirection: 'row'}}>
          <Button onPress={this.joinRoom} style={[styles.button, {flex:0.45}]}>
            <Text style={styles.text}>Join</Text>
          </Button>
        </View>

        <View style={{flex: 0.15}} />

        <View style={{flex: 0.06, flexDirection: 'row'}}>
          <Button onPress={this.createRoom} style={[styles.button, {flex:0.25}]}>
              <Text style={styles.text}>Create</Text>
          </Button>
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  background: {
    flex: 1,
    alignItems:'center',
    flexDirection: 'column',
  },
  logo: {
    flex: 0.1,
    flexDirection: 'row'
  },
  button: {
    height: '100%',
    justifyContent:'center',
    backgroundColor: '#404040',
  },
  text: {
    color:'white',
    fontWeight: 'bold',
  },
});