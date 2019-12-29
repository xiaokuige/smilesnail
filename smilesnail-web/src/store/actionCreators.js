import * as constants from './actionTypes'
import { getUserData } from '../api'
// 0. 用户登录
export const getUserDataAction = (data, callback)=>{
    return (dispatch)=>{
        // 2.1 发起网络请求
        getUserData(data).then((res)=>{
            if(res.status_code === 200){
                const userData = res.result;
                dispatch({
                    type: constants.INIT_USER_DATA,
                    userData
                });
                // 成功的回调
                callback && callback(userData);
            }else {
                alert(res.result);
            }
        }).catch((error)=>{
            alert(error);
        })
    }
};