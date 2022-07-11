import { Button, Checkbox, Form, Input, Row, Col, message } from 'antd';
import React from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';



const Login = (props) =>  {
    const navigate = useNavigate();

    const onFinish = (values) => {
        console.log('Success:', values);
    
        axios.post(`http://localhost:8080/api/auth/login`, { username: values.username, password: values.password })
        .catch(error => {
            message.error(error.response.data.message);
        })
        .then(res => {
            message.success('Succesfully logged in ' + res.data.username);
            props.state.token = res.data.password
            navigate("/home");
        })
      
    };
    
    const onFinishFailed = (errorInfo) => {
    console.log('Failed:', errorInfo);
    };

    return (
        <Row>
            <Col span={7}></Col>
            <Col span={8}>
            <Form
                name="basic"
                labelCol={{
                span: 8,
                }}
                wrapperCol={{
                span: 16,
                }}
                initialValues={{
                remember: true,
                }}
                onFinish={onFinish}
                onFinishFailed={onFinishFailed}
                autoComplete="off"
            >
                <Form.Item
                label="Username"
                name="username"
                rules={[
                    {
                    required: true,
                    message: 'Please input your username!',
                    },
                ]}
                >
                <Input />
                </Form.Item>
        
                <Form.Item
                label="Password"
                name="password"
                rules={[
                    {
                    required: true,
                    message: 'Please input your password!',
                    },
                ]}
                >
                <Input.Password />
                </Form.Item>
        
                <Form.Item
                name="remember"
                valuePropName="checked"
                wrapperCol={{
                    offset: 8,
                    span: 16,
                }}
                >
                <Checkbox>Remember me</Checkbox>
                </Form.Item>
        
                <Form.Item
                wrapperCol={{
                    offset: 8,
                    span: 16,
                }}
                >
                <Button type="primary" htmlType="submit">
                    Submit
                </Button>
                </Form.Item>
            </Form>
            </Col>
            <Col span={9}></Col>
        </Row>
    );
  }

export default Login;