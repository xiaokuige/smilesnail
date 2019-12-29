import ajax from './ajax'

//0.定义基础路径
const BASE_URL = '';

export const getUserData = (data)=> ajax(BASE_URL + '/user/api/login', data, 'POST');