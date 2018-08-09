import React from 'react';

import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';

const MenuList = () => {
  return (
    <div>
      <List>
        <ListItem>
          <ListItemText primary={'A Menu Item!'} />
        </ListItem>
        <ListItem>
          <ListItemText primary={'Wow Another!'} />
        </ListItem>
      </List>
    </div>
  );
}

export default MenuList;
