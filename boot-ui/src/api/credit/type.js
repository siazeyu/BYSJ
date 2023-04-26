import request from '@/utils/request'

// 查询加分项列表
export function listType(query) {
  return request({
    url: '/credit/type/list',
    method: 'get',
    params: query
  })
}

// 查询加分项详细
export function getType(typeId) {
  return request({
    url: '/credit/type/' + typeId,
    method: 'get'
  })
}

// 新增加分项
export function addType(data) {
  return request({
    url: '/credit/type',
    method: 'post',
    data: data
  })
}

// 修改加分项
export function updateType(data) {
  return request({
    url: '/credit/type',
    method: 'put',
    data: data
  })
}

// 删除加分项
export function delType(typeId) {
  return request({
    url: '/credit/type/' + typeId,
    method: 'delete'
  })
}
