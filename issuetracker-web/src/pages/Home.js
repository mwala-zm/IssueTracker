import React from 'react'

class Home extends React.Component  {
  render() {
    console.log(this.props.state)
    return (
      <div>
        <h2>Home</h2>
      </div>
    );
  }    
}

export default Home;