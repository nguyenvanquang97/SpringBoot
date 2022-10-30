import React from 'react'
import './Loading.css'
function index() {
  return (
    <div>
    <div className='loading-container'>
    <div className="spinner-border text-primary" role="status">
  <span className="visually-hidden">Loading...</span>
</div>
    </div>
    </div>
  )
}

export default index