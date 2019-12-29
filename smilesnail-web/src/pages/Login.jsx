import React, {Component} from 'react';
import {getUserDataAction} from '../store/actionCreators'
class Login extends Component{
    constructor(props){
        super(props);
        this.state = {
            user_name:'',
            password:''
        }
    }

    render(){
        return(
            <div className="login">
                <div className="login-wrap">
                    <div className="col-md-offset-1 col-md-10">
                        <div className="input-group input-group-lg">
                            <span className="input-group-addon">
                                <i className="fa fa-id-card-o"></i>
                            </span>
                            <input
                                name="user_name"
                                type="text"
                                className="form-control"
                                placeholder="用户名"
                                onChange={e=>this._onInputChange(e)}
                                onKeyUp={e=>this._onInputKeyUp(e)}
                            />
                        </div>
                        <div className="input-group input-group-lg">
                            <span className="input-group-addon">
                                <i className="fa fa-key"></i>
                            </span>
                            <input
                                name="user_pwd"
                                type="password"
                                className="form-control"
                                placeholder="密码"
                                onChange={e=>this._onInputChange(e)}
                                onKeyUp={e=>this._onInputKeyUp(e)}
                            />
                        </div>
                        <button
                            type="submit"
                            className="btn btn-lg btn-danger btn-block"
                            onClick={e=>this._onSubmit(e)}
                        >
                            登 录
                        </button>
                    </div>
                </div>
            </div>
            )
    }

    //1.当输入框的内容发生变化
    _onInputChange(e){
        //1.1获取数据
        let inputValue = e.target.value,
            inputName = e.target.name;

        //1.2更新数据
        this.setState({
            [inputName] : inputValue
        })
    }

    // 2.处理回车
    _onInputKeyUp(e){
        if(e.keyCode === 13){
            this._onSubmit();
        }
    }

    //3.当用户提交表单
    _onSubmit(){
        //3.1获取数据
        const {user_name,password} = this.state;
        //3.2验证数据
        if(!user_name){
            alert("输入的口令不能为空！");
            return;
        }
        if(!password){
            alert("输入的密码不能为空！");
            return;
        }
        let params = {
            'user_name':user_name,
            'password': password
        }

        //3.4发起网络请求
        this.props.reqLogin(params,(userData)=>{
            if(userData.token!=''){
                if(userData.token !== ''){
                    this.props.history.push('/');
                }
            }
        })
    }
}
const mapDispatchToProps = (dispatch)=>{
    return {
        reqLogin(data, callback){
            const action = getUserDataAction(data, callback);
            dispatch(action)
        }
    }
};
export default Login