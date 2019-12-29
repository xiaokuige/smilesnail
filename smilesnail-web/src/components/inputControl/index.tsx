import React, { Component } from 'react';
import { RouteComponentProps } from "react-router-dom";
import { Layout, Row, Col, Avatar, Tabs, Collapse, List, Descriptions, Input, Button, } from 'antd';
import '../../css/style.css';
const { Header, Content, Sider } = Layout;
const { TabPane } = Tabs;
const { Panel } = Collapse;

interface Props {
  onSendMsg: any
}

export default class InputControlComponent extends React.Component<Props> {
	state = {
		content: ''
	};

	handleSendMsg = () => {
		console.log('handleSendMsg');
		this.props.onSendMsg(this.state.content);
	};

	handleGetInputValue = (event: any) => {
	  this.setState({
	    content : event.target.value,
	  })
	};

	render() {
		return (
			<div>
				<Input.TextArea onChange={this.handleGetInputValue}
								rows={2}></Input.TextArea>
				<Button onClick={this.handleSendMsg}>发送</Button>
			</div>
		);
	}
}