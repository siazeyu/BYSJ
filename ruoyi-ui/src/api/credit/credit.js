import request from '@/utils/request'

// 查询学分申请列表
export function listCredit(query) {
  return request({
    url: '/credit/credit/list',
    method: 'get',
    params: query
  })
}

// 查询学分申请详细
export function getCredit(id) {
  return request({
    url: '/credit/credit/' + id,
    method: 'get'
  })
}

// 新增学分申请
export function addCredit(data) {
  return request({
    url: '/credit/credit',
    method: 'post',
    data: data
  })
}

// 修改学分申请
export function updateCredit(data) {
  return request({
    url: '/credit/credit',
    method: 'put',
    data: data
  })
}

// 删除学分申请
export function delCredit(id) {
  return request({
    url: '/credit/credit/' + id,
    method: 'delete'
  })
}
