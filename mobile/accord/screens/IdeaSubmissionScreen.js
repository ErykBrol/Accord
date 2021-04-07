import React from 'react';
import { StyleSheet, Text, View, Image, TextInput, ActivityIndicator } from 'react-native';
import { Button, Input, Toast } from 'native-base';
import ColourView from '../components/ColourView';
import Endpoints from '../constants/Endpoints';
import Keywords from '../constants/Keywords';

export default class RoomCreationScreen extends React.Component {
  static navigationOptions = ({ navigation, screenProps }) => ({
    title: 'IdeaSubmissionScreen',
    header: null
  })

  constructor(props) {
    super(props);

    this.state = {
        topic: '',
        time: 30,
        txt: '',
        duration: 3000,
        showToast: false,
        suggestion1: '',
        suggestion2: ''
    };
  }

  componentDidMount(){
    // Room creator starts tournament stage after 30 seconds of getting to this screen
    // No timer yet but it will be added later
    this.getSuggestions()
    this.setState({ time: this.props.screenProps.time });
    let that = this;
    setTimeout(() => {that.updateTime()}, 1000);
  }

  getSuggestions(){
    let suggestions = this.props.screenProps.suggestions;
    console.log(suggestions);
    let newSuggestion1 = suggestions[Math.floor(Math.random()*suggestions.length)];
    let newSuggestion2 = suggestions[Math.floor(Math.random()*suggestions.length)];
    this.setState({ suggestion1: newSuggestion1, suggestion2: newSuggestion2 });
  }

  componentDidUpdate(){
    if (this.props.screenProps.tournamentStarted == true){
      this.props.navigation.navigate('VotingScreen');
    }
  }

  updateTime = () => {
    if (this.props.screenProps.role == Keywords.CREATOR && this.state.time == 0){
        this.props.screenProps.stompClient.send(Endpoints.TOURNAMENT, {}, "")
    }
    if (this.state.time > 0){
        let newTime = this.state.time - 1;
        this.setState({ time: newTime });
        if (this.state.time < 4) {
            this.setState({ duration: 0 })
        } else if (this.state.time <= 1) {
            this.setState({ duration: 0 });
        }
        let that = this;
        setTimeout(() => {that.updateTime()}, 1000);
    }
  }

  saveSubmission = () => {
    if (this.checkNonEmptySubmission() == true) {
        this.props.screenProps.stompClient.send(Endpoints.SUBMISSION, {}, JSON.stringify({'name': this.state.txt })); 
        this.setState({ txt: '' });
        this.getSuggestions();
    }
  }

  checkNonEmptySubmission = () => {
      if (this.state.txt == '') {
        Toast.show({
            text: "Enter a non-empty idea!",
            position: 'bottom',
            type: 'danger',
            duration: this.state.duration,
          });
          return false;
      } else if (this.checkAllEmpty()) {
        Toast.show({
            text: "Enter a non-empty idea!",
            position: 'bottom',
            type: 'danger',
            duration: this.state.duration,
          });
          this.setState({ txt: ''});
          return false;
      } else {
        return true;
      }
  }

  checkAllEmpty = () => {
      let l = this.state.txt.length
      let c = 0

      for (var i = 0; i < l; i++) {
        if (this.state.txt[i] == ' ') {
            c++
        }
      }

      if (c == l) {
          return true;
      } else {
          return false;
      }
  }

  changeText = (text) => {
    this.setState({ txt: text })
  }

  suggest1 = () => {
    const newTxt = this.state.suggestion1;
    this.setState({txt: newTxt});
  }

  suggest2 = () => {
    const newTxt = this.state.suggestion2;
    this.setState({txt: newTxt});
  }

  render() {
    return (
        <View style={styles.background}>
            <ColourView />
            <View style={{ flex: 0.1 }} />
            <View style={styles.backdropView}>
                <Text style={styles.text}>{this.props.screenProps.topic}</Text>
            </View>
            <View style={{flex: 0.1}} />
            <ActivityIndicator
                size="large"
                style={{transform: [{scale: 2}]}}
                color="#ffffff"
            />
            <View style={{ flex: 0.05 }} />
            <View style={styles.backdropView}>
                <Text style={styles.text}>{this.state.time}</Text>
            </View>
            <View style={{flex: 0.05}} />
            <View style={{flexDirection: 'row'}}>
                <Button onPress={this.suggest1} style={[styles.button, {flex:0.35}]}>
                    <Text style={styles.buttonText}>{this.state.suggestion1}</Text>
                </Button>
                <View style={{flex: 0.1}} />
                <Button onPress={this.suggest2} style={[styles.button, {flex:0.35}]}>
                    <Text style={styles.buttonText}>{this.state.suggestion2}</Text>
                </Button>
            </View>
            <View style={{flex: 0.05}} />
            <View keyboardShouldPersistTaps={true} style={styles.inputContainer} >
                <Input
                    ref="in"
                    // blurOnSubmit={false} // Currently broken in react native v0.49, will be fixed in v0.50
                    style={styles.inputBox}
                    value={this.state.txt}
                    placeholder='Enter ideas here'
                    autoFocus={true}
                    onChangeText={(text) => this.changeText(text)}
                    returnKeyType='go'
                    onSubmitEditing={() => this.saveSubmission()}
                />
            </View>
            <View style={{flex: 0.2}} />
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

    buttonText: {
        fontSize: 16,
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
        flex: 0.08, 
        backgroundColor: 'white', 
        flexDirection: 'row',
        alignItems: 'center',
    },
    
    inputBox: {
        flex: 0.7,
        backgroundColor: 'white',
        flexDirection: 'row',
        textAlign: 'center',
        fontSize: 18,
    }, 

    button: {
        flex: 0.5,
        justifyContent: 'center',
        backgroundColor: '#404040',
    },

    backdropView: {
        backgroundColor: 'rgba(0,0,0,0)',
        alignItems: 'center',
    },
});