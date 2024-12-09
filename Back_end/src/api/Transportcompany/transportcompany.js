import request from '@/utils/request'

// 查询Transportcompany列表
export function listTransportcompany(query) {
  return request({
    url: '/Transportcompany/transportcompany/list',
    method: 'get',
    params: query
  })
}

// 查询Transportcompany详细
export function getTransportcompany(companyId) {
  return request({
    url: '/Transportcompany/transportcompany/' + companyId,
    method: 'get'
  })
}

// 新增Transportcompany
export function addTransportcompany(data) {
  return request({
    url: '/Transportcompany/transportcompany',
    method: 'post',
    data: data
  })
}

// 修改Transportcompany
export function updateTransportcompany(data) {
  return request({
    url: '/Transportcompany/transportcompany',
    method: 'put',
    data: data
  })
}

// 删除Transportcompany
export function delTransportcompany(companyId) {
  return request({
    url: '/Transportcompany/transportcompany/' + companyId,
    method: 'delete'
  })
}
