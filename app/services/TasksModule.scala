package services

import play.api.inject.{SimpleModule, _}

class TasksModule extends SimpleModule(bind[DataProducer].toSelf.eagerly())
