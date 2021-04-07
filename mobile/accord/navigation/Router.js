import { StackNavigator } from 'react-navigation';
import HomeScreen from '../screens/HomeScreen';
import RoomCreationScreen from '../screens/RoomCreationScreen';
import IdeaSubmissionScreen from '../screens/IdeaSubmissionScreen';
import WaitingScreen from '../screens/WaitingScreen';
import VotingScreen from '../screens/VotingScreen';
import ResultsScreen from '../screens/ResultScreen';

export default StackNavigator({
  Home: {
    screen: HomeScreen,
  },

  RoomCreation: {
    screen: RoomCreationScreen,
  },
  
  IdeaSubmission: {
    screen: IdeaSubmissionScreen,
  },

  Waiting: {
      screen: WaitingScreen,
  },
  
  VotingScreen: {
    screen: VotingScreen,
  },

  ResultsScreen: {
    screen: ResultsScreen,
  }
}, {
  mode: 'modal',
});
