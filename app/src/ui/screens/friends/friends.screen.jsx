import React, { useEffect, useState } from 'react';
import { useHistory } from 'react-router-dom';
import { useFriendshipApi } from '../../../hooks';
import { ROUTES } from '../../../routes';
import { DataList, LayoutContainer, UserListItem } from '../../components';

export function FriendsScreen() {
  const { push } = useHistory();
  const { listMyFriends } = useFriendshipApi();
  const [friends, setFriends] = useState([]);

  useEffect(() => {
    (async () => {
      const response = await listMyFriends();

      setFriends(response);
    })();
  }, []);

  const handleOpenProfile = (data) => {
    push(`${ROUTES.PROFILE}/${data.email}`);
  };

  return (
    <LayoutContainer centerColumn>
      <DataList
        dataList={friends}
        itemComponent={UserListItem}
        keyExtractor={({ email }) => email}
        onClick={handleOpenProfile}
      />
    </LayoutContainer>
  );
}
