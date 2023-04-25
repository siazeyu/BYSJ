import request from '@/utils/request'

// 查询申请路线列表
export function listRoute(query) {
  return request({
    url: '/credit/route/list',
    method: 'get',
    params: query
  })
}

// 查询申请路线详细
export function getRoute(routeId) {
  return request({
    url: '/credit/route/' + routeId,
    method: 'get'
  })
}

// 新增申请路线
export function addRoute(data) {
  return request({
    url: '/credit/route',
    method: 'post',
    data: data
  })
}

// 修改申请路线
export function updateRoute(data) {
  return request({
    url: '/credit/route',
    method: 'put',
    data: data
  })
}

// 删除申请路线
export function delRoute(routeId) {
  return request({
    url: '/credit/route/' + routeId,
    method: 'delete'
  })
}
