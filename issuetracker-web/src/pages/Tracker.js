import axios from 'axios';
import React , { useState } from 'react';
import { Space, Table, Typography, Button } from 'antd';

const { Link } = Typography;


const columns = [
  {
    title: 'Name',
    dataIndex: 'name',
    key: 'name',
    render: (_, record) => <Link href={`/view_inspection/${record.id}`}>{record.name}</Link>,
  },
  {
    title: 'Status',
    dataIndex: 'status',
    key: 'status',
  },
  {
    title: 'Description',
    dataIndex: 'description',
    key: 'description',
  },
  {
    title: 'Date',
    dataIndex: 'date',
    key: 'date',
  },
  {
    title: 'Location',
    dataIndex: ['equipment', 'location'],
    key: 'location',
  },
  {
    title: 'State',
    dataIndex: ['state', 'name'],
    key: 'name',
  },
  {
    title: 'Submitted by',
    dataIndex: ['users', 'username'],
    key: 'username',
  },
  {
    title: 'Action',
    key: 'action',
    render: (_, record) => (
      <Space size="middle">
        <Link type="success" href={record._links.complete.href} strong>Complete</Link>
        <Link type="warning" href={record._links.cancel.href} strong>Cancel</Link>
      </Space>
    ),
  },
];

const rowSelection = {
  onChange: (selectedRowKeys, selectedRows) => {
    console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
  },
  getCheckboxProps: (record) => ({
    disabled: record.name === 'Disabled User',
    // Column configuration not to be checked
    name: record.name,
  }),
};

class Tracker extends React.Component {
    // constructor(props) {
    //   super(props);
    //   this.state = {};
    // }

    state = {
    }

    componentDidMount() {
      let currentComponent = this;
      var config = {
        method: 'get',
        url: 'http://localhost:8080/inspections/',
        headers: { 
          'Authorization': 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtd2l6YSIsImV4cCI6MTY1NzMxMTQyNSwiaWF0IjoxNjU3MjI1MDI1LCJhdXRob3JpdGllcyI6WyJJbnNwZWN0b3IiLCJTdXBlcnZpc29yIl19.ddhLNhjQTTrmKDckbtFUe6KBP4dMrTfT_mxmVWR7O7fL9RecjKCNX3FEwaH08yOtu3n1T_f8Qct20DUOdUO9qA', 
          'Content-Type': 'application/json'
        }
      };
      
      axios(config)
      .then(function (response) {
        // console.log(JSON.stringify(response.data));
        // console.log(config)
        const data = response.data._embedded.inspectionModelList;
        currentComponent.setState({ data });
        // console.log(this.state.data)
      })
      .catch(function (error) {
        console.log(error);
      });
    }

    render(){
      let x = this.state.data;
      let table = [];
      {x && x.map((product) => (table.push(product)))};
      return (
        <div>
          <h2>Tracker</h2>
          <Button type="primary">
            Open Modal
          </Button>
          <Table 
            columns={columns} 
            dataSource={table}
            rowSelection={{
              type: "checkbox",
              ...rowSelection,
            }}
          />
        </div>
      );
    }
  }

export default Tracker;