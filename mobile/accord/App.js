import Expo from 'expo';
import React from 'react';
import Navigator from './navigation/Router';
import { Root } from 'native-base';
import { View } from 'react-native';
import SockJS from "sockjs-client";
import Stomp from "stompjs";
import Endpoints from './constants/Endpoints';
import Keywords from './constants/Keywords';

let _stompClient = null;

export default class App extends React.Component {
  state = {
    roomCode: '',
    topic: '',
    time: 30,
    roomCount: 0,
    roomClosed: false,
    roomExists: false,
    failedJoinRoom: false,
    joinRoomStage: true,
    tournamentStarted: false,
    role: Keywords.JOINER,
    leftChoice: '',
    rightChoice: '',
    results: [],
    suggestions: [],
    tournamentDone: false,
    resultsReady: false,
    fontLoaded: false,
  }

  componentDidMount() {
    let app = this;
    let socket = new SockJS(Endpoints.URL);
    _stompClient = Stomp.over(socket);
    _stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        _stompClient.subscribe(Endpoints.MESSAGE, app._socketHandler);
    });
    this.setState({ stompClient: _stompClient });
  }

  _socketHandler = (message) => {
       try {
         let messageBody = JSON.parse(message.body);

         if (this.state.joinRoomStage) {
          this.setState({ roomExists: true });
          this.setState({ failedJoinRoom: false });
          this.setState({ joinRoomStage: false });
        }
        if ('roomCode' in messageBody){
          this.setState({ roomCode: messageBody.roomCode, 
            topic: messageBody.topic,
            time: messageBody.duration,
            roomClosed: messageBody.closed,
            roomCount: messageBody.roomCount,
            suggestions: messageBody.suggestions });
        } else if ('contenders' in messageBody) {
          this.setState({ tournamentStarted: true, 
            leftChoice: messageBody.matchUp.first.name, 
            rightChoice: messageBody.matchUp.second.name, });
        } else if ('first' in messageBody && messageBody.winner == null){
          this.setState({ leftChoice: messageBody.first.name, 
            rightChoice: messageBody.second.name, });
        } else if (Array.isArray(messageBody)){
          let temp = messageBody;
          temp.reverse();
          this.setState({ tournamentDone: true,
            results: temp,
            resultsReady: true });
        }
      } catch(SyntaxError) {
         this.setState({ roomExists: false });
         this.setState({ failedJoinRoom: true });
      }
  }

  _setJoinFailed = () => {
    this.setState({ failedJoinRoom: false });
  }

  _setRoomCode = (newRoomCode) => {
    this.setState({ roomCode: newRoomCode });
  }

  _setTopic = (newTopic) => {
    this.setState({ topic: newTopic })
  }

  _setRole = (newRole) => {
    this.setState({ role: newRole })
  }

  render() {
    return (
      <Root>
          <Navigator screenProps={{
            stompClient: _stompClient,
            socketHandler: this._socketHandler,
            setRoomCode: this._setRoomCode,
            setTopic: this._setTopic,
            setRole: this._setRole,
            roomCode: this.state.roomCode,
            roomExists: this.state.roomExists,
            failedJoinRoom: this.state.failedJoinRoom,
            setFail: this._setJoinFailed,
            topic: this.state.topic,
            roomCount: this.state.roomCount,
            time: this.state.time,
            role: this.state.role,
            roomClosed: this.state.roomClosed,
            leftChoice: this.state.leftChoice,
            rightChoice: this.state.rightChoice,
            tournamentStarted: this.state.tournamentStarted,
            tournamentDone: this.state.tournamentDone,
            results: this.state.results,
            fontLoaded: this.state.fontLoaded,
            suggestions: this.state.suggestions,
          }}
          />
      </Root>
    );
  }
}
Expo.registerRootComponent(App);
