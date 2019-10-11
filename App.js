import React, {Fragment, Component} from 'react';
import {
  SafeAreaView,
  StyleSheet,
  Text,
  TextInput,
  StatusBar,
  NativeModules,
  Button,
  NativeEventEmitter,
} from 'react-native';

class App extends Component {
  state = {
    delay: NativeModules.DelayStateExample.delay,
    reactStartTime: 0,
    reactTime: 0,
    cppValue: NativeModules.DelayStateExample.cppValue,
  }

  componentDidMount() {
    new NativeEventEmitter(NativeModules.DelayStateExample).addListener('ValueChange', (event) => {
      this.setState({ delay: event.value, reactTime: new Date() - this.state.reactStartTime });
    })
  }

  render() {
    return (
      <Fragment>
        <StatusBar barStyle="dark-content" />
        <SafeAreaView style={styles.container}>
          
          <Button title="Click" onPress={() => {
            this.setState({ reactStartTime: new Date() });
            NativeModules.DelayStateExample.whenClicked(); }}/>
          <Text style={styles.text}>{this.state.cppValue}</Text>
          <Text style={ {paddingTop: 10, paddingBottom: 10} }>Delay change response time: {this.state.reactTime}ms</Text>
          <TextInput style={styles.textinp} value={this.state.delay.toString()}
              onChangeText={(value) => {
                this.setState({ reactStartTime: new Date() })
                NativeModules.DelayStateExample.setDelay(parseInt(value))}}></TextInput>
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
  },
  textinp: {
    fontSize: 20,
    backgroundColor: '#dedede',
    padding: 20
  }
})

export default App;
