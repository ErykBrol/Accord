import React from 'react';
import { StyleSheet, Text, View, Image, TextInput, ActivityIndicator, Linking, Root } from 'react-native';
import { Button, Input, Toast, Icon } from 'native-base';
import ColourView from '../components/ColourView'

export default class VotingScreen extends React.Component {
  static navigationOptions = ({ navigation, screenProps }) => ({
    title: 'ResultScreen',
    header: null
  })

  constructor(props) {
    super(props);

    this.state = {
      results: [],
    };  
  }

  componentDidMount(){
    this.setState({ results: this.props.screenProps.results });
  }

  renderResult(index){
    let size = 40 - 5 * index;
    let weight = index == 0 ? 'bold' : 'normal'
    return(
      <View style={{flex: 0.2}} >
        <Text style={{textAlign: 'center', color: 'white',fontSize: size, fontWeight: weight}} 
          onPress={() => Linking.openURL('https://www.google.ca/maps/search/' + this.state.results[index].name)}>{
          this.state.results[index] != null ? ((index + 1) + '. ' + (this.state.results[index].name)  + '  ' ) : ''}
        </Text>
      </View>
    )
  }

  render() {
    return (
        <View style={styles.background}>
          <ColourView />
          <View style={styles.backdropView}>
            <View style={{ flex: 0.1, flexDirection: 'row' }} />
            <Text style={{textAlign: 'center', color: 'white',fontSize: 24}} >Results</Text>
            <View style={{ flex: 0.05}} />
            {this.renderResult(0)}
            {this.renderResult(1)}
            {this.renderResult(2)}
            {this.renderResult(3)}
            {this.renderResult(4)}
            <View style={{flex: 0.2}}/>
            <Text style={{textAlign: 'center', color: 'white',fontSize: 15}}>Tap item to see directions</Text>
          </View>
        </View>
    );
  }
}

const styles = StyleSheet.create({
    background: {
        flex: 1,
        alignItems: 'center',
        flexDirection: 'column',
    },
    backdropView: {
        backgroundColor: 'rgba(0,0,0,0)',
        alignItems: 'center',
    },


});