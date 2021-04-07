import React from 'react';
import {   AppRegistry, StyleSheet, Text, View, Animated} from 'react-native';


export default class ColourView extends React.Component {
  constructor(props) {
      super(props);
      this.state = {
        colour: new Animated.Value(0),
      }
  }

  // Executed while the component is being rendered
  componentDidMount() {
    Animated.loop(
      Animated.sequence([
        Animated.timing(this.state.colour, {
          toValue: 100,
          duration: 20000,
        }),
        Animated.timing(this.state.colour, {
          toValue: 0,
          duration: 20000
        })
      ])
    ).start()
  }

  render() {
    // It's only cycling between 3 colours (red, green, blue) right now, we can add more later
    let interpolatedColour = this.state.colour.interpolate({
        inputRange: [0, 50, 100],
      outputRange: ['rgba(255,0,0, 1)', 'rgba(0,255,0, 1)', 'rgba(0,0,255, 1)',]
    });
    return (
      <Animated.View 
          style={[styles.container, {backgroundColor: interpolatedColour}]}
      />
    );
  }
}

var styles = StyleSheet.create({
  container: {
    flex: 1,
    position: 'absolute',
    width: '100%',
    height: '100%',
    // alignItems:'center',
    // justifyContent:'center',
    // flexDirection: 'column',
  },
});