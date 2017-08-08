package com.example.root.demokotlin.presentation.di

import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Created by root on 03/08/2017.
 */

@Qualifier
@Retention(value = AnnotationRetention.RUNTIME)
@Scope
annotation class ForFragment