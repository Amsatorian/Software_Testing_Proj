import request from '@/utils/request'

// 查询Productcategory列表
export function listProductcategory(query) {
  return request({
    url: '/Productcategory/productcategory/list',
    method: 'get',
    params: query
  })
}

// 查询Productcategory详细
export function getProductcategory(categoryId) {
  return request({
    url: '/Productcategory/productcategory/' + categoryId,
    method: 'get'
  })
}

// 新增Productcategory
export function addProductcategory(data) {
  return request({
    url: '/Productcategory/productcategory',
    method: 'post',
    data: data
  })
}

// 修改Productcategory
export function updateProductcategory(data) {
  return request({
    url: '/Productcategory/productcategory',
    method: 'put',
    data: data
  })
}

// 删除Productcategory
export function delProductcategory(categoryId) {
  return request({
    url: '/Productcategory/productcategory/' + categoryId,
    method: 'delete'
  })
}
