import React, {Fragment, Component} from 'react';
import {
  SafeAreaView,
  StyleSheet,
  Text,
  StatusBar,
  NativeModules,
  Button,
  Alert,
} from 'react-native';

class App extends Component {
  state = {
    clicks: NativeModules.StateExample.clicks,
    reactStartTime: 0,
    reactTime: 0,
  }

  render() {
    return (
      <Fragment>
        <StatusBar barStyle="dark-content" />
        <SafeAreaView style={styles.container}>
          <Text style={styles.text}>Clicked: {this.state.clicks}</Text>
          <Button title="Click" onPress={() => {
            this.setState({ reactStartTime: new Date() });
            NativeModules.StateExample.whenClicked((value) => {
            this.setState({ clicks: value, reactTime: new Date() - this.state.reactStartTime });
          }) }}/>
          <Text style={ {paddingTop: 10} }>Response time: {this.state.reactTime}ms</Text>
        </SafeAreaView>
      </Fragment>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'stretch',
    margin: 20,
  },
  text: {
    fontSize: 20,
    textAlign: "center",
    padding: 20,
  }
})

export default App;
