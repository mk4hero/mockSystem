import request from '@/utils/request'

export function listInfo(query) {
  return request({
    url: '/mock/info/list',
    method: 'get',
    params: query
  })
}

export function getInfo(id) {
  return request({
    url: '/mock/info/' + id,
    method: 'get'
  })
}

export function updateInfo(data) {
  return request({
    url: '/mock/info',
    method: 'put',
    data: data
  })
}

export function addInfo(data) {
  return request({
    url: '/mock/info',
    method: 'post',
    data: data
  })
}

export function delInfo(id) {
  return request({
    url: '/mock/info/' + id,
    method: 'delete'
  })
}

// 查询下拉树结构
export function msgTreeSelect(busiCode) {
  return request({
    url: '/mock/info/list',
    method: 'get',
    params: busiCode
    // url: '/system/user/deptTree',
    // method: 'get'
  })
}
