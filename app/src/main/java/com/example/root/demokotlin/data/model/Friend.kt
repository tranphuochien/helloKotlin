package com.example.root.demokotlin.data.model

import org.greenrobot.greendao.annotation.Entity
import org.greenrobot.greendao.annotation.Id

/**
 * Created by root on 03/08/2017.
 */

@Entity
class Friend(@Id var id: String?, var name: String?, var linkAvatar: String?) {
}