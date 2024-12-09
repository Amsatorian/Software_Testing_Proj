import request from '@/utils/request'

// 查询activity列表
export function listActivity(query) {
  return request({
    url: '/activity/activity/list',
    method: 'get',
    params: query
  })
}

// 查询activity详细
export function getActivity(id) {
  return request({
    url: '/activity/activity/' + id,
    method: 'get'
  })
}

// 新增activity
export function addActivity(data) {
  return request({
    url: '/activity/activity',
    method: 'post',
    data: data
  })
}

// 修改activity
export function updateActivity(data) {
  return request({
    url: '/activity/activity',
    method: 'put',
    data: data
  })
}

// 删除activity
export function delActivity(id) {
  return request({
    url: '/activity/activity/' + id,
    method: 'delete'
  })
}
