import React from 'react';
import PropTypes from 'prop-types';

import AppBar from '@material-ui/core/AppBar';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';

const MenuHeader = (props) => {
  return (
    <div>
      <AppBar>
        <Toolbar disableGutters={!props.open}>
          <IconButton onClick={props.openDrawer}>
            <MenuIcon />
          </IconButton>
          <Typography variant="title" color="inherit" noWrap>
            Taco Golf
          </Typography>
        </Toolbar>
      </AppBar>
    </div>
  );
}

MenuHeader.propTypes = {
  open:       PropTypes.bool,
  openDrawer: PropTypes.func
}

export default MenuHeader;
