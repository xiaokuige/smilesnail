import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import 'antd/dist/antd.css'
import './assert/index.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap';
import {Provider} from 'react-redux'
import store from './store'

ReactDOM.render(
    <Provider store={store}>
        <App />
    </Provider>
    , document.getElementById('root'));
