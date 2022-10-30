import React from 'react'

function Address() {
  return (
    <div className='p-4'>
         <h2>Danh sách tỉnh thành phố</h2>
 <select id="province">
    <option hiden>Chọn tỉnh thành phố</option>
 </select>
 <select id="district">
    <option hidden>Chọn quận huyện</option>
 </select>
 <select id="commune">
 <option hidden>Chọn xã phường</option>
 </select>

    </div>
  )
}

export default Address