package com.github.pshirshov.izumi.distage.provisioning

import com.github.pshirshov.izumi.distage.model.provisioning.{Provision, ProvisionImmutable}
import com.github.pshirshov.izumi.distage.model.reflection.universe.RuntimeDIUniverse

import scala.collection.mutable

case class ProvisionActive
(
  instances: mutable.HashMap[RuntimeDIUniverse.DIKey, Any] = mutable.HashMap[RuntimeDIUniverse.DIKey, Any]()
  , imports: mutable.HashMap[RuntimeDIUniverse.DIKey, Any] = mutable.HashMap[RuntimeDIUniverse.DIKey, Any]()
) extends Provision {
  def toImmutable: ProvisionImmutable = {
    ProvisionImmutable(instances.toMap, imports.toMap)
  }

  override def narrow(allRequiredKeys: Set[RuntimeDIUniverse.DIKey]): Provision = {
    toImmutable.narrow(allRequiredKeys)
  }

  override def extend(values: collection.Map[RuntimeDIUniverse.DIKey, Any]): Provision = {
    toImmutable.extend(values)
  }
}
