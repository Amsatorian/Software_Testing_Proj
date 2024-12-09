import request from '@/utils/request'

// 查询customer列表
export function listCustomer(query) {
  return request({
    url: '/customer/customer/list',
    method: 'get',
    params: query
  })
}

// 查询customer详细
export function getCustomer(customerId) {
  return request({
    url: '/customer/customer/' + customerId,
    method: 'get'
  })
}

// 新增customer
export function addCustomer(data) {
  return request({
    url: '/customer/customer',
    method: 'post',
    data: data
  })
}

// 修改customer
export function updateCustomer(data) {
  return request({
    url: '/customer/customer',
    method: 'put',
    data: data
  })
}

// 删除customer
export function delCustomer(customerId) {
  return request({
    url: '/customer/customer/' + customerId,
    method: 'delete'
  })
}
