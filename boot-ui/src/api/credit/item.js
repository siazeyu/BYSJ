import request from '@/utils/request'

// 查询申请路线子项列表
export function listItem(query) {
  return request({
    url: '/credit/item/list',
    method: 'get',
    params: query
  })
}

// 查询申请路线子项详细
export function getItem(id) {
  return request({
    url: '/credit/item/' + id,
    method: 'get'
  })
}

// 新增申请路线子项
export function addItem(data) {
  return request({
    url: '/credit/item',
    method: 'post',
    data: data
  })
}

// 修改申请路线子项
export function updateItem(data) {
  return request({
    url: '/credit/item',
    method: 'put',
    data: data
  })
}

// 删除申请路线子项
export function delItem(id) {
  return request({
    url: '/credit/item/' + id,
    method: 'delete'
  })
}
