import React, { useEffect, useState } from 'react';
import { useHistory, useLocation } from 'react-router-dom';
import { useUserApi } from '../../../hooks';
import { ROUTES } from '../../../routes';
import { DataList, LayoutContainer, UserListItem } from '../../components';

export function SearchResultScreen() {
  const { push } = useHistory();
  const queryParams = useQuery();
  const [queryValue, setQueryValue] = useState(queryParams.get('query'));
  const [result, setResult] = useState([]);
  const [page, setPage] = useState(0);
  const { findByNameOrEmail } = useUserApi();

  useEffect(() => {
    const curr = queryParams.get('query');
    if (curr !== queryValue) setQueryValue(curr);
  }, [queryParams]);

  useEffect(() => startTimer(updateSearch), [queryValue]);

  const updateSearch = async () => {
    const response = await findByNameOrEmail(queryValue, page);

    if (response.error) setResult([]);
    else setResult(response);
  };

  const handleNextPage = () => {
    setPage(page + 1);
  };

  const handleOpenProfile = (data) => {
    push(`${ROUTES.PROFILE}/${data.email}`);
  };

  return (
    <>
      <LayoutContainer centerColumn>
        <DataList
          dataList={result}
          itemComponent={UserListItem}
          keyExtractor={({ email }) => email}
          onClick={handleOpenProfile}
        />
      </LayoutContainer>
    </>
  );
}

function useQuery() {
  return new URLSearchParams(useLocation().search);
}

let timer;
function startTimer(func) {
  clearTimeout(timer);
  timer = setTimeout(() => func(), 500);
}
