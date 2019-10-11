import React, {Fragment, Component} from 'react';
import {
  SafeAreaView,
  StyleSheet,
  Text,
  StatusBar,
  NativeModules,
  Button,
  NativeEventEmitter,
} from 'react-native';

class App extends Component {
  state = {
    clicks: NativeModules.ClickStateExample.clicks,
    reactStartTime: 0,
    reactTime: 0,
  }

  componentDidMount() {
    new NativeEventEmitter(NativeModules.ClickStateExample).addListener('ValueChange', (event) => {
      this.setState({ clicks: event.value, reactTime: new Date() - this.state.reactStartTime });
    })
  }

  render() {
    return (
      <Fragment>
        <StatusBar barStyle="dark-content" />
        <SafeAreaView style={styles.container}>
          <Text style={styles.text}>Clicked: {this.state.clicks}</Text>
          <Button title="Click" onPress={() => {
            this.setState({ reactStartTime: new Date() });
            NativeModules.ClickStateExample.whenClicked(); }}/>
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
