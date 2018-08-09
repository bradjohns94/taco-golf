import React from 'react';
import PropTypes from 'prop-types';

import MenuDrawer from './Menu/MenuDrawer.jsx';
import MenuHeader from './Menu/MenuHeader.jsx';

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      sideBarOpen: false
    };
  }

  handleDrawerOpen = () => {
    this.setState({ sideBarOpen: true });
  };

  handleDrawerClose = () => {
    this.setState({ sideBarOpen: false });
  };

  render() {
    return (
      <div>
        <MenuHeader
          open={this.state.sideBarOpen}
          openDrawer={this.handleDrawerOpen} />
        <MenuDrawer
          open={this.state.sideBarOpen}
          closeDrawer={this.handleDrawerClose} />
      </div>
    );
  }
}

export default App;
