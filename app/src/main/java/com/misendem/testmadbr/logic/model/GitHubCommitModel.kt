package com.misendem.testmadbr.logic.model

class GitHubCommitModel(
    var commit: Commit,
    var sha: String
) {
    inner class Commit(
        var message: String,
        var author: GitHubAuthor
    )

    inner class GitHubAuthor(
        var name: String,
        var date: String
    )
}