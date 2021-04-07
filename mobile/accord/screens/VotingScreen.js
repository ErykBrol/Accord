import React from 'react';
import { StyleSheet, Text, View, Image, TextInput, ActivityIndicator } from 'react-native';
import { Button, Input } from 'native-base';
import ColourView from '../components/ColourView'
import Endpoints from '../constants/Endpoints';
import Keywords from '../constants/Keywords';
import VoteButton from '../components/VoteButton'

export default class VotingScreen extends React.Component {
  static navigationOptions = ({ navigation, screenProps }) => ({
    title: 'VotingScreen',
    header: null
  })

  constructor(props) {
    super(props);
    this.state = {
    }
  }

  componentDidUpdate(nextProps, nextState){
    if (this.props.screenProps.tournamentDone){
        this.props.navigation.navigate('ResultsScreen');
    }
  }

  render() {
    return (
        <View style={styles.background}>
            <ColourView />
            <View style={{ flex: 0.1 }} />
            <View style={styles.backdropView}>
                <Text style={{ textAlign: 'center', color: 'white', fontSize: 24 }} >Vote</Text>
            </View>
            <View style={{ flex: 0.2 }}/>
            <View style={styles.buttonContainer}>
                <View style={{flex: 0.1}}/>
                <View style={styles.bc2}>
                    <VoteButton text={this.props.screenProps.leftChoice}
                        press={this.vote} stompClient={this.props.screenProps.stompClient}/>
                </View>
                <View style={{flex: 0.1}}/>
                <View style={styles.bc2}>
                    <VoteButton text={this.props.screenProps.rightChoice} 
                        press={this.vote} stompClient={this.props.screenProps.stompClient}/>
                </View>
                <View style={{flex: 0.1}}/>
            </View>
        </View>
    );
  }
}

const styles = StyleSheet.create({
    background: {
        flex: 1,
        alignItems:'center',
    },

    bc2: {
        flex: 0.7,
        justifyContent: 'center',
    },

    buttonContainer: {
        flex: 0.5,
        flexDirection: 'row',
        justifyContent: 'center',
    },

    text: {
        fontSize: 22,
        color: 'white',
    }, 

    button: {
        flex:1,
        backgroundColor: '#404040',
    },
    backdropView: {
        backgroundColor: 'rgba(0,0,0,0)',
        alignItems: 'center',
    },
});