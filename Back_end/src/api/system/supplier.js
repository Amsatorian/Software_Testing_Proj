import request from '@/utils/request'

// 查询supplier列表
export function listSupplier(query) {
  return request({
    url: '/system/supplier/list',
    method: 'get',
    params: query
  })
}

// 查询supplier详细
export function getSupplier(supplierId) {
  return request({
    url: '/system/supplier/' + supplierId,
    method: 'get'
  })
}

// 新增supplier
export function addSupplier(data) {
  return request({
    url: '/system/supplier',
    method: 'post',
    data: data
  })
}

// 修改supplier
export function updateSupplier(data) {
  return request({
    url: '/system/supplier',
    method: 'put',
    data: data
  })
}

// 删除supplier
export function delSupplier(supplierId) {
  return request({
    url: '/system/supplier/' + supplierId,
    method: 'delete'
  })
}
