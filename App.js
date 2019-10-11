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
    clicks: NativeModules.StateExample.clicks
  }

  render() {
    console.log("vvk::", NativeModules);
    return (
      <Fragment>
        <StatusBar barStyle="dark-content" />
        <SafeAreaView style={styles.container}>
          <Text style={styles.text}>Clicked: {this.state.clicks}</Text>
          <Button title="Click" onPress={() => { NativeModules.StateExample.whenClicked((value) => {
            this.setState({ clicks: value });
            // Alert.alert('Clicked', 'Button clicked: '+value)
          }) }}/>
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
