import React from 'react';
import PropTypes from 'prop-types';

import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import Divider from '@material-ui/core/Divider';
import Drawer from '@material-ui/core/Drawer';
import IconButton from '@material-ui/core/IconButton';

import MenuList from './MenuList.jsx';

const MenuDrawer = (props) => {
  return (
    <div>
      <Drawer
        variant="persistent"
        anchor="left"
        open={props.open}>
        <IconButton onClick={props.closeDrawer}>
          <ChevronLeftIcon />
        </IconButton>
        <Divider />
        <MenuList />
      </Drawer>
    </div>
  );
}

MenuDrawer.propTypes = {
  open:         PropTypes.bool,
  closeDrawer:  PropTypes.func
}

export default MenuDrawer;
