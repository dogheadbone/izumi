package org.bitbucket.pshirshov.izumi.distage.commons

import java.lang.reflect.Method

import org.bitbucket.pshirshov.izumi.distage.{TypeFull, TypeSymb}

import scala.reflect.internal.Symbols
import scala.reflect.runtime.{currentMirror, universe}
import scala.language.reflectiveCalls

object ReflectionUtil {
  def toJavaMethod(definingClass: TypeFull, methodSymbol: TypeSymb): Method = {
    // https://stackoverflow.com/questions/16787163/get-a-java-lang-reflect-method-from-a-reflect-runtime-universe-methodsymbol
    val method = methodSymbol.asMethod
    val runtimeClass = currentMirror.runtimeClass(definingClass.tpe)
    val mirror = universe.runtimeMirror(runtimeClass.getClassLoader)
    val privateMirror = mirror.asInstanceOf[ {
      def methodToJava(sym: Symbols#MethodSymbol): Method
    }]
    val javaMethod = privateMirror.methodToJava(method.asInstanceOf[Symbols#MethodSymbol])
    javaMethod
  }
}
