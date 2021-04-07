import React from 'react';
import { StyleSheet, Text } from 'react-native';
import { Button } from 'native-base';
import Endpoints from '../constants/Endpoints';
import Keywords from '../constants/Keywords';

export default class VoteButton extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
    };
  }


  vote(choice) {
    this.props.stompClient.send(Endpoints.VOTE, {}, choice)
  }

  render() {
    return (
      <Button block style={styles.button} onPress={() => this.vote(this.props.text)}>
          <Text style={styles.text}>{this.props.text}</Text>
      </Button>
    );
  }
}

  const styles = StyleSheet.create({
    text: {
        fontSize: 22,
        color: 'white',
    }, 

    button: {
        flex:1,
        backgroundColor: '#404040',
    },
});