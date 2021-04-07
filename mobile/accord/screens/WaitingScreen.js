import React from 'react';
import { StyleSheet, Text, View, Image, TouchableHighlight} from 'react-native';
import {Input, Button } from 'native-base';
import ColourView from '../components/ColourView';
import Endpoints from '../constants/Endpoints';
import Keywords from '../constants/Keywords';

export default class WaitingScreen extends React.Component {
  static navigationOptions = ({ navigation, screenProps }) => ({
    title: 'WaitingScreen',
    header: null
  })

  constructor(props) {
      super(props);

      this.state = {
      };

  }

  componentDidUpdate(){
    if (this.props.screenProps.roomClosed == true){
      this.props.navigation.navigate('IdeaSubmission');
    }
  }

  start = () => {
    this.props.screenProps.stompClient.send(Endpoints.CLOSE, {}, "");
  }

  _renderStartButton() {
    if (this.props.screenProps.role == Keywords.CREATOR) {
      return(
        <View style={{flex: 0.07, flexDirection: 'row'}}>
          <Button onPress={this.start} style={[styles.button, {flex:0.25}]}>
              <Text style={styles.text}>Start</Text>
          </Button>
        </View>
      );
    }
    return null;
  };
  

  render() {
    return (
      <View style={styles.background}>
        <ColourView />
        <View style={{ flex: 0.15 }} />
        <View style={styles.backdropView}>
                <Text style={styles.headline}>{this.props.screenProps.roomCode}</Text>
            </View>
        <View style={{ flex: 0.05 }} />        
        <View style={styles.backdropView}>
        <Text style={styles.text}>{this.props.screenProps.roomCount + ' user' +
            (this.props.screenProps.roomCount > 1 ? 's' : '') + ' in the room'}</Text>
            </View>
        <View style={{ flex: 0.15 }} />

        <View style={styles.icon} >
            <Image source={require('../assets/loading_icon.gif')} style={{height:'100%'}} resizeMode='contain' />
        </View>
        <View style={{ flex: 0.20 }} />
        {this._renderStartButton()}
      </View>
    );
  }
}

const styles = StyleSheet.create({
  background: {
    flex: 1,
    alignItems:'center',

  },
  icon: {
    flex: 0.2,
    flexDirection: 'row'
  },

  headline: {
      fontSize: 40,
      color: 'white',
  }, 

  backdropView: {
      backgroundColor: 'rgba(0,0,0,0)',
      alignItems: 'center',
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