import request from '@/utils/request'

const questionnaireApi = {
    GetAllInPublic: '/questionnaire/queryAllInPublic',
    GetDetail: '/questionnaire/queryDetail',
    Submit: '/questionnaire/submit'
}

export function getAllInPublic() {
    return request({
        url: questionnaireApi.GetAllInPublic,
        method: 'get'
    })
}
export function getDetail(parameter) {
    return request({
        url: questionnaireApi.GetDetail,
        method: 'get',
        params: parameter,
    })
}
export function submit(userId, answers) {
    return request({
        url: questionnaireApi.Submit,
        method: 'post',
        data: {
            userId: userId,
            answers: answers
        }
    })
}