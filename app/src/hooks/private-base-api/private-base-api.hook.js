import { useMemo } from 'react';
import { useGlobalUser } from '../../context';
import { useBaseApi } from '../base-api/base-api.hook';

export function usePrivateBaseApi(context) {
  const [{ token }] = useGlobalUser();
  const baseAPI = useBaseApi(context, { authorization: token });

  return useMemo(() => baseAPI, []);
}
