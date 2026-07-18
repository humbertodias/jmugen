var $wnd = $wnd || window.parent;
var __gwtModuleFunction = $wnd.jmugen;
var $sendStats = __gwtModuleFunction.__sendStats;
$sendStats('moduleStartup', 'moduleEvalStart');
var $gwt_version = "2.10.0";
var $strongName = 'C6D27B900AEF58C909BEB9D358D0CFE0';
var $gwt = {};
var $doc = $wnd.document;
var $moduleName, $moduleBase;
function __gwtStartLoadingFragment(frag) {
var fragFile = 'deferredjs/' + $strongName + '/' + frag + '.cache.js';
return __gwtModuleFunction.__startLoadingFragment(fragFile);
}
function __gwtInstallCode(code) {return __gwtModuleFunction.__installRunAsyncCode(code);}
function __gwt_isKnownPropertyValue(propName, propValue) {
return __gwtModuleFunction.__gwt_isKnownPropertyValue(propName, propValue);
}
function __gwt_getMetaProperty(name) {
return __gwtModuleFunction.__gwt_getMetaProperty(name);
}
var $stats = $wnd.__gwtStatsEvent ? function(a) {
return $wnd.__gwtStatsEvent && $wnd.__gwtStatsEvent(a);
} : null;
var $sessionId = $wnd.__gwtStatsSessionId ? $wnd.__gwtStatsSessionId : null;
var $intern_0 = 2147483647, $intern_1 = {3:1, 12:1, 4:1}, $intern_2 = {3:1}, $intern_3 = {11:1, 3:1}, $intern_4 = 1000000, $intern_5 = 65535, $intern_6 = {21:1, 3:1}, $intern_7 = -16777217, $intern_8 = 0.10000000149011612, $intern_9 = {3:1, 4:1}, $intern_10 = {35:1, 3:1}, $intern_11 = {3:1, 4:1, 61:1}, $intern_12 = 34963, $intern_13 = 34962, $intern_14 = 45.511112213134766, $intern_15 = 16384, $intern_16 = 15525485, $intern_17 = 5.9604644775390625E-8, $intern_18 = 16777216, $intern_19 = 524288, $intern_20 = {3:1, 9:1}, $intern_21 = {3:1, 6:1, 9:1}, $intern_22 = {l:687125, m:2549245, h:648055}, $intern_23 = 0.800000011920929, $intern_24 = {58:1, 52:1, 54:1, 53:1, 59:1, 46:1, 39:1}, $intern_25 = {17:1, 37:1, 3:1, 7:1, 8:1}, $intern_26 = {37:1, 73:1, 3:1, 7:1, 8:1}, $intern_27 = {42:1, 3:1, 7:1, 8:1}, $intern_28 = {108:1, 3:1, 6:1, 9:1}, $intern_29 = 4194303, $intern_30 = 1048575, $intern_31 = 4194304, $intern_32 = 17592186044416, $intern_33 = -17592186044416, $intern_34 = 32768, $intern_35 = 65536, $intern_36 = 131072, $intern_37 = 2097152, $intern_38 = 33554432, $intern_39 = 67108864, $intern_40 = {58:1, 52:1, 54:1, 53:1, 59:1, 142:1, 46:1, 39:1}, $intern_41 = {22:1, 3:1, 6:1, 9:1}, $intern_42 = -2147483648, $intern_43 = Infinity, $intern_44 = -Infinity, $intern_45 = {7:1, 64:1}, $intern_46 = {7:1, 64:1, 105:1}, $intern_47 = {7:1, 88:1}, $intern_48 = {104:1}, $intern_49 = {82:1}, $intern_50 = {43:1}, $intern_51 = {3:1, 82:1}, $intern_52 = {3:1, 4:1, 511:1}, $intern_53 = 0.4000000059604645, $intern_54 = {3:1, 12:1, 4:1, 160:1}, $intern_55 = {3:1, 12:1, 4:1, 159:1}, $intern_56 = 0.8500000238418579, $intern_57 = 2.4000000953674316, $intern_58 = 1.2000000476837158, $intern_59 = 0.009999999776482582, $intern_60 = 5.199999809265137, $intern_61 = 0.6000000238418579, $intern_62 = 1.399999976158142, $intern_63 = {3:1, 4:1, 106:1}, $intern_64 = {3:1, 4:1, 275:1}, $intern_65 = {3:1, 211:1}, $intern_66 = {76:1}, $intern_67 = 0.07999999821186066, $intern_68 = 0.550000011920929, $intern_69 = 0.0010000000474974513, $intern_70 = 9.999999747378752E-5, $intern_71 = {13:1};
var _, prototypesByTypeId_0, initFnList_0, permutationId = -1;
function setGwtProperty(propertyName, propertyValue){
  typeof window === 'object' && typeof window['$gwt'] === 'object' && (window['$gwt'][propertyName] = propertyValue);
}

function gwtOnLoad_0(errFn, modName, modBase, softPermutationId){
  ensureModuleInit();
  var initFnList = initFnList_0;
  $moduleName = modName;
  $moduleBase = modBase;
  permutationId = softPermutationId;
  function initializeModules(){
    for (var i = 0; i < initFnList.length; i++) {
      initFnList[i]();
    }
  }

  if (errFn) {
    try {
      $entry(initializeModules)();
    }
     catch (e) {
      errFn(modName, e);
    }
  }
   else {
    $entry(initializeModules)();
  }
}

function ensureModuleInit(){
  initFnList_0 == null && (initFnList_0 = []);
}

function addInitFunctions(){
  ensureModuleInit();
  var initFnList = initFnList_0;
  for (var i = 0; i < arguments.length; i++) {
    initFnList.push(arguments[i]);
  }
}

function typeMarkerFn(){
}

function toString_30(object){
  var number;
  if (Array.isArray(object) && object.typeMarker === typeMarkerFn) {
    return $getName(getClass__Ljava_lang_Class___devirtual$(object)) + '@' + (number = hashCode__I__devirtual$(object) >>> 0 , number.toString(16));
  }
  return object.toString();
}

function portableObjCreate(obj){
  function F(){
  }

  ;
  F.prototype = obj || {};
  return new F;
}

function emptyMethod(){
}

function defineClass(typeId, superTypeIdOrPrototype, castableTypeMap){
  var prototypesByTypeId = prototypesByTypeId_0, superPrototype;
  var prototype_0 = prototypesByTypeId[typeId];
  var clazz = prototype_0 instanceof Array?prototype_0[0]:null;
  if (prototype_0 && !clazz) {
    _ = prototype_0;
  }
   else {
    _ = (superPrototype = superTypeIdOrPrototype && superTypeIdOrPrototype.prototype , !superPrototype && (superPrototype = prototypesByTypeId_0[superTypeIdOrPrototype]) , portableObjCreate(superPrototype));
    _.castableTypeMap = castableTypeMap;
    !superTypeIdOrPrototype && (_.typeMarker = typeMarkerFn);
    prototypesByTypeId[typeId] = _;
  }
  for (var i = 3; i < arguments.length; ++i) {
    arguments[i].prototype = _;
  }
  clazz && (_.___clazz = clazz);
}

$wnd.goog = $wnd.goog || {};
$wnd.goog.global = $wnd.goog.global || $wnd;
prototypesByTypeId_0 = {};
function $equals(this$static, other){
  return maskUndefined(this$static) === maskUndefined(other);
}

function Object_0(){
}

function equals_Ljava_lang_Object__Z__devirtual$(this$static, other){
  return instanceOfString(this$static)?$equals_5(this$static, other):instanceOfDouble(this$static)?$equals_3(this$static, other):instanceOfBoolean(this$static)?(checkCriticalNotNull(this$static) , maskUndefined(this$static) === maskUndefined(other)):hasJavaObjectVirtualDispatch(this$static)?this$static.equals_0(other):isJavaArray(this$static)?$equals(this$static, other):$equals_0(this$static, other);
}

function getClass__Ljava_lang_Class___devirtual$(this$static){
  return instanceOfString(this$static)?Ljava_lang_String_2_classLit:instanceOfDouble(this$static)?Ljava_lang_Double_2_classLit:instanceOfBoolean(this$static)?Ljava_lang_Boolean_2_classLit:hasJavaObjectVirtualDispatch(this$static)?this$static.___clazz:isJavaArray(this$static)?this$static.___clazz:this$static.___clazz || Array.isArray(this$static) && getClassLiteralForArray(Lcom_google_gwt_core_client_JavaScriptObject_2_classLit, 1) || Lcom_google_gwt_core_client_JavaScriptObject_2_classLit;
}

function hashCode__I__devirtual$(this$static){
  return instanceOfString(this$static)?$hashCode_2(this$static):instanceOfDouble(this$static)?round_int((checkCriticalNotNull(this$static) , this$static)):instanceOfBoolean(this$static)?(checkCriticalNotNull(this$static) , this$static)?1231:1237:hasJavaObjectVirtualDispatch(this$static)?this$static.hashCode_0():isJavaArray(this$static)?getObjectIdentityHashCode(this$static):!!this$static && !!this$static.hashCode?this$static.hashCode():getObjectIdentityHashCode(this$static);
}

defineClass(1, null, {}, Object_0);
_.equals_0 = function equals(other){
  return $equals(this, other);
}
;
_.getClass_0 = function getClass_0(){
  return this.___clazz;
}
;
_.hashCode_0 = function hashCode_0(){
  return getObjectIdentityHashCode(this);
}
;
_.toString_0 = function toString_1(){
  var number;
  return $getName(getClass__Ljava_lang_Class___devirtual$(this)) + '@' + (number = hashCode__I__devirtual$(this) >>> 0 , number.toString(16));
}
;
_.equals = function(other){
  return this.equals_0(other);
}
;
_.hashCode = function(){
  return this.hashCode_0();
}
;
_.toString = function(){
  return this.toString_0();
}
;
function canCast(src_0, dstId){
  if (instanceOfString(src_0)) {
    return !!stringCastMap[dstId];
  }
   else if (src_0.castableTypeMap) {
    return !!src_0.castableTypeMap[dstId];
  }
   else if (instanceOfDouble(src_0)) {
    return !!doubleCastMap[dstId];
  }
   else if (instanceOfBoolean(src_0)) {
    return !!booleanCastMap[dstId];
  }
  return false;
}

function castTo(src_0, dstId){
  checkCriticalType(src_0 == null || canCast(src_0, dstId));
  return src_0;
}

function castToArray(src_0){
  var elementTypeCategory;
  checkCriticalType(src_0 == null || Array.isArray(src_0) && (elementTypeCategory = getElementTypeCategory(src_0) , !(elementTypeCategory >= 14 && elementTypeCategory <= 16)));
  return src_0;
}

function castToBoolean(src_0){
  checkCriticalType(src_0 == null || instanceOfBoolean(src_0));
  return src_0;
}

function castToJso(src_0){
  checkCriticalType(src_0 == null || isJsObjectOrFunction(src_0) && !(src_0.typeMarker === typeMarkerFn));
  return src_0;
}

function castToString(src_0){
  checkCriticalType(src_0 == null || instanceOfString(src_0));
  return src_0;
}

function hasJavaObjectVirtualDispatch(src_0){
  return !Array.isArray(src_0) && src_0.typeMarker === typeMarkerFn;
}

function instanceOf(src_0, dstId){
  return src_0 != null && canCast(src_0, dstId);
}

function instanceOfBoolean(src_0){
  return typeof src_0 === 'boolean';
}

function instanceOfDouble(src_0){
  return typeof src_0 === 'number';
}

function instanceOfJso(src_0){
  return src_0 != null && isJsObjectOrFunction(src_0) && !(src_0.typeMarker === typeMarkerFn);
}

function instanceOfString(src_0){
  return typeof src_0 === 'string';
}

function isJsObjectOrFunction(src_0){
  return typeof src_0 === 'object' || typeof src_0 === 'function';
}

function maskUndefined(src_0){
  return src_0 == null?null:src_0;
}

function round_int(x_0){
  return Math.max(Math.min(x_0, $intern_0), -2147483648) | 0;
}

function throwClassCastExceptionUnlessNull(o){
  checkCriticalType(o == null);
  return o;
}

var booleanCastMap, doubleCastMap, stringCastMap;
function $ensureNamesAreInitialized(this$static){
  if (this$static.typeName != null) {
    return;
  }
  initializeNames(this$static);
}

function $getName(this$static){
  $ensureNamesAreInitialized(this$static);
  return this$static.typeName;
}

function $getSimpleName(this$static){
  $ensureNamesAreInitialized(this$static);
  return this$static.simpleName;
}

function Class(){
  ++nextSequentialId;
  this.typeName = null;
  this.simpleName = null;
  this.packageName = null;
  this.compoundName = null;
  this.canonicalName = null;
  this.typeId = null;
  this.arrayLiterals = null;
}

function createClassObject(packageName, compoundClassName){
  var clazz;
  clazz = new Class;
  clazz.packageName = packageName;
  clazz.compoundName = compoundClassName;
  return clazz;
}

function createForClass(packageName, compoundClassName, typeId){
  var clazz;
  clazz = createClassObject(packageName, compoundClassName);
  maybeSetClassLiteral(typeId, clazz);
  return clazz;
}

function createForEnum(packageName, compoundClassName, typeId, enumConstantsFunc){
  var clazz;
  clazz = createClassObject(packageName, compoundClassName);
  maybeSetClassLiteral(typeId, clazz);
  clazz.modifiers = enumConstantsFunc?8:0;
  clazz.enumConstantsFunc = enumConstantsFunc;
  return clazz;
}

function createForInterface(packageName, compoundClassName){
  var clazz;
  clazz = createClassObject(packageName, compoundClassName);
  clazz.modifiers = 2;
  return clazz;
}

function createForPrimitive(className, primitiveTypeId){
  var clazz;
  clazz = createClassObject('', className);
  clazz.typeId = primitiveTypeId;
  clazz.modifiers = 1;
  return clazz;
}

function getClassLiteralForArray_0(leafClass, dimensions){
  var arrayLiterals = leafClass.arrayLiterals = leafClass.arrayLiterals || [];
  return arrayLiterals[dimensions] || (arrayLiterals[dimensions] = leafClass.createClassLiteralForArray(dimensions));
}

function getPrototypeForClass(clazz){
  if (clazz.isPrimitive()) {
    return null;
  }
  var typeId = clazz.typeId;
  return prototypesByTypeId_0[typeId];
}

function initializeNames(clazz){
  if (clazz.isArray_0()) {
    var componentType = clazz.componentType;
    componentType.isPrimitive()?(clazz.typeName = '[' + componentType.typeId):!componentType.isArray_0()?(clazz.typeName = '[L' + componentType.getName() + ';'):(clazz.typeName = '[' + componentType.getName());
    clazz.canonicalName = componentType.getCanonicalName() + '[]';
    clazz.simpleName = componentType.getSimpleName() + '[]';
    return;
  }
  var packageName = clazz.packageName;
  var compoundName = clazz.compoundName;
  compoundName = compoundName.split('/');
  clazz.typeName = join_0('.', [packageName, join_0('$', compoundName)]);
  clazz.canonicalName = join_0('.', [packageName, join_0('.', compoundName)]);
  clazz.simpleName = compoundName[compoundName.length - 1];
}

function join_0(separator, strings){
  var i = 0;
  while (!strings[i] || strings[i] == '') {
    i++;
  }
  var result = strings[i++];
  for (; i < strings.length; i++) {
    if (!strings[i] || strings[i] == '') {
      continue;
    }
    result += separator + strings[i];
  }
  return result;
}

function maybeSetClassLiteral(typeId, clazz){
  var proto;
  if (!typeId) {
    return;
  }
  clazz.typeId = typeId;
  var prototype_0 = getPrototypeForClass(clazz);
  if (!prototype_0) {
    prototypesByTypeId_0[typeId] = [clazz];
    return;
  }
  prototype_0.___clazz = clazz;
}

defineClass(220, 1, {}, Class);
_.createClassLiteralForArray = function createClassLiteralForArray(dimensions){
  var clazz;
  clazz = new Class;
  clazz.modifiers = 4;
  dimensions > 1?(clazz.componentType = getClassLiteralForArray_0(this, dimensions - 1)):(clazz.componentType = this);
  return clazz;
}
;
_.getCanonicalName = function getCanonicalName(){
  $ensureNamesAreInitialized(this);
  return this.canonicalName;
}
;
_.getName = function getName(){
  return $getName(this);
}
;
_.getSimpleName = function getSimpleName(){
  return $getSimpleName(this);
}
;
_.isArray_0 = function isArray(){
  return (this.modifiers & 4) != 0;
}
;
_.isPrimitive = function isPrimitive(){
  return (this.modifiers & 1) != 0;
}
;
_.toString_0 = function toString_36(){
  return ((this.modifiers & 2) != 0?'interface ':(this.modifiers & 1) != 0?'':'class ') + ($ensureNamesAreInitialized(this) , this.typeName);
}
;
_.modifiers = 0;
var nextSequentialId = 1;
var Ljava_lang_Object_2_classLit = createForClass('java.lang', 'Object', 1);
var Ljava_lang_Class_2_classLit = createForClass('java.lang', 'Class', 220);
defineClass(533, 1, {});
var Lcom_badlogic_gdx_AbstractGraphics_2_classLit = createForClass('com.badlogic.gdx', 'AbstractGraphics', 533);
function $isKeyPressed(this$static, key){
  if (key == -1) {
    return this$static.pressedKeyCount > 0;
  }
  if (key < 0 || key > 255) {
    return false;
  }
  return this$static.pressedKeys[key];
}

defineClass(407, 1, {});
_.keyJustPressed = false;
_.pressedKeyCount = 0;
var Lcom_badlogic_gdx_AbstractInput_2_classLit = createForClass('com.badlogic.gdx', 'AbstractInput', 407);
function $compareTo(this$static, other){
  return this$static.ordinal - other.ordinal;
}

function Enum(name_0, ordinal){
  this.name_0 = name_0;
  this.ordinal = ordinal;
}

function createValueOfMap(enumConstants){
  var result, value_0, value$array, value$index, value$max;
  result = {};
  for (value$array = enumConstants , value$index = 0 , value$max = value$array.length; value$index < value$max; ++value$index) {
    value_0 = value$array[value$index];
    result[':' + (value_0.name_0 != null?value_0.name_0:'' + value_0.ordinal)] = value_0;
  }
  return result;
}

function valueOf(map_0, name_0){
  var result;
  checkCriticalNotNull(name_0);
  result = map_0[':' + name_0];
  checkCriticalArgument_0(!!result, 'Enum constant undefined: ' + name_0);
  return result;
}

defineClass(8, 1, {3:1, 7:1, 8:1});
_.compareTo = function compareTo(other){
  return $compareTo(this, castTo(other, 8));
}
;
_.equals_0 = function equals_0(other){
  return this === other;
}
;
_.hashCode_0 = function hashCode_1(){
  return getObjectIdentityHashCode(this);
}
;
_.toString_0 = function toString_2(){
  return this.name_0 != null?this.name_0:'' + this.ordinal;
}
;
_.ordinal = 0;
var Ljava_lang_Enum_2_classLit = createForClass('java.lang', 'Enum', 8);
function $clinit_Files$FileType(){
  $clinit_Files$FileType = emptyMethod;
  Classpath = new Files$FileType('Classpath', 0);
  Internal = new Files$FileType('Internal', 1);
  External = new Files$FileType('External', 2);
  Absolute = new Files$FileType('Absolute', 3);
  Local = new Files$FileType('Local', 4);
}

function Files$FileType(enum$name, enum$ordinal){
  Enum.call(this, enum$name, enum$ordinal);
}

function values_0(){
  $clinit_Files$FileType();
  return stampJavaTypeInfo(getClassLiteralForArray(Lcom_badlogic_gdx_Files$FileType_2_classLit, 1), $intern_1, 99, 0, [Classpath, Internal, External, Absolute, Local]);
}

defineClass(99, 8, {99:1, 3:1, 7:1, 8:1}, Files$FileType);
var Absolute, Classpath, External, Internal, Local;
var Lcom_badlogic_gdx_Files$FileType_2_classLit = createForEnum('com.badlogic.gdx', 'Files/FileType', 99, values_0);
var app_0, audio_0, files_0, gl_0, gl20, graphics, input_1;
function $newMusic(this$static, file){
  return $createMusic(this$static.webAudioAPIManager, file);
}

function DefaultGwtAudio(){
  new ObjectMap;
  this.webAudioAPIManager = new WebAudioAPIManager;
}

defineClass(284, 1, {}, DefaultGwtAudio);
_.webAudioAPIManager = null;
var Lcom_badlogic_gdx_backends_gwt_DefaultGwtAudio_2_classLit = createForClass('com.badlogic.gdx.backends.gwt', 'DefaultGwtAudio', 284);
function $getAvailablePointer(this$static){
  var i;
  for (i = 0; i < 20; i++) {
    if (!$containsValue(this$static.touchMap, valueOf_1(i)))
      return i;
  }
  return -1;
}

function $getButton(button){
  if (button == 1)
    return 0;
  if (button == 2)
    return 1;
  if (button == 4)
    return 2;
  return 0;
}

function $getMovementXJSNI(event_0){
  return event_0.movementX || event_0.webkitMovementX || 0;
}

function $getMovementYJSNI(event_0){
  return event_0.movementY || event_0.webkitMovementY || 0;
}

function $getRelativeX(e, target){
  var xScaleRatio;
  xScaleRatio = target.width / (target.clientWidth | 0);
  return round_int($wnd.Math.round(xScaleRatio * (((e.clientX || 0) | 0) - $getAbsoluteLeftImpl($getViewportElement(target.ownerDocument), target) + $getScrollLeft(target) + $getScrollLeft_0(target.ownerDocument))));
}

function $getRelativeX_0(touch, target){
  var xScaleRatio;
  xScaleRatio = target.width / (target.clientWidth | 0);
  return round_int($wnd.Math.round(xScaleRatio * (((touch.clientX || 0) | 0) - $getAbsoluteLeftImpl($getViewportElement(target.ownerDocument), target) + $getScrollLeft(target) + $getScrollLeft_0(target.ownerDocument))));
}

function $getRelativeY(e, target){
  var yScaleRatio;
  yScaleRatio = target.height / (target.clientHeight | 0);
  return round_int($wnd.Math.round(yScaleRatio * (((e.clientY || 0) | 0) - $getAbsoluteTopImpl($getViewportElement(target.ownerDocument), target) + ((target.scrollTop || 0) | 0) + $getScrollTop(target.ownerDocument))));
}

function $getRelativeY_0(touch, target){
  var yScaleRatio;
  yScaleRatio = target.height / (target.clientHeight | 0);
  return round_int($wnd.Math.round(yScaleRatio * (((touch.clientY || 0) | 0) - $getAbsoluteTopImpl($getViewportElement(target.ownerDocument), target) + ((target.scrollTop || 0) | 0) + $getScrollTop(target.ownerDocument))));
}

function $isCursorCatchedJSNI(canvas){
  !navigator.pointer && (navigator.pointer = navigator.pointer || navigator.webkitPointer || navigator.mozPointer);
  if (navigator.pointer) {
    if (typeof navigator.pointer.isLocked === 'boolean') {
      return navigator.pointer.isLocked;
    }
     else if (typeof navigator.pointer.isLocked === 'function') {
      return navigator.pointer.isLocked();
    }
     else if (typeof navigator.pointer.islocked === 'function') {
      return navigator.pointer.islocked();
    }
  }
  if ($doc.pointerLockElement === canvas || $doc.mozPointerLockElement === canvas) {
    return true;
  }
  return false;
}

function $isTouched(this$static){
  var pointer;
  for (pointer = 0; pointer < 20; pointer++) {
    if (this$static.touched[pointer]) {
      return true;
    }
  }
  return false;
}

function $keyForCode(keyCode, location_0){
  switch (keyCode) {
    case 18:
      return location_0 == 2?58:57;
    case 8:
      return 67;
    case 17:
      return location_0 == 2?130:129;
    case 46:
      return 112;
    case 40:
      return 20;
    case 35:
      return 123;
    case 13:
      return location_0 == 3?160:66;
    case 27:
      return 111;
    case 36:
      return 3;
    case 37:
      return 21;
    case 34:
      return 93;
    case 33:
      return 92;
    case 39:
      return 22;
    case 16:
      return location_0 == 2?60:59;
    case 9:
      return 61;
    case 38:
      return 19;
    case 19:
      return 121;
    case 20:
      return 115;
    case 32:
      return 62;
    case 45:
      return 124;
    case 48:
      return 7;
    case 49:
      return 8;
    case 50:
      return 9;
    case 51:
      return 10;
    case 52:
      return 11;
    case 53:
      return 12;
    case 54:
      return 13;
    case 55:
      return 14;
    case 56:
      return 15;
    case 57:
      return 16;
    case 65:
      return 29;
    case 66:
      return 30;
    case 67:
      return 31;
    case 68:
      return 32;
    case 69:
      return 33;
    case 70:
      return 34;
    case 71:
      return 35;
    case 72:
      return 36;
    case 73:
      return 37;
    case 74:
      return 38;
    case 75:
      return 39;
    case 76:
      return 40;
    case 77:
      return 41;
    case 78:
      return 42;
    case 79:
      return 43;
    case 80:
      return 44;
    case 81:
      return 45;
    case 82:
      return 46;
    case 83:
      return 47;
    case 84:
      return 48;
    case 85:
      return 49;
    case 86:
      return 50;
    case 87:
      return 51;
    case 88:
      return 52;
    case 89:
      return 53;
    case 90:
      return 54;
    case 92:
    case 192:
    default:case 91:
      return 0;
    case 96:
      return 144;
    case 97:
      return 145;
    case 98:
      return 146;
    case 99:
      return 147;
    case 100:
      return 148;
    case 101:
      return 149;
    case 102:
      return 150;
    case 103:
      return 151;
    case 104:
      return 152;
    case 105:
      return 153;
    case 106:
      return 155;
    case 107:
      return 157;
    case 109:
      return 156;
    case 110:
      return 158;
    case 111:
      return 154;
    case 112:
      return 131;
    case 113:
      return 132;
    case 114:
      return 133;
    case 115:
      return 134;
    case 116:
      return 135;
    case 117:
      return 136;
    case 118:
      return 137;
    case 119:
      return 138;
    case 120:
      return 139;
    case 121:
      return 140;
    case 122:
      return 141;
    case 123:
      return 142;
    case 124:
      return 183;
    case 125:
      return 184;
    case 126:
      return 185;
    case 127:
      return 186;
    case 128:
      return 187;
    case 129:
      return 188;
    case 130:
      return 189;
    case 131:
      return 190;
    case 132:
      return 191;
    case 133:
      return 192;
    case 134:
      return 193;
    case 135:
      return 194;
    case 144:
      return 143;
    case 145:
      return 116;
    case 174:
    case 182:
      return 25;
    case 175:
    case 183:
      return 24;
    case 176:
      return 87;
    case 177:
      return 88;
    case 178:
      return 86;
    case 179:
      return 85;
    case 44:
      return 120;
    case 186:
      return 74;
    case 187:
      return 70;
    case 188:
      return 55;
    case 189:
      return 69;
    case 190:
      return 56;
    case 191:
      return 76;
    case 219:
      return 71;
    case 220:
      return 73;
    case 221:
      return 72;
    case 222:
      return 75;
  }
}

function $reset(this$static){
  var i;
  if (this$static.justTouched) {
    this$static.justTouched = false;
    for (i = 0; i < this$static.justPressedButtons.length; i++) {
      this$static.justPressedButtons[i] = false;
    }
  }
  if (this$static.keyJustPressed) {
    this$static.keyJustPressed = false;
    for (i = 0; i < this$static.justPressedKeys.length; i++) {
      this$static.justPressedKeys[i] = false;
    }
  }
}

function $setupAccelerometer(this$static){
  if ('Accelerometer' in $wnd && allowsFeature('accelerometer')) {
    !this$static.accelerometer && (this$static.accelerometer = new $wnd.Accelerometer);
    this$static.accelerometer.activated || (this$static.accelerometer.start() , undefined);
  }
}

function DefaultGwtInput(canvas, config){
  this.keysToCatch = new IntSet;
  this.pressedKeys = initUnidimensionalArray(Z_classLit, $intern_2, 5, 256, 16, 1);
  this.justPressedKeys = initUnidimensionalArray(Z_classLit, $intern_2, 5, 256, 16, 1);
  this.touchMap = new IntMap;
  this.touched = initUnidimensionalArray(Z_classLit, $intern_2, 5, 20, 16, 1);
  this.touchX = initUnidimensionalArray(I_classLit, $intern_3, 5, 20, 15, 1);
  this.touchY = initUnidimensionalArray(I_classLit, $intern_3, 5, 20, 15, 1);
  this.deltaX = initUnidimensionalArray(I_classLit, $intern_3, 5, 20, 15, 1);
  this.deltaY = initUnidimensionalArray(I_classLit, $intern_3, 5, 20, 15, 1);
  this.pressedButtons = new IntSet;
  this.pressedKeySet = new IntSet;
  this.justPressedButtons = initUnidimensionalArray(Z_classLit, $intern_2, 5, 5, 16, 1);
  this.canvas = canvas;
  config.useAccelerometer && allowsFeature('accelerometer') && (agentInfo.isFirefox?$setupAccelerometer(this):queryPermission('accelerometer', new DefaultGwtInput$1(this)));
  addEventListener_0(this.canvas, 'mousedown', this, true);
  addEventListener_0($doc, 'mousedown', this, true);
  addEventListener_0(this.canvas, 'mouseup', this, true);
  addEventListener_0($doc, 'mouseup', this, true);
  addEventListener_0(this.canvas, 'mousemove', this, true);
  addEventListener_0($doc, 'mousemove', this, true);
  addEventListener_0(this.canvas, getMouseWheelEvent(), this, true);
  addEventListener_0($doc, 'keydown', this, false);
  addEventListener_0($doc, 'keyup', this, false);
  addEventListener_0($doc, 'keypress', this, false);
  addEventListener_0($wnd, 'blur', this, false);
  addEventListener_0(this.canvas, 'touchstart', this, true);
  addEventListener_0(this.canvas, 'touchmove', this, true);
  addEventListener_0(this.canvas, 'touchcancel', this, true);
  addEventListener_0(this.canvas, 'touchend', this, true);
  $add_2(this.keysToCatch, 67);
}

function addEventListener_0(target, name_0, handler, capture){
  target.addEventListener(name_0, function(e){
    handler.handleEvent(e);
  }
  , capture);
}

function getMouseWheelEvent(){
  if (navigator.userAgent.toLowerCase().indexOf('firefox') != -1) {
    return 'DOMMouseScroll';
  }
   else {
    return 'mousewheel';
  }
}

defineClass(286, 407, {}, DefaultGwtInput);
_.handleEvent = function handleEvent(e){
  var button, code_0, i, iterator, j, mouseX, mouseY, real, touch, touchId, touches;
  if ($equals_5(e.type, 'mousedown')) {
    if (!$equals_0(e.target, this.canvas) || $contains_0(this.pressedButtons, $getButton($eventGetButton(e)))) {
      mouseX = $getRelativeX(e, this.canvas);
      mouseY = $getRelativeY(e, this.canvas);
      (mouseX < 0 || mouseX > graphics.canvas.width || mouseY < 0 || mouseY > graphics.canvas.height) && (this.hasFocus = false);
      return;
    }
    this.hasFocus = true;
    this.justTouched = true;
    this.touched[0] = true;
    button = $getButton($eventGetButton(e));
    $add_2(this.pressedButtons, button);
    this.justPressedButtons[button] = true;
    this.deltaX[0] = 0;
    this.deltaY[0] = 0;
    if ($isCursorCatchedJSNI(this.canvas)) {
      this.touchX[0] = round_int(this.touchX[0] + $getMovementXJSNI(e));
      this.touchY[0] = round_int(this.touchY[0] + $getMovementYJSNI(e));
    }
     else {
      this.touchX[0] = $getRelativeX(e, this.canvas);
      this.touchY[0] = $getRelativeY(e, this.canvas);
    }
    $clinit_System();
    mul_1(fromDouble_0(Date.now()), $intern_4);
  }
  if ($equals_5(e.type, 'mousemove')) {
    if ($isCursorCatchedJSNI(this.canvas)) {
      this.deltaX[0] = round_int($getMovementXJSNI(e));
      this.deltaY[0] = round_int($getMovementYJSNI(e));
      this.touchX[0] = round_int(this.touchX[0] + $getMovementXJSNI(e));
      this.touchY[0] = round_int(this.touchY[0] + $getMovementYJSNI(e));
    }
     else {
      this.deltaX[0] = $getRelativeX(e, this.canvas) - this.touchX[0];
      this.deltaY[0] = $getRelativeY(e, this.canvas) - this.touchY[0];
      this.touchX[0] = $getRelativeX(e, this.canvas);
      this.touchY[0] = $getRelativeY(e, this.canvas);
    }
    $clinit_System();
    mul_1(fromDouble_0(Date.now()), $intern_4);
  }
  if ($equals_5(e.type, 'mouseup')) {
    if (!$contains_0(this.pressedButtons, $getButton($eventGetButton(e))))
      return;
    $remove_0(this.pressedButtons, $getButton($eventGetButton(e)));
    this.touched[0] = this.pressedButtons.size_0 > 0;
    if ($isCursorCatchedJSNI(this.canvas)) {
      this.deltaX[0] = round_int($getMovementXJSNI(e));
      this.deltaY[0] = round_int($getMovementYJSNI(e));
      this.touchX[0] = round_int(this.touchX[0] + $getMovementXJSNI(e));
      this.touchY[0] = round_int(this.touchY[0] + $getMovementYJSNI(e));
    }
     else {
      this.deltaX[0] = $getRelativeX(e, this.canvas) - this.touchX[0];
      this.deltaY[0] = $getRelativeY(e, this.canvas) - this.touchY[0];
      this.touchX[0] = $getRelativeX(e, this.canvas);
      this.touchY[0] = $getRelativeY(e, this.canvas);
    }
    $clinit_System();
    mul_1(fromDouble_0(Date.now()), $intern_4);
    this.touched[0] = false;
  }
  if ($equals_5(e.type, getMouseWheelEvent())) {
    $clinit_System();
    mul_1(fromDouble_0(Date.now()), $intern_4);
    e.preventDefault();
  }
  if (this.hasFocus && !$equals_5(e.type, 'blur')) {
    if ($equals_5(e.type, 'keydown')) {
      code_0 = $keyForCode(e.keyCode | 0, e.location || 0);
      $contains_0(this.keysToCatch, code_0) && (e.preventDefault() , undefined);
      if (code_0 == 67)
      ;
      else {
        if (!this.pressedKeys[code_0]) {
          $add_2(this.pressedKeySet, code_0);
          ++this.pressedKeyCount;
          this.pressedKeys[code_0] = true;
          this.keyJustPressed = true;
          this.justPressedKeys[code_0] = true;
        }
      }
    }
    $equals_5(e.type, 'keypress') && (e.charCode || 0) & $intern_5;
    if ($equals_5(e.type, 'keyup')) {
      code_0 = $keyForCode(e.keyCode | 0, e.location || 0);
      $contains_0(this.keysToCatch, code_0) && (e.preventDefault() , undefined);
      if (this.pressedKeys[code_0]) {
        $remove_0(this.pressedKeySet, code_0);
        --this.pressedKeyCount;
        this.pressedKeys[code_0] = false;
      }
    }
  }
   else if (this.pressedKeyCount > 0) {
    iterator = $iterator_2(this.pressedKeySet);
    while (iterator.hasNext) {
      code_0 = $next_1(iterator);
      if (this.pressedKeys[code_0]) {
        $remove_0(this.pressedKeySet, code_0);
        --this.pressedKeyCount;
        this.pressedKeys[code_0] = false;
      }
    }
  }
  if ($equals_5(e.type, 'touchstart')) {
    this.justTouched = true;
    touches = e.changedTouches;
    for (i = 0 , j = touches.length; i < j; i++) {
      touch = touches[i];
      real = touch.identifier;
      $put(this.touchMap, real, valueOf_1(touchId = $getAvailablePointer(this)));
      this.touched[touchId] = true;
      this.touchX[touchId] = $getRelativeX_0(touch, this.canvas);
      this.touchY[touchId] = $getRelativeY_0(touch, this.canvas);
      this.deltaX[touchId] = 0;
      this.deltaY[touchId] = 0;
    }
    $clinit_System();
    mul_1(fromDouble_0(Date.now()), $intern_4);
    e.preventDefault();
  }
  if ($equals_5(e.type, 'touchmove')) {
    touches = e.changedTouches;
    for (i = 0 , j = touches.length; i < j; i++) {
      touch = touches[i];
      real = touch.identifier;
      touchId = castTo($get_3(this.touchMap, real), 44).value_0;
      this.deltaX[touchId] = $getRelativeX_0(touch, this.canvas) - this.touchX[touchId];
      this.deltaY[touchId] = $getRelativeY_0(touch, this.canvas) - this.touchY[touchId];
      this.touchX[touchId] = $getRelativeX_0(touch, this.canvas);
      this.touchY[touchId] = $getRelativeY_0(touch, this.canvas);
    }
    $clinit_System();
    mul_1(fromDouble_0(Date.now()), $intern_4);
    e.preventDefault();
  }
  if ($equals_5(e.type, 'touchcancel')) {
    touches = e.changedTouches;
    for (i = 0 , j = touches.length; i < j; i++) {
      touch = touches[i];
      real = touch.identifier;
      touchId = castTo($get_3(this.touchMap, real), 44).value_0;
      $remove(this.touchMap, real);
      this.touched[touchId] = false;
      this.deltaX[touchId] = $getRelativeX_0(touch, this.canvas) - this.touchX[touchId];
      this.deltaY[touchId] = $getRelativeY_0(touch, this.canvas) - this.touchY[touchId];
      this.touchX[touchId] = $getRelativeX_0(touch, this.canvas);
      this.touchY[touchId] = $getRelativeY_0(touch, this.canvas);
    }
    $clinit_System();
    mul_1(fromDouble_0(Date.now()), $intern_4);
    e.preventDefault();
  }
  if ($equals_5(e.type, 'touchend')) {
    touches = e.changedTouches;
    for (i = 0 , j = touches.length; i < j; i++) {
      touch = touches[i];
      real = touch.identifier;
      touchId = castTo($get_3(this.touchMap, real), 44).value_0;
      $remove(this.touchMap, real);
      this.touched[touchId] = false;
      this.deltaX[touchId] = $getRelativeX_0(touch, this.canvas) - this.touchX[touchId];
      this.deltaY[touchId] = $getRelativeY_0(touch, this.canvas) - this.touchY[touchId];
      this.touchX[touchId] = $getRelativeX_0(touch, this.canvas);
      this.touchY[touchId] = $getRelativeY_0(touch, this.canvas);
    }
    $clinit_System();
    mul_1(fromDouble_0(Date.now()), $intern_4);
    e.preventDefault();
  }
}
;
_.hasFocus = true;
_.justTouched = false;
var Lcom_badlogic_gdx_backends_gwt_DefaultGwtInput_2_classLit = createForClass('com.badlogic.gdx.backends.gwt', 'DefaultGwtInput', 286);
function DefaultGwtInput$1(this$0){
  this.this$01 = this$0;
}

defineClass(408, 1, {}, DefaultGwtInput$1);
_.denied = function denied(){
}
;
_.granted = function granted(){
  $setupAccelerometer(this.this$01);
}
;
_.prompt_0 = function prompt_0(){
  $setupAccelerometer(this.this$01);
}
;
var Lcom_badlogic_gdx_backends_gwt_DefaultGwtInput$1_2_classLit = createForClass('com.badlogic.gdx.backends.gwt', 'DefaultGwtInput/1', 408);
function $equals_0(this$static, other){
  return !!this$static && !!this$static.equals?this$static.equals(other):maskUndefined(this$static) === maskUndefined(other);
}

var Lcom_google_gwt_core_client_JavaScriptObject_2_classLit = createForClass('com.google.gwt.core.client', 'JavaScriptObject$', 0);
function $addEventListeners(this$static){
  var self_0 = this$static;
  var eventName = null;
  'hidden' in $doc?(eventName = 'visibilitychange'):'webkitHidden' in $doc?(eventName = 'webkitvisibilitychange'):'mozHidden' in $doc?(eventName = 'mozvisibilitychange'):'msHidden' in $doc && (eventName = 'msvisibilitychange');
  eventName !== null && $doc.addEventListener(eventName, function(e){
    self_0.onVisibilityChange($doc['hidden'] !== true);
  }
  );
}

function $addLifecycleListener(this$static, listener){
  $add_0(this$static.lifecycleListeners, listener);
}

function $createPreloaderPanel(this$static, logoUrl){
  var logo, meter, meterPanel, meterStyle, preloaderPanel;
  preloaderPanel = new VerticalPanel;
  ($clinit_DOM() , preloaderPanel.element).className = 'gdx-preloader';
  logo = new Image_2(logoUrl);
  logo.element.className = 'logo';
  $add_6(preloaderPanel, logo);
  meterPanel = new SimplePanel;
  meter = new InlineHTML;
  meterStyle = meter.element.style;
  meterStyle['width'] = ($clinit_Style$Unit() , '0.0%');
  meterPanel.element.className = 'gdx-meter';
  setStyleName(meterPanel.element, 'red');
  $add_5(meterPanel, meter);
  $add_6(preloaderPanel, meterPanel);
  $add_6(this$static.root, preloaderPanel);
  return new GwtApplication$3(meterStyle);
}

function $error(this$static, tag, message){
  this$static.logLevel >= 1 && (console.error(tag + ': ' + message) , undefined);
}

function $error_0(this$static, tag, message, exception){
  this$static.logLevel >= 1 && ($logText(tag + ': ' + message + '\n' + $getMessages(exception), true) , $logText($getStackTrace(exception), false));
}

function $log(this$static, tag, message){
  this$static.logLevel >= 2 && (console.log(tag + ': ' + message) , undefined);
}

function $mainLoop(this$static){
  var i;
  $update_1(this$static.graphics);
  if (graphics.canvas.width != this$static.lastWidth || graphics.canvas.height != this$static.lastHeight) {
    this$static.lastWidth = this$static.graphics.canvas.width;
    this$static.lastHeight = this$static.graphics.canvas.height;
    $glViewport(gl_0, 0, 0, this$static.lastWidth, this$static.lastHeight);
    $resize_5(this$static.listener, this$static.lastWidth, this$static.lastHeight);
  }
  $addAll(this$static.runnablesHelper, this$static.runnables);
  $clear(this$static.runnables);
  for (i = 0; i < this$static.runnablesHelper.size_0; i++) {
    castTo($get_1(this$static.runnablesHelper, i), 215).run();
  }
  $clear(this$static.runnablesHelper);
  this$static.graphics.frameId = add_2(this$static.graphics.frameId, 1);
  $render_10(this$static.listener);
  $reset(this$static.input_0);
}

function $onModuleLoad(this$static){
  var density, element, height, panel, width_0, window_0, userAgent, window_1, callback, moduleUrl, correctLength;
  agentInfo = (userAgent = navigator.userAgent.toLowerCase() , {isFirefox:userAgent.indexOf('firefox') != -1, isChrome:userAgent.indexOf('chrome') != -1, isSafari:userAgent.indexOf('safari') != -1, isOpera:userAgent.indexOf('opera') != -1, isIE:userAgent.indexOf('msie') != -1 || userAgent.indexOf('trident') != -1, isMacOS:userAgent.indexOf('mac') != -1, isLinux:userAgent.indexOf('linux') != -1, isWindows:userAgent.indexOf('win') != -1});
  this$static.listener = (initialize() , prepare() , window_1 = (initialized || initialize() , windowInstance) , $setGameWindowCallback(window_1, (!instance_0 && (instance_0 = new GameMenu) , instance_0)) , window_1);
  this$static.config = (window_0 = (initialized || initialize() , windowInstance) , new GwtApplicationConfiguration(window_0.width_0, window_0.height_0));
  element = $getElementById($doc, 'embed-' + ($clinit_Impl() , $moduleName));
  if ($isFixedSizeApplication(this$static.config)) {
    width_0 = this$static.config.width_0;
    height = this$static.config.height_0;
    if (this$static.config.usePhysicalPixels) {
      density = $wnd.devicePixelRatio || 1;
      width_0 = round_int(width_0 / density);
      height = round_int(height / density);
    }
  }
   else {
    width_0 = $getClientWidth($doc) - this$static.config.padHorizontal;
    height = $getClientHeight($doc) - this$static.config.padVertical;
    $enableScrolling($doc);
    $doc.body.style.margin = '0';
    addResizeHandler(new GwtApplication$ResizeListener(this$static));
  }
  if (!element) {
    panel = new VerticalPanel;
    ($clinit_DOM() , panel.element).style['width'] = '' + width_0 + 'px';
    panel.element.style['height'] = '' + height + 'px';
    $setHorizontalAlignment(panel, ($clinit_HasHorizontalAlignment() , ALIGN_CENTER));
    $setVerticalAlignment(panel, ($clinit_HasVerticalAlignment() , ALIGN_MIDDLE));
    $add_4(($clinit_RootPanel() , get_0()), panel);
    $setWidth(get_0(), '' + width_0 + 'px');
    $setHeight(get_0(), '' + height + 'px');
    this$static.root = panel;
  }
   else {
    panel = new VerticalPanel;
    ($clinit_DOM() , panel.element).style['width'] = '' + width_0 + 'px';
    panel.element.style['height'] = '' + height + 'px';
    $setHorizontalAlignment(panel, ($clinit_HasHorizontalAlignment() , ALIGN_CENTER));
    $setVerticalAlignment(panel, ($clinit_HasVerticalAlignment() , ALIGN_MIDDLE));
    $appendChild(element, panel.element);
    this$static.root = panel;
  }
  callback = $createPreloaderPanel(this$static, getModuleBaseURL() + 'logo.png');
  this$static.preloader = new Preloader((moduleUrl = getModuleBaseURL() , correctLength = moduleUrl.length - $moduleName.length - 1 , checkCriticalStringBounds(0, correctLength, moduleUrl.length) , moduleUrl.substr(0, correctLength) + 'assets/'));
  $preload(this$static.preloader, new GwtApplication$1(this$static, callback));
}

function $postRunnable(this$static, runnable){
  $add_0(this$static.runnables, runnable);
}

function $setupLoop(this$static){
  var e, t;
  app_0 = this$static;
  try {
    this$static.graphics = new GwtGraphics(this$static.root, this$static.config);
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 9)) {
      e = $e0;
      $clear_0(this$static.root);
      $error_0(this$static, 'GwtApplication', 'exception: ' + e.getMessage(), e);
      $add_6(this$static.root, new Label);
      return;
    }
     else 
      throw toJs($e0);
  }
  this$static.lastWidth = this$static.graphics.canvas.width;
  this$static.lastHeight = this$static.graphics.canvas.height;
  graphics = this$static.graphics;
  gl20 = this$static.graphics.gl20;
  gl_0 = gl20;
  this$static.config.disableAudio?(this$static.audio = null):(this$static.audio = new DefaultGwtAudio);
  audio_0 = this$static.audio;
  files_0 = new GwtFiles(this$static.preloader);
  this$static.input_0 = new DefaultGwtInput(this$static.graphics.canvas, this$static.config);
  input_1 = this$static.input_0;
  new GwtNet;
  new GwtClipboard;
  try {
    $create_0(this$static.listener);
    $resize_5(this$static.listener, this$static.graphics.canvas.width, this$static.graphics.canvas.height);
  }
   catch ($e1) {
    $e1 = toJava($e1);
    if (instanceOf($e1, 9)) {
      t = $e1;
      $error_0(this$static, 'GwtApplication', 'exception: ' + t.getMessage(), t);
      $printStackTraceImpl(t, ($clinit_System() , err_0), '');
      throw toJs(new RuntimeException_2(t));
    }
     else 
      throw toJs($e1);
  }
  (!instance && (instance = $isNativelySupported()?new AnimationSchedulerImplStandard:new AnimationSchedulerImplTimer) , instance).requestAnimationFrame_0(new GwtApplication$2(this$static), this$static.graphics.canvas);
}

defineClass(279, 1, {});
_.onVisibilityChange = function onVisibilityChange(visible){
  var listener, listener$iterator, audioContext, gainNode, audioContext_0, gainNode_0;
  if (visible) {
    for (listener$iterator = $iterator_0(this.lifecycleListeners); $hasNext(listener$iterator);) {
      listener = castTo($next(listener$iterator), 292);
      audioContext = listener.audioContext;
      gainNode = listener.globalVolumeNode;
      gainNode.connect(audioContext.destination);
    }
  }
   else {
    for (listener$iterator = $iterator_0(this.lifecycleListeners); $hasNext(listener$iterator);) {
      listener = castTo($next(listener$iterator), 292);
      audioContext_0 = listener.audioContext;
      gainNode_0 = listener.globalVolumeNode;
      gainNode_0.disconnect(audioContext_0.destination);
    }
  }
}
;
_.lastHeight = 0;
_.lastWidth = 0;
_.logLevel = 1;
_.root = null;
var agentInfo;
var Lcom_badlogic_gdx_backends_gwt_GwtApplication_2_classLit = createForClass('com.badlogic.gdx.backends.gwt', 'GwtApplication', 279);
function $update(this$static, state){
  $update_0(this$static.val$callback2, state);
  if (eq_0($getDownloadedSize(state), $getTotalSize(state))) {
    $clear_0(this$static.this$01.root);
    $setupLoop(this$static.this$01);
    $addEventListeners(this$static.this$01);
  }
}

function GwtApplication$1(this$0, val$callback){
  this.this$01 = this$0;
  this.val$callback2 = val$callback;
}

defineClass(282, 1, {}, GwtApplication$1);
var Lcom_badlogic_gdx_backends_gwt_GwtApplication$1_2_classLit = createForClass('com.badlogic.gdx.backends.gwt', 'GwtApplication/1', 282);
function $execute(this$static){
  var t;
  try {
    $mainLoop(this$static.this$01);
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 9)) {
      t = $e0;
      $error_0(this$static.this$01, 'GwtApplication', 'exception: ' + t.getMessage(), t);
      throw toJs(new RuntimeException_2(t));
    }
     else 
      throw toJs($e0);
  }
  (!instance && (instance = $isNativelySupported()?new AnimationSchedulerImplStandard:new AnimationSchedulerImplTimer) , instance).requestAnimationFrame_0(this$static, this$static.this$01.graphics.canvas);
}

function GwtApplication$2(this$0){
  this.this$01 = this$0;
}

defineClass(289, 1, {}, GwtApplication$2);
_.execute = function execute(timestamp){
  $execute(this);
}
;
var Lcom_badlogic_gdx_backends_gwt_GwtApplication$2_2_classLit = createForClass('com.badlogic.gdx.backends.gwt', 'GwtApplication/2', 289);
function $update_0(this$static, state){
  var total;
  this$static.val$meterStyle2['width'] = (total = $getTotalSize(state) , 100 * (compare_0(total, 0) == 0?1:toDouble_0($getDownloadedSize(state)) / toDouble_0(total)) + ($clinit_Style$Unit() , '%'));
}

function GwtApplication$3(val$meterStyle){
  this.val$meterStyle2 = val$meterStyle;
}

defineClass(291, 1, {}, GwtApplication$3);
var Lcom_badlogic_gdx_backends_gwt_GwtApplication$3_2_classLit = createForClass('com.badlogic.gdx.backends.gwt', 'GwtApplication/3', 291);
function $onResize(this$static, event_0){
  var density, height, width_0;
  width_0 = event_0.width_0 - this$static.this$01.config.padHorizontal;
  height = event_0.height_0 - this$static.this$01.config.padVertical;
  if (width_0 == 0 || height == 0) {
    return;
  }
  $setWidth(this$static.this$01.root, '' + width_0 + 'px');
  $setHeight(this$static.this$01.root, '' + height + 'px');
  if (this$static.this$01.graphics) {
    if (this$static.this$01.config.usePhysicalPixels) {
      density = $wnd.devicePixelRatio || 1;
      width_0 = round_int(width_0 * density);
      height = round_int(height * density);
    }
    $setCanvasSize(this$static.this$01.graphics, width_0, height);
  }
}

function GwtApplication$ResizeListener(this$0){
  this.this$01 = this$0;
}

defineClass(280, 1, {513:1, 293:1}, GwtApplication$ResizeListener);
var Lcom_badlogic_gdx_backends_gwt_GwtApplication$ResizeListener_2_classLit = createForClass('com.badlogic.gdx.backends.gwt', 'GwtApplication/ResizeListener', 280);
function $isFixedSizeApplication(this$static){
  return this$static.width_0 != 0 && this$static.height_0 != 0;
}

function GwtApplicationConfiguration(width_0, height){
  this.width_0 = width_0;
  this.height_0 = height;
  this.usePhysicalPixels = false;
}

defineClass(212, 1, {}, GwtApplicationConfiguration);
_.disableAudio = false;
_.height_0 = 0;
_.padHorizontal = 10;
_.padVertical = 10;
_.useAccelerometer = true;
_.usePhysicalPixels = false;
_.width_0 = 0;
var Lcom_badlogic_gdx_backends_gwt_GwtApplicationConfiguration_2_classLit = createForClass('com.badlogic.gdx.backends.gwt', 'GwtApplicationConfiguration', 212);
function $getMessages(e){
  var sb;
  sb = new StringBuilder;
  while (e) {
    $append_3(sb, e.getMessage() + '\n');
    e = e.cause_0;
  }
  return sb.string;
}

function $getStackTrace(e){
  var sb, trace, trace$array, trace$index, trace$max;
  sb = new StringBuilder;
  for (trace$array = (e.stackTrace == null && (e.stackTrace = constructJavaStackTrace(e)) , e.stackTrace) , trace$index = 0 , trace$max = trace$array.length; trace$index < trace$max; ++trace$index) {
    trace = trace$array[trace$index];
    $append_3(sb, trace.className_0 + '.' + trace.methodName + '(' + (trace.fileName != null?trace.fileName:'Unknown Source') + (trace.lineNumber >= 0?':' + trace.lineNumber:'') + ')' + '\n');
  }
  return sb.string;
}

function $logText(message, error){
  error?(console.error(message) , undefined):(console.log(message) , undefined);
}

function $setContentJSNI(content_0){
  'clipboard' in $wnd.navigator && $wnd.navigator.clipboard.writeText(content_0);
}

function GwtClipboard(){
  new GwtClipboard$ClipboardWriteHandler(this);
}

defineClass(288, 1, {}, GwtClipboard);
_.content_0 = '';
var Lcom_badlogic_gdx_backends_gwt_GwtClipboard_2_classLit = createForClass('com.badlogic.gdx.backends.gwt', 'GwtClipboard', 288);
function GwtClipboard$ClipboardWriteHandler(this$0){
  this.this$01 = this$0;
}

defineClass(400, 1, {}, GwtClipboard$ClipboardWriteHandler);
_.denied = function denied_0(){
}
;
_.granted = function granted_0(){
  $setContentJSNI(this.this$01.content_0);
}
;
_.prompt_0 = function prompt_1(){
  $setContentJSNI(this.this$01.content_0);
}
;
var Lcom_badlogic_gdx_backends_gwt_GwtClipboard$ClipboardWriteHandler_2_classLit = createForClass('com.badlogic.gdx.backends.gwt', 'GwtClipboard/ClipboardWriteHandler', 400);
function allowsFeature(feature){
  if (!('featurePolicy' in $wnd.document))
    return true;
  return $wnd.document.featurePolicy.allowsFeature(feature);
}

defineClass(548, 1, {});
_.toString_0 = function toString_3(){
  throw toJs(new GdxRuntimeException('Stub'));
}
;
var Lcom_badlogic_gdx_files_FileHandle_2_classLit = createForClass('com.badlogic.gdx.files', 'FileHandle', 548);
function $read(this$static){
  var in_0;
  in_0 = $read_1(this$static.preloader, this$static.file);
  if (!in_0)
    throw toJs(new GdxRuntimeException(this$static.file + ' does not exist'));
  return in_0;
}

function $readBytes(this$static){
  var $caught_ex_9, $primary_ex_8, buffer, count, ex, input_0, length_0, newBuffer, position;
  length_0 = toInt_0($length(this$static.preloader, this$static.file));
  length_0 == 0 && (length_0 = 512);
  buffer = initUnidimensionalArray(B_classLit, $intern_6, 5, length_0, 15, 1);
  position = 0;
  try {
    $primary_ex_8 = null;
    try {
      input_0 = $read(this$static);
      while (true) {
        count = input_0.read_0(buffer, position, buffer.length - position);
        if (count == -1)
          break;
        position += count;
        if (position == buffer.length) {
          newBuffer = initUnidimensionalArray(B_classLit, $intern_6, 5, buffer.length * 2, 15, 1);
          arraycopy(buffer, 0, newBuffer, 0, position);
          buffer = newBuffer;
        }
      }
    }
     catch ($e0) {
      $e0 = toJava($e0);
      if (instanceOf($e0, 9)) {
        $caught_ex_9 = $e0;
        $primary_ex_8 = $caught_ex_9;
        throw toJs($primary_ex_8);
      }
       else 
        throw toJs($e0);
    }
     finally {
      $primary_ex_8 = safeClose(input_0, $primary_ex_8);
      if ($primary_ex_8)
        throw toJs($primary_ex_8);
    }
  }
   catch ($e1) {
    $e1 = toJava($e1);
    if (instanceOf($e1, 22)) {
      ex = $e1;
      throw toJs(new GdxRuntimeException_0('Error reading file: ' + this$static, ex));
    }
     else 
      throw toJs($e1);
  }
  if (position < buffer.length) {
    newBuffer = initUnidimensionalArray(B_classLit, $intern_6, 5, position, 15, 1);
    arraycopy(buffer, 0, newBuffer, 0, position);
    buffer = newBuffer;
  }
  return buffer;
}

function GwtFileHandle(preloader, fileName, type_0){
  if (type_0 != ($clinit_Files$FileType() , Internal) && type_0 != Classpath)
    throw toJs(new GdxRuntimeException("FileType '" + type_0 + "' Not supported in GWT backend"));
  this.preloader = preloader;
  this.file = fixSlashes(fileName);
}

function fixSlashes(path){
  var suffixlength;
  path = $replace(path);
  suffixlength = '/'.length;
  $equals_5(path.substr(path.length - suffixlength, suffixlength), '/') && (path = $substring_0(path, 0, path.length - 1));
  return path;
}

defineClass(483, 548, {}, GwtFileHandle);
_.toString_0 = function toString_4(){
  return this.file;
}
;
var Lcom_badlogic_gdx_backends_gwt_GwtFileHandle_2_classLit = createForClass('com.badlogic.gdx.backends.gwt', 'GwtFileHandle', 483);
function $clinit_GwtFiles(){
  $clinit_GwtFiles = emptyMethod;
  !localStorage_0 && ($clinit_Storage$StorageSupportDetector() , localStorageSupported) && (localStorage_0 = new Storage_0);
}

function $internal(this$static, path){
  return new GwtFileHandle(this$static.preloader, path, ($clinit_Files$FileType() , Internal));
}

function GwtFiles(preloader){
  $clinit_GwtFiles();
  this.preloader = preloader;
}

defineClass(285, 1, {}, GwtFiles);
var Lcom_badlogic_gdx_backends_gwt_GwtFiles_2_classLit = createForClass('com.badlogic.gdx.backends.gwt', 'GwtFiles', 285);
function $copy(buffer){
  return $subarray_1(castTo(buffer, 105).getTypedArray(), buffer.position, buffer.limit - buffer.position);
}

function $copy_0(buffer){
  return $subarray(castTo(buffer, 105).getTypedArray(), buffer.position, buffer.limit - buffer.position);
}

function $copy_1(buffer){
  return $subarray_0(castTo(buffer, 105).getTypedArray(), buffer.position, buffer.limit - buffer.position);
}

function $glAttachShader(this$static, program, shader){
  var glProgram, glShader;
  glProgram = this$static.programs[program];
  glShader = this$static.shaders[shader];
  $attachShader(this$static.gl, glProgram, glShader);
}

function $glBindBuffer(this$static, target, buffer){
  $bindBuffer(this$static.gl, target, this$static.buffers[buffer]);
}

function $glBindTexture(this$static, target, texture){
  $bindTexture(this$static.gl, target, this$static.textures[texture]);
}

function $glBlendFuncSeparate(this$static, srcRGB, dstRGB, srcAlpha, dstAlpha){
  $blendFuncSeparate(this$static.gl, srcRGB, dstRGB, srcAlpha, dstAlpha);
}

function $glBufferData(this$static, target, size_0, data_0, usage){
  if (instanceOf(data_0, 68)) {
    $bufferData_0(this$static.gl, target, $copy_0(castTo(data_0, 68)), usage);
  }
   else if (instanceOf(data_0, 80)) {
    $bufferData_0(this$static.gl, target, $copy_1(castTo(data_0, 80)), usage);
  }
   else if (instanceOf(data_0, 64)) {
    $bufferData_0(this$static.gl, target, $copy(castTo(data_0, 64)), usage);
  }
   else if (!data_0) {
    $bufferData(this$static.gl, target, size_0, usage);
  }
   else {
    throw toJs(new GdxRuntimeException('Can only cope with FloatBuffer and ShortBuffer at the moment'));
  }
}

function $glBufferSubData(this$static, target, data_0){
  if (data_0) {
    $bufferSubData(this$static.gl, target, 0, $subarray_1(data_0.byteArray, data_0.position, data_0.limit - data_0.position));
  }
   else {
    throw toJs(new GdxRuntimeException('Can only cope with FloatBuffer and ShortBuffer at the moment'));
  }
}

function $glDrawArrays(this$static, mode, count){
  $drawArrays(this$static.gl, mode, 0, count);
}

function $glDrawElements(this$static, mode, count){
  $drawElements(this$static.gl, mode, count, 5123, 0);
}

function $glDrawElements_0(this$static, mode, count, indices){
  $drawElements(this$static.gl, mode, count, 5123, indices.position);
}

function $glGenBuffer(this$static){
  var buffer;
  buffer = this$static.gl.createBuffer();
  return $add(this$static.buffers, buffer);
}

function $glGenTexture(this$static){
  var texture;
  texture = this$static.gl.createTexture();
  return $add(this$static.textures, texture);
}

function $glGetActiveAttrib(this$static, program, index_0, size_0, type_0){
  var activeAttrib;
  activeAttrib = $getActiveAttrib(this$static.gl, this$static.programs[program], index_0);
  size_0.put_0(activeAttrib.size);
  $flip_0(size_0);
  type_0.put_0(activeAttrib.type);
  $flip_0(type_0);
  return activeAttrib.name;
}

function $glGetActiveUniform(this$static, program, index_0, size_0, type_0){
  var activeUniform;
  activeUniform = $getActiveUniform(this$static.gl, this$static.programs[program], index_0);
  size_0.put_0(activeUniform.size);
  $flip_0(size_0);
  type_0.put_0(activeUniform.type);
  $flip_0(type_0);
  return activeUniform.name;
}

function $glGetAttribLocation(this$static, program, name_0){
  var prog;
  prog = this$static.programs[program];
  return $getAttribLocation(this$static.gl, prog, name_0);
}

function $glGetFloatv(){
  throw toJs(new GdxRuntimeException('glGetFloat not supported by GWT WebGL backend'));
}

function $glGetProgramInfoLog(this$static, program){
  return $getProgramInfoLog(this$static.gl, this$static.programs[program]);
}

function $glGetProgramiv(this$static, program, pname, params){
  var result;
  if (pname == 35712 || pname == 35714 || pname == 35715) {
    result = $getProgramParameterb(this$static.gl, this$static.programs[program], pname);
    params.put_0(result?1:0);
  }
   else {
    params.put_0($getProgramParameteri(this$static.gl, this$static.programs[program], pname));
  }
  $flip_0(params);
}

function $glGetString(this$static, name_0){
  return $getParameterString(this$static.gl, name_0);
}

function $glGetUniformLocation(this$static, program, name_0){
  var id_0, location_0, progUniforms;
  location_0 = $getUniformLocation(this$static.gl, this$static.programs[program], name_0);
  if (!location_0)
    return -1;
  progUniforms = this$static.uniforms[program];
  if (!progUniforms) {
    progUniforms = [undefined];
    this$static.uniforms[program] = progUniforms;
  }
  id_0 = $add(progUniforms, location_0);
  return id_0;
}

function $glTexImage2D(this$static, level, width_0, height, pixels){
  var arrayHolder, buffer, byteOffset, pixmap, remainingBytes, webGLArray;
  if (!pixels) {
    $texImage2D(this$static.gl, 3553, level, 6408, width_0, height, 0, 6408, 5121, null);
  }
   else {
    if (pixels) {
      if (pixels.limit > 1) {
        arrayHolder = castTo(pixels, 105);
        webGLArray = arrayHolder.getTypedArray();
        remainingBytes = (pixels.limit - pixels.position) * 4;
        byteOffset = webGLArray.byteOffset + pixels.position * 4;
        buffer = create_8(webGLArray.buffer, byteOffset, remainingBytes);
        $texImage2D(this$static.gl, 3553, level, 6408, width_0, height, 0, 6408, 5121, buffer);
      }
       else {
        pixmap = castTo($get_8(($clinit_Pixmap() , pixmaps), valueOf_1(pixels.get_3(0))), 118);
        $texImage2D_0(this$static.gl, 3553, level, 6408, 6408, 5121, (!pixmap.canvas && $create(pixmap) , $getCanvasElement(pixmap.canvas)));
      }
    }
     else {
      throw toJs(new GdxRuntimeException('Unsupported Buffer type'));
    }
  }
}

function $glTexParameterf(this$static, param){
  $texParameterf(this$static.gl, 3553, 34046, param);
}

function $glTexParameteri(this$static, target, pname, param){
  $texParameterf(this$static.gl, target, pname, param);
}

function $glUniformMatrix4fv(this$static, location_0, value_0){
  var loc, array;
  loc = this$static.uniforms[this$static.currProgram][location_0];
  $uniformMatrix4fv(this$static.gl, loc, false, (array = create_1(value_0.length) , array.set(value_0, 0) , undefined , array));
}

function $glUseProgram(this$static, program){
  this$static.currProgram = program;
  $useProgram(this$static.gl, this$static.programs[program]);
}

function $glViewport(this$static, x_0, y_0, width_0, height){
  $viewport(this$static.gl, x_0, y_0, width_0, height);
}

function GwtGL20(gl){
  this.programs = [undefined];
  this.shaders = [undefined];
  this.buffers = [undefined];
  this.textures = [undefined];
  this.uniforms = [undefined];
  new Float32Array(40000);
  new Int32Array(12000);
  new Int16Array(12000);
  new Int8Array(12000);
  this.gl = gl;
  this.gl.pixelStorei(37441, 0);
}

defineClass(404, 1, {}, GwtGL20);
_.currProgram = 0;
var Lcom_badlogic_gdx_backends_gwt_GwtGL20_2_classLit = createForClass('com.badlogic.gdx.backends.gwt', 'GwtGL20', 404);
function $add(this$static, value_0){
  this$static.push(value_0);
  return this$static.length - 1;
}

function $setCanvasSize(this$static, width_0, height){
  var density;
  $setWidth_0(this$static.canvas, width_0);
  $setHeight_0(this$static.canvas, height);
  if (this$static.config.usePhysicalPixels) {
    density = $wnd.devicePixelRatio || 1;
    this$static.canvas.style['width'] = width_0 / density + ($clinit_Style$Unit() , 'px');
    this$static.canvas.style['height'] = height / density + 'px';
  }
}

function $update_1(this$static){
  var currTimeStamp;
  currTimeStamp = ($clinit_System() , fromDouble_0(Date.now()));
  this$static.deltaTime = toDouble_0(sub_1(currTimeStamp, this$static.lastTimeStamp)) / 1000;
  this$static.lastTimeStamp = currTimeStamp;
  this$static.time += this$static.deltaTime;
  ++this$static.frames_0;
  if (this$static.time > 1) {
    this$static.time = 0;
    this$static.frames_0 = 0;
  }
}

function GwtGraphics(root, config){
  var attributes, canvasWidget, density, height, versionString, width_0;
  this.lastTimeStamp = ($clinit_System() , fromDouble_0(Date.now()));
  canvasWidget = createIfSupported();
  if (!canvasWidget)
    throw toJs(new GdxRuntimeException('Canvas not supported'));
  this.canvas = ($clinit_DOM() , canvasWidget.element);
  $add_6(root, canvasWidget);
  this.config = config;
  if (config.width_0 != 0 && config.height_0 != 0) {
    $setCanvasSize(this, config.width_0, config.height_0);
  }
   else {
    width_0 = $getClientWidth($doc) - config.padHorizontal;
    height = $getClientHeight($doc) - config.padVertical;
    density = config.usePhysicalPixels?$wnd.devicePixelRatio || 1:1;
    $setCanvasSize(this, round_int(density * width_0), round_int(density * height));
  }
  attributes = {premultipliedAlpha:false};
  attributes.antialias = false;
  attributes.stencil = false;
  attributes.alpha = false;
  attributes.premultipliedAlpha = false;
  attributes.preserveDrawingBuffer = false;
  attributes.xrCompatible = false;
  this.context = getContext(this.canvas, attributes);
  this.gl20 = new GwtGL20(this.context);
  $viewport(this.context, 0, 0, this.canvas.width, this.canvas.height);
  versionString = $glGetString(this.gl20, 7938);
  $glGetString(this.gl20, 7936);
  $glGetString(this.gl20, 7937);
  new GLVersion(versionString);
}

defineClass(283, 533, {}, GwtGraphics);
_.deltaTime = 0;
_.frameId = -1;
_.frames_0 = 0;
_.lastTimeStamp = 0;
_.time = 0;
var Lcom_badlogic_gdx_backends_gwt_GwtGraphics_2_classLit = createForClass('com.badlogic.gdx.backends.gwt', 'GwtGraphics', 283);
function GwtNet(){
  new ObjectMap;
  new ObjectMap;
}

defineClass(287, 1, {}, GwtNet);
var Lcom_badlogic_gdx_backends_gwt_GwtNet_2_classLit = createForClass('com.badlogic.gdx.backends.gwt', 'GwtNet', 287);
function queryPermission(permission, result){
  'permissions' in $wnd.navigator?$wnd.navigator.permissions.query({name:permission}).then(function(permissionStatus){
    permissionStatus.state === 'granted'?result.granted():permissionStatus.state === 'denied'?result.denied():permissionStatus.state === 'prompt' && result.prompt_0();
    permissionStatus.onchange = function(){
      this.state === 'granted'?result.granted():this.state === 'denied'?result.denied():this.state === 'prompt' && result.prompt_0();
    }
    ;
  }
  ):result.granted();
}

function $load(this$static, url_0, type_0, mimeType, listener){
  var crossOrigin, s, i;
  switch (type_0.ordinal) {
    case 2:
      $loadText(url_0, listener);
      break;
    case 0:
      crossOrigin = null;
      $startsWith(url_0, ($clinit_Impl() , s = $doc.location.href , i = s.indexOf('#') , i != -1 && (s = s.substring(0, i)) , i = s.indexOf('?') , i != -1 && (s = s.substring(0, i)) , i = s.lastIndexOf('/') , i != -1 && (s = s.substring(0, i)) , s.length > 0?s + '/':'')) || (crossOrigin = 'anonymous');
      $loadImage(this$static, url_0, mimeType, crossOrigin, listener);
      break;
    case 3:
      $loadBinary(url_0, listener);
      break;
    case 1:
      $loadBinary(url_0, new AssetDownloader$3(listener));
      break;
    case 4:
      $onSuccess_2(listener, null);
      break;
    default:throw toJs(new GdxRuntimeException('Unsupported asset type ' + type_0));
  }
}

function $loadBinary(url_0, listener){
  var request;
  request = create_9();
  $setOnReadyStateChange(request, new AssetDownloader$2(listener));
  setOnProgress(listener);
  request.open('GET', url_0, true);
  $setResponseType(request, ($clinit_XMLHttpRequest$ResponseType() , ArrayBuffer_0).responseTypeString);
  request.send(null);
}

function $loadImage(this$static, url_0, mimeType, crossOrigin, listener){
  var image;
  if (this$static.useBrowserCache || this$static.useInlineBase64) {
    $loadBinary(url_0, new AssetDownloader$4(this$static, listener, crossOrigin, mimeType, url_0));
  }
   else {
    image = new Image;
    crossOrigin != null && (image.setAttribute('crossOrigin', crossOrigin) , undefined);
    hookImgListener(image, new AssetDownloader$5(listener, image));
    image.src = url_0;
  }
}

function $loadText(url_0, listener){
  var request;
  request = create_9();
  $setOnReadyStateChange(request, new AssetDownloader$1(listener));
  setOnProgress(listener);
  request.open('GET', url_0, true);
  request.setRequestHeader('Content-Type', 'text/plain; charset=utf-8');
  request.send(null);
}

function AssetDownloader(){
  this.useBrowserCache = true;
  this.useInlineBase64 = false;
}

function hookImgListener(img, h){
  img.addEventListener('load', function(e){
    h.onEvent(e);
  }
  , false);
  img.addEventListener('error', function(e){
    h.onEvent(e);
  }
  , false);
}

function setOnProgress(listener){
  this.onprogress = $entry(function(evt){
    listener.onProgress(evt.loaded);
  }
  );
}

defineClass(218, 1, {}, AssetDownloader);
_.useBrowserCache = false;
_.useInlineBase64 = false;
var Lcom_badlogic_gdx_backends_gwt_preloader_AssetDownloader_2_classLit = createForClass('com.badlogic.gdx.backends.gwt.preloader', 'AssetDownloader', 218);
function AssetDownloader$1(val$listener){
  this.val$listener2 = val$listener;
}

defineClass(298, 1, {}, AssetDownloader$1);
_.onReadyStateChange = function onReadyStateChange(xhr){
  xhr.readyState == 4 && (xhr.status != 200?this.val$listener2.onFailure():this.val$listener2.onSuccess(xhr.responseText));
}
;
var Lcom_badlogic_gdx_backends_gwt_preloader_AssetDownloader$1_2_classLit = createForClass('com.badlogic.gdx.backends.gwt.preloader', 'AssetDownloader/1', 298);
function AssetDownloader$2(val$listener){
  this.val$listener2 = val$listener;
}

defineClass(299, 1, {}, AssetDownloader$2);
_.onReadyStateChange = function onReadyStateChange_0(xhr){
  var data_0;
  if (xhr.readyState == 4) {
    if (xhr.status != 200) {
      this.val$listener2.onFailure();
    }
     else {
      data_0 = create_7(xhr.response);
      this.val$listener2.onSuccess(new Blob_0(data_0));
    }
  }
}
;
var Lcom_badlogic_gdx_backends_gwt_preloader_AssetDownloader$2_2_classLit = createForClass('com.badlogic.gdx.backends.gwt.preloader', 'AssetDownloader/2', 299);
function $onSuccess(this$static, result){
  $onSuccess_2(this$static.val$listener2, result);
}

function AssetDownloader$3(val$listener){
  this.val$listener2 = val$listener;
}

defineClass(297, 1, {}, AssetDownloader$3);
_.onSuccess = function onSuccess(result){
  $onSuccess(this, castTo(result, 72));
}
;
_.onFailure = function onFailure(){
  $onFailure(this.val$listener2);
}
;
_.onProgress = function onProgress(amount){
  $onProgress(this.val$listener2, amount);
}
;
var Lcom_badlogic_gdx_backends_gwt_preloader_AssetDownloader$3_2_classLit = createForClass('com.badlogic.gdx.backends.gwt.preloader', 'AssetDownloader/3', 297);
function $onSuccess_0(this$static, result){
  var image;
  image = new Image;
  this$static.val$crossOrigin3 != null && $setAttribute(image, 'crossOrigin', this$static.val$crossOrigin3);
  hookImgListener(image, new AssetDownloader$4$1(this$static.val$listener2, image));
  this$static.this$01.useInlineBase64?$setSrc_0(image, 'data:' + this$static.val$mimeType4 + ';base64,' + $toBase64(result)):$setSrc_0(image, this$static.val$url5);
}

function AssetDownloader$4(this$0, val$listener, val$crossOrigin, val$mimeType, val$url){
  this.this$01 = this$0;
  this.val$listener2 = val$listener;
  this.val$crossOrigin3 = val$crossOrigin;
  this.val$mimeType4 = val$mimeType;
  this.val$url5 = val$url;
}

defineClass(300, 1, {}, AssetDownloader$4);
_.onSuccess = function onSuccess_0(result){
  $onSuccess_0(this, castTo(result, 72));
}
;
_.onFailure = function onFailure_0(){
  $onFailure(this.val$listener2);
}
;
_.onProgress = function onProgress_0(amount){
  $onProgress(this.val$listener2, amount);
}
;
var Lcom_badlogic_gdx_backends_gwt_preloader_AssetDownloader$4_2_classLit = createForClass('com.badlogic.gdx.backends.gwt.preloader', 'AssetDownloader/4', 300);
function AssetDownloader$4$1(val$listener, val$image){
  this.val$listener2 = val$listener;
  this.val$image3 = val$image;
}

defineClass(302, 1, {}, AssetDownloader$4$1);
_.onEvent = function onEvent(event_0){
  $equals_5(event_0.type, 'error')?$onFailure(this.val$listener2):$onSuccess_2(this.val$listener2, this.val$image3);
}
;
var Lcom_badlogic_gdx_backends_gwt_preloader_AssetDownloader$4$1_2_classLit = createForClass('com.badlogic.gdx.backends.gwt.preloader', 'AssetDownloader/4/1', 302);
function AssetDownloader$5(val$listener, val$image){
  this.val$listener2 = val$listener;
  this.val$image3 = val$image;
}

defineClass(301, 1, {}, AssetDownloader$5);
_.onEvent = function onEvent_0(event_0){
  $equals_5(event_0.type, 'error')?$onFailure(this.val$listener2):$onSuccess_2(this.val$listener2, this.val$image3);
}
;
var Lcom_badlogic_gdx_backends_gwt_preloader_AssetDownloader$5_2_classLit = createForClass('com.badlogic.gdx.backends.gwt.preloader', 'AssetDownloader/5', 301);
function $clinit_AssetFilter$AssetType(){
  $clinit_AssetFilter$AssetType = emptyMethod;
  Image_0 = new AssetFilter$AssetType('Image', 0);
  Audio_0 = new AssetFilter$AssetType('Audio', 1);
  Text_0 = new AssetFilter$AssetType('Text', 2);
  Binary = new AssetFilter$AssetType('Binary', 3);
  Directory_0 = new AssetFilter$AssetType('Directory', 4);
}

function AssetFilter$AssetType(enum$name, enum$ordinal){
  Enum.call(this, enum$name, enum$ordinal);
}

function values_1(){
  $clinit_AssetFilter$AssetType();
  return stampJavaTypeInfo(getClassLiteralForArray(Lcom_badlogic_gdx_backends_gwt_preloader_AssetFilter$AssetType_2_classLit, 1), $intern_1, 98, 0, [Image_0, Audio_0, Text_0, Binary, Directory_0]);
}

defineClass(98, 8, {98:1, 3:1, 7:1, 8:1}, AssetFilter$AssetType);
var Audio_0, Binary, Directory_0, Image_0, Text_0;
var Lcom_badlogic_gdx_backends_gwt_preloader_AssetFilter$AssetType_2_classLit = createForEnum('com.badlogic.gdx.backends.gwt.preloader', 'AssetFilter/AssetType', 98, values_1);
function $get(this$static, i){
  return this$static.data_0[i];
}

function $toBase64(this$static){
  var encoded, i, j, length_0;
  length_0 = this$static.data_0.length;
  encoded = new StringBuilder_0;
  for (i = 0; i < length_0; i += 3) {
    if (length_0 - i >= 3) {
      j = ((this$static.data_0[i] & 255) << 16) + ((this$static.data_0[i + 1] & 255) << 8) + (this$static.data_0[i + 2] & 255);
      $append(encoded, (checkCriticalStringElementIndex(j >> 18 & 63, 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/'.length) , 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/'.charCodeAt(j >> 18 & 63)));
      $append(encoded, (checkCriticalStringElementIndex(j >> 12 & 63, 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/'.length) , 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/'.charCodeAt(j >> 12 & 63)));
      $append(encoded, (checkCriticalStringElementIndex(j >> 6 & 63, 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/'.length) , 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/'.charCodeAt(j >> 6 & 63)));
      $append(encoded, (checkCriticalStringElementIndex(j & 63, 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/'.length) , 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/'.charCodeAt(j & 63)));
    }
     else if (length_0 - i >= 2) {
      j = ((this$static.data_0[i] & 255) << 16) + ((this$static.data_0[i + 1] & 255) << 8);
      $append(encoded, (checkCriticalStringElementIndex(j >> 18 & 63, 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/'.length) , 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/'.charCodeAt(j >> 18 & 63)));
      $append(encoded, (checkCriticalStringElementIndex(j >> 12 & 63, 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/'.length) , 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/'.charCodeAt(j >> 12 & 63)));
      $append(encoded, (checkCriticalStringElementIndex(j >> 6 & 63, 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/'.length) , 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/'.charCodeAt(j >> 6 & 63)));
      encoded.string += '=';
    }
     else {
      j = (this$static.data_0[i] & 255) << 16;
      $append(encoded, (checkCriticalStringElementIndex(j >> 18 & 63, 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/'.length) , 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/'.charCodeAt(j >> 18 & 63)));
      $append(encoded, (checkCriticalStringElementIndex(j >> 12 & 63, 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/'.length) , 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/'.charCodeAt(j >> 12 & 63)));
      encoded.string += '==';
    }
  }
  return encoded.string;
}

function Blob_0(data_0){
  this.data_0 = data_0;
}

defineClass(72, 1, {72:1}, Blob_0);
var Lcom_badlogic_gdx_backends_gwt_preloader_Blob_2_classLit = createForClass('com.badlogic.gdx.backends.gwt.preloader', 'Blob', 72);
function $read_0(this$static, buffer){
  checkCriticalNotNull(buffer);
  return this$static.read_0(buffer, 0, buffer.length);
}

defineClass(541, 1, {});
_.close_0 = function close_0(){
}
;
_.read_0 = function read_0(buffer, byteOffset, byteCount){
  var c, e, i;
  checkCriticalNotNull(buffer);
  checkOffsetAndCount(buffer.length, byteOffset, byteCount);
  for (i = 0; i < byteCount; ++i) {
    try {
      if ((c = this.read()) == -1) {
        return i == 0?-1:i;
      }
    }
     catch ($e0) {
      $e0 = toJava($e0);
      if (instanceOf($e0, 22)) {
        e = $e0;
        if (i != 0) {
          return i;
        }
        throw toJs(e);
      }
       else 
        throw toJs($e0);
    }
    buffer[byteOffset + i] = c << 24 >> 24;
  }
  return byteCount;
}
;
var Ljava_io_InputStream_2_classLit = createForClass('java.io', 'InputStream', 541);
function Blob$1(this$0){
  this.this$01 = this$0;
}

defineClass(219, 541, {}, Blob$1);
_.read = function read_1(){
  if (this.pos == this.this$01.data_0.length)
    return -1;
  return $get(this.this$01, this.pos++) & 255;
}
;
_.pos = 0;
var Lcom_badlogic_gdx_backends_gwt_preloader_Blob$1_2_classLit = createForClass('com.badlogic.gdx.backends.gwt.preloader', 'Blob/1', 219);
function $contains(this$static, file){
  return $locateKey_2(this$static.texts, file) >= 0 || $locateKey_2(this$static.images, file) >= 0 || $locateKey_2(this$static.binaries, file) >= 0 || $locateKey_2(this$static.audio, file) >= 0 || $locateKey_2(this$static.directories_0, file) >= 0;
}

function $length(this$static, file){
  var lastArg;
  if ($locateKey_2(this$static.texts, file) >= 0) {
    return $getBytes((lastArg = castToString($get_6(this$static.texts, file)) , $clinit_StandardCharsets() , lastArg)).length;
  }
  if ($locateKey_2(this$static.images, file) >= 0) {
    return 1;
  }
  if ($locateKey_2(this$static.binaries, file) >= 0) {
    return castTo($get_6(this$static.binaries, file), 72).data_0.length;
  }
  if ($locateKey_2(this$static.audio, file) >= 0) {
    return castTo($get_6(this$static.audio, file), 72).data_0.length;
  }
  return 0;
}

function $preload(this$static, callback){
  $loadText(this$static.baseUrl + 'assets.txt' + '?etag=' + toString_29(($clinit_System() , fromDouble_0(Date.now()))), new Preloader$1(this$static, callback));
}

function $putAssetInMap(this$static, result, asset){
  switch (asset.type_0.ordinal) {
    case 2:
      $put_1(this$static.texts, asset.file, castToString(result));
      break;
    case 0:
      $put_1(this$static.images, asset.file, castToJso(result));
      break;
    case 3:
      $put_1(this$static.binaries, asset.file, castTo(result, 72));
      break;
    case 1:
      $put_1(this$static.audio, asset.file, castTo(result, 72));
      break;
    case 4:
      $put_1(this$static.directories_0, asset.file, null);
  }
}

function $read_1(this$static, file){
  var lastArg;
  if ($locateKey_2(this$static.texts, file) >= 0) {
    return new ByteArrayInputStream($getBytes((lastArg = castToString($get_6(this$static.texts, file)) , $clinit_StandardCharsets() , lastArg)));
  }
  if ($locateKey_2(this$static.images, file) >= 0) {
    return new ByteArrayInputStream(initUnidimensionalArray(B_classLit, $intern_6, 5, 1, 15, 1));
  }
  if ($locateKey_2(this$static.binaries, file) >= 0) {
    return new Blob$1(castTo($get_6(this$static.binaries, file), 72));
  }
  if ($locateKey_2(this$static.audio, file) >= 0) {
    return new Blob$1(castTo($get_6(this$static.audio, file), 72));
  }
  return null;
}

function Preloader(newBaseURL){
  this.loader = new AssetDownloader;
  this.directories_0 = new ObjectMap;
  this.images = new ObjectMap;
  this.audio = new ObjectMap;
  this.texts = new ObjectMap;
  this.binaries = new ObjectMap;
  this.stillToFetchAssets = new ObjectMap;
  this.assetNames = new ObjectMap;
  this.baseUrl = newBaseURL;
}

defineClass(281, 1, {}, Preloader);
var Lcom_badlogic_gdx_backends_gwt_preloader_Preloader_2_classLit = createForClass('com.badlogic.gdx.backends.gwt.preloader', 'Preloader', 281);
function $onSuccess_1(this$static, result){
  var asset, assetMimeType, assetPathMd5, assetPathOrig, assetPreload, assetTypeCode, assets, i, line, line$array, line$index, line$max, lines, size_0, state, tokens, type_0;
  lines = $split(result, '\n');
  assets = new Array_1(lines.length);
  for (line$array = lines , line$index = 0 , line$max = line$array.length; line$index < line$max; ++line$index) {
    line = line$array[line$index];
    tokens = $split(line, ':');
    if (tokens.length != 6) {
      throw toJs(new GdxRuntimeException('Invalid assets description file.'));
    }
    assetTypeCode = tokens[0];
    assetPathOrig = tokens[1];
    assetPathMd5 = tokens[2];
    size_0 = __parseAndValidateLong(tokens[3]);
    assetMimeType = tokens[4];
    assetPreload = $equals_5(tokens[5], '1');
    type_0 = ($clinit_AssetFilter$AssetType() , Text_0);
    $equals_5(assetTypeCode, 'i') && (type_0 = Image_0);
    $equals_5(assetTypeCode, 'b') && (type_0 = Binary);
    $equals_5(assetTypeCode, 'a') && (type_0 = Audio_0);
    $equals_5(assetTypeCode, 'd') && (type_0 = Directory_0);
    type_0 == Audio_0 && !this$static.this$01.loader.useBrowserCache && (size_0 = 0);
    asset = new Preloader$Asset($trim(assetPathOrig), $trim(assetPathMd5), type_0, size_0, assetMimeType);
    $put_1(this$static.this$01.assetNames, asset.file, asset.url_0);
    assetPreload || $startsWith(asset.file, 'com/badlogic/')?$add_0(assets, asset):$put_1(this$static.this$01.stillToFetchAssets, asset.file, asset);
  }
  state = new Preloader$PreloaderState(assets);
  for (i = 0; i < assets.size_0; i++) {
    asset = castTo($get_1(assets, i), 122);
    if ($contains(this$static.this$01, asset.file)) {
      asset.loaded_0 = asset.size_0;
      asset.succeed = true;
      continue;
    }
    $load(this$static.this$01.loader, this$static.this$01.baseUrl + ('' + asset.url_0), asset.type_0, asset.mimeType, new Preloader$1$1(this$static, asset, this$static.val$callback2, state));
  }
  $update(this$static.val$callback2, state);
}

function Preloader$1(this$0, val$callback){
  this.this$01 = this$0;
  this.val$callback2 = val$callback;
}

defineClass(294, 1, {}, Preloader$1);
_.onSuccess = function onSuccess_1(result){
  $onSuccess_1(this, castToString(result));
}
;
_.onFailure = function onFailure_1(){
  $clinit_System();
  String.fromCharCode(10);
}
;
_.onProgress = function onProgress_1(amount){
}
;
var Lcom_badlogic_gdx_backends_gwt_preloader_Preloader$1_2_classLit = createForClass('com.badlogic.gdx.backends.gwt.preloader', 'Preloader/1', 294);
function $onFailure(this$static){
  this$static.val$asset2.failed = true;
  $clinit_System();
  String.fromCharCode(10);
  $update(this$static.val$callback3, this$static.val$state4);
}

function $onProgress(this$static, amount){
  this$static.val$asset2.loaded_0 = fromDouble_0(amount);
  $update(this$static.val$callback3, this$static.val$state4);
}

function $onSuccess_2(this$static, result){
  $putAssetInMap(this$static.this$11.this$01, result, this$static.val$asset2);
  this$static.val$asset2.succeed = true;
  $update(this$static.val$callback3, this$static.val$state4);
}

function Preloader$1$1(this$1, val$asset, val$callback, val$state){
  this.this$11 = this$1;
  this.val$asset2 = val$asset;
  this.val$callback3 = val$callback;
  this.val$state4 = val$state;
}

defineClass(296, 1, {}, Preloader$1$1);
_.onFailure = function onFailure_2(){
  $onFailure(this);
}
;
_.onProgress = function onProgress_2(amount){
  $onProgress(this, amount);
}
;
_.onSuccess = function onSuccess_2(result){
  $onSuccess_2(this, result);
}
;
var Lcom_badlogic_gdx_backends_gwt_preloader_Preloader$1$1_2_classLit = createForClass('com.badlogic.gdx.backends.gwt.preloader', 'Preloader/1/1', 296);
function Preloader$Asset(file, url_0, type_0, size_0, mimeType){
  this.file = file;
  this.url_0 = url_0;
  this.type_0 = type_0;
  this.size_0 = size_0;
  this.mimeType = mimeType;
}

defineClass(122, 1, {122:1}, Preloader$Asset);
_.failed = false;
_.loaded_0 = 0;
_.size_0 = 0;
_.succeed = false;
var Lcom_badlogic_gdx_backends_gwt_preloader_Preloader$Asset_2_classLit = createForClass('com.badlogic.gdx.backends.gwt.preloader', 'Preloader/Asset', 122);
function $getDownloadedSize(this$static){
  var asset, i, size_0;
  size_0 = 0;
  for (i = 0; i < this$static.assets.size_0; i++) {
    asset = castTo($get_1(this$static.assets, i), 122);
    size_0 = add_2(size_0, asset.succeed || asset.failed?asset.size_0:min_0(asset.size_0, asset.loaded_0));
  }
  return size_0;
}

function $getTotalSize(this$static){
  var asset, i, size_0;
  size_0 = 0;
  for (i = 0; i < this$static.assets.size_0; i++) {
    asset = castTo($get_1(this$static.assets, i), 122);
    size_0 = add_2(size_0, asset.size_0);
  }
  return size_0;
}

function Preloader$PreloaderState(assets){
  this.assets = assets;
}

defineClass(295, 1, {}, Preloader$PreloaderState);
var Lcom_badlogic_gdx_backends_gwt_preloader_Preloader$PreloaderState_2_classLit = createForClass('com.badlogic.gdx.backends.gwt.preloader', 'Preloader/PreloaderState', 295);
function $setSourceJSNI(this$static, sourceNode){
  var gainNode = this$static.gainNode;
  var panNode = this$static.panNode;
  panNode?sourceNode.connect(panNode):sourceNode.connect(gainNode);
}

function $setVolumeJSNI(this$static, volume){
  var gainNode = this$static.gainNode;
  gainNode.gain.value = volume;
}

function $setupAudoGraph(this$static){
  var audioContext = this$static.audioContext;
  var panNode = null;
  if (audioContext.createStereoPanner) {
    panNode = audioContext.createStereoPanner();
    panNode.pan.value = 0;
    this$static.panNode = panNode;
  }
  var gainNode = null;
  audioContext.createGain?(gainNode = audioContext.createGain()):(gainNode = audioContext.createGainNode());
  this$static.gainNode = gainNode;
  gainNode.gain.value = 1;
  panNode && panNode.connect(gainNode);
  gainNode.connect(this$static.destinationNode);
}

function AudioControlGraph(audioContext, destinationNode){
  this.audioContext = audioContext;
  this.destinationNode = destinationNode;
  $setupAudoGraph(this);
}

defineClass(205, 1, {205:1}, AudioControlGraph);
var Lcom_badlogic_gdx_backends_gwt_webaudio_AudioControlGraph_2_classLit = createForClass('com.badlogic.gdx.backends.gwt.webaudio', 'AudioControlGraph', 205);
function $free(this$static, object){
  if (!object)
    throw toJs(new IllegalArgumentException_0('object cannot be null.'));
  if (this$static.freeObjects.size_0 < this$static.max_0) {
    $add_0(this$static.freeObjects, object);
    this$static.peak = $wnd.Math.max(this$static.peak, this$static.freeObjects.size_0);
  }
}

defineClass(241, 1, {});
_.max_0 = 0;
_.peak = 0;
var Lcom_badlogic_gdx_utils_Pool_2_classLit = createForClass('com.badlogic.gdx.utils', 'Pool', 241);
function AudioControlGraphPool(audioContext, destinationNode){
  this.freeObjects = new Array_2(false, 16);
  this.max_0 = $intern_0;
  this.audioContext = audioContext;
  this.destinationNode = destinationNode;
}

defineClass(428, 241, {}, AudioControlGraphPool);
var Lcom_badlogic_gdx_backends_gwt_webaudio_AudioControlGraphPool_2_classLit = createForClass('com.badlogic.gdx.backends.gwt.webaudio', 'AudioControlGraphPool', 428);
function $createMusic(this$static, fileHandle){
  var audio, music, url_0;
  url_0 = fileHandle.preloader.baseUrl + ('' + castToString($get_7(fileHandle.preloader.assetNames, fileHandle.file, fileHandle.file)));
  audio = createIfSupported_0();
  $setSrc(($clinit_DOM() , audio.element), url_0);
  music = new WebAudioAPIMusic(this$static.audioContext, audio, this$static.audioControlGraphPool);
  return music;
}

function $hookUpSoundUnlockers(this$static){
  var self_0 = this$static;
  var audioContext = self_0.audioContext;
  var userInputEventNames = ['click', 'contextmenu', 'auxclick', 'dblclick', 'mousedown', 'mouseup', 'pointerup', 'touchend', 'keydown', 'keyup', 'touchstart'];
  var unlock = function(e){
    audioContext.resume();
    self_0.setUnlocked();
    userInputEventNames.forEach(function(eventName){
      $doc.removeEventListener(eventName, unlock);
    }
    );
  }
  ;
  userInputEventNames.forEach(function(eventName){
    $doc.addEventListener(eventName, unlock);
  }
  );
}

function WebAudioAPIManager(){
  var audioContext, gainNode;
  new AssetDownloader;
  this.audioContext = createAudioContextJSNI();
  this.globalVolumeNode = (audioContext = this.audioContext , gainNode = null , audioContext.createGain?(gainNode = audioContext.createGain()):(gainNode = audioContext.createGainNode()) , gainNode.gain.value = 1 , gainNode.connect(audioContext.destination) , gainNode);
  this.audioControlGraphPool = new AudioControlGraphPool(this.audioContext, this.globalVolumeNode);
  $addLifecycleListener(app_0, this);
  this.audioContext.state !== 'running'?$hookUpSoundUnlockers(this):$log(app_0, 'Webaudio', 'Audiocontext unlocked');
}

function createAudioContextJSNI(){
  var AudioContext_0 = window.AudioContext || window.webkitAudioContext;
  if (AudioContext_0) {
    var audioContext = new AudioContext_0;
    return audioContext;
  }
  return null;
}

defineClass(418, 1, {292:1}, WebAudioAPIManager);
_.setUnlocked = function setUnlocked(){
  $log(app_0, 'Webaudio', 'Audiocontext unlocked');
}
;
var Lcom_badlogic_gdx_backends_gwt_webaudio_WebAudioAPIManager_2_classLit = createForClass('com.badlogic.gdx.backends.gwt.webaudio', 'WebAudioAPIManager', 418);
function $createMediaElementAudioSourceNode(this$static, audioContext, audioElement){
  var source = audioContext.createMediaElementSource(audioElement);
  var self_0 = this$static;
  audioElement.addEventListener('ended', self_0.ended);
  return source;
}

function $dispose(this$static){
  $getMediaElement(this$static.audio).pause();
  $free(this$static.audioControlGraphPool, this$static.audioControlGraph);
}

function $setVolume(this$static, volume){
  $setVolumeJSNI(this$static.audioControlGraph, volume);
}

function $stop(this$static){
  $getMediaElement(this$static.audio).pause();
  $setCurrentTime(this$static.audio);
}

function WebAudioAPIMusic(audioContext, audio, audioControlGraphPool){
  var audioSourceNode;
  this.audio = audio;
  this.audioControlGraphPool = audioControlGraphPool;
  audioSourceNode = $createMediaElementAudioSourceNode(this, audioContext, ($clinit_DOM() , audio.element));
  this.audioControlGraph = castTo(audioControlGraphPool.freeObjects.size_0 == 0?new AudioControlGraph(audioControlGraphPool.audioContext, audioControlGraphPool.destinationNode):$pop(audioControlGraphPool.freeObjects), 205);
  $setSourceJSNI(this.audioControlGraph, audioSourceNode);
}

defineClass(496, 1, {}, WebAudioAPIMusic);
_.ended = function ended(){
}
;
var Lcom_badlogic_gdx_backends_gwt_webaudio_WebAudioAPIMusic_2_classLit = createForClass('com.badlogic.gdx.backends.gwt.webaudio', 'WebAudioAPIMusic', 496);
defineClass(409, 1, {});
_.far = 100;
_.near = 1;
_.viewportHeight = 0;
_.viewportWidth = 0;
var Lcom_badlogic_gdx_graphics_Camera_2_classLit = createForClass('com.badlogic.gdx.graphics', 'Camera', 409);
function $clinit_Color(){
  $clinit_Color = emptyMethod;
  WHITE = new Color(1, 1, 1, 1);
  new Color_0(-1077952513);
  new Color_0(2139062271);
  new Color_0(1061109759);
  new Color(0, 0, 0, 1);
  WHITE_FLOAT_BITS = $toFloatBits(WHITE);
  new Color(0, 0, 0, 0);
  new Color(1, 1, 1, 0);
  new Color(0, 0, 1, 1);
  new Color(0, 0, 0.5, 1);
  new Color_0(1097458175);
  new Color_0(1887473919);
  new Color_0(-2016482305);
  new Color(0, 1, 1, 1);
  new Color(0, 0.5, 0.5, 1);
  new Color_0(16711935);
  new Color_0(2147418367);
  new Color_0(852308735);
  new Color_0(579543807);
  new Color_0(1804477439);
  new Color_0(-65281);
  new Color_0(-2686721);
  new Color_0(-626712321);
  new Color_0(-5963521);
  new Color_0(-1958407169);
  new Color_0(-759919361);
  new Color_0(-1306385665);
  new Color_0(-16776961);
  new Color_0(-13361921);
  new Color_0(-8433409);
  new Color_0(-92245249);
  new Color_0(-9849601);
  new Color(1, 0, 1, 1);
  new Color_0(-1608453889);
  new Color_0(-293409025);
  new Color_0(-1339006721);
}

function $clamp(this$static){
  this$static.r < 0?(this$static.r = 0):this$static.r > 1 && (this$static.r = 1);
  this$static.g < 0?(this$static.g = 0):this$static.g > 1 && (this$static.g = 1);
  this$static.b < 0?(this$static.b = 0):this$static.b > 1 && (this$static.b = 1);
  this$static.a < 0?(this$static.a = 0):this$static.a > 1 && (this$static.a = 1);
  return this$static;
}

function $set(this$static, r, g, b, a){
  this$static.r = r;
  this$static.g = g;
  this$static.b = b;
  this$static.a = a;
  return $clamp(this$static);
}

function $set_0(this$static, color_0){
  this$static.r = color_0.r;
  this$static.g = color_0.g;
  this$static.b = color_0.b;
  this$static.a = color_0.a;
  return this$static;
}

function $toFloatBits(this$static){
  var color_0;
  color_0 = round_int(255 * this$static.a) << 24 | round_int(255 * this$static.b) << 16 | round_int(255 * this$static.g) << 8 | round_int(255 * this$static.r);
  return $clinit_Numbers() , wia[0] = color_0 & $intern_7 , undefined , wfa[0];
}

function Color(r, g, b, a){
  $clinit_Color();
  this.r = r;
  this.g = g;
  this.b = b;
  this.a = a;
  $clamp(this);
}

function Color_0(rgba8888){
  this.r = ((rgba8888 & -16777216) >>> 24) / 255;
  this.g = ((rgba8888 & 16711680) >>> 16) / 255;
  this.b = ((rgba8888 & 65280) >>> 8) / 255;
  this.a = (rgba8888 & 255) / 255;
}

defineClass(15, 1, {15:1}, Color, Color_0);
_.equals_0 = function equals_1(o){
  var color_0;
  if (this === o)
    return true;
  if (o == null || Lcom_badlogic_gdx_graphics_Color_2_classLit != getClass__Ljava_lang_Class___devirtual$(o))
    return false;
  color_0 = castTo(o, 15);
  return (round_int(255 * this.a) << 24 | round_int(255 * this.b) << 16 | round_int(255 * this.g) << 8 | round_int(255 * this.r)) == (round_int(255 * color_0.a) << 24 | round_int(255 * color_0.b) << 16 | round_int(255 * color_0.g) << 8 | round_int(255 * color_0.r));
}
;
_.hashCode_0 = function hashCode_2(){
  var result;
  result = this.r != 0?floatToIntBits(this.r):0;
  result = 31 * result + (this.g != 0?floatToIntBits(this.g):0);
  result = 31 * result + (this.b != 0?floatToIntBits(this.b):0);
  result = 31 * result + (this.a != 0?floatToIntBits(this.a):0);
  return result;
}
;
_.toString_0 = function toString_5(){
  var number, value_0;
  value_0 = (number = (round_int(255 * this.r) << 24 | round_int(255 * this.g) << 16 | round_int(255 * this.b) << 8 | round_int(255 * this.a)) >>> 0 , number.toString(16));
  while (value_0.length < 8)
    value_0 = '0' + value_0;
  return value_0;
}
;
_.a = 0;
_.b = 0;
_.g = 0;
_.r = 0;
var WHITE, WHITE_FLOAT_BITS = 0;
var Lcom_badlogic_gdx_graphics_Color_2_classLit = createForClass('com.badlogic.gdx.graphics', 'Color', 15);
function $bind(this$static){
  $glBindTexture(gl_0, this$static.glTarget, this$static.glHandle);
}

function $unsafeSetAnisotropicFilter(this$static, level, force){
  var max_0;
  max_0 = getMaxAnisotropicFilterLevel();
  if (max_0 == 1)
    return 1;
  level = $wnd.Math.min(level, max_0);
  if (!force && ($clinit_MathUtils() , $wnd.Math.abs(level - this$static.anisotropicFilterLevel) <= $intern_8))
    return this$static.anisotropicFilterLevel;
  $glTexParameterf(gl20, level);
  return this$static.anisotropicFilterLevel = level;
}

function $unsafeSetFilter(this$static, minFilter, magFilter){
  if (minFilter) {
    $glTexParameteri(gl_0, this$static.glTarget, 10241, minFilter.glEnum);
    this$static.minFilter = minFilter;
  }
  if (magFilter) {
    $glTexParameteri(gl_0, this$static.glTarget, 10240, magFilter.glEnum);
    this$static.magFilter = magFilter;
  }
}

function $unsafeSetWrap(this$static, u, v){
  if (u) {
    $glTexParameteri(gl_0, this$static.glTarget, 10242, u.glEnum);
    this$static.uWrap = u;
  }
  if (v) {
    $glTexParameteri(gl_0, this$static.glTarget, 10243, v.glEnum);
    this$static.vWrap = v;
  }
}

function getMaxAnisotropicFilterLevel(){
  var buffer;
  if (maxAnisotropicFilterLevel > 0)
    return maxAnisotropicFilterLevel;
  if (graphics.context.getExtension('GL_EXT_texture_filter_anisotropic')) {
    buffer = newFloatBuffer(16);
    $position(buffer, 0);
    $limit(buffer, buffer.capacity);
    $glGetFloatv();
    return maxAnisotropicFilterLevel = buffer.floatArray[0];
  }
  return maxAnisotropicFilterLevel = 1;
}

function uploadImageData(data_0){
  var disposePixmap, lastArg, pixmap, tmp;
  pixmap = data_0.pixmap;
  disposePixmap = data_0.disposePixmap;
  if (data_0.format != pixmap.format) {
    tmp = new Pixmap(pixmap.width_0, (lastArg = pixmap.height_0 , lastArg));
    $setBlending(tmp);
    $drawPixmap(tmp, pixmap, pixmap.width_0, pixmap.height_0);
    data_0.disposePixmap && $remove_6(($clinit_Pixmap() , pixmaps), valueOf_1(pixmap.id_0));
    pixmap = tmp;
    disposePixmap = true;
  }
  gl_0.gl.pixelStorei(3317, 1);
  data_0.useMipMaps?generateMipMap(pixmap, pixmap.width_0, pixmap.height_0):$glTexImage2D(gl_0, 0, pixmap.width_0, pixmap.height_0, pixmap.buffer_0);
  disposePixmap && $remove_6(($clinit_Pixmap() , pixmaps), valueOf_1(pixmap.id_0));
}

defineClass(431, 1, {});
_.anisotropicFilterLevel = 1;
_.glHandle = 0;
_.glTarget = 0;
var maxAnisotropicFilterLevel = 0;
var Lcom_badlogic_gdx_graphics_GLTexture_2_classLit = createForClass('com.badlogic.gdx.graphics', 'GLTexture', 431);
function $clinit_Mesh(){
  $clinit_Mesh = emptyMethod;
  meshes = new HashMap;
}

function $getVertexAttribute(this$static, usage){
  var attributes, i, len;
  attributes = this$static.vertices.getAttributes();
  len = attributes.attributes.length;
  for (i = 0; i < len; i++)
    if (attributes.attributes[i].usage == usage)
      return attributes.attributes[i];
  return null;
}

function $render(this$static, shader, primitiveType){
  $render_1(this$static, shader, primitiveType, this$static.indices.getNumMaxIndices() > 0?this$static.indices.getNumIndices():this$static.vertices.getNumVertices(), this$static.autoBind);
}

function $render_0(this$static, shader, count){
  $render_1(this$static, shader, 4, count, this$static.autoBind);
}

function $render_1(this$static, shader, primitiveType, count, autoBind){
  var buffer, oldPosition;
  if (count == 0)
    return;
  autoBind && (this$static.vertices.bind_1(shader, null) , this$static.indices.getNumIndices() > 0 && this$static.indices.bind_0());
  if (this$static.isVertexArray) {
    if (this$static.indices.getNumIndices() > 0) {
      buffer = this$static.indices.getBuffer(false);
      oldPosition = buffer.position;
      $position(buffer, 0);
      $glDrawElements_0(gl20, primitiveType, count, buffer);
      $position(buffer, oldPosition);
    }
     else {
      $glDrawArrays(gl20, primitiveType, count);
    }
  }
   else {
    if (this$static.indices.getNumIndices() > 0) {
      if (count > this$static.indices.getNumMaxIndices()) {
        throw toJs(new GdxRuntimeException('Mesh attempting to access memory outside of the index buffer (count: ' + count + ', offset: ' + 0 + ', max: ' + this$static.indices.getNumMaxIndices() + ')'));
      }
      $glDrawElements(gl20, primitiveType, count);
    }
     else {
      $glDrawArrays(gl20, primitiveType, count);
    }
  }
  autoBind && (this$static.vertices.unbind_0(shader, null) , this$static.indices.getNumIndices() > 0 && this$static.indices.unbind());
}

function $setIndices(this$static, indices){
  this$static.indices.setIndices(indices, 0, indices.length);
  return this$static;
}

function $setVertices(this$static, vertices, count){
  this$static.vertices.setVertices(vertices, 0, count);
  return this$static;
}

function Mesh(type_0, attributes){
  $clinit_Vector3();
  switch (type_0.ordinal) {
    case 1:
      this.vertices = new VertexBufferObject_0(4000, attributes);
      this.indices = new IndexBufferObject(6000);
      this.isVertexArray = false;
      break;
    case 2:
      this.vertices = new VertexBufferObjectSubData_0(attributes);
      this.indices = new IndexBufferObjectSubData;
      this.isVertexArray = false;
      break;
    case 3:
      this.vertices = new VertexBufferObjectWithVAO_0(attributes);
      this.indices = new IndexBufferObjectSubData;
      this.isVertexArray = false;
      break;
    case 0:
    default:this.vertices = new VertexArray_0(attributes);
      this.indices = new IndexArray;
      this.isVertexArray = true;
  }
  addManagedMesh(app_0, this);
}

function Mesh_0(type_0, attributes){
  $clinit_Mesh();
  Mesh.call(this, type_0, new VertexAttributes(attributes));
}

function Mesh_1(attributes){
  $clinit_Mesh();
  $clinit_Vector3();
  this.vertices = new VertexBufferObject_0(5000, new VertexAttributes(attributes));
  this.indices = new IndexBufferObject(0);
  this.isVertexArray = false;
  addManagedMesh(app_0, this);
}

function addManagedMesh(app, mesh){
  var managedResources;
  managedResources = castTo($get_8(meshes, app), 50);
  !managedResources && (managedResources = new Array_0);
  $add_0(managedResources, mesh);
  $put_4(meshes, app, managedResources);
}

defineClass(182, 1, {}, Mesh_0, Mesh_1);
_.autoBind = true;
_.isVertexArray = false;
var meshes;
var Lcom_badlogic_gdx_graphics_Mesh_2_classLit = createForClass('com.badlogic.gdx.graphics', 'Mesh', 182);
function $clinit_Mesh$VertexDataType(){
  $clinit_Mesh$VertexDataType = emptyMethod;
  VertexArray = new Mesh$VertexDataType('VertexArray', 0);
  VertexBufferObject = new Mesh$VertexDataType('VertexBufferObject', 1);
  VertexBufferObjectSubData = new Mesh$VertexDataType('VertexBufferObjectSubData', 2);
  VertexBufferObjectWithVAO = new Mesh$VertexDataType('VertexBufferObjectWithVAO', 3);
}

function Mesh$VertexDataType(enum$name, enum$ordinal){
  Enum.call(this, enum$name, enum$ordinal);
}

function values_2(){
  $clinit_Mesh$VertexDataType();
  return stampJavaTypeInfo(getClassLiteralForArray(Lcom_badlogic_gdx_graphics_Mesh$VertexDataType_2_classLit, 1), $intern_1, 109, 0, [VertexArray, VertexBufferObject, VertexBufferObjectSubData, VertexBufferObjectWithVAO]);
}

defineClass(109, 8, {109:1, 3:1, 7:1, 8:1}, Mesh$VertexDataType);
var VertexArray, VertexBufferObject, VertexBufferObjectSubData, VertexBufferObjectWithVAO;
var Lcom_badlogic_gdx_graphics_Mesh$VertexDataType_2_classLit = createForEnum('com.badlogic.gdx.graphics', 'Mesh/VertexDataType', 109, values_2);
function $setToOrtho(this$static, viewportWidth, viewportHeight){
  $set_5(this$static.up, 0, -1, 0);
  $set_5(this$static.direction_0, 0, 0, 1);
  $set_5(this$static.position, this$static.zoom * viewportWidth / 2, this$static.zoom * viewportHeight / 2, 0);
  this$static.viewportWidth = viewportWidth;
  this$static.viewportHeight = viewportHeight;
  $update_2(this$static);
}

function $update_2(this$static){
  $setToOrtho_0(this$static.projection, this$static.zoom * -this$static.viewportWidth / 2, this$static.zoom * (this$static.viewportWidth / 2), this$static.zoom * -(this$static.viewportHeight / 2), this$static.zoom * this$static.viewportHeight / 2, this$static.near, this$static.far);
  $setToLookAt(this$static.view, this$static.direction_0, this$static.up);
  $translate(this$static.view, -this$static.position.x_0, -this$static.position.y_0, -this$static.position.z_0);
  $set_1(this$static.combined, this$static.projection.val_0);
  $clinit_Matrix4();
  matrix4_mul(this$static.combined.val_0, this$static.view.val_0);
  $set_1(this$static.invProjectionView, this$static.combined.val_0);
  matrix4_inv(this$static.invProjectionView.val_0);
  $update_3(this$static.frustum, this$static.invProjectionView);
}

function OrthographicCamera(){
  this.position = new Vector3;
  this.direction_0 = new Vector3_0(0, 0, -1);
  this.up = new Vector3_0(0, 1, 0);
  this.projection = new Matrix4;
  this.view = new Matrix4;
  this.combined = new Matrix4;
  this.invProjectionView = new Matrix4;
  this.frustum = new Frustum;
  $clinit_Vector3();
  new Ray(new Vector3, new Vector3);
  this.near = 0;
}

defineClass(410, 409, {}, OrthographicCamera);
_.zoom = 1;
var Lcom_badlogic_gdx_graphics_OrthographicCamera_2_classLit = createForClass('com.badlogic.gdx.graphics', 'OrthographicCamera', 410);
function $clinit_Pixmap(){
  $clinit_Pixmap = emptyMethod;
  pixmaps = new HashMap;
}

function $create(this$static){
  this$static.canvas = createIfSupported();
  $setWidth_0($getCanvasElement(this$static.canvas), this$static.width_0);
  $setHeight_0($getCanvasElement(this$static.canvas), this$static.height_0);
  this$static.context = $getContext2d(this$static.canvas);
  $setGlobalCompositeOperation(this$static.context, ($clinit_Context2d$Composite() , SOURCE_OVER).value_0);
}

function $drawPixel(this$static, x_0, y_0, color_0){
  !this$static.canvas && $create(this$static);
  this$static.r = color_0 >>> 24 & 255;
  this$static.g = color_0 >>> 16 & 255;
  this$static.b = color_0 >>> 8 & 255;
  this$static.a = (color_0 & 255) / 255;
  this$static.color_0 = 'rgba(' + this$static.r + ',' + this$static.g + ',' + this$static.b + ',' + this$static.a + ')';
  $setFillStyleWeb(this$static.context, this$static.color_0);
  $setStrokeStyleWeb(this$static.context, this$static.color_0);
  $rectangle(this$static, x_0, y_0);
}

function $drawPixmap(this$static, pixmap, srcWidth, srcHeight){
  var image;
  image = (!pixmap.canvas && $create(pixmap) , $getCanvasElement(pixmap.canvas));
  $image(this$static, image, srcWidth, srcHeight, srcWidth, srcHeight);
}

function $drawPixmap_0(this$static, pixmap, srcWidth, srcHeight, dstWidth, dstHeight){
  $image(this$static, (!pixmap.canvas && $create(pixmap) , $getCanvasElement(pixmap.canvas)), srcWidth, srcHeight, dstWidth, dstHeight);
}

function $image(this$static, image, srcWidth, srcHeight, dstWidth, dstHeight){
  !this$static.canvas && $create(this$static);
  if (this$static.blending == 0) {
    this$static.context.fillStyle = 'rgba(255,255,255,1.0)';
    this$static.context.strokeStyle = 'rgba(255,255,255,1.0)';
    this$static.context.globalCompositeOperation = 'destination-out';
    this$static.context.beginPath();
    $rect_0(this$static.context, 0, 0, dstWidth, dstHeight);
    !this$static.canvas && $create(this$static);
    this$static.context.fill();
    this$static.context.closePath();
    $setFillStyleWeb(this$static.context, this$static.color_0);
    $setStrokeStyleWeb(this$static.context, this$static.color_0);
    $setGlobalCompositeOperation(this$static.context, ($clinit_Context2d$Composite() , SOURCE_OVER).value_0);
  }
  srcWidth != 0 && srcHeight != 0 && dstWidth != 0 && dstHeight != 0 && $drawImage(this$static.context, image, 0, 0, srcWidth, srcHeight, 0, 0, dstWidth, dstHeight);
}

function $rectangle(this$static, x_0, y_0){
  !this$static.canvas && $create(this$static);
  if (this$static.blending == 0) {
    this$static.context.fillStyle = 'rgba(255,255,255,1.0)';
    this$static.context.strokeStyle = 'rgba(255,255,255,1.0)';
    this$static.context.globalCompositeOperation = 'destination-out';
    this$static.context.beginPath();
    $rect_0(this$static.context, x_0, y_0, 1, 1);
    !this$static.canvas && $create(this$static);
    this$static.context.fill();
    this$static.context.closePath();
    $setFillStyleWeb(this$static.context, this$static.color_0);
    $setStrokeStyleWeb(this$static.context, this$static.color_0);
    $setGlobalCompositeOperation(this$static.context, ($clinit_Context2d$Composite() , SOURCE_OVER).value_0);
  }
  this$static.context.beginPath();
  $rect_0(this$static.context, x_0, y_0, 1, 1);
  !this$static.canvas && $create(this$static);
  this$static.context.fill();
  this$static.context.closePath();
}

function $setBlending(this$static){
  this$static.blending = 0;
  !this$static.canvas && $create(this$static);
  $setGlobalCompositeOperation(this$static.context, ($clinit_Context2d$Composite() , SOURCE_OVER).value_0);
}

function Pixmap(width_0, height){
  $clinit_Pixmap();
  this.color_0 = 'rgba(' + this.r + ',' + this.g + ',' + this.b + ',' + this.a + ')';
  this.width_0 = width_0;
  this.height_0 = height;
  this.format = ($clinit_Pixmap$Format() , RGBA8888);
  this.buffer_0 = newIntBuffer();
  this.id_0 = nextId++;
  this.buffer_0.put_1(0, this.id_0);
  $put_4(pixmaps, valueOf_1(this.id_0), this);
}

defineClass(118, 1, {118:1}, Pixmap);
_.a = 0;
_.b = 255;
_.blending = 1;
_.g = 255;
_.height_0 = 0;
_.id_0 = 0;
_.r = 255;
_.width_0 = 0;
var nextId = 0, pixmaps;
var Lcom_badlogic_gdx_graphics_Pixmap_2_classLit = createForClass('com.badlogic.gdx.graphics', 'Pixmap', 118);
function $clinit_Pixmap$Format(){
  $clinit_Pixmap$Format = emptyMethod;
  Alpha = new Pixmap$Format('Alpha', 0);
  Intensity = new Pixmap$Format('Intensity', 1);
  LuminanceAlpha = new Pixmap$Format('LuminanceAlpha', 2);
  RGB565 = new Pixmap$Format('RGB565', 3);
  RGBA4444 = new Pixmap$Format('RGBA4444', 4);
  RGB888 = new Pixmap$Format('RGB888', 5);
  RGBA8888 = new Pixmap$Format('RGBA8888', 6);
}

function Pixmap$Format(enum$name, enum$ordinal){
  Enum.call(this, enum$name, enum$ordinal);
}

function values_3(){
  $clinit_Pixmap$Format();
  return stampJavaTypeInfo(getClassLiteralForArray(Lcom_badlogic_gdx_graphics_Pixmap$Format_2_classLit, 1), $intern_1, 87, 0, [Alpha, Intensity, LuminanceAlpha, RGB565, RGBA4444, RGB888, RGBA8888]);
}

defineClass(87, 8, {87:1, 3:1, 7:1, 8:1}, Pixmap$Format);
var Alpha, Intensity, LuminanceAlpha, RGB565, RGB888, RGBA4444, RGBA8888;
var Lcom_badlogic_gdx_graphics_Pixmap$Format_2_classLit = createForEnum('com.badlogic.gdx.graphics', 'Pixmap/Format', 87, values_3);
function $clinit_Texture(){
  $clinit_Texture = emptyMethod;
  managedTextures = new HashMap;
}

function $load_0(this$static, data_0){
  if (!!this$static.data_0 && data_0.managed != this$static.data_0.managed)
    throw toJs(new GdxRuntimeException('New data must have the same managed status as the old data'));
  this$static.data_0 = data_0;
  $glBindTexture(gl_0, this$static.glTarget, this$static.glHandle);
  uploadImageData(data_0);
  $unsafeSetFilter(this$static, this$static.minFilter, this$static.magFilter);
  $unsafeSetWrap(this$static, this$static.uWrap, this$static.vWrap);
  $unsafeSetAnisotropicFilter(this$static, this$static.anisotropicFilterLevel, true);
  $glBindTexture(gl_0, this$static.glTarget, 0);
}

function Texture(glHandle, data_0){
  this.minFilter = ($clinit_Texture$TextureFilter() , Nearest);
  this.magFilter = Nearest;
  this.uWrap = ($clinit_Texture$TextureWrap() , ClampToEdge);
  this.vWrap = ClampToEdge;
  this.glTarget = 3553;
  this.glHandle = glHandle;
  $load_0(this, data_0);
  data_0.managed && addManagedTexture(app_0, this);
}

function Texture_0(pixmap){
  $clinit_Texture();
  Texture_1.call(this, new PixmapTextureData(pixmap));
}

function Texture_1(data_0){
  Texture.call(this, $glGenTexture(gl_0), data_0);
}

function addManagedTexture(app, texture){
  var managedTextureArray;
  managedTextureArray = castTo($get_8(managedTextures, app), 50);
  !managedTextureArray && (managedTextureArray = new Array_0);
  $add_0(managedTextureArray, texture);
  $put_4(managedTextures, app, managedTextureArray);
}

defineClass(78, 431, {78:1}, Texture_0);
_.toString_0 = function toString_6(){
  var number;
  return $ensureNamesAreInitialized(Lcom_badlogic_gdx_graphics_Texture_2_classLit) , Lcom_badlogic_gdx_graphics_Texture_2_classLit.typeName + '@' + (number = hashCode__I__devirtual$(this) >>> 0 , number.toString(16));
}
;
var managedTextures;
var Lcom_badlogic_gdx_graphics_Texture_2_classLit = createForClass('com.badlogic.gdx.graphics', 'Texture', 78);
function $clinit_Texture$TextureFilter(){
  $clinit_Texture$TextureFilter = emptyMethod;
  Nearest = new Texture$TextureFilter('Nearest', 0, 9728);
  Linear = new Texture$TextureFilter('Linear', 1, 9729);
  MipMap = new Texture$TextureFilter('MipMap', 2, 9987);
  MipMapNearestNearest = new Texture$TextureFilter('MipMapNearestNearest', 3, 9984);
  MipMapLinearNearest = new Texture$TextureFilter('MipMapLinearNearest', 4, 9985);
  MipMapNearestLinear = new Texture$TextureFilter('MipMapNearestLinear', 5, 9986);
  MipMapLinearLinear = new Texture$TextureFilter('MipMapLinearLinear', 6, 9987);
}

function Texture$TextureFilter(enum$name, enum$ordinal, glEnum){
  Enum.call(this, enum$name, enum$ordinal);
  this.glEnum = glEnum;
}

function values_4(){
  $clinit_Texture$TextureFilter();
  return stampJavaTypeInfo(getClassLiteralForArray(Lcom_badlogic_gdx_graphics_Texture$TextureFilter_2_classLit, 1), $intern_1, 85, 0, [Nearest, Linear, MipMap, MipMapNearestNearest, MipMapLinearNearest, MipMapNearestLinear, MipMapLinearLinear]);
}

defineClass(85, 8, {85:1, 3:1, 7:1, 8:1}, Texture$TextureFilter);
_.glEnum = 0;
var Linear, MipMap, MipMapLinearLinear, MipMapLinearNearest, MipMapNearestLinear, MipMapNearestNearest, Nearest;
var Lcom_badlogic_gdx_graphics_Texture$TextureFilter_2_classLit = createForEnum('com.badlogic.gdx.graphics', 'Texture/TextureFilter', 85, values_4);
function $clinit_Texture$TextureWrap(){
  $clinit_Texture$TextureWrap = emptyMethod;
  MirroredRepeat = new Texture$TextureWrap('MirroredRepeat', 0, 33648);
  ClampToEdge = new Texture$TextureWrap('ClampToEdge', 1, 33071);
  Repeat = new Texture$TextureWrap('Repeat', 2, 10497);
}

function Texture$TextureWrap(enum$name, enum$ordinal, glEnum){
  Enum.call(this, enum$name, enum$ordinal);
  this.glEnum = glEnum;
}

function values_5(){
  $clinit_Texture$TextureWrap();
  return stampJavaTypeInfo(getClassLiteralForArray(Lcom_badlogic_gdx_graphics_Texture$TextureWrap_2_classLit, 1), $intern_1, 127, 0, [MirroredRepeat, ClampToEdge, Repeat]);
}

defineClass(127, 8, {127:1, 3:1, 7:1, 8:1}, Texture$TextureWrap);
_.glEnum = 0;
var ClampToEdge, MirroredRepeat, Repeat;
var Lcom_badlogic_gdx_graphics_Texture$TextureWrap_2_classLit = createForEnum('com.badlogic.gdx.graphics', 'Texture/TextureWrap', 127, values_5);
function $equals_1(this$static, other){
  return !!other && this$static.usage == other.usage && this$static.numComponents == other.numComponents && this$static.type_0 == other.type_0 && this$static.normalized == other.normalized && $equals_5(this$static.alias, other.alias) && this$static.unit == other.unit;
}

function $getSizeInBytes(this$static){
  switch (this$static.type_0) {
    case 5125:
    case 5124:
    case 5126:
    case 5132:
      return 4 * this$static.numComponents;
    case 5121:
    case 5120:
      return this$static.numComponents;
    case 5123:
    case 5122:
      return 2 * this$static.numComponents;
  }
  return 0;
}

function $hashCode(this$static){
  var result;
  result = (this$static.usageIndex << 8) + (this$static.unit & 255);
  result = 541 * result + this$static.numComponents;
  result = 541 * result + $hashCode_2(this$static.alias);
  return result;
}

function VertexAttribute(usage, numComponents, alias){
  this.usage = usage;
  this.numComponents = numComponents;
  this.type_0 = usage == 4?5121:5126;
  this.normalized = usage == 4;
  this.alias = alias;
  this.unit = 0;
  this.usageIndex = numberOfTrailingZeros(usage);
}

defineClass(62, 1, {62:1}, VertexAttribute);
_.equals_0 = function equals_2(obj){
  if (!instanceOf(obj, 62)) {
    return false;
  }
  return $equals_1(this, castTo(obj, 62));
}
;
_.hashCode_0 = function hashCode_3(){
  return $hashCode(this);
}
;
_.normalized = false;
_.numComponents = 0;
_.offset = 0;
_.type_0 = 0;
_.unit = 0;
_.usage = 0;
_.usageIndex = 0;
var Lcom_badlogic_gdx_graphics_VertexAttribute_2_classLit = createForClass('com.badlogic.gdx.graphics', 'VertexAttribute', 62);
function $calculateOffsets(this$static){
  var attribute, count, i;
  count = 0;
  for (i = 0; i < this$static.attributes.length; i++) {
    attribute = this$static.attributes[i];
    attribute.offset = count;
    count += $getSizeInBytes(attribute);
  }
  return count;
}

function $compareTo_0(this$static, o){
  var i, m1, m2, va0, va1;
  if (this$static.attributes.length != o.attributes.length)
    return this$static.attributes.length - o.attributes.length;
  m1 = $getMask(this$static);
  m2 = $getMask(o);
  if (compare_0(m1, m2) != 0)
    return compare_0(m1, m2) < 0?-1:1;
  for (i = this$static.attributes.length - 1; i >= 0; --i) {
    va0 = this$static.attributes[i];
    va1 = o.attributes[i];
    if (va0.usage != va1.usage)
      return va0.usage - va1.usage;
    if (va0.unit != va1.unit)
      return va0.unit - va1.unit;
    if (va0.numComponents != va1.numComponents)
      return va0.numComponents - va1.numComponents;
    if (va0.normalized != va1.normalized)
      return va0.normalized?1:-1;
    if (va0.type_0 != va1.type_0)
      return va0.type_0 - va1.type_0;
  }
  return 0;
}

function $get_0(this$static, index_0){
  return this$static.attributes[index_0];
}

function $getMask(this$static){
  var i, result;
  if (eq_0(this$static.mask, -1)) {
    result = 0;
    for (i = 0; i < this$static.attributes.length; i++) {
      result = or_0(result, this$static.attributes[i].usage);
    }
    this$static.mask = result;
  }
  return this$static.mask;
}

function VertexAttributes(attributes){
  var i, list;
  if (attributes.length == 0)
    throw toJs(new IllegalArgumentException_0('attributes must be >= 1'));
  list = initUnidimensionalArray(Lcom_badlogic_gdx_graphics_VertexAttribute_2_classLit, $intern_9, 62, attributes.length, 0, 1);
  for (i = 0; i < attributes.length; i++)
    list[i] = attributes[i];
  this.attributes = list;
  this.vertexSize = $calculateOffsets(this);
}

defineClass(111, 1, {111:1, 7:1}, VertexAttributes);
_.compareTo = function compareTo_0(o){
  return $compareTo_0(this, castTo(o, 111));
}
;
_.equals_0 = function equals_3(obj){
  var i, other;
  if (obj === this)
    return true;
  if (!instanceOf(obj, 111))
    return false;
  other = castTo(obj, 111);
  if (this.attributes.length != other.attributes.length)
    return false;
  for (i = 0; i < this.attributes.length; i++) {
    if (!$equals_1(this.attributes[i], other.attributes[i]))
      return false;
  }
  return true;
}
;
_.hashCode_0 = function hashCode_4(){
  var i, result;
  result = 61 * this.attributes.length;
  for (i = 0; i < this.attributes.length; i++)
    result = add_2(mul_1(result, 61), $hashCode(this.attributes[i]));
  return toInt_0(xor_0(result, createLongEmul(shr(isSmallLong0(result)?toBigLong(result):result, 32))));
}
;
_.iterator = function iterator_0(){
  !this.iterable && (this.iterable = new VertexAttributes$ReadonlyIterable(this.attributes));
  return $iterator(this.iterable);
}
;
_.toString_0 = function toString_7(){
  var builder, i;
  builder = new StringBuilder;
  builder.string += '[';
  for (i = 0; i < this.attributes.length; i++) {
    builder.string += '(';
    $append_3(builder, this.attributes[i].alias);
    builder.string += ', ';
    $append_0(builder, this.attributes[i].usage);
    builder.string += ', ';
    $append_0(builder, this.attributes[i].numComponents);
    builder.string += ', ';
    $append_0(builder, this.attributes[i].offset);
    builder.string += ')';
    builder.string += '\n';
  }
  builder.string += ']';
  return builder.string;
}
;
_.mask = -1;
_.vertexSize = 0;
var Lcom_badlogic_gdx_graphics_VertexAttributes_2_classLit = createForClass('com.badlogic.gdx.graphics', 'VertexAttributes', 111);
function $iterator(this$static){
  if (allocateIterators)
    return new VertexAttributes$ReadonlyIterator(this$static.array);
  if (!this$static.iterator1) {
    this$static.iterator1 = new VertexAttributes$ReadonlyIterator(this$static.array);
    this$static.iterator2 = new VertexAttributes$ReadonlyIterator(this$static.array);
  }
  if (!this$static.iterator1.valid) {
    this$static.iterator1.index_0 = 0;
    this$static.iterator1.valid = true;
    this$static.iterator2.valid = false;
    return this$static.iterator1;
  }
  this$static.iterator2.index_0 = 0;
  this$static.iterator2.valid = true;
  this$static.iterator1.valid = false;
  return this$static.iterator2;
}

function VertexAttributes$ReadonlyIterable(array){
  this.array = array;
}

defineClass(437, 1, {}, VertexAttributes$ReadonlyIterable);
_.iterator = function iterator_1(){
  return $iterator(this);
}
;
var Lcom_badlogic_gdx_graphics_VertexAttributes$ReadonlyIterable_2_classLit = createForClass('com.badlogic.gdx.graphics', 'VertexAttributes/ReadonlyIterable', 437);
function VertexAttributes$ReadonlyIterator(array){
  this.array = array;
}

defineClass(184, 1, {}, VertexAttributes$ReadonlyIterator);
_.hasNext_0 = function hasNext(){
  if (!this.valid)
    throw toJs(new GdxRuntimeException('#iterator() cannot be used nested.'));
  return this.index_0 < this.array.length;
}
;
_.iterator = function iterator_2(){
  return this;
}
;
_.next_1 = function next_0(){
  if (this.index_0 >= this.array.length)
    throw toJs(new NoSuchElementException_0('' + this.index_0));
  if (!this.valid)
    throw toJs(new GdxRuntimeException('#iterator() cannot be used nested.'));
  return this.array[this.index_0++];
}
;
_.index_0 = 0;
_.valid = true;
var Lcom_badlogic_gdx_graphics_VertexAttributes$ReadonlyIterator_2_classLit = createForClass('com.badlogic.gdx.graphics', 'VertexAttributes/ReadonlyIterator', 184);
function $flip(this$static, x_0, y_0){
  var temp;
  if (x_0) {
    temp = this$static.u;
    this$static.u = this$static.u2;
    this$static.u2 = temp;
  }
  if (y_0) {
    temp = this$static.v;
    this$static.v = this$static.v2;
    this$static.v2 = temp;
  }
}

function $setRegion(this$static, u, v, u2, v2){
  var adjustX, adjustY, texHeight, texWidth;
  texWidth = this$static.texture.data_0.pixmap.width_0;
  texHeight = this$static.texture.data_0.pixmap.height_0;
  this$static.regionWidth = round_int($wnd.Math.round($wnd.Math.abs(u2 - u) * texWidth));
  this$static.regionHeight = round_int($wnd.Math.round($wnd.Math.abs(v2 - v) * texHeight));
  if (this$static.regionWidth == 1 && this$static.regionHeight == 1) {
    adjustX = 0.25 / texWidth;
    u += adjustX;
    u2 -= adjustX;
    adjustY = 0.25 / texHeight;
    v += adjustY;
    v2 -= adjustY;
  }
  this$static.u = u;
  this$static.v = v;
  this$static.u2 = u2;
  this$static.v2 = v2;
}

function TextureRegion(texture, x_0, y_0, width_0, height){
  var invTexWidth, invTexHeight;
  this.texture = texture;
  invTexWidth = 1 / this.texture.data_0.pixmap.width_0;
  invTexHeight = 1 / this.texture.data_0.pixmap.height_0;
  this.setRegion(x_0 * invTexWidth, y_0 * invTexHeight, (x_0 + width_0) * invTexWidth, (y_0 + height) * invTexHeight);
  this.regionWidth = $wnd.Math.abs(width_0);
  this.regionHeight = $wnd.Math.abs(height);
}

defineClass(489, 1, {}, TextureRegion);
_.setRegion = function setRegion(u, v, u2, v2){
  $setRegion(this, u, v, u2, v2);
}
;
_.regionHeight = 0;
_.regionWidth = 0;
_.u = 0;
_.u2 = 0;
_.v = 0;
_.v2 = 0;
var Lcom_badlogic_gdx_graphics_g2d_TextureRegion_2_classLit = createForClass('com.badlogic.gdx.graphics.g2d', 'TextureRegion', 489);
function $getVertices(this$static){
  var cos_0, localX, localX2, localX2Cos, localX2Sin, localXCos, localXSin, localY, localY2, localY2Cos, localY2Sin, localYCos, localYSin, sin_0, vertices, worldOriginX, worldOriginY, x1, x2, x3, y1, y2, y3;
  if (this$static.dirty) {
    this$static.dirty = false;
    vertices = this$static.vertices;
    localX = -this$static.originX;
    localY = -this$static.originY;
    localX2 = localX + this$static.width_0;
    localY2 = localY + this$static.height_0;
    worldOriginX = this$static.x_0 - localX;
    worldOriginY = this$static.y_0 - localY;
    if (this$static.scaleX != 1 || this$static.scaleY != 1) {
      localX *= this$static.scaleX;
      localY *= this$static.scaleY;
      localX2 *= this$static.scaleX;
      localY2 *= this$static.scaleY;
    }
    if (this$static.rotation != 0) {
      cos_0 = cosDeg(this$static.rotation);
      sin_0 = sinDeg(this$static.rotation);
      localXCos = localX * cos_0;
      localXSin = localX * sin_0;
      localYCos = localY * cos_0;
      localYSin = localY * sin_0;
      localX2Cos = localX2 * cos_0;
      localX2Sin = localX2 * sin_0;
      localY2Cos = localY2 * cos_0;
      localY2Sin = localY2 * sin_0;
      x1 = localXCos - localYSin + worldOriginX;
      y1 = localYCos + localXSin + worldOriginY;
      vertices[0] = x1;
      vertices[1] = y1;
      x2 = localXCos - localY2Sin + worldOriginX;
      y2 = localY2Cos + localXSin + worldOriginY;
      vertices[5] = x2;
      vertices[6] = y2;
      x3 = localX2Cos - localY2Sin + worldOriginX;
      y3 = localY2Cos + localX2Sin + worldOriginY;
      vertices[10] = x3;
      vertices[11] = y3;
      vertices[15] = x1 + (x3 - x2);
      vertices[16] = y3 - (y2 - y1);
    }
     else {
      x1 = localX + worldOriginX;
      y1 = localY + worldOriginY;
      x2 = localX2 + worldOriginX;
      y2 = localY2 + worldOriginY;
      vertices[0] = x1;
      vertices[1] = y1;
      vertices[5] = x1;
      vertices[6] = y2;
      vertices[10] = x2;
      vertices[11] = y2;
      vertices[15] = x2;
      vertices[16] = y1;
    }
  }
  return this$static.vertices;
}

function $setAlpha(this$static, a){
  if (this$static.color_0.a != a) {
    this$static.color_0.a = a;
    this$static.packedColor = $toFloatBits(this$static.color_0);
    this$static.vertices[2] = this$static.packedColor;
    this$static.vertices[7] = this$static.packedColor;
    this$static.vertices[12] = this$static.packedColor;
    this$static.vertices[17] = this$static.packedColor;
  }
}

function $setColor(this$static, r, g, b, a){
  var vertices;
  $set(this$static.color_0, r, g, b, a);
  this$static.packedColor = $toFloatBits(this$static.color_0);
  vertices = this$static.vertices;
  vertices[2] = this$static.packedColor;
  vertices[7] = this$static.packedColor;
  vertices[12] = this$static.packedColor;
  vertices[17] = this$static.packedColor;
}

function $setOrigin(this$static, originX, originY){
  this$static.originX = originX;
  this$static.originY = originY;
  this$static.dirty = true;
}

function $setPosition(this$static, x_0, y_0){
  var vertices, x2, y2;
  this$static.x_0 = x_0;
  this$static.y_0 = y_0;
  if (this$static.dirty)
    return;
  if (this$static.rotation != 0 || this$static.scaleX != 1 || this$static.scaleY != 1) {
    this$static.dirty = true;
    return;
  }
  x2 = x_0 + this$static.width_0;
  y2 = y_0 + this$static.height_0;
  vertices = this$static.vertices;
  vertices[0] = x_0;
  vertices[1] = y_0;
  vertices[5] = x_0;
  vertices[6] = y2;
  vertices[10] = x2;
  vertices[11] = y2;
  vertices[15] = x2;
  vertices[16] = y_0;
}

function $setRegion_0(this$static, u, v, u2, v2){
  var vertices;
  $setRegion(this$static, u, v, u2, v2);
  vertices = this$static.vertices;
  vertices[3] = u;
  vertices[4] = v2;
  vertices[8] = u;
  vertices[9] = v;
  vertices[13] = u2;
  vertices[14] = v;
  vertices[18] = u2;
  vertices[19] = v2;
}

function $setSize(this$static, width_0, height){
  var vertices, x2, y2;
  this$static.width_0 = width_0;
  this$static.height_0 = height;
  if (this$static.dirty)
    return;
  if (this$static.rotation != 0 || this$static.scaleX != 1 || this$static.scaleY != 1) {
    this$static.dirty = true;
    return;
  }
  x2 = this$static.x_0 + width_0;
  y2 = this$static.y_0 + height;
  vertices = this$static.vertices;
  vertices[0] = this$static.x_0;
  vertices[1] = this$static.y_0;
  vertices[5] = this$static.x_0;
  vertices[6] = y2;
  vertices[10] = x2;
  vertices[11] = y2;
  vertices[15] = x2;
  vertices[16] = this$static.y_0;
}

function Sprite(region){
  this.vertices = initUnidimensionalArray(F_classLit, $intern_10, 5, 20, 15, 1);
  this.color_0 = new Color(1, 1, 1, 1);
  this.packedColor = ($clinit_Color() , WHITE_FLOAT_BITS);
  this.scaleX = 1;
  this.scaleY = 1;
  this.dirty = true;
  this.texture = region.texture;
  $setRegion_0(this, region.u, region.v, region.u2, region.v2);
  $setColor(this, 1, 1, 1, 1);
  $setSize(this, region.regionWidth, region.regionHeight);
  $setOrigin(this, this.width_0 / 2, this.height_0 / 2);
}

defineClass(490, 489, {}, Sprite);
_.setRegion = function setRegion_0(u, v, u2, v2){
  $setRegion_0(this, u, v, u2, v2);
}
;
_.dirty = false;
_.height_0 = 0;
_.originX = 0;
_.originY = 0;
_.packedColor = 0;
_.rotation = 0;
_.scaleX = 0;
_.scaleY = 0;
_.width_0 = 0;
_.x_0 = 0;
_.y_0 = 0;
var Lcom_badlogic_gdx_graphics_g2d_Sprite_2_classLit = createForClass('com.badlogic.gdx.graphics.g2d', 'Sprite', 490);
function $clinit_SpriteBatch(){
  $clinit_SpriteBatch = emptyMethod;
  defaultVertexDataType = ($clinit_Mesh$VertexDataType() , VertexBufferObject);
}

function $begin(this$static){
  if (this$static.drawing)
    throw toJs(new IllegalStateException_0('SpriteBatch.end must be called before begin.'));
  this$static.renderCalls = 0;
  gl_0.gl.depthMask(false);
  $bind_0(this$static.shader);
  $setupMatrices(this$static);
  this$static.drawing = true;
}

function $draw(this$static, texture, spriteVertices, offset, count){
  var copyCount, remainingVertices, verticesLength;
  if (!this$static.drawing)
    throw toJs(new IllegalStateException_0('SpriteBatch.begin must be called before draw.'));
  verticesLength = this$static.vertices.length;
  remainingVertices = verticesLength;
  if (texture != this$static.lastTexture) {
    $flush(this$static);
    this$static.lastTexture = texture;
  }
   else {
    remainingVertices -= this$static.idx;
    if (remainingVertices == 0) {
      $flush(this$static);
      remainingVertices = verticesLength;
    }
  }
  copyCount = $wnd.Math.min(remainingVertices, count);
  arraycopy(spriteVertices, offset, this$static.vertices, this$static.idx, copyCount);
  this$static.idx += copyCount;
  count -= copyCount;
  while (count > 0) {
    offset += copyCount;
    $flush(this$static);
    copyCount = $wnd.Math.min(verticesLength, count);
    arraycopy(spriteVertices, offset, this$static.vertices, 0, copyCount);
    this$static.idx += copyCount;
    count -= copyCount;
  }
}

function $end(this$static){
  var gl;
  if (!this$static.drawing)
    throw toJs(new IllegalStateException_0('SpriteBatch.begin must be called before end.'));
  this$static.idx > 0 && $flush(this$static);
  this$static.lastTexture = null;
  this$static.drawing = false;
  gl = gl_0;
  gl.gl.depthMask(true);
  gl.gl.disable(3042);
}

function $flush(this$static){
  var count, indicesBuffer, mesh, spritesInBatch;
  if (this$static.idx == 0)
    return;
  ++this$static.renderCalls;
  ++this$static.totalRenderCalls;
  spritesInBatch = this$static.idx / 20 | 0;
  spritesInBatch > this$static.maxSpritesInBatch && (this$static.maxSpritesInBatch = spritesInBatch);
  count = spritesInBatch * 6;
  $bind(this$static.lastTexture);
  mesh = this$static.mesh;
  $setVertices(mesh, this$static.vertices, this$static.idx);
  if (this$static.currentDataType == ($clinit_Mesh$VertexDataType() , VertexArray)) {
    indicesBuffer = mesh.indices.getBuffer(true);
    $position(indicesBuffer, 0);
    $limit(indicesBuffer, count);
  }
  gl_0.gl.enable(3042);
  this$static.blendSrcFunc != -1 && $glBlendFuncSeparate(gl_0, this$static.blendSrcFunc, this$static.blendDstFunc, this$static.blendSrcFuncAlpha, this$static.blendDstFuncAlpha);
  $render_0(mesh, this$static.shader, count);
  this$static.idx = 0;
}

function $setBlendFunctionSeparate(this$static, srcFuncColor, dstFuncColor, srcFuncAlpha, dstFuncAlpha){
  if (this$static.blendSrcFunc == srcFuncColor && this$static.blendDstFunc == dstFuncColor && this$static.blendSrcFuncAlpha == srcFuncAlpha && this$static.blendDstFuncAlpha == dstFuncAlpha)
    return;
  $flush(this$static);
  this$static.blendSrcFunc = srcFuncColor;
  this$static.blendDstFunc = dstFuncColor;
  this$static.blendSrcFuncAlpha = srcFuncAlpha;
  this$static.blendDstFuncAlpha = dstFuncAlpha;
}

function $setColor_0(this$static, tint){
  var color_0;
  $set_0(this$static.color_0, tint);
  color_0 = round_int(255 * tint.a) << 24 | round_int(255 * tint.b) << 16 | round_int(255 * tint.g) << 8 | round_int(255 * tint.r);
  $clinit_Numbers();
  wia[0] = color_0 & $intern_7;
  wfa[0];
}

function $setProjectionMatrix(this$static, projection){
  this$static.drawing && $flush(this$static);
  $set_1(this$static.projectionMatrix, projection.val_0);
  this$static.drawing && $setupMatrices(this$static);
}

function $setTransformMatrix(this$static, transform){
  this$static.drawing && $flush(this$static);
  $set_1(this$static.transformMatrix, transform.val_0);
  this$static.drawing && $setupMatrices(this$static);
}

function $setupMatrices(this$static){
  $mul($set_1(this$static.combinedMatrix, this$static.projectionMatrix.val_0), this$static.transformMatrix);
  $setUniformMatrix_0(this$static.shader, 'u_projTrans', this$static.combinedMatrix);
  $setUniformi(this$static.shader, 'u_texture', 0);
}

function SpriteBatch(){
  $clinit_SpriteBatch();
  SpriteBatch_0.call(this);
}

function SpriteBatch_0(){
  var i, indices, j, vertexDataType;
  this.transformMatrix = new Matrix4;
  this.projectionMatrix = new Matrix4;
  this.combinedMatrix = new Matrix4;
  this.color_0 = new Color(1, 1, 1, 1);
  $clinit_Color();
  vertexDataType = defaultVertexDataType;
  this.currentDataType = vertexDataType;
  this.mesh = new Mesh_0(this.currentDataType, stampJavaTypeInfo(getClassLiteralForArray(Lcom_badlogic_gdx_graphics_VertexAttribute_2_classLit, 1), $intern_9, 62, 0, [new VertexAttribute(1, 2, 'a_position'), new VertexAttribute(4, 4, 'a_color'), new VertexAttribute(16, 2, 'a_texCoord0')]));
  $setToOrtho2D(this.projectionMatrix, graphics.canvas.width, graphics.canvas.height);
  this.vertices = initUnidimensionalArray(F_classLit, $intern_10, 5, 20000, 15, 1);
  indices = initUnidimensionalArray(S_classLit, $intern_2, 5, 6000, 15, 1);
  j = 0;
  for (i = 0; i < 6000; i += 6 , j = j + 4 << 16 >> 16) {
    indices[i] = j;
    indices[i + 1] = j + 1 << 16 >> 16;
    indices[i + 2] = j + 2 << 16 >> 16;
    indices[i + 3] = j + 2 << 16 >> 16;
    indices[i + 4] = j + 3 << 16 >> 16;
    indices[i + 5] = j;
  }
  $setIndices(this.mesh, indices);
  this.shader = createDefaultShader();
  if (vertexDataType != ($clinit_Mesh$VertexDataType() , VertexArray)) {
    this.mesh.indices.bind_0();
    this.mesh.indices.unbind();
  }
}

function createDefaultShader(){
  var shader;
  shader = new ShaderProgram('attribute vec4 a_position;\nattribute vec4 a_color;\nattribute vec2 a_texCoord0;\nuniform mat4 u_projTrans;\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\n\nvoid main()\n{\n   v_color = a_color;\n   v_color.a = v_color.a * (255.0/254.0);\n   v_texCoords = a_texCoord0;\n   gl_Position =  u_projTrans * a_position;\n}\n', '#ifdef GL_ES\n#define LOWP lowp\nprecision mediump float;\n#else\n#define LOWP \n#endif\nvarying LOWP vec4 v_color;\nvarying vec2 v_texCoords;\nuniform sampler2D u_texture;\nvoid main()\n{\n  gl_FragColor = v_color * texture2D(u_texture, v_texCoords);\n}');
  if (!shader.isCompiled)
    throw toJs(new IllegalArgumentException_0('Error compiling shader: ' + $getLog(shader)));
  return shader;
}

defineClass(238, 1, {}, SpriteBatch);
_.blendDstFunc = 771;
_.blendDstFuncAlpha = 771;
_.blendSrcFunc = 770;
_.blendSrcFuncAlpha = 770;
_.drawing = false;
_.idx = 0;
_.lastTexture = null;
_.maxSpritesInBatch = 0;
_.renderCalls = 0;
_.totalRenderCalls = 0;
var defaultVertexDataType;
var Lcom_badlogic_gdx_graphics_g2d_SpriteBatch_2_classLit = createForClass('com.badlogic.gdx.graphics.g2d', 'SpriteBatch', 238);
function $extractVersion(patternString, versionString){
  var found, matcher, pattern, result, resultSplit;
  pattern = new Pattern(patternString);
  matcher = new Matcher(pattern, versionString);
  found = $test(matcher.regExp, matcher.input_0);
  if (found) {
    result = matcher.matchResult[1];
    resultSplit = $split(result, '\\.');
    $parseInt(resultSplit[0], 2);
    resultSplit.length < 2?0:$parseInt(resultSplit[1], 0);
    resultSplit.length < 3?0:$parseInt(resultSplit[2], 0);
  }
   else {
    $log(app_0, 'GLVersion', 'Invalid version string: ' + versionString);
  }
}

function $parseInt(v, defaultValue){
  try {
    return __parseAndValidateInt(v, 10);
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 56)) {
      $error(app_0, 'libGDX GL', 'Error parsing number: ' + v + ', assuming: ' + defaultValue);
      return defaultValue;
    }
     else 
      throw toJs($e0);
  }
}

function GLVersion(versionString){
  this.type_0 = 2;
  this.type_0 == 1?$extractVersion('OpenGL ES (\\d(\\.\\d){0,2})', versionString):this.type_0 == 2?$extractVersion('WebGL (\\d(\\.\\d){0,2})', versionString):this.type_0 == 0 && $extractVersion('(\\d(\\.\\d){0,2})', versionString);
}

defineClass(405, 1, {}, GLVersion);
_.type_0 = 0;
var Lcom_badlogic_gdx_graphics_glutils_GLVersion_2_classLit = createForClass('com.badlogic.gdx.graphics.glutils', 'GLVersion', 405);
function glViewport(x_0, y_0, width_0, height){
  graphics.canvas.width != graphics.canvas.width || graphics.canvas.height != graphics.canvas.height?$glViewport(gl_0, round_int(x_0 * graphics.canvas.width / graphics.canvas.width), round_int(y_0 * graphics.canvas.height / graphics.canvas.height), round_int(width_0 * graphics.canvas.width / graphics.canvas.width), round_int(height * graphics.canvas.height / graphics.canvas.height)):$glViewport(gl_0, x_0, y_0, width_0, height);
}

function $begin_0(this$static, projModelView, primitiveType){
  $set_1(this$static.projModelView, projModelView.val_0);
  this$static.primitiveType = primitiveType;
}

function $buildVertexAttributes(){
  var array, attribs, i, i0;
  attribs = new Array_0;
  $add_0(attribs, new VertexAttribute(1, 3, 'a_position'));
  $add_0(attribs, new VertexAttribute(4, 4, 'a_color'));
  for (i0 = 0; i0 < 0; i0++) {
    $add_0(attribs, new VertexAttribute(16, 2, 'a_texCoord' + i0));
  }
  array = initUnidimensionalArray(Lcom_badlogic_gdx_graphics_VertexAttribute_2_classLit, $intern_9, 62, attribs.size_0, 0, 1);
  for (i = 0; i < attribs.size_0; i++)
    array[i] = castTo($get_1(attribs, i), 62);
  return array;
}

function $color(this$static, colorBits){
  this$static.vertices[this$static.vertexIdx + this$static.colorOffset] = colorBits;
}

function $color_0(this$static, r, g, b, a){
  var color_0;
  this$static.vertices[this$static.vertexIdx + this$static.colorOffset] = ($clinit_Color() , color_0 = round_int(255 * a) << 24 | round_int(255 * b) << 16 | round_int(255 * g) << 8 | round_int(255 * r) , $clinit_Numbers() , wia[0] = color_0 & $intern_7 , undefined , wfa[0]);
}

function $flush_0(this$static){
  var i;
  if (this$static.numVertices == 0)
    return;
  $bind_0(this$static.shader);
  $setUniformMatrix_0(this$static.shader, 'u_projModelView', this$static.projModelView);
  for (i = 0; i < this$static.numTexCoords; i++)
    $setUniformi(this$static.shader, this$static.shaderUniformNames[i], i);
  $setVertices(this$static.mesh, this$static.vertices, this$static.vertexIdx);
  $render(this$static.mesh, this$static.shader, this$static.primitiveType);
  this$static.vertexIdx = 0;
  this$static.numVertices = 0;
}

function $vertex(this$static, x_0, y_0, z_0){
  var idx;
  idx = this$static.vertexIdx;
  this$static.vertices[idx] = x_0;
  this$static.vertices[idx + 1] = y_0;
  this$static.vertices[idx + 2] = z_0;
  this$static.vertexIdx += this$static.vertexSize;
  ++this$static.numVertices;
}

function ImmediateModeRenderer20(){
  ImmediateModeRenderer20_0.call(this, createDefaultShader_0());
}

function ImmediateModeRenderer20_0(shader){
  var attribs, i;
  this.projModelView = new Matrix4;
  this.maxVertices = 5000;
  this.numTexCoords = 0;
  this.shader = shader;
  attribs = $buildVertexAttributes();
  this.mesh = new Mesh_1(attribs);
  this.vertices = initUnidimensionalArray(F_classLit, $intern_10, 5, 5000 * (this.mesh.vertices.getAttributes().vertexSize / 4 | 0), 15, 1);
  this.vertexSize = this.mesh.vertices.getAttributes().vertexSize / 4 | 0;
  $getVertexAttribute(this.mesh, 8)?$getVertexAttribute(this.mesh, 8).offset / 4 | 0:0;
  this.colorOffset = $getVertexAttribute(this.mesh, 4)?$getVertexAttribute(this.mesh, 4).offset / 4 | 0:0;
  $getVertexAttribute(this.mesh, 16)?$getVertexAttribute(this.mesh, 16).offset / 4 | 0:0;
  this.shaderUniformNames = initUnidimensionalArray(Ljava_lang_String_2_classLit, $intern_11, 2, 0, 6, 1);
  for (i = 0; i < 0; i++) {
    this.shaderUniformNames[i] = 'u_sampler' + i;
  }
}

function createDefaultShader_0(){
  var fragmentShader, program, vertexShader;
  vertexShader = createVertexShader();
  fragmentShader = createFragmentShader();
  program = new ShaderProgram(vertexShader, fragmentShader);
  if (!program.isCompiled)
    throw toJs(new GdxRuntimeException('Error compiling shader: ' + $getLog(program)));
  return program;
}

function createFragmentShader(){
  var i, i0, shader;
  shader = '#ifdef GL_ES\nprecision mediump float;\n#endif\n';
  shader += 'varying vec4 v_col;\n';
  for (i0 = 0; i0 < 0; i0++) {
    shader += 'varying vec2 v_tex' + i0 + ';\n';
    shader += 'uniform sampler2D u_sampler' + i0 + ';\n';
  }
  shader += 'void main() {\n   gl_FragColor = v_col';
  for (i = 0; i < 0; i++) {
    i == -1?(shader += ' texture2D(u_sampler' + i + ',  v_tex' + i + ')'):(shader += ' texture2D(u_sampler' + i + ',  v_tex' + i + ') *');
  }
  shader += ';\n}';
  return shader;
}

function createVertexShader(){
  var i, i0, i1, shader;
  shader = 'attribute vec4 a_position;\nattribute vec4 a_color;\n';
  for (i0 = 0; i0 < 0; i0++) {
    shader += 'attribute vec2 a_texCoord' + i0 + ';\n';
  }
  shader += 'uniform mat4 u_projModelView;\nvarying vec4 v_col;\n';
  for (i1 = 0; i1 < 0; i1++) {
    shader += 'varying vec2 v_tex' + i1 + ';\n';
  }
  shader += 'void main() {\n   gl_Position = u_projModelView * a_position;\n';
  shader += '   v_col = a_color;\n   v_col.a *= 255.0 / 254.0;\n';
  for (i = 0; i < 0; i++) {
    shader += '   v_tex' + i + ' = ' + 'a_texCoord' + i + ';\n';
  }
  shader += '   gl_PointSize = 1.0;\n}\n';
  return shader;
}

defineClass(263, 1, {}, ImmediateModeRenderer20);
_.colorOffset = 0;
_.maxVertices = 0;
_.numTexCoords = 0;
_.numVertices = 0;
_.primitiveType = 0;
_.vertexIdx = 0;
_.vertexSize = 0;
var Lcom_badlogic_gdx_graphics_glutils_ImmediateModeRenderer20_2_classLit = createForClass('com.badlogic.gdx.graphics.glutils', 'ImmediateModeRenderer20', 263);
function IndexBufferObject(maxIndices){
  this.buffer_0 = newShortBuffer(maxIndices);
  $flip_0(this.buffer_0);
  this.bufferHandle = $glGenBuffer(gl20);
  this.usage = 35048;
}

defineClass(189, 1, {}, IndexBufferObject);
_.bind_0 = function bind_0(){
  if (this.bufferHandle == 0)
    throw toJs(new GdxRuntimeException('No buffer allocated!'));
  $glBindBuffer(gl20, $intern_12, this.bufferHandle);
  if (this.isDirty) {
    $glBufferData(gl20, $intern_12, this.buffer_0.limit, this.buffer_0, this.usage);
    this.isDirty = false;
  }
  this.isBound = true;
}
;
_.getBuffer = function getBuffer(forWriting){
  this.isDirty = this.isDirty | forWriting;
  return this.buffer_0;
}
;
_.getNumIndices = function getNumIndices(){
  return this.buffer_0.limit;
}
;
_.getNumMaxIndices = function getNumMaxIndices(){
  return this.buffer_0.capacity;
}
;
_.setIndices = function setIndices(indices, offset, count){
  this.isDirty = true;
  $clear_1(this.buffer_0);
  $put_3(this.buffer_0, indices, offset, count);
  $flip_0(this.buffer_0);
  if (this.isBound) {
    $glBufferData(gl20, $intern_12, this.buffer_0.limit, this.buffer_0, this.usage);
    this.isDirty = false;
  }
}
;
_.unbind = function unbind(){
  $glBindBuffer(gl20, $intern_12, 0);
  this.isBound = false;
}
;
_.bufferHandle = 0;
_.isBound = false;
_.isDirty = true;
_.usage = 0;
var Lcom_badlogic_gdx_graphics_glutils_IndexBufferObject_2_classLit = createForClass('com.badlogic.gdx.graphics.glutils', 'IndexBufferObject', 189);
function IndexArray(){
  this.buffer_0 = newShortBuffer(6000);
  $flip_0(this.buffer_0);
  this.bufferHandle = $glGenBuffer(gl20);
  this.usage = 35044;
}

defineClass(448, 189, {}, IndexArray);
var Lcom_badlogic_gdx_graphics_glutils_IndexArray_2_classLit = createForClass('com.badlogic.gdx.graphics.glutils', 'IndexArray', 448);
function IndexBufferObjectSubData(){
  var result;
  this.byteBuffer = newByteBuffer(12000);
  this.usage = 35048;
  this.buffer_0 = $asShortBuffer(this.byteBuffer);
  $flip_0(this.buffer_0);
  $flip_0(this.byteBuffer);
  this.bufferHandle = (result = $glGenBuffer(gl20) , $glBindBuffer(gl20, $intern_12, result) , $glBufferData(gl20, $intern_12, this.byteBuffer.capacity, null, this.usage) , $glBindBuffer(gl20, $intern_12, 0) , result);
}

defineClass(243, 1, {}, IndexBufferObjectSubData);
_.bind_0 = function bind_1(){
  if (this.bufferHandle == 0)
    throw toJs(new GdxRuntimeException('IndexBufferObject cannot be used after it has been disposed.'));
  $glBindBuffer(gl20, $intern_12, this.bufferHandle);
  if (this.isDirty) {
    $limit(this.byteBuffer, this.buffer_0.limit * 2);
    $glBufferSubData(gl20, $intern_12, this.byteBuffer);
    this.isDirty = false;
  }
  this.isBound = true;
}
;
_.getBuffer = function getBuffer_0(forWriting){
  this.isDirty = this.isDirty | forWriting;
  return this.buffer_0;
}
;
_.getNumIndices = function getNumIndices_0(){
  return this.buffer_0.limit;
}
;
_.getNumMaxIndices = function getNumMaxIndices_0(){
  return this.buffer_0.capacity;
}
;
_.setIndices = function setIndices_0(indices, offset, count){
  this.isDirty = true;
  $clear_1(this.buffer_0);
  $put_3(this.buffer_0, indices, offset, count);
  $flip_0(this.buffer_0);
  $position(this.byteBuffer, 0);
  $limit(this.byteBuffer, count << 1);
  if (this.isBound) {
    $glBufferSubData(gl20, $intern_12, this.byteBuffer);
    this.isDirty = false;
  }
}
;
_.unbind = function unbind_0(){
  $glBindBuffer(gl20, $intern_12, 0);
  this.isBound = false;
}
;
_.bufferHandle = 0;
_.isBound = false;
_.isDirty = true;
_.usage = 0;
var Lcom_badlogic_gdx_graphics_glutils_IndexBufferObjectSubData_2_classLit = createForClass('com.badlogic.gdx.graphics.glutils', 'IndexBufferObjectSubData', 243);
function generateMipMap(pixmap, textureWidth, textureHeight){
  if (!useHWMipMap) {
    generateMipMapCPU(pixmap, textureWidth, textureHeight);
    return;
  }
  $glTexImage2D(gl_0, 0, pixmap.width_0, pixmap.height_0, pixmap.buffer_0);
  gl20.gl.generateMipmap(3553);
}

function generateMipMapCPU(pixmap, textureWidth, textureHeight){
  var height, lastArg, level, tmp, width_0;
  $glTexImage2D(gl_0, 0, pixmap.width_0, pixmap.height_0, pixmap.buffer_0);
  if (!gl20 && textureWidth != textureHeight)
    throw toJs(new GdxRuntimeException('texture width and height must be square when using mipmapping.'));
  width_0 = pixmap.width_0 / 2 | 0;
  height = pixmap.height_0 / 2 | 0;
  level = 1;
  while (width_0 > 0 && height > 0) {
    tmp = new Pixmap(width_0, (lastArg = height , lastArg));
    $setBlending(tmp);
    $drawPixmap_0(tmp, pixmap, pixmap.width_0, pixmap.height_0, width_0, height);
    level > 1 && $remove_6(($clinit_Pixmap() , pixmaps), valueOf_1(pixmap.id_0));
    pixmap = tmp;
    $glTexImage2D(gl_0, level, pixmap.width_0, pixmap.height_0, pixmap.buffer_0);
    width_0 = pixmap.width_0 / 2 | 0;
    height = pixmap.height_0 / 2 | 0;
    ++level;
  }
}

var useHWMipMap = true;
function PixmapTextureData(pixmap){
  this.pixmap = pixmap;
  this.format = pixmap.format;
  this.useMipMaps = false;
  this.disposePixmap = false;
  this.managed = false;
}

defineClass(261, 1, {}, PixmapTextureData);
_.disposePixmap = false;
_.managed = false;
_.useMipMaps = false;
var Lcom_badlogic_gdx_graphics_glutils_PixmapTextureData_2_classLit = createForClass('com.badlogic.gdx.graphics.glutils', 'PixmapTextureData', 261);
function $clinit_ShaderProgram(){
  $clinit_ShaderProgram = emptyMethod;
  shaders = new ObjectMap;
  newIntBuffer();
}

function $addManagedShader(app, shaderProgram){
  var managedResources;
  managedResources = castTo($get_6(shaders, app), 50);
  !managedResources && (managedResources = new Array_0);
  $add_0(managedResources, shaderProgram);
  $put_1(shaders, app, managedResources);
}

function $bind_0(this$static){
  var gl;
  gl = gl20;
  $checkManaged(this$static);
  $glUseProgram(gl, this$static.program);
}

function $checkManaged(this$static){
  if (this$static.invalidated) {
    $compileShaders(this$static, this$static.vertexShaderSource, this$static.fragmentShaderSource);
    this$static.invalidated = false;
  }
}

function $compileShaders(this$static, vertexShader, fragmentShader){
  var gl, program0, program;
  this$static.vertexShaderHandle = $loadShader(this$static, 35633, vertexShader);
  this$static.fragmentShaderHandle = $loadShader(this$static, 35632, fragmentShader);
  if (this$static.vertexShaderHandle == -1 || this$static.fragmentShaderHandle == -1) {
    this$static.isCompiled = false;
    return;
  }
  this$static.program = $linkProgram(this$static, (gl = gl20 , program0 = (program = gl.gl.createProgram() , $add(gl.programs, program)) , program0 != 0?program0:-1));
  if (this$static.program == -1) {
    this$static.isCompiled = false;
    return;
  }
  this$static.isCompiled = true;
}

function $disableVertexAttribute(this$static, name_0){
  var gl, location_0;
  gl = gl20;
  $checkManaged(this$static);
  location_0 = $fetchAttributeLocation(this$static, name_0);
  if (location_0 == -1)
    return;
  $disableVertexAttribArray(gl.gl, location_0);
}

function $enableVertexAttribute(this$static, location_0){
  var gl;
  gl = gl20;
  $checkManaged(this$static);
  $enableVertexAttribArray(gl.gl, location_0);
}

function $fetchAttributeLocation(this$static, name_0){
  var gl, location_0;
  gl = gl20;
  if ((location_0 = $get_5(this$static.attributes, name_0, -2)) == -2) {
    location_0 = $glGetAttribLocation(gl, this$static.program, name_0);
    $put_0(this$static.attributes, name_0, location_0);
  }
  return location_0;
}

function $fetchAttributes(this$static){
  var i, location_0, name_0, numAttributes;
  $clear_1(this$static.params);
  $glGetProgramiv(gl20, this$static.program, 35721, this$static.params);
  numAttributes = this$static.params.get_3(0);
  this$static.attributeNames = initUnidimensionalArray(Ljava_lang_String_2_classLit, $intern_11, 2, numAttributes, 6, 1);
  for (i = 0; i < numAttributes; i++) {
    $clear_1(this$static.params);
    this$static.params.put_1(0, 1);
    $clear_1(this$static.type_0);
    name_0 = $glGetActiveAttrib(gl20, this$static.program, i, this$static.params, this$static.type_0);
    location_0 = $glGetAttribLocation(gl20, this$static.program, name_0);
    $put_0(this$static.attributes, name_0, location_0);
    $put_0(this$static.attributeTypes, name_0, this$static.type_0.get_3(0));
    $put_0(this$static.attributeSizes, name_0, this$static.params.get_3(0));
    this$static.attributeNames[i] = name_0;
  }
}

function $fetchUniformLocation(this$static, name_0, pedantic){
  var location_0;
  if ((location_0 = $get_5(this$static.uniforms, name_0, -2)) == -2) {
    location_0 = $glGetUniformLocation(gl20, this$static.program, name_0);
    if (location_0 == -1 && pedantic) {
      if (this$static.isCompiled)
        throw toJs(new IllegalArgumentException_0("No uniform with name '" + name_0 + "' in shader"));
      throw toJs(new IllegalStateException_0('An attempted fetch uniform from uncompiled shader \n' + $getLog(this$static)));
    }
    $put_0(this$static.uniforms, name_0, location_0);
  }
  return location_0;
}

function $fetchUniforms(this$static){
  var i, location_0, name_0, numUniforms;
  $clear_1(this$static.params);
  $glGetProgramiv(gl20, this$static.program, 35718, this$static.params);
  numUniforms = this$static.params.get_3(0);
  this$static.uniformNames = initUnidimensionalArray(Ljava_lang_String_2_classLit, $intern_11, 2, numUniforms, 6, 1);
  for (i = 0; i < numUniforms; i++) {
    $clear_1(this$static.params);
    this$static.params.put_1(0, 1);
    $clear_1(this$static.type_0);
    name_0 = $glGetActiveUniform(gl20, this$static.program, i, this$static.params, this$static.type_0);
    location_0 = $glGetUniformLocation(gl20, this$static.program, name_0);
    $put_0(this$static.uniforms, name_0, location_0);
    $put_0(this$static.uniformTypes, name_0, this$static.type_0.get_3(0));
    $put_0(this$static.uniformSizes, name_0, this$static.params.get_3(0));
    this$static.uniformNames[i] = name_0;
  }
}

function $getAttributeLocation(this$static, name_0){
  return $get_5(this$static.attributes, name_0, -1);
}

function $getLog(this$static){
  if (this$static.isCompiled) {
    this$static.log_0 = $glGetProgramInfoLog(gl20, this$static.program);
    return this$static.log_0;
  }
   else {
    return this$static.log_0;
  }
}

function $linkProgram(this$static, program){
  var gl, intbuf, linked, tmp;
  gl = gl20;
  if (program == -1)
    return -1;
  $glAttachShader(gl, program, this$static.vertexShaderHandle);
  $glAttachShader(gl, program, this$static.fragmentShaderHandle);
  $linkProgram_0(gl.gl, gl.programs[program]);
  tmp = allocateDirect(4);
  $order(tmp, ($clinit_ByteOrder() , $clinit_ByteOrder() , NATIVE_ORDER));
  intbuf = (tmp.order == 0?BIG_ENDIAN:LITTLE_ENDIAN) == (null , NATIVE_ORDER)?new DirectReadWriteIntBufferAdapter($slice(tmp)):new IntToByteBufferAdapter($slice(tmp));
  $glGetProgramiv(gl, program, 35714, intbuf);
  linked = intbuf.get_3(0);
  if (linked == 0) {
    this$static.log_0 = $glGetProgramInfoLog(gl20, program);
    return -1;
  }
  return program;
}

function $loadShader(this$static, type_0, source){
  var compiled, gl, glShader, infoLog, intbuf, shader, shader0, result;
  gl = gl20;
  intbuf = newIntBuffer();
  shader0 = (shader = $createShader(gl.gl, type_0) , $add(gl.shaders, shader));
  if (shader0 == 0)
    return -1;
  $shaderSource(gl.gl, gl.shaders[shader0], source);
  glShader = gl.shaders[shader0];
  $compileShader(gl.gl, glShader);
  result = $getShaderParameterb(gl.gl, gl.shaders[shader0], 35713);
  intbuf.put_0(result?1:0);
  $flip_0(intbuf);
  compiled = intbuf.get_3(0);
  if (compiled == 0) {
    infoLog = $getShaderInfoLog(gl.gl, gl.shaders[shader0]);
    this$static.log_0 += type_0 == 35633?'Vertex shader\n':'Fragment shader:\n';
    this$static.log_0 += '' + infoLog;
    return -1;
  }
  return shader0;
}

function $setUniformMatrix(this$static, location_0, matrix){
  var gl;
  gl = gl20;
  $checkManaged(this$static);
  $glUniformMatrix4fv(gl, location_0, matrix.val_0);
}

function $setUniformMatrix_0(this$static, name_0, matrix){
  $setUniformMatrix(this$static, $fetchUniformLocation(this$static, name_0, pedantic_0), matrix);
}

function $setUniformi(this$static, name_0, value_0){
  var gl, loc, location_0;
  gl = gl20;
  $checkManaged(this$static);
  location_0 = $fetchUniformLocation(this$static, name_0, pedantic_0);
  loc = gl.uniforms[gl.currProgram][location_0];
  $uniform1i(gl.gl, loc, value_0);
}

function $setVertexAttribute(this$static, location_0, size_0, type_0, normalize, stride, offset){
  var gl;
  gl = gl20;
  $checkManaged(this$static);
  $vertexAttribPointer(gl.gl, location_0, size_0, type_0, normalize, stride, offset);
}

function ShaderProgram(vertexShader, fragmentShader){
  $clinit_ShaderProgram();
  this.uniforms = new ObjectIntMap;
  this.uniformTypes = new ObjectIntMap;
  this.uniformSizes = new ObjectIntMap;
  this.attributes = new ObjectIntMap;
  this.attributeTypes = new ObjectIntMap;
  this.attributeSizes = new ObjectIntMap;
  this.params = newIntBuffer();
  this.type_0 = newIntBuffer();
  prependVertexCode.length > 0 && (vertexShader = prependVertexCode + vertexShader);
  prependFragmentCode.length > 0 && (fragmentShader = prependFragmentCode + fragmentShader);
  this.vertexShaderSource = vertexShader;
  this.fragmentShaderSource = fragmentShader;
  newFloatBuffer(16);
  $compileShaders(this, vertexShader, fragmentShader);
  if (this.isCompiled) {
    $fetchAttributes(this);
    $fetchUniforms(this);
    $addManagedShader(app_0, this);
  }
}

defineClass(240, 1, {}, ShaderProgram);
_.fragmentShaderHandle = 0;
_.invalidated = false;
_.isCompiled = false;
_.log_0 = '';
_.program = 0;
_.vertexShaderHandle = 0;
var pedantic_0 = true, prependFragmentCode = '', prependVertexCode = '', shaders;
var Lcom_badlogic_gdx_graphics_glutils_ShaderProgram_2_classLit = createForClass('com.badlogic.gdx.graphics.glutils', 'ShaderProgram', 240);
function $begin_1(this$static, type_0){
  if (this$static.shapeType)
    throw toJs(new IllegalStateException_0('Call end() before beginning a new shape batch.'));
  this$static.shapeType = type_0;
  if (this$static.matrixDirty) {
    $set_1(this$static.combinedMatrix, this$static.projectionMatrix.val_0);
    $clinit_Matrix4();
    matrix4_mul(this$static.combinedMatrix.val_0, this$static.transformMatrix.val_0);
    this$static.matrixDirty = false;
  }
  $begin_0(this$static.renderer, this$static.combinedMatrix, this$static.shapeType.glType);
}

function $check(this$static, preferred, other, newVertices){
  var type_0;
  if (!this$static.shapeType)
    throw toJs(new IllegalStateException_0('begin must be called first.'));
  if (this$static.shapeType != preferred && this$static.shapeType != other) {
    if (!this$static.autoShapeType) {
      if (!other)
        throw toJs(new IllegalStateException_0('Must call begin(ShapeType.' + preferred + ').'));
      else 
        throw toJs(new IllegalStateException_0('Must call begin(ShapeType.' + preferred + ') or begin(ShapeType.' + other + ').'));
    }
    $flush_0(this$static.renderer);
    this$static.shapeType = null;
    $begin_1(this$static, preferred);
  }
   else if (this$static.matrixDirty) {
    type_0 = this$static.shapeType;
    $flush_0(this$static.renderer);
    this$static.shapeType = null;
    $begin_1(this$static, type_0);
  }
   else if (this$static.renderer.maxVertices - this$static.renderer.numVertices < newVertices) {
    type_0 = this$static.shapeType;
    $flush_0(this$static.renderer);
    this$static.shapeType = null;
    $begin_1(this$static, type_0);
  }
}

function $end_0(this$static){
  $flush_0(this$static.renderer);
  this$static.shapeType = null;
}

function $line(this$static, x_0, y_0, x2, y2){
  $line_0(this$static, x_0, y_0, x2, y2, this$static.color_0, this$static.color_0);
}

function $line_0(this$static, x_0, y_0, x2, y2, c1, c2){
  if (this$static.shapeType == ($clinit_ShapeRenderer$ShapeType() , Filled)) {
    $rectLine(this$static, x_0, y_0, x2, y2, this$static.defaultRectLineWidth, c1, c2);
    return;
  }
  $check(this$static, Line, null, 2);
  $color_0(this$static.renderer, c1.r, c1.g, c1.b, c1.a);
  $vertex(this$static.renderer, x_0, y_0, 0);
  $color_0(this$static.renderer, c2.r, c2.g, c2.b, c2.a);
  $vertex(this$static.renderer, x2, y2, 0);
}

function $rect(this$static, x_0, y_0, width_0, height){
  var colorBits;
  $check(this$static, ($clinit_ShapeRenderer$ShapeType() , Line), Filled, 8);
  colorBits = $toFloatBits(this$static.color_0);
  if (this$static.shapeType == Line) {
    $color(this$static.renderer, colorBits);
    $vertex(this$static.renderer, x_0, y_0, 0);
    $color(this$static.renderer, colorBits);
    $vertex(this$static.renderer, x_0 + width_0, y_0, 0);
    $color(this$static.renderer, colorBits);
    $vertex(this$static.renderer, x_0 + width_0, y_0, 0);
    $color(this$static.renderer, colorBits);
    $vertex(this$static.renderer, x_0 + width_0, y_0 + height, 0);
    $color(this$static.renderer, colorBits);
    $vertex(this$static.renderer, x_0 + width_0, y_0 + height, 0);
    $color(this$static.renderer, colorBits);
    $vertex(this$static.renderer, x_0, y_0 + height, 0);
    $color(this$static.renderer, colorBits);
    $vertex(this$static.renderer, x_0, y_0 + height, 0);
    $color(this$static.renderer, colorBits);
    $vertex(this$static.renderer, x_0, y_0, 0);
  }
   else {
    $color(this$static.renderer, colorBits);
    $vertex(this$static.renderer, x_0, y_0, 0);
    $color(this$static.renderer, colorBits);
    $vertex(this$static.renderer, x_0 + width_0, y_0, 0);
    $color(this$static.renderer, colorBits);
    $vertex(this$static.renderer, x_0 + width_0, y_0 + height, 0);
    $color(this$static.renderer, colorBits);
    $vertex(this$static.renderer, x_0 + width_0, y_0 + height, 0);
    $color(this$static.renderer, colorBits);
    $vertex(this$static.renderer, x_0, y_0 + height, 0);
    $color(this$static.renderer, colorBits);
    $vertex(this$static.renderer, x_0, y_0, 0);
  }
}

function $rectLine(this$static, x1, y1, x2, y2, width_0, c1, c2){
  var col1Bits, col2Bits, color_0, color0, t, tx, ty;
  $check(this$static, ($clinit_ShapeRenderer$ShapeType() , Line), Filled, 8);
  col1Bits = (color0 = round_int(255 * c1.a) << 24 | round_int(255 * c1.b) << 16 | round_int(255 * c1.g) << 8 | round_int(255 * c1.r) , $clinit_Numbers() , wia[0] = color0 & $intern_7 , undefined , wfa[0]);
  col2Bits = (color_0 = round_int(255 * c2.a) << 24 | round_int(255 * c2.b) << 16 | round_int(255 * c2.g) << 8 | round_int(255 * c2.r) , wia[0] = color_0 & $intern_7 , undefined , wfa[0]);
  t = $nor($set_4(this$static.tmp, y2 - y1, x1 - x2));
  width_0 *= 0.5;
  tx = t.x_0 * width_0;
  ty = t.y_0 * width_0;
  if (this$static.shapeType == Line) {
    $color(this$static.renderer, col1Bits);
    $vertex(this$static.renderer, x1 + tx, y1 + ty, 0);
    $color(this$static.renderer, col1Bits);
    $vertex(this$static.renderer, x1 - tx, y1 - ty, 0);
    $color(this$static.renderer, col2Bits);
    $vertex(this$static.renderer, x2 + tx, y2 + ty, 0);
    $color(this$static.renderer, col2Bits);
    $vertex(this$static.renderer, x2 - tx, y2 - ty, 0);
    $color(this$static.renderer, col2Bits);
    $vertex(this$static.renderer, x2 + tx, y2 + ty, 0);
    $color(this$static.renderer, col1Bits);
    $vertex(this$static.renderer, x1 + tx, y1 + ty, 0);
    $color(this$static.renderer, col2Bits);
    $vertex(this$static.renderer, x2 - tx, y2 - ty, 0);
    $color(this$static.renderer, col1Bits);
    $vertex(this$static.renderer, x1 - tx, y1 - ty, 0);
  }
   else {
    $color(this$static.renderer, col1Bits);
    $vertex(this$static.renderer, x1 + tx, y1 + ty, 0);
    $color(this$static.renderer, col1Bits);
    $vertex(this$static.renderer, x1 - tx, y1 - ty, 0);
    $color(this$static.renderer, col2Bits);
    $vertex(this$static.renderer, x2 + tx, y2 + ty, 0);
    $color(this$static.renderer, col2Bits);
    $vertex(this$static.renderer, x2 - tx, y2 - ty, 0);
    $color(this$static.renderer, col2Bits);
    $vertex(this$static.renderer, x2 + tx, y2 + ty, 0);
    $color(this$static.renderer, col1Bits);
    $vertex(this$static.renderer, x1 - tx, y1 - ty, 0);
  }
}

function $setColor_1(this$static, color_0){
  $set_0(this$static.color_0, color_0);
}

function $setProjectionMatrix_0(this$static, matrix){
  $set_1(this$static.projectionMatrix, matrix.val_0);
  this$static.matrixDirty = true;
}

function ShapeRenderer(){
  this.projectionMatrix = new Matrix4;
  this.transformMatrix = new Matrix4;
  this.combinedMatrix = new Matrix4;
  this.tmp = new Vector2;
  this.color_0 = new Color(1, 1, 1, 1);
  this.renderer = new ImmediateModeRenderer20;
  $setToOrtho2D(this.projectionMatrix, graphics.canvas.width, graphics.canvas.height);
  this.matrixDirty = true;
}

defineClass(200, 1, {}, ShapeRenderer);
_.autoShapeType = false;
_.defaultRectLineWidth = 0.75;
_.matrixDirty = false;
var Lcom_badlogic_gdx_graphics_glutils_ShapeRenderer_2_classLit = createForClass('com.badlogic.gdx.graphics.glutils', 'ShapeRenderer', 200);
function $clinit_ShapeRenderer$ShapeType(){
  $clinit_ShapeRenderer$ShapeType = emptyMethod;
  Point = new ShapeRenderer$ShapeType('Point', 0, 0);
  Line = new ShapeRenderer$ShapeType('Line', 1, 1);
  Filled = new ShapeRenderer$ShapeType('Filled', 2, 4);
}

function ShapeRenderer$ShapeType(enum$name, enum$ordinal, glType){
  Enum.call(this, enum$name, enum$ordinal);
  this.glType = glType;
}

function values_6(){
  $clinit_ShapeRenderer$ShapeType();
  return stampJavaTypeInfo(getClassLiteralForArray(Lcom_badlogic_gdx_graphics_glutils_ShapeRenderer$ShapeType_2_classLit, 1), $intern_1, 136, 0, [Point, Line, Filled]);
}

defineClass(136, 8, {136:1, 3:1, 7:1, 8:1}, ShapeRenderer$ShapeType);
_.glType = 0;
var Filled, Line, Point;
var Lcom_badlogic_gdx_graphics_glutils_ShapeRenderer$ShapeType_2_classLit = createForEnum('com.badlogic.gdx.graphics.glutils', 'ShapeRenderer/ShapeType', 136, values_6);
function $bufferChanged(this$static){
  if (this$static.isBound) {
    $glBufferData(gl20, $intern_13, this$static.buffer_0.limit, this$static.buffer_0, this$static.usage);
    this$static.isDirty = false;
  }
}

function VertexBufferObject_0(numVertices, attributes){
  this.attributes = attributes;
  this.buffer_0 = newFloatBuffer((this.attributes.vertexSize / 4 | 0) * numVertices);
  $flip_0(this.buffer_0);
  this.bufferHandle = $glGenBuffer(gl20);
  this.usage = 35048;
}

defineClass(188, 1, {}, VertexBufferObject_0);
_.bind_1 = function bind_2(shader, locations){
  var attribute, gl, i, location_0, numAttributes;
  gl = gl20;
  $glBindBuffer(gl, $intern_13, this.bufferHandle);
  if (this.isDirty) {
    $glBufferData(gl, $intern_13, this.buffer_0.limit, this.buffer_0, this.usage);
    this.isDirty = false;
  }
  numAttributes = this.attributes.attributes.length;
  for (i = 0; i < numAttributes; i++) {
    attribute = $get_0(this.attributes, i);
    location_0 = $getAttributeLocation(shader, attribute.alias);
    if (location_0 < 0)
      continue;
    $enableVertexAttribute(shader, location_0);
    $setVertexAttribute(shader, location_0, attribute.numComponents, attribute.type_0, attribute.normalized, this.attributes.vertexSize, attribute.offset);
  }
  this.isBound = true;
}
;
_.getAttributes = function getAttributes(){
  return this.attributes;
}
;
_.getNumVertices = function getNumVertices(){
  return this.buffer_0.limit / (this.attributes.vertexSize / 4 | 0) | 0;
}
;
_.setVertices = function setVertices(vertices, offset, count){
  this.isDirty = true;
  copy_0(vertices, this.buffer_0, count, offset);
  $position(this.buffer_0, 0);
  $limit(this.buffer_0, count);
  $bufferChanged(this);
}
;
_.unbind_0 = function unbind_1(shader, locations){
  var gl, i, numAttributes;
  gl = gl20;
  numAttributes = this.attributes.attributes.length;
  for (i = 0; i < numAttributes; i++) {
    $disableVertexAttribute(shader, $get_0(this.attributes, i).alias);
  }
  $bindBuffer(gl.gl, $intern_13, gl.buffers[0]);
  this.isBound = false;
}
;
_.bufferHandle = 0;
_.isBound = false;
_.isDirty = false;
_.usage = 0;
var Lcom_badlogic_gdx_graphics_glutils_VertexBufferObject_2_classLit = createForClass('com.badlogic.gdx.graphics.glutils', 'VertexBufferObject', 188);
function VertexArray_0(attributes){
  VertexBufferObject_0.call(this, 4000, attributes);
}

defineClass(447, 188, {}, VertexArray_0);
var Lcom_badlogic_gdx_graphics_glutils_VertexArray_2_classLit = createForClass('com.badlogic.gdx.graphics.glutils', 'VertexArray', 447);
function $bufferChanged_0(this$static){
  if (this$static.isBound) {
    $glBufferSubData(gl20, $intern_13, this$static.byteBuffer);
    this$static.isDirty = false;
  }
}

function VertexBufferObjectSubData_0(attributes){
  var result;
  this.attributes = attributes;
  this.byteBuffer = newByteBuffer(this.attributes.vertexSize * 4000);
  this.isDirect = true;
  this.usage = 35048;
  this.buffer_0 = new DirectReadWriteFloatBufferAdapter($slice(this.byteBuffer));
  this.bufferHandle = (result = $glGenBuffer(gl20) , $glBindBuffer(gl20, $intern_13, result) , $glBufferData(gl20, $intern_13, this.byteBuffer.capacity, null, this.usage) , $glBindBuffer(gl20, $intern_13, 0) , result);
  $flip_0(this.buffer_0);
  $flip_0(this.byteBuffer);
}

defineClass(445, 1, {}, VertexBufferObjectSubData_0);
_.bind_1 = function bind_3(shader, locations){
  var attribute, gl, i, location_0, numAttributes;
  gl = gl20;
  $glBindBuffer(gl, $intern_13, this.bufferHandle);
  if (this.isDirty) {
    $limit(this.byteBuffer, this.buffer_0.limit * 4);
    $glBufferData(gl, $intern_13, this.byteBuffer.limit, this.byteBuffer, this.usage);
    this.isDirty = false;
  }
  numAttributes = this.attributes.attributes.length;
  for (i = 0; i < numAttributes; i++) {
    attribute = $get_0(this.attributes, i);
    location_0 = $getAttributeLocation(shader, attribute.alias);
    if (location_0 < 0)
      continue;
    $enableVertexAttribute(shader, location_0);
    $setVertexAttribute(shader, location_0, attribute.numComponents, attribute.type_0, attribute.normalized, this.attributes.vertexSize, attribute.offset);
  }
  this.isBound = true;
}
;
_.getAttributes = function getAttributes_0(){
  return this.attributes;
}
;
_.getNumVertices = function getNumVertices_0(){
  return this.buffer_0.limit * 4 / this.attributes.vertexSize | 0;
}
;
_.setVertices = function setVertices_0(vertices, offset, count){
  this.isDirty = true;
  if (this.isDirect) {
    copy_0(vertices, this.byteBuffer, count, offset);
    $position(this.buffer_0, 0);
    $limit(this.buffer_0, count);
  }
   else {
    $clear_1(this.buffer_0);
    $put_2(this.buffer_0, vertices, offset, count);
    $flip_0(this.buffer_0);
    $position(this.byteBuffer, 0);
    $limit(this.byteBuffer, this.buffer_0.limit << 2);
  }
  $bufferChanged_0(this);
}
;
_.unbind_0 = function unbind_2(shader, locations){
  var gl, i, numAttributes;
  gl = gl20;
  numAttributes = this.attributes.attributes.length;
  for (i = 0; i < numAttributes; i++) {
    $disableVertexAttribute(shader, $get_0(this.attributes, i).alias);
  }
  $bindBuffer(gl.gl, $intern_13, gl.buffers[0]);
  this.isBound = false;
}
;
_.bufferHandle = 0;
_.isBound = false;
_.isDirect = false;
_.isDirty = false;
_.usage = 0;
var Lcom_badlogic_gdx_graphics_glutils_VertexBufferObjectSubData_2_classLit = createForClass('com.badlogic.gdx.graphics.glutils', 'VertexBufferObjectSubData', 445);
function $clinit_VertexBufferObjectWithVAO(){
  $clinit_VertexBufferObjectWithVAO = emptyMethod;
  tmpHandle = newIntBuffer();
}

function $bindAttributes(this$static, shader){
  var attribute, i, location_0, numAttributes, stillValid;
  stillValid = this$static.cachedLocations.size_0 != 0;
  numAttributes = this$static.attributes.attributes.length;
  if (stillValid) {
    for (i = 0; stillValid && i < numAttributes; i++) {
      attribute = $get_0(this$static.attributes, i);
      location_0 = $getAttributeLocation(shader, attribute.alias);
      stillValid = location_0 == $get_2(this$static.cachedLocations, i);
    }
  }
  if (!stillValid) {
    $glBindBuffer(gl_0, $intern_13, this$static.bufferHandle);
    $unbindAttributes(this$static, shader);
    this$static.cachedLocations.size_0 = 0;
    for (i = 0; i < numAttributes; i++) {
      attribute = $get_0(this$static.attributes, i);
      $add_1(this$static.cachedLocations, $getAttributeLocation(shader, attribute.alias));
      location_0 = $get_2(this$static.cachedLocations, i);
      if (location_0 < 0) {
        continue;
      }
      $enableVertexAttribute(shader, location_0);
      $setVertexAttribute(shader, location_0, attribute.numComponents, attribute.type_0, attribute.normalized, this$static.attributes.vertexSize, attribute.offset);
    }
  }
}

function $bindData(this$static){
  if (this$static.isDirty) {
    null.$_nullMethod();
    $limit(this$static.buffer_0, this$static.buffer_0.limit);
    null.$_nullMethod();
    this$static.isDirty = false;
  }
}

function $bufferChanged_1(this$static){
  if (this$static.isBound) {
    $glBufferData(gl20, $intern_13, this$static.buffer_0.limit, this$static.buffer_0, this$static.usage);
    this$static.isDirty = false;
  }
}

function $unbindAttributes(this$static, shaderProgram){
  var i, location_0, numAttributes, gl;
  if (this$static.cachedLocations.size_0 == 0) {
    return;
  }
  numAttributes = this$static.attributes.attributes.length;
  for (i = 0; i < numAttributes; i++) {
    location_0 = $get_2(this$static.cachedLocations, i);
    if (location_0 < 0) {
      continue;
    }
    gl = gl20;
    $checkManaged(shaderProgram);
    $disableVertexAttribArray(gl.gl, location_0);
  }
}

function VertexBufferObjectWithVAO_0(attributes){
  $clinit_VertexBufferObjectWithVAO();
  this.cachedLocations = new IntArray;
  this.attributes = attributes;
  this.buffer_0 = newFloatBuffer((this.attributes.vertexSize / 4 | 0) * 4000);
  $flip_0(this.buffer_0);
  this.bufferHandle = $glGenBuffer(gl20);
  this.usage = 35048;
  $clear_1(tmpHandle);
  null.$_nullMethod();
  tmpHandle.get_2();
}

defineClass(446, 1, {}, VertexBufferObjectWithVAO_0);
_.bind_1 = function bind_4(shader, locations){
  null.$_nullMethod();
  $bindAttributes(this, shader);
  $bindData(this);
  this.isBound = true;
}
;
_.getAttributes = function getAttributes_1(){
  return this.attributes;
}
;
_.getNumVertices = function getNumVertices_1(){
  return this.buffer_0.limit * 4 / this.attributes.vertexSize | 0;
}
;
_.setVertices = function setVertices_1(vertices, offset, count){
  this.isDirty = true;
  copy_0(vertices, this.buffer_0, count, offset);
  $position(this.buffer_0, 0);
  $limit(this.buffer_0, count);
  $bufferChanged_1(this);
}
;
_.unbind_0 = function unbind_3(shader, locations){
  null.$_nullMethod();
  this.isBound = false;
}
;
_.bufferHandle = 0;
_.isBound = false;
_.isDirty = false;
_.usage = 0;
var tmpHandle;
var Lcom_badlogic_gdx_graphics_glutils_VertexBufferObjectWithVAO_2_classLit = createForClass('com.badlogic.gdx.graphics.glutils', 'VertexBufferObjectWithVAO', 446);
function $clinit_Frustum(){
  $clinit_Frustum = emptyMethod;
  var j, v, v$array, v$index, v$max;
  clipSpacePlanePoints = stampJavaTypeInfo(getClassLiteralForArray(Lcom_badlogic_gdx_math_Vector3_2_classLit, 1), $intern_9, 18, 0, [new Vector3_0(-1, -1, -1), new Vector3_0(1, -1, -1), new Vector3_0(1, 1, -1), new Vector3_0(-1, 1, -1), new Vector3_0(-1, -1, 1), new Vector3_0(1, -1, 1), new Vector3_0(1, 1, 1), new Vector3_0(-1, 1, 1)]);
  clipSpacePlanePointsArray = initUnidimensionalArray(F_classLit, $intern_10, 5, 24, 15, 1);
  j = 0;
  for (v$array = clipSpacePlanePoints , v$index = 0 , v$max = v$array.length; v$index < v$max; ++v$index) {
    v = v$array[v$index];
    clipSpacePlanePointsArray[j++] = v.x_0;
    clipSpacePlanePointsArray[j++] = v.y_0;
    clipSpacePlanePointsArray[j++] = v.z_0;
  }
  $clinit_Vector3();
}

function $update_3(this$static, inverseProjectionView){
  var i, j, v;
  arraycopy(clipSpacePlanePointsArray, 0, this$static.planePointsArray, 0, clipSpacePlanePointsArray.length);
  prj(inverseProjectionView.val_0, this$static.planePointsArray, 0);
  for (i = 0 , j = 0; i < 8; i++) {
    v = this$static.planePoints[i];
    v.x_0 = this$static.planePointsArray[j++];
    v.y_0 = this$static.planePointsArray[j++];
    v.z_0 = this$static.planePointsArray[j++];
  }
  $set_2(this$static.planes[0], this$static.planePoints[1], this$static.planePoints[0], this$static.planePoints[2]);
  $set_2(this$static.planes[1], this$static.planePoints[4], this$static.planePoints[5], this$static.planePoints[7]);
  $set_2(this$static.planes[2], this$static.planePoints[0], this$static.planePoints[4], this$static.planePoints[3]);
  $set_2(this$static.planes[3], this$static.planePoints[5], this$static.planePoints[1], this$static.planePoints[6]);
  $set_2(this$static.planes[4], this$static.planePoints[2], this$static.planePoints[3], this$static.planePoints[6]);
  $set_2(this$static.planes[5], this$static.planePoints[4], this$static.planePoints[0], this$static.planePoints[1]);
}

function Frustum(){
  $clinit_Frustum();
  var i;
  this.planes = initUnidimensionalArray(Lcom_badlogic_gdx_math_Plane_2_classLit, $intern_9, 185, 6, 0, 1);
  this.planePoints = stampJavaTypeInfo(getClassLiteralForArray(Lcom_badlogic_gdx_math_Vector3_2_classLit, 1), $intern_9, 18, 0, [new Vector3, new Vector3, new Vector3, new Vector3, new Vector3, new Vector3, new Vector3, new Vector3]);
  this.planePointsArray = initUnidimensionalArray(F_classLit, $intern_10, 5, 24, 15, 1);
  for (i = 0; i < 6; i++) {
    this.planes[i] = new Plane(new Vector3);
  }
}

defineClass(438, 1, {}, Frustum);
var clipSpacePlanePoints, clipSpacePlanePointsArray;
var Lcom_badlogic_gdx_math_Frustum_2_classLit = createForClass('com.badlogic.gdx.math', 'Frustum', 438);
function $clinit_MathUtils(){
  $clinit_MathUtils = emptyMethod;
  new RandomXS128;
}

function cosDeg(degrees){
  $clinit_MathUtils();
  return ($clinit_MathUtils$Sin() , table)[round_int((degrees + 90) * $intern_14) & 16383];
}

function nextPowerOfTwo(value_0){
  $clinit_MathUtils();
  if (value_0 == 0)
    return 1;
  --value_0;
  value_0 |= value_0 >> 1;
  value_0 |= value_0 >> 2;
  value_0 |= value_0 >> 4;
  value_0 |= value_0 >> 8;
  value_0 |= value_0 >> 16;
  return value_0 + 1;
}

function sinDeg(degrees){
  $clinit_MathUtils();
  return ($clinit_MathUtils$Sin() , table)[round_int(degrees * $intern_14) & 16383];
}

function $clinit_MathUtils$Sin(){
  $clinit_MathUtils$Sin = emptyMethod;
  var i;
  table = initUnidimensionalArray(F_classLit, $intern_10, 5, $intern_15, 15, 1);
  for (i = 0; i < $intern_15; i++)
    table[i] = $wnd.Math.sin((i + 0.5) / $intern_15 * 6.2831854820251465);
  table[0] = 0;
  table[4096] = 1;
  table[8192] = 0;
  table[12288] = -1;
}

var table;
function $clinit_Matrix4(){
  $clinit_Matrix4 = emptyMethod;
  tmp_0 = initUnidimensionalArray(F_classLit, $intern_10, 5, 16, 15, 1);
  new Quaternion;
  new Quaternion;
  l_vez = new Vector3;
  l_vex = new Vector3;
  l_vey = new Vector3;
  $clinit_Vector3();
  new Matrix4;
}

function $idt(this$static){
  this$static.val_0[0] = 1;
  this$static.val_0[4] = 0;
  this$static.val_0[8] = 0;
  this$static.val_0[12] = 0;
  this$static.val_0[1] = 0;
  this$static.val_0[5] = 1;
  this$static.val_0[9] = 0;
  this$static.val_0[13] = 0;
  this$static.val_0[2] = 0;
  this$static.val_0[6] = 0;
  this$static.val_0[10] = 1;
  this$static.val_0[14] = 0;
  this$static.val_0[3] = 0;
  this$static.val_0[7] = 0;
  this$static.val_0[11] = 0;
  this$static.val_0[15] = 1;
  return this$static;
}

function $mul(this$static, matrix){
  tmp_0[0] = this$static.val_0[0] * matrix.val_0[0] + this$static.val_0[4] * matrix.val_0[1] + this$static.val_0[8] * matrix.val_0[2] + this$static.val_0[12] * matrix.val_0[3];
  tmp_0[4] = this$static.val_0[0] * matrix.val_0[4] + this$static.val_0[4] * matrix.val_0[5] + this$static.val_0[8] * matrix.val_0[6] + this$static.val_0[12] * matrix.val_0[7];
  tmp_0[8] = this$static.val_0[0] * matrix.val_0[8] + this$static.val_0[4] * matrix.val_0[9] + this$static.val_0[8] * matrix.val_0[10] + this$static.val_0[12] * matrix.val_0[11];
  tmp_0[12] = this$static.val_0[0] * matrix.val_0[12] + this$static.val_0[4] * matrix.val_0[13] + this$static.val_0[8] * matrix.val_0[14] + this$static.val_0[12] * matrix.val_0[15];
  tmp_0[1] = this$static.val_0[1] * matrix.val_0[0] + this$static.val_0[5] * matrix.val_0[1] + this$static.val_0[9] * matrix.val_0[2] + this$static.val_0[13] * matrix.val_0[3];
  tmp_0[5] = this$static.val_0[1] * matrix.val_0[4] + this$static.val_0[5] * matrix.val_0[5] + this$static.val_0[9] * matrix.val_0[6] + this$static.val_0[13] * matrix.val_0[7];
  tmp_0[9] = this$static.val_0[1] * matrix.val_0[8] + this$static.val_0[5] * matrix.val_0[9] + this$static.val_0[9] * matrix.val_0[10] + this$static.val_0[13] * matrix.val_0[11];
  tmp_0[13] = this$static.val_0[1] * matrix.val_0[12] + this$static.val_0[5] * matrix.val_0[13] + this$static.val_0[9] * matrix.val_0[14] + this$static.val_0[13] * matrix.val_0[15];
  tmp_0[2] = this$static.val_0[2] * matrix.val_0[0] + this$static.val_0[6] * matrix.val_0[1] + this$static.val_0[10] * matrix.val_0[2] + this$static.val_0[14] * matrix.val_0[3];
  tmp_0[6] = this$static.val_0[2] * matrix.val_0[4] + this$static.val_0[6] * matrix.val_0[5] + this$static.val_0[10] * matrix.val_0[6] + this$static.val_0[14] * matrix.val_0[7];
  tmp_0[10] = this$static.val_0[2] * matrix.val_0[8] + this$static.val_0[6] * matrix.val_0[9] + this$static.val_0[10] * matrix.val_0[10] + this$static.val_0[14] * matrix.val_0[11];
  tmp_0[14] = this$static.val_0[2] * matrix.val_0[12] + this$static.val_0[6] * matrix.val_0[13] + this$static.val_0[10] * matrix.val_0[14] + this$static.val_0[14] * matrix.val_0[15];
  tmp_0[3] = this$static.val_0[3] * matrix.val_0[0] + this$static.val_0[7] * matrix.val_0[1] + this$static.val_0[11] * matrix.val_0[2] + this$static.val_0[15] * matrix.val_0[3];
  tmp_0[7] = this$static.val_0[3] * matrix.val_0[4] + this$static.val_0[7] * matrix.val_0[5] + this$static.val_0[11] * matrix.val_0[6] + this$static.val_0[15] * matrix.val_0[7];
  tmp_0[11] = this$static.val_0[3] * matrix.val_0[8] + this$static.val_0[7] * matrix.val_0[9] + this$static.val_0[11] * matrix.val_0[10] + this$static.val_0[15] * matrix.val_0[11];
  tmp_0[15] = this$static.val_0[3] * matrix.val_0[12] + this$static.val_0[7] * matrix.val_0[13] + this$static.val_0[11] * matrix.val_0[14] + this$static.val_0[15] * matrix.val_0[15];
  return $set_1(this$static, tmp_0);
}

function $scale(this$static, scaleX, scaleY){
  tmp_0[0] = scaleX;
  tmp_0[4] = 0;
  tmp_0[8] = 0;
  tmp_0[12] = 0;
  tmp_0[1] = 0;
  tmp_0[5] = scaleY;
  tmp_0[9] = 0;
  tmp_0[13] = 0;
  tmp_0[2] = 0;
  tmp_0[6] = 0;
  tmp_0[10] = 1;
  tmp_0[14] = 0;
  tmp_0[3] = 0;
  tmp_0[7] = 0;
  tmp_0[11] = 0;
  tmp_0[15] = 1;
  matrix4_mul(this$static.val_0, tmp_0);
  return this$static;
}

function $set_1(this$static, values){
  this$static.val_0[0] = values[0];
  this$static.val_0[1] = values[1];
  this$static.val_0[2] = values[2];
  this$static.val_0[3] = values[3];
  this$static.val_0[4] = values[4];
  this$static.val_0[5] = values[5];
  this$static.val_0[6] = values[6];
  this$static.val_0[7] = values[7];
  this$static.val_0[8] = values[8];
  this$static.val_0[9] = values[9];
  this$static.val_0[10] = values[10];
  this$static.val_0[11] = values[11];
  this$static.val_0[12] = values[12];
  this$static.val_0[13] = values[13];
  this$static.val_0[14] = values[14];
  this$static.val_0[15] = values[15];
  return this$static;
}

function $setToLookAt(this$static, direction, up){
  $nor_0($set_6(l_vez, direction));
  $nor_0($set_6(l_vex, direction));
  $nor_0($crs_0(l_vex, up));
  $nor_0($crs_0($set_6(l_vey, l_vex), l_vez));
  $idt(this$static);
  this$static.val_0[0] = l_vex.x_0;
  this$static.val_0[4] = l_vex.y_0;
  this$static.val_0[8] = l_vex.z_0;
  this$static.val_0[1] = l_vey.x_0;
  this$static.val_0[5] = l_vey.y_0;
  this$static.val_0[9] = l_vey.z_0;
  this$static.val_0[2] = -l_vez.x_0;
  this$static.val_0[6] = -l_vez.y_0;
  this$static.val_0[10] = -l_vez.z_0;
  return this$static;
}

function $setToOrtho_0(this$static, left, right, bottom, top_0, near, far){
  var tx, ty, tz, x_orth, y_orth, z_orth;
  $idt(this$static);
  x_orth = 2 / (right - left);
  y_orth = 2 / (top_0 - bottom);
  z_orth = -2 / (far - near);
  tx = -(right + left) / (right - left);
  ty = -(top_0 + bottom) / (top_0 - bottom);
  tz = -(far + near) / (far - near);
  this$static.val_0[0] = x_orth;
  this$static.val_0[1] = 0;
  this$static.val_0[2] = 0;
  this$static.val_0[3] = 0;
  this$static.val_0[4] = 0;
  this$static.val_0[5] = y_orth;
  this$static.val_0[6] = 0;
  this$static.val_0[7] = 0;
  this$static.val_0[8] = 0;
  this$static.val_0[9] = 0;
  this$static.val_0[10] = z_orth;
  this$static.val_0[11] = 0;
  this$static.val_0[12] = tx;
  this$static.val_0[13] = ty;
  this$static.val_0[14] = tz;
  this$static.val_0[15] = 1;
  return this$static;
}

function $setToOrtho2D(this$static, width_0, height){
  $setToOrtho_0(this$static, 0, width_0, 0, height, 0, 1);
  return this$static;
}

function $translate(this$static, x_0, y_0, z_0){
  tmp_0[0] = 1;
  tmp_0[4] = 0;
  tmp_0[8] = 0;
  tmp_0[12] = x_0;
  tmp_0[1] = 0;
  tmp_0[5] = 1;
  tmp_0[9] = 0;
  tmp_0[13] = y_0;
  tmp_0[2] = 0;
  tmp_0[6] = 0;
  tmp_0[10] = 1;
  tmp_0[14] = z_0;
  tmp_0[3] = 0;
  tmp_0[7] = 0;
  tmp_0[11] = 0;
  tmp_0[15] = 1;
  matrix4_mul(this$static.val_0, tmp_0);
  return this$static;
}

function Matrix4(){
  $clinit_Matrix4();
  this.val_0 = initUnidimensionalArray(F_classLit, $intern_10, 5, 16, 15, 1);
  this.val_0[0] = 1;
  this.val_0[5] = 1;
  this.val_0[10] = 1;
  this.val_0[15] = 1;
}

function matrix4_inv(val){
  $clinit_Matrix4();
  var inv_det, l_det, tmp;
  tmp = initUnidimensionalArray(F_classLit, $intern_10, 5, 16, 15, 1);
  l_det = val[3] * val[6] * val[9] * val[12] - val[2] * val[7] * val[9] * val[12] - val[3] * val[5] * val[10] * val[12] + val[1] * val[7] * val[10] * val[12] + val[2] * val[5] * val[11] * val[12] - val[1] * val[6] * val[11] * val[12] - val[3] * val[6] * val[8] * val[13] + val[2] * val[7] * val[8] * val[13] + val[3] * val[4] * val[10] * val[13] - val[0] * val[7] * val[10] * val[13] - val[2] * val[4] * val[11] * val[13] + val[0] * val[6] * val[11] * val[13] + val[3] * val[5] * val[8] * val[14] - val[1] * val[7] * val[8] * val[14] - val[3] * val[4] * val[9] * val[14] + val[0] * val[7] * val[9] * val[14] + val[1] * val[4] * val[11] * val[14] - val[0] * val[5] * val[11] * val[14] - val[2] * val[5] * val[8] * val[15] + val[1] * val[6] * val[8] * val[15] + val[2] * val[4] * val[9] * val[15] - val[0] * val[6] * val[9] * val[15] - val[1] * val[4] * val[10] * val[15] + val[0] * val[5] * val[10] * val[15];
  if (l_det == 0)
    return false;
  tmp[0] = val[9] * val[14] * val[7] - val[13] * val[10] * val[7] + val[13] * val[6] * val[11] - val[5] * val[14] * val[11] - val[9] * val[6] * val[15] + val[5] * val[10] * val[15];
  tmp[4] = val[12] * val[10] * val[7] - val[8] * val[14] * val[7] - val[12] * val[6] * val[11] + val[4] * val[14] * val[11] + val[8] * val[6] * val[15] - val[4] * val[10] * val[15];
  tmp[8] = val[8] * val[13] * val[7] - val[12] * val[9] * val[7] + val[12] * val[5] * val[11] - val[4] * val[13] * val[11] - val[8] * val[5] * val[15] + val[4] * val[9] * val[15];
  tmp[12] = val[12] * val[9] * val[6] - val[8] * val[13] * val[6] - val[12] * val[5] * val[10] + val[4] * val[13] * val[10] + val[8] * val[5] * val[14] - val[4] * val[9] * val[14];
  tmp[1] = val[13] * val[10] * val[3] - val[9] * val[14] * val[3] - val[13] * val[2] * val[11] + val[1] * val[14] * val[11] + val[9] * val[2] * val[15] - val[1] * val[10] * val[15];
  tmp[5] = val[8] * val[14] * val[3] - val[12] * val[10] * val[3] + val[12] * val[2] * val[11] - val[0] * val[14] * val[11] - val[8] * val[2] * val[15] + val[0] * val[10] * val[15];
  tmp[9] = val[12] * val[9] * val[3] - val[8] * val[13] * val[3] - val[12] * val[1] * val[11] + val[0] * val[13] * val[11] + val[8] * val[1] * val[15] - val[0] * val[9] * val[15];
  tmp[13] = val[8] * val[13] * val[2] - val[12] * val[9] * val[2] + val[12] * val[1] * val[10] - val[0] * val[13] * val[10] - val[8] * val[1] * val[14] + val[0] * val[9] * val[14];
  tmp[2] = val[5] * val[14] * val[3] - val[13] * val[6] * val[3] + val[13] * val[2] * val[7] - val[1] * val[14] * val[7] - val[5] * val[2] * val[15] + val[1] * val[6] * val[15];
  tmp[6] = val[12] * val[6] * val[3] - val[4] * val[14] * val[3] - val[12] * val[2] * val[7] + val[0] * val[14] * val[7] + val[4] * val[2] * val[15] - val[0] * val[6] * val[15];
  tmp[10] = val[4] * val[13] * val[3] - val[12] * val[5] * val[3] + val[12] * val[1] * val[7] - val[0] * val[13] * val[7] - val[4] * val[1] * val[15] + val[0] * val[5] * val[15];
  tmp[14] = val[12] * val[5] * val[2] - val[4] * val[13] * val[2] - val[12] * val[1] * val[6] + val[0] * val[13] * val[6] + val[4] * val[1] * val[14] - val[0] * val[5] * val[14];
  tmp[3] = val[9] * val[6] * val[3] - val[5] * val[10] * val[3] - val[9] * val[2] * val[7] + val[1] * val[10] * val[7] + val[5] * val[2] * val[11] - val[1] * val[6] * val[11];
  tmp[7] = val[4] * val[10] * val[3] - val[8] * val[6] * val[3] + val[8] * val[2] * val[7] - val[0] * val[10] * val[7] - val[4] * val[2] * val[11] + val[0] * val[6] * val[11];
  tmp[11] = val[8] * val[5] * val[3] - val[4] * val[9] * val[3] - val[8] * val[1] * val[7] + val[0] * val[9] * val[7] + val[4] * val[1] * val[11] - val[0] * val[5] * val[11];
  tmp[15] = val[4] * val[9] * val[2] - val[8] * val[5] * val[2] + val[8] * val[1] * val[6] - val[0] * val[9] * val[6] - val[4] * val[1] * val[10] + val[0] * val[5] * val[10];
  inv_det = 1 / l_det;
  val[0] = tmp[0] * inv_det;
  val[4] = tmp[4] * inv_det;
  val[8] = tmp[8] * inv_det;
  val[12] = tmp[12] * inv_det;
  val[1] = tmp[1] * inv_det;
  val[5] = tmp[5] * inv_det;
  val[9] = tmp[9] * inv_det;
  val[13] = tmp[13] * inv_det;
  val[2] = tmp[2] * inv_det;
  val[6] = tmp[6] * inv_det;
  val[10] = tmp[10] * inv_det;
  val[14] = tmp[14] * inv_det;
  val[3] = tmp[3] * inv_det;
  val[7] = tmp[7] * inv_det;
  val[11] = tmp[11] * inv_det;
  val[15] = tmp[15] * inv_det;
  return true;
}

function matrix4_mul(mata, matb){
  $clinit_Matrix4();
  var tmp;
  tmp = initUnidimensionalArray(F_classLit, $intern_10, 5, 16, 15, 1);
  tmp[0] = mata[0] * matb[0] + mata[4] * matb[1] + mata[8] * matb[2] + mata[12] * matb[3];
  tmp[4] = mata[0] * matb[4] + mata[4] * matb[5] + mata[8] * matb[6] + mata[12] * matb[7];
  tmp[8] = mata[0] * matb[8] + mata[4] * matb[9] + mata[8] * matb[10] + mata[12] * matb[11];
  tmp[12] = mata[0] * matb[12] + mata[4] * matb[13] + mata[8] * matb[14] + mata[12] * matb[15];
  tmp[1] = mata[1] * matb[0] + mata[5] * matb[1] + mata[9] * matb[2] + mata[13] * matb[3];
  tmp[5] = mata[1] * matb[4] + mata[5] * matb[5] + mata[9] * matb[6] + mata[13] * matb[7];
  tmp[9] = mata[1] * matb[8] + mata[5] * matb[9] + mata[9] * matb[10] + mata[13] * matb[11];
  tmp[13] = mata[1] * matb[12] + mata[5] * matb[13] + mata[9] * matb[14] + mata[13] * matb[15];
  tmp[2] = mata[2] * matb[0] + mata[6] * matb[1] + mata[10] * matb[2] + mata[14] * matb[3];
  tmp[6] = mata[2] * matb[4] + mata[6] * matb[5] + mata[10] * matb[6] + mata[14] * matb[7];
  tmp[10] = mata[2] * matb[8] + mata[6] * matb[9] + mata[10] * matb[10] + mata[14] * matb[11];
  tmp[14] = mata[2] * matb[12] + mata[6] * matb[13] + mata[10] * matb[14] + mata[14] * matb[15];
  tmp[3] = mata[3] * matb[0] + mata[7] * matb[1] + mata[11] * matb[2] + mata[15] * matb[3];
  tmp[7] = mata[3] * matb[4] + mata[7] * matb[5] + mata[11] * matb[6] + mata[15] * matb[7];
  tmp[11] = mata[3] * matb[8] + mata[7] * matb[9] + mata[11] * matb[10] + mata[15] * matb[11];
  tmp[15] = mata[3] * matb[12] + mata[7] * matb[13] + mata[11] * matb[14] + mata[15] * matb[15];
  arraycopy(tmp, 0, mata, 0, 16);
}

function prj(mat, vecs, offset){
  var inv_w, x_0, y_0, z_0;
  $clinit_Matrix4();
  var i;
  for (i = 0; i < 8; i++) {
    inv_w = 1 / (vecs[offset] * mat[3] + vecs[offset + 1] * mat[7] + vecs[offset + 2] * mat[11] + mat[15]);
    x_0 = (vecs[offset] * mat[0] + vecs[offset + 1] * mat[4] + vecs[offset + 2] * mat[8] + mat[12]) * inv_w;
    y_0 = (vecs[offset] * mat[1] + vecs[offset + 1] * mat[5] + vecs[offset + 2] * mat[9] + mat[13]) * inv_w;
    z_0 = (vecs[offset] * mat[2] + vecs[offset + 1] * mat[6] + vecs[offset + 2] * mat[10] + mat[14]) * inv_w;
    vecs[offset] = x_0;
    vecs[offset + 1] = y_0;
    vecs[offset + 2] = z_0;
    offset += 3;
  }
}

defineClass(47, 1, $intern_2, Matrix4);
_.toString_0 = function toString_8(){
  return '[' + this.val_0[0] + '|' + this.val_0[4] + '|' + this.val_0[8] + '|' + this.val_0[12] + ']\n' + '[' + this.val_0[1] + '|' + this.val_0[5] + '|' + this.val_0[9] + '|' + this.val_0[13] + ']\n' + '[' + this.val_0[2] + '|' + this.val_0[6] + '|' + this.val_0[10] + '|' + this.val_0[14] + ']\n' + '[' + this.val_0[3] + '|' + this.val_0[7] + '|' + this.val_0[11] + '|' + this.val_0[15] + ']\n';
}
;
var l_vex, l_vey, l_vez, tmp_0;
var Lcom_badlogic_gdx_math_Matrix4_2_classLit = createForClass('com.badlogic.gdx.math', 'Matrix4', 47);
function $set_2(this$static, point1, point2, point3){
  $nor_0($crs($sub_0($set_6(this$static.normal, point1), point2), point2.x_0 - point3.x_0, point2.y_0 - point3.y_0, point2.z_0 - point3.z_0));
  this$static.d = -$dot(point1, this$static.normal);
}

function Plane(normal){
  this.normal = new Vector3;
  $nor_0($set_6(this.normal, normal));
  this.d = 0;
}

defineClass(185, 1, {185:1, 3:1}, Plane);
_.toString_0 = function toString_9(){
  return $toString(this.normal) + ', ' + this.d;
}
;
_.d = 0;
var Lcom_badlogic_gdx_math_Plane_2_classLit = createForClass('com.badlogic.gdx.math', 'Plane', 185);
function $clinit_Quaternion(){
  $clinit_Quaternion = emptyMethod;
  new Quaternion_0;
  new Quaternion_0;
}

function $set_3(this$static, x_0, y_0, z_0, w){
  this$static.x_0 = x_0;
  this$static.y_0 = y_0;
  this$static.z_0 = z_0;
  this$static.w = w;
  return this$static;
}

function Quaternion(){
  $clinit_Quaternion();
  $set_3(this, 0, 0, 0, 1);
}

function Quaternion_0(){
  $set_3(this, 0, 0, 0, 0);
}

defineClass(97, 1, {97:1, 3:1}, Quaternion, Quaternion_0);
_.equals_0 = function equals_4(obj){
  var other;
  if (this === obj) {
    return true;
  }
  if (obj == null) {
    return false;
  }
  if (!instanceOf(obj, 97)) {
    return false;
  }
  other = castTo(obj, 97);
  return floatToRawIntBits(this.w) == floatToRawIntBits(other.w) && floatToRawIntBits(this.x_0) == floatToRawIntBits(other.x_0) && floatToRawIntBits(this.y_0) == floatToRawIntBits(other.y_0) && floatToRawIntBits(this.z_0) == floatToRawIntBits(other.z_0);
}
;
_.hashCode_0 = function hashCode_5(){
  var result;
  result = 1;
  result = 31 * result + floatToRawIntBits(this.w);
  result = 31 * result + floatToRawIntBits(this.x_0);
  result = 31 * result + floatToRawIntBits(this.y_0);
  result = 31 * result + floatToRawIntBits(this.z_0);
  return result;
}
;
_.toString_0 = function toString_10(){
  return '[' + this.x_0 + '|' + this.y_0 + '|' + this.z_0 + '|' + this.w + ']';
}
;
_.w = 0;
_.x_0 = 0;
_.y_0 = 0;
_.z_0 = 0;
var Lcom_badlogic_gdx_math_Quaternion_2_classLit = createForClass('com.badlogic.gdx.math', 'Quaternion', 97);
function $clinit_Random(){
  $clinit_Random = emptyMethod;
  var i, i0, twoToTheXMinus24Tmp, twoToTheXMinus48Tmp;
  twoToTheXMinus24 = initUnidimensionalArray(D_classLit, $intern_2, 5, 25, 15, 1);
  twoToTheXMinus48 = initUnidimensionalArray(D_classLit, $intern_2, 5, 33, 15, 1);
  twoToTheXMinus48Tmp = 1.52587890625E-5;
  for (i0 = 32; i0 >= 0; i0--) {
    twoToTheXMinus48[i0] = twoToTheXMinus48Tmp;
    twoToTheXMinus48Tmp *= 0.5;
  }
  twoToTheXMinus24Tmp = 1;
  for (i = 24; i >= 0; i--) {
    twoToTheXMinus24[i] = twoToTheXMinus24Tmp;
    twoToTheXMinus24Tmp *= 0.5;
  }
}

function $nextInternal(this$static){
  var carry, dval, h, hi, l, lo;
  hi = this$static.seedhi * $intern_16 + this$static.seedlo * 1502;
  lo = this$static.seedlo * $intern_16 + 11;
  carry = $wnd.Math.floor(lo * $intern_17);
  hi += carry;
  lo -= carry * $intern_18;
  hi %= $intern_18;
  this$static.seedhi = hi;
  this$static.seedlo = lo;
  h = this$static.seedhi * 256;
  l = $wnd.Math.floor(this$static.seedlo * twoToTheXMinus48[32]);
  dval = h + l;
  dval >= 2147483648 && (dval -= 4294967296);
  return dval;
}

function $nextLong(this$static){
  return add_2(shl_0(fromDouble_0($nextInternal(this$static)), 32), fromDouble_0($nextInternal(this$static)));
}

function Random(){
  var hi, lo, seed;
  seed = uniqueSeed++ + Date.now();
  hi = round_int($wnd.Math.floor(seed * $intern_17)) & 16777215;
  lo = round_int(seed - hi * $intern_18);
  this.seedhi = hi ^ 1502;
  this.seedlo = lo ^ $intern_16;
}

defineClass(237, 1, {}, Random);
_.seedhi = 0;
_.seedlo = 0;
var twoToTheXMinus24, twoToTheXMinus48, uniqueSeed = 0;
var Ljava_util_Random_2_classLit = createForClass('java.util', 'Random', 237);
function $setSeed(seed){
  var seed0;
  seed0 = murmurHash3(compare_0(seed, 0) == 0?{l:0, m:0, h:$intern_19}:seed);
  murmurHash3(seed0);
}

function RandomXS128(){
  $clinit_Random();
  Random.call(this);
  $setSeed($nextLong(new Random));
}

function murmurHash3(x_0){
  x_0 = xor_0(x_0, shru_0(x_0, 33));
  x_0 = mul_1(x_0, {l:1412301, m:4153269, h:1045786});
  x_0 = xor_0(x_0, shru_0(x_0, 33));
  x_0 = mul_1(x_0, {l:388179, m:2619498, h:806123});
  x_0 = xor_0(x_0, shru_0(x_0, 33));
  return x_0;
}

defineClass(396, 237, {}, RandomXS128);
var Lcom_badlogic_gdx_math_RandomXS128_2_classLit = createForClass('com.badlogic.gdx.math', 'RandomXS128', 396);
function Rectangle(){
}

defineClass(178, 1, {178:1, 3:1}, Rectangle);
_.equals_0 = function equals_5(obj){
  var other;
  if (this === obj)
    return true;
  if (obj == null)
    return false;
  if (Lcom_badlogic_gdx_math_Rectangle_2_classLit != getClass__Ljava_lang_Class___devirtual$(obj))
    return false;
  other = castTo(obj, 178);
  if (floatToRawIntBits(this.height_0) != floatToRawIntBits(other.height_0))
    return false;
  if (floatToRawIntBits(this.width_0) != floatToRawIntBits(other.width_0))
    return false;
  if (floatToRawIntBits(this.x_0) != floatToRawIntBits(other.x_0))
    return false;
  if (floatToRawIntBits(this.y_0) != floatToRawIntBits(other.y_0))
    return false;
  return true;
}
;
_.hashCode_0 = function hashCode_6(){
  var result;
  result = 1;
  result = 31 * result + floatToRawIntBits(this.height_0);
  result = 31 * result + floatToRawIntBits(this.width_0);
  result = 31 * result + floatToRawIntBits(this.x_0);
  result = 31 * result + floatToRawIntBits(this.y_0);
  return result;
}
;
_.toString_0 = function toString_11(){
  return '[' + this.x_0 + ',' + this.y_0 + ',' + this.width_0 + ',' + this.height_0 + ']';
}
;
_.height_0 = 0;
_.width_0 = 0;
_.x_0 = 0;
_.y_0 = 0;
var Lcom_badlogic_gdx_math_Rectangle_2_classLit = createForClass('com.badlogic.gdx.math', 'Rectangle', 178);
function $clinit_Vector2(){
  $clinit_Vector2 = emptyMethod;
  new Vector2_0(1, 0);
  new Vector2_0(0, 1);
  new Vector2_0(0, 0);
  new Vector2_0(1, 1);
}

function $nor(this$static){
  var len;
  len = $wnd.Math.sqrt(this$static.x_0 * this$static.x_0 + this$static.y_0 * this$static.y_0);
  if (len != 0) {
    this$static.x_0 /= len;
    this$static.y_0 /= len;
  }
  return this$static;
}

function $set_4(this$static, x_0, y_0){
  this$static.x_0 = x_0;
  this$static.y_0 = y_0;
  return this$static;
}

function Vector2(){
  $clinit_Vector2();
}

function Vector2_0(x_0, y_0){
  this.x_0 = x_0;
  this.y_0 = y_0;
}

defineClass(89, 1, {554:1, 89:1, 3:1}, Vector2, Vector2_0);
_.equals_0 = function equals_6(obj){
  var other;
  if (this === obj)
    return true;
  if (obj == null)
    return false;
  if (Lcom_badlogic_gdx_math_Vector2_2_classLit != getClass__Ljava_lang_Class___devirtual$(obj))
    return false;
  other = castTo(obj, 89);
  if (floatToIntBits(this.x_0) != floatToIntBits(other.x_0))
    return false;
  if (floatToIntBits(this.y_0) != floatToIntBits(other.y_0))
    return false;
  return true;
}
;
_.hashCode_0 = function hashCode_7(){
  var result;
  result = 1;
  result = 31 * result + floatToIntBits(this.x_0);
  result = 31 * result + floatToIntBits(this.y_0);
  return result;
}
;
_.toString_0 = function toString_12(){
  return '(' + this.x_0 + ',' + this.y_0 + ')';
}
;
_.x_0 = 0;
_.y_0 = 0;
var Lcom_badlogic_gdx_math_Vector2_2_classLit = createForClass('com.badlogic.gdx.math', 'Vector2', 89);
function $clinit_Vector3(){
  $clinit_Vector3 = emptyMethod;
  new Vector3_0(1, 0, 0);
  new Vector3_0(0, 1, 0);
  new Vector3_0(0, 0, 1);
  new Vector3_0(0, 0, 0);
  new Vector3_0(1, 1, 1);
  new Matrix4;
}

function $crs(this$static, x_0, y_0, z_0){
  return $set_5(this$static, this$static.y_0 * z_0 - this$static.z_0 * y_0, this$static.z_0 * x_0 - this$static.x_0 * z_0, this$static.x_0 * y_0 - this$static.y_0 * x_0);
}

function $crs_0(this$static, vector){
  return $set_5(this$static, this$static.y_0 * vector.z_0 - this$static.z_0 * vector.y_0, this$static.z_0 * vector.x_0 - this$static.x_0 * vector.z_0, this$static.x_0 * vector.y_0 - this$static.y_0 * vector.x_0);
}

function $dot(this$static, vector){
  return this$static.x_0 * vector.x_0 + this$static.y_0 * vector.y_0 + this$static.z_0 * vector.z_0;
}

function $equals_2(this$static, obj){
  var other;
  if (this$static === obj)
    return true;
  if (obj == null)
    return false;
  if (Lcom_badlogic_gdx_math_Vector3_2_classLit != getClass__Ljava_lang_Class___devirtual$(obj))
    return false;
  other = castTo(obj, 18);
  if (floatToIntBits(this$static.x_0) != floatToIntBits(other.x_0))
    return false;
  if (floatToIntBits(this$static.y_0) != floatToIntBits(other.y_0))
    return false;
  if (floatToIntBits(this$static.z_0) != floatToIntBits(other.z_0))
    return false;
  return true;
}

function $hashCode_0(this$static){
  var result;
  result = 1;
  result = 31 * result + floatToIntBits(this$static.x_0);
  result = 31 * result + floatToIntBits(this$static.y_0);
  result = 31 * result + floatToIntBits(this$static.z_0);
  return result;
}

function $nor_0(this$static){
  var len2;
  len2 = this$static.x_0 * this$static.x_0 + this$static.y_0 * this$static.y_0 + this$static.z_0 * this$static.z_0;
  if (len2 == 0 || len2 == 1)
    return this$static;
  return $scl(this$static, 1 / $wnd.Math.sqrt(len2));
}

function $prj(this$static, matrix){
  var l_mat, l_w;
  l_mat = matrix.val_0;
  l_w = 1 / (this$static.x_0 * l_mat[3] + this$static.y_0 * l_mat[7] + this$static.z_0 * l_mat[11] + l_mat[15]);
  return $set_5(this$static, (this$static.x_0 * l_mat[0] + this$static.y_0 * l_mat[4] + this$static.z_0 * l_mat[8] + l_mat[12]) * l_w, (this$static.x_0 * l_mat[1] + this$static.y_0 * l_mat[5] + this$static.z_0 * l_mat[9] + l_mat[13]) * l_w, (this$static.x_0 * l_mat[2] + this$static.y_0 * l_mat[6] + this$static.z_0 * l_mat[10] + l_mat[14]) * l_w);
}

function $scl(this$static, scalar){
  return $set_5(this$static, this$static.x_0 * scalar, this$static.y_0 * scalar, this$static.z_0 * scalar);
}

function $set_5(this$static, x_0, y_0, z_0){
  this$static.x_0 = x_0;
  this$static.y_0 = y_0;
  this$static.z_0 = z_0;
  return this$static;
}

function $set_6(this$static, vector){
  return $set_5(this$static, vector.x_0, vector.y_0, vector.z_0);
}

function $sub(this$static, x_0, y_0, z_0){
  return $set_5(this$static, this$static.x_0 - x_0, this$static.y_0 - y_0, this$static.z_0 - z_0);
}

function $sub_0(this$static, a_vec){
  return $sub(this$static, a_vec.x_0, a_vec.y_0, a_vec.z_0);
}

function $toString(this$static){
  return '(' + this$static.x_0 + ',' + this$static.y_0 + ',' + this$static.z_0 + ')';
}

function Vector3(){
  $clinit_Vector3();
}

function Vector3_0(x_0, y_0, z_0){
  $clinit_Vector3();
  $set_5(this, x_0, y_0, z_0);
}

defineClass(18, 1, {554:1, 18:1, 3:1}, Vector3, Vector3_0);
_.equals_0 = function equals_7(obj){
  return $equals_2(this, obj);
}
;
_.hashCode_0 = function hashCode_8(){
  return $hashCode_0(this);
}
;
_.toString_0 = function toString_13(){
  return $toString(this);
}
;
_.x_0 = 0;
_.y_0 = 0;
_.z_0 = 0;
var Lcom_badlogic_gdx_math_Vector3_2_classLit = createForClass('com.badlogic.gdx.math', 'Vector3', 18);
function $clinit_Ray(){
  $clinit_Ray = emptyMethod;
  $clinit_Vector3();
}

function Ray(origin_0, direction){
  $clinit_Ray();
  this.origin_0 = new Vector3;
  this.direction_0 = new Vector3;
  $set_6(this.origin_0, origin_0);
  $nor_0($set_6(this.direction_0, direction));
}

defineClass(186, 1, {186:1, 3:1}, Ray);
_.equals_0 = function equals_8(o){
  var r;
  if (o === this)
    return true;
  if (o == null || getClass__Ljava_lang_Class___devirtual$(o) != Lcom_badlogic_gdx_math_collision_Ray_2_classLit)
    return false;
  r = castTo(o, 186);
  return $equals_2(this.direction_0, r.direction_0) && $equals_2(this.origin_0, r.origin_0);
}
;
_.hashCode_0 = function hashCode_9(){
  var result;
  result = 1;
  result = 73 * result + $hashCode_0(this.direction_0);
  result = 73 * result + $hashCode_0(this.origin_0);
  return result;
}
;
_.toString_0 = function toString_14(){
  return 'ray [' + this.origin_0 + ':' + this.direction_0 + ']';
}
;
var Lcom_badlogic_gdx_math_collision_Ray_2_classLit = createForClass('com.badlogic.gdx.math.collision', 'Ray', 186);
function $add_0(this$static, value_0){
  var items;
  items = this$static.items;
  this$static.size_0 == items.length && (items = $resize(this$static, $wnd.Math.max(8, round_int(this$static.size_0 * 1.75))));
  setCheck(items, this$static.size_0++, value_0);
}

function $addAll(this$static, array){
  $addAll_0(this$static, array.items, array.size_0);
}

function $addAll_0(this$static, array, count){
  var items, sizeNeeded;
  items = this$static.items;
  sizeNeeded = this$static.size_0 + count;
  sizeNeeded > items.length && (items = $resize(this$static, $wnd.Math.max($wnd.Math.max(8, sizeNeeded), round_int(this$static.size_0 * 1.75))));
  arraycopy(array, 0, items, this$static.size_0, count);
  this$static.size_0 = sizeNeeded;
}

function $clear(this$static){
  fill(this$static.items, this$static.size_0);
  this$static.size_0 = 0;
}

function $get_1(this$static, index_0){
  if (index_0 >= this$static.size_0)
    throw toJs(new IndexOutOfBoundsException_0("index can't be >= size: " + index_0 + ' >= ' + this$static.size_0));
  return this$static.items[index_0];
}

function $iterator_0(this$static){
  if (allocateIterators)
    return new Array$ArrayIterator(this$static);
  !this$static.iterable && (this$static.iterable = new Array$ArrayIterable(this$static));
  return $iterator_1(this$static.iterable);
}

function $pop(this$static){
  var item_0;
  if (this$static.size_0 == 0)
    throw toJs(new IllegalStateException_0('Array is empty.'));
  --this$static.size_0;
  item_0 = this$static.items[this$static.size_0];
  setCheck(this$static.items, this$static.size_0, null);
  return item_0;
}

function $resize(this$static, newSize){
  this$static.items = copyOf_0(this$static.items, newSize);
  return this$static.items;
}

function Array_0(){
  Array_2.call(this, true, 16);
}

function Array_1(capacity){
  Array_2.call(this, true, capacity);
}

function Array_2(ordered, capacity){
  var lastArg;
  Array_3.call(this, ordered, (lastArg = capacity , lastArg));
}

function Array_3(ordered, capacity){
  this.ordered = ordered;
  this.items = initUnidimensionalArray(Ljava_lang_Object_2_classLit, $intern_9, 1, capacity, 5, 1);
}

defineClass(50, 1, {50:1}, Array_0, Array_1, Array_2);
_.iterator = function iterator_3(){
  return $iterator_0(this);
}
;
_.equals_0 = function equals_9(object){
  var array, i, items1, items2, n, o1, o2;
  if (object === this)
    return true;
  if (!this.ordered)
    return false;
  if (!instanceOf(object, 50))
    return false;
  array = castTo(object, 50);
  if (!array.ordered)
    return false;
  n = this.size_0;
  if (n != array.size_0)
    return false;
  items1 = this.items;
  items2 = array.items;
  for (i = 0; i < n; i++) {
    o1 = items1[i];
    o2 = items2[i];
    if (!(o1 == null?o2 == null:equals_Ljava_lang_Object__Z__devirtual$(o1, o2)))
      return false;
  }
  return true;
}
;
_.hashCode_0 = function hashCode_10(){
  var h, i, item_0, items, n;
  if (!this.ordered)
    return getObjectIdentityHashCode(this);
  items = this.items;
  h = 1;
  for (i = 0 , n = this.size_0; i < n; i++) {
    h *= 31;
    item_0 = items[i];
    item_0 != null && (h += hashCode__I__devirtual$(item_0));
  }
  return h;
}
;
_.toString_0 = function toString_15(){
  var buffer, i, items;
  if (this.size_0 == 0)
    return '[]';
  items = this.items;
  buffer = new StringBuilder_0;
  buffer.string += '[';
  $append_2(buffer, items[0]);
  for (i = 1; i < this.size_0; i++) {
    buffer.string += ', ';
    $append_2(buffer, items[i]);
  }
  buffer.string += ']';
  return buffer.string;
}
;
_.ordered = false;
_.size_0 = 0;
var Lcom_badlogic_gdx_utils_Array_2_classLit = createForClass('com.badlogic.gdx.utils', 'Array', 50);
function $iterator_1(this$static){
  if (allocateIterators)
    return new Array$ArrayIterator(this$static.array);
  if (!this$static.iterator1) {
    this$static.iterator1 = new Array$ArrayIterator(this$static.array);
    this$static.iterator2 = new Array$ArrayIterator(this$static.array);
  }
  if (!this$static.iterator1.valid) {
    this$static.iterator1.index_0 = 0;
    this$static.iterator1.valid = true;
    this$static.iterator2.valid = false;
    return this$static.iterator1;
  }
  this$static.iterator2.index_0 = 0;
  this$static.iterator2.valid = true;
  this$static.iterator1.valid = false;
  return this$static.iterator2;
}

function Array$ArrayIterable(array){
  this.array = array;
}

defineClass(229, 1, {}, Array$ArrayIterable);
_.iterator = function iterator_4(){
  return $iterator_1(this);
}
;
var Lcom_badlogic_gdx_utils_Array$ArrayIterable_2_classLit = createForClass('com.badlogic.gdx.utils', 'Array/ArrayIterable', 229);
function $hasNext(this$static){
  if (!this$static.valid) {
    throw toJs(new GdxRuntimeException('#iterator() cannot be used nested.'));
  }
  return this$static.index_0 < this$static.array.size_0;
}

function $next(this$static){
  if (this$static.index_0 >= this$static.array.size_0)
    throw toJs(new NoSuchElementException_0('' + this$static.index_0));
  if (!this$static.valid) {
    throw toJs(new GdxRuntimeException('#iterator() cannot be used nested.'));
  }
  return this$static.array.items[this$static.index_0++];
}

function Array$ArrayIterator(array){
  this.array = array;
}

defineClass(143, 1, {}, Array$ArrayIterator);
_.iterator = function iterator_5(){
  return this;
}
;
_.hasNext_0 = function hasNext_0(){
  return $hasNext(this);
}
;
_.next_1 = function next_1(){
  return $next(this);
}
;
_.index_0 = 0;
_.valid = true;
var Lcom_badlogic_gdx_utils_Array$ArrayIterator_2_classLit = createForClass('com.badlogic.gdx.utils', 'Array/ArrayIterator', 143);
function asFloatBuffer(data_0){
  var buffer;
  buffer = null;
  instanceOf(data_0, 64)?(buffer = castTo(data_0, 64).asFloatBuffer()):instanceOf(data_0, 68) && (buffer = castTo(data_0, 68));
  if (!buffer)
    throw toJs(new GdxRuntimeException('data must be a ByteBuffer or FloatBuffer'));
  return buffer;
}

function copy_0(src_0, dst, numFloats, offset){
  var floatBuffer;
  floatBuffer = asFloatBuffer(dst);
  $clear_1(floatBuffer);
  $position(dst, 0);
  $put_2(floatBuffer, src_0, offset, numFloats);
  $position(dst, 0);
  instanceOf(dst, 64)?$limit(dst, numFloats << 2):$limit(dst, numFloats);
}

function newByteBuffer(numBytes){
  var buffer;
  buffer = allocateDirect(numBytes);
  $order(buffer, ($clinit_ByteOrder() , $clinit_ByteOrder() , NATIVE_ORDER));
  return buffer;
}

function newFloatBuffer(numFloats){
  var buffer;
  buffer = allocateDirect(numFloats * 4);
  $order(buffer, ($clinit_ByteOrder() , $clinit_ByteOrder() , NATIVE_ORDER));
  return new DirectReadWriteFloatBufferAdapter($slice(buffer));
}

function newIntBuffer(){
  var buffer;
  buffer = allocateDirect(4);
  $order(buffer, ($clinit_ByteOrder() , $clinit_ByteOrder() , NATIVE_ORDER));
  return (buffer.order == 0?BIG_ENDIAN:LITTLE_ENDIAN) == (null , NATIVE_ORDER)?new DirectReadWriteIntBufferAdapter($slice(buffer)):new IntToByteBufferAdapter($slice(buffer));
}

function newShortBuffer(numShorts){
  var buffer;
  buffer = allocateDirect(numShorts * 2);
  $order(buffer, ($clinit_ByteOrder() , $clinit_ByteOrder() , NATIVE_ORDER));
  return (buffer.order == 0?BIG_ENDIAN:LITTLE_ENDIAN) == (null , NATIVE_ORDER)?new DirectReadWriteShortBufferAdapter($slice(buffer)):new ShortToByteBufferAdapter($slice(buffer));
}

var allocateIterators = false;
function $$init(this$static){
  this$static.stackTrace = initUnidimensionalArray(Ljava_lang_StackTraceElement_2_classLit, $intern_9, 83, 0, 0, 1);
}

function $addSuppressed(this$static, exception){
  checkCriticalNotNull_0(exception, 'Cannot suppress a null exception.');
  checkCriticalArgument_0(exception != this$static, 'Exception can not suppress itself.');
  if (this$static.disableSuppression) {
    return;
  }
  this$static.suppressedExceptions == null?(this$static.suppressedExceptions = stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_Throwable_2_classLit, 1), $intern_9, 9, 0, [exception])):(this$static.suppressedExceptions[this$static.suppressedExceptions.length] = exception);
}

function $fillInStackTrace(this$static){
  if (this$static.writableStackTrace) {
    this$static.backingJsObject !== '__noinit__' && this$static.initializeBackingError();
    this$static.stackTrace = null;
  }
  return this$static;
}

function $linkBack(this$static, error){
  if (error instanceof Object) {
    try {
      error.__java$exception = this$static;
      if (navigator.userAgent.toLowerCase().indexOf('msie') != -1 && $doc.documentMode < 9) {
        return;
      }
      var throwable = this$static;
      Object.defineProperties(error, {cause:{get:function(){
        var cause = throwable.getCause();
        return cause && cause.getBackingJsObject();
      }
      }, suppressed:{get:function(){
        return throwable.getBackingSuppressed();
      }
      }});
    }
     catch (ignored) {
    }
  }
}

function $printStackTraceImpl(this$static, out, ident){
  var t, t$array, t$index, t$max, theCause;
  String.fromCharCode(10);
  $printStackTraceItems(this$static);
  for (t$array = (this$static.suppressedExceptions == null && (this$static.suppressedExceptions = initUnidimensionalArray(Ljava_lang_Throwable_2_classLit, $intern_9, 9, 0, 0, 1)) , this$static.suppressedExceptions) , t$index = 0 , t$max = t$array.length; t$index < t$max; ++t$index) {
    t = t$array[t$index];
    $printStackTraceImpl(t, out, '\t' + ident);
  }
  theCause = this$static.cause_0;
  !!theCause && $printStackTraceImpl(theCause, out, ident);
}

function $printStackTraceItems(this$static){
  var element$array, element$index, element$max;
  for (element$array = (this$static.stackTrace == null && (this$static.stackTrace = constructJavaStackTrace(this$static)) , this$static.stackTrace) , element$index = 0 , element$max = element$array.length; element$index < element$max; ++element$index) {
    String.fromCharCode(10);
  }
}

function $setBackingJsObject(this$static, backingJsObject){
  this$static.backingJsObject = backingJsObject;
  $linkBack(this$static, backingJsObject);
}

function $toString_0(this$static, message){
  var className;
  className = $getName(this$static.___clazz);
  return message == null?className:className + ': ' + message;
}

function Throwable(message, cause){
  $$init(this);
  this.cause_0 = cause;
  this.detailMessage = message;
  $fillInStackTrace(this);
  this.initializeBackingError();
}

function fixIE(e){
  if (!('stack' in e)) {
    try {
      throw e;
    }
     catch (ignored) {
    }
  }
  return e;
}

defineClass(9, 1, $intern_20);
_.createError = function createError(msg){
  return new Error(msg);
}
;
_.getBackingJsObject = function getBackingJsObject(){
  return this.backingJsObject;
}
;
_.getBackingSuppressed = function getBackingSuppressed(){
  var i, result, suppressed;
  suppressed = (this.suppressedExceptions == null && (this.suppressedExceptions = initUnidimensionalArray(Ljava_lang_Throwable_2_classLit, $intern_9, 9, 0, 0, 1)) , this.suppressedExceptions);
  result = initUnidimensionalArray(Ljava_lang_Object_2_classLit, $intern_9, 1, suppressed.length, 5, 1);
  for (i = 0; i < suppressed.length; i++) {
    result[i] = suppressed[i].backingJsObject;
  }
  return result;
}
;
_.getCause = function getCause(){
  return this.cause_0;
}
;
_.getMessage = function getMessage(){
  return this.detailMessage;
}
;
_.initializeBackingError = function initializeBackingError(){
  $setBackingJsObject(this, fixIE(this.createError($toString_0(this, this.detailMessage))));
  captureStackTrace(this);
}
;
_.toString_0 = function toString_16(){
  return $toString_0(this, this.getMessage());
}
;
_.backingJsObject = '__noinit__';
_.disableSuppression = false;
_.writableStackTrace = true;
var Ljava_lang_Throwable_2_classLit = createForClass('java.lang', 'Throwable', 9);
function Exception(){
  $$init(this);
  $fillInStackTrace(this);
  this.initializeBackingError();
}

function Exception_0(message){
  $$init(this);
  this.detailMessage = message;
  $fillInStackTrace(this);
  this.initializeBackingError();
}

function Exception_1(message, cause){
  Throwable.call(this, message, cause);
}

function Exception_2(cause){
  $$init(this);
  this.detailMessage = !cause?null:$toString_0(cause, cause.getMessage());
  this.cause_0 = cause;
  $fillInStackTrace(this);
  this.initializeBackingError();
}

defineClass(6, 9, $intern_21);
var Ljava_lang_Exception_2_classLit = createForClass('java.lang', 'Exception', 6);
function RuntimeException(){
  Exception.call(this);
}

function RuntimeException_0(message){
  Exception_0.call(this, message);
}

function RuntimeException_1(message, cause){
  Exception_1.call(this, message, cause);
}

function RuntimeException_2(cause){
  Exception_2.call(this, cause);
}

defineClass(27, 6, $intern_21, RuntimeException_0, RuntimeException_1, RuntimeException_2);
var Ljava_lang_RuntimeException_2_classLit = createForClass('java.lang', 'RuntimeException', 27);
function GdxRuntimeException(message){
  RuntimeException_0.call(this, message);
}

function GdxRuntimeException_0(message, t){
  RuntimeException_1.call(this, message, t);
}

defineClass(26, 27, {26:1, 3:1, 6:1, 9:1}, GdxRuntimeException, GdxRuntimeException_0);
var Lcom_badlogic_gdx_utils_GdxRuntimeException_2_classLit = createForClass('com.badlogic.gdx.utils', 'GdxRuntimeException', 26);
function $add_1(this$static, value_0){
  var items;
  items = this$static.items;
  this$static.size_0 == items.length && (items = $resize_0(this$static, $wnd.Math.max(8, round_int(this$static.size_0 * 1.75))));
  items[this$static.size_0++] = value_0;
}

function $get_2(this$static, index_0){
  if (index_0 >= this$static.size_0)
    throw toJs(new IndexOutOfBoundsException_0("index can't be >= size: " + index_0 + ' >= ' + this$static.size_0));
  return this$static.items[index_0];
}

function $resize_0(this$static, newSize){
  var items, newItems;
  newItems = initUnidimensionalArray(I_classLit, $intern_3, 5, newSize, 15, 1);
  items = this$static.items;
  arraycopy(items, 0, newItems, 0, $wnd.Math.min(this$static.size_0, newItems.length));
  this$static.items = newItems;
  return newItems;
}

function IntArray(){
  this.ordered = true;
  this.items = initUnidimensionalArray(I_classLit, $intern_3, 5, 16, 15, 1);
}

defineClass(131, 1, {131:1}, IntArray);
_.equals_0 = function equals_10(object){
  var array, i, items1, items2, n;
  if (object === this)
    return true;
  if (!this.ordered)
    return false;
  if (!instanceOf(object, 131))
    return false;
  array = castTo(object, 131);
  if (!array.ordered)
    return false;
  n = this.size_0;
  if (n != array.size_0)
    return false;
  items1 = this.items;
  items2 = array.items;
  for (i = 0; i < n; i++)
    if (items1[i] !== items2[i])
      return false;
  return true;
}
;
_.hashCode_0 = function hashCode_11(){
  var h, i, items, n;
  if (!this.ordered)
    return getObjectIdentityHashCode(this);
  items = this.items;
  h = 1;
  for (i = 0 , n = this.size_0; i < n; i++)
    h = h * 31 + items[i];
  return h;
}
;
_.toString_0 = function toString_17(){
  var buffer, i, items;
  if (this.size_0 == 0)
    return '[]';
  items = this.items;
  buffer = new StringBuilder_0;
  buffer.string += '[';
  $append_0(buffer, items[0]);
  for (i = 1; i < this.size_0; i++) {
    buffer.string += ', ';
    $append_0(buffer, items[i]);
  }
  buffer.string += ']';
  return buffer.string;
}
;
_.ordered = false;
_.size_0 = 0;
var Lcom_badlogic_gdx_utils_IntArray_2_classLit = createForClass('com.badlogic.gdx.utils', 'IntArray', 131);
function $containsValue(this$static, value_0){
  var i, keyTable, valueTable;
  valueTable = this$static.valueTable;
  if (!value_0) {
    if (this$static.hasZeroValue && !this$static.zeroValue)
      return true;
    keyTable = this$static.keyTable;
    for (i = valueTable.length - 1; i >= 0; i--)
      if (keyTable[i] != 0 && valueTable[i] == null)
        return true;
  }
   else {
    if (this$static.hasZeroValue && $equals_4(value_0, this$static.zeroValue))
      return true;
    for (i = valueTable.length - 1; i >= 0; i--)
      if ($equals_4(value_0, valueTable[i]))
        return true;
  }
  return false;
}

function $entries(this$static){
  if (allocateIterators)
    return new IntMap$Entries(this$static);
  if (!this$static.entries1) {
    this$static.entries1 = new IntMap$Entries(this$static);
    this$static.entries2 = new IntMap$Entries(this$static);
  }
  if (!this$static.entries1.valid) {
    $reset_0(this$static.entries1);
    this$static.entries1.valid = true;
    this$static.entries2.valid = false;
    return this$static.entries1;
  }
  $reset_0(this$static.entries2);
  this$static.entries2.valid = true;
  this$static.entries1.valid = false;
  return this$static.entries2;
}

function $get_3(this$static, key){
  var i;
  if (key == 0)
    return this$static.hasZeroValue?this$static.zeroValue:null;
  i = $locateKey(this$static, key);
  return i >= 0?this$static.valueTable[i]:null;
}

function $get_4(this$static, key, defaultValue){
  var i;
  if (key == 0)
    return this$static.hasZeroValue?this$static.zeroValue:defaultValue;
  i = $locateKey(this$static, key);
  return i >= 0?this$static.valueTable[i]:defaultValue;
}

function $locateKey(this$static, key){
  var i, keyTable, other;
  keyTable = this$static.keyTable;
  for (i = toInt_0(shru_0(mul_1(key, $intern_22), this$static.shift_0));; i = i + 1 & this$static.mask) {
    other = keyTable[i];
    if (other == 0)
      return -(i + 1);
    if (other == key)
      return i;
  }
}

function $put(this$static, key, value_0){
  var i, oldValue;
  if (key == 0) {
    oldValue = this$static.zeroValue;
    this$static.zeroValue = value_0;
    if (!this$static.hasZeroValue) {
      this$static.hasZeroValue = true;
      ++this$static.size_0;
    }
    return oldValue;
  }
  i = $locateKey(this$static, key);
  if (i >= 0) {
    oldValue = this$static.valueTable[i];
    this$static.valueTable[i] = value_0;
    return oldValue;
  }
  i = -(i + 1);
  this$static.keyTable[i] = key;
  this$static.valueTable[i] = value_0;
  ++this$static.size_0 >= this$static.threshold && $resize_1(this$static, this$static.keyTable.length << 1);
  return null;
}

function $putResize(this$static, key, value_0){
  var i, keyTable;
  keyTable = this$static.keyTable;
  for (i = toInt_0(shru_0(mul_1(key, $intern_22), this$static.shift_0));; i = i + 1 & this$static.mask) {
    if (keyTable[i] == 0) {
      keyTable[i] = key;
      this$static.valueTable[i] = value_0;
      return;
    }
  }
}

function $remove(this$static, key){
  var i, keyTable, mask, next, oldValue, oldValue0, placement, valueTable;
  if (key == 0) {
    if (!this$static.hasZeroValue)
      return null;
    this$static.hasZeroValue = false;
    oldValue0 = this$static.zeroValue;
    this$static.zeroValue = null;
    --this$static.size_0;
    return oldValue0;
  }
  i = $locateKey(this$static, key);
  if (i < 0)
    return null;
  keyTable = this$static.keyTable;
  valueTable = this$static.valueTable;
  oldValue = valueTable[i];
  mask = this$static.mask;
  next = i + 1 & mask;
  while ((key = keyTable[next]) != 0) {
    placement = toInt_0(shru_0(mul_1(key, $intern_22), this$static.shift_0));
    if ((next - placement & mask) > (i - placement & mask)) {
      keyTable[i] = key;
      valueTable[i] = valueTable[next];
      i = next;
    }
    next = next + 1 & mask;
  }
  keyTable[i] = 0;
  valueTable[i] = null;
  --this$static.size_0;
  return oldValue;
}

function $resize_1(this$static, newSize){
  var i, key, oldCapacity, oldKeyTable, oldValueTable;
  oldCapacity = this$static.keyTable.length;
  this$static.threshold = round_int(newSize * this$static.loadFactor);
  this$static.mask = newSize - 1;
  this$static.shift_0 = numberOfLeadingZeros_1(this$static.mask);
  oldKeyTable = this$static.keyTable;
  oldValueTable = this$static.valueTable;
  this$static.keyTable = initUnidimensionalArray(I_classLit, $intern_3, 5, newSize, 15, 1);
  this$static.valueTable = initUnidimensionalArray(Ljava_lang_Object_2_classLit, $intern_9, 1, newSize, 5, 1);
  if (this$static.size_0 > 0) {
    for (i = 0; i < oldCapacity; i++) {
      key = oldKeyTable[i];
      key != 0 && $putResize(this$static, key, oldValueTable[i]);
    }
  }
}

function IntMap(){
  var tableSize;
  this.loadFactor = $intern_23;
  tableSize = tableSize_0(20);
  this.threshold = round_int(tableSize * $intern_23);
  this.mask = tableSize - 1;
  this.shift_0 = numberOfLeadingZeros_1(fromInt(this.mask));
  this.keyTable = initUnidimensionalArray(I_classLit, $intern_3, 5, tableSize, 15, 1);
  this.valueTable = initUnidimensionalArray(Ljava_lang_Object_2_classLit, $intern_9, 1, tableSize, 5, 1);
}

defineClass(126, 1, {126:1}, IntMap);
_.equals_0 = function equals_11(obj){
  var i, key, keyTable, n, other, value_0, valueTable;
  if (obj === this)
    return true;
  if (!instanceOf(obj, 126))
    return false;
  other = castTo(obj, 126);
  if (other.size_0 != this.size_0)
    return false;
  if (other.hasZeroValue != this.hasZeroValue)
    return false;
  if (this.hasZeroValue) {
    if (!other.zeroValue) {
      if (this.zeroValue)
        return false;
    }
     else {
      if (!$equals_4(other.zeroValue, this.zeroValue))
        return false;
    }
  }
  keyTable = this.keyTable;
  valueTable = this.valueTable;
  for (i = 0 , n = keyTable.length; i < n; i++) {
    key = keyTable[i];
    if (key != 0) {
      value_0 = valueTable[i];
      if (value_0 == null) {
        if ($get_4(other, key, ($clinit_ObjectMap() , dummy)) != null)
          return false;
      }
       else {
        if (!equals_Ljava_lang_Object__Z__devirtual$(value_0, $get_3(other, key)))
          return false;
      }
    }
  }
  return true;
}
;
_.hashCode_0 = function hashCode_12(){
  var h, i, key, keyTable, n, value_0, valueTable;
  h = this.size_0;
  this.hasZeroValue && !!this.zeroValue && (h += this.zeroValue.value_0);
  keyTable = this.keyTable;
  valueTable = this.valueTable;
  for (i = 0 , n = keyTable.length; i < n; i++) {
    key = keyTable[i];
    if (key != 0) {
      h += key * 31;
      value_0 = valueTable[i];
      value_0 != null && (h += hashCode__I__devirtual$(value_0));
    }
  }
  return h;
}
;
_.iterator = function iterator_6(){
  return $entries(this);
}
;
_.toString_0 = function toString_18(){
  var buffer, i, key, keyTable, valueTable;
  if (this.size_0 == 0)
    return '[]';
  buffer = new StringBuilder_0;
  buffer.string += '[';
  keyTable = this.keyTable;
  valueTable = this.valueTable;
  i = keyTable.length;
  if (this.hasZeroValue) {
    buffer.string += '0=';
    $append_2(buffer, this.zeroValue);
  }
   else {
    while (i-- > 0) {
      key = keyTable[i];
      if (key == 0)
        continue;
      buffer.string += key;
      buffer.string += '=';
      $append_2(buffer, valueTable[i]);
      break;
    }
  }
  while (i-- > 0) {
    key = keyTable[i];
    if (key == 0)
      continue;
    buffer.string += ', ';
    buffer.string += key;
    buffer.string += '=';
    $append_2(buffer, valueTable[i]);
  }
  buffer.string += ']';
  return buffer.string;
}
;
_.hasZeroValue = false;
_.loadFactor = 0;
_.mask = 0;
_.shift_0 = 0;
_.size_0 = 0;
_.threshold = 0;
var Lcom_badlogic_gdx_utils_IntMap_2_classLit = createForClass('com.badlogic.gdx.utils', 'IntMap', 126);
function $findNextIndex(this$static){
  var keyTable, n;
  keyTable = this$static.map_0.keyTable;
  for (n = keyTable.length; ++this$static.nextIndex < n;) {
    if (keyTable[this$static.nextIndex] != 0) {
      this$static.hasNext = true;
      return;
    }
  }
  this$static.hasNext = false;
}

function $reset_0(this$static){
  this$static.nextIndex = -1;
  this$static.map_0.hasZeroValue?(this$static.hasNext = true):$findNextIndex(this$static);
}

defineClass(430, 1, {});
_.hasNext = false;
_.nextIndex = 0;
_.valid = true;
var Lcom_badlogic_gdx_utils_IntMap$MapIterator_2_classLit = createForClass('com.badlogic.gdx.utils', 'IntMap/MapIterator', 430);
function $next_0(this$static){
  var keyTable;
  if (!this$static.hasNext)
    throw toJs(new NoSuchElementException);
  if (!this$static.valid)
    throw toJs(new GdxRuntimeException('#iterator() cannot be used nested.'));
  keyTable = this$static.map_0.keyTable;
  if (this$static.nextIndex == -1) {
    this$static.entry.key = 0;
    this$static.entry.value_0 = this$static.map_0.zeroValue;
  }
   else {
    this$static.entry.key = keyTable[this$static.nextIndex];
    this$static.entry.value_0 = this$static.map_0.valueTable[this$static.nextIndex];
  }
  $findNextIndex(this$static);
  return this$static.entry;
}

function IntMap$Entries(map_0){
  this.map_0 = map_0;
  this.nextIndex = -1;
  this.map_0.hasZeroValue?(this.hasNext = true):$findNextIndex(this);
  this.entry = new IntMap$Entry;
}

defineClass(180, 430, {}, IntMap$Entries);
_.next_1 = function next_2(){
  return $next_0(this);
}
;
_.hasNext_0 = function hasNext_1(){
  if (!this.valid)
    throw toJs(new GdxRuntimeException('#iterator() cannot be used nested.'));
  return this.hasNext;
}
;
_.iterator = function iterator_7(){
  return this;
}
;
var Lcom_badlogic_gdx_utils_IntMap$Entries_2_classLit = createForClass('com.badlogic.gdx.utils', 'IntMap/Entries', 180);
function IntMap$Entry(){
}

defineClass(429, 1, {}, IntMap$Entry);
_.toString_0 = function toString_19(){
  return this.key + '=' + this.value_0;
}
;
_.key = 0;
var Lcom_badlogic_gdx_utils_IntMap$Entry_2_classLit = createForClass('com.badlogic.gdx.utils', 'IntMap/Entry', 429);
function $add_2(this$static, key){
  var i;
  if (key == 0) {
    if (this$static.hasZeroValue)
      return false;
    this$static.hasZeroValue = true;
    ++this$static.size_0;
    return true;
  }
  i = $locateKey_0(this$static, key);
  if (i >= 0)
    return false;
  i = -(i + 1);
  this$static.keyTable[i] = key;
  ++this$static.size_0 >= this$static.threshold && $resize_2(this$static, this$static.keyTable.length << 1);
  return true;
}

function $addResize(this$static, key){
  var i, keyTable;
  keyTable = this$static.keyTable;
  for (i = toInt_0(shru_0(mul_1(key, $intern_22), this$static.shift_0));; i = i + 1 & this$static.mask) {
    if (keyTable[i] == 0) {
      keyTable[i] = key;
      return;
    }
  }
}

function $contains_0(this$static, key){
  if (key == 0)
    return this$static.hasZeroValue;
  return $locateKey_0(this$static, key) >= 0;
}

function $iterator_2(this$static){
  if (allocateIterators)
    return new IntSet$IntSetIterator(this$static);
  if (!this$static.iterator1) {
    this$static.iterator1 = new IntSet$IntSetIterator(this$static);
    this$static.iterator2 = new IntSet$IntSetIterator(this$static);
  }
  if (!this$static.iterator1.valid) {
    $reset_1(this$static.iterator1);
    this$static.iterator1.valid = true;
    this$static.iterator2.valid = false;
    return this$static.iterator1;
  }
  $reset_1(this$static.iterator2);
  this$static.iterator2.valid = true;
  this$static.iterator1.valid = false;
  return this$static.iterator2;
}

function $locateKey_0(this$static, key){
  var i, keyTable, other;
  keyTable = this$static.keyTable;
  for (i = toInt_0(shru_0(mul_1(key, $intern_22), this$static.shift_0));; i = i + 1 & this$static.mask) {
    other = keyTable[i];
    if (other == 0)
      return -(i + 1);
    if (other == key)
      return i;
  }
}

function $remove_0(this$static, key){
  var i, keyTable, mask, next, placement;
  if (key == 0) {
    if (!this$static.hasZeroValue)
      return false;
    this$static.hasZeroValue = false;
    --this$static.size_0;
    return true;
  }
  i = $locateKey_0(this$static, key);
  if (i < 0)
    return false;
  keyTable = this$static.keyTable;
  mask = this$static.mask;
  next = i + 1 & mask;
  while ((key = keyTable[next]) != 0) {
    placement = toInt_0(shru_0(mul_1(key, $intern_22), this$static.shift_0));
    if ((next - placement & mask) > (i - placement & mask)) {
      keyTable[i] = key;
      i = next;
    }
    next = next + 1 & mask;
  }
  keyTable[i] = 0;
  --this$static.size_0;
  return true;
}

function $resize_2(this$static, newSize){
  var i, key, oldCapacity, oldKeyTable;
  oldCapacity = this$static.keyTable.length;
  this$static.threshold = round_int(newSize * this$static.loadFactor);
  this$static.mask = newSize - 1;
  this$static.shift_0 = numberOfLeadingZeros_1(this$static.mask);
  oldKeyTable = this$static.keyTable;
  this$static.keyTable = initUnidimensionalArray(I_classLit, $intern_3, 5, newSize, 15, 1);
  if (this$static.size_0 > 0) {
    for (i = 0; i < oldCapacity; i++) {
      key = oldKeyTable[i];
      key != 0 && $addResize(this$static, key);
    }
  }
}

function IntSet(){
  var tableSize;
  this.loadFactor = $intern_23;
  tableSize = tableSize_0(51);
  this.threshold = round_int(tableSize * $intern_23);
  this.mask = tableSize - 1;
  this.shift_0 = numberOfLeadingZeros_1(fromInt(this.mask));
  this.keyTable = initUnidimensionalArray(I_classLit, $intern_3, 5, tableSize, 15, 1);
}

defineClass(100, 1, {100:1}, IntSet);
_.equals_0 = function equals_12(obj){
  var i, keyTable, n, other;
  if (!instanceOf(obj, 100))
    return false;
  other = castTo(obj, 100);
  if (other.size_0 != this.size_0)
    return false;
  if (other.hasZeroValue != this.hasZeroValue)
    return false;
  keyTable = this.keyTable;
  for (i = 0 , n = keyTable.length; i < n; i++)
    if (keyTable[i] != 0 && !$contains_0(other, keyTable[i]))
      return false;
  return true;
}
;
_.hashCode_0 = function hashCode_13(){
  var h, i, key, keyTable, n;
  h = this.size_0;
  keyTable = this.keyTable;
  for (i = 0 , n = keyTable.length; i < n; i++) {
    key = keyTable[i];
    key != 0 && (h += key);
  }
  return h;
}
;
_.toString_0 = function toString_20(){
  var buffer, i, key, keyTable;
  if (this.size_0 == 0)
    return '[]';
  buffer = new StringBuilder_0;
  buffer.string += '[';
  keyTable = this.keyTable;
  i = keyTable.length;
  if (this.hasZeroValue)
    buffer.string += '0';
  else {
    while (i-- > 0) {
      key = keyTable[i];
      if (key == 0)
        continue;
      buffer.string += key;
      break;
    }
  }
  while (i-- > 0) {
    key = keyTable[i];
    if (key == 0)
      continue;
    buffer.string += ', ';
    buffer.string += key;
  }
  buffer.string += ']';
  return buffer.string;
}
;
_.hasZeroValue = false;
_.loadFactor = 0;
_.mask = 0;
_.shift_0 = 0;
_.size_0 = 0;
_.threshold = 0;
var Lcom_badlogic_gdx_utils_IntSet_2_classLit = createForClass('com.badlogic.gdx.utils', 'IntSet', 100);
function $findNextIndex_0(this$static){
  var keyTable, n;
  keyTable = this$static.set_0.keyTable;
  for (n = keyTable.length; ++this$static.nextIndex < n;) {
    if (keyTable[this$static.nextIndex] != 0) {
      this$static.hasNext = true;
      return;
    }
  }
  this$static.hasNext = false;
}

function $next_1(this$static){
  var key;
  if (!this$static.hasNext)
    throw toJs(new NoSuchElementException);
  if (!this$static.valid)
    throw toJs(new GdxRuntimeException('#iterator() cannot be used nested.'));
  key = this$static.nextIndex == -1?0:this$static.set_0.keyTable[this$static.nextIndex];
  $findNextIndex_0(this$static);
  return key;
}

function $reset_1(this$static){
  this$static.nextIndex = -1;
  this$static.set_0.hasZeroValue?(this$static.hasNext = true):$findNextIndex_0(this$static);
}

function IntSet$IntSetIterator(set_0){
  this.set_0 = set_0;
  this.nextIndex = -1;
  this.set_0.hasZeroValue?(this.hasNext = true):$findNextIndex_0(this);
}

defineClass(181, 1, {}, IntSet$IntSetIterator);
_.hasNext = false;
_.nextIndex = 0;
_.valid = true;
var Lcom_badlogic_gdx_utils_IntSet$IntSetIterator_2_classLit = createForClass('com.badlogic.gdx.utils', 'IntSet/IntSetIterator', 181);
function floatToIntBits(value_0){
  return $clinit_Numbers() , wfa[0] = value_0 , undefined , wia[0];
}

function floatToRawIntBits(value_0){
  return $clinit_Numbers() , wfa[0] = value_0 , undefined , wia[0];
}

function $entries_0(this$static){
  if (allocateIterators)
    return new ObjectIntMap$Entries(this$static);
  if (!this$static.entries1) {
    this$static.entries1 = new ObjectIntMap$Entries(this$static);
    this$static.entries2 = new ObjectIntMap$Entries(this$static);
  }
  if (!this$static.entries1.valid) {
    $reset_2(this$static.entries1);
    this$static.entries1.valid = true;
    this$static.entries2.valid = false;
    return this$static.entries1;
  }
  $reset_2(this$static.entries2);
  this$static.entries2.valid = true;
  this$static.entries1.valid = false;
  return this$static.entries2;
}

function $get_5(this$static, key, defaultValue){
  var i;
  i = $locateKey_1(this$static, key);
  return i < 0?defaultValue:this$static.valueTable[i];
}

function $locateKey_1(this$static, key){
  var i, keyTable, other;
  if (key == null)
    throw toJs(new IllegalArgumentException_0('key cannot be null.'));
  keyTable = this$static.keyTable;
  for (i = toInt_0(shru_0(mul_1(hashCode__I__devirtual$(key), $intern_22), this$static.shift_0));; i = i + 1 & this$static.mask) {
    other = keyTable[i];
    if (other == null)
      return -(i + 1);
    if (equals_Ljava_lang_Object__Z__devirtual$(other, key))
      return i;
  }
}

function $put_0(this$static, key, value_0){
  var i;
  i = $locateKey_1(this$static, key);
  if (i >= 0) {
    this$static.valueTable[i] = value_0;
    return;
  }
  i = -(i + 1);
  this$static.keyTable[i] = key;
  this$static.valueTable[i] = value_0;
  ++this$static.size_0 >= this$static.threshold && $resize_3(this$static, this$static.keyTable.length << 1);
}

function $putResize_0(this$static, key, value_0){
  var i, keyTable;
  keyTable = this$static.keyTable;
  for (i = toInt_0(shru_0(mul_1(hashCode__I__devirtual$(key), $intern_22), this$static.shift_0));; i = i + 1 & this$static.mask) {
    if (keyTable[i] == null) {
      keyTable[i] = key;
      this$static.valueTable[i] = value_0;
      return;
    }
  }
}

function $resize_3(this$static, newSize){
  var i, key, oldCapacity, oldKeyTable, oldValueTable;
  oldCapacity = this$static.keyTable.length;
  this$static.threshold = round_int(newSize * this$static.loadFactor);
  this$static.mask = newSize - 1;
  this$static.shift_0 = numberOfLeadingZeros_1(this$static.mask);
  oldKeyTable = this$static.keyTable;
  oldValueTable = this$static.valueTable;
  this$static.keyTable = initUnidimensionalArray(Ljava_lang_Object_2_classLit, $intern_9, 1, newSize, 5, 1);
  this$static.valueTable = initUnidimensionalArray(I_classLit, $intern_3, 5, newSize, 15, 1);
  if (this$static.size_0 > 0) {
    for (i = 0; i < oldCapacity; i++) {
      key = oldKeyTable[i];
      key != null && $putResize_0(this$static, key, oldValueTable[i]);
    }
  }
}

function $toString_1(this$static){
  var buffer, i, key, keyTable, valueTable;
  if (this$static.size_0 == 0)
    return '{}';
  buffer = new StringBuilder_0;
  buffer.string += '{';
  keyTable = this$static.keyTable;
  valueTable = this$static.valueTable;
  i = keyTable.length;
  while (i-- > 0) {
    key = keyTable[i];
    if (key == null)
      continue;
    buffer.string += '' + key;
    buffer.string += '=';
    buffer.string += valueTable[i];
    break;
  }
  while (i-- > 0) {
    key = keyTable[i];
    if (key == null)
      continue;
    buffer.string += ', ';
    buffer.string += '' + key;
    buffer.string += '=';
    buffer.string += valueTable[i];
  }
  buffer.string += '}';
  return buffer.string;
}

function ObjectIntMap(){
  var tableSize;
  this.loadFactor = $intern_23;
  tableSize = tableSize_0(51);
  this.threshold = round_int(tableSize * $intern_23);
  this.mask = tableSize - 1;
  this.shift_0 = numberOfLeadingZeros_1(fromInt(this.mask));
  this.keyTable = initUnidimensionalArray(Ljava_lang_Object_2_classLit, $intern_9, 1, tableSize, 5, 1);
  this.valueTable = initUnidimensionalArray(I_classLit, $intern_3, 5, tableSize, 15, 1);
}

defineClass(79, 1, {79:1}, ObjectIntMap);
_.iterator = function iterator_8(){
  return $entries_0(this);
}
;
_.equals_0 = function equals_13(obj){
  var i, i0, key, keyTable, n, other, otherValue, valueTable;
  if (obj === this)
    return true;
  if (!instanceOf(obj, 79))
    return false;
  other = castTo(obj, 79);
  if (other.size_0 != this.size_0)
    return false;
  keyTable = this.keyTable;
  valueTable = this.valueTable;
  for (i0 = 0 , n = keyTable.length; i0 < n; i0++) {
    key = keyTable[i0];
    if (key != null) {
      otherValue = (i = $locateKey_1(other, key) , i < 0?0:other.valueTable[i]);
      if (otherValue == 0 && $locateKey_1(other, key) < 0)
        return false;
      if (otherValue != valueTable[i0])
        return false;
    }
  }
  return true;
}
;
_.hashCode_0 = function hashCode_14(){
  var h, i, key, keyTable, n, valueTable;
  h = this.size_0;
  keyTable = this.keyTable;
  valueTable = this.valueTable;
  for (i = 0 , n = keyTable.length; i < n; i++) {
    key = keyTable[i];
    key != null && (h += hashCode__I__devirtual$(key) + valueTable[i]);
  }
  return h;
}
;
_.toString_0 = function toString_21(){
  return $toString_1(this);
}
;
_.loadFactor = 0;
_.mask = 0;
_.shift_0 = 0;
_.size_0 = 0;
_.threshold = 0;
var Lcom_badlogic_gdx_utils_ObjectIntMap_2_classLit = createForClass('com.badlogic.gdx.utils', 'ObjectIntMap', 79);
function $findNextIndex_1(this$static){
  var keyTable, n;
  keyTable = this$static.map_0.keyTable;
  for (n = keyTable.length; ++this$static.nextIndex < n;) {
    if (keyTable[this$static.nextIndex] != null) {
      this$static.hasNext = true;
      return;
    }
  }
  this$static.hasNext = false;
}

function $reset_2(this$static){
  this$static.nextIndex = -1;
  $findNextIndex_1(this$static);
}

defineClass(452, 1, {});
_.hasNext = false;
_.nextIndex = 0;
_.valid = true;
var Lcom_badlogic_gdx_utils_ObjectIntMap$MapIterator_2_classLit = createForClass('com.badlogic.gdx.utils', 'ObjectIntMap/MapIterator', 452);
function $next_2(this$static){
  var keyTable;
  if (!this$static.hasNext)
    throw toJs(new NoSuchElementException);
  if (!this$static.valid)
    throw toJs(new GdxRuntimeException('#iterator() cannot be used nested.'));
  keyTable = this$static.map_0.keyTable;
  this$static.entry.key = keyTable[this$static.nextIndex];
  this$static.entry.value_0 = this$static.map_0.valueTable[this$static.nextIndex];
  $findNextIndex_1(this$static);
  return this$static.entry;
}

function ObjectIntMap$Entries(map_0){
  this.map_0 = map_0;
  this.nextIndex = -1;
  $findNextIndex_1(this);
  this.entry = new ObjectIntMap$Entry;
}

defineClass(192, 452, {}, ObjectIntMap$Entries);
_.iterator = function iterator_9(){
  return this;
}
;
_.next_1 = function next_3(){
  return $next_2(this);
}
;
_.hasNext_0 = function hasNext_2(){
  if (!this.valid)
    throw toJs(new GdxRuntimeException('#iterator() cannot be used nested.'));
  return this.hasNext;
}
;
var Lcom_badlogic_gdx_utils_ObjectIntMap$Entries_2_classLit = createForClass('com.badlogic.gdx.utils', 'ObjectIntMap/Entries', 192);
function ObjectIntMap$Entry(){
}

defineClass(451, 1, {}, ObjectIntMap$Entry);
_.toString_0 = function toString_22(){
  return this.key + '=' + this.value_0;
}
;
_.value_0 = 0;
var Lcom_badlogic_gdx_utils_ObjectIntMap$Entry_2_classLit = createForClass('com.badlogic.gdx.utils', 'ObjectIntMap/Entry', 451);
function $clinit_ObjectMap(){
  $clinit_ObjectMap = emptyMethod;
  dummy = new Object_0;
}

function $entries_1(this$static){
  if (allocateIterators)
    return new ObjectMap$Entries(this$static);
  if (!this$static.entries1) {
    this$static.entries1 = new ObjectMap$Entries(this$static);
    this$static.entries2 = new ObjectMap$Entries(this$static);
  }
  if (!this$static.entries1.valid) {
    $reset_3(this$static.entries1);
    this$static.entries1.valid = true;
    this$static.entries2.valid = false;
    return this$static.entries1;
  }
  $reset_3(this$static.entries2);
  this$static.entries2.valid = true;
  this$static.entries1.valid = false;
  return this$static.entries2;
}

function $get_6(this$static, key){
  var i;
  i = $locateKey_2(this$static, key);
  return i < 0?null:this$static.valueTable[i];
}

function $get_7(this$static, key, defaultValue){
  var i;
  i = $locateKey_2(this$static, key);
  return i < 0?defaultValue:this$static.valueTable[i];
}

function $locateKey_2(this$static, key){
  var i, keyTable, other;
  if (key == null)
    throw toJs(new IllegalArgumentException_0('key cannot be null.'));
  keyTable = this$static.keyTable;
  for (i = toInt_0(shru_0(mul_1(hashCode__I__devirtual$(key), $intern_22), this$static.shift_0));; i = i + 1 & this$static.mask) {
    other = keyTable[i];
    if (other == null)
      return -(i + 1);
    if (equals_Ljava_lang_Object__Z__devirtual$(other, key))
      return i;
  }
}

function $put_1(this$static, key, value_0){
  var i, oldValue;
  i = $locateKey_2(this$static, key);
  if (i >= 0) {
    oldValue = this$static.valueTable[i];
    this$static.valueTable[i] = value_0;
    return oldValue;
  }
  i = -(i + 1);
  this$static.keyTable[i] = key;
  this$static.valueTable[i] = value_0;
  ++this$static.size_0 >= this$static.threshold && $resize_4(this$static, this$static.keyTable.length << 1);
  return null;
}

function $putResize_1(this$static, key, value_0){
  var i, keyTable;
  keyTable = this$static.keyTable;
  for (i = toInt_0(shru_0(mul_1(hashCode__I__devirtual$(key), $intern_22), this$static.shift_0));; i = i + 1 & this$static.mask) {
    if (keyTable[i] == null) {
      keyTable[i] = key;
      this$static.valueTable[i] = value_0;
      return;
    }
  }
}

function $resize_4(this$static, newSize){
  var i, key, oldCapacity, oldKeyTable, oldValueTable;
  oldCapacity = this$static.keyTable.length;
  this$static.threshold = round_int(newSize * this$static.loadFactor);
  this$static.mask = newSize - 1;
  this$static.shift_0 = numberOfLeadingZeros_1(this$static.mask);
  oldKeyTable = this$static.keyTable;
  oldValueTable = this$static.valueTable;
  this$static.keyTable = initUnidimensionalArray(Ljava_lang_Object_2_classLit, $intern_9, 1, newSize, 5, 1);
  this$static.valueTable = initUnidimensionalArray(Ljava_lang_Object_2_classLit, $intern_9, 1, newSize, 5, 1);
  if (this$static.size_0 > 0) {
    for (i = 0; i < oldCapacity; i++) {
      key = oldKeyTable[i];
      key != null && $putResize_1(this$static, key, oldValueTable[i]);
    }
  }
}

function $toString_2(this$static){
  var buffer, i, key, keyTable, value_0, valueTable;
  if (this$static.size_0 == 0)
    return '{}';
  buffer = new StringBuilder_0;
  buffer.string += '{';
  keyTable = this$static.keyTable;
  valueTable = this$static.valueTable;
  i = keyTable.length;
  while (i-- > 0) {
    key = keyTable[i];
    if (key == null)
      continue;
    buffer.string += '' + (key === this$static?'(this)':key);
    buffer.string += '=';
    value_0 = valueTable[i];
    buffer.string += '' + (value_0 === this$static?'(this)':value_0);
    break;
  }
  while (i-- > 0) {
    key = keyTable[i];
    if (key == null)
      continue;
    buffer.string += ', ';
    buffer.string += '' + (key === this$static?'(this)':key);
    buffer.string += '=';
    value_0 = valueTable[i];
    buffer.string += '' + (value_0 === this$static?'(this)':value_0);
  }
  buffer.string += '}';
  return buffer.string;
}

function ObjectMap(){
  var tableSize;
  $clinit_ObjectMap();
  this.loadFactor = $intern_23;
  tableSize = tableSize_0(51);
  this.threshold = round_int(tableSize * $intern_23);
  this.mask = tableSize - 1;
  this.shift_0 = numberOfLeadingZeros_1(fromInt(this.mask));
  this.keyTable = initUnidimensionalArray(Ljava_lang_Object_2_classLit, $intern_9, 1, tableSize, 5, 1);
  this.valueTable = initUnidimensionalArray(Ljava_lang_Object_2_classLit, $intern_9, 1, tableSize, 5, 1);
}

defineClass(55, 1, {55:1}, ObjectMap);
_.iterator = function iterator_10(){
  return $entries_1(this);
}
;
_.equals_0 = function equals_14(obj){
  var i, i0, key, keyTable, n, other, value_0, valueTable;
  if (obj === this)
    return true;
  if (!instanceOf(obj, 55))
    return false;
  other = castTo(obj, 55);
  if (other.size_0 != this.size_0)
    return false;
  keyTable = this.keyTable;
  valueTable = this.valueTable;
  for (i0 = 0 , n = keyTable.length; i0 < n; i0++) {
    key = keyTable[i0];
    if (key != null) {
      value_0 = valueTable[i0];
      if (value_0 == null) {
        if ($get_7(other, key, dummy) != null)
          return false;
      }
       else {
        if (!equals_Ljava_lang_Object__Z__devirtual$(value_0, (i = $locateKey_2(other, key) , i < 0?null:other.valueTable[i])))
          return false;
      }
    }
  }
  return true;
}
;
_.hashCode_0 = function hashCode_15(){
  var h, i, key, keyTable, n, value_0, valueTable;
  h = this.size_0;
  keyTable = this.keyTable;
  valueTable = this.valueTable;
  for (i = 0 , n = keyTable.length; i < n; i++) {
    key = keyTable[i];
    if (key != null) {
      h += hashCode__I__devirtual$(key);
      value_0 = valueTable[i];
      value_0 != null && (h += hashCode__I__devirtual$(value_0));
    }
  }
  return h;
}
;
_.toString_0 = function toString_23(){
  return $toString_2(this);
}
;
_.loadFactor = 0;
_.mask = 0;
_.shift_0 = 0;
_.size_0 = 0;
_.threshold = 0;
var dummy;
var Lcom_badlogic_gdx_utils_ObjectMap_2_classLit = createForClass('com.badlogic.gdx.utils', 'ObjectMap', 55);
function $findNextIndex_2(this$static){
  var keyTable, n;
  keyTable = this$static.map_0.keyTable;
  for (n = keyTable.length; ++this$static.nextIndex < n;) {
    if (keyTable[this$static.nextIndex] != null) {
      this$static.hasNext = true;
      return;
    }
  }
  this$static.hasNext = false;
}

function $reset_3(this$static){
  this$static.nextIndex = -1;
  $findNextIndex_2(this$static);
}

defineClass(328, 1, {});
_.hasNext = false;
_.nextIndex = 0;
_.valid = true;
var Lcom_badlogic_gdx_utils_ObjectMap$MapIterator_2_classLit = createForClass('com.badlogic.gdx.utils', 'ObjectMap/MapIterator', 328);
function $next_3(this$static){
  var keyTable;
  if (!this$static.hasNext)
    throw toJs(new NoSuchElementException);
  if (!this$static.valid)
    throw toJs(new GdxRuntimeException('#iterator() cannot be used nested.'));
  keyTable = this$static.map_0.keyTable;
  this$static.entry.key = keyTable[this$static.nextIndex];
  this$static.entry.value_0 = this$static.map_0.valueTable[this$static.nextIndex];
  $findNextIndex_2(this$static);
  return this$static.entry;
}

function ObjectMap$Entries(map_0){
  this.map_0 = map_0;
  this.nextIndex = -1;
  $findNextIndex_2(this);
  this.entry = new ObjectMap$Entry;
}

defineClass(170, 328, {}, ObjectMap$Entries);
_.iterator = function iterator_11(){
  return this;
}
;
_.next_1 = function next_4(){
  return $next_3(this);
}
;
_.hasNext_0 = function hasNext_3(){
  if (!this.valid)
    throw toJs(new GdxRuntimeException('#iterator() cannot be used nested.'));
  return this.hasNext;
}
;
var Lcom_badlogic_gdx_utils_ObjectMap$Entries_2_classLit = createForClass('com.badlogic.gdx.utils', 'ObjectMap/Entries', 170);
function ObjectMap$Entry(){
}

defineClass(327, 1, {}, ObjectMap$Entry);
_.toString_0 = function toString_24(){
  return this.key + '=' + this.value_0;
}
;
var Lcom_badlogic_gdx_utils_ObjectMap$Entry_2_classLit = createForClass('com.badlogic.gdx.utils', 'ObjectMap/Entry', 327);
function tableSize_0(capacity){
  var tableSize;
  if (capacity < 0)
    throw toJs(new IllegalArgumentException_0('capacity must be >= 0: ' + capacity));
  tableSize = nextPowerOfTwo($wnd.Math.max(2, round_int($wnd.Math.ceil(capacity / $intern_23))));
  if (tableSize > 1073741824)
    throw toJs(new IllegalArgumentException_0('The required capacity is too large: ' + capacity));
  return tableSize;
}

function $clinit_Scaling(){
  $clinit_Scaling = emptyMethod;
  temp_0 = new Vector2;
}

var temp_0;
function $apply(sourceWidth, sourceHeight, targetWidth, targetHeight){
  var scale, sourceRatio, targetRatio;
  targetRatio = targetHeight / targetWidth;
  sourceRatio = sourceHeight / sourceWidth;
  scale = targetRatio > sourceRatio?targetWidth / sourceWidth:targetHeight / sourceHeight;
  temp_0.x_0 = sourceWidth * scale;
  temp_0.y_0 = sourceHeight * scale;
  return temp_0;
}

function $clinit_Utf8Decoder(){
  $clinit_Utf8Decoder = emptyMethod;
  BYTE_TABLE = stampJavaTypeInfo(getClassLiteralForArray(B_classLit, 1), $intern_6, 5, 15, [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 10, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 3, 3, 11, 6, 6, 6, 5, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8]);
  TRANSITION_TABLE = stampJavaTypeInfo(getClassLiteralForArray(B_classLit, 1), $intern_6, 5, 15, [0, 12, 24, 36, 60, 96, 84, 12, 12, 12, 48, 72, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 0, 12, 12, 12, 12, 12, 0, 12, 0, 12, 12, 12, 24, 12, 12, 12, 12, 12, 24, 12, 24, 12, 12, 12, 12, 12, 12, 12, 12, 12, 24, 12, 12, 12, 12, 12, 24, 12, 12, 12, 12, 12, 12, 12, 24, 12, 12, 12, 12, 12, 12, 12, 12, 12, 36, 12, 36, 12, 12, 12, 36, 12, 12, 12, 12, 12, 36, 12, 36, 12, 12, 12, 36, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12]);
}

function $decode(this$static, b){
  var codePointLength, i, next, type_0;
  if (b > 0 && this$static.state_0 == 0) {
    this$static.charBuffer[this$static.charOffset++] = b & 255 & $intern_5;
  }
   else {
    i = b & 255;
    type_0 = BYTE_TABLE[i];
    this$static.codePoint = this$static.state_0 == 0?255 >> type_0 & i:i & 63 | this$static.codePoint << 6;
    next = TRANSITION_TABLE[this$static.state_0 + type_0];
    switch (next) {
      case 0:
        this$static.state_0 = next;
        if (this$static.codePoint < 55296) {
          this$static.charBuffer[this$static.charOffset++] = this$static.codePoint & $intern_5;
        }
         else {
          codePointLength = toChars(this$static.codePoint, this$static.utf16Char, 0);
          this$static.charBuffer[this$static.charOffset++] = this$static.utf16Char[0];
          codePointLength == 2 && (this$static.charBuffer[this$static.charOffset++] = this$static.utf16Char[1]);
        }

        break;
      case 12:
        this$static.codePoint = 0;
        this$static.state_0 = 0;
        this$static.charBuffer[this$static.charOffset++] = 65533;
        break;
      default:this$static.state_0 = next;
    }
  }
}

function $decode_0(this$static, b, length_0, charBuffer, charOffset){
  var end, i;
  this$static.charBuffer = charBuffer;
  this$static.charOffset = charOffset;
  end = length_0;
  for (i = 0; i < end; i++)
    $decode(this$static, b[i]);
  return this$static.charOffset - charOffset;
}

function Utf8Decoder(){
  $clinit_Utf8Decoder();
  this.utf16Char = initUnidimensionalArray(C_classLit, $intern_2, 5, 2, 15, 1);
  this.state_0 = 0;
}

defineClass(484, 1, {}, Utf8Decoder);
_.charOffset = 0;
_.codePoint = 0;
_.state_0 = 0;
var BYTE_TABLE, TRANSITION_TABLE;
var Lcom_badlogic_gdx_utils_Utf8Decoder_2_classLit = createForClass('com.badlogic.gdx.utils', 'Utf8Decoder', 484);
function $apply_0(this$static, centerCamera){
  glViewport(this$static.screenX_0, this$static.screenY_0, this$static.screenWidth, this$static.screenHeight);
  this$static.camera.viewportWidth = this$static.worldWidth;
  this$static.camera.viewportHeight = this$static.worldHeight;
  centerCamera && $set_5(this$static.camera.position, this$static.worldWidth / 2, this$static.worldHeight / 2, 0);
  $update_2(this$static.camera);
}

defineClass(411, 1, {});
_.screenHeight = 0;
_.screenWidth = 0;
_.screenX_0 = 0;
_.screenY_0 = 0;
_.worldHeight = 0;
_.worldWidth = 0;
var Lcom_badlogic_gdx_utils_viewport_Viewport_2_classLit = createForClass('com.badlogic.gdx.utils.viewport', 'Viewport', 411);
function $update_4(this$static, screenWidth, screenHeight){
  var scaled, viewportHeight, viewportWidth;
  scaled = $apply(this$static.worldWidth, this$static.worldHeight, screenWidth, screenHeight);
  viewportWidth = round_int($wnd.Math.round(scaled.x_0));
  viewportHeight = round_int($wnd.Math.round(scaled.y_0));
  this$static.screenX_0 = (screenWidth - viewportWidth) / 2 | 0;
  this$static.screenY_0 = (screenHeight - viewportHeight) / 2 | 0;
  this$static.screenWidth = viewportWidth;
  this$static.screenHeight = viewportHeight;
  $apply_0(this$static, true);
}

function ScalingViewport(camera){
  $clinit_Vector3();
  this.worldWidth = 320;
  this.worldHeight = 240;
  this.camera = camera;
}

defineClass(412, 411, {});
var Lcom_badlogic_gdx_utils_viewport_ScalingViewport_2_classLit = createForClass('com.badlogic.gdx.utils.viewport', 'ScalingViewport', 412);
function FitViewport(camera){
  ScalingViewport.call(this, ($clinit_Scaling() , camera));
}

defineClass(413, 412, {}, FitViewport);
var Lcom_badlogic_gdx_utils_viewport_FitViewport_2_classLit = createForClass('com.badlogic.gdx.utils.viewport', 'FitViewport', 413);
defineClass(514, 1, {});
var instance;
var Lcom_google_gwt_animation_client_AnimationScheduler_2_classLit = createForClass('com.google.gwt.animation.client', 'AnimationScheduler', 514);
defineClass(164, 1, {164:1});
var Lcom_google_gwt_animation_client_AnimationScheduler$AnimationHandle_2_classLit = createForClass('com.google.gwt.animation.client', 'AnimationScheduler/AnimationHandle', 164);
function $isNativelySupported(){
  return !!$wnd.requestAnimationFrame && !!$wnd.cancelAnimationFrame;
}

function AnimationSchedulerImplStandard(){
}

function requestImpl(cb, element){
  var callback = $entry(function(){
    var time = Date.now();
    cb.execute(time);
  }
  );
  var handle = $wnd.requestAnimationFrame(callback, element);
  return {id:handle};
}

defineClass(213, 514, {}, AnimationSchedulerImplStandard);
_.requestAnimationFrame_0 = function requestAnimationFrame_0(callback, element){
  requestImpl(callback, element);
  return new AnimationSchedulerImplStandard$1;
}
;
var Lcom_google_gwt_animation_client_AnimationSchedulerImplStandard_2_classLit = createForClass('com.google.gwt.animation.client', 'AnimationSchedulerImplStandard', 213);
function AnimationSchedulerImplStandard$1(){
}

defineClass(424, 164, {164:1}, AnimationSchedulerImplStandard$1);
var Lcom_google_gwt_animation_client_AnimationSchedulerImplStandard$1_2_classLit = createForClass('com.google.gwt.animation.client', 'AnimationSchedulerImplStandard/1', 424);
function $updateAnimations(this$static){
  var curAnimations, duration, requestId, requestId$array, requestId$index, requestId$max;
  curAnimations = initUnidimensionalArray(Lcom_google_gwt_animation_client_AnimationSchedulerImplTimer$AnimationHandleImpl_2_classLit, {556:1, 3:1, 4:1}, 179, this$static.animationRequests.array.length, 0, 1);
  curAnimations = castTo($toArray_0(this$static.animationRequests, curAnimations), 556);
  duration = new Duration;
  for (requestId$array = curAnimations , requestId$index = 0 , requestId$max = requestId$array.length; requestId$index < requestId$max; ++requestId$index) {
    requestId = requestId$array[requestId$index];
    $remove_7(this$static.animationRequests, requestId);
    $execute(requestId.callback);
  }
  this$static.animationRequests.array.length > 0 && $schedule(this$static.timer, $wnd.Math.max(5, 16 - (Date.now() - duration.start_0)));
}

function AnimationSchedulerImplTimer(){
  this.animationRequests = new ArrayList;
  this.timer = new AnimationSchedulerImplTimer$1(this);
}

defineClass(214, 514, {}, AnimationSchedulerImplTimer);
_.requestAnimationFrame_0 = function requestAnimationFrame_1(callback, element){
  var requestId;
  requestId = new AnimationSchedulerImplTimer$AnimationHandleImpl(callback);
  $add_8(this.animationRequests, requestId);
  this.animationRequests.array.length == 1 && $schedule(this.timer, 16);
  return requestId;
}
;
var Lcom_google_gwt_animation_client_AnimationSchedulerImplTimer_2_classLit = createForClass('com.google.gwt.animation.client', 'AnimationSchedulerImplTimer', 214);
function $cancel(this$static){
  if (!this$static.timerId) {
    return;
  }
  ++this$static.cancelCounter;
  this$static.isRepeating?clearInterval_0(this$static.timerId.value_0):clearTimeout_0(this$static.timerId.value_0);
  this$static.timerId = null;
}

function $schedule(this$static, delayMillis){
  if (delayMillis < 0) {
    throw toJs(new IllegalArgumentException_0('must be non-negative'));
  }
  !!this$static.timerId && $cancel(this$static);
  this$static.isRepeating = false;
  this$static.timerId = valueOf_1(setTimeout_0(createCallback(this$static, this$static.cancelCounter), delayMillis));
}

function clearInterval_0(timerId){
  $wnd.clearInterval(timerId);
}

function clearTimeout_0(timerId){
  $wnd.clearTimeout(timerId);
}

function createCallback(timer, cancelCounter){
  return $entry(function(){
    timer.fire_0(cancelCounter);
  }
  );
}

function setTimeout_0(func, time){
  return $wnd.setTimeout(func, time);
}

defineClass(406, 1, {});
_.fire_0 = function fire(scheduleCancelCounter){
  if (scheduleCancelCounter != this.cancelCounter) {
    return;
  }
  this.isRepeating || (this.timerId = null);
  $updateAnimations(this.this$01);
}
;
_.cancelCounter = 0;
_.isRepeating = false;
_.timerId = null;
var Lcom_google_gwt_user_client_Timer_2_classLit = createForClass('com.google.gwt.user.client', 'Timer', 406);
function AnimationSchedulerImplTimer$1(this$0){
  this.this$01 = this$0;
}

defineClass(425, 406, {}, AnimationSchedulerImplTimer$1);
var Lcom_google_gwt_animation_client_AnimationSchedulerImplTimer$1_2_classLit = createForClass('com.google.gwt.animation.client', 'AnimationSchedulerImplTimer/1', 425);
function AnimationSchedulerImplTimer$AnimationHandleImpl(callback){
  this.callback = callback;
}

defineClass(179, 164, {164:1, 179:1}, AnimationSchedulerImplTimer$AnimationHandleImpl);
var Lcom_google_gwt_animation_client_AnimationSchedulerImplTimer$AnimationHandleImpl_2_classLit = createForClass('com.google.gwt.animation.client', 'AnimationSchedulerImplTimer/AnimationHandleImpl', 179);
function $getElement(this$static){
  return $clinit_DOM() , this$static.element;
}

function $replaceNode(node, newNode){
  var p = node.parentNode;
  if (!p) {
    return;
  }
  p.insertBefore(newNode, node);
  p.removeChild(node);
}

function $setElement(this$static, elem){
  $setElement_0(this$static, ($clinit_DOM() , elem));
}

function $setElement_0(this$static, elem){
  this$static.element = elem;
}

function $setHeight(this$static, height){
  ($clinit_DOM() , this$static.element).style['height'] = height;
}

function $setWidth(this$static, width_0){
  ($clinit_DOM() , this$static.element).style['width'] = width_0;
}

function setStyleName(elem, style){
  if (!elem) {
    throw toJs(new RuntimeException_0('Null widget handle. If you are creating a composite, ensure that initWidget() has been called.'));
  }
  style = $trim(style);
  if (style.length == 0) {
    throw toJs(new IllegalArgumentException_0('Style names cannot be empty'));
  }
  $addClassName(elem, style);
}

defineClass(46, 1, {53:1, 46:1});
_.toString_0 = function toString_25(){
  if (!this.element) {
    return '(null handle)';
  }
  return $toString_3(($clinit_DOM() , this.element));
}
;
var Lcom_google_gwt_user_client_ui_UIObject_2_classLit = createForClass('com.google.gwt.user.client.ui', 'UIObject', 46);
function $onAttach(this$static){
  var bitsToAdd;
  if (this$static.attached) {
    throw toJs(new IllegalStateException_0("Should only call onAttach when the widget is detached from the browser's document"));
  }
  this$static.attached = true;
  $clinit_DOM();
  setEventListener(this$static.element, this$static);
  bitsToAdd = this$static.eventsToSink;
  this$static.eventsToSink = -1;
  bitsToAdd > 0 && (this$static.eventsToSink == -1?$sinkEvents(this$static.element, bitsToAdd | (this$static.element.__eventBits || 0)):(this$static.eventsToSink |= bitsToAdd));
  this$static.doAttachChildren();
  this$static.onLoad();
}

function $onBrowserEvent(this$static, event_0){
  var related;
  switch ($clinit_DOM() , $eventGetTypeInt(event_0.type)) {
    case 16:
    case 32:
      related = $eventGetRelatedTarget(event_0);
      if (!!related && $isOrHasChild(this$static.element, related)) {
        return;
      }

  }
}

function $onDetach(this$static){
  if (!this$static.attached) {
    throw toJs(new IllegalStateException_0("Should only call onDetach when the widget is attached to the browser's document"));
  }
  try {
    this$static.doDetachChildren();
  }
   finally {
    $clinit_DOM();
    this$static.element.__listener = null;
    this$static.attached = false;
  }
}

function $removeFromParent(this$static){
  if (!this$static.parent_0) {
    $clinit_RootPanel();
    $contains_4(widgetsToDetach, this$static) && detachNow(this$static);
  }
   else if (this$static.parent_0) {
    this$static.parent_0.remove(this$static);
  }
   else if (this$static.parent_0) {
    throw toJs(new IllegalStateException_0("This widget's parent does not implement HasWidgets"));
  }
}

function $replaceElement(this$static, elem){
  this$static.attached && ($clinit_DOM() , this$static.element.__listener = null , undefined);
  !!this$static.element && $replaceNode(this$static.element, elem);
  this$static.element = elem;
  this$static.attached && ($clinit_DOM() , setEventListener(this$static.element, this$static));
}

function $setParent(this$static, parent_0){
  var oldParent;
  oldParent = this$static.parent_0;
  if (!parent_0) {
    try {
      !!oldParent && oldParent.attached && $onDetach(this$static);
    }
     finally {
      this$static.parent_0 = null;
    }
  }
   else {
    if (oldParent) {
      throw toJs(new IllegalStateException_0('Cannot set a new parent without first clearing the old parent'));
    }
    this$static.parent_0 = parent_0;
    parent_0.attached && this$static.onAttach();
  }
}

defineClass(39, 46, $intern_24);
_.doAttachChildren = function doAttachChildren(){
}
;
_.doDetachChildren = function doDetachChildren(){
}
;
_.onAttach = function onAttach(){
  $onAttach(this);
}
;
_.onBrowserEvent = function onBrowserEvent(event_0){
  $onBrowserEvent(this, event_0);
}
;
_.onLoad = function onLoad(){
}
;
_.attached = false;
_.eventsToSink = 0;
var Lcom_google_gwt_user_client_ui_Widget_2_classLit = createForClass('com.google.gwt.user.client.ui', 'Widget', 39);
function $clinit_FocusWidget(){
  $clinit_FocusWidget = emptyMethod;
  $clinit_FocusImpl();
}

defineClass(542, 39, $intern_24);
_.onAttach = function onAttach_0(){
  var tabIndex;
  $onAttach(this);
  tabIndex = ($clinit_DOM() , this.element).tabIndex;
  -1 == tabIndex && (this.element.tabIndex = 0 , undefined);
}
;
var Lcom_google_gwt_user_client_ui_FocusWidget_2_classLit = createForClass('com.google.gwt.user.client.ui', 'FocusWidget', 542);
function $getCanvasElement(this$static){
  return $clinit_DOM() , this$static.element;
}

function $getContext2d(this$static){
  return ($clinit_DOM() , this$static.element).getContext('2d');
}

function Canvas_0(element){
  $setElement_0(this, ($clinit_DOM() , element));
}

function createIfSupported(){
  $clinit_FocusWidget();
  var element;
  !detector && (detector = new Canvas$CanvasElementSupportDetectedMaybe);
  element = $doc.createElement('canvas');
  if (!element.getContext) {
    return null;
  }
  return new Canvas_0(element);
}

defineClass(402, 542, $intern_24, Canvas_0);
var detector;
var Lcom_google_gwt_canvas_client_Canvas_2_classLit = createForClass('com.google.gwt.canvas.client', 'Canvas', 402);
defineClass(543, 1, {});
var Lcom_google_gwt_canvas_client_Canvas$CanvasElementSupportDetector_2_classLit = createForClass('com.google.gwt.canvas.client', 'Canvas/CanvasElementSupportDetector', 543);
function Canvas$CanvasElementSupportDetectedMaybe(){
}

defineClass(403, 543, {}, Canvas$CanvasElementSupportDetectedMaybe);
var Lcom_google_gwt_canvas_client_Canvas$CanvasElementSupportDetectedMaybe_2_classLit = createForClass('com.google.gwt.canvas.client', 'Canvas/CanvasElementSupportDetectedMaybe', 403);
function $drawImage(this$static, image, sx, sy, sw, sh, dx, dy, dw, dh){
  this$static.drawImage(image, sx, sy, sw, sh, dx, dy, dw, dh);
}

function $rect_0(this$static, x_0, y_0, w, h){
  this$static.rect(x_0, y_0, w, h);
}

function $setFillStyleWeb(this$static, fillStyle){
  this$static.fillStyle = fillStyle;
}

function $setGlobalCompositeOperation(this$static, globalCompositeOperation){
  this$static.globalCompositeOperation = globalCompositeOperation;
}

function $setStrokeStyleWeb(this$static, strokeStyle){
  this$static.strokeStyle = strokeStyle;
}

function $clinit_Context2d$Composite(){
  $clinit_Context2d$Composite = emptyMethod;
  COPY = new Context2d$Composite('COPY', 0, 'copy');
  DESTINATION_ATOP = new Context2d$Composite('DESTINATION_ATOP', 1, 'destination-atop');
  DESTINATION_IN = new Context2d$Composite('DESTINATION_IN', 2, 'destination-in');
  DESTINATION_OUT = new Context2d$Composite('DESTINATION_OUT', 3, 'destination-out');
  DESTINATION_OVER = new Context2d$Composite('DESTINATION_OVER', 4, 'destination-over');
  LIGHTER = new Context2d$Composite('LIGHTER', 5, 'lighter');
  SOURCE_ATOP = new Context2d$Composite('SOURCE_ATOP', 6, 'source-atop');
  SOURCE_IN = new Context2d$Composite('SOURCE_IN', 7, 'source-in');
  SOURCE_OUT = new Context2d$Composite('SOURCE_OUT', 8, 'source-out');
  SOURCE_OVER = new Context2d$Composite('SOURCE_OVER', 9, 'source-over');
  XOR = new Context2d$Composite('XOR', 10, 'xor');
}

function Context2d$Composite(enum$name, enum$ordinal, value_0){
  Enum.call(this, enum$name, enum$ordinal);
  this.value_0 = value_0;
}

function values_7(){
  $clinit_Context2d$Composite();
  return stampJavaTypeInfo(getClassLiteralForArray(Lcom_google_gwt_canvas_dom_client_Context2d$Composite_2_classLit, 1), $intern_1, 66, 0, [COPY, DESTINATION_ATOP, DESTINATION_IN, DESTINATION_OUT, DESTINATION_OVER, LIGHTER, SOURCE_ATOP, SOURCE_IN, SOURCE_OUT, SOURCE_OVER, XOR]);
}

defineClass(66, 8, {66:1, 3:1, 7:1, 8:1}, Context2d$Composite);
var COPY, DESTINATION_ATOP, DESTINATION_IN, DESTINATION_OUT, DESTINATION_OVER, LIGHTER, SOURCE_ATOP, SOURCE_IN, SOURCE_OUT, SOURCE_OVER, XOR;
var Lcom_google_gwt_canvas_dom_client_Context2d$Composite_2_classLit = createForEnum('com.google.gwt.canvas.dom.client', 'Context2d/Composite', 66, values_7);
function Duration(){
  this.start_0 = Date.now();
}

defineClass(231, 1, {}, Duration);
_.start_0 = 0;
var Lcom_google_gwt_core_client_Duration_2_classLit = createForClass('com.google.gwt.core.client', 'Duration', 231);
defineClass(166, 27, $intern_21);
var Ljava_lang_JsException_2_classLit = createForClass('java.lang', 'JsException', 166);
defineClass(307, 166, $intern_21);
var Lcom_google_gwt_core_client_impl_JavaScriptExceptionBase_2_classLit = createForClass('com.google.gwt.core.client.impl', 'JavaScriptExceptionBase', 307);
function $clinit_JavaScriptException(){
  $clinit_JavaScriptException = emptyMethod;
  NOT_SET = new Object_0;
}

function $ensureInit(this$static){
  var exception;
  if (this$static.message_0 == null) {
    exception = maskUndefined(this$static.e) === maskUndefined(NOT_SET)?null:this$static.e;
    this$static.name_0 = exception == null?'null':instanceOfJso(exception)?getExceptionName0(castToJso(exception)):instanceOfString(exception)?'String':$getName(getClass__Ljava_lang_Class___devirtual$(exception));
    this$static.description = this$static.description + ': ' + (instanceOfJso(exception)?getExceptionDescription0(castToJso(exception)):exception + '');
    this$static.message_0 = '(' + this$static.name_0 + ') ' + this$static.description;
  }
}

function JavaScriptException(e){
  $clinit_JavaScriptException();
  $$init(this);
  $fillInStackTrace(this);
  this.backingJsObject = e;
  $linkBack(this, e);
  this.detailMessage = e == null?'null':toString_30(e);
  this.description = '';
  this.e = e;
  this.description = '';
}

function getExceptionDescription0(e){
  return e == null?null:e.message;
}

function getExceptionName0(e){
  return e == null?null:e.name;
}

defineClass(123, 307, {123:1, 3:1, 6:1, 9:1}, JavaScriptException);
_.getMessage = function getMessage_0(){
  $ensureInit(this);
  return this.message_0;
}
;
_.getThrown = function getThrown(){
  return maskUndefined(this.e) === maskUndefined(NOT_SET)?null:this.e;
}
;
var NOT_SET;
var Lcom_google_gwt_core_client_JavaScriptException_2_classLit = createForClass('com.google.gwt.core.client', 'JavaScriptException', 123);
defineClass(512, 1, {});
var Lcom_google_gwt_core_client_Scheduler_2_classLit = createForClass('com.google.gwt.core.client', 'Scheduler', 512);
function $clinit_Impl(){
  $clinit_Impl = emptyMethod;
  !!($clinit_StackTraceCreator() , collector);
}

function apply_0(jsFunction, thisObj, args){
  return jsFunction.apply(thisObj, args);
  var __0;
}

function enter_0(){
  var now_0;
  if (entryDepth != 0) {
    now_0 = Date.now();
    if (now_0 - watchdogEntryDepthLastScheduled > 2000) {
      watchdogEntryDepthLastScheduled = now_0;
      watchdogEntryDepthTimerId = $wnd.setTimeout(watchdogEntryDepthRun, 10);
    }
  }
  if (entryDepth++ == 0) {
    $flushEntryCommands(($clinit_SchedulerImpl() , INSTANCE));
    return true;
  }
  return false;
}

function entry_0(jsFunction){
  $clinit_Impl();
  return function(){
    return entry0(jsFunction, this, arguments);
    var __0;
  }
  ;
}

function entry0(jsFunction, thisObj, args){
  var initialEntry;
  initialEntry = enter_0();
  try {
    return apply_0(jsFunction, thisObj, args);
  }
   finally {
    exit(initialEntry);
  }
}

function exit(initialEntry){
  initialEntry && $flushFinallyCommands(($clinit_SchedulerImpl() , INSTANCE));
  --entryDepth;
  if (initialEntry) {
    if (watchdogEntryDepthTimerId != -1) {
      watchdogEntryDepthCancel(watchdogEntryDepthTimerId);
      watchdogEntryDepthTimerId = -1;
    }
  }
}

function getModuleBaseURL(){
  $clinit_Impl();
  var key = '__gwtDevModeHook:' + $moduleName + ':moduleBase';
  var global_0 = $wnd || self;
  return global_0[key] || $moduleBase;
}

function reportToBrowser(e){
  $clinit_Impl();
  $wnd.setTimeout(function(){
    throw e;
  }
  , 0);
}

function watchdogEntryDepthCancel(timerId){
  $wnd.clearTimeout(timerId);
}

function watchdogEntryDepthRun(){
  entryDepth != 0 && (entryDepth = 0);
  watchdogEntryDepthTimerId = -1;
}

var entryDepth = 0, watchdogEntryDepthLastScheduled = 0, watchdogEntryDepthTimerId = -1;
function $clinit_SchedulerImpl(){
  $clinit_SchedulerImpl = emptyMethod;
  INSTANCE = new SchedulerImpl;
}

function $flushEntryCommands(this$static){
  var oldQueue, rescheduled;
  if (this$static.entryCommands) {
    rescheduled = null;
    do {
      oldQueue = this$static.entryCommands;
      this$static.entryCommands = null;
      rescheduled = runScheduledTasks(oldQueue, rescheduled);
    }
     while (this$static.entryCommands);
    this$static.entryCommands = rescheduled;
  }
}

function $flushFinallyCommands(this$static){
  var oldQueue, rescheduled;
  if (this$static.finallyCommands) {
    rescheduled = null;
    do {
      oldQueue = this$static.finallyCommands;
      this$static.finallyCommands = null;
      rescheduled = runScheduledTasks(oldQueue, rescheduled);
    }
     while (this$static.finallyCommands);
    this$static.finallyCommands = rescheduled;
  }
}

function $flushPostEventPumpCommands(this$static){
  var oldDeferred;
  if (this$static.deferredCommands) {
    oldDeferred = this$static.deferredCommands;
    this$static.deferredCommands = null;
    !this$static.incrementalCommands && (this$static.incrementalCommands = []);
    runScheduledTasks(oldDeferred, this$static.incrementalCommands);
  }
  !!this$static.incrementalCommands && (this$static.incrementalCommands = $runRepeatingTasks(this$static.incrementalCommands));
}

function $isWorkQueued(this$static){
  return !!this$static.deferredCommands || !!this$static.incrementalCommands;
}

function $maybeSchedulePostEventPumpCommands(this$static){
  if (!this$static.shouldBeRunning) {
    this$static.shouldBeRunning = true;
    !this$static.flusher && (this$static.flusher = new SchedulerImpl$Flusher(this$static));
    scheduleFixedDelayImpl(this$static.flusher, 1);
    !this$static.rescue && (this$static.rescue = new SchedulerImpl$Rescuer(this$static));
    scheduleFixedDelayImpl(this$static.rescue, 50);
  }
}

function $runRepeatingTasks(tasks){
  var canceledSomeTasks, duration, executedSomeTask, i, length_0, newTasks, t;
  length_0 = tasks.length;
  if (length_0 == 0) {
    return null;
  }
  canceledSomeTasks = false;
  duration = new Duration;
  while (Date.now() - duration.start_0 < 16) {
    executedSomeTask = false;
    for (i = 0; i < length_0; i++) {
      t = tasks[i];
      if (!t) {
        continue;
      }
      executedSomeTask = true;
      if (!t[0].execute_0()) {
        tasks[i] = null;
        canceledSomeTasks = true;
      }
    }
    if (!executedSomeTask) {
      break;
    }
  }
  if (canceledSomeTasks) {
    newTasks = [];
    for (i = 0; i < length_0; i++) {
      !!tasks[i] && (newTasks[newTasks.length] = tasks[i] , undefined);
    }
    return newTasks.length == 0?null:newTasks;
  }
   else {
    return tasks;
  }
}

function $scheduleDeferred(this$static, cmd){
  this$static.deferredCommands = push_0(this$static.deferredCommands, [cmd, false]);
  $maybeSchedulePostEventPumpCommands(this$static);
}

function SchedulerImpl(){
}

function execute_0(cmd){
  return cmd.execute_0();
}

function push_0(queue, task){
  !queue && (queue = []);
  queue[queue.length] = task;
  return queue;
}

function runScheduledTasks(tasks, rescheduled){
  var e, i, j, t;
  for (i = 0 , j = tasks.length; i < j; i++) {
    t = tasks[i];
    try {
      t[1]?t[0].execute_0() && (rescheduled = push_0(rescheduled, t)):t[0].execute_1();
    }
     catch ($e0) {
      $e0 = toJava($e0);
      if (instanceOf($e0, 9)) {
        e = $e0;
        $clinit_Impl();
        reportToBrowser(instanceOf(e, 123)?castTo(e, 123).getThrown():e);
      }
       else 
        throw toJs($e0);
    }
  }
  return rescheduled;
}

function scheduleFixedDelayImpl(cmd, delayMs){
  $clinit_SchedulerImpl();
  function callback(){
    var ret = $entry(execute_0)(cmd);
    ret && $wnd.setTimeout(callback, delayMs);
  }

  $wnd.setTimeout(callback, delayMs);
}

defineClass(370, 512, {}, SchedulerImpl);
_.flushRunning = false;
_.shouldBeRunning = false;
var INSTANCE;
var Lcom_google_gwt_core_client_impl_SchedulerImpl_2_classLit = createForClass('com.google.gwt.core.client.impl', 'SchedulerImpl', 370);
function SchedulerImpl$Flusher(this$0){
  this.this$01 = this$0;
}

defineClass(371, 1, {}, SchedulerImpl$Flusher);
_.execute_0 = function execute_1(){
  this.this$01.flushRunning = true;
  $flushPostEventPumpCommands(this.this$01);
  this.this$01.flushRunning = false;
  return this.this$01.shouldBeRunning = $isWorkQueued(this.this$01);
}
;
var Lcom_google_gwt_core_client_impl_SchedulerImpl$Flusher_2_classLit = createForClass('com.google.gwt.core.client.impl', 'SchedulerImpl/Flusher', 371);
function SchedulerImpl$Rescuer(this$0){
  this.this$01 = this$0;
}

defineClass(372, 1, {}, SchedulerImpl$Rescuer);
_.execute_0 = function execute_2(){
  this.this$01.flushRunning && scheduleFixedDelayImpl(this.this$01.flusher, 1);
  return this.this$01.shouldBeRunning;
}
;
var Lcom_google_gwt_core_client_impl_SchedulerImpl$Rescuer_2_classLit = createForClass('com.google.gwt.core.client.impl', 'SchedulerImpl/Rescuer', 372);
function $clinit_StackTraceCreator(){
  $clinit_StackTraceCreator = emptyMethod;
  var c, enforceLegacy;
  enforceLegacy = !supportsErrorStack();
  c = new StackTraceCreator$CollectorModernNoSourceMap;
  collector = enforceLegacy?new StackTraceCreator$CollectorLegacy:c;
}

function captureStackTrace(error){
  $clinit_StackTraceCreator();
  collector.collect(error);
}

function constructJavaStackTrace(thrown){
  $clinit_StackTraceCreator();
  var stackTrace;
  stackTrace = collector.getStackTrace(thrown);
  return dropInternalFrames(stackTrace);
}

function dropInternalFrames(stackTrace){
  var dropFrameUntilFnName, dropFrameUntilFnName2, i, numberOfFramesToSearch;
  dropFrameUntilFnName = 'captureStackTrace';
  dropFrameUntilFnName2 = 'initializeBackingError';
  numberOfFramesToSearch = $wnd.Math.min(stackTrace.length, 5);
  for (i = numberOfFramesToSearch - 1; i >= 0; i--) {
    if ($equals_5(stackTrace[i].methodName, dropFrameUntilFnName) || $equals_5(stackTrace[i].methodName, dropFrameUntilFnName2)) {
      stackTrace.length >= i + 1 && stackTrace.splice(0, i + 1);
      break;
    }
  }
  return stackTrace;
}

function extractFunctionName(fnName){
  var fnRE = /function(?:\s+([\w$]+))?\s*\(/;
  var match_0 = fnRE.exec(fnName);
  return match_0 && match_0[1] || 'anonymous';
}

function parseInt_0(number){
  $clinit_StackTraceCreator();
  return parseInt(number) || -1;
}

function split_0(t){
  $clinit_StackTraceCreator();
  var e = t.backingJsObject;
  if (e && e.stack) {
    var stack_0 = e.stack;
    var toString_0 = e + '\n';
    stack_0.substring(0, toString_0.length) == toString_0 && (stack_0 = stack_0.substring(toString_0.length));
    return stack_0.split('\n');
  }
  return [];
}

function supportsErrorStack(){
  if (Error.stackTraceLimit > 0) {
    $wnd.Error.stackTraceLimit = Error.stackTraceLimit = 64;
    return true;
  }
  return 'stack' in new Error;
}

var collector;
defineClass(524, 1, {});
var Lcom_google_gwt_core_client_impl_StackTraceCreator$Collector_2_classLit = createForClass('com.google.gwt.core.client.impl', 'StackTraceCreator/Collector', 524);
function StackTraceCreator$CollectorLegacy(){
}

defineClass(308, 524, {}, StackTraceCreator$CollectorLegacy);
_.collect = function collect(error){
  var seen = {}, name_1;
  var fnStack = [];
  error['fnStack'] = fnStack;
  var callee = arguments.callee.caller;
  while (callee) {
    var name_0 = ($clinit_StackTraceCreator() , callee.name || (callee.name = extractFunctionName(callee.toString())));
    fnStack.push(name_0);
    var keyName = ':' + name_0;
    var withThisName = seen[keyName];
    if (withThisName) {
      var i, j;
      for (i = 0 , j = withThisName.length; i < j; i++) {
        if (withThisName[i] === callee) {
          return;
        }
      }
    }
    (withThisName || (seen[keyName] = [])).push(callee);
    callee = callee.caller;
  }
}
;
_.getStackTrace = function getStackTrace(t){
  var i, length_0, stack_0, stackTrace;
  stack_0 = ($clinit_StackTraceCreator() , t && t['fnStack']?t['fnStack']:[]);
  length_0 = stack_0.length;
  stackTrace = initUnidimensionalArray(Ljava_lang_StackTraceElement_2_classLit, $intern_9, 83, length_0, 0, 1);
  for (i = 0; i < length_0; i++) {
    stackTrace[i] = new StackTraceElement(stack_0[i], null, -1);
  }
  return stackTrace;
}
;
var Lcom_google_gwt_core_client_impl_StackTraceCreator$CollectorLegacy_2_classLit = createForClass('com.google.gwt.core.client.impl', 'StackTraceCreator/CollectorLegacy', 308);
function $parse(this$static, stString){
  var closeParen, col, endFileUrlIndex, fileName, index_0, lastColonIndex, line, location_0, toReturn;
  location_0 = '';
  if (stString.length == 0) {
    return this$static.createSte('Unknown', 'anonymous', -1, -1);
  }
  toReturn = $trim(stString);
  $equals_5(toReturn.substr(0, 3), 'at ') && (toReturn = (checkCriticalStringElementIndex(3, toReturn.length + 1) , toReturn.substr(3)));
  toReturn = toReturn.replace(/\[.*?\]/g, '');
  index_0 = toReturn.indexOf('(');
  if (index_0 == -1) {
    index_0 = toReturn.indexOf('@');
    if (index_0 == -1) {
      location_0 = toReturn;
      toReturn = '';
    }
     else {
      location_0 = $trim((checkCriticalStringElementIndex(index_0 + 1, toReturn.length + 1) , toReturn.substr(index_0 + 1)));
      toReturn = $trim((checkCriticalStringBounds(0, index_0, toReturn.length) , toReturn.substr(0, index_0)));
    }
  }
   else {
    closeParen = toReturn.indexOf(')', index_0);
    location_0 = (checkCriticalStringBounds(index_0 + 1, closeParen, toReturn.length) , toReturn.substr(index_0 + 1, closeParen - (index_0 + 1)));
    toReturn = $trim((checkCriticalStringBounds(0, index_0, toReturn.length) , toReturn.substr(0, index_0)));
  }
  index_0 = $indexOf_0(toReturn, fromCodePoint(46));
  index_0 != -1 && (toReturn = (checkCriticalStringElementIndex(index_0 + 1, toReturn.length + 1) , toReturn.substr(index_0 + 1)));
  (toReturn.length == 0 || $equals_5(toReturn, 'Anonymous function')) && (toReturn = 'anonymous');
  lastColonIndex = $lastIndexOf(location_0, fromCodePoint(58));
  endFileUrlIndex = $lastIndexOf_0(location_0, fromCodePoint(58), lastColonIndex - 1);
  line = -1;
  col = -1;
  fileName = 'Unknown';
  if (lastColonIndex != -1 && endFileUrlIndex != -1) {
    fileName = (checkCriticalStringBounds(0, endFileUrlIndex, location_0.length) , location_0.substr(0, endFileUrlIndex));
    line = parseInt_0((checkCriticalStringBounds(endFileUrlIndex + 1, lastColonIndex, location_0.length) , location_0.substr(endFileUrlIndex + 1, lastColonIndex - (endFileUrlIndex + 1))));
    col = parseInt_0((checkCriticalStringElementIndex(lastColonIndex + 1, location_0.length + 1) , location_0.substr(lastColonIndex + 1)));
  }
  return this$static.createSte(fileName, toReturn, line, col);
}

defineClass(525, 524, {});
_.collect = function collect_0(error){
}
;
_.createSte = function createSte(fileName, method, line, col){
  return new StackTraceElement(method, fileName + '@' + col, line < 0?-1:line);
}
;
_.getStackTrace = function getStackTrace_0(t){
  var addIndex, i, length_0, stack_0, stackTrace, ste;
  stack_0 = split_0(t);
  stackTrace = initUnidimensionalArray(Ljava_lang_StackTraceElement_2_classLit, $intern_9, 83, 0, 0, 1);
  addIndex = 0;
  length_0 = stack_0.length;
  if (length_0 == 0) {
    return stackTrace;
  }
  ste = $parse(this, stack_0[0]);
  $equals_5(ste.methodName, 'anonymous') || (stackTrace[addIndex++] = ste);
  for (i = 1; i < length_0; i++) {
    stackTrace[addIndex++] = $parse(this, stack_0[i]);
  }
  return stackTrace;
}
;
var Lcom_google_gwt_core_client_impl_StackTraceCreator$CollectorModern_2_classLit = createForClass('com.google.gwt.core.client.impl', 'StackTraceCreator/CollectorModern', 525);
function StackTraceCreator$CollectorModernNoSourceMap(){
}

defineClass(309, 525, {}, StackTraceCreator$CollectorModernNoSourceMap);
_.createSte = function createSte_0(fileName, method, line, col){
  return new StackTraceElement(method, fileName, -1);
}
;
var Lcom_google_gwt_core_client_impl_StackTraceCreator$CollectorModernNoSourceMap_2_classLit = createForClass('com.google.gwt.core.client.impl', 'StackTraceCreator/CollectorModernNoSourceMap', 309);
function $clinit_Numbers(){
  $clinit_Numbers = emptyMethod;
  wba = new Int8Array(8);
  wia = create_5(wba.buffer, 0, 2);
  wfa = create_2(wba.buffer, 0, 1);
  create_3(wba.buffer, 0, 1);
}

function intBitsToFloat(i){
  $clinit_Numbers();
  wia[0] = i;
  return wfa[0];
}

var wba, wfa, wia;
function $appendChild(this$static, newChild){
  return this$static.appendChild(newChild);
}

function $removeChild(this$static, oldChild){
  return this$static.removeChild(oldChild);
}

function $addClassName(this$static, className){
  var idx, oldClassName;
  className = trimClassName(className);
  oldClassName = this$static.className || '';
  idx = indexOfName(oldClassName, className);
  if (idx == -1) {
    oldClassName.length > 0?(this$static.className = oldClassName + ' ' + className || '' , undefined):(this$static.className = className || '' , undefined);
    return true;
  }
  return false;
}

function $getPropertyString(this$static, name_0){
  return this$static[name_0] == null?null:String(this$static[name_0]);
}

function $setAttribute(this$static, name_0, value_0){
  this$static.setAttribute(name_0, value_0);
}

function indexOfName(nameList, name_0){
  var idx, last, lastPos;
  idx = nameList.indexOf(name_0);
  while (idx != -1) {
    if (idx == 0 || (checkCriticalStringElementIndex(idx - 1, nameList.length) , nameList.charCodeAt(idx - 1) == 32)) {
      last = idx + name_0.length;
      lastPos = nameList.length;
      if (last == lastPos || last < lastPos && (checkCriticalStringElementIndex(last, nameList.length) , nameList.charCodeAt(last) == 32)) {
        break;
      }
    }
    idx = nameList.indexOf(name_0, idx + 1);
  }
  return idx;
}

function trimClassName(className){
  className = $trim(className);
  return className;
}

function $setSrc(this$static, url_0){
  this$static.src = url_0;
}

function $setHeight_0(this$static, height){
  this$static.height = height;
}

function $setWidth_0(this$static, width_0){
  this$static.width = width_0;
}

function $getParentElement(node){
  var parent_0 = node.parentNode;
  (!parent_0 || parent_0.nodeType != 1) && (parent_0 = null);
  return parent_0;
}

function $dispatchEvent(target, evt){
  target.dispatchEvent(evt);
}

function $eventGetButton(evt){
  var button = evt.button;
  if (button == 1) {
    return 4;
  }
   else if (button == 2) {
    return 2;
  }
  return 1;
}

function $getDocumentScrollingElement(doc){
  if (doc.scrollingElement) {
    return doc.scrollingElement;
  }
  return $equals_5(doc.compatMode, 'CSS1Compat')?doc.documentElement:doc.body;
}

function $eventGetRelatedTarget(evt){
  var relatedTarget = evt.relatedTarget;
  if (!relatedTarget) {
    return null;
  }
  try {
    var nodeName = relatedTarget.nodeName;
    return relatedTarget;
  }
   catch (e) {
    return null;
  }
}

function $getAbsoluteLeftImpl(viewport, elem){
  if (Element.prototype.getBoundingClientRect) {
    return elem.getBoundingClientRect().left + viewport.scrollLeft | 0;
  }
   else {
    var doc = elem.ownerDocument;
    return doc.getBoxObjectFor(elem).screenX - doc.getBoxObjectFor(doc.documentElement).screenX;
  }
}

function $getAbsoluteTopImpl(viewport, elem){
  if (Element.prototype.getBoundingClientRect) {
    return elem.getBoundingClientRect().top + viewport.scrollTop | 0;
  }
   else {
    var doc = elem.ownerDocument;
    return doc.getBoxObjectFor(elem).screenY - doc.getBoxObjectFor(doc.documentElement).screenY;
  }
}

function $getScrollLeft(elem){
  var geckoVersion, style;
  geckoVersion = (cachedGeckoVersion == -2 && (cachedGeckoVersion = getNativeGeckoVersion()) , cachedGeckoVersion);
  if (!(geckoVersion != -1 && geckoVersion >= 1009000) && (style = elem.ownerDocument.defaultView.getComputedStyle(elem, null) , style.direction == 'rtl')) {
    return ((elem.scrollLeft || 0) | 0) - (((elem.scrollWidth || 0) | 0) - (elem.clientWidth | 0));
  }
  return (elem.scrollLeft || 0) | 0;
}

function $isOrHasChild(parent_0, child){
  return parent_0 === child || !!(parent_0.compareDocumentPosition(child) & 16);
}

function $toString_3(elem){
  var doc = elem.ownerDocument;
  var temp = elem.cloneNode(true);
  var tempDiv = doc.createElement('DIV');
  tempDiv.appendChild(temp);
  outer = tempDiv.innerHTML;
  temp.innerHTML = '';
  return outer;
}

function getNativeGeckoVersion(){
  var result = /rv:([0-9]+)\.([0-9]+)(\.([0-9]+))?.*?/.exec(navigator.userAgent.toLowerCase());
  if (result && result.length >= 3) {
    var version = parseInt(result[1]) * $intern_4 + parseInt(result[2]) * 1000 + parseInt(result.length >= 5 && !isNaN(result[4])?result[4]:0);
    return version;
  }
  return -1;
}

var cachedGeckoVersion = -2;
function $enableScrolling(this$static){
  ($equals_5(this$static.compatMode, 'CSS1Compat')?this$static.documentElement:this$static.body).style['overflow'] = 'hidden';
}

function $getClientHeight(this$static){
  return ($equals_5(this$static.compatMode, 'CSS1Compat')?this$static.documentElement:this$static.body).clientHeight | 0;
}

function $getClientWidth(this$static){
  return ($equals_5(this$static.compatMode, 'CSS1Compat')?this$static.documentElement:this$static.body).clientWidth | 0;
}

function $getElementById(this$static, elementId){
  return this$static.getElementById(elementId);
}

function $getScrollLeft_0(this$static){
  var scrollingElement;
  return $getScrollLeft((scrollingElement = $getDocumentScrollingElement(this$static) , scrollingElement?scrollingElement:this$static.documentElement));
}

function $getScrollTop(this$static){
  var scrollingElement;
  return ((scrollingElement = $getDocumentScrollingElement(this$static) , scrollingElement?scrollingElement:this$static.documentElement).scrollTop || 0) | 0;
}

function $getViewportElement(this$static){
  return $equals_5(this$static.compatMode, 'CSS1Compat')?this$static.documentElement:this$static.body;
}

function $setSrc_0(this$static, src_0){
  this$static.src = src_0;
}

function $clinit_Style$Display(){
  $clinit_Style$Display = emptyMethod;
  NONE = new Style$Display$1;
  BLOCK = new Style$Display$2;
  INLINE = new Style$Display$3;
  INLINE_BLOCK = new Style$Display$4;
  INLINE_TABLE = new Style$Display$5;
  LIST_ITEM = new Style$Display$6;
  RUN_IN = new Style$Display$7;
  TABLE = new Style$Display$8;
  TABLE_CAPTION = new Style$Display$9;
  TABLE_COLUMN_GROUP = new Style$Display$10;
  TABLE_HEADER_GROUP = new Style$Display$11;
  TABLE_FOOTER_GROUP = new Style$Display$12;
  TABLE_ROW_GROUP = new Style$Display$13;
  TABLE_CELL = new Style$Display$14;
  TABLE_COLUMN = new Style$Display$15;
  TABLE_ROW = new Style$Display$16;
  INITIAL = new Style$Display$17;
  FLEX = new Style$Display$18;
  INLINE_FLEX = new Style$Display$19;
}

function Style$Display(enum$name, enum$ordinal){
  Enum.call(this, enum$name, enum$ordinal);
}

function values_8(){
  $clinit_Style$Display();
  return stampJavaTypeInfo(getClassLiteralForArray(Lcom_google_gwt_dom_client_Style$Display_2_classLit, 1), $intern_1, 17, 0, [NONE, BLOCK, INLINE, INLINE_BLOCK, INLINE_TABLE, LIST_ITEM, RUN_IN, TABLE, TABLE_CAPTION, TABLE_COLUMN_GROUP, TABLE_HEADER_GROUP, TABLE_FOOTER_GROUP, TABLE_ROW_GROUP, TABLE_CELL, TABLE_COLUMN, TABLE_ROW, INITIAL, FLEX, INLINE_FLEX]);
}

defineClass(17, 8, $intern_25);
var BLOCK, FLEX, INITIAL, INLINE, INLINE_BLOCK, INLINE_FLEX, INLINE_TABLE, LIST_ITEM, NONE, RUN_IN, TABLE, TABLE_CAPTION, TABLE_CELL, TABLE_COLUMN, TABLE_COLUMN_GROUP, TABLE_FOOTER_GROUP, TABLE_HEADER_GROUP, TABLE_ROW, TABLE_ROW_GROUP;
var Lcom_google_gwt_dom_client_Style$Display_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display', 17, values_8);
function Style$Display$1(){
  Style$Display.call(this, 'NONE', 0);
}

defineClass(344, 17, $intern_25, Style$Display$1);
var Lcom_google_gwt_dom_client_Style$Display$1_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/1', 344, null);
function Style$Display$10(){
  Style$Display.call(this, 'TABLE_COLUMN_GROUP', 9);
}

defineClass(353, 17, $intern_25, Style$Display$10);
var Lcom_google_gwt_dom_client_Style$Display$10_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/10', 353, null);
function Style$Display$11(){
  Style$Display.call(this, 'TABLE_HEADER_GROUP', 10);
}

defineClass(354, 17, $intern_25, Style$Display$11);
var Lcom_google_gwt_dom_client_Style$Display$11_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/11', 354, null);
function Style$Display$12(){
  Style$Display.call(this, 'TABLE_FOOTER_GROUP', 11);
}

defineClass(355, 17, $intern_25, Style$Display$12);
var Lcom_google_gwt_dom_client_Style$Display$12_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/12', 355, null);
function Style$Display$13(){
  Style$Display.call(this, 'TABLE_ROW_GROUP', 12);
}

defineClass(356, 17, $intern_25, Style$Display$13);
var Lcom_google_gwt_dom_client_Style$Display$13_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/13', 356, null);
function Style$Display$14(){
  Style$Display.call(this, 'TABLE_CELL', 13);
}

defineClass(357, 17, $intern_25, Style$Display$14);
var Lcom_google_gwt_dom_client_Style$Display$14_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/14', 357, null);
function Style$Display$15(){
  Style$Display.call(this, 'TABLE_COLUMN', 14);
}

defineClass(358, 17, $intern_25, Style$Display$15);
var Lcom_google_gwt_dom_client_Style$Display$15_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/15', 358, null);
function Style$Display$16(){
  Style$Display.call(this, 'TABLE_ROW', 15);
}

defineClass(359, 17, $intern_25, Style$Display$16);
var Lcom_google_gwt_dom_client_Style$Display$16_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/16', 359, null);
function Style$Display$17(){
  Style$Display.call(this, 'INITIAL', 16);
}

defineClass(360, 17, $intern_25, Style$Display$17);
var Lcom_google_gwt_dom_client_Style$Display$17_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/17', 360, null);
function Style$Display$18(){
  Style$Display.call(this, 'FLEX', 17);
}

defineClass(361, 17, $intern_25, Style$Display$18);
var Lcom_google_gwt_dom_client_Style$Display$18_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/18', 361, null);
function Style$Display$19(){
  Style$Display.call(this, 'INLINE_FLEX', 18);
}

defineClass(362, 17, $intern_25, Style$Display$19);
var Lcom_google_gwt_dom_client_Style$Display$19_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/19', 362, null);
function Style$Display$2(){
  Style$Display.call(this, 'BLOCK', 1);
}

defineClass(345, 17, $intern_25, Style$Display$2);
var Lcom_google_gwt_dom_client_Style$Display$2_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/2', 345, null);
function Style$Display$3(){
  Style$Display.call(this, 'INLINE', 2);
}

defineClass(346, 17, $intern_25, Style$Display$3);
var Lcom_google_gwt_dom_client_Style$Display$3_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/3', 346, null);
function Style$Display$4(){
  Style$Display.call(this, 'INLINE_BLOCK', 3);
}

defineClass(347, 17, $intern_25, Style$Display$4);
var Lcom_google_gwt_dom_client_Style$Display$4_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/4', 347, null);
function Style$Display$5(){
  Style$Display.call(this, 'INLINE_TABLE', 4);
}

defineClass(348, 17, $intern_25, Style$Display$5);
var Lcom_google_gwt_dom_client_Style$Display$5_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/5', 348, null);
function Style$Display$6(){
  Style$Display.call(this, 'LIST_ITEM', 5);
}

defineClass(349, 17, $intern_25, Style$Display$6);
var Lcom_google_gwt_dom_client_Style$Display$6_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/6', 349, null);
function Style$Display$7(){
  Style$Display.call(this, 'RUN_IN', 6);
}

defineClass(350, 17, $intern_25, Style$Display$7);
var Lcom_google_gwt_dom_client_Style$Display$7_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/7', 350, null);
function Style$Display$8(){
  Style$Display.call(this, 'TABLE', 7);
}

defineClass(351, 17, $intern_25, Style$Display$8);
var Lcom_google_gwt_dom_client_Style$Display$8_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/8', 351, null);
function Style$Display$9(){
  Style$Display.call(this, 'TABLE_CAPTION', 8);
}

defineClass(352, 17, $intern_25, Style$Display$9);
var Lcom_google_gwt_dom_client_Style$Display$9_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Display/9', 352, null);
function $clinit_Style$TextAlign(){
  $clinit_Style$TextAlign = emptyMethod;
  CENTER = new Style$TextAlign$1;
  JUSTIFY = new Style$TextAlign$2;
  LEFT = new Style$TextAlign$3;
  RIGHT = new Style$TextAlign$4;
}

function Style$TextAlign(enum$name, enum$ordinal){
  Enum.call(this, enum$name, enum$ordinal);
}

function values_9(){
  $clinit_Style$TextAlign();
  return stampJavaTypeInfo(getClassLiteralForArray(Lcom_google_gwt_dom_client_Style$TextAlign_2_classLit, 1), $intern_1, 73, 0, [CENTER, JUSTIFY, LEFT, RIGHT]);
}

defineClass(73, 8, $intern_26);
var CENTER, JUSTIFY, LEFT, RIGHT;
var Lcom_google_gwt_dom_client_Style$TextAlign_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/TextAlign', 73, values_9);
function Style$TextAlign$1(){
  Style$TextAlign.call(this, 'CENTER', 0);
}

defineClass(363, 73, $intern_26, Style$TextAlign$1);
var Lcom_google_gwt_dom_client_Style$TextAlign$1_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/TextAlign/1', 363, null);
function Style$TextAlign$2(){
  Style$TextAlign.call(this, 'JUSTIFY', 1);
}

defineClass(364, 73, $intern_26, Style$TextAlign$2);
var Lcom_google_gwt_dom_client_Style$TextAlign$2_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/TextAlign/2', 364, null);
function Style$TextAlign$3(){
  Style$TextAlign.call(this, 'LEFT', 2);
}

defineClass(365, 73, $intern_26, Style$TextAlign$3);
var Lcom_google_gwt_dom_client_Style$TextAlign$3_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/TextAlign/3', 365, null);
function Style$TextAlign$4(){
  Style$TextAlign.call(this, 'RIGHT', 3);
}

defineClass(366, 73, $intern_26, Style$TextAlign$4);
var Lcom_google_gwt_dom_client_Style$TextAlign$4_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/TextAlign/4', 366, null);
function $clinit_Style$Unit(){
  $clinit_Style$Unit = emptyMethod;
  PX = new Style$Unit$1;
  PCT = new Style$Unit$2;
  EM = new Style$Unit$3;
  EX = new Style$Unit$4;
  PT = new Style$Unit$5;
  PC = new Style$Unit$6;
  IN = new Style$Unit$7;
  CM = new Style$Unit$8;
  MM = new Style$Unit$9;
}

function Style$Unit(enum$name, enum$ordinal){
  Enum.call(this, enum$name, enum$ordinal);
}

function values_10(){
  $clinit_Style$Unit();
  return stampJavaTypeInfo(getClassLiteralForArray(Lcom_google_gwt_dom_client_Style$Unit_2_classLit, 1), $intern_1, 42, 0, [PX, PCT, EM, EX, PT, PC, IN, CM, MM]);
}

defineClass(42, 8, $intern_27);
var CM, EM, EX, IN, MM, PC, PCT, PT, PX;
var Lcom_google_gwt_dom_client_Style$Unit_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Unit', 42, values_10);
function Style$Unit$1(){
  Style$Unit.call(this, 'PX', 0);
}

defineClass(335, 42, $intern_27, Style$Unit$1);
var Lcom_google_gwt_dom_client_Style$Unit$1_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Unit/1', 335, null);
function Style$Unit$2(){
  Style$Unit.call(this, 'PCT', 1);
}

defineClass(336, 42, $intern_27, Style$Unit$2);
var Lcom_google_gwt_dom_client_Style$Unit$2_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Unit/2', 336, null);
function Style$Unit$3(){
  Style$Unit.call(this, 'EM', 2);
}

defineClass(337, 42, $intern_27, Style$Unit$3);
var Lcom_google_gwt_dom_client_Style$Unit$3_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Unit/3', 337, null);
function Style$Unit$4(){
  Style$Unit.call(this, 'EX', 3);
}

defineClass(338, 42, $intern_27, Style$Unit$4);
var Lcom_google_gwt_dom_client_Style$Unit$4_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Unit/4', 338, null);
function Style$Unit$5(){
  Style$Unit.call(this, 'PT', 4);
}

defineClass(339, 42, $intern_27, Style$Unit$5);
var Lcom_google_gwt_dom_client_Style$Unit$5_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Unit/5', 339, null);
function Style$Unit$6(){
  Style$Unit.call(this, 'PC', 5);
}

defineClass(340, 42, $intern_27, Style$Unit$6);
var Lcom_google_gwt_dom_client_Style$Unit$6_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Unit/6', 340, null);
function Style$Unit$7(){
  Style$Unit.call(this, 'IN', 6);
}

defineClass(341, 42, $intern_27, Style$Unit$7);
var Lcom_google_gwt_dom_client_Style$Unit$7_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Unit/7', 341, null);
function Style$Unit$8(){
  Style$Unit.call(this, 'CM', 7);
}

defineClass(342, 42, $intern_27, Style$Unit$8);
var Lcom_google_gwt_dom_client_Style$Unit$8_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Unit/8', 342, null);
function Style$Unit$9(){
  Style$Unit.call(this, 'MM', 8);
}

defineClass(343, 42, $intern_27, Style$Unit$9);
var Lcom_google_gwt_dom_client_Style$Unit$9_2_classLit = createForEnum('com.google.gwt.dom.client', 'Style/Unit/9', 343, null);
defineClass(530, 1, {});
_.toString_0 = function toString_26(){
  return 'An event type';
}
;
var Lcom_google_web_bindery_event_shared_Event_2_classLit = createForClass('com.google.web.bindery.event.shared', 'Event', 530);
function $overrideSource(this$static, source){
  this$static.source = source;
}

defineClass(531, 530, {});
_.getAssociatedType_0 = function getAssociatedType(){
  return this.getAssociatedType();
}
;
_.dead = false;
var Lcom_google_gwt_event_shared_GwtEvent_2_classLit = createForClass('com.google.gwt.event.shared', 'GwtEvent', 531);
function CloseEvent_0(){
}

function fire_0(source){
  var event_0;
  if (TYPE) {
    event_0 = new CloseEvent_0;
    $fireEvent(source, event_0);
  }
}

defineClass(395, 531, {}, CloseEvent_0);
_.dispatch = function dispatch(handler){
  castTo(handler, 552);
  detachWidgets();
}
;
_.getAssociatedType_0 = function getAssociatedType_1(){
  return TYPE;
}
;
_.getAssociatedType = function getAssociatedType_0(){
  return TYPE;
}
;
var TYPE;
var Lcom_google_gwt_event_logical_shared_CloseEvent_2_classLit = createForClass('com.google.gwt.event.logical.shared', 'CloseEvent', 395);
function $dispatch(this$static, handler){
  $onResize(handler, this$static);
}

function ResizeEvent(width_0, height){
  this.width_0 = width_0;
  this.height_0 = height;
}

function fire_1(source, width_0, height){
  var event_0;
  if (TYPE_0) {
    event_0 = new ResizeEvent(width_0, height);
    $fireEvent(source, event_0);
  }
}

defineClass(334, 531, {}, ResizeEvent);
_.dispatch = function dispatch_0(handler){
  $dispatch(this, castTo(handler, 513));
}
;
_.getAssociatedType_0 = function getAssociatedType_3(){
  return TYPE_0;
}
;
_.getAssociatedType = function getAssociatedType_2(){
  return TYPE_0;
}
;
_.height_0 = 0;
_.width_0 = 0;
var TYPE_0;
var Lcom_google_gwt_event_logical_shared_ResizeEvent_2_classLit = createForClass('com.google.gwt.event.logical.shared', 'ResizeEvent', 334);
defineClass(317, 1, {});
_.hashCode_0 = function hashCode_16(){
  return this.index_0;
}
;
_.toString_0 = function toString_27(){
  return 'Event type';
}
;
_.index_0 = 0;
var nextHashCode = 0;
var Lcom_google_web_bindery_event_shared_Event$Type_2_classLit = createForClass('com.google.web.bindery.event.shared', 'Event/Type', 317);
function GwtEvent$Type(){
  this.index_0 = ++nextHashCode;
}

defineClass(169, 317, {}, GwtEvent$Type);
var Lcom_google_gwt_event_shared_GwtEvent$Type_2_classLit = createForClass('com.google.gwt.event.shared', 'GwtEvent/Type', 169);
function $addHandler(this$static, type_0, handler){
  return $doAdd(this$static.eventBus, type_0, handler) , new LegacyHandlerWrapper;
}

function $fireEvent(this$static, event_0){
  var e, oldSource;
  !event_0.dead || (event_0.dead = false , event_0.source = null);
  oldSource = event_0.source;
  $overrideSource(event_0, this$static.source);
  try {
    $doFire(this$static.eventBus, event_0);
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 108)) {
      e = $e0;
      throw toJs(new UmbrellaException_0(e.causes));
    }
     else 
      throw toJs($e0);
  }
   finally {
    oldSource == null?(event_0.dead = true , event_0.source = null):(event_0.source = oldSource);
  }
}

defineClass(227, 1, {52:1});
var Lcom_google_gwt_event_shared_HandlerManager_2_classLit = createForClass('com.google.gwt.event.shared', 'HandlerManager', 227);
defineClass(532, 1, {});
var Lcom_google_web_bindery_event_shared_EventBus_2_classLit = createForClass('com.google.web.bindery.event.shared', 'EventBus', 532);
function $defer(this$static, command){
  !this$static.deferredDeltas && (this$static.deferredDeltas = new ArrayList);
  $add_8(this$static.deferredDeltas, command);
}

function $doAdd(this$static, type_0, handler){
  var l;
  if (!type_0) {
    throw toJs(new NullPointerException_0('Cannot add a handler with a null type'));
  }
  this$static.firingDepth > 0?$defer(this$static, new SimpleEventBus$2(this$static, type_0, handler)):(l = $ensureHandlerList(this$static, type_0, null) , l.add_0(handler));
  return new SimpleEventBus$1;
}

function $doAddNow(this$static, type_0, source, handler){
  var l;
  l = $ensureHandlerList(this$static, type_0, source);
  l.add_0(handler);
}

function $doFire(this$static, event_0){
  var causes, directHandlers, e, handler, handlers, it;
  try {
    ++this$static.firingDepth;
    handlers = (directHandlers = $getHandlerList(this$static, event_0.getAssociatedType_0()) , directHandlers);
    causes = null;
    it = this$static.isReverseOrder?handlers.listIterator_0(handlers.size_1()):handlers.listIterator();
    while (this$static.isReverseOrder?it.hasPrevious():it.hasNext_0()) {
      handler = this$static.isReverseOrder?it.previous():it.next_1();
      try {
        event_0.dispatch(castTo(handler, 293));
      }
       catch ($e0) {
        $e0 = toJava($e0);
        if (instanceOf($e0, 9)) {
          e = $e0;
          !causes && (causes = new HashSet);
          $put_4(causes.map_0, e, causes);
        }
         else 
          throw toJs($e0);
      }
    }
    if (causes) {
      throw toJs(new UmbrellaException(causes));
    }
  }
   finally {
    --this$static.firingDepth;
    this$static.firingDepth == 0 && $handleQueuedAddsAndRemoves(this$static);
  }
}

function $ensureHandlerList(this$static, type_0, source){
  var handlers, sourceMap;
  sourceMap = castTo($get_8(this$static.map_0, type_0), 121);
  if (!sourceMap) {
    sourceMap = new HashMap;
    $put_4(this$static.map_0, type_0, sourceMap);
  }
  handlers = castTo(sourceMap.get_5(source), 82);
  if (!handlers) {
    handlers = new ArrayList;
    sourceMap.put_3(source, handlers);
  }
  return handlers;
}

function $getHandlerList(this$static, type_0){
  var handlers, sourceMap;
  sourceMap = castTo($get_8(this$static.map_0, type_0), 121);
  if (!sourceMap) {
    return $clinit_Collections() , $clinit_Collections() , EMPTY_LIST;
  }
  handlers = castTo(sourceMap.get_5(null), 82);
  if (!handlers) {
    return $clinit_Collections() , $clinit_Collections() , EMPTY_LIST;
  }
  return handlers;
}

function $handleQueuedAddsAndRemoves(this$static){
  var c, c$iterator;
  if (this$static.deferredDeltas) {
    try {
      for (c$iterator = new ArrayList$1(this$static.deferredDeltas); c$iterator.i < c$iterator.this$01.array.length;) {
        c = castTo($next_7(c$iterator), 551);
        $doAddNow(c.this$01, c.val$type2, c.val$source3, c.val$handler4);
      }
    }
     finally {
      this$static.deferredDeltas = null;
    }
  }
}

defineClass(318, 532, {});
_.firingDepth = 0;
_.isReverseOrder = false;
var Lcom_google_web_bindery_event_shared_SimpleEventBus_2_classLit = createForClass('com.google.web.bindery.event.shared', 'SimpleEventBus', 318);
function HandlerManager$Bus(){
  this.map_0 = new HashMap;
  this.isReverseOrder = false;
}

defineClass(319, 318, {}, HandlerManager$Bus);
var Lcom_google_gwt_event_shared_HandlerManager$Bus_2_classLit = createForClass('com.google.gwt.event.shared', 'HandlerManager/Bus', 319);
function LegacyHandlerWrapper(){
}

defineClass(379, 1, {}, LegacyHandlerWrapper);
var Lcom_google_gwt_event_shared_LegacyHandlerWrapper_2_classLit = createForClass('com.google.gwt.event.shared', 'LegacyHandlerWrapper', 379);
function UmbrellaException(causes){
  var cause, cause$iterator, i;
  RuntimeException_1.call(this, makeMessage(causes), causes.isEmpty()?null:castTo(causes.iterator().next_1(), 9));
  this.causes = causes;
  i = 0;
  for (cause$iterator = causes.iterator(); cause$iterator.hasNext_0();) {
    cause = castTo(cause$iterator.next_1(), 9);
    if (i++ == 0) {
      continue;
    }
    $addSuppressed(this, cause);
  }
}

function makeMessage(causes){
  var b, count, first, t, t$iterator;
  count = causes.size_1();
  if (count == 0) {
    return null;
  }
  b = new StringBuilder_1(count == 1?'Exception caught: ':count + ' exceptions caught: ');
  first = true;
  for (t$iterator = causes.iterator(); t$iterator.hasNext_0();) {
    t = castTo(t$iterator.next_1(), 9);
    first?(first = false):(b.string += '; ' , b);
    $append_3(b, t.getMessage());
  }
  return b.string;
}

defineClass(108, 27, $intern_28, UmbrellaException);
var Lcom_google_web_bindery_event_shared_UmbrellaException_2_classLit = createForClass('com.google.web.bindery.event.shared', 'UmbrellaException', 108);
function UmbrellaException_0(causes){
  UmbrellaException.call(this, causes);
}

defineClass(226, 108, $intern_28, UmbrellaException_0);
var Lcom_google_gwt_event_shared_UmbrellaException_2_classLit = createForClass('com.google.gwt.event.shared', 'UmbrellaException', 226);
function getDirectionOnElement(elem){
  var dirPropertyValue;
  dirPropertyValue = $getPropertyString(elem, 'dir');
  if ($equalsIgnoreCase('rtl', dirPropertyValue)) {
    return $clinit_HasDirection$Direction() , RTL;
  }
   else if ($equalsIgnoreCase('ltr', dirPropertyValue)) {
    return $clinit_HasDirection$Direction() , LTR;
  }
  return $clinit_HasDirection$Direction() , DEFAULT;
}

function setDirectionOnElement(elem, direction){
  switch (direction.ordinal) {
    case 0:
      {
        elem['dir'] = 'rtl';
        break;
      }

    case 1:
      {
        elem['dir'] = 'ltr';
        break;
      }

    case 2:
      {
        getDirectionOnElement(elem) != ($clinit_HasDirection$Direction() , DEFAULT) && (elem['dir'] = '' , undefined);
        break;
      }

  }
}

function $clinit_HasDirection$Direction(){
  $clinit_HasDirection$Direction = emptyMethod;
  RTL = new HasDirection$Direction('RTL', 0);
  LTR = new HasDirection$Direction('LTR', 1);
  DEFAULT = new HasDirection$Direction('DEFAULT', 2);
}

function HasDirection$Direction(enum$name, enum$ordinal){
  Enum.call(this, enum$name, enum$ordinal);
}

function values_11(){
  $clinit_HasDirection$Direction();
  return stampJavaTypeInfo(getClassLiteralForArray(Lcom_google_gwt_i18n_client_HasDirection$Direction_2_classLit, 1), $intern_1, 124, 0, [RTL, LTR, DEFAULT]);
}

defineClass(124, 8, {124:1, 3:1, 7:1, 8:1}, HasDirection$Direction);
var DEFAULT, LTR, RTL;
var Lcom_google_gwt_i18n_client_HasDirection$Direction_2_classLit = createForEnum('com.google.gwt.i18n.client', 'HasDirection/Direction', 124, values_11);
function canSet(array, value_0){
  var elementTypeCategory;
  switch (getElementTypeCategory(array)) {
    case 6:
      return instanceOfString(value_0);
    case 7:
      return instanceOfDouble(value_0);
    case 8:
      return instanceOfBoolean(value_0);
    case 3:
      return Array.isArray(value_0) && (elementTypeCategory = getElementTypeCategory(value_0) , !(elementTypeCategory >= 14 && elementTypeCategory <= 16));
    case 11:
      return value_0 != null && typeof value_0 === 'function';
    case 12:
      return value_0 != null && (typeof value_0 === 'object' || typeof value_0 == 'function');
    case 0:
      return canCast(value_0, array.__elementTypeId$);
    case 2:
      return isJsObjectOrFunction(value_0) && !(value_0.typeMarker === typeMarkerFn);
    case 1:
      return isJsObjectOrFunction(value_0) && !(value_0.typeMarker === typeMarkerFn) || canCast(value_0, array.__elementTypeId$);
    default:return true;
  }
}

function getClassLiteralForArray(clazz, dimensions){
  return getClassLiteralForArray_0(clazz, dimensions);
}

function getElementTypeCategory(array){
  return array.__elementTypeCategory$ == null?10:array.__elementTypeCategory$;
}

function initUnidimensionalArray(leafClassLiteral, castableTypeMap, elementTypeId, length_0, elementTypeCategory, dimensions){
  var result;
  result = initializeArrayElementsWithDefaults(elementTypeCategory, length_0);
  elementTypeCategory != 10 && stampJavaTypeInfo(getClassLiteralForArray(leafClassLiteral, dimensions), castableTypeMap, elementTypeId, elementTypeCategory, result);
  return result;
}

function initializeArrayElementsWithDefaults(elementTypeCategory, length_0){
  var array = new Array(length_0);
  var initValue;
  switch (elementTypeCategory) {
    case 14:
    case 15:
      initValue = 0;
      break;
    case 16:
      initValue = false;
      break;
    default:return array;
  }
  for (var i = 0; i < length_0; ++i) {
    array[i] = initValue;
  }
  return array;
}

function isJavaArray(src_0){
  return Array.isArray(src_0) && src_0.typeMarker === typeMarkerFn;
}

function setCheck(array, index_0, value_0){
  checkCriticalArrayType(value_0 == null || canSet(array, value_0));
  return array[index_0] = value_0;
}

function stampJavaTypeInfo(arrayClass, castableTypeMap, elementTypeId, elementTypeCategory, array){
  array.___clazz = arrayClass;
  array.castableTypeMap = castableTypeMap;
  array.typeMarker = typeMarkerFn;
  array.__elementTypeId$ = elementTypeId;
  array.__elementTypeCategory$ = elementTypeCategory;
  return array;
}

function stampJavaTypeInfo_0(array, referenceType){
  getElementTypeCategory(referenceType) != 10 && stampJavaTypeInfo(getClass__Ljava_lang_Class___devirtual$(referenceType), referenceType.castableTypeMap, referenceType.__elementTypeId$, getElementTypeCategory(referenceType), array);
  return array;
}

function create(value_0){
  var a0, a1, a2;
  a0 = value_0 & $intern_29;
  a1 = value_0 >> 22 & $intern_29;
  a2 = value_0 < 0?$intern_30:0;
  return create0(a0, a1, a2);
}

function create_0(a){
  return create0(a.l, a.m, a.h);
}

function create0(l, m, h){
  return {l:l, m:m, h:h};
}

function divMod(a, b, computeRemainder){
  var aIsCopy, aIsMinValue, aIsNegative, bpower, c, negative;
  if (b.l == 0 && b.m == 0 && b.h == 0) {
    throw toJs(new ArithmeticException);
  }
  if (a.l == 0 && a.m == 0 && a.h == 0) {
    computeRemainder && (remainder = create0(0, 0, 0));
    return create0(0, 0, 0);
  }
  if (b.h == $intern_19 && b.m == 0 && b.l == 0) {
    return divModByMinValue(a, computeRemainder);
  }
  negative = false;
  if (b.h >> 19 != 0) {
    b = neg(b);
    negative = !negative;
  }
  bpower = powerOfTwo(b);
  aIsNegative = false;
  aIsMinValue = false;
  aIsCopy = false;
  if (a.h == $intern_19 && a.m == 0 && a.l == 0) {
    aIsMinValue = true;
    aIsNegative = true;
    if (bpower == -1) {
      a = create_0(($clinit_BigLongLib$Const() , MAX_VALUE));
      aIsCopy = true;
      negative = !negative;
    }
     else {
      c = shr(a, bpower);
      negative && negate(c);
      computeRemainder && (remainder = create0(0, 0, 0));
      return c;
    }
  }
   else if (a.h >> 19 != 0) {
    aIsNegative = true;
    a = neg(a);
    aIsCopy = true;
    negative = !negative;
  }
  if (bpower != -1) {
    return divModByShift(a, bpower, negative, aIsNegative, computeRemainder);
  }
  if (compare(a, b) < 0) {
    computeRemainder && (aIsNegative?(remainder = neg(a)):(remainder = create0(a.l, a.m, a.h)));
    return create0(0, 0, 0);
  }
  return divModHelper(aIsCopy?a:create0(a.l, a.m, a.h), b, negative, aIsNegative, aIsMinValue, computeRemainder);
}

function divModByMinValue(a, computeRemainder){
  if (a.h == $intern_19 && a.m == 0 && a.l == 0) {
    computeRemainder && (remainder = create0(0, 0, 0));
    return create_0(($clinit_BigLongLib$Const() , ONE));
  }
  computeRemainder && (remainder = create0(a.l, a.m, a.h));
  return create0(0, 0, 0);
}

function divModByShift(a, bpower, negative, aIsNegative, computeRemainder){
  var c;
  c = shr(a, bpower);
  negative && negate(c);
  if (computeRemainder) {
    a = maskRight(a, bpower);
    aIsNegative?(remainder = neg(a)):(remainder = create0(a.l, a.m, a.h));
  }
  return c;
}

function divModHelper(a, b, negative, aIsNegative, aIsMinValue, computeRemainder){
  var bshift, gte, quotient, shift_0, a1, a2, a0;
  shift_0 = numberOfLeadingZeros(b) - numberOfLeadingZeros(a);
  bshift = shl(b, shift_0);
  quotient = create0(0, 0, 0);
  while (shift_0 >= 0) {
    gte = trialSubtract(a, bshift);
    if (gte) {
      shift_0 < 22?(quotient.l |= 1 << shift_0 , undefined):shift_0 < 44?(quotient.m |= 1 << shift_0 - 22 , undefined):(quotient.h |= 1 << shift_0 - 44 , undefined);
      if (a.l == 0 && a.m == 0 && a.h == 0) {
        break;
      }
    }
    a1 = bshift.m;
    a2 = bshift.h;
    a0 = bshift.l;
    bshift.h = a2 >>> 1;
    bshift.m = a1 >>> 1 | (a2 & 1) << 21;
    bshift.l = a0 >>> 1 | (a1 & 1) << 21;
    --shift_0;
  }
  negative && negate(quotient);
  if (computeRemainder) {
    if (aIsNegative) {
      remainder = neg(a);
      aIsMinValue && (remainder = sub_0(remainder, ($clinit_BigLongLib$Const() , ONE)));
    }
     else {
      remainder = create0(a.l, a.m, a.h);
    }
  }
  return quotient;
}

function maskRight(a, bits){
  var b0, b1, b2;
  if (bits <= 22) {
    b0 = a.l & (1 << bits) - 1;
    b1 = b2 = 0;
  }
   else if (bits <= 44) {
    b0 = a.l;
    b1 = a.m & (1 << bits - 22) - 1;
    b2 = 0;
  }
   else {
    b0 = a.l;
    b1 = a.m;
    b2 = a.h & (1 << bits - 44) - 1;
  }
  return create0(b0, b1, b2);
}

function negate(a){
  var neg0, neg1, neg2;
  neg0 = ~a.l + 1 & $intern_29;
  neg1 = ~a.m + (neg0 == 0?1:0) & $intern_29;
  neg2 = ~a.h + (neg0 == 0 && neg1 == 0?1:0) & $intern_30;
  a.l = neg0;
  a.m = neg1;
  a.h = neg2;
}

function numberOfLeadingZeros(a){
  var b1, b2;
  b2 = numberOfLeadingZeros_0(a.h);
  if (b2 == 32) {
    b1 = numberOfLeadingZeros_0(a.m);
    return b1 == 32?numberOfLeadingZeros_0(a.l) + 32:b1 + 20 - 10;
  }
   else {
    return b2 - 12;
  }
}

function powerOfTwo(a){
  var h, l, m;
  l = a.l;
  if ((l & l - 1) != 0) {
    return -1;
  }
  m = a.m;
  if ((m & m - 1) != 0) {
    return -1;
  }
  h = a.h;
  if ((h & h - 1) != 0) {
    return -1;
  }
  if (h == 0 && m == 0 && l == 0) {
    return -1;
  }
  if (h == 0 && m == 0 && l != 0) {
    return numberOfTrailingZeros(l);
  }
  if (h == 0 && m != 0 && l == 0) {
    return numberOfTrailingZeros(m) + 22;
  }
  if (h != 0 && m == 0 && l == 0) {
    return numberOfTrailingZeros(h) + 44;
  }
  return -1;
}

function toDoubleHelper(a){
  return a.l + a.m * $intern_31 + a.h * $intern_32;
}

function trialSubtract(a, b){
  var sum0, sum1, sum2;
  sum2 = a.h - b.h;
  if (sum2 < 0) {
    return false;
  }
  sum0 = a.l - b.l;
  sum1 = a.m - b.m + (sum0 >> 22);
  sum2 += sum1 >> 22;
  if (sum2 < 0) {
    return false;
  }
  a.l = sum0 & $intern_29;
  a.m = sum1 & $intern_29;
  a.h = sum2 & $intern_30;
  return true;
}

var remainder;
function add_1(a, b){
  var sum0, sum1, sum2;
  sum0 = a.l + b.l;
  sum1 = a.m + b.m + (sum0 >> 22);
  sum2 = a.h + b.h + (sum1 >> 22);
  return create0(sum0 & $intern_29, sum1 & $intern_29, sum2 & $intern_30);
}

function compare(a, b){
  var a0, a1, a2, b0, b1, b2, signA, signB;
  signA = a.h >> 19;
  signB = b.h >> 19;
  if (signA != signB) {
    return signB - signA;
  }
  a2 = a.h;
  b2 = b.h;
  if (a2 != b2) {
    return a2 - b2;
  }
  a1 = a.m;
  b1 = b.m;
  if (a1 != b1) {
    return a1 - b1;
  }
  a0 = a.l;
  b0 = b.l;
  return a0 - b0;
}

function fromDouble(value_0){
  var a0, a1, a2, negative, result;
  if (isNaN(value_0)) {
    return $clinit_BigLongLib$Const() , ZERO;
  }
  if (value_0 < -9223372036854775808) {
    return $clinit_BigLongLib$Const() , MIN_VALUE;
  }
  if (value_0 >= 9223372036854775807) {
    return $clinit_BigLongLib$Const() , MAX_VALUE;
  }
  negative = false;
  if (value_0 < 0) {
    negative = true;
    value_0 = -value_0;
  }
  a2 = 0;
  if (value_0 >= $intern_32) {
    a2 = round_int(value_0 / $intern_32);
    value_0 -= a2 * $intern_32;
  }
  a1 = 0;
  if (value_0 >= $intern_31) {
    a1 = round_int(value_0 / $intern_31);
    value_0 -= a1 * $intern_31;
  }
  a0 = round_int(value_0);
  result = create0(a0, a1, a2);
  negative && negate(result);
  return result;
}

function mul_0(a, b){
  var a0, a1, a2, a3, a4, b0, b1, b2, b3, b4, c0, c00, c01, c1, c10, c11, c12, c13, c2, c22, c23, c24, p0, p1, p2, p3, p4;
  a0 = a.l & 8191;
  a1 = a.l >> 13 | (a.m & 15) << 9;
  a2 = a.m >> 4 & 8191;
  a3 = a.m >> 17 | (a.h & 255) << 5;
  a4 = (a.h & 1048320) >> 8;
  b0 = b.l & 8191;
  b1 = b.l >> 13 | (b.m & 15) << 9;
  b2 = b.m >> 4 & 8191;
  b3 = b.m >> 17 | (b.h & 255) << 5;
  b4 = (b.h & 1048320) >> 8;
  p0 = a0 * b0;
  p1 = a1 * b0;
  p2 = a2 * b0;
  p3 = a3 * b0;
  p4 = a4 * b0;
  if (b1 != 0) {
    p1 += a0 * b1;
    p2 += a1 * b1;
    p3 += a2 * b1;
    p4 += a3 * b1;
  }
  if (b2 != 0) {
    p2 += a0 * b2;
    p3 += a1 * b2;
    p4 += a2 * b2;
  }
  if (b3 != 0) {
    p3 += a0 * b3;
    p4 += a1 * b3;
  }
  b4 != 0 && (p4 += a0 * b4);
  c00 = p0 & $intern_29;
  c01 = (p1 & 511) << 13;
  c0 = c00 + c01;
  c10 = p0 >> 22;
  c11 = p1 >> 9;
  c12 = (p2 & 262143) << 4;
  c13 = (p3 & 31) << 17;
  c1 = c10 + c11 + c12 + c13;
  c22 = p2 >> 18;
  c23 = p3 >> 5;
  c24 = (p4 & 4095) << 8;
  c2 = c22 + c23 + c24;
  c1 += c0 >> 22;
  c0 &= $intern_29;
  c2 += c1 >> 22;
  c1 &= $intern_29;
  c2 &= $intern_30;
  return create0(c0, c1, c2);
}

function neg(a){
  var neg0, neg1, neg2;
  neg0 = ~a.l + 1 & $intern_29;
  neg1 = ~a.m + (neg0 == 0?1:0) & $intern_29;
  neg2 = ~a.h + (neg0 == 0 && neg1 == 0?1:0) & $intern_30;
  return create0(neg0, neg1, neg2);
}

function or(a, b){
  return create0(a.l | b.l, a.m | b.m, a.h | b.h);
}

function shl(a, n){
  var res0, res1, res2;
  n &= 63;
  if (n < 22) {
    res0 = a.l << n;
    res1 = a.m << n | a.l >> 22 - n;
    res2 = a.h << n | a.m >> 22 - n;
  }
   else if (n < 44) {
    res0 = 0;
    res1 = a.l << n - 22;
    res2 = a.m << n - 22 | a.l >> 44 - n;
  }
   else {
    res0 = 0;
    res1 = 0;
    res2 = a.l << n - 44;
  }
  return create0(res0 & $intern_29, res1 & $intern_29, res2 & $intern_30);
}

function shr(a, n){
  var a2, negative, res0, res1, res2;
  n &= 63;
  a2 = a.h;
  negative = (a2 & $intern_19) != 0;
  negative && (a2 |= -1048576);
  if (n < 22) {
    res2 = a2 >> n;
    res1 = a.m >> n | a2 << 22 - n;
    res0 = a.l >> n | a.m << 22 - n;
  }
   else if (n < 44) {
    res2 = negative?$intern_30:0;
    res1 = a2 >> n - 22;
    res0 = a.m >> n - 22 | a2 << 44 - n;
  }
   else {
    res2 = negative?$intern_30:0;
    res1 = negative?$intern_29:0;
    res0 = a2 >> n - 44;
  }
  return create0(res0 & $intern_29, res1 & $intern_29, res2 & $intern_30);
}

function shru(a, n){
  var a2, res0, res1, res2;
  n &= 63;
  a2 = a.h & $intern_30;
  if (n < 22) {
    res2 = a2 >>> n;
    res1 = a.m >> n | a2 << 22 - n;
    res0 = a.l >> n | a.m << 22 - n;
  }
   else if (n < 44) {
    res2 = 0;
    res1 = a2 >>> n - 22;
    res0 = a.m >> n - 22 | a.h << 44 - n;
  }
   else {
    res2 = 0;
    res1 = 0;
    res0 = a2 >>> n - 44;
  }
  return create0(res0 & $intern_29, res1 & $intern_29, res2 & $intern_30);
}

function sub_0(a, b){
  var sum0, sum1, sum2;
  sum0 = a.l - b.l;
  sum1 = a.m - b.m + (sum0 >> 22);
  sum2 = a.h - b.h + (sum1 >> 22);
  return create0(sum0 & $intern_29, sum1 & $intern_29, sum2 & $intern_30);
}

function toDouble(a){
  if (compare(a, ($clinit_BigLongLib$Const() , ZERO)) < 0) {
    return -toDoubleHelper(neg(a));
  }
  return a.l + a.m * $intern_31 + a.h * $intern_32;
}

function toInt(a){
  return a.l | a.m << 22;
}

function toString_28(a){
  var digits, rem, res, tenPowerLong, zeroesNeeded;
  if (a.l == 0 && a.m == 0 && a.h == 0) {
    return '0';
  }
  if (a.h == $intern_19 && a.m == 0 && a.l == 0) {
    return '-9223372036854775808';
  }
  if (a.h >> 19 != 0) {
    return '-' + toString_28(neg(a));
  }
  rem = a;
  res = '';
  while (!(rem.l == 0 && rem.m == 0 && rem.h == 0)) {
    tenPowerLong = create(1000000000);
    rem = divMod(rem, tenPowerLong, true);
    digits = '' + toInt(remainder);
    if (!(rem.l == 0 && rem.m == 0 && rem.h == 0)) {
      zeroesNeeded = 9 - digits.length;
      for (; zeroesNeeded > 0; zeroesNeeded--) {
        digits = '0' + digits;
      }
    }
    res = digits + res;
  }
  return res;
}

function xor(a, b){
  return create0(a.l ^ b.l, a.m ^ b.m, a.h ^ b.h);
}

function $clinit_BigLongLib$Const(){
  $clinit_BigLongLib$Const = emptyMethod;
  MAX_VALUE = create0($intern_29, $intern_29, 524287);
  MIN_VALUE = create0(0, 0, $intern_19);
  ONE = create(1);
  create(2);
  ZERO = create(0);
}

var MAX_VALUE, MIN_VALUE, ONE, ZERO;
function safeClose(resource, mainException){
  var e;
  if (!resource) {
    return mainException;
  }
  try {
    resource.close_0();
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 9)) {
      e = $e0;
      if (!mainException) {
        return e;
      }
      $addSuppressed(mainException, e);
    }
     else 
      throw toJs($e0);
  }
  return mainException;
}

function toJava(e){
  var javaException;
  if (instanceOf(e, 9)) {
    return e;
  }
  javaException = e && e.__java$exception;
  if (!javaException) {
    javaException = new JavaScriptException(e);
    captureStackTrace(javaException);
  }
  return javaException;
}

function toJs(t){
  return t.backingJsObject;
}

function add_2(a, b){
  var result;
  if (isSmallLong0(a) && isSmallLong0(b)) {
    result = a + b;
    if ($intern_33 < result && result < $intern_32) {
      return result;
    }
  }
  return createLongEmul(add_1(isSmallLong0(a)?toBigLong(a):a, isSmallLong0(b)?toBigLong(b):b));
}

function compare_0(a, b){
  var result;
  if (isSmallLong0(a) && isSmallLong0(b)) {
    result = a - b;
    if (!isNaN(result)) {
      return result;
    }
  }
  return compare(isSmallLong0(a)?toBigLong(a):a, isSmallLong0(b)?toBigLong(b):b);
}

function createLongEmul(big_0){
  var a2;
  a2 = big_0.h;
  if (a2 == 0) {
    return big_0.l + big_0.m * $intern_31;
  }
  if (a2 == $intern_30) {
    return big_0.l + big_0.m * $intern_31 - $intern_32;
  }
  return big_0;
}

function div(a, b){
  var result;
  if (isSmallLong0(a) && isSmallLong0(b)) {
    result = a / b;
    if ($intern_33 < result && result < $intern_32) {
      return result < 0?$wnd.Math.ceil(result):$wnd.Math.floor(result);
    }
  }
  return createLongEmul(divMod(isSmallLong0(a)?toBigLong(a):a, isSmallLong0(b)?toBigLong(b):b, false));
}

function eq_0(a, b){
  return compare_0(a, b) == 0;
}

function fromDouble_0(value_0){
  if ($intern_33 < value_0 && value_0 < $intern_32) {
    return value_0 < 0?$wnd.Math.ceil(value_0):$wnd.Math.floor(value_0);
  }
  return createLongEmul(fromDouble(value_0));
}

function fromInt(value_0){
  return value_0;
}

function gt(a, b){
  return compare_0(a, b) > 0;
}

function gte_0(a, b){
  return compare_0(a, b) >= 0;
}

function isSmallLong0(value_0){
  return typeof value_0 === 'number';
}

function lt(a, b){
  return compare_0(a, b) < 0;
}

function lte(a, b){
  return compare_0(a, b) <= 0;
}

function mul_1(a, b){
  var result;
  if (isSmallLong0(a) && isSmallLong0(b)) {
    result = a * b;
    if ($intern_33 < result && result < $intern_32) {
      return result;
    }
  }
  return createLongEmul(mul_0(isSmallLong0(a)?toBigLong(a):a, isSmallLong0(b)?toBigLong(b):b));
}

function neg_0(a){
  var result;
  if (isSmallLong0(a)) {
    result = 0 - a;
    if (!isNaN(result)) {
      return result;
    }
  }
  return createLongEmul(neg(a));
}

function or_0(a, b){
  return createLongEmul(or(isSmallLong0(a)?toBigLong(a):a, isSmallLong0(b)?toBigLong(b):b));
}

function shl_0(a, n){
  return createLongEmul(shl(isSmallLong0(a)?toBigLong(a):a, n));
}

function shru_0(a, n){
  return createLongEmul(shru(isSmallLong0(a)?toBigLong(a):a, n));
}

function sub_1(a, b){
  var result;
  if (isSmallLong0(a) && isSmallLong0(b)) {
    result = a - b;
    if ($intern_33 < result && result < $intern_32) {
      return result;
    }
  }
  return createLongEmul(sub_0(isSmallLong0(a)?toBigLong(a):a, isSmallLong0(b)?toBigLong(b):b));
}

function toBigLong(longValue){
  var a0, a1, a3, value_0;
  value_0 = longValue;
  a3 = 0;
  if (value_0 < 0) {
    value_0 += $intern_32;
    a3 = $intern_30;
  }
  a1 = round_int(value_0 / $intern_31);
  a0 = round_int(value_0 - a1 * $intern_31);
  return create0(a0, a1, a3);
}

function toDouble_0(a){
  var d;
  if (isSmallLong0(a)) {
    d = a;
    return d == -0.?0:d;
  }
  return toDouble(a);
}

function toInt_0(a){
  if (isSmallLong0(a)) {
    return a | 0;
  }
  return toInt(a);
}

function toString_29(a){
  if (isSmallLong0(a)) {
    return '' + a;
  }
  return toString_28(a);
}

function xor_0(a, b){
  return createLongEmul(xor(isSmallLong0(a)?toBigLong(a):a, isSmallLong0(b)?toBigLong(b):b));
}

function init(){
  $wnd.setTimeout($entry(assertCompileTimeUserAgent));
  $onModuleLoad_0();
  $onModuleLoad(new LGDXWebApplication);
}

function $getMediaElement(this$static){
  return $clinit_DOM() , this$static.element;
}

function $play(this$static){
  ($clinit_DOM() , this$static.element).play();
}

function $setCurrentTime(this$static){
  ($clinit_DOM() , this$static.element).currentTime = 0;
}

function $setLoop(this$static){
  ($clinit_DOM() , this$static.element).setAttribute('loop', '');
}

defineClass(493, 542, $intern_24);
var Lcom_google_gwt_media_client_MediaBase_2_classLit = createForClass('com.google.gwt.media.client', 'MediaBase', 493);
function Audio_1(element){
  $setElement_0(this, ($clinit_DOM() , element));
}

function createIfSupported_0(){
  $clinit_FocusWidget();
  var element;
  !detector_0 && (detector_0 = new Audio$AudioElementSupportDetectedMaybe);
  element = $doc.createElement('audio');
  if (!element.canPlayType) {
    return null;
  }
  return new Audio_1(element);
}

defineClass(494, 493, $intern_24, Audio_1);
var detector_0;
var Lcom_google_gwt_media_client_Audio_2_classLit = createForClass('com.google.gwt.media.client', 'Audio', 494);
defineClass(550, 1, {});
var Lcom_google_gwt_media_client_Audio$AudioElementSupportDetector_2_classLit = createForClass('com.google.gwt.media.client', 'Audio/AudioElementSupportDetector', 550);
function Audio$AudioElementSupportDetectedMaybe(){
}

defineClass(495, 550, {}, Audio$AudioElementSupportDetectedMaybe);
var Lcom_google_gwt_media_client_Audio$AudioElementSupportDetectedMaybe_2_classLit = createForClass('com.google.gwt.media.client', 'Audio/AudioElementSupportDetectedMaybe', 495);
function $exec(this$static, input_0){
  return this$static.exec(input_0);
}

function $test(this$static, input_0){
  return this$static.test(input_0);
}

function SafeUriString(uri_0){
  this.uri_0 = uri_0;
}

defineClass(177, 1, {555:1, 177:1}, SafeUriString);
_.equals_0 = function equals_15(obj){
  if (!instanceOf(obj, 177)) {
    return false;
  }
  return $equals_5(this.uri_0, castTo(obj, 555).uri_0);
}
;
_.hashCode_0 = function hashCode_17(){
  return $hashCode_2(this.uri_0);
}
;
_.toString_0 = function toString_31(){
  return 'safe: "' + this.uri_0 + '"';
}
;
var Lcom_google_gwt_safehtml_shared_SafeUriString_2_classLit = createForClass('com.google.gwt.safehtml.shared', 'SafeUriString', 177);
function $clinit_UriUtils(){
  $clinit_UriUtils = emptyMethod;
  new RegExp('%5B', 'g');
  new RegExp('%5D', 'g');
}

function $getItem(this$static, key){
  return $getItem_0(this$static.storage, key);
}

function Storage_0(){
  this.storage = 'localStorage';
}

defineClass(242, 1, {}, Storage_0);
var localStorage_0;
var Lcom_google_gwt_storage_client_Storage_2_classLit = createForClass('com.google.gwt.storage.client', 'Storage', 242);
function $clinit_Storage$StorageSupportDetector(){
  $clinit_Storage$StorageSupportDetector = emptyMethod;
  localStorageSupported = checkStorageSupport('localStorage');
  checkStorageSupport('sessionStorage');
}

function checkStorageSupport(storage){
  var c = '_gwt_dummy_';
  try {
    $wnd[storage].setItem(c, c);
    $wnd[storage].removeItem(c);
    return true;
  }
   catch (e) {
    return false;
  }
}

var localStorageSupported = false;
function $getItem_0(storage, key){
  return $wnd[storage].getItem(key);
}

function $subarray(this$static, begin, end){
  return this$static.subarray(begin, end);
}

function create_1(length_0){
  return new Float32Array(length_0);
}

function create_2(buffer, byteOffset, length_0){
  return new Float32Array(buffer, byteOffset, length_0);
}

function create_3(buffer, byteOffset, length_0){
  return new Float64Array(buffer, byteOffset, length_0);
}

function $subarray_0(this$static, begin, end){
  return this$static.subarray(begin, end);
}

function create_4(buffer, byteOffset, length_0){
  return new Int16Array(buffer, byteOffset, length_0);
}

function create_5(buffer, byteOffset, length_0){
  return new Int32Array(buffer, byteOffset, length_0);
}

function $subarray_1(this$static, begin, end){
  return this$static.subarray(begin, end);
}

function create_6(length_0){
  return new Int8Array(length_0);
}

function create_7(buffer){
  return new Int8Array(buffer);
}

function create_8(buffer, byteOffset, length_0){
  return new Uint8Array(buffer, byteOffset, length_0);
}

function $clinit_DOM(){
  $clinit_DOM = emptyMethod;
  $clinit_DOMImplMozilla();
}

function dispatchEvent_0(evt, elem, listener){
  $clinit_DOM();
  var prevCurrentEvent;
  prevCurrentEvent = currentEvent;
  currentEvent = evt;
  elem == sCaptureElem && $eventGetTypeInt(evt.type) == 8192 && (sCaptureElem = null);
  listener.onBrowserEvent(evt);
  currentEvent = prevCurrentEvent;
}

function resolve(maybePotential){
  $clinit_DOM();
  return maybePotential.__gwt_resolve?maybePotential.__gwt_resolve():maybePotential;
}

var currentEvent = null, sCaptureElem;
function $onModuleLoad_0(){
  var allowedModes, currentMode, i;
  currentMode = $doc.compatMode;
  allowedModes = stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_String_2_classLit, 1), $intern_11, 2, 6, ['CSS1Compat']);
  for (i = 0; i < allowedModes.length; i++) {
    if ($equals_5(allowedModes[i], currentMode)) {
      return;
    }
  }
  allowedModes.length == 1 && $equals_5('CSS1Compat', allowedModes[0]) && $equals_5('BackCompat', currentMode)?"GWT no longer supports Quirks Mode (document.compatMode=' BackCompat').<br>Make sure your application's host HTML page has a Standards Mode (document.compatMode=' CSS1Compat') doctype,<br>e.g. by using &lt;!doctype html&gt; at the start of your application's HTML page.<br><br>To continue using this unsupported rendering mode and risk layout problems, suppress this message by adding<br>the following line to your*.gwt.xml module file:<br>&nbsp;&nbsp;&lt;extend-configuration-property name=\"document.compatMode\" value=\"" + currentMode + '"/&gt;':"Your *.gwt.xml module configuration prohibits the use of the current document rendering mode (document.compatMode=' " + currentMode + "').<br>Modify your application's host HTML page doctype, or update your custom " + "'document.compatMode' configuration property settings.";
}

function sinkEvents(elem){
  $clinit_DOM();
  $sinkEvents(elem, $intern_34);
}

function addCloseHandler(handler){
  maybeInitializeCloseHandlers();
  return addHandler(TYPE?TYPE:(TYPE = new GwtEvent$Type), handler);
}

function addHandler(type_0, handler){
  return $addHandler((!handlers_0 && (handlers_0 = new Window$WindowHandlers) , handlers_0), type_0, handler);
}

function addResizeHandler(handler){
  maybeInitializeCloseHandlers();
  maybeInitializeResizeHandlers();
  return addHandler((!TYPE_0 && (TYPE_0 = new GwtEvent$Type) , TYPE_0), handler);
}

function maybeInitializeCloseHandlers(){
  if (!closeHandlersInitialized) {
    $initWindowCloseHandler();
    closeHandlersInitialized = true;
  }
}

function maybeInitializeResizeHandlers(){
  if (!resizeHandlersInitialized) {
    $initWindowResizeHandler();
    resizeHandlersInitialized = true;
  }
}

function onClosing(){
  var event_0;
  if (closeHandlersInitialized) {
    event_0 = new Window$ClosingEvent;
    !!handlers_0 && $fireEvent(handlers_0, event_0);
    return null;
  }
  return null;
}

function onResize(){
  var height, width_0;
  if (resizeHandlersInitialized) {
    width_0 = $getClientWidth($doc);
    height = $getClientHeight($doc);
    if (lastResizeWidth != width_0 || lastResizeHeight != height) {
      lastResizeWidth = width_0;
      lastResizeHeight = height;
      fire_1((!handlers_0 && (handlers_0 = new Window$WindowHandlers) , handlers_0), width_0, height);
    }
  }
}

var closeHandlersInitialized = false, handlers_0, lastResizeHeight = 0, lastResizeWidth = 0, resizeHandlersInitialized = false;
function $clinit_Window$ClosingEvent(){
  $clinit_Window$ClosingEvent = emptyMethod;
  TYPE_1 = new GwtEvent$Type;
}

function Window$ClosingEvent(){
  $clinit_Window$ClosingEvent();
}

defineClass(316, 531, {}, Window$ClosingEvent);
_.dispatch = function dispatch_1(handler){
  throwClassCastExceptionUnlessNull(handler);
  null.$_nullMethod();
}
;
_.getAssociatedType_0 = function getAssociatedType_5(){
  return TYPE_1;
}
;
_.getAssociatedType = function getAssociatedType_4(){
  return TYPE_1;
}
;
var TYPE_1;
var Lcom_google_gwt_user_client_Window$ClosingEvent_2_classLit = createForClass('com.google.gwt.user.client', 'Window/ClosingEvent', 316);
function Window$WindowHandlers(){
  this.eventBus = new HandlerManager$Bus;
  this.source = null;
}

defineClass(168, 227, {52:1}, Window$WindowHandlers);
var Lcom_google_gwt_user_client_Window$WindowHandlers_2_classLit = createForClass('com.google.gwt.user.client', 'Window/WindowHandlers', 168);
function $eventGetTypeInt(eventType){
  switch (eventType) {
    case 'blur':
      return 4096;
    case 'change':
      return 1024;
    case 'click':
      return 1;
    case 'dblclick':
      return 2;
    case 'focus':
      return 2048;
    case 'keydown':
      return 128;
    case 'keypress':
      return 256;
    case 'keyup':
      return 512;
    case 'load':
      return $intern_34;
    case 'losecapture':
      return 8192;
    case 'mousedown':
      return 4;
    case 'mousemove':
      return 64;
    case 'mouseout':
      return 32;
    case 'mouseover':
      return 16;
    case 'mouseup':
      return 8;
    case 'scroll':
      return $intern_15;
    case 'error':
      return $intern_35;
    case 'DOMMouseScroll':
    case 'mousewheel':
      return $intern_36;
    case 'contextmenu':
      return 262144;
    case 'paste':
      return $intern_19;
    case 'touchstart':
      return 1048576;
    case 'touchmove':
      return $intern_37;
    case 'touchend':
      return $intern_31;
    case 'touchcancel':
      return 8388608;
    case 'gesturestart':
      return $intern_18;
    case 'gesturechange':
      return $intern_38;
    case 'gestureend':
      return $intern_39;
    default:return -1;
  }
}

function $maybeInitializeEventSystem(){
  if (!eventSystemIsInitialized) {
    $initEventSystem();
    $initSyntheticMouseUpEvents();
    eventSystemIsInitialized = true;
  }
}

function getEventListener(elem){
  var maybeListener = elem.__listener;
  return !instanceOfJso(maybeListener) && instanceOf(maybeListener, 54)?maybeListener:null;
}

function setEventListener(elem, listener){
  elem.__listener = listener;
}

var eventSystemIsInitialized = false;
function $clinit_DOMImplStandard(){
  $clinit_DOMImplStandard = emptyMethod;
  bitlessEventDispatchers = {_default_:dispatchEvent_2, dragenter:dispatchDragEvent, dragover:dispatchDragEvent};
  captureEventDispatchers = {click:dispatchCapturedMouseEvent, dblclick:dispatchCapturedMouseEvent, mousedown:dispatchCapturedMouseEvent, mouseup:dispatchCapturedMouseEvent, mousemove:dispatchCapturedMouseEvent, mouseover:dispatchCapturedMouseEvent, mouseout:dispatchCapturedMouseEvent, mousewheel:dispatchCapturedMouseEvent, keydown:dispatchCapturedEvent, keyup:dispatchCapturedEvent, keypress:dispatchCapturedEvent, touchstart:dispatchCapturedMouseEvent, touchend:dispatchCapturedMouseEvent, touchmove:dispatchCapturedMouseEvent, touchcancel:dispatchCapturedMouseEvent, gesturestart:dispatchCapturedMouseEvent, gestureend:dispatchCapturedMouseEvent, gesturechange:dispatchCapturedMouseEvent};
}

function $initEventSystem(){
  dispatchEvent_1 = $entry(dispatchEvent_2);
  dispatchUnhandledEvent = $entry(dispatchUnhandledEvent_0);
  var foreach = foreach_0;
  var bitlessEvents = bitlessEventDispatchers;
  foreach(bitlessEvents, function(e, fn){
    bitlessEvents[e] = $entry(fn);
  }
  );
  var captureEvents_0 = captureEventDispatchers;
  foreach(captureEvents_0, function(e, fn){
    captureEvents_0[e] = $entry(fn);
  }
  );
  foreach(captureEvents_0, function(e, fn){
    $wnd.addEventListener(e, fn, true);
  }
  );
}

function $sinkEventsImpl(elem, bits){
  var chMask = (elem.__eventBits || 0) ^ bits;
  elem.__eventBits = bits;
  if (!chMask)
    return;
  chMask & 1 && (elem.onclick = bits & 1?dispatchEvent_1:null);
  chMask & 2 && (elem.ondblclick = bits & 2?dispatchEvent_1:null);
  chMask & 4 && (elem.onmousedown = bits & 4?dispatchEvent_1:null);
  chMask & 8 && (elem.onmouseup = bits & 8?dispatchEvent_1:null);
  chMask & 16 && (elem.onmouseover = bits & 16?dispatchEvent_1:null);
  chMask & 32 && (elem.onmouseout = bits & 32?dispatchEvent_1:null);
  chMask & 64 && (elem.onmousemove = bits & 64?dispatchEvent_1:null);
  chMask & 128 && (elem.onkeydown = bits & 128?dispatchEvent_1:null);
  chMask & 256 && (elem.onkeypress = bits & 256?dispatchEvent_1:null);
  chMask & 512 && (elem.onkeyup = bits & 512?dispatchEvent_1:null);
  chMask & 1024 && (elem.onchange = bits & 1024?dispatchEvent_1:null);
  chMask & 2048 && (elem.onfocus = bits & 2048?dispatchEvent_1:null);
  chMask & 4096 && (elem.onblur = bits & 4096?dispatchEvent_1:null);
  chMask & 8192 && (elem.onlosecapture = bits & 8192?dispatchEvent_1:null);
  chMask & $intern_15 && (elem.onscroll = bits & $intern_15?dispatchEvent_1:null);
  chMask & $intern_34 && (elem.onload = bits & $intern_34?dispatchUnhandledEvent:null);
  chMask & $intern_35 && (elem.onerror = bits & $intern_35?dispatchEvent_1:null);
  chMask & $intern_36 && (elem.onmousewheel = bits & $intern_36?dispatchEvent_1:null);
  chMask & 262144 && (elem.oncontextmenu = bits & 262144?dispatchEvent_1:null);
  chMask & $intern_19 && (elem.onpaste = bits & $intern_19?dispatchEvent_1:null);
  chMask & 1048576 && (elem.ontouchstart = bits & 1048576?dispatchEvent_1:null);
  chMask & $intern_37 && (elem.ontouchmove = bits & $intern_37?dispatchEvent_1:null);
  chMask & $intern_31 && (elem.ontouchend = bits & $intern_31?dispatchEvent_1:null);
  chMask & 8388608 && (elem.ontouchcancel = bits & 8388608?dispatchEvent_1:null);
  chMask & $intern_18 && (elem.ongesturestart = bits & $intern_18?dispatchEvent_1:null);
  chMask & $intern_38 && (elem.ongesturechange = bits & $intern_38?dispatchEvent_1:null);
  chMask & $intern_39 && (elem.ongestureend = bits & $intern_39?dispatchEvent_1:null);
}

function dispatchCapturedEvent(evt){
  $clinit_DOM();
}

function dispatchCapturedMouseEvent(evt){
  $clinit_DOMImplStandard();
  $clinit_DOM();
  return;
}

function dispatchDragEvent(evt){
  evt.preventDefault();
  dispatchEvent_2(evt);
}

function dispatchEvent_2(evt){
  var element;
  element = getFirstAncestorWithListener(evt);
  if (!element) {
    return;
  }
  dispatchEvent_0(evt, element.nodeType != 1?null:element, getEventListener(element));
}

function dispatchUnhandledEvent_0(evt){
  var element;
  element = evt.currentTarget;
  element['__gwtLastUnhandledEvent'] = evt.type;
  dispatchEvent_2(evt);
}

function getFirstAncestorWithListener(evt){
  var curElem;
  curElem = evt.currentTarget;
  while (!!curElem && !getEventListener(curElem)) {
    curElem = curElem.parentNode;
  }
  return curElem;
}

var bitlessEventDispatchers, captureElem, captureEventDispatchers, dispatchEvent_1, dispatchUnhandledEvent;
function $clinit_DOMImplMozilla(){
  $clinit_DOMImplMozilla = emptyMethod;
  $clinit_DOMImplStandard();
  captureEventDispatchers['DOMMouseScroll'] = dispatchCapturedMouseEvent;
}

function $initSyntheticMouseUpEvents(){
  $wnd.addEventListener('mouseout', $entry(function(evt){
    var cap = ($clinit_DOMImplStandard() , captureElem);
    if (cap && !evt.relatedTarget) {
      if ('html' == evt.target.tagName.toLowerCase()) {
        var muEvent = $doc.createEvent('MouseEvents');
        muEvent.initMouseEvent('mouseup', true, true, $wnd, 0, evt.screenX, evt.screenY, evt.clientX, evt.clientY, evt.ctrlKey, evt.altKey, evt.shiftKey, evt.metaKey, evt.button, null);
        cap.dispatchEvent(muEvent);
      }
    }
  }
  ), true);
}

function $sinkEvents(elem, bits){
  $maybeInitializeEventSystem();
  $sinkEventsImpl(elem, bits);
  bits & $intern_36 && elem.addEventListener('DOMMouseScroll', ($clinit_DOMImplStandard() , dispatchEvent_1), false);
}

function foreach_0(map_0, fn){
  for (var e in map_0) {
    map_0.hasOwnProperty(e) && fn(e, map_0[e]);
  }
}

function $initWindowCloseHandler(){
  var oldOnBeforeUnload = $wnd.onbeforeunload;
  var oldOnUnload = $wnd.onunload;
  $wnd.onbeforeunload = function(evt){
    var ret, oldRet;
    try {
      ret = $entry(onClosing)();
    }
     finally {
      oldRet = oldOnBeforeUnload && oldOnBeforeUnload(evt);
    }
    if (ret != null) {
      return ret;
    }
    if (oldRet != null) {
      return oldRet;
    }
  }
  ;
  $wnd.onunload = $entry(function(evt){
    try {
      closeHandlersInitialized && fire_0((!handlers_0 && (handlers_0 = new Window$WindowHandlers) , handlers_0));
    }
     finally {
      oldOnUnload && oldOnUnload(evt);
      $wnd.onresize = null;
      $wnd.onscroll = null;
      $wnd.onbeforeunload = null;
      $wnd.onunload = null;
    }
  }
  );
}

function $initWindowResizeHandler(){
  var oldOnResize = $wnd.onresize;
  $wnd.onresize = $entry(function(evt){
    try {
      onResize();
    }
     finally {
      oldOnResize && oldOnResize(evt);
    }
  }
  );
}

function $clear_0(this$static){
  var it;
  it = new WidgetCollection$WidgetIterator(this$static.children);
  while (it.index_0 < it.this$01.size_0) {
    $next_5(it);
    $remove_5(it);
  }
}

defineClass(528, 39, $intern_24);
_.doAttachChildren = function doAttachChildren_0(){
  tryCommand(this, ($clinit_AttachDetachException() , attachCommand));
}
;
_.doDetachChildren = function doDetachChildren_0(){
  tryCommand(this, ($clinit_AttachDetachException() , detachCommand));
}
;
var Lcom_google_gwt_user_client_ui_Panel_2_classLit = createForClass('com.google.gwt.user.client.ui', 'Panel', 528);
function $add_3(this$static, child, container){
  $removeFromParent(child);
  $add_7(this$static.children, child);
  $clinit_DOM();
  $appendChild(container, resolve(child.element));
  $setParent(child, this$static);
}

function $remove_1(this$static, w){
  var elem;
  if (w.parent_0 != this$static) {
    return false;
  }
  try {
    $setParent(w, null);
  }
   finally {
    elem = ($clinit_DOM() , w.element);
    $removeChild((null , $getParentElement(elem)), elem);
    $remove_4(this$static.children, w);
  }
  return true;
}

function ComplexPanel(){
  this.children = new WidgetCollection(this);
}

defineClass(225, 528, $intern_24);
_.iterator = function iterator_12(){
  return new WidgetCollection$WidgetIterator(this.children);
}
;
_.remove = function remove_0(w){
  return $remove_1(this, w);
}
;
var Lcom_google_gwt_user_client_ui_ComplexPanel_2_classLit = createForClass('com.google.gwt.user.client.ui', 'ComplexPanel', 225);
function $add_4(this$static, w){
  $add_3(this$static, w, ($clinit_DOM() , this$static.element));
}

function changeToStaticPositioning(elem){
  elem.style['left'] = '';
  elem.style['top'] = '';
  elem.style['position'] = '';
}

defineClass(322, 225, $intern_24);
_.remove = function remove_1(w){
  var removed;
  removed = $remove_1(this, w);
  removed && changeToStaticPositioning(($clinit_DOM() , w.element));
  return removed;
}
;
var Lcom_google_gwt_user_client_ui_AbsolutePanel_2_classLit = createForClass('com.google.gwt.user.client.ui', 'AbsolutePanel', 322);
function $clinit_AttachDetachException(){
  $clinit_AttachDetachException = emptyMethod;
  attachCommand = new AttachDetachException$1;
  detachCommand = new AttachDetachException$2;
}

function AttachDetachException(causes){
  UmbrellaException_0.call(this, causes);
}

function tryCommand(hasWidgets, c){
  $clinit_AttachDetachException();
  var caught, e, w, w$iterator;
  caught = null;
  for (w$iterator = hasWidgets.iterator(); w$iterator.hasNext_0();) {
    w = castTo(w$iterator.next_1(), 39);
    try {
      c.execute_2(w);
    }
     catch ($e0) {
      $e0 = toJava($e0);
      if (instanceOf($e0, 9)) {
        e = $e0;
        !caught && (caught = new HashSet);
        $put_4(caught.map_0, e, caught);
      }
       else 
        throw toJs($e0);
    }
  }
  if (caught) {
    throw toJs(new AttachDetachException(caught));
  }
}

defineClass(313, 226, $intern_28, AttachDetachException);
var attachCommand, detachCommand;
var Lcom_google_gwt_user_client_ui_AttachDetachException_2_classLit = createForClass('com.google.gwt.user.client.ui', 'AttachDetachException', 313);
function AttachDetachException$1(){
}

defineClass(314, 1, {}, AttachDetachException$1);
_.execute_2 = function execute_3(w){
  w.onAttach();
}
;
var Lcom_google_gwt_user_client_ui_AttachDetachException$1_2_classLit = createForClass('com.google.gwt.user.client.ui', 'AttachDetachException/1', 314);
function AttachDetachException$2(){
}

defineClass(315, 1, {}, AttachDetachException$2);
_.execute_2 = function execute_4(w){
  $onDetach(w);
}
;
var Lcom_google_gwt_user_client_ui_AttachDetachException$2_2_classLit = createForClass('com.google.gwt.user.client.ui', 'AttachDetachException/2', 315);
function $setCellHorizontalAlignment(td, align_0){
  ($clinit_DOM() , td)['align'] = align_0.textAlignString;
}

function $setCellVerticalAlignment(td, align_0){
  $setCellVerticalAlignment_0(($clinit_DOM() , td), align_0);
}

function $setCellVerticalAlignment_0(td, align_0){
  td.style['verticalAlign'] = align_0.verticalAlignString;
}

defineClass(312, 225, $intern_24);
var Lcom_google_gwt_user_client_ui_CellPanel_2_classLit = createForClass('com.google.gwt.user.client.ui', 'CellPanel', 312);
function $setTextOrHtml(this$static){
  this$static.element.textContent = "Sorry, your browser doesn't seem to support WebGL";
  if (this$static.textDir != this$static.initialElementDir) {
    this$static.textDir = this$static.initialElementDir;
    setDirectionOnElement(this$static.element, this$static.initialElementDir);
  }
}

function DirectionalTextHelper(element){
  this.element = element;
  this.initialElementDir = getDirectionOnElement(element);
  this.textDir = this.initialElementDir;
}

defineClass(397, 1, {}, DirectionalTextHelper);
var Lcom_google_gwt_user_client_ui_DirectionalTextHelper_2_classLit = createForClass('com.google.gwt.user.client.ui', 'DirectionalTextHelper', 397);
function $clinit_HasHorizontalAlignment(){
  $clinit_HasHorizontalAlignment = emptyMethod;
  ALIGN_CENTER = new HasHorizontalAlignment$HorizontalAlignmentConstant(($clinit_Style$TextAlign() , 'center'));
  new HasHorizontalAlignment$HorizontalAlignmentConstant('justify');
  ALIGN_LEFT = new HasHorizontalAlignment$HorizontalAlignmentConstant('left');
  new HasHorizontalAlignment$HorizontalAlignmentConstant('right');
  ALIGN_LOCALE_START = ALIGN_LEFT;
  ALIGN_DEFAULT = ALIGN_LOCALE_START;
}

var ALIGN_CENTER, ALIGN_DEFAULT, ALIGN_LEFT, ALIGN_LOCALE_START;
function LabelBase(element){
  $setElement_0(this, ($clinit_DOM() , element));
  this.directionalTextHelper = new DirectionalTextHelper(this.element);
}

defineClass(148, 39, $intern_24);
var Lcom_google_gwt_user_client_ui_LabelBase_2_classLit = createForClass('com.google.gwt.user.client.ui', 'LabelBase', 148);
function Label(){
  LabelBase.call(this, $doc.createElement('div'));
  ($clinit_DOM() , this.element).className = 'gwt-Label';
  $setTextOrHtml(this.directionalTextHelper);
}

defineClass(163, 148, $intern_24, Label);
var Lcom_google_gwt_user_client_ui_Label_2_classLit = createForClass('com.google.gwt.user.client.ui', 'Label', 163);
function HTML(element){
  var lastArg;
  LabelBase.call(this, (lastArg = element , $equalsIgnoreCase('span', element.tagName) , lastArg));
}

defineClass(390, 163, $intern_24);
var Lcom_google_gwt_user_client_ui_HTML_2_classLit = createForClass('com.google.gwt.user.client.ui', 'HTML', 390);
function $clinit_HasVerticalAlignment(){
  $clinit_HasVerticalAlignment = emptyMethod;
  new HasVerticalAlignment$VerticalAlignmentConstant('bottom');
  ALIGN_MIDDLE = new HasVerticalAlignment$VerticalAlignmentConstant('middle');
  ALIGN_TOP = new HasVerticalAlignment$VerticalAlignmentConstant('top');
}

var ALIGN_MIDDLE, ALIGN_TOP;
defineClass(529, 1, {});
var Lcom_google_gwt_user_client_ui_HasHorizontalAlignment$AutoHorizontalAlignmentConstant_2_classLit = createForClass('com.google.gwt.user.client.ui', 'HasHorizontalAlignment/AutoHorizontalAlignmentConstant', 529);
function HasHorizontalAlignment$HorizontalAlignmentConstant(textAlignString){
  this.textAlignString = textAlignString;
}

defineClass(141, 529, {}, HasHorizontalAlignment$HorizontalAlignmentConstant);
var Lcom_google_gwt_user_client_ui_HasHorizontalAlignment$HorizontalAlignmentConstant_2_classLit = createForClass('com.google.gwt.user.client.ui', 'HasHorizontalAlignment/HorizontalAlignmentConstant', 141);
function HasVerticalAlignment$VerticalAlignmentConstant(verticalAlignString){
  this.verticalAlignString = verticalAlignString;
}

defineClass(167, 1, {}, HasVerticalAlignment$VerticalAlignmentConstant);
var Lcom_google_gwt_user_client_ui_HasVerticalAlignment$VerticalAlignmentConstant_2_classLit = createForClass('com.google.gwt.user.client.ui', 'HasVerticalAlignment/VerticalAlignmentConstant', 167);
function $clinit_Image(){
  $clinit_Image = emptyMethod;
  new HashMap;
}

function $changeState(this$static, newState){
  this$static.state_0 = newState;
}

function Image_1(url_0){
  $changeState(this, new Image$UnclippedState(this, url_0));
  ($clinit_DOM() , this.element).className = 'gwt-Image';
}

function Image_2(url_0){
  $clinit_Image();
  Image_1.call(this, ($clinit_UriUtils() , new SafeUriString(url_0)));
}

defineClass(216, 39, $intern_24, Image_2);
_.onBrowserEvent = function onBrowserEvent_0(event_0){
  $clinit_DOM();
  $eventGetTypeInt(event_0.type) == $intern_34 && !!this.state_0 && (this.element['__gwtLastUnhandledEvent'] = '' , undefined);
  $onBrowserEvent(this, event_0);
}
;
_.onLoad = function onLoad_0(){
  $onLoad(this.state_0, this);
}
;
var Lcom_google_gwt_user_client_ui_Image_2_classLit = createForClass('com.google.gwt.user.client.ui', 'Image', 216);
function $onLoad(this$static, image){
  var unhandledEvent;
  unhandledEvent = $getPropertyString(($clinit_DOM() , image.element), '__gwtLastUnhandledEvent');
  $equals_5('load', unhandledEvent) && (this$static.syntheticEventCommand = new Image$State$1(this$static, image) , $scheduleDeferred(($clinit_SchedulerImpl() , INSTANCE), this$static.syntheticEventCommand));
}

defineClass(388, 1, {});
_.syntheticEventCommand = null;
var Lcom_google_gwt_user_client_ui_Image$State_2_classLit = createForClass('com.google.gwt.user.client.ui', 'Image/State', 388);
function Image$State$1(this$1, val$image){
  this.this$11 = this$1;
  this.val$image2 = val$image;
}

defineClass(389, 1, {}, Image$State$1);
_.execute_1 = function execute_5(){
  var evt, evt_0;
  if (this.val$image2.state_0 != this.this$11 || this != this.this$11.syntheticEventCommand) {
    return;
  }
  this.this$11.syntheticEventCommand = null;
  if (!this.val$image2.attached) {
    $getImageElement(this.val$image2)['__gwtLastUnhandledEvent'] = 'load';
    return;
  }
  evt = (evt_0 = $doc.createEvent('HTMLEvents') , evt_0.initEvent('load', false, false) , evt_0);
  $dispatchEvent($getImageElement(this.val$image2), evt);
}
;
var Lcom_google_gwt_user_client_ui_Image$State$1_2_classLit = createForClass('com.google.gwt.user.client.ui', 'Image/State/1', 389);
function $getImageElement(image){
  return $clinit_DOM() , image.element;
}

function Image$UnclippedState(image, url_0){
  $replaceElement(image, $doc.createElement('img'));
  sinkEvents(($clinit_DOM() , image.element));
  image.eventsToSink == -1?$sinkEvents(image.element, 133398655 | (image.element.__eventBits || 0)):(image.eventsToSink |= 133398655);
  !!image.state_0 && (image.element['__gwtLastUnhandledEvent'] = '' , undefined);
  $setSrc_0(image.element, url_0.uri_0);
}

defineClass(236, 388, {}, Image$UnclippedState);
var Lcom_google_gwt_user_client_ui_Image$UnclippedState_2_classLit = createForClass('com.google.gwt.user.client.ui', 'Image/UnclippedState', 236);
function InlineHTML(){
  HTML.call(this, $doc.createElement('span'));
  ($clinit_DOM() , this.element).className = 'gwt-InlineHTML';
}

defineClass(290, 390, $intern_24, InlineHTML);
var Lcom_google_gwt_user_client_ui_InlineHTML_2_classLit = createForClass('com.google.gwt.user.client.ui', 'InlineHTML', 290);
function $clinit_RootPanel(){
  $clinit_RootPanel = emptyMethod;
  maybeDetachCommand = new RootPanel$1;
  rootPanels = new HashMap;
  widgetsToDetach = new HashSet;
}

function RootPanel(elem){
  ComplexPanel.call(this);
  $setElement_0(this, ($clinit_DOM() , elem));
  $onAttach(this);
}

function detachNow(widget){
  $clinit_RootPanel();
  try {
    $onDetach(widget);
  }
   finally {
    $remove_8(widgetsToDetach, widget);
  }
}

function detachWidgets(){
  $clinit_RootPanel();
  try {
    tryCommand(widgetsToDetach, maybeDetachCommand);
  }
   finally {
    $reset_4(widgetsToDetach.map_0);
    $reset_4(rootPanels);
  }
}

function get_0(){
  $clinit_RootPanel();
  var rp;
  rp = castTo($get_8(rootPanels, null), 142);
  if (rp) {
    return rp;
  }
  $size(rootPanels) == 0 && addCloseHandler(new RootPanel$2);
  rp = new RootPanel$DefaultRootPanel;
  $put_4(rootPanels, null, rp);
  $add_10(widgetsToDetach, rp);
  return rp;
}

defineClass(142, 322, $intern_40);
var maybeDetachCommand, rootPanels, widgetsToDetach;
var Lcom_google_gwt_user_client_ui_RootPanel_2_classLit = createForClass('com.google.gwt.user.client.ui', 'RootPanel', 142);
function RootPanel$1(){
}

defineClass(324, 1, {}, RootPanel$1);
_.execute_2 = function execute_6(w){
  w.attached && $onDetach(w);
}
;
var Lcom_google_gwt_user_client_ui_RootPanel$1_2_classLit = createForClass('com.google.gwt.user.client.ui', 'RootPanel/1', 324);
function RootPanel$2(){
}

defineClass(325, 1, {552:1, 293:1}, RootPanel$2);
var Lcom_google_gwt_user_client_ui_RootPanel$2_2_classLit = createForClass('com.google.gwt.user.client.ui', 'RootPanel/2', 325);
function RootPanel$DefaultRootPanel(){
  RootPanel.call(this, $doc.body);
}

defineClass(323, 142, $intern_40, RootPanel$DefaultRootPanel);
var Lcom_google_gwt_user_client_ui_RootPanel$DefaultRootPanel_2_classLit = createForClass('com.google.gwt.user.client.ui', 'RootPanel/DefaultRootPanel', 323);
function $add_5(this$static, w){
  if (this$static.widget) {
    throw toJs(new IllegalStateException_0('SimplePanel can only contain one child widget'));
  }
  $setWidget(this$static, w);
}

function $remove_2(this$static, w){
  if (this$static.widget != w) {
    return false;
  }
  try {
    $setParent(w, null);
  }
   finally {
    $removeChild(($clinit_DOM() , this$static.element), w.element);
    this$static.widget = null;
  }
  return true;
}

function $setWidget(this$static, w){
  if (w == this$static.widget) {
    return;
  }
  $removeFromParent(w);
  !!this$static.widget && $remove_2(this$static, this$static.widget);
  this$static.widget = w;
  $clinit_DOM();
  $appendChild(this$static.element, resolve($getElement(this$static.widget)));
  $setParent(w, this$static);
}

function SimplePanel(){
  SimplePanel_0.call(this, ($clinit_DOM() , $doc.createElement('div')));
}

function SimplePanel_0(elem){
  $setElement_0(this, ($clinit_DOM() , elem));
}

defineClass(217, 528, $intern_24, SimplePanel);
_.iterator = function iterator_13(){
  return new SimplePanel$1(this);
}
;
_.remove = function remove_2(w){
  return $remove_2(this, w);
}
;
var Lcom_google_gwt_user_client_ui_SimplePanel_2_classLit = createForClass('com.google.gwt.user.client.ui', 'SimplePanel', 217);
function $next_4(this$static){
  if (!this$static.hasElement || !this$static.this$01.widget) {
    throw toJs(new NoSuchElementException);
  }
  this$static.hasElement = false;
  return this$static.this$01.widget;
}

function SimplePanel$1(this$0){
  this.this$01 = this$0;
  this.hasElement = !!this.this$01.widget;
}

defineClass(391, 1, {}, SimplePanel$1);
_.next_1 = function next_5(){
  return $next_4(this);
}
;
_.hasNext_0 = function hasNext_4(){
  return this.hasElement;
}
;
_.hasElement = false;
var Lcom_google_gwt_user_client_ui_SimplePanel$1_2_classLit = createForClass('com.google.gwt.user.client.ui', 'SimplePanel/1', 391);
function $add_6(this$static, w){
  var td, tr, td_0;
  tr = ($clinit_DOM() , $doc.createElement('tr'));
  td = (td_0 = $doc.createElement('td') , $setCellHorizontalAlignment(td_0, this$static.horzAlign) , $setCellVerticalAlignment(td_0, this$static.vertAlign) , td_0);
  $appendChild(tr, resolve(td));
  $appendChild(this$static.body_0, resolve(tr));
  $add_3(this$static, w, td);
}

function $setHorizontalAlignment(this$static, align_0){
  this$static.horzAlign = align_0;
}

function $setVerticalAlignment(this$static, align_0){
  this$static.vertAlign = align_0;
}

function VerticalPanel(){
  ComplexPanel.call(this);
  this.table = ($clinit_DOM() , $doc.createElement('table'));
  this.body_0 = $doc.createElement('tbody');
  $appendChild(this.table, resolve(this.body_0));
  $setElement(this, this.table);
  this.horzAlign = ($clinit_HasHorizontalAlignment() , ALIGN_DEFAULT);
  this.vertAlign = ($clinit_HasVerticalAlignment() , ALIGN_TOP);
  this.table['cellSpacing'] = '0';
  this.table['cellPadding'] = '0';
}

defineClass(162, 312, $intern_24, VerticalPanel);
_.remove = function remove_3(w){
  var removed, td;
  td = ($clinit_DOM() , $getParentElement(w.element));
  removed = $remove_1(this, w);
  removed && $removeChild(this.body_0, $getParentElement(td));
  return removed;
}
;
var Lcom_google_gwt_user_client_ui_VerticalPanel_2_classLit = createForClass('com.google.gwt.user.client.ui', 'VerticalPanel', 162);
function $add_7(this$static, w){
  $insert(this$static, w, this$static.size_0);
}

function $indexOf(this$static, w){
  var i;
  for (i = 0; i < this$static.size_0; ++i) {
    if (this$static.array[i] == w) {
      return i;
    }
  }
  return -1;
}

function $insert(this$static, w, beforeIndex){
  var i, i0, newArray;
  if (beforeIndex < 0 || beforeIndex > this$static.size_0) {
    throw toJs(new IndexOutOfBoundsException);
  }
  if (this$static.size_0 == this$static.array.length) {
    newArray = initUnidimensionalArray(Lcom_google_gwt_user_client_ui_Widget_2_classLit, $intern_9, 39, this$static.array.length * 2, 0, 1);
    for (i0 = 0; i0 < this$static.array.length; ++i0) {
      newArray[i0] = this$static.array[i0];
    }
    this$static.array = newArray;
  }
  ++this$static.size_0;
  for (i = this$static.size_0 - 1; i > beforeIndex; --i) {
    this$static.array[i] = this$static.array[i - 1];
  }
  this$static.array[beforeIndex] = w;
}

function $remove_3(this$static, index_0){
  var i;
  if (index_0 < 0 || index_0 >= this$static.size_0) {
    throw toJs(new IndexOutOfBoundsException);
  }
  --this$static.size_0;
  for (i = index_0; i < this$static.size_0; ++i) {
    this$static.array[i] = this$static.array[i + 1];
  }
  this$static.array[this$static.size_0] = null;
}

function $remove_4(this$static, w){
  var index_0;
  index_0 = $indexOf(this$static, w);
  if (index_0 == -1) {
    throw toJs(new NoSuchElementException);
  }
  $remove_3(this$static, index_0);
}

function WidgetCollection(parent_0){
  this.parent_0 = parent_0;
  this.array = initUnidimensionalArray(Lcom_google_gwt_user_client_ui_Widget_2_classLit, $intern_9, 39, 4, 0, 1);
}

defineClass(378, 1, {}, WidgetCollection);
_.iterator = function iterator_14(){
  return new WidgetCollection$WidgetIterator(this);
}
;
_.size_0 = 0;
var Lcom_google_gwt_user_client_ui_WidgetCollection_2_classLit = createForClass('com.google.gwt.user.client.ui', 'WidgetCollection', 378);
function $next_5(this$static){
  if (this$static.index_0 >= this$static.this$01.size_0) {
    throw toJs(new NoSuchElementException);
  }
  this$static.currentWidget = this$static.this$01.array[this$static.index_0];
  ++this$static.index_0;
  return this$static.currentWidget;
}

function $remove_5(this$static){
  if (!this$static.currentWidget) {
    throw toJs(new IllegalStateException);
  }
  this$static.this$01.parent_0.remove(this$static.currentWidget);
  --this$static.index_0;
  this$static.currentWidget = null;
}

function WidgetCollection$WidgetIterator(this$0){
  this.this$01 = this$0;
}

defineClass(173, 1, {}, WidgetCollection$WidgetIterator);
_.next_1 = function next_6(){
  return $next_5(this);
}
;
_.hasNext_0 = function hasNext_5(){
  return this.index_0 < this.this$01.size_0;
}
;
_.index_0 = 0;
var Lcom_google_gwt_user_client_ui_WidgetCollection$WidgetIterator_2_classLit = createForClass('com.google.gwt.user.client.ui', 'WidgetCollection/WidgetIterator', 173);
function $clinit_FocusImpl(){
  $clinit_FocusImpl = emptyMethod;
  implPanel = new FocusImplStandard;
  implPanel?new FocusImpl:implPanel;
}

function FocusImpl(){
}

defineClass(417, 1, {}, FocusImpl);
var implPanel;
var Lcom_google_gwt_user_client_ui_impl_FocusImpl_2_classLit = createForClass('com.google.gwt.user.client.ui.impl', 'FocusImpl', 417);
function FocusImplStandard(){
}

defineClass(427, 417, {}, FocusImplStandard);
var Lcom_google_gwt_user_client_ui_impl_FocusImplStandard_2_classLit = createForClass('com.google.gwt.user.client.ui.impl', 'FocusImplStandard', 427);
function assertCompileTimeUserAgent(){
  var runtimeValue;
  runtimeValue = $getRuntimeValue();
  if (!$equals_5('gecko1_8', runtimeValue)) {
    throw toJs(new UserAgentAsserter$UserAgentAssertionError(runtimeValue));
  }
}

function Error_0(message, cause){
  Throwable.call(this, message, cause);
}

defineClass(161, 9, $intern_20);
var Ljava_lang_Error_2_classLit = createForClass('java.lang', 'Error', 161);
defineClass(60, 161, $intern_20);
var Ljava_lang_AssertionError_2_classLit = createForClass('java.lang', 'AssertionError', 60);
function UserAgentAsserter$UserAgentAssertionError(runtimeValue){
  Error_0.call(this, 'Possible problem with your *.gwt.xml module file.\nThe compile time user.agent value (gecko1_8) does not match the runtime user.agent value (' + runtimeValue + ').\n' + 'Expect more errors.' == null?'null':toString_30('Possible problem with your *.gwt.xml module file.\nThe compile time user.agent value (gecko1_8) does not match the runtime user.agent value (' + runtimeValue + ').\n' + 'Expect more errors.'), instanceOf('Possible problem with your *.gwt.xml module file.\nThe compile time user.agent value (gecko1_8) does not match the runtime user.agent value (' + runtimeValue + ').\n' + 'Expect more errors.', 9)?castTo('Possible problem with your *.gwt.xml module file.\nThe compile time user.agent value (gecko1_8) does not match the runtime user.agent value (' + runtimeValue + ').\n' + 'Expect more errors.', 9):null);
}

defineClass(277, 60, $intern_20, UserAgentAsserter$UserAgentAssertionError);
var Lcom_google_gwt_useragent_client_UserAgentAsserter$UserAgentAssertionError_2_classLit = createForClass('com.google.gwt.useragent.client', 'UserAgentAsserter/UserAgentAssertionError', 277);
function $getRuntimeValue(){
  var ua = navigator.userAgent.toLowerCase();
  var docMode = $doc.documentMode;
  if (function(){
    return ua.indexOf('webkit') != -1;
  }
  ())
    return 'safari';
  if (function(){
    return ua.indexOf('gecko') != -1 || docMode >= 11;
  }
  ())
    return 'gecko1_8';
  return 'unknown';
}

function $attachShader(this$static, program, shader){
  this$static.attachShader(program, shader);
}

function $bindBuffer(this$static, target, buffer){
  this$static.bindBuffer(target, buffer);
}

function $bindTexture(this$static, target, texture){
  this$static.bindTexture(target, texture);
}

function $blendFuncSeparate(this$static, srcRGB, dstRGB, srcAlpha, dstAlpha){
  this$static.blendFuncSeparate(srcRGB, dstRGB, srcAlpha, dstAlpha);
}

function $bufferData(this$static, target, size_0, usage){
  this$static.bufferData(target, size_0, usage);
}

function $bufferData_0(this$static, target, data_0, usage){
  this$static.bufferData(target, data_0, usage);
}

function $bufferSubData(this$static, target, offset, data_0){
  this$static.bufferSubData(target, offset, data_0);
}

function $compileShader(this$static, shader){
  this$static.compileShader(shader);
}

function $createShader(this$static, type_0){
  return this$static.createShader(type_0);
}

function $disableVertexAttribArray(this$static, index_0){
  this$static.disableVertexAttribArray(index_0);
}

function $drawArrays(this$static, mode, first, count){
  this$static.drawArrays(mode, first, count);
}

function $drawElements(this$static, mode, count, type_0, offset){
  this$static.drawElements(mode, count, type_0, offset);
}

function $enableVertexAttribArray(this$static, index_0){
  this$static.enableVertexAttribArray(index_0);
}

function $getActiveAttrib(this$static, program, index_0){
  return this$static.getActiveAttrib(program, index_0);
}

function $getActiveUniform(this$static, program, index_0){
  return this$static.getActiveUniform(program, index_0);
}

function $getAttribLocation(this$static, program, name_0){
  return this$static.getAttribLocation(program, name_0);
}

function $getParameterString(this$static, pname){
  return this$static.getParameter(pname);
}

function $getProgramInfoLog(this$static, program){
  return this$static.getProgramInfoLog(program);
}

function $getProgramParameterb(this$static, program, pname){
  return this$static.getProgramParameter(program, pname);
}

function $getProgramParameteri(this$static, program, pname){
  return this$static.getProgramParameter(program, pname);
}

function $getShaderInfoLog(this$static, shader){
  return this$static.getShaderInfoLog(shader);
}

function $getShaderParameterb(this$static, shader, pname){
  return this$static.getShaderParameter(shader, pname);
}

function $getUniformLocation(this$static, program, name_0){
  return this$static.getUniformLocation(program, name_0);
}

function $linkProgram_0(this$static, program){
  this$static.linkProgram(program);
}

function $shaderSource(this$static, shader, source){
  this$static.shaderSource(shader, source);
}

function $texImage2D(this$static, target, level, internalformat, width_0, height, border, format, type_0, pixels){
  this$static.texImage2D(target, level, internalformat, width_0, height, border, format, type_0, pixels);
}

function $texImage2D_0(this$static, target, level, internalformat, format, type_0, canvas){
  this$static.texImage2D(target, level, internalformat, format, type_0, canvas);
}

function $texParameterf(this$static, target, pname, param){
  this$static.texParameterf(target, pname, param);
}

function $uniform1i(this$static, location_0, x_0){
  this$static.uniform1i(location_0, x_0);
}

function $uniformMatrix4fv(this$static, location_0, transpose, value_0){
  this$static.uniformMatrix4fv(location_0, transpose, value_0);
}

function $useProgram(this$static, program){
  this$static.useProgram(program);
}

function $vertexAttribPointer(this$static, indx, size_0, type_0, normalized, stride, offset){
  this$static.vertexAttribPointer(indx, size_0, type_0, normalized, stride, offset);
}

function $viewport(this$static, x_0, y_0, width_0, height){
  this$static.viewport(x_0, y_0, width_0, height);
}

function getContext(canvas, attributes){
  var names = ['experimental-webgl', 'webgl', 'moz-webgl', 'webkit-webgl', 'webkit-3d'];
  for (var i = 0; i < names.length; i++) {
    try {
      var ctx = canvas.getContext(names[i], attributes);
      if (ctx != null) {
        if ($wnd.WebGLDebugUtils) {
          $wnd.console && $wnd.console.log && console.log('WebGL debugging enabled');
          return $wnd.WebGLDebugUtils.makeDebugContext(ctx);
        }
        return ctx;
      }
    }
     catch (e) {
    }
  }
  return null;
}

function $setOnReadyStateChange(this$static, handler){
  var _this = this$static;
  this$static.onreadystatechange = $entry(function(){
    handler.onReadyStateChange(_this);
  }
  );
}

function $setResponseType(this$static, responseType){
  this$static.responseType = responseType;
}

function create_9(){
  return new $wnd.XMLHttpRequest;
}

function $clinit_XMLHttpRequest$ResponseType(){
  $clinit_XMLHttpRequest$ResponseType = emptyMethod;
  Default = new XMLHttpRequest$ResponseType('Default', 0, '');
  ArrayBuffer_0 = new XMLHttpRequest$ResponseType('ArrayBuffer', 1, 'arraybuffer');
}

function XMLHttpRequest$ResponseType(enum$name, enum$ordinal, responseTypeString){
  Enum.call(this, enum$name, enum$ordinal);
  this.responseTypeString = responseTypeString;
}

function values_12(){
  $clinit_XMLHttpRequest$ResponseType();
  return stampJavaTypeInfo(getClassLiteralForArray(Lcom_google_gwt_xhr_client_XMLHttpRequest$ResponseType_2_classLit, 1), $intern_1, 149, 0, [Default, ArrayBuffer_0]);
}

defineClass(149, 8, {149:1, 3:1, 7:1, 8:1}, XMLHttpRequest$ResponseType);
var ArrayBuffer_0, Default;
var Lcom_google_gwt_xhr_client_XMLHttpRequest$ResponseType_2_classLit = createForEnum('com.google.gwt.xhr.client', 'XMLHttpRequest/ResponseType', 149, values_12);
function SimpleEventBus$1(){
}

defineClass(320, 1, {}, SimpleEventBus$1);
var Lcom_google_web_bindery_event_shared_SimpleEventBus$1_2_classLit = createForClass('com.google.web.bindery.event.shared', 'SimpleEventBus/1', 320);
function SimpleEventBus$2(this$0, val$type, val$handler){
  this.this$01 = this$0;
  this.val$type2 = val$type;
  this.val$source3 = null;
  this.val$handler4 = val$handler;
}

defineClass(321, 1, {551:1}, SimpleEventBus$2);
var Lcom_google_web_bindery_event_shared_SimpleEventBus$2_2_classLit = createForClass('com.google.web.bindery.event.shared', 'SimpleEventBus/2', 321);
function $read_2(this$static, buffer, byteOffset, byteCount){
  return this$static.in_0.read_0(buffer, byteOffset, byteCount);
}

function FilterInputStream(in_0){
  this.in_0 = in_0;
}

defineClass(197, 541, {});
_.close_0 = function close_1(){
  this.in_0.close_0();
}
;
_.read = function read_2(){
  return this.in_0.read();
}
;
_.read_0 = function read_3(buffer, byteOffset, byteCount){
  return $read_2(this, buffer, byteOffset, byteCount);
}
;
var Ljava_io_FilterInputStream_2_classLit = createForClass('java.io', 'FilterInputStream', 197);
function BufferedInputStream(in_0){
  FilterInputStream.call(this, in_0);
}

defineClass(198, 197, {}, BufferedInputStream);
var Ljava_io_BufferedInputStream_2_classLit = createForClass('java.io', 'BufferedInputStream', 198);
function $read_3(this$static, buf){
  return this$static.read_1(buf, 0, buf.length);
}

defineClass(546, 1, {});
var Ljava_io_Reader_2_classLit = createForClass('java.io', 'Reader', 546);
function $readLine(this$static){
  var i, sb;
  sb = new StringBuilder;
  while (true) {
    this$static.position >= this$static.limit && (this$static.position = 0 , this$static.limit = $read_3(this$static.in_0, this$static.buffer_0));
    if (this$static.position >= this$static.limit) {
      return sb.string.length == 0?null:sb.string;
    }
    for (i = this$static.position; i < this$static.limit; ++i) {
      if (this$static.buffer_0[i] == 13) {
        $append_4(sb, this$static.buffer_0, this$static.position, i - this$static.position);
        this$static.position = i + 1;
        if (i + 1 < this$static.limit) {
          this$static.buffer_0[i + 1] == 10 && (this$static.position = i + 2);
        }
         else {
          this$static.position = 0;
          this$static.limit = $read_3(this$static.in_0, this$static.buffer_0);
          this$static.buffer_0[this$static.position] == 10 && (this$static.position += 1);
        }
        return sb.string;
      }
       else if (this$static.buffer_0[i] == 10) {
        $append_4(sb, this$static.buffer_0, this$static.position, i - this$static.position);
        this$static.position = i + 1;
        return sb.string;
      }
    }
    $append_4(sb, this$static.buffer_0, this$static.position, this$static.limit - this$static.position);
    this$static.position = this$static.limit;
  }
}

function BufferedReader(in_0){
  this.in_0 = in_0;
  this.buffer_0 = initUnidimensionalArray(C_classLit, $intern_2, 5, 8192, 15, 1);
}

defineClass(63, 546, {63:1}, BufferedReader);
_.close_0 = function close_2(){
  this.in_0.close_0();
}
;
_.read_1 = function read_4(b, offset, length_0){
  var c, count, remaining;
  count = 0;
  this.position >= this.limit && length_0 < this.buffer_0.length && (this.position = 0 , this.limit = $read_3(this.in_0, this.buffer_0));
  if (this.position < this.limit) {
    remaining = this.limit - this.position;
    remaining > length_0 && (remaining = length_0);
    arraycopy(this.buffer_0, this.position, b, offset, remaining);
    count += remaining;
    this.position += remaining;
    offset += remaining;
    length_0 -= remaining;
  }
  if (length_0 > 0) {
    c = this.in_0.read_1(b, offset, length_0);
    c == -1?count == 0 && (count = -1):(count += c);
  }
  return count;
}
;
_.limit = 0;
_.position = 0;
var Ljava_io_BufferedReader_2_classLit = createForClass('java.io', 'BufferedReader', 63);
function $skip(this$static, byteCount){
  var temp;
  if (compare_0(byteCount, 0) <= 0) {
    return 0;
  }
  temp = this$static.pos;
  this$static.pos = lt(this$static.count - this$static.pos, byteCount)?this$static.count:toInt_0(add_2(this$static.pos, byteCount));
  return this$static.pos - temp;
}

function ByteArrayInputStream(buf){
  this.mark = 0;
  this.buf = buf;
  this.count = buf.length;
}

defineClass(84, 541, {}, ByteArrayInputStream);
_.close_0 = function close_3(){
}
;
_.read = function read_5(){
  return this.pos < this.count?this.buf[this.pos++] & 255:-1;
}
;
_.read_0 = function read_6(buffer, byteOffset, byteCount){
  var copylen;
  checkCriticalNotNull(buffer);
  checkOffsetAndCount(buffer.length, byteOffset, byteCount);
  if (this.pos >= this.count) {
    return -1;
  }
  if (byteCount == 0) {
    return 0;
  }
  copylen = this.count - this.pos < byteCount?this.count - this.pos:byteCount;
  arraycopy(this.buf, this.pos, buffer, byteOffset, copylen);
  this.pos += copylen;
  return copylen;
}
;
_.count = 0;
_.mark = 0;
_.pos = 0;
var Ljava_io_ByteArrayInputStream_2_classLit = createForClass('java.io', 'ByteArrayInputStream', 84);
function $write(this$static, buffer){
  checkCriticalNotNull(buffer);
  $write_0(this$static, buffer, buffer.length);
}

defineClass(526, 1, {});
_.close_0 = function close_4(){
}
;
var Ljava_io_OutputStream_2_classLit = createForClass('java.io', 'OutputStream', 526);
function $expand(this$static, i){
  var newbuf;
  if (this$static.count + i <= this$static.buf.length) {
    return;
  }
  newbuf = initUnidimensionalArray(B_classLit, $intern_6, 5, (this$static.count + i) * 2, 15, 1);
  arraycopy(this$static.buf, 0, newbuf, 0, this$static.count);
  this$static.buf = newbuf;
}

function $toByteArray(this$static){
  var newArray;
  newArray = initUnidimensionalArray(B_classLit, $intern_6, 5, this$static.count, 15, 1);
  arraycopy(this$static.buf, 0, newArray, 0, this$static.count);
  return newArray;
}

function $write_0(this$static, buffer, len){
  checkCriticalNotNull(buffer);
  checkOffsetAndCount(buffer.length, 0, len);
  if (len == 0) {
    return;
  }
  $expand(this$static, len);
  arraycopy(buffer, 0, this$static.buf, this$static.count, len);
  this$static.count += len;
}

function ByteArrayOutputStream(){
  this.buf = initUnidimensionalArray(B_classLit, $intern_6, 5, 32, 15, 1);
}

defineClass(249, 526, {}, ByteArrayOutputStream);
_.close_0 = function close_5(){
}
;
_.toString_0 = function toString_32(){
  var lastArg;
  return valueOf_3($decodeString(this.buf, (lastArg = this.count , $clinit_EmulatedCharset() , lastArg)));
}
;
_.count = 0;
var Ljava_io_ByteArrayOutputStream_2_classLit = createForClass('java.io', 'ByteArrayOutputStream', 249);
function $readByte(this$static){
  var i;
  i = this$static.is.read();
  if (i == -1) {
    throw toJs(new EOFException);
  }
  return i << 24 >> 24;
}

function DataInputStream(is){
  FilterInputStream.call(this, is);
  this.is = is;
}

defineClass(254, 197, {}, DataInputStream);
_.close_0 = function close_6(){
  this.is.close_0();
}
;
_.read = function read_7(){
  return this.is.read();
}
;
var Ljava_io_DataInputStream_2_classLit = createForClass('java.io', 'DataInputStream', 254);
function IOException(message){
  Exception_0.call(this, message);
}

function IOException_0(message, throwable){
  Exception_1.call(this, message, throwable);
}

function IOException_1(throwable){
  Exception_2.call(this, throwable);
}

defineClass(22, 6, $intern_41, IOException, IOException_0, IOException_1);
var Ljava_io_IOException_2_classLit = createForClass('java.io', 'IOException', 22);
function EOFException(){
  Exception.call(this);
}

defineClass(492, 22, $intern_41, EOFException);
var Ljava_io_EOFException_2_classLit = createForClass('java.io', 'EOFException', 492);
function $clinit_File(){
  $clinit_File = emptyMethod;
  ROOT = new File_1('');
  LocalStorage = (!localStorage_0 && ($clinit_Storage$StorageSupportDetector() , localStorageSupported) && (localStorage_0 = new Storage_0) , localStorage_0);
}

function $getAbsoluteFile(this$static){
  if ($isAbsolute(this$static)) {
    return this$static;
  }
  if (!this$static.parent_0) {
    return new File_0(ROOT, this$static.name_0);
  }
  return new File_0($getAbsoluteFile(this$static.parent_0), this$static.name_0);
}

function $getCanonicalFile(this$static){
  var cParent;
  cParent = !this$static.parent_0?null:$getCanonicalFile(this$static.parent_0);
  if ($equals_5(this$static.name_0, '.')) {
    return !cParent?ROOT:cParent;
  }
  !!cParent && $equals_5(cParent.name_0, '') && (cParent = null);
  if ($equals_5(this$static.name_0, '..')) {
    if (!cParent) {
      return ROOT;
    }
    if (!cParent.parent_0) {
      return ROOT;
    }
    return cParent.parent_0;
  }
  if (!cParent && !$equals_5(this$static.name_0, '')) {
    return new File_0(ROOT, this$static.name_0);
  }
  return new File_0(cParent, this$static.name_0);
}

function $getPath(this$static){
  return !this$static.parent_0?this$static.name_0:$getPath(this$static.parent_0) + '/' + this$static.name_0;
}

function $hashCode_1(this$static){
  return this$static.parent_0?$hashCode_1(this$static.parent_0) + $hashCode_2(this$static.name_0):$hashCode_2(this$static.name_0);
}

function $isAbsolute(this$static){
  if ($equals_5(this$static.name_0, '') && !this$static.parent_0) {
    return true;
  }
  if (!this$static.parent_0) {
    return false;
  }
  return $isAbsolute(this$static.parent_0);
}

function File_0(parent_0, child){
  $clinit_File();
  this.parent_0 = parent_0;
  this.name_0 = child;
}

function File_1(pathname){
  $clinit_File();
  var cut, suffixlength;
  while (suffixlength = '/'.length , $equals_5(pathname.substr(pathname.length - suffixlength, suffixlength), '/') && pathname.length > 0) {
    pathname = $substring_0(pathname, 0, pathname.length - 1);
  }
  cut = $lastIndexOf(pathname, fromCodePoint(47));
  if (cut == -1) {
    this.name_0 = pathname;
  }
   else if (cut == 0) {
    this.name_0 = (checkCriticalStringElementIndex(cut, pathname.length + 1) , pathname.substr(cut));
    this.parent_0 = $equals_5(this.name_0, '')?null:ROOT;
  }
   else {
    this.name_0 = (checkCriticalStringElementIndex(cut + 1, pathname.length + 1) , pathname.substr(cut + 1));
    this.parent_0 = new File_1((checkCriticalStringBounds(0, cut, pathname.length) , pathname.substr(0, cut)));
  }
}

defineClass(28, 1, {28:1}, File_0, File_1);
_.equals_0 = function equals_16(obj){
  if (!instanceOf(obj, 28)) {
    return false;
  }
  return $equals_5($getPath(this), $getPath(castTo(obj, 28)));
}
;
_.hashCode_0 = function hashCode_18(){
  return $hashCode_1(this);
}
;
_.toString_0 = function toString_33(){
  return this.name_0;
}
;
var LocalStorage, ROOT;
var Ljava_io_File_2_classLit = createForClass('java.io', 'File', 28);
defineClass(527, 526, {});
_.close_0 = function close_7(){
  var e, thrown;
  thrown = null;
  try {
    this.flush();
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 9)) {
      e = $e0;
      thrown = e;
    }
     else 
      throw toJs($e0);
  }
  try {
    null.$_nullMethod();
  }
   catch ($e1) {
    $e1 = toJava($e1);
    if (instanceOf($e1, 9)) {
      e = $e1;
      !thrown && (thrown = e);
    }
     else 
      throw toJs($e1);
  }
  if (thrown) {
    throw toJs(new IOException_1(thrown));
  }
}
;
_.flush = function flush(){
  null.$_nullMethod();
}
;
var Ljava_io_FilterOutputStream_2_classLit = createForClass('java.io', 'FilterOutputStream', 527);
function checkOffsetAndCount(length_0, offset, count){
  if (offset < 0 || count < 0 || offset + count > length_0) {
    throw toJs(new IndexOutOfBoundsException);
  }
}

function InputStreamReader(in_0){
  this.in_0 = in_0;
  this.utf8Decoder = new Utf8Decoder;
}

defineClass(193, 546, {}, InputStreamReader);
_.close_0 = function close_8(){
}
;
_.read_1 = function read_8(b, offset, length_0){
  var buffer, c;
  buffer = initUnidimensionalArray(B_classLit, $intern_6, 5, length_0, 15, 1);
  c = $read_0(this.in_0, buffer);
  return c <= 0?c:$decode_0(this.utf8Decoder, buffer, c, b, offset);
}
;
var Ljava_io_InputStreamReader_2_classLit = createForClass('java.io', 'InputStreamReader', 193);
function PrintStream(){
}

defineClass(311, 527, {}, PrintStream);
_.close_0 = function close_9(){
}
;
_.flush = function flush_0(){
}
;
var Ljava_io_PrintStream_2_classLit = createForClass('java.io', 'PrintStream', 311);
function StringReader(in_0){
  this.in_0 = in_0;
}

defineClass(202, 546, {}, StringReader);
_.close_0 = function close_10(){
}
;
_.read_1 = function read_9(b, offset, length_0){
  if (length_0 > this.in_0.length - this.position) {
    length_0 = this.in_0.length - this.position;
    if (length_0 <= 0) {
      return -1;
    }
  }
  $getChars(this.in_0, this.position, this.position + length_0, b, offset);
  this.position += length_0;
  return length_0;
}
;
_.position = 0;
var Ljava_io_StringReader_2_classLit = createForClass('java.io', 'StringReader', 202);
function AbstractStringBuilder(string){
  this.string = string;
}

defineClass(165, 1, {278:1});
_.toString_0 = function toString_34(){
  return this.string;
}
;
var Ljava_lang_AbstractStringBuilder_2_classLit = createForClass('java.lang', 'AbstractStringBuilder', 165);
function ArithmeticException(){
  RuntimeException_0.call(this, 'divide by zero');
}

defineClass(326, 27, $intern_21, ArithmeticException);
var Ljava_lang_ArithmeticException_2_classLit = createForClass('java.lang', 'ArithmeticException', 326);
function IndexOutOfBoundsException(){
  RuntimeException.call(this);
}

function IndexOutOfBoundsException_0(message){
  RuntimeException_0.call(this, message);
}

defineClass(36, 27, $intern_21, IndexOutOfBoundsException, IndexOutOfBoundsException_0);
var Ljava_lang_IndexOutOfBoundsException_2_classLit = createForClass('java.lang', 'IndexOutOfBoundsException', 36);
function ArrayIndexOutOfBoundsException(msg){
  IndexOutOfBoundsException_0.call(this, msg);
}

defineClass(399, 36, $intern_21, ArrayIndexOutOfBoundsException);
var Ljava_lang_ArrayIndexOutOfBoundsException_2_classLit = createForClass('java.lang', 'ArrayIndexOutOfBoundsException', 399);
function ArrayStoreException(){
  RuntimeException.call(this);
}

function ArrayStoreException_0(message){
  RuntimeException_0.call(this, message);
}

defineClass(228, 27, $intern_21, ArrayStoreException, ArrayStoreException_0);
var Ljava_lang_ArrayStoreException_2_classLit = createForClass('java.lang', 'ArrayStoreException', 228);
function $clinit_Boolean(){
  $clinit_Boolean = emptyMethod;
  TRUE = true;
}

function $booleanValue(this$static){
  return checkCriticalNotNull(this$static) , this$static;
}

function $compareTo_1(this$static, b){
  return compare_1((checkCriticalNotNull(this$static) , this$static), (checkCriticalNotNull(b) , b));
}

function compare_1(x_0, y_0){
  $clinit_Boolean();
  return x_0 == y_0?0:x_0?1:-1;
}

function compareTo_Ljava_lang_Object__I__devirtual$(this$static, other){
  $clinit_Boolean();
  return instanceOfString(this$static)?$compareTo_6(this$static, castToString(other)):instanceOfDouble(this$static)?$compareTo_3(this$static, (checkCriticalType(other == null || instanceOfDouble(other)) , other)):instanceOfBoolean(this$static)?$compareTo_1(this$static, castToBoolean(other)):this$static.compareTo(other);
}

booleanCastMap = {3:1, 303:1, 7:1};
var TRUE;
var Ljava_lang_Boolean_2_classLit = createForClass('java.lang', 'Boolean', 303);
function $compareTo_2(this$static, c){
  return this$static.value_0 - c.value_0;
}

function Character(value_0){
  this.value_0 = value_0;
}

function codePointAt(cs, index_0, limit){
  var hiSurrogate, loSurrogate;
  hiSurrogate = $charAt(cs, index_0++);
  if (hiSurrogate >= 55296 && hiSurrogate <= 56319 && index_0 < limit && isLowSurrogate(loSurrogate = (checkCriticalStringElementIndex(index_0, cs.length) , cs.charCodeAt(index_0)))) {
    return $intern_35 + ((hiSurrogate & 1023) << 10) + (loSurrogate & 1023);
  }
  return hiSurrogate;
}

function digit_0(c, radix){
  if (radix < 2 || radix > 36) {
    return -1;
  }
  if (c >= 48 && c < 48 + $wnd.Math.min(radix, 10)) {
    return c - 48;
  }
  if (c >= 97 && c < radix + 97 - 10) {
    return c - 97 + 10;
  }
  if (c >= 65 && c < radix + 65 - 10) {
    return c - 65 + 10;
  }
  return -1;
}

function forDigit(digit){
  var overBaseTen;
  overBaseTen = digit - 10;
  return (overBaseTen < 0?48 + digit:97 + overBaseTen) & $intern_5;
}

function isDigit(c){
  digitRegex == null && (digitRegex = new RegExp('\\d'));
  return digitRegex.test(String.fromCharCode(c));
}

function isLowSurrogate(ch_0){
  return ch_0 >= 56320 && ch_0 <= 57343;
}

function isWhitespace(ch_0){
  whitespaceRegex == null && (whitespaceRegex = new RegExp('[\\u1680\\u180E\\u2000-\\u2006\\u2008-\\u200A\\u2028\\u2029\\u205F\\u3000\\uFEFF]|[\\t-\\r ]|[\\x1C-\\x1F]'));
  return whitespaceRegex.test(ch_0);
}

function toChars(codePoint, dst, dstIndex){
  checkCriticalArgument(codePoint >= 0 && codePoint <= 1114111);
  if (codePoint >= $intern_35) {
    dst[dstIndex++] = 55296 + (codePoint - $intern_35 >> 10 & 1023) & $intern_5;
    dst[dstIndex] = 56320 + (codePoint - $intern_35 & 1023) & $intern_5;
    return 2;
  }
   else {
    dst[dstIndex] = codePoint & $intern_5;
    return 1;
  }
}

function valueOf_0(c){
  var result;
  if (c < 128) {
    return $clinit_Character$BoxedValues() , result = boxedValues[c] , !result && (result = boxedValues[c] = new Character(c)) , result;
  }
  return new Character(c);
}

defineClass(95, 1, {3:1, 95:1, 7:1}, Character);
_.compareTo = function compareTo_1(c){
  return $compareTo_2(this, castTo(c, 95));
}
;
_.equals_0 = function equals_17(o){
  return instanceOf(o, 95) && castTo(o, 95).value_0 == this.value_0;
}
;
_.hashCode_0 = function hashCode_19(){
  return this.value_0;
}
;
_.toString_0 = function toString_35(){
  return String.fromCharCode(this.value_0);
}
;
_.value_0 = 0;
var digitRegex, isLeterOrDigitRegex, leterRegex, whitespaceRegex;
var Ljava_lang_Character_2_classLit = createForClass('java.lang', 'Character', 95);
function $clinit_Character$BoxedValues(){
  $clinit_Character$BoxedValues = emptyMethod;
  boxedValues = initUnidimensionalArray(Ljava_lang_Character_2_classLit, $intern_9, 95, 128, 0, 1);
}

var boxedValues;
function ClassCastException(){
  RuntimeException_0.call(this, null);
}

defineClass(306, 27, $intern_21, ClassCastException);
var Ljava_lang_ClassCastException_2_classLit = createForClass('java.lang', 'ClassCastException', 306);
function __decodeNumberString(s){
  var negative, radix;
  if ($equals_5(s.substr(0, 1), '-')) {
    negative = true;
    s = (checkCriticalStringElementIndex(1, s.length + 1) , s.substr(1));
  }
   else {
    negative = false;
    $equals_5(s.substr(0, 1), '+') && (s = (checkCriticalStringElementIndex(1, s.length + 1) , s.substr(1)));
  }
  if ($equals_5(s.substr(0, 2), '0x') || $equals_5(s.substr(0, 2), '0X')) {
    s = (checkCriticalStringElementIndex(2, s.length + 1) , s.substr(2));
    radix = 16;
  }
   else if ($equals_5(s.substr(0, 1), '#')) {
    s = (checkCriticalStringElementIndex(1, s.length + 1) , s.substr(1));
    radix = 16;
  }
   else 
    $equals_5(s.substr(0, 1), '0')?(radix = 8):(radix = 10);
  negative && (s = '-' + s);
  return new Number$__Decode(radix, s);
}

function __parseAndValidateDouble(s){
  floatRegex == null && (floatRegex = new RegExp('^\\s*[+-]?(NaN|Infinity|((\\d+\\.?\\d*)|(\\.\\d+))([eE][+-]?\\d+)?[dDfF]?)\\s*$'));
  if (!floatRegex.test(s)) {
    throw toJs(new NumberFormatException('For input string: "' + s + '"'));
  }
  return parseFloat(s);
}

function __parseAndValidateInt(s, radix){
  var i, isTooLow, length_0, startIndex, toReturn;
  if (s == null) {
    throw toJs(new NumberFormatException('null'));
  }
  if (radix < 2 || radix > 36) {
    throw toJs(new NumberFormatException('radix ' + radix + ' out of range'));
  }
  length_0 = s.length;
  startIndex = length_0 > 0 && (checkCriticalStringElementIndex(0, s.length) , s.charCodeAt(0) == 45 || (checkCriticalStringElementIndex(0, s.length) , s.charCodeAt(0) == 43))?1:0;
  for (i = startIndex; i < length_0; i++) {
    if (digit_0((checkCriticalStringElementIndex(i, s.length) , s.charCodeAt(i)), radix) == -1) {
      throw toJs(new NumberFormatException('For input string: "' + s + '"'));
    }
  }
  toReturn = parseInt(s, radix);
  isTooLow = toReturn < $intern_42;
  if (isNaN(toReturn)) {
    throw toJs(new NumberFormatException('For input string: "' + s + '"'));
  }
   else if (isTooLow || toReturn > $intern_0) {
    throw toJs(new NumberFormatException('For input string: "' + s + '"'));
  }
  return toReturn;
}

function __parseAndValidateLong(s){
  var c, firstTime, head, i, length_0, maxDigits, minValue, negative, orig, radixPower, toReturn;
  if (s == null) {
    throw toJs(new NumberFormatException('null'));
  }
  orig = s;
  length_0 = s.length;
  negative = false;
  if (length_0 > 0) {
    c = (checkCriticalStringElementIndex(0, s.length) , s.charCodeAt(0));
    if (c == 45 || c == 43) {
      s = (checkCriticalStringElementIndex(1, s.length + 1) , s.substr(1));
      --length_0;
      negative = c == 45;
    }
  }
  if (length_0 == 0) {
    throw toJs(new NumberFormatException('For input string: "' + orig + '"'));
  }
  while (s.length > 0 && (checkCriticalStringElementIndex(0, s.length) , s.charCodeAt(0) == 48)) {
    s = (checkCriticalStringElementIndex(1, s.length + 1) , s.substr(1));
    --length_0;
  }
  if (length_0 > ($clinit_Number$__ParseLong() , maxLengthForRadix)[10]) {
    throw toJs(new NumberFormatException('For input string: "' + orig + '"'));
  }
  for (i = 0; i < length_0; i++) {
    if (digit_0((checkCriticalStringElementIndex(i, s.length) , s.charCodeAt(i)), 10) == -1) {
      throw toJs(new NumberFormatException('For input string: "' + orig + '"'));
    }
  }
  toReturn = 0;
  maxDigits = maxDigitsForRadix[10];
  radixPower = maxDigitsRadixPower[10];
  minValue = neg_0(maxValueForRadix[10]);
  firstTime = true;
  head = length_0 % maxDigits;
  if (head > 0) {
    toReturn = -parseInt((checkCriticalStringBounds(0, head, s.length) , s.substr(0, head)), 10);
    s = (checkCriticalStringElementIndex(head, s.length + 1) , s.substr(head));
    length_0 -= head;
    firstTime = false;
  }
  while (length_0 >= maxDigits) {
    head = parseInt((checkCriticalStringBounds(0, maxDigits, s.length) , s.substr(0, maxDigits)), 10);
    s = (checkCriticalStringElementIndex(maxDigits, s.length + 1) , s.substr(maxDigits));
    length_0 -= maxDigits;
    if (firstTime) {
      firstTime = false;
    }
     else {
      if (compare_0(toReturn, minValue) < 0) {
        throw toJs(new NumberFormatException('For input string: "' + orig + '"'));
      }
      toReturn = mul_1(toReturn, radixPower);
    }
    toReturn = sub_1(toReturn, head);
  }
  if (compare_0(toReturn, 0) > 0) {
    throw toJs(new NumberFormatException('For input string: "' + orig + '"'));
  }
  if (!negative) {
    toReturn = neg_0(toReturn);
    if (compare_0(toReturn, 0) < 0) {
      throw toJs(new NumberFormatException('For input string: "' + orig + '"'));
    }
  }
  return toReturn;
}

function floatValue__F__devirtual$(this$static){
  return instanceOfDouble(this$static)?(checkCriticalNotNull(this$static) , this$static):this$static.floatValue();
}

function intValue__I__devirtual$(this$static){
  return instanceOfDouble(this$static)?round_int((checkCriticalNotNull(this$static) , this$static)):this$static.intValue();
}

defineClass(23, 1, {3:1, 23:1});
var floatRegex;
var Ljava_lang_Number_2_classLit = createForClass('java.lang', 'Number', 23);
function $compareTo_3(this$static, b){
  return compare_2((checkCriticalNotNull(this$static) , this$static), (checkCriticalNotNull(b) , b));
}

function $equals_3(this$static, o){
  return checkCriticalNotNull(this$static) , maskUndefined(this$static) === maskUndefined(o);
}

function compare_2(x_0, y_0){
  if (x_0 < y_0) {
    return -1;
  }
  if (x_0 > y_0) {
    return 1;
  }
  if (x_0 == y_0) {
    return x_0 == 0?compare_2(1 / x_0, 1 / y_0):0;
  }
  return isNaN(x_0)?isNaN(y_0)?0:1:-1;
}

doubleCastMap = {3:1, 7:1, 305:1, 23:1};
var Ljava_lang_Double_2_classLit = createForClass('java.lang', 'Double', 305);
function $compareTo_4(this$static, b){
  return compare_2(this$static.value_0, b.value_0);
}

function Float(value_0){
  this.value_0 = value_0;
}

function Float_0(s){
  this.value_0 = parseFloat_0(s);
}

function parseFloat_0(s){
  var doubleValue;
  doubleValue = __parseAndValidateDouble(s);
  if (doubleValue > 3.4028234663852886E38) {
    return $intern_43;
  }
   else if (doubleValue < -3.4028234663852886E38) {
    return $intern_44;
  }
  return doubleValue;
}

defineClass(38, 23, {3:1, 7:1, 38:1, 23:1}, Float, Float_0);
_.compareTo = function compareTo_2(b){
  return $compareTo_4(this, castTo(b, 38));
}
;
_.equals_0 = function equals_18(o){
  return instanceOf(o, 38) && $equals_3(this.value_0, castTo(o, 38).value_0);
}
;
_.floatValue = function floatValue(){
  return this.value_0;
}
;
_.hashCode_0 = function hashCode_20(){
  return round_int(this.value_0);
}
;
_.intValue = function intValue(){
  return round_int(this.value_0);
}
;
_.toString_0 = function toString_37(){
  return '' + this.value_0;
}
;
_.value_0 = 0;
var Ljava_lang_Float_2_classLit = createForClass('java.lang', 'Float', 38);
function IllegalArgumentException(){
  RuntimeException.call(this);
}

function IllegalArgumentException_0(message){
  RuntimeException_0.call(this, message);
}

defineClass(24, 27, {3:1, 6:1, 24:1, 9:1}, IllegalArgumentException, IllegalArgumentException_0);
var Ljava_lang_IllegalArgumentException_2_classLit = createForClass('java.lang', 'IllegalArgumentException', 24);
function IllegalStateException(){
  RuntimeException.call(this);
}

function IllegalStateException_0(s){
  RuntimeException_0.call(this, s);
}

function IllegalStateException_1(cause){
  RuntimeException_2.call(this, cause);
}

defineClass(45, 27, $intern_21, IllegalStateException, IllegalStateException_0, IllegalStateException_1);
var Ljava_lang_IllegalStateException_2_classLit = createForClass('java.lang', 'IllegalStateException', 45);
function $compareTo_5(this$static, b){
  return compare_3(this$static.value_0, b.value_0);
}

function $equals_4(this$static, o){
  return instanceOf(o, 44) && castTo(o, 44).value_0 == this$static.value_0;
}

function Integer(value_0){
  this.value_0 = value_0;
}

function compare_3(x_0, y_0){
  return x_0 < y_0?-1:x_0 > y_0?1:0;
}

function numberOfLeadingZeros_0(i){
  var m, n, y_0;
  if (i < 0) {
    return 0;
  }
   else if (i == 0) {
    return 32;
  }
   else {
    y_0 = -(i >> 16);
    m = y_0 >> 16 & 16;
    n = 16 - m;
    i = i >> m;
    y_0 = i - 256;
    m = y_0 >> 16 & 8;
    n += m;
    i <<= m;
    y_0 = i - 4096;
    m = y_0 >> 16 & 4;
    n += m;
    i <<= m;
    y_0 = i - $intern_15;
    m = y_0 >> 16 & 2;
    n += m;
    i <<= m;
    y_0 = i >> 14;
    m = y_0 & ~(y_0 >> 1);
    return n + 2 - m;
  }
}

function numberOfTrailingZeros(i){
  var r, rtn;
  if (i == 0) {
    return 32;
  }
   else {
    rtn = 0;
    for (r = 1; (r & i) == 0; r <<= 1) {
      ++rtn;
    }
    return rtn;
  }
}

function valueOf_1(i){
  var rebase, result;
  if (i > -129 && i < 128) {
    return $clinit_Integer$BoxedValues() , rebase = i + 128 , result = boxedValues_0[rebase] , !result && (result = boxedValues_0[rebase] = new Integer(i)) , result;
  }
  return new Integer(i);
}

defineClass(44, 23, {3:1, 7:1, 44:1, 23:1}, Integer);
_.compareTo = function compareTo_3(b){
  return $compareTo_5(this, castTo(b, 44));
}
;
_.equals_0 = function equals_19(o){
  return $equals_4(this, o);
}
;
_.floatValue = function floatValue_0(){
  return this.value_0;
}
;
_.hashCode_0 = function hashCode_21(){
  return this.value_0;
}
;
_.intValue = function intValue_0(){
  return this.value_0;
}
;
_.toString_0 = function toString_38(){
  return '' + this.value_0;
}
;
_.value_0 = 0;
var Ljava_lang_Integer_2_classLit = createForClass('java.lang', 'Integer', 44);
function $clinit_Integer$BoxedValues(){
  $clinit_Integer$BoxedValues = emptyMethod;
  boxedValues_0 = initUnidimensionalArray(Ljava_lang_Integer_2_classLit, $intern_9, 44, 256, 0, 1);
}

var boxedValues_0;
function compare_4(x_0, y_0){
  return compare_0(x_0, y_0) < 0?-1:compare_0(x_0, y_0) > 0?1:0;
}

function numberOfLeadingZeros_1(l){
  var high;
  high = toInt_0(shru_0(l, 32));
  return high != 0?numberOfLeadingZeros_0(high):numberOfLeadingZeros_0(toInt_0(l)) + 32;
}

function toPowerOfTwoUnsignedString(value_0){
  var buf, highBits, number, pos;
  highBits = toInt_0(shru_0(value_0, 32));
  if (highBits == 0) {
    return number = toInt_0(value_0) >>> 0 , number.toString(16);
  }
  buf = initUnidimensionalArray(C_classLit, $intern_2, 5, 17, 15, 1);
  pos = 17;
  do {
    buf[--pos] = forDigit(toInt_0(value_0) & 15);
    value_0 = shru_0(value_0, 4);
  }
   while (compare_0(value_0, 0) != 0);
  return valueOf_4(buf, pos, 17 - pos);
}

function abs_1(x_0){
  return compare_0(x_0, 0) < 0?neg_0(x_0):x_0;
}

function min_0(x_0, y_0){
  return compare_0(x_0, y_0) < 0?x_0:y_0;
}

defineClass(608, 1, {});
function NegativeArraySizeException(message){
  RuntimeException_0.call(this, message);
}

defineClass(426, 27, $intern_21, NegativeArraySizeException);
var Ljava_lang_NegativeArraySizeException_2_classLit = createForClass('java.lang', 'NegativeArraySizeException', 426);
function NullPointerException(){
  RuntimeException.call(this);
}

function NullPointerException_0(message){
  RuntimeException_0.call(this, message);
}

defineClass(107, 166, $intern_21, NullPointerException, NullPointerException_0);
_.createError = function createError_0(msg){
  return new TypeError(msg);
}
;
var Ljava_lang_NullPointerException_2_classLit = createForClass('java.lang', 'NullPointerException', 107);
function Number$__Decode(radix, payload){
  this.radix = radix;
  this.payload = payload;
}

defineClass(304, 1, {}, Number$__Decode);
_.radix = 0;
var Ljava_lang_Number$_1_1Decode_2_classLit = createForClass('java.lang', 'Number/__Decode', 304);
function $clinit_Number$__ParseLong(){
  $clinit_Number$__ParseLong = emptyMethod;
  var i;
  maxDigitsForRadix = stampJavaTypeInfo(getClassLiteralForArray(I_classLit, 1), $intern_3, 5, 15, [-1, -1, 30, 19, 15, 13, 11, 11, 10, 9, 9, 8, 8, 8, 8, 7, 7, 7, 7, 7, 7, 7, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 5]);
  maxDigitsRadixPower = initUnidimensionalArray(I_classLit, $intern_3, 5, 37, 15, 1);
  maxLengthForRadix = stampJavaTypeInfo(getClassLiteralForArray(I_classLit, 1), $intern_3, 5, 15, [-1, -1, 63, 40, 32, 28, 25, 23, 21, 20, 19, 19, 18, 18, 17, 17, 16, 16, 16, 15, 15, 15, 15, 14, 14, 14, 14, 14, 14, 13, 13, 13, 13, 13, 13, 13, 13]);
  maxValueForRadix = initUnidimensionalArray(J_classLit, $intern_2, 5, 37, 14, 1);
  for (i = 2; i <= 36; i++) {
    maxDigitsRadixPower[i] = round_int($wnd.Math.pow(i, maxDigitsForRadix[i]));
    maxValueForRadix[i] = div({l:$intern_29, m:$intern_29, h:524287}, maxDigitsRadixPower[i]);
  }
}

var maxDigitsForRadix, maxDigitsRadixPower, maxLengthForRadix, maxValueForRadix;
function NumberFormatException(message){
  IllegalArgumentException_0.call(this, message);
}

defineClass(56, 24, {3:1, 6:1, 24:1, 56:1, 9:1}, NumberFormatException);
var Ljava_lang_NumberFormatException_2_classLit = createForClass('java.lang', 'NumberFormatException', 56);
function StackTraceElement(methodName, fileName, lineNumber){
  this.className_0 = 'Unknown';
  this.methodName = methodName;
  this.fileName = fileName;
  this.lineNumber = lineNumber;
}

defineClass(83, 1, {3:1, 83:1}, StackTraceElement);
_.equals_0 = function equals_20(other){
  var st;
  if (instanceOf(other, 83)) {
    st = castTo(other, 83);
    return this.lineNumber == st.lineNumber && this.methodName == st.methodName && this.className_0 == st.className_0 && this.fileName == st.fileName;
  }
  return false;
}
;
_.hashCode_0 = function hashCode_22(){
  return hashCode_32(stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_Object_2_classLit, 1), $intern_9, 1, 5, [valueOf_1(this.lineNumber), this.className_0, this.methodName, this.fileName]));
}
;
_.toString_0 = function toString_39(){
  return this.className_0 + '.' + this.methodName + '(' + (this.fileName != null?this.fileName:'Unknown Source') + (this.lineNumber >= 0?':' + this.lineNumber:'') + ')';
}
;
_.lineNumber = 0;
var Ljava_lang_StackTraceElement_2_classLit = createForClass('java.lang', 'StackTraceElement', 83);
function $charAt(this$static, index_0){
  checkCriticalStringElementIndex(index_0, this$static.length);
  return this$static.charCodeAt(index_0);
}

function $compareTo_6(this$static, other){
  var a, b;
  a = (checkCriticalNotNull(this$static) , this$static);
  b = (checkCriticalNotNull(other) , other);
  return a == b?0:a < b?-1:1;
}

function $compareToIgnoreCase(this$static, other){
  return $compareTo_6(this$static.toLowerCase(), other.toLowerCase());
}

function $equals_5(this$static, other){
  return checkCriticalNotNull(this$static) , maskUndefined(this$static) === maskUndefined(other);
}

function $equalsIgnoreCase(this$static, other){
  checkCriticalNotNull(this$static);
  if (other == null) {
    return false;
  }
  if ($equals_5(this$static, other)) {
    return true;
  }
  return this$static.length == other.length && $equals_5(this$static.toLowerCase(), other.toLowerCase());
}

function $getChars(this$static, srcBegin, srcEnd, dst, dstBegin){
  checkCriticalStringBounds(srcBegin, srcEnd, this$static.length);
  checkCriticalStringBounds(dstBegin, dstBegin + (srcEnd - srcBegin), dst.length);
  $getChars0(this$static, srcBegin, srcEnd, dst, dstBegin);
}

function $getChars0(this$static, srcBegin, srcEnd, dst, dstBegin){
  while (srcBegin < srcEnd) {
    dst[dstBegin++] = $charAt(this$static, srcBegin++);
  }
}

function $hashCode_2(this$static){
  var h, i;
  h = 0;
  for (i = 0; i < this$static.length; i++) {
    h = (h << 5) - h + (checkCriticalStringElementIndex(i, this$static.length) , this$static.charCodeAt(i)) | 0;
  }
  return h;
}

function $indexOf_0(this$static, str){
  return this$static.indexOf(str);
}

function $indexOf_1(this$static, str){
  return this$static.indexOf(str, 0);
}

function $lastIndexOf(this$static, str){
  return this$static.lastIndexOf(str);
}

function $lastIndexOf_0(this$static, str, start_0){
  return this$static.lastIndexOf(str, start_0);
}

function $replace(this$static){
  var hex, number, regex, replace;
  hex = (number = 92 >>> 0 , number.toString(16));
  regex = '\\u' + $substring('0000', hex.length) + hex;
  replace = String.fromCharCode(47);
  return this$static.replace(new RegExp(regex, 'g'), replace);
}

function $replaceAll(this$static, regex, replace){
  replace = translateReplaceString(replace);
  return this$static.replace(new RegExp(regex, 'g'), replace);
}

function $split(this$static, regex){
  var compiled, count, lastNonEmpty, lastTrail, matchIndex, matchObj, out, trail;
  compiled = new RegExp(regex, 'g');
  out = initUnidimensionalArray(Ljava_lang_String_2_classLit, $intern_11, 2, 0, 6, 1);
  count = 0;
  trail = this$static;
  lastTrail = null;
  while (true) {
    matchObj = compiled.exec(trail);
    if (matchObj == null || trail == '') {
      out[count] = trail;
      break;
    }
     else {
      matchIndex = matchObj.index;
      out[count] = (checkCriticalStringBounds(0, matchIndex, trail.length) , trail.substr(0, matchIndex));
      trail = $substring_0(trail, matchIndex + matchObj[0].length, trail.length);
      compiled.lastIndex = 0;
      if (lastTrail == trail) {
        out[count] = (checkCriticalStringBounds(0, 1, trail.length) , trail.substr(0, 1));
        trail = (checkCriticalStringElementIndex(1, trail.length + 1) , trail.substr(1));
      }
      lastTrail = trail;
      ++count;
    }
  }
  if (this$static.length > 0) {
    lastNonEmpty = out.length;
    while (lastNonEmpty > 0 && out[lastNonEmpty - 1] == '') {
      --lastNonEmpty;
    }
    lastNonEmpty < out.length && (out.length = lastNonEmpty);
  }
  return out;
}

function $startsWith(this$static, prefix){
  return $equals_5(this$static.substr(0, prefix.length), prefix);
}

function $substring(this$static, beginIndex){
  checkCriticalStringElementIndex(beginIndex, this$static.length + 1);
  return this$static.substr(beginIndex);
}

function $substring_0(this$static, beginIndex, endIndex){
  checkCriticalStringBounds(beginIndex, endIndex, this$static.length);
  return this$static.substr(beginIndex, endIndex - beginIndex);
}

function $toCharArray(this$static){
  var charArr, n;
  n = this$static.length;
  charArr = initUnidimensionalArray(C_classLit, $intern_2, 5, n, 15, 1);
  $getChars0(this$static, 0, n, charArr, 0);
  return charArr;
}

function $toLowerCase(this$static, locale){
  return locale == ($clinit_Locale() , $clinit_Locale() , defaultLocale)?this$static.toLocaleLowerCase():this$static.toLowerCase();
}

function $toUpperCase(this$static, locale){
  return locale == ($clinit_Locale() , $clinit_Locale() , defaultLocale)?this$static.toLocaleUpperCase():this$static.toUpperCase();
}

function $trim(this$static){
  var end, length_0, start_0;
  length_0 = this$static.length;
  start_0 = 0;
  while (start_0 < length_0 && (checkCriticalStringElementIndex(start_0, this$static.length) , this$static.charCodeAt(start_0) <= 32)) {
    ++start_0;
  }
  end = length_0;
  while (end > start_0 && (checkCriticalStringElementIndex(end - 1, this$static.length) , this$static.charCodeAt(end - 1) <= 32)) {
    --end;
  }
  return start_0 > 0 || end < length_0?(checkCriticalStringBounds(start_0, end, this$static.length) , this$static.substr(start_0, end - start_0)):this$static;
}

function fromCharCode(array){
  return String.fromCharCode.apply(null, array);
}

function fromCodePoint(codePoint){
  var hiSurrogate, loSurrogate;
  if (codePoint >= $intern_35) {
    hiSurrogate = 55296 + (codePoint - $intern_35 >> 10 & 1023) & $intern_5;
    loSurrogate = 56320 + (codePoint - $intern_35 & 1023) & $intern_5;
    return String.fromCharCode(hiSurrogate) + ('' + String.fromCharCode(loSurrogate));
  }
   else {
    return String.fromCharCode(codePoint & $intern_5);
  }
}

function translateReplaceString(replaceStr){
  var pos;
  pos = 0;
  while (0 <= (pos = replaceStr.indexOf('\\', pos))) {
    checkCriticalStringElementIndex(pos + 1, replaceStr.length);
    replaceStr.charCodeAt(pos + 1) == 36?(replaceStr = (checkCriticalStringBounds(0, pos, replaceStr.length) , replaceStr.substr(0, pos) + '$' + $substring(replaceStr, ++pos))):(replaceStr = (checkCriticalStringBounds(0, pos, replaceStr.length) , replaceStr.substr(0, pos) + ('' + $substring(replaceStr, ++pos))));
  }
  return replaceStr;
}

function valueOf_2(x_0){
  return !x_0?'null':x_0.name_0 != null?x_0.name_0:'' + x_0.ordinal;
}

function valueOf_3(x_0){
  return valueOf_4(x_0, 0, x_0.length);
}

function valueOf_4(x_0, offset, count){
  var batchEnd, batchStart, end, s;
  end = offset + count;
  checkCriticalStringBounds(offset, end, x_0.length);
  s = '';
  for (batchStart = offset; batchStart < end;) {
    batchEnd = $wnd.Math.min(batchStart + 10000, end);
    s += fromCharCode(x_0.slice(batchStart, batchEnd));
    batchStart = batchEnd;
  }
  return s;
}

stringCastMap = {3:1, 278:1, 7:1, 2:1};
var Ljava_lang_String_2_classLit = createForClass('java.lang', 'String', 2);
function $append(this$static, x_0){
  this$static.string += String.fromCharCode(x_0);
  return this$static;
}

function $append_0(this$static, x_0){
  this$static.string += x_0;
  return this$static;
}

function $append_1(this$static, x_0){
  this$static.string += '' + x_0;
  return this$static;
}

function $append_2(this$static, x_0){
  this$static.string += '' + x_0;
  return this$static;
}

function $append_3(this$static, x_0){
  this$static.string += '' + x_0;
  return this$static;
}

function $append_4(this$static, x_0, start_0, len){
  this$static.string += valueOf_4(x_0, start_0, len);
  return this$static;
}

function StringBuilder(){
  AbstractStringBuilder.call(this, '');
}

function StringBuilder_0(){
  AbstractStringBuilder.call(this, '');
}

function StringBuilder_1(s){
  AbstractStringBuilder.call(this, (checkCriticalNotNull(s) , s));
}

defineClass(34, 165, {278:1}, StringBuilder, StringBuilder_0, StringBuilder_1);
var Ljava_lang_StringBuilder_2_classLit = createForClass('java.lang', 'StringBuilder', 34);
function StringIndexOutOfBoundsException(message){
  IndexOutOfBoundsException_0.call(this, message);
}

defineClass(224, 36, $intern_21, StringIndexOutOfBoundsException);
var Ljava_lang_StringIndexOutOfBoundsException_2_classLit = createForClass('java.lang', 'StringIndexOutOfBoundsException', 224);
function $clinit_System(){
  $clinit_System = emptyMethod;
  err_0 = new PrintStream;
}

function arraycopy(src_0, srcOfs, dest, destOfs, len){
  $clinit_System();
  var destArray, destComp, destEnd, destType, srcArray, srcComp, srcType;
  checkCriticalNotNull_0(src_0, 'src');
  checkCriticalNotNull_0(dest, 'dest');
  srcType = getClass__Ljava_lang_Class___devirtual$(src_0);
  destType = getClass__Ljava_lang_Class___devirtual$(dest);
  checkCriticalArrayType_0((srcType.modifiers & 4) != 0, 'srcType is not an array');
  checkCriticalArrayType_0((destType.modifiers & 4) != 0, 'destType is not an array');
  srcComp = srcType.componentType;
  destComp = destType.componentType;
  checkCriticalArrayType_0((srcComp.modifiers & 1) != 0?srcComp == destComp:(destComp.modifiers & 1) == 0, "Array types don't match");
  checkArrayCopyIndicies(src_0, srcOfs, dest, destOfs, len);
  if ((srcComp.modifiers & 1) == 0 && srcType != destType) {
    srcArray = castToArray(src_0);
    destArray = castToArray(dest);
    if (maskUndefined(src_0) === maskUndefined(dest) && srcOfs < destOfs) {
      srcOfs += len;
      for (destEnd = destOfs + len; destEnd-- > destOfs;) {
        setCheck(destArray, destEnd, srcArray[--srcOfs]);
      }
    }
     else {
      for (destEnd = destOfs + len; destOfs < destEnd;) {
        setCheck(destArray, destOfs++, srcArray[srcOfs++]);
      }
    }
  }
   else {
    copy_1(src_0, srcOfs, dest, destOfs, len);
  }
}

function checkArrayCopyIndicies(src_0, srcOfs, dest, destOfs, len){
  var destlen, srclen;
  srclen = src_0.length;
  destlen = dest.length;
  if (srcOfs < 0 || destOfs < 0 || len < 0 || srcOfs + len > srclen || destOfs + len > destlen) {
    throw toJs(new IndexOutOfBoundsException);
  }
}

defineClass(613, 1, {});
var err_0;
function UnsupportedOperationException(){
  RuntimeException.call(this);
}

function UnsupportedOperationException_0(message){
  RuntimeException_0.call(this, message);
}

defineClass(140, 27, $intern_21, UnsupportedOperationException, UnsupportedOperationException_0);
var Ljava_lang_UnsupportedOperationException_2_classLit = createForClass('java.lang', 'UnsupportedOperationException', 140);
function $clear_1(this$static){
  this$static.position = 0;
  this$static.mark = -1;
  this$static.limit = this$static.capacity;
  return this$static;
}

function $flip_0(this$static){
  this$static.limit = this$static.position;
  this$static.position = 0;
  this$static.mark = -1;
  return this$static;
}

function $limit(this$static, newLimit){
  if (newLimit < 0 || newLimit > this$static.capacity) {
    throw toJs(new IllegalArgumentException);
  }
  this$static.limit = newLimit;
  this$static.position > newLimit && (this$static.position = newLimit);
  this$static.mark != -1 && this$static.mark > newLimit && (this$static.mark = -1);
  return this$static;
}

function $position(this$static, newPosition){
  if (newPosition < 0 || newPosition > this$static.limit) {
    throw toJs(new IllegalArgumentException);
  }
  this$static.position = newPosition;
  this$static.mark != -1 && this$static.mark > this$static.position && (this$static.mark = -1);
  return this$static;
}

function Buffer(capacity){
  if (capacity < 0) {
    throw toJs(new IllegalArgumentException);
  }
  this.capacity = this.limit = capacity;
}

defineClass(155, 1, {});
_.capacity = 0;
_.limit = 0;
_.mark = -1;
_.position = 0;
var Ljava_nio_Buffer_2_classLit = createForClass('java.nio', 'Buffer', 155);
function $compareTo_7(this$static, otherBuffer){
  var compareRemaining, otherByte, otherPos, thisByte, thisPos;
  compareRemaining = this$static.limit - this$static.position < otherBuffer.limit - otherBuffer.position?this$static.limit - this$static.position:otherBuffer.limit - otherBuffer.position;
  thisPos = this$static.position;
  otherPos = otherBuffer.position;
  while (compareRemaining > 0) {
    thisByte = this$static.get_0(thisPos);
    otherByte = otherBuffer.get_0(otherPos);
    if (thisByte != otherByte) {
      return thisByte < otherByte?-1:1;
    }
    ++thisPos;
    ++otherPos;
    --compareRemaining;
  }
  return this$static.limit - this$static.position - (otherBuffer.limit - otherBuffer.position);
}

function $order(this$static, byteOrder){
  return this$static.order = byteOrder == ($clinit_ByteOrder() , BIG_ENDIAN)?0:1 , this$static;
}

function allocateDirect(capacity){
  if (capacity < 0) {
    throw toJs(new IllegalArgumentException);
  }
  return new DirectReadWriteByteBuffer(capacity);
}

defineClass(64, 155, $intern_45);
_.compareTo = function compareTo_4(otherBuffer){
  return $compareTo_7(this, castTo(otherBuffer, 64));
}
;
_.equals_0 = function equals_21(other){
  var equalSoFar, myPosition, otherBuffer, otherPosition;
  if (!instanceOf(other, 64)) {
    return false;
  }
  otherBuffer = castTo(other, 64);
  if (this.limit - this.position != otherBuffer.limit - otherBuffer.position) {
    return false;
  }
  myPosition = this.position;
  otherPosition = otherBuffer.position;
  equalSoFar = true;
  while (equalSoFar && myPosition < this.limit) {
    equalSoFar = this.get_0(myPosition++) == otherBuffer.get_0(otherPosition++);
  }
  return equalSoFar;
}
;
_.hashCode_0 = function hashCode_23(){
  var hash, myPosition;
  myPosition = this.position;
  hash = 0;
  while (myPosition < this.limit) {
    hash = hash + this.get_0(myPosition++);
  }
  return hash;
}
;
_.toString_0 = function toString_40(){
  var sb;
  sb = new StringBuilder;
  $append_3(sb, $getName(this.___clazz));
  sb.string += ', status: capacity=';
  $append_0(sb, this.capacity);
  sb.string += ' position=';
  $append_0(sb, this.position);
  sb.string += ' limit=';
  $append_0(sb, this.limit);
  return sb.string;
}
;
_.order = 0;
var Ljava_nio_ByteBuffer_2_classLit = createForClass('java.nio', 'ByteBuffer', 64);
function BaseByteBuffer(capacity){
  Buffer.call(this, capacity);
}

defineClass(252, 64, $intern_45);
_.asFloatBuffer = function asFloatBuffer_0(){
  return new FloatToByteBufferAdapter(this.slice_0());
}
;
var Ljava_nio_BaseByteBuffer_2_classLit = createForClass('java.nio', 'BaseByteBuffer', 252);
function BufferOverflowException(){
  RuntimeException.call(this);
}

defineClass(135, 27, $intern_21, BufferOverflowException);
var Ljava_nio_BufferOverflowException_2_classLit = createForClass('java.nio', 'BufferOverflowException', 135);
function BufferUnderflowException(){
  RuntimeException.call(this);
}

defineClass(497, 27, $intern_21, BufferUnderflowException);
var Ljava_nio_BufferUnderflowException_2_classLit = createForClass('java.nio', 'BufferUnderflowException', 497);
function $clinit_ByteOrder(){
  $clinit_ByteOrder = emptyMethod;
  BIG_ENDIAN = new ByteOrder('BIG_ENDIAN');
  LITTLE_ENDIAN = new ByteOrder('LITTLE_ENDIAN');
  NATIVE_ORDER = LITTLE_ENDIAN;
}

function ByteOrder(name_0){
  this.name_0 = name_0;
}

defineClass(245, 1, {}, ByteOrder);
_.toString_0 = function toString_41(){
  return this.name_0;
}
;
var BIG_ENDIAN, LITTLE_ENDIAN, NATIVE_ORDER;
var Ljava_nio_ByteOrder_2_classLit = createForClass('java.nio', 'ByteOrder', 245);
function $loadInt(this$static, baseOffset){
  var bytes, i;
  bytes = 0;
  if (this$static.order == 0) {
    for (i = 0; i < 4; i++) {
      bytes = bytes << 8;
      bytes = bytes | this$static.byteArray[baseOffset + i] & 255;
    }
  }
   else {
    for (i = 3; i >= 0; i--) {
      bytes = bytes << 8;
      bytes = bytes | this$static.byteArray[baseOffset + i] & 255;
    }
  }
  return bytes;
}

function $loadShort(this$static, baseOffset){
  var bytes;
  bytes = 0;
  if (this$static.order == 0) {
    bytes = this$static.byteArray[baseOffset] << 8 << 16 >> 16;
    bytes = (bytes | this$static.byteArray[baseOffset + 1] & 255) << 16 >> 16;
  }
   else {
    bytes = this$static.byteArray[baseOffset + 1] << 8 << 16 >> 16;
    bytes = (bytes | this$static.byteArray[baseOffset] & 255) << 16 >> 16;
  }
  return bytes;
}

function $store(this$static, baseOffset, value_0){
  var i;
  if (this$static.order == 0) {
    for (i = 3; i >= 0; i--) {
      this$static.byteArray[baseOffset + i] = (value_0 & 255) << 24 >> 24;
      value_0 = value_0 >> 8;
    }
  }
   else {
    for (i = 0; i <= 3; i++) {
      this$static.byteArray[baseOffset + i] = (value_0 & 255) << 24 >> 24;
      value_0 = value_0 >> 8;
    }
  }
}

function $store_0(this$static, baseOffset, value_0){
  if (this$static.order == 0) {
    this$static.byteArray[baseOffset] = (value_0 >> 8 & 255) << 24 >> 24;
    this$static.byteArray[baseOffset + 1] = (value_0 & 255) << 24 >> 24;
  }
   else {
    this$static.byteArray[baseOffset + 1] = (value_0 >> 8 & 255) << 24 >> 24;
    this$static.byteArray[baseOffset] = (value_0 & 255) << 24 >> 24;
  }
}

function DirectByteBuffer(buffer, capacity, offset){
  BaseByteBuffer.call(this, capacity);
  this.byteArray = new Int8Array(buffer, offset, capacity);
}

defineClass(199, 252, $intern_46);
_.get_0 = function get_1(index_0){
  return this.byteArray[index_0];
}
;
_.getFloat = function getFloat(index_0){
  return intBitsToFloat($loadInt(this, index_0));
}
;
_.getTypedArray = function getTypedArray(){
  return this.byteArray;
}
;
var Ljava_nio_DirectByteBuffer_2_classLit = createForClass('java.nio', 'DirectByteBuffer', 199);
function $asShortBuffer(this$static){
  return (this$static.order == 0?($clinit_ByteOrder() , BIG_ENDIAN):($clinit_ByteOrder() , LITTLE_ENDIAN)) == ($clinit_ByteOrder() , $clinit_ByteOrder() , NATIVE_ORDER)?new DirectReadWriteShortBufferAdapter($slice(this$static)):new ShortToByteBufferAdapter($slice(this$static));
}

function $putInt(this$static, index_0, value_0){
  $store(this$static, index_0, value_0);
  return this$static;
}

function $putShort(this$static, index_0, value_0){
  $store_0(this$static, index_0, value_0);
  return this$static;
}

function $slice(this$static){
  var slice_0;
  slice_0 = new DirectReadWriteByteBuffer_0(this$static.byteArray.buffer, this$static.limit - this$static.position, this$static.byteArray.byteOffset + this$static.position);
  slice_0.order = this$static.order;
  return slice_0;
}

function DirectReadWriteByteBuffer(capacity){
  DirectByteBuffer.call(this, new ArrayBuffer(capacity), capacity, 0);
}

function DirectReadWriteByteBuffer_0(backingArray, capacity, arrayOffset){
  DirectByteBuffer.call(this, backingArray, capacity, arrayOffset);
}

defineClass(253, 199, $intern_46, DirectReadWriteByteBuffer, DirectReadWriteByteBuffer_0);
_.asFloatBuffer = function asFloatBuffer_1(){
  return new DirectReadWriteFloatBufferAdapter($slice(this));
}
;
_.putFloat = function putFloat(index_0, value_0){
  return $putInt(this, index_0, ($clinit_Numbers() , wfa[0] = value_0 , undefined , wia[0]));
}
;
_.slice_0 = function slice_1(){
  return $slice(this);
}
;
var Ljava_nio_DirectReadWriteByteBuffer_2_classLit = createForClass('java.nio', 'DirectReadWriteByteBuffer', 253);
function $compareTo_8(this$static, otherBuffer){
  var compareRemaining, otherFloat, otherPos, thisFloat, thisPos;
  compareRemaining = this$static.limit - this$static.position < otherBuffer.limit - otherBuffer.position?this$static.limit - this$static.position:otherBuffer.limit - otherBuffer.position;
  thisPos = this$static.position;
  otherPos = otherBuffer.position;
  while (compareRemaining > 0) {
    thisFloat = this$static.get_1(thisPos);
    otherFloat = otherBuffer.get_1(otherPos);
    if (thisFloat != otherFloat && (thisFloat == thisFloat || otherFloat == otherFloat)) {
      return thisFloat < otherFloat?-1:1;
    }
    ++thisPos;
    ++otherPos;
    --compareRemaining;
  }
  return this$static.limit - this$static.position - (otherBuffer.limit - otherBuffer.position);
}

function $put_2(this$static, src_0, off, len){
  var i, length_0;
  length_0 = src_0.length;
  if (off < 0 || len < 0 || gt(add_2(off, len), length_0)) {
    throw toJs(new IndexOutOfBoundsException);
  }
  if (len > this$static.limit - this$static.position) {
    throw toJs(new BufferOverflowException);
  }
  for (i = off; i < off + len; i++) {
    this$static.put(src_0[i]);
  }
  return this$static;
}

function FloatBuffer(capacity){
  Buffer.call(this, capacity);
}

defineClass(68, 155, {7:1, 68:1});
_.compareTo = function compareTo_5(otherBuffer){
  return $compareTo_8(this, castTo(otherBuffer, 68));
}
;
_.equals_0 = function equals_22(other){
  var equalSoFar, myPosition, otherBuffer, otherPosition;
  if (!instanceOf(other, 68)) {
    return false;
  }
  otherBuffer = castTo(other, 68);
  if (this.limit - this.position != otherBuffer.limit - otherBuffer.position) {
    return false;
  }
  myPosition = this.position;
  otherPosition = otherBuffer.position;
  equalSoFar = true;
  while (equalSoFar && myPosition < this.limit) {
    equalSoFar = this.get_1(myPosition++) == otherBuffer.get_1(otherPosition++);
  }
  return equalSoFar;
}
;
_.toString_0 = function toString_42(){
  var sb;
  sb = new StringBuilder;
  $append_3(sb, $getName(this.___clazz));
  sb.string += ', status: capacity=';
  $append_0(sb, this.capacity);
  sb.string += ' position=';
  $append_0(sb, this.position);
  sb.string += ' limit=';
  $append_0(sb, this.limit);
  return sb.string;
}
;
var Ljava_nio_FloatBuffer_2_classLit = createForClass('java.nio', 'FloatBuffer', 68);
function DirectReadWriteFloatBufferAdapter(byteBuffer){
  FloatBuffer.call(this, byteBuffer.capacity >> 2);
  this.byteBuffer = byteBuffer;
  $clear_1(this.byteBuffer);
  this.floatArray = create_2(byteBuffer.byteArray.buffer, byteBuffer.byteArray.byteOffset, this.capacity);
}

defineClass(204, 68, {7:1, 68:1, 105:1}, DirectReadWriteFloatBufferAdapter);
_.get_1 = function get_2(index_0){
  return this.floatArray[index_0];
}
;
_.getTypedArray = function getTypedArray_0(){
  return this.floatArray;
}
;
_.put = function put(c){
  this.floatArray[this.position++] = c;
  return this;
}
;
var Ljava_nio_DirectReadWriteFloatBufferAdapter_2_classLit = createForClass('java.nio', 'DirectReadWriteFloatBufferAdapter', 204);
function $compareTo_9(this$static, otherBuffer){
  var compareRemaining, otherInt, otherPos, thisInt, thisPos;
  compareRemaining = this$static.limit - this$static.position < otherBuffer.limit - otherBuffer.position?this$static.limit - this$static.position:otherBuffer.limit - otherBuffer.position;
  thisPos = this$static.position;
  otherPos = otherBuffer.position;
  while (compareRemaining > 0) {
    thisInt = this$static.get_3(thisPos);
    otherInt = otherBuffer.get_3(otherPos);
    if (thisInt != otherInt) {
      return thisInt < otherInt?-1:1;
    }
    ++thisPos;
    ++otherPos;
    --compareRemaining;
  }
  return this$static.limit - this$static.position - (otherBuffer.limit - otherBuffer.position);
}

function IntBuffer(capacity){
  Buffer.call(this, capacity);
}

defineClass(94, 155, {7:1, 94:1});
_.compareTo = function compareTo_6(otherBuffer){
  return $compareTo_9(this, castTo(otherBuffer, 94));
}
;
_.equals_0 = function equals_23(other){
  var equalSoFar, myPosition, otherBuffer, otherPosition;
  if (!instanceOf(other, 94)) {
    return false;
  }
  otherBuffer = castTo(other, 94);
  if (this.limit - this.position != otherBuffer.limit - otherBuffer.position) {
    return false;
  }
  myPosition = this.position;
  otherPosition = otherBuffer.position;
  equalSoFar = true;
  while (equalSoFar && myPosition < this.limit) {
    equalSoFar = this.get_3(myPosition++) == otherBuffer.get_3(otherPosition++);
  }
  return equalSoFar;
}
;
_.hashCode_0 = function hashCode_24(){
  var hash, myPosition;
  myPosition = this.position;
  hash = 0;
  while (myPosition < this.limit) {
    hash = hash + this.get_3(myPosition++);
  }
  return hash;
}
;
_.toString_0 = function toString_43(){
  var sb;
  sb = new StringBuilder;
  $append_3(sb, $getName(this.___clazz));
  sb.string += ', status: capacity=';
  $append_0(sb, this.capacity);
  sb.string += ' position=';
  $append_0(sb, this.position);
  sb.string += ' limit=';
  $append_0(sb, this.limit);
  return sb.string;
}
;
var Ljava_nio_IntBuffer_2_classLit = createForClass('java.nio', 'IntBuffer', 94);
function DirectReadWriteIntBufferAdapter(byteBuffer){
  IntBuffer.call(this, byteBuffer.capacity >> 2);
  this.byteBuffer = byteBuffer;
  $clear_1(this.byteBuffer);
  this.intArray = create_5(byteBuffer.byteArray.buffer, byteBuffer.byteArray.byteOffset, this.capacity);
}

defineClass(259, 94, {7:1, 105:1, 94:1}, DirectReadWriteIntBufferAdapter);
_.get_2 = function get_3(){
  return this.intArray[this.position++];
}
;
_.get_3 = function get_4(index_0){
  return this.intArray[index_0];
}
;
_.getTypedArray = function getTypedArray_1(){
  return this.intArray;
}
;
_.put_0 = function put_0(c){
  this.intArray[this.position++] = c;
  return this;
}
;
_.put_1 = function put_1(index_0, c){
  this.intArray[index_0] = c;
  return this;
}
;
var Ljava_nio_DirectReadWriteIntBufferAdapter_2_classLit = createForClass('java.nio', 'DirectReadWriteIntBufferAdapter', 259);
function $compareTo_10(this$static, otherBuffer){
  var compareRemaining, otherByte, otherPos, thisByte, thisPos;
  compareRemaining = this$static.limit - this$static.position < otherBuffer.limit - otherBuffer.position?this$static.limit - this$static.position:otherBuffer.limit - otherBuffer.position;
  thisPos = this$static.position;
  otherPos = otherBuffer.position;
  while (compareRemaining > 0) {
    thisByte = this$static.get_4(thisPos);
    otherByte = otherBuffer.get_4(otherPos);
    if (thisByte != otherByte) {
      return thisByte < otherByte?-1:1;
    }
    ++thisPos;
    ++otherPos;
    --compareRemaining;
  }
  return this$static.limit - this$static.position - (otherBuffer.limit - otherBuffer.position);
}

function $put_3(this$static, src_0, off, len){
  var i, length_0;
  length_0 = src_0.length;
  if (off < 0 || len < 0 || gt(add_2(off, len), length_0)) {
    throw toJs(new IndexOutOfBoundsException);
  }
  if (len > this$static.limit - this$static.position) {
    throw toJs(new BufferOverflowException);
  }
  for (i = off; i < off + len; i++) {
    this$static.put_2(src_0[i]);
  }
  return this$static;
}

function ShortBuffer(capacity){
  Buffer.call(this, capacity);
}

defineClass(80, 155, {7:1, 80:1});
_.compareTo = function compareTo_7(otherBuffer){
  return $compareTo_10(this, castTo(otherBuffer, 80));
}
;
_.equals_0 = function equals_24(other){
  var equalSoFar, myPosition, otherBuffer, otherPosition;
  if (!instanceOf(other, 80)) {
    return false;
  }
  otherBuffer = castTo(other, 80);
  if (this.limit - this.position != otherBuffer.limit - otherBuffer.position) {
    return false;
  }
  myPosition = this.position;
  otherPosition = otherBuffer.position;
  equalSoFar = true;
  while (equalSoFar && myPosition < this.limit) {
    equalSoFar = this.get_4(myPosition++) == otherBuffer.get_4(otherPosition++);
  }
  return equalSoFar;
}
;
_.hashCode_0 = function hashCode_25(){
  var hash, myPosition;
  myPosition = this.position;
  hash = 0;
  while (myPosition < this.limit) {
    hash = hash + this.get_4(myPosition++);
  }
  return hash;
}
;
_.toString_0 = function toString_44(){
  var sb;
  sb = new StringBuilder;
  $append_3(sb, $getName(this.___clazz));
  sb.string += ', status: capacity=';
  $append_0(sb, this.capacity);
  sb.string += ' position=';
  $append_0(sb, this.position);
  sb.string += ' limit=';
  $append_0(sb, this.limit);
  return sb.string;
}
;
var Ljava_nio_ShortBuffer_2_classLit = createForClass('java.nio', 'ShortBuffer', 80);
function DirectReadWriteShortBufferAdapter(byteBuffer){
  ShortBuffer.call(this, byteBuffer.capacity >> 1);
  this.byteBuffer = byteBuffer;
  $clear_1(this.byteBuffer);
  this.shortArray = create_4(byteBuffer.byteArray.buffer, byteBuffer.byteArray.byteOffset, this.capacity);
}

defineClass(260, 80, {7:1, 105:1, 80:1}, DirectReadWriteShortBufferAdapter);
_.get_4 = function get_5(index_0){
  return this.shortArray[index_0];
}
;
_.getTypedArray = function getTypedArray_2(){
  return this.shortArray;
}
;
_.put_2 = function put_2(c){
  this.shortArray[this.position++] = c;
  return this;
}
;
var Ljava_nio_DirectReadWriteShortBufferAdapter_2_classLit = createForClass('java.nio', 'DirectReadWriteShortBufferAdapter', 260);
function FloatToByteBufferAdapter(byteBuffer){
  FloatBuffer.call(this, byteBuffer.capacity >> 2);
  this.byteBuffer = byteBuffer;
  $clear_1(this.byteBuffer);
}

defineClass(487, 68, {7:1, 68:1}, FloatToByteBufferAdapter);
_.get_1 = function get_6(index_0){
  if (index_0 < 0 || index_0 >= this.limit) {
    throw toJs(new IndexOutOfBoundsException);
  }
  return this.byteBuffer.getFloat(index_0 << 2);
}
;
_.put = function put_3(c){
  if (this.position == this.limit) {
    throw toJs(new BufferOverflowException);
  }
  this.byteBuffer.putFloat(this.position++ << 2, c);
  return this;
}
;
var Ljava_nio_FloatToByteBufferAdapter_2_classLit = createForClass('java.nio', 'FloatToByteBufferAdapter', 487);
function $getInt(this$static, index_0){
  if (index_0 < 0 || index_0 + 4 > this$static.limit) {
    throw toJs(new IndexOutOfBoundsException);
  }
  return $loadInt_0(this$static, index_0);
}

function $loadInt_0(this$static, index_0){
  var baseOffset, bytes, i;
  baseOffset = this$static.offset + index_0;
  bytes = 0;
  if (this$static.order == 0) {
    for (i = 0; i < 4; i++) {
      bytes = bytes << 8;
      bytes = bytes | this$static.backingArray[baseOffset + i] & 255;
    }
  }
   else {
    for (i = 3; i >= 0; i--) {
      bytes = bytes << 8;
      bytes = bytes | this$static.backingArray[baseOffset + i] & 255;
    }
  }
  return bytes;
}

function $store_1(this$static, index_0, value_0){
  var baseOffset, i;
  baseOffset = this$static.offset + index_0;
  if (this$static.order == 0) {
    for (i = 3; i >= 0; i--) {
      this$static.backingArray[baseOffset + i] = (value_0 & 255) << 24 >> 24;
      value_0 = value_0 >> 8;
    }
  }
   else {
    for (i = 0; i <= 3; i++) {
      this$static.backingArray[baseOffset + i] = (value_0 & 255) << 24 >> 24;
      value_0 = value_0 >> 8;
    }
  }
}

function HeapByteBuffer(backingArray, capacity, offset){
  BaseByteBuffer.call(this, capacity);
  this.backingArray = backingArray;
  this.offset = offset;
  if (offset + capacity > backingArray.length) {
    throw toJs(new IndexOutOfBoundsException);
  }
}

defineClass(203, 252, $intern_45);
_.get_0 = function get_7(index_0){
  if (index_0 < 0 || index_0 >= this.limit) {
    throw toJs(new IndexOutOfBoundsException);
  }
  return this.backingArray[this.offset + index_0];
}
;
_.getFloat = function getFloat_0(index_0){
  return intBitsToFloat($getInt(this, index_0));
}
;
_.offset = 0;
var Ljava_nio_HeapByteBuffer_2_classLit = createForClass('java.nio', 'HeapByteBuffer', 203);
function IntToByteBufferAdapter(byteBuffer){
  IntBuffer.call(this, byteBuffer.capacity >> 2);
  this.byteBuffer = byteBuffer;
  $clear_1(this.byteBuffer);
}

defineClass(257, 94, {7:1, 94:1}, IntToByteBufferAdapter);
_.get_2 = function get_8(){
  if (this.position == this.limit) {
    throw toJs(new BufferUnderflowException);
  }
  return $loadInt(this.byteBuffer, this.position++ << 2);
}
;
_.get_3 = function get_9(index_0){
  if (index_0 < 0 || index_0 >= this.limit) {
    throw toJs(new IndexOutOfBoundsException);
  }
  return $loadInt(this.byteBuffer, index_0 << 2);
}
;
_.put_0 = function put_4(c){
  if (this.position == this.limit) {
    throw toJs(new BufferOverflowException);
  }
  $putInt(this.byteBuffer, this.position++ << 2, c);
  return this;
}
;
_.put_1 = function put_5(index_0, c){
  if (index_0 < 0 || index_0 >= this.limit) {
    throw toJs(new IndexOutOfBoundsException);
  }
  $putInt(this.byteBuffer, index_0 << 2, c);
  return this;
}
;
var Ljava_nio_IntToByteBufferAdapter_2_classLit = createForClass('java.nio', 'IntToByteBufferAdapter', 257);
function $putInt_0(this$static, index_0, value_0){
  if (index_0 < 0 || gt(add_2(index_0, 4), this$static.limit)) {
    throw toJs(new IndexOutOfBoundsException);
  }
  $store_1(this$static, index_0, value_0);
  return this$static;
}

function ReadWriteHeapByteBuffer(backingArray){
  HeapByteBuffer.call(this, backingArray, backingArray.length, 0);
}

function ReadWriteHeapByteBuffer_0(backingArray, capacity, arrayOffset){
  HeapByteBuffer.call(this, backingArray, capacity, arrayOffset);
}

defineClass(255, 203, $intern_45, ReadWriteHeapByteBuffer, ReadWriteHeapByteBuffer_0);
_.putFloat = function putFloat_0(index_0, value_0){
  return $putInt_0(this, index_0, ($clinit_Numbers() , wfa[0] = value_0 , undefined , wia[0]));
}
;
_.slice_0 = function slice_2(){
  var slice_0;
  slice_0 = new ReadWriteHeapByteBuffer_0(this.backingArray, this.limit - this.position, this.offset + this.position);
  slice_0.order = this.order;
  return slice_0;
}
;
var Ljava_nio_ReadWriteHeapByteBuffer_2_classLit = createForClass('java.nio', 'ReadWriteHeapByteBuffer', 255);
function ShortToByteBufferAdapter(byteBuffer){
  ShortBuffer.call(this, byteBuffer.capacity >> 1);
  this.byteBuffer = byteBuffer;
  $clear_1(this.byteBuffer);
}

defineClass(258, 80, {7:1, 80:1}, ShortToByteBufferAdapter);
_.get_4 = function get_10(index_0){
  if (index_0 < 0 || index_0 >= this.limit) {
    throw toJs(new IndexOutOfBoundsException);
  }
  return $loadShort(this.byteBuffer, index_0 << 1);
}
;
_.put_2 = function put_6(c){
  if (this.position == this.limit) {
    throw toJs(new BufferOverflowException);
  }
  $putShort(this.byteBuffer, this.position++ << 1, c);
  return this;
}
;
var Ljava_nio_ShortToByteBufferAdapter_2_classLit = createForClass('java.nio', 'ShortToByteBufferAdapter', 258);
function $compareTo_11(this$static, that){
  return $compareToIgnoreCase(this$static.name_0, that.name_0);
}

defineClass(88, 1, $intern_47);
_.compareTo = function compareTo_8(that){
  return $compareTo_11(this, castTo(that, 88));
}
;
_.equals_0 = function equals_25(o){
  var that;
  if (o === this) {
    return true;
  }
  if (!instanceOf(o, 88)) {
    return false;
  }
  that = castTo(o, 88);
  return $equals_5(this.name_0, that.name_0);
}
;
_.hashCode_0 = function hashCode_26(){
  return $hashCode_2(this.name_0);
}
;
_.toString_0 = function toString_45(){
  return this.name_0;
}
;
var Ljava_nio_charset_Charset_2_classLit = createForClass('java.nio.charset', 'Charset', 88);
function $clinit_StandardCharsets(){
  $clinit_StandardCharsets = emptyMethod;
  $clinit_EmulatedCharset();
}

function $advanceToFind(this$static, o){
  var e, iter;
  for (iter = this$static.iterator(); iter.hasNext_0();) {
    e = iter.next_1();
    if (maskUndefined(o) === maskUndefined(e) || o != null && equals_Ljava_lang_Object__Z__devirtual$(o, e)) {
      return true;
    }
  }
  return false;
}

function $containsAll(this$static, c){
  var e, e$iterator;
  checkCriticalNotNull(c);
  for (e$iterator = c.iterator(); e$iterator.hasNext_0();) {
    e = e$iterator.next_1();
    if (!this$static.contains(e)) {
      return false;
    }
  }
  return true;
}

function $removeAll(this$static, c){
  var changed, iter, o, nextNode;
  checkCriticalNotNull(c);
  changed = false;
  for (iter = $listIterator(this$static, 0); iter.currentNode != iter.this$01.tail;) {
    o = $next_9(iter);
    if ($indexOf_2(c, o, 0) != -1) {
      checkCriticalState(!!iter.lastNode);
      nextNode = iter.lastNode.next_0;
      $removeNode(iter.this$01, iter.lastNode);
      iter.currentNode == iter.lastNode?(iter.currentNode = nextNode):--iter.currentIndex;
      iter.lastNode = null;
      changed = true;
    }
  }
  return changed;
}

function $toArray(this$static, a){
  var i, it, result, size_0;
  size_0 = this$static.size_0;
  a.length < size_0 && (a = stampJavaTypeInfo_1(new Array(size_0), a));
  result = a;
  it = $listIterator(this$static, 0);
  for (i = 0; i < size_0; ++i) {
    setCheck(result, i, $next_9(it));
  }
  a.length > size_0 && setCheck(a, size_0, null);
  return a;
}

function $toString_4(this$static){
  var e, e$iterator, joiner;
  joiner = new StringJoiner('[', ']');
  for (e$iterator = this$static.iterator(); e$iterator.hasNext_0();) {
    e = e$iterator.next_1();
    $add_12(joiner, maskUndefined(e) === maskUndefined(this$static)?'(this Collection)':e == null?'null':toString_30(e));
  }
  return !joiner.builder?joiner.emptyValue:joiner.suffix.length == 0?joiner.builder.string:joiner.builder.string + ('' + joiner.suffix);
}

defineClass(535, 1, {});
_.add_0 = function add_3(o){
  throw toJs(new UnsupportedOperationException_0('Add not supported on this collection'));
}
;
_.contains = function contains(o){
  return $advanceToFind(this, o);
}
;
_.isEmpty = function isEmpty(){
  return this.size_1() == 0;
}
;
_.toString_0 = function toString_46(){
  return $toString_4(this);
}
;
var Ljava_util_AbstractCollection_2_classLit = createForClass('java.util', 'AbstractCollection', 535);
function $containsEntry(this$static, entry){
  var key, ourValue, value_0;
  key = entry.getKey();
  value_0 = entry.getValue();
  ourValue = this$static.get_5(key);
  if (!(maskUndefined(value_0) === maskUndefined(ourValue) || value_0 != null && equals_Ljava_lang_Object__Z__devirtual$(value_0, ourValue))) {
    return false;
  }
  if (ourValue == null && !this$static.containsKey(key)) {
    return false;
  }
  return true;
}

function $implFindEntry(this$static, key){
  var entry, iter, k;
  for (iter = this$static.entrySet().iterator(); iter.hasNext_0();) {
    entry = castTo(iter.next_1(), 43);
    k = entry.getKey();
    if (maskUndefined(key) === maskUndefined(k) || key != null && equals_Ljava_lang_Object__Z__devirtual$(key, k)) {
      return entry;
    }
  }
  return null;
}

function $toString_5(this$static, o){
  return o === this$static?'(this Map)':o == null?'null':toString_30(o);
}

function getEntryValueOrNull(entry){
  return !entry?null:entry.getValue();
}

defineClass(534, 1, {121:1});
_.containsKey = function containsKey(key){
  return !!$implFindEntry(this, key);
}
;
_.containsValue = function containsValue(value_0){
  var entry, entry$iterator, v;
  for (entry$iterator = this.entrySet().iterator(); entry$iterator.hasNext_0();) {
    entry = castTo(entry$iterator.next_1(), 43);
    v = entry.getValue();
    if (maskUndefined(value_0) === maskUndefined(v) || value_0 != null && equals_Ljava_lang_Object__Z__devirtual$(value_0, v)) {
      return true;
    }
  }
  return false;
}
;
_.equals_0 = function equals_26(obj){
  var entry, entry$iterator, otherMap;
  if (obj === this) {
    return true;
  }
  if (!instanceOf(obj, 121)) {
    return false;
  }
  otherMap = castTo(obj, 121);
  if (this.size_1() != otherMap.size_1()) {
    return false;
  }
  for (entry$iterator = otherMap.entrySet().iterator(); entry$iterator.hasNext_0();) {
    entry = castTo(entry$iterator.next_1(), 43);
    if (!$containsEntry(this, entry)) {
      return false;
    }
  }
  return true;
}
;
_.get_5 = function get_11(key){
  return getEntryValueOrNull($implFindEntry(this, key));
}
;
_.hashCode_0 = function hashCode_27(){
  return hashCode_33(this.entrySet());
}
;
_.put_3 = function put_7(key, value_0){
  throw toJs(new UnsupportedOperationException_0('Put not supported on this map'));
}
;
_.size_1 = function size_1(){
  return this.entrySet().size_1();
}
;
_.toString_0 = function toString_47(){
  var entry, entry$iterator, joiner;
  joiner = new StringJoiner('{', '}');
  for (entry$iterator = this.entrySet().iterator(); entry$iterator.hasNext_0();) {
    entry = castTo(entry$iterator.next_1(), 43);
    $add_12(joiner, $toString_5(this, entry.getKey()) + '=' + $toString_5(this, entry.getValue()));
  }
  return !joiner.builder?joiner.emptyValue:joiner.suffix.length == 0?joiner.builder.string:joiner.builder.string + ('' + joiner.suffix);
}
;
var Ljava_util_AbstractMap_2_classLit = createForClass('java.util', 'AbstractMap', 534);
function $containsKey(this$static, key){
  return instanceOfString(key)?$hasStringValue(this$static, key):!!$getEntry(this$static.hashCodeMap, key);
}

function $containsValue_0(value_0, entries){
  var entry, entry$iterator;
  for (entry$iterator = entries.iterator(); entry$iterator.hasNext_0();) {
    entry = castTo(entry$iterator.next_1(), 43);
    if ($equals_7(value_0, entry.getValue())) {
      return true;
    }
  }
  return false;
}

function $get_8(this$static, key){
  return instanceOfString(key)?$getStringValue(this$static, key):getEntryValueOrNull($getEntry(this$static.hashCodeMap, key));
}

function $getStringValue(this$static, key){
  return key == null?getEntryValueOrNull($getEntry(this$static.hashCodeMap, null)):$get_12(this$static.stringMap, key);
}

function $hasStringValue(this$static, key){
  return key == null?!!$getEntry(this$static.hashCodeMap, null):$contains_5(this$static.stringMap, key);
}

function $put_4(this$static, key, value_0){
  return instanceOfString(key)?$putStringValue(this$static, key, value_0):$put_6(this$static.hashCodeMap, key, value_0);
}

function $putStringValue(this$static, key, value_0){
  return key == null?$put_6(this$static.hashCodeMap, null, value_0):$put_7(this$static.stringMap, key, value_0);
}

function $remove_6(this$static, key){
  return instanceOfString(key)?$removeStringValue(this$static, key):$remove_9(this$static.hashCodeMap, key);
}

function $removeStringValue(this$static, key){
  return key == null?$remove_9(this$static.hashCodeMap, null):$remove_10(this$static.stringMap, key);
}

function $reset_4(this$static){
  this$static.hashCodeMap = new InternalHashCodeMap(this$static);
  this$static.stringMap = new InternalStringMap(this$static);
  ++this$static.modCount;
}

function $size(this$static){
  return this$static.hashCodeMap.size_0 + this$static.stringMap.size_0;
}

defineClass(367, 534, {121:1});
_.containsKey = function containsKey_0(key){
  return $containsKey(this, key);
}
;
_.containsValue = function containsValue_0(value_0){
  return $containsValue_0(value_0, this.stringMap) || $containsValue_0(value_0, this.hashCodeMap);
}
;
_.entrySet = function entrySet(){
  return new AbstractHashMap$EntrySet(this);
}
;
_.get_5 = function get_12(key){
  return $get_8(this, key);
}
;
_.put_3 = function put_8(key, value_0){
  return $put_4(this, key, value_0);
}
;
_.size_1 = function size_2(){
  return $size(this);
}
;
_.modCount = 0;
var Ljava_util_AbstractHashMap_2_classLit = createForClass('java.util', 'AbstractHashMap', 367);
defineClass(536, 535, $intern_48);
_.equals_0 = function equals_27(o){
  var other;
  if (o === this) {
    return true;
  }
  if (!instanceOf(o, 104)) {
    return false;
  }
  other = castTo(o, 104);
  if (other.size_1() != this.size_1()) {
    return false;
  }
  return $containsAll(this, other);
}
;
_.hashCode_0 = function hashCode_28(){
  return hashCode_33(this);
}
;
var Ljava_util_AbstractSet_2_classLit = createForClass('java.util', 'AbstractSet', 536);
function $contains_1(this$static, o){
  if (instanceOf(o, 43)) {
    return $containsEntry(this$static.this$01, castTo(o, 43));
  }
  return false;
}

function AbstractHashMap$EntrySet(this$0){
  this.this$01 = this$0;
}

defineClass(74, 536, $intern_48, AbstractHashMap$EntrySet);
_.contains = function contains_0(o){
  return $contains_1(this, o);
}
;
_.iterator = function iterator_15(){
  return new AbstractHashMap$EntrySetIterator(this.this$01);
}
;
_.size_1 = function size_3(){
  return $size(this.this$01);
}
;
var Ljava_util_AbstractHashMap$EntrySet_2_classLit = createForClass('java.util', 'AbstractHashMap/EntrySet', 74);
function $computeHasNext(this$static){
  if (this$static.current.hasNext_0()) {
    return true;
  }
  if (this$static.current != this$static.stringMapEntries) {
    return false;
  }
  this$static.current = new InternalHashCodeMap$1(this$static.this$01.hashCodeMap);
  return this$static.current.hasNext_0();
}

function $next_6(this$static){
  var rv;
  checkCriticalConcurrentModification(this$static.this$01.modCount, this$static.lastModCount);
  checkCriticalElement(this$static.hasNext);
  rv = castTo(this$static.current.next_1(), 43);
  this$static.hasNext = $computeHasNext(this$static);
  return rv;
}

function AbstractHashMap$EntrySetIterator(this$0){
  this.this$01 = this$0;
  this.stringMapEntries = new InternalStringMap$1(this.this$01.stringMap);
  this.current = this.stringMapEntries;
  this.hasNext = $computeHasNext(this);
  this.lastModCount = this.this$01.modCount;
}

defineClass(75, 1, {}, AbstractHashMap$EntrySetIterator);
_.next_1 = function next_7(){
  return $next_6(this);
}
;
_.hasNext_0 = function hasNext_6(){
  return this.hasNext;
}
;
_.hasNext = false;
_.lastModCount = 0;
var Ljava_util_AbstractHashMap$EntrySetIterator_2_classLit = createForClass('java.util', 'AbstractHashMap/EntrySetIterator', 75);
function $sort(this$static, c){
  var a, i;
  a = $toArray(this$static, initUnidimensionalArray(Ljava_lang_Object_2_classLit, $intern_9, 1, this$static.size_0, 5, 1));
  mergeSort(a, 0, a.length, c);
  for (i = 0; i < a.length; i++) {
    $set_7(this$static, i, a[i]);
  }
}

function $equals_6(this$static, o){
  var elem, elem$iterator, elemOther, iterOther, other;
  if (maskUndefined(o) === maskUndefined(this$static)) {
    return true;
  }
  if (!instanceOf(o, 82)) {
    return false;
  }
  other = castTo(o, 82);
  if (this$static.size_1() != other.size_1()) {
    return false;
  }
  iterOther = other.iterator();
  for (elem$iterator = this$static.iterator(); elem$iterator.hasNext_0();) {
    elem = elem$iterator.next_1();
    elemOther = iterOther.next_1();
    if (!(maskUndefined(elem) === maskUndefined(elemOther) || elem != null && equals_Ljava_lang_Object__Z__devirtual$(elem, elemOther))) {
      return false;
    }
  }
  return true;
}

defineClass(538, 535, $intern_49);
_.add_1 = function add_4(index_0, element){
  throw toJs(new UnsupportedOperationException_0('Add not supported on this list'));
}
;
_.add_0 = function add_5(obj){
  this.add_1(this.size_1(), obj);
  return true;
}
;
_.equals_0 = function equals_28(o){
  return $equals_6(this, o);
}
;
_.hashCode_0 = function hashCode_29(){
  return hashCode_34(this);
}
;
_.iterator = function iterator_16(){
  return new AbstractList$IteratorImpl(this);
}
;
_.listIterator = function listIterator(){
  return this.listIterator_0(0);
}
;
_.listIterator_0 = function listIterator_0(from){
  return new AbstractList$ListIteratorImpl(this, from);
}
;
var Ljava_util_AbstractList_2_classLit = createForClass('java.util', 'AbstractList', 538);
function AbstractList$IteratorImpl(this$0){
  this.this$01_0 = this$0;
}

defineClass(233, 1, {}, AbstractList$IteratorImpl);
_.hasNext_0 = function hasNext_7(){
  return this.i < this.this$01_0.size_1();
}
;
_.next_1 = function next_8(){
  checkCriticalElement(this.i < this.this$01_0.size_1());
  return this.this$01_0.get_6(this.i++);
}
;
_.i = 0;
var Ljava_util_AbstractList$IteratorImpl_2_classLit = createForClass('java.util', 'AbstractList/IteratorImpl', 233);
function $previous(this$static){
  checkCriticalElement(this$static.i > 0);
  return this$static.this$01.get_6(--this$static.i);
}

function AbstractList$ListIteratorImpl(this$0, start_0){
  this.this$01 = this$0;
  AbstractList$IteratorImpl.call(this, this$0);
  checkCriticalPositionIndex(start_0, this$0.size_1());
  this.i = start_0;
}

defineClass(175, 233, {}, AbstractList$ListIteratorImpl);
_.hasPrevious = function hasPrevious(){
  return this.i > 0;
}
;
_.previous = function previous_0(){
  return $previous(this);
}
;
var Ljava_util_AbstractList$ListIteratorImpl_2_classLit = createForClass('java.util', 'AbstractList/ListIteratorImpl', 175);
function AbstractMap$1(this$0){
  this.this$01 = this$0;
}

defineClass(146, 536, $intern_48, AbstractMap$1);
_.contains = function contains_1(key){
  return $containsKey(this.this$01, key);
}
;
_.iterator = function iterator_17(){
  var outerIter;
  return outerIter = new AbstractHashMap$EntrySetIterator((new AbstractHashMap$EntrySet(this.this$01)).this$01) , new AbstractMap$1$1(outerIter);
}
;
_.size_1 = function size_4(){
  return $size(this.this$01);
}
;
var Ljava_util_AbstractMap$1_2_classLit = createForClass('java.util', 'AbstractMap/1', 146);
function AbstractMap$1$1(val$outerIter){
  this.val$outerIter2 = val$outerIter;
}

defineClass(125, 1, {}, AbstractMap$1$1);
_.hasNext_0 = function hasNext_8(){
  return this.val$outerIter2.hasNext;
}
;
_.next_1 = function next_9(){
  var entry;
  return entry = $next_6(this.val$outerIter2) , entry.getKey();
}
;
var Ljava_util_AbstractMap$1$1_2_classLit = createForClass('java.util', 'AbstractMap/1/1', 125);
function AbstractMap$2(this$0){
  this.this$01 = this$0;
}

defineClass(230, 535, {}, AbstractMap$2);
_.contains = function contains_2(value_0){
  return this.this$01.containsValue(value_0);
}
;
_.iterator = function iterator_18(){
  var outerIter;
  return outerIter = this.this$01.entrySet().iterator() , new AbstractMap$2$1(outerIter);
}
;
_.size_1 = function size_5(){
  return this.this$01.size_1();
}
;
var Ljava_util_AbstractMap$2_2_classLit = createForClass('java.util', 'AbstractMap/2', 230);
function AbstractMap$2$1(val$outerIter){
  this.val$outerIter2 = val$outerIter;
}

defineClass(172, 1, {}, AbstractMap$2$1);
_.hasNext_0 = function hasNext_9(){
  return this.val$outerIter2.hasNext_0();
}
;
_.next_1 = function next_10(){
  var entry;
  return entry = castTo(this.val$outerIter2.next_1(), 43) , entry.getValue();
}
;
var Ljava_util_AbstractMap$2$1_2_classLit = createForClass('java.util', 'AbstractMap/2/1', 172);
defineClass(368, 1, $intern_50);
_.equals_0 = function equals_29(other){
  var entry;
  if (!instanceOf(other, 43)) {
    return false;
  }
  entry = castTo(other, 43);
  return equals_35(this.key, entry.getKey()) && equals_35(this.value_0, entry.getValue());
}
;
_.getKey = function getKey(){
  return this.key;
}
;
_.getValue = function getValue(){
  return this.value_0;
}
;
_.hashCode_0 = function hashCode_30(){
  return hashCode_38(this.key) ^ hashCode_38(this.value_0);
}
;
_.setValue = function setValue(value_0){
  var oldValue;
  oldValue = this.value_0;
  this.value_0 = value_0;
  return oldValue;
}
;
_.toString_0 = function toString_48(){
  return this.key + '=' + this.value_0;
}
;
var Ljava_util_AbstractMap$AbstractEntry_2_classLit = createForClass('java.util', 'AbstractMap/AbstractEntry', 368);
function AbstractMap$SimpleEntry(key, value_0){
  this.key = key;
  this.value_0 = value_0;
}

defineClass(369, 368, $intern_50, AbstractMap$SimpleEntry);
var Ljava_util_AbstractMap$SimpleEntry_2_classLit = createForClass('java.util', 'AbstractMap/SimpleEntry', 369);
defineClass(539, 1, $intern_50);
_.equals_0 = function equals_30(other){
  var entry;
  if (!instanceOf(other, 43)) {
    return false;
  }
  entry = castTo(other, 43);
  return equals_35(this.getKey(), entry.getKey()) && equals_35(this.getValue(), entry.getValue());
}
;
_.hashCode_0 = function hashCode_31(){
  return hashCode_38(this.getKey()) ^ hashCode_38(this.getValue());
}
;
_.toString_0 = function toString_49(){
  return this.getKey() + '=' + this.getValue();
}
;
var Ljava_util_AbstractMapEntry_2_classLit = createForClass('java.util', 'AbstractMapEntry', 539);
function $get_9(this$static, index_0){
  var iter;
  iter = $listIterator(this$static, index_0);
  try {
    return $next_9(iter);
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 57)) {
      throw toJs(new IndexOutOfBoundsException_0("Can't get element " + index_0));
    }
     else 
      throw toJs($e0);
  }
}

function $set_7(this$static, index_0, element){
  var iter, old;
  iter = $listIterator(this$static, index_0);
  try {
    old = $next_9(iter);
    checkCriticalState(!!iter.lastNode);
    iter.lastNode.value_0 = element;
    return old;
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 57)) {
      throw toJs(new IndexOutOfBoundsException_0("Can't set element " + index_0));
    }
     else 
      throw toJs($e0);
  }
}

defineClass(540, 538, $intern_49);
_.add_1 = function add_6(index_0, element){
  var iter;
  iter = $listIterator(this, index_0);
  $addNode(iter.this$01, element, iter.currentNode.prev, iter.currentNode);
  ++iter.currentIndex;
  iter.lastNode = null;
}
;
_.get_6 = function get_13(index_0){
  return $get_9(this, index_0);
}
;
_.iterator = function iterator_19(){
  return $listIterator(this, 0);
}
;
var Ljava_util_AbstractSequentialList_2_classLit = createForClass('java.util', 'AbstractSequentialList', 540);
function $add_8(this$static, o){
  push_1(this$static.array, o);
  return true;
}

function $get_10(this$static, index_0){
  checkCriticalElementIndex(index_0, this$static.array.length);
  return this$static.array[index_0];
}

function $indexOf_2(this$static, o, index_0){
  for (; index_0 < this$static.array.length; ++index_0) {
    if (equals_35(o, this$static.array[index_0])) {
      return index_0;
    }
  }
  return -1;
}

function $remove_7(this$static, o){
  var i;
  i = $indexOf_2(this$static, o, 0);
  if (i == -1) {
    return false;
  }
  checkCriticalElementIndex(i, this$static.array.length);
  removeFrom(this$static.array, i);
  return true;
}

function $toArray_0(this$static, out){
  var i, size_0;
  size_0 = this$static.array.length;
  out.length < size_0 && (out = stampJavaTypeInfo_1(new Array(size_0), out));
  for (i = 0; i < size_0; ++i) {
    setCheck(out, i, this$static.array[i]);
  }
  out.length > size_0 && setCheck(out, size_0, null);
  return out;
}

function ArrayList(){
  this.array = initUnidimensionalArray(Ljava_lang_Object_2_classLit, $intern_9, 1, 0, 5, 1);
}

defineClass(20, 538, $intern_51, ArrayList);
_.add_1 = function add_7(index_0, o){
  checkCriticalPositionIndex(index_0, this.array.length);
  insertTo(this.array, index_0, o);
}
;
_.add_0 = function add_8(o){
  return $add_8(this, o);
}
;
_.contains = function contains_3(o){
  return $indexOf_2(this, o, 0) != -1;
}
;
_.get_6 = function get_14(index_0){
  return $get_10(this, index_0);
}
;
_.isEmpty = function isEmpty_0(){
  return this.array.length == 0;
}
;
_.iterator = function iterator_20(){
  return new ArrayList$1(this);
}
;
_.size_1 = function size_6(){
  return this.array.length;
}
;
var Ljava_util_ArrayList_2_classLit = createForClass('java.util', 'ArrayList', 20);
function $next_7(this$static){
  checkCriticalElement(this$static.i < this$static.this$01.array.length);
  this$static.last = this$static.i++;
  return this$static.this$01.array[this$static.last];
}

function ArrayList$1(this$0){
  this.this$01 = this$0;
}

defineClass(32, 1, {}, ArrayList$1);
_.hasNext_0 = function hasNext_10(){
  return this.i < this.this$01.array.length;
}
;
_.next_1 = function next_11(){
  return $next_7(this);
}
;
_.i = 0;
_.last = -1;
var Ljava_util_ArrayList$1_2_classLit = createForClass('java.util', 'ArrayList/1', 32);
function checkCopyOfRange(original, from, to){
  var len;
  checkCriticalArgument_0(from <= to, from + ' > ' + to);
  len = original.length;
  checkCriticalArrayBounds(from, from, len);
}

function copyOf(original, newLength){
  checkCriticalArraySize(newLength);
  return copyPrimitiveArray(original, initUnidimensionalArray(B_classLit, $intern_6, 5, newLength, 15, 1), 0, newLength);
}

function copyOf_0(original, newLength){
  var result;
  checkCriticalArraySize(newLength);
  return result = original.slice(0, newLength) , result.length = newLength , stampJavaTypeInfo_0(result, original);
}

function copyPrimitiveArray(original, copy, from, to){
  var copyLen, len;
  len = original.length;
  copyLen = $wnd.Math.min(to, len) - from;
  copy_1(original, from, copy, 0, copyLen);
  return copy;
}

function fill(a, toIndex){
  checkCriticalArrayBounds(0, toIndex, a.length);
  fill0(a, toIndex);
}

function fill0(a, toIndex){
  var i;
  for (i = 0; i < toIndex; ++i) {
    setCheck(a, i, null);
  }
}

function hashCode_32(a){
  var e, e$array, e$index, e$max, hashCode;
  hashCode = 1;
  for (e$array = a , e$index = 0 , e$max = e$array.length; e$index < e$max; ++e$index) {
    e = e$array[e$index];
    hashCode = 31 * hashCode + (e != null?hashCode__I__devirtual$(e):0);
    hashCode = hashCode | 0;
  }
  return hashCode;
}

function insertionSort(array, low, high, comp){
  var i, j, t;
  for (i = low + 1; i < high; ++i) {
    for (j = i; j > low && comp.compare(array[j - 1], array[j]) > 0; --j) {
      t = array[j];
      setCheck(array, j, array[j - 1]);
      setCheck(array, j - 1, t);
    }
  }
}

function merge(src_0, srcLow, srcMid, srcHigh, dest, destLow, destHigh, comp){
  var topIdx;
  topIdx = srcMid;
  while (destLow < destHigh) {
    topIdx >= srcHigh || srcLow < srcMid && comp.compare(src_0[srcLow], src_0[topIdx]) <= 0?setCheck(dest, destLow++, src_0[srcLow++]):setCheck(dest, destLow++, src_0[topIdx++]);
  }
}

function mergeSort(x_0, fromIndex, toIndex, comp){
  var temp;
  comp = ($clinit_Comparators() , !comp?INTERNAL_NATURAL_ORDER:comp);
  temp = x_0.slice(fromIndex, toIndex);
  mergeSort_0(temp, x_0, fromIndex, toIndex, -fromIndex, comp);
}

function mergeSort_0(temp, array, low, high, ofs, comp){
  var length_0, tempHigh, tempLow, tempMid;
  length_0 = high - low;
  if (length_0 < 7) {
    insertionSort(array, low, high, comp);
    return;
  }
  tempLow = low + ofs;
  tempHigh = high + ofs;
  tempMid = tempLow + (tempHigh - tempLow >> 1);
  mergeSort_0(array, temp, tempLow, tempMid, -ofs, comp);
  mergeSort_0(array, temp, tempMid, tempHigh, -ofs, comp);
  if (comp.compare(temp[tempMid - 1], temp[tempMid]) <= 0) {
    while (low < high) {
      setCheck(array, low++, temp[tempLow++]);
    }
    return;
  }
  merge(temp, tempLow, tempMid, tempHigh, array, low, high, comp);
}

function $clinit_Collections(){
  $clinit_Collections = emptyMethod;
  EMPTY_LIST = new Collections$EmptyList;
}

function hashCode_33(collection){
  $clinit_Collections();
  var e, e$iterator, hashCode;
  hashCode = 0;
  for (e$iterator = collection.iterator(); e$iterator.hasNext_0();) {
    e = e$iterator.next_1();
    hashCode = hashCode + (e != null?hashCode__I__devirtual$(e):0);
    hashCode = hashCode | 0;
  }
  return hashCode;
}

function hashCode_34(list){
  $clinit_Collections();
  var e, e$iterator, hashCode;
  hashCode = 1;
  for (e$iterator = list.iterator(); e$iterator.hasNext_0();) {
    e = e$iterator.next_1();
    hashCode = 31 * hashCode + (e != null?hashCode__I__devirtual$(e):0);
    hashCode = hashCode | 0;
  }
  return hashCode;
}

var EMPTY_LIST;
function Collections$EmptyList(){
}

defineClass(380, 538, $intern_51, Collections$EmptyList);
_.contains = function contains_4(object){
  return false;
}
;
_.get_6 = function get_15(location_0){
  checkCriticalElementIndex(location_0, 0);
  return null;
}
;
_.iterator = function iterator_21(){
  return $clinit_Collections() , $clinit_Collections$EmptyListIterator() , INSTANCE_0;
}
;
_.listIterator = function listIterator_1(){
  return $clinit_Collections() , $clinit_Collections$EmptyListIterator() , INSTANCE_0;
}
;
_.size_1 = function size_7(){
  return 0;
}
;
var Ljava_util_Collections$EmptyList_2_classLit = createForClass('java.util', 'Collections/EmptyList', 380);
function $clinit_Collections$EmptyListIterator(){
  $clinit_Collections$EmptyListIterator = emptyMethod;
  INSTANCE_0 = new Collections$EmptyListIterator;
}

function Collections$EmptyListIterator(){
}

defineClass(381, 1, {}, Collections$EmptyListIterator);
_.hasNext_0 = function hasNext_11(){
  return false;
}
;
_.hasPrevious = function hasPrevious_0(){
  return false;
}
;
_.next_1 = function next_12(){
  throw toJs(new NoSuchElementException);
}
;
_.previous = function previous_1(){
  throw toJs(new NoSuchElementException);
}
;
var INSTANCE_0;
var Ljava_util_Collections$EmptyListIterator_2_classLit = createForClass('java.util', 'Collections/EmptyListIterator', 381);
defineClass(382, 1, {});
_.add_0 = function add_9(o){
  throw toJs(new UnsupportedOperationException);
}
;
_.isEmpty = function isEmpty_1(){
  return this.coll.array.length == 0;
}
;
_.iterator = function iterator_22(){
  return new Collections$UnmodifiableCollectionIterator(new ArrayList$1(this.coll));
}
;
_.size_1 = function size_8(){
  return this.coll.array.length;
}
;
_.toString_0 = function toString_50(){
  return $toString_4(this.coll);
}
;
var Ljava_util_Collections$UnmodifiableCollection_2_classLit = createForClass('java.util', 'Collections/UnmodifiableCollection', 382);
function Collections$UnmodifiableCollectionIterator(it){
  this.it = it;
}

defineClass(174, 1, {}, Collections$UnmodifiableCollectionIterator);
_.hasNext_0 = function hasNext_12(){
  return this.it.hasNext_0();
}
;
_.next_1 = function next_13(){
  return this.it.next_1();
}
;
var Ljava_util_Collections$UnmodifiableCollectionIterator_2_classLit = createForClass('java.util', 'Collections/UnmodifiableCollectionIterator', 174);
defineClass(383, 382, $intern_49);
_.equals_0 = function equals_31(o){
  return $equals_6(this.list, o);
}
;
_.hashCode_0 = function hashCode_35(){
  return hashCode_34(this.list);
}
;
_.isEmpty = function isEmpty_2(){
  return this.list.array.length == 0;
}
;
_.listIterator = function listIterator_2(){
  return new Collections$UnmodifiableListIterator(new AbstractList$ListIteratorImpl(this.list, 0));
}
;
_.listIterator_0 = function listIterator_3(from){
  return new Collections$UnmodifiableListIterator(new AbstractList$ListIteratorImpl(this.list, from));
}
;
var Ljava_util_Collections$UnmodifiableList_2_classLit = createForClass('java.util', 'Collections/UnmodifiableList', 383);
function Collections$UnmodifiableListIterator(lit){
  Collections$UnmodifiableCollectionIterator.call(this, lit);
  this.lit = lit;
}

defineClass(232, 174, {}, Collections$UnmodifiableListIterator);
_.hasPrevious = function hasPrevious_1(){
  return this.lit.i > 0;
}
;
_.previous = function previous_2(){
  return $previous(this.lit);
}
;
var Ljava_util_Collections$UnmodifiableListIterator_2_classLit = createForClass('java.util', 'Collections/UnmodifiableListIterator', 232);
function Collections$UnmodifiableRandomAccessList(list){
  this.coll = list;
  this.list = list;
}

defineClass(384, 383, $intern_49, Collections$UnmodifiableRandomAccessList);
var Ljava_util_Collections$UnmodifiableRandomAccessList_2_classLit = createForClass('java.util', 'Collections/UnmodifiableRandomAccessList', 384);
function $clinit_Comparators(){
  $clinit_Comparators = emptyMethod;
  INTERNAL_NATURAL_ORDER = new Comparators$NaturalOrderComparator;
}

var INTERNAL_NATURAL_ORDER;
function $compare(a, b){
  return checkCriticalNotNull(a) , compareTo_Ljava_lang_Object__I__devirtual$(a, (checkCriticalNotNull(b) , b));
}

function Comparators$NaturalOrderComparator(){
}

defineClass(491, 1, $intern_2, Comparators$NaturalOrderComparator);
_.compare = function compare_5(a, b){
  return $compare(castTo(a, 7), castTo(b, 7));
}
;
_.equals_0 = function equals_32(other){
  return this === other;
}
;
var Ljava_util_Comparators$NaturalOrderComparator_2_classLit = createForClass('java.util', 'Comparators/NaturalOrderComparator', 491);
function ConcurrentModificationException(){
  RuntimeException.call(this);
}

defineClass(401, 27, $intern_21, ConcurrentModificationException);
var Ljava_util_ConcurrentModificationException_2_classLit = createForClass('java.util', 'ConcurrentModificationException', 401);
function $compareTo_12(this$static, other){
  return compare_4(fromDouble_0(this$static.jsdate.getTime()), fromDouble_0(other.jsdate.getTime()));
}

function Date_0(){
  this.jsdate = new $wnd.Date;
}

function padTwo(number){
  return number < 10?'0' + number:'' + number;
}

defineClass(116, 1, {3:1, 7:1, 116:1}, Date_0);
_.compareTo = function compareTo_9(other){
  return $compareTo_12(this, castTo(other, 116));
}
;
_.equals_0 = function equals_33(obj){
  return instanceOf(obj, 116) && eq_0(fromDouble_0(this.jsdate.getTime()), fromDouble_0(castTo(obj, 116).jsdate.getTime()));
}
;
_.hashCode_0 = function hashCode_36(){
  var time;
  time = fromDouble_0(this.jsdate.getTime());
  return toInt_0(xor_0(time, shru_0(time, 32)));
}
;
_.toString_0 = function toString_51(){
  var hourOffset, minuteOffset, offset;
  offset = -this.jsdate.getTimezoneOffset();
  hourOffset = (offset >= 0?'+':'') + (offset / 60 | 0);
  minuteOffset = padTwo($wnd.Math.abs(offset) % 60);
  return ($clinit_Date$StringData() , DAYS)[this.jsdate.getDay()] + ' ' + MONTHS[this.jsdate.getMonth()] + ' ' + padTwo(this.jsdate.getDate()) + ' ' + padTwo(this.jsdate.getHours()) + ':' + padTwo(this.jsdate.getMinutes()) + ':' + padTwo(this.jsdate.getSeconds()) + ' GMT' + hourOffset + minuteOffset + ' ' + this.jsdate.getFullYear();
}
;
var Ljava_util_Date_2_classLit = createForClass('java.util', 'Date', 116);
function $clinit_Date$StringData(){
  $clinit_Date$StringData = emptyMethod;
  DAYS = stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_String_2_classLit, 1), $intern_11, 2, 6, ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']);
  MONTHS = stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_String_2_classLit, 1), $intern_11, 2, 6, ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']);
}

var DAYS, MONTHS;
function $get_11(this$static, k){
  return $contains_3(this$static.keySet, k)?this$static.values[castTo(k, 8).ordinal]:null;
}

function $put_5(this$static, key, value_0){
  $add_9(this$static.keySet, key);
  return setAt(this$static.values, key.ordinal, value_0);
}

function EnumMap(type_0){
  var all;
  this.keySet = (all = castTo(type_0.enumConstantsFunc && type_0.enumConstantsFunc(), 12) , new EnumSet$EnumSetImpl(all, castTo(createFrom(all, all.length), 12)));
  this.values = initUnidimensionalArray(Ljava_lang_Object_2_classLit, $intern_9, 1, this.keySet.all.length, 5, 1);
}

defineClass(432, 534, {121:1}, EnumMap);
_.put_3 = function put_9(key, value_0){
  return $add_9(this.keySet, key) , setAt(this.values, null.$_nullField, value_0);
}
;
_.containsKey = function containsKey_1(key){
  return $contains_3(this.keySet, key);
}
;
_.containsValue = function containsValue_1(value_0){
  var key, key$iterator;
  for (key$iterator = new EnumSet$EnumSetImpl$IteratorImpl(this.keySet); key$iterator.i < key$iterator.this$11.all.length;) {
    key = $next_8(key$iterator);
    if (equals_35(value_0, this.values[key.ordinal])) {
      return true;
    }
  }
  return false;
}
;
_.entrySet = function entrySet_0(){
  return new EnumMap$EntrySet(this);
}
;
_.get_5 = function get_16(k){
  return $get_11(this, k);
}
;
_.size_1 = function size_9(){
  return this.keySet.size_0;
}
;
var Ljava_util_EnumMap_2_classLit = createForClass('java.util', 'EnumMap', 432);
function $contains_2(this$static, o){
  if (instanceOf(o, 43)) {
    return $containsEntry(this$static.this$01, castTo(o, 43));
  }
  return false;
}

function EnumMap$EntrySet(this$0){
  this.this$01 = this$0;
}

defineClass(433, 536, $intern_48, EnumMap$EntrySet);
_.contains = function contains_5(o){
  return $contains_2(this, o);
}
;
_.iterator = function iterator_23(){
  return new EnumMap$EntrySetIterator(this.this$01);
}
;
_.size_1 = function size_10(){
  return this.this$01.keySet.size_0;
}
;
var Ljava_util_EnumMap$EntrySet_2_classLit = createForClass('java.util', 'EnumMap/EntrySet', 433);
function EnumMap$EntrySetIterator(this$0){
  this.this$01 = this$0;
  this.it = new EnumSet$EnumSetImpl$IteratorImpl(this.this$01.keySet);
}

defineClass(434, 1, {}, EnumMap$EntrySetIterator);
_.next_1 = function next_14(){
  return this.key = $next_8(this.it) , new EnumMap$MapEntry(this.this$01, this.key);
}
;
_.hasNext_0 = function hasNext_13(){
  return $hasNext_0(this.it);
}
;
var Ljava_util_EnumMap$EntrySetIterator_2_classLit = createForClass('java.util', 'EnumMap/EntrySetIterator', 434);
function EnumMap$MapEntry(this$0, key){
  this.this$01 = this$0;
  this.key = key;
}

defineClass(435, 539, $intern_50, EnumMap$MapEntry);
_.getKey = function getKey_0(){
  return this.key;
}
;
_.getValue = function getValue_0(){
  return this.this$01.values[this.key.ordinal];
}
;
_.setValue = function setValue_0(value_0){
  return setAt(this.this$01.values, this.key.ordinal, value_0);
}
;
var Ljava_util_EnumMap$MapEntry_2_classLit = createForClass('java.util', 'EnumMap/MapEntry', 435);
defineClass(545, 536, $intern_48);
var Ljava_util_EnumSet_2_classLit = createForClass('java.util', 'EnumSet', 545);
function $add_9(this$static, e){
  var ordinal;
  checkCriticalNotNull(e);
  ordinal = e.ordinal;
  if (!this$static.set_0[ordinal]) {
    setCheck(this$static.set_0, ordinal, e);
    ++this$static.size_0;
    return true;
  }
  return false;
}

function $contains_3(this$static, o){
  return instanceOf(o, 8) && $containsEnum(this$static, castTo(o, 8));
}

function $containsEnum(this$static, e){
  return !!e && this$static.set_0[e.ordinal] == e;
}

function EnumSet$EnumSetImpl(all, set_0){
  this.all = all;
  this.set_0 = set_0;
  this.size_0 = 0;
}

defineClass(439, 545, $intern_48, EnumSet$EnumSetImpl);
_.add_0 = function add_10(e){
  return $add_9(this, throwClassCastExceptionUnlessNull(e));
}
;
_.contains = function contains_6(o){
  return $contains_3(this, o);
}
;
_.iterator = function iterator_24(){
  return new EnumSet$EnumSetImpl$IteratorImpl(this);
}
;
_.size_1 = function size_11(){
  return this.size_0;
}
;
_.size_0 = 0;
var Ljava_util_EnumSet$EnumSetImpl_2_classLit = createForClass('java.util', 'EnumSet/EnumSetImpl', 439);
function $findNext(this$static){
  var c;
  ++this$static.i;
  for (c = this$static.this$11.all.length; this$static.i < c; ++this$static.i) {
    if (this$static.this$11.set_0[this$static.i]) {
      return;
    }
  }
}

function $hasNext_0(this$static){
  return this$static.i < this$static.this$11.all.length;
}

function $next_8(this$static){
  checkCriticalElement(this$static.i < this$static.this$11.all.length);
  this$static.last = this$static.i;
  $findNext(this$static);
  return this$static.this$11.set_0[this$static.last];
}

function EnumSet$EnumSetImpl$IteratorImpl(this$1){
  this.this$11 = this$1;
  $findNext(this);
}

defineClass(187, 1, {}, EnumSet$EnumSetImpl$IteratorImpl);
_.next_1 = function next_15(){
  return $next_8(this);
}
;
_.hasNext_0 = function hasNext_14(){
  return $hasNext_0(this);
}
;
_.i = -1;
_.last = -1;
var Ljava_util_EnumSet$EnumSetImpl$IteratorImpl_2_classLit = createForClass('java.util', 'EnumSet/EnumSetImpl/IteratorImpl', 187);
function $equals_7(value1, value2){
  return maskUndefined(value1) === maskUndefined(value2) || value1 != null && equals_Ljava_lang_Object__Z__devirtual$(value1, value2);
}

function $getHashCode(key){
  var hashCode;
  if (key == null) {
    return 0;
  }
  hashCode = hashCode__I__devirtual$(key);
  return hashCode | 0;
}

function HashMap(){
  $reset_4(this);
}

defineClass(14, 367, {3:1, 121:1}, HashMap);
var Ljava_util_HashMap_2_classLit = createForClass('java.util', 'HashMap', 14);
function $add_10(this$static, o){
  var old;
  old = $put_4(this$static.map_0, o, this$static);
  return old == null;
}

function $contains_4(this$static, o){
  return $containsKey(this$static.map_0, o);
}

function $remove_8(this$static, o){
  return $remove_6(this$static.map_0, o) != null;
}

function HashSet(){
  this.map_0 = new HashMap;
}

defineClass(147, 536, {3:1, 104:1}, HashSet);
_.add_0 = function add_11(o){
  return $add_10(this, o);
}
;
_.contains = function contains_7(o){
  return $contains_4(this, o);
}
;
_.isEmpty = function isEmpty_3(){
  return $size(this.map_0) == 0;
}
;
_.iterator = function iterator_25(){
  var outerIter;
  return outerIter = new AbstractHashMap$EntrySetIterator((new AbstractHashMap$EntrySet((new AbstractMap$1(this.map_0)).this$01)).this$01) , new AbstractMap$1$1(outerIter);
}
;
_.size_1 = function size_12(){
  return $size(this.map_0);
}
;
var Ljava_util_HashSet_2_classLit = createForClass('java.util', 'HashSet', 147);
function $findEntryInChain(key, chain){
  var entry, entry$array, entry$index, entry$max;
  for (entry$array = chain , entry$index = 0 , entry$max = entry$array.length; entry$index < entry$max; ++entry$index) {
    entry = entry$array[entry$index];
    if ($equals_7(key, entry.getKey())) {
      return entry;
    }
  }
  return null;
}

function $getChainOrEmpty(this$static, hashCode){
  var chain;
  chain = this$static.backingMap.get(hashCode);
  return chain == null?initUnidimensionalArray(Ljava_lang_Object_2_classLit, $intern_9, 1, 0, 5, 1):chain;
}

function $getEntry(this$static, key){
  return $findEntryInChain(key, $getChainOrEmpty(this$static, $getHashCode(key)));
}

function $put_6(this$static, key, value_0){
  var chain, chain0, entry, hashCode;
  hashCode = $getHashCode(key);
  chain0 = (chain = this$static.backingMap.get(hashCode) , chain == null?initUnidimensionalArray(Ljava_lang_Object_2_classLit, $intern_9, 1, 0, 5, 1):chain);
  if (chain0.length == 0) {
    this$static.backingMap.set(hashCode, chain0);
  }
   else {
    entry = $findEntryInChain(key, chain0);
    if (entry) {
      return entry.setValue(value_0);
    }
  }
  setCheck(chain0, chain0.length, new AbstractMap$SimpleEntry(key, value_0));
  ++this$static.size_0;
  ++this$static.host.modCount;
  return null;
}

function $remove_9(this$static, key){
  var chain, chain0, entry, hashCode, i;
  hashCode = $getHashCode(key);
  chain0 = (chain = this$static.backingMap.get(hashCode) , chain == null?initUnidimensionalArray(Ljava_lang_Object_2_classLit, $intern_9, 1, 0, 5, 1):chain);
  for (i = 0; i < chain0.length; i++) {
    entry = chain0[i];
    if ($equals_7(key, entry.getKey())) {
      if (chain0.length == 1) {
        chain0.length = 0;
        $delete(this$static.backingMap, hashCode);
      }
       else {
        chain0.splice(i, 1);
      }
      --this$static.size_0;
      ++this$static.host.modCount;
      return entry.getValue();
    }
  }
  return null;
}

function InternalHashCodeMap(host){
  this.backingMap = newJsMap();
  this.host = host;
}

defineClass(385, 1, {}, InternalHashCodeMap);
_.iterator = function iterator_26(){
  return new InternalHashCodeMap$1(this);
}
;
_.size_0 = 0;
var Ljava_util_InternalHashCodeMap_2_classLit = createForClass('java.util', 'InternalHashCodeMap', 385);
function InternalHashCodeMap$1(this$0){
  this.this$01 = this$0;
  this.chains = this.this$01.backingMap.entries();
  this.chain = initUnidimensionalArray(Ljava_lang_Object_2_classLit, $intern_9, 1, 0, 5, 1);
}

defineClass(234, 1, {}, InternalHashCodeMap$1);
_.next_1 = function next_16(){
  return this.lastEntry = this.chain[this.itemIndex++] , this.lastEntry;
}
;
_.hasNext_0 = function hasNext_15(){
  var current;
  if (this.itemIndex < this.chain.length) {
    return true;
  }
  current = this.chains.next();
  if (!current.done) {
    this.chain = current.value[1];
    this.itemIndex = 0;
    return true;
  }
  return false;
}
;
_.itemIndex = 0;
_.lastEntry = null;
var Ljava_util_InternalHashCodeMap$1_2_classLit = createForClass('java.util', 'InternalHashCodeMap/1', 234);
function $delete(this$static, key){
  var fn;
  fn = this$static['delete'];
  fn.call(this$static, key);
}

function $delete_0(this$static, key){
  var fn;
  fn = this$static['delete'];
  fn.call(this$static, key);
}

function $clinit_InternalJsMapFactory(){
  $clinit_InternalJsMapFactory = emptyMethod;
  jsMapCtor = getJsMapConstructor();
}

function canHandleObjectCreateAndProto(){
  if (!Object.create || !Object.getOwnPropertyNames) {
    return false;
  }
  var protoField = '__proto__';
  var map_0 = Object.create(null);
  if (map_0[protoField] !== undefined) {
    return false;
  }
  var keys_0 = Object.getOwnPropertyNames(map_0);
  if (keys_0.length != 0) {
    return false;
  }
  map_0[protoField] = 42;
  if (map_0[protoField] !== 42) {
    return false;
  }
  if (Object.getOwnPropertyNames(map_0).length == 0) {
    return false;
  }
  return true;
}

function getJsMapConstructor(){
  function isCorrectIterationProtocol(){
    try {
      return (new Map).entries().next().done;
    }
     catch (e) {
      return false;
    }
  }

  if (typeof Map === 'function' && Map.prototype.entries && isCorrectIterationProtocol()) {
    return Map;
  }
   else {
    return getJsMapPolyFill();
  }
}

function getJsMapPolyFill(){
  function Stringmap(){
    this.obj = this.createObject();
  }

  ;
  Stringmap.prototype.createObject = function(key){
    return Object.create(null);
  }
  ;
  Stringmap.prototype.get = function(key){
    return this.obj[key];
  }
  ;
  Stringmap.prototype.set = function(key, value_0){
    this.obj[key] = value_0;
  }
  ;
  Stringmap.prototype['delete'] = function(key){
    delete this.obj[key];
  }
  ;
  Stringmap.prototype.keys = function(){
    return Object.getOwnPropertyNames(this.obj);
  }
  ;
  Stringmap.prototype.entries = function(){
    var keys_0 = this.keys();
    var map_0 = this;
    var nextIndex = 0;
    return {next:function(){
      if (nextIndex >= keys_0.length)
        return {done:true};
      var key = keys_0[nextIndex++];
      return {value:[key, map_0.get(key)], done:false};
    }
    };
  }
  ;
  if (!canHandleObjectCreateAndProto()) {
    Stringmap.prototype.createObject = function(){
      return {};
    }
    ;
    Stringmap.prototype.get = function(key){
      return this.obj[':' + key];
    }
    ;
    Stringmap.prototype.set = function(key, value_0){
      this.obj[':' + key] = value_0;
    }
    ;
    Stringmap.prototype['delete'] = function(key){
      delete this.obj[':' + key];
    }
    ;
    Stringmap.prototype.keys = function(){
      var result = [];
      for (var key in this.obj) {
        key.charCodeAt(0) == 58 && result.push(key.substring(1));
      }
      return result;
    }
    ;
  }
  return Stringmap;
}

function newJsMap(){
  $clinit_InternalJsMapFactory();
  return new jsMapCtor;
}

var jsMapCtor;
function $contains_5(this$static, key){
  return !(this$static.backingMap.get(key) === undefined);
}

function $get_12(this$static, key){
  return this$static.backingMap.get(key);
}

function $put_7(this$static, key, value_0){
  var oldValue;
  oldValue = this$static.backingMap.get(key);
  this$static.backingMap.set(key, value_0 === undefined?null:value_0);
  if (oldValue === undefined) {
    ++this$static.size_0;
    ++this$static.host.modCount;
  }
   else {
    ++this$static.valueMod;
  }
  return oldValue;
}

function $remove_10(this$static, key){
  var value_0;
  value_0 = this$static.backingMap.get(key);
  if (value_0 === undefined) {
    ++this$static.valueMod;
  }
   else {
    $delete_0(this$static.backingMap, key);
    --this$static.size_0;
    ++this$static.host.modCount;
  }
  return value_0;
}

function InternalStringMap(host){
  this.backingMap = newJsMap();
  this.host = host;
}

defineClass(386, 1, {}, InternalStringMap);
_.iterator = function iterator_27(){
  return new InternalStringMap$1(this);
}
;
_.size_0 = 0;
_.valueMod = 0;
var Ljava_util_InternalStringMap_2_classLit = createForClass('java.util', 'InternalStringMap', 386);
function InternalStringMap$1(this$0){
  this.this$01 = this$0;
  this.entries_0 = this.this$01.backingMap.entries();
  this.current = this.entries_0.next();
}

defineClass(235, 1, {}, InternalStringMap$1);
_.next_1 = function next_17(){
  return this.last = this.current , this.current = this.entries_0.next() , new InternalStringMap$2(this.this$01, this.last, this.this$01.valueMod);
}
;
_.hasNext_0 = function hasNext_16(){
  return !this.current.done;
}
;
var Ljava_util_InternalStringMap$1_2_classLit = createForClass('java.util', 'InternalStringMap/1', 235);
function InternalStringMap$2(this$0, val$entry, val$lastValueMod){
  this.this$01 = this$0;
  this.val$entry2 = val$entry;
  this.val$lastValueMod3 = val$lastValueMod;
}

defineClass(387, 539, $intern_50, InternalStringMap$2);
_.getKey = function getKey_1(){
  return this.val$entry2.value[0];
}
;
_.getValue = function getValue_1(){
  if (this.this$01.valueMod != this.val$lastValueMod3) {
    return $get_12(this.this$01, this.val$entry2.value[0]);
  }
  return this.val$entry2.value[1];
}
;
_.setValue = function setValue_1(object){
  return $put_7(this.this$01, this.val$entry2.value[0], object);
}
;
_.val$lastValueMod3 = 0;
var Ljava_util_InternalStringMap$2_2_classLit = createForClass('java.util', 'InternalStringMap/2', 387);
function $add_11(this$static, o){
  $addNode(this$static, o, this$static.tail.prev, this$static.tail);
  return true;
}

function $addNode(this$static, o, prev, next){
  var node;
  node = new LinkedList$Node;
  node.value_0 = o;
  node.prev = prev;
  node.next_0 = next;
  next.prev = prev.next_0 = node;
  ++this$static.size_0;
}

function $listIterator(this$static, index_0){
  var i, node;
  checkCriticalPositionIndex(index_0, this$static.size_0);
  if (index_0 >= this$static.size_0 >> 1) {
    node = this$static.tail;
    for (i = this$static.size_0; i > index_0; --i) {
      node = node.prev;
    }
  }
   else {
    node = this$static.header.next_0;
    for (i = 0; i < index_0; ++i) {
      node = node.next_0;
    }
  }
  return new LinkedList$ListIteratorImpl(this$static, index_0, node);
}

function $removeNode(this$static, node){
  var oldValue;
  oldValue = node.value_0;
  node.next_0.prev = node.prev;
  node.prev.next_0 = node.next_0;
  node.next_0 = node.prev = null;
  node.value_0 = null;
  --this$static.size_0;
  return oldValue;
}

function $reset_5(this$static){
  this$static.header.next_0 = this$static.tail;
  this$static.tail.prev = this$static.header;
  this$static.header.prev = this$static.tail.next_0 = null;
  this$static.size_0 = 0;
}

function LinkedList(){
  this.header = new LinkedList$Node;
  this.tail = new LinkedList$Node;
  $reset_5(this);
}

defineClass(96, 540, $intern_51, LinkedList);
_.add_0 = function add_12(o){
  return $add_11(this, o);
}
;
_.listIterator_0 = function listIterator_4(index_0){
  return $listIterator(this, index_0);
}
;
_.size_1 = function size_13(){
  return this.size_0;
}
;
_.size_0 = 0;
var Ljava_util_LinkedList_2_classLit = createForClass('java.util', 'LinkedList', 96);
function $next_9(this$static){
  checkCriticalElement(this$static.currentNode != this$static.this$01.tail);
  this$static.lastNode = this$static.currentNode;
  this$static.currentNode = this$static.currentNode.next_0;
  ++this$static.currentIndex;
  return this$static.lastNode.value_0;
}

function LinkedList$ListIteratorImpl(this$0, index_0, startNode){
  this.this$01 = this$0;
  this.currentNode = startNode;
  this.currentIndex = index_0;
}

defineClass(393, 1, {}, LinkedList$ListIteratorImpl);
_.hasNext_0 = function hasNext_17(){
  return this.currentNode != this.this$01.tail;
}
;
_.hasPrevious = function hasPrevious_2(){
  return this.currentNode.prev != this.this$01.header;
}
;
_.next_1 = function next_18(){
  return $next_9(this);
}
;
_.previous = function previous_3(){
  checkCriticalElement(this.currentNode.prev != this.this$01.header);
  this.lastNode = this.currentNode = this.currentNode.prev;
  --this.currentIndex;
  return this.lastNode.value_0;
}
;
_.currentIndex = 0;
_.lastNode = null;
var Ljava_util_LinkedList$ListIteratorImpl_2_classLit = createForClass('java.util', 'LinkedList/ListIteratorImpl', 393);
function LinkedList$Node(){
}

defineClass(176, 1, {}, LinkedList$Node);
var Ljava_util_LinkedList$Node_2_classLit = createForClass('java.util', 'LinkedList/Node', 176);
function $clinit_Locale(){
  $clinit_Locale = emptyMethod;
  new Locale('en', 'CA');
  new Locale('fr', 'CA');
  new Locale('zh', 'CN');
  new Locale('zh', '');
  new Locale('en', '');
  new Locale('fr', 'FR');
  new Locale('fr', '');
  new Locale('de', '');
  new Locale('de', 'DE');
  new Locale('it', '');
  new Locale('it', 'IT');
  new Locale('ja', 'JP');
  new Locale('ja', '');
  new Locale('ko', 'KR');
  new Locale('ko', '');
  new Locale('zh', 'CN');
  ROOT_0 = new Locale('', '');
  new Locale('zh', 'CN');
  new Locale('zh', 'TW');
  new Locale('zh', 'TW');
  new Locale('en', 'GB');
  US = new Locale('en', 'US');
  defaultLocale = initDefault();
}

function Locale(lowerCaseLanguageCode, upperCaseCountryCode){
  this.languageCode = lowerCaseLanguageCode;
  this.countryCode = upperCaseCountryCode;
  this.variantCode = '';
}

function initDefault(){
  var browserLanguage, defaultLoc, locale;
  defaultLoc = US;
  browserLanguage = $wnd.navigator.languages?$wnd.navigator.languages[0]:$wnd.navigator.userLanguage || $wnd.navigator.language;
  if (browserLanguage != null && browserLanguage.length > 0) {
    locale = $split(browserLanguage, '-');
    defaultLoc = new Locale(locale[0].toLowerCase(), locale.length > 1?locale[1].toUpperCase():'');
  }
  return defaultLoc;
}

function toNewString(languageCode, countryCode, variantCode){
  var result;
  if (languageCode.length == 0 && countryCode.length == 0) {
    return '';
  }
  result = new StringBuilder_0;
  result.string += '' + languageCode;
  (countryCode.length > 0 || variantCode.length > 0) && (result.string += '_' , result);
  result.string += '' + countryCode;
  variantCode.length > 0 && (result.string += '_' , result);
  result.string += '' + variantCode;
  return result.string;
}

defineClass(31, 1, {3:1, 31:1}, Locale);
_.equals_0 = function equals_34(object){
  var o;
  if (object === this) {
    return true;
  }
  if (instanceOf(object, 31)) {
    o = castTo(object, 31);
    return $equals_5(this.languageCode, o.languageCode) && $equals_5(this.countryCode, o.countryCode) && $equals_5(this.variantCode, o.variantCode);
  }
  return false;
}
;
_.hashCode_0 = function hashCode_37(){
  return $hashCode_2(this.countryCode) + $hashCode_2(this.languageCode) + $hashCode_2(this.variantCode);
}
;
_.toString_0 = function toString_52(){
  var result;
  result = this.cachedToStringResult;
  result == null && (result = this.cachedToStringResult = toNewString(this.languageCode, this.countryCode, this.variantCode));
  return result;
}
;
var ROOT_0, US, defaultLocale;
var Ljava_util_Locale_2_classLit = createForClass('java.util', 'Locale', 31);
function NoSuchElementException(){
  RuntimeException.call(this);
}

function NoSuchElementException_0(s){
  RuntimeException_0.call(this, s);
}

defineClass(57, 27, {3:1, 6:1, 9:1, 57:1}, NoSuchElementException, NoSuchElementException_0);
var Ljava_util_NoSuchElementException_2_classLit = createForClass('java.util', 'NoSuchElementException', 57);
function equals_35(a, b){
  return maskUndefined(a) === maskUndefined(b) || a != null && equals_Ljava_lang_Object__Z__devirtual$(a, b);
}

function hashCode_38(o){
  return o != null?hashCode__I__devirtual$(o):0;
}

function $add_12(this$static, newElement){
  !this$static.builder?(this$static.builder = new StringBuilder_1(this$static.prefix)):$append_3(this$static.builder, this$static.delimiter);
  $append_1(this$static.builder, newElement);
  return this$static;
}

function StringJoiner(prefix, suffix){
  this.delimiter = ', ';
  this.prefix = prefix;
  this.suffix = suffix;
  this.emptyValue = this.prefix + ('' + this.suffix);
}

defineClass(221, 1, {}, StringJoiner);
_.toString_0 = function toString_53(){
  return !this.builder?this.emptyValue:this.suffix.length == 0?this.builder.string:this.builder.string + ('' + this.suffix);
}
;
var Ljava_util_StringJoiner_2_classLit = createForClass('java.util', 'StringJoiner', 221);
function $hasMoreTokens(this$static){
  var i, length_0;
  if (this$static.delimiters == null) {
    throw toJs(new NullPointerException_0('delimiters == null'));
  }
  length_0 = this$static.string.length;
  if (this$static.position < length_0) {
    if (this$static.returnDelimiters)
      return true;
    for (i = this$static.position; i < length_0; i++)
      if ($indexOf_1(this$static.delimiters, fromCodePoint($charAt(this$static.string, i))) == -1)
        return true;
  }
  return false;
}

function $nextToken(this$static){
  var i, length_0;
  if (this$static.delimiters == null) {
    throw toJs(new NullPointerException_0('delimiters == null'));
  }
  i = this$static.position;
  length_0 = this$static.string.length;
  if (i < length_0) {
    if (this$static.returnDelimiters) {
      if ($indexOf_1(this$static.delimiters, fromCodePoint($charAt(this$static.string, this$static.position))) >= 0)
        return String.fromCharCode($charAt(this$static.string, this$static.position++));
      for (++this$static.position; this$static.position < length_0; this$static.position++)
        if ($indexOf_1(this$static.delimiters, fromCodePoint($charAt(this$static.string, this$static.position))) >= 0)
          return $substring_0(this$static.string, i, this$static.position);
      return $substring(this$static.string, i);
    }
    while (i < length_0 && $indexOf_1(this$static.delimiters, fromCodePoint($charAt(this$static.string, i))) >= 0)
      ++i;
    this$static.position = i;
    if (i < length_0) {
      for (++this$static.position; this$static.position < length_0; this$static.position++)
        if ($indexOf_1(this$static.delimiters, fromCodePoint($charAt(this$static.string, this$static.position))) >= 0)
          return $substring_0(this$static.string, i, this$static.position);
      return $substring(this$static.string, i);
    }
  }
  throw toJs(new NoSuchElementException);
}

function StringTokenizer(string){
  StringTokenizer_0.call(this, string);
}

function StringTokenizer_0(string){
  if (string == null) {
    throw toJs(new NullPointerException_0('string == null'));
  }
  this.string = string;
  this.delimiters = '\r\n';
  this.returnDelimiters = false;
  this.position = 0;
}

defineClass(152, 1, {}, StringTokenizer);
_.position = 0;
_.returnDelimiters = false;
var Ljava_util_StringTokenizer_2_classLit = createForClass('java.util', 'StringTokenizer', 152);
function AtomicInteger(){
  this.value_0 = 0;
}

defineClass(398, 23, {3:1, 23:1}, AtomicInteger);
_.floatValue = function floatValue_1(){
  return this.value_0;
}
;
_.intValue = function intValue_1(){
  return this.value_0;
}
;
_.toString_0 = function toString_54(){
  return '' + this.value_0;
}
;
_.value_0 = 0;
var Ljava_util_concurrent_atomic_AtomicInteger_2_classLit = createForClass('java.util.concurrent.atomic', 'AtomicInteger', 398);
function $set_8(this$static, newValue){
  this$static.value_0 = newValue;
}

function AtomicReference(initialValue){
  this.value_0 = initialValue;
}

defineClass(449, 1, {}, AtomicReference);
_.toString_0 = function toString_55(){
  return valueOf_2(this.value_0);
}
;
var Ljava_util_concurrent_atomic_AtomicReference_2_classLit = createForClass('java.util.concurrent.atomic', 'AtomicReference', 449);
function $find(this$static){
  return $test(this$static.regExp, this$static.input_0);
}

function $matches(this$static){
  return $test(this$static.regExp, this$static.input_0);
}

function Matcher(pattern, input_0){
  this.regExp = pattern.regExp;
  this.input_0 = input_0 == null?'null':input_0;
  this.matchResult = $exec(this.regExp, this.input_0);
}

defineClass(19, 1, {}, Matcher);
var Ljava_util_regex_Matcher_2_classLit = createForClass('java.util.regex', 'Matcher', 19);
function Pattern(regExp){
  this.regExp = new RegExp(regExp);
}

defineClass(30, 1, {}, Pattern);
var Ljava_util_regex_Pattern_2_classLit = createForClass('java.util.regex', 'Pattern', 30);
function copy_1(src_0, srcOfs, dest, destOfs, len){
  var batchEnd, batchStart, destArray, end, spliceArgs;
  if (len == 0) {
    return;
  }
  if (maskUndefined(src_0) === maskUndefined(dest)) {
    src_0 = src_0.slice(srcOfs, srcOfs + len);
    srcOfs = 0;
  }
  destArray = dest;
  for (batchStart = srcOfs , end = srcOfs + len; batchStart < end;) {
    batchEnd = $wnd.Math.min(batchStart + 10000, end);
    len = batchEnd - batchStart;
    spliceArgs = src_0.slice(batchStart, batchEnd);
    spliceArgs.splice(0, 0, destOfs, len);
    Array.prototype.splice.apply(destArray, spliceArgs);
    batchStart = batchEnd;
    destOfs += len;
  }
}

function createFrom(array, length_0){
  return stampJavaTypeInfo_1(new Array(length_0), array);
}

function insertTo(array, index_0, value_0){
  array.splice(index_0, 0, value_0);
}

function push_1(array, o){
  array.push(o);
}

function removeFrom(array, index_0){
  array.splice(index_0, 1);
}

function setAt(array, index_0, value_0){
  var originalValue;
  originalValue = array[index_0];
  array[index_0] = value_0;
  return originalValue;
}

defineClass(610, 1, {});
function stampJavaTypeInfo_1(array, referenceType){
  return stampJavaTypeInfo_0(array, referenceType);
}

function $clinit_EmulatedCharset(){
  $clinit_EmulatedCharset = emptyMethod;
  new EmulatedCharset$UtfCharset;
  new EmulatedCharset$LatinCharset('ISO-LATIN-1');
  new EmulatedCharset$LatinCharset('ISO-8859-1');
}

function EmulatedCharset(name_0){
  this.name_0 = name_0;
}

defineClass(222, 88, $intern_47);
var Ljavaemul_internal_EmulatedCharset_2_classLit = createForClass('javaemul.internal', 'EmulatedCharset', 222);
function EmulatedCharset$LatinCharset(name_0){
  EmulatedCharset.call(this, name_0);
}

defineClass(223, 222, $intern_47, EmulatedCharset$LatinCharset);
var Ljavaemul_internal_EmulatedCharset$LatinCharset_2_classLit = createForClass('javaemul.internal', 'EmulatedCharset/LatinCharset', 223);
function $decodeString(bytes, len){
  var b, ch_0, charCount, chars, count, i, i0, number, outIdx;
  charCount = 0;
  for (i0 = 0; i0 < len;) {
    ++charCount;
    ch_0 = bytes[i0];
    if ((ch_0 & 192) == 128) {
      throw toJs(new IllegalArgumentException_0('Invalid UTF8 sequence'));
    }
     else if ((ch_0 & 128) == 0) {
      ++i0;
    }
     else if ((ch_0 & 224) == 192) {
      i0 += 2;
    }
     else if ((ch_0 & 240) == 224) {
      i0 += 3;
    }
     else if ((ch_0 & 248) == 240) {
      i0 += 4;
    }
     else {
      throw toJs(new IllegalArgumentException_0('Invalid UTF8 sequence'));
    }
    if (i0 > len) {
      throw toJs(new IndexOutOfBoundsException_0('Invalid UTF8 sequence'));
    }
  }
  chars = initUnidimensionalArray(C_classLit, $intern_2, 5, charCount, 15, 1);
  outIdx = 0;
  count = 0;
  for (i = 0; i < len;) {
    ch_0 = bytes[i++];
    if ((ch_0 & 128) == 0) {
      count = 1;
      ch_0 &= 127;
    }
     else if ((ch_0 & 224) == 192) {
      count = 2;
      ch_0 &= 31;
    }
     else if ((ch_0 & 240) == 224) {
      count = 3;
      ch_0 &= 15;
    }
     else if ((ch_0 & 248) == 240) {
      count = 4;
      ch_0 &= 7;
    }
     else if ((ch_0 & 252) == 248) {
      count = 5;
      ch_0 &= 3;
    }
    while (--count > 0) {
      b = bytes[i++];
      if ((b & 192) != 128) {
        throw toJs(new IllegalArgumentException_0('Invalid UTF8 sequence at ' + (i - 1) + ', byte=' + (number = b >>> 0 , number.toString(16))));
      }
      ch_0 = ch_0 << 6 | b & 63;
    }
    outIdx += toChars(ch_0, chars, outIdx);
  }
  return chars;
}

function $encodeUtf8(bytes, codePoint){
  if (codePoint < 128) {
    bytes.push((codePoint & 127) << 24 >> 24);
  }
   else if (codePoint < 2048) {
    bytes.push((codePoint >> 6 & 31 | 192) << 24 >> 24);
    bytes.push((codePoint & 63 | 128) << 24 >> 24);
  }
   else if (codePoint < $intern_35) {
    bytes.push((codePoint >> 12 & 15 | 224) << 24 >> 24);
    bytes.push((codePoint >> 6 & 63 | 128) << 24 >> 24);
    bytes.push((codePoint & 63 | 128) << 24 >> 24);
  }
   else if (codePoint < $intern_37) {
    bytes.push((codePoint >> 18 & 7 | 240) << 24 >> 24);
    bytes.push((codePoint >> 12 & 63 | 128) << 24 >> 24);
    bytes.push((codePoint >> 6 & 63 | 128) << 24 >> 24);
    bytes.push((codePoint & 63 | 128) << 24 >> 24);
  }
   else if (codePoint < $intern_39) {
    bytes.push((codePoint >> 24 & 3 | 248) << 24 >> 24);
    bytes.push((codePoint >> 18 & 63 | 128) << 24 >> 24);
    bytes.push((codePoint >> 12 & 63 | 128) << 24 >> 24);
    bytes.push((codePoint >> 6 & 63 | 128) << 24 >> 24);
    bytes.push((codePoint & 63 | 128) << 24 >> 24);
  }
   else {
    throw toJs(new IllegalArgumentException_0('Character out of range: ' + codePoint));
  }
}

function $getBytes(str){
  var bytes, ch_0, i, n;
  n = str.length;
  bytes = initUnidimensionalArray(B_classLit, $intern_6, 5, 0, 15, 1);
  for (i = 0; i < n;) {
    ch_0 = codePointAt(str, i, str.length);
    i += ch_0 >= $intern_35?2:1;
    $encodeUtf8(bytes, ch_0);
  }
  return bytes;
}

function EmulatedCharset$UtfCharset(){
  EmulatedCharset.call(this, 'UTF-8');
}

defineClass(310, 222, $intern_47, EmulatedCharset$UtfCharset);
var Ljavaemul_internal_EmulatedCharset$UtfCharset_2_classLit = createForClass('javaemul.internal', 'EmulatedCharset/UtfCharset', 310);
function getObjectIdentityHashCode(o){
  return o.$H || (o.$H = ++nextHash);
}

var nextHash = 0;
function checkCriticalArgument(expression){
  if (!expression) {
    throw toJs(new IllegalArgumentException);
  }
}

function checkCriticalArgument_0(expression, errorMessage){
  if (!expression) {
    throw toJs(new IllegalArgumentException_0(errorMessage));
  }
}

function checkCriticalArrayBounds(start_0, end, length_0){
  if (start_0 > end) {
    throw toJs(new IllegalArgumentException_0('fromIndex: ' + start_0 + ' > toIndex: ' + end));
  }
  if (start_0 < 0 || end > length_0) {
    throw toJs(new ArrayIndexOutOfBoundsException('fromIndex: ' + start_0 + ', toIndex: ' + end + ', length: ' + length_0));
  }
}

function checkCriticalArraySize(size_0){
  if (size_0 < 0) {
    throw toJs(new NegativeArraySizeException('Negative array size: ' + size_0));
  }
}

function checkCriticalArrayType(expression){
  if (!expression) {
    throw toJs(new ArrayStoreException);
  }
}

function checkCriticalArrayType_0(expression, errorMessage){
  if (!expression) {
    throw toJs(new ArrayStoreException_0(errorMessage));
  }
}

function checkCriticalConcurrentModification(currentModCount, recordedModCount){
  if (currentModCount != recordedModCount) {
    throw toJs(new ConcurrentModificationException);
  }
}

function checkCriticalElement(expression){
  if (!expression) {
    throw toJs(new NoSuchElementException);
  }
}

function checkCriticalElementIndex(index_0, size_0){
  if (index_0 < 0 || index_0 >= size_0) {
    throw toJs(new IndexOutOfBoundsException_0('Index: ' + index_0 + ', Size: ' + size_0));
  }
}

function checkCriticalNotNull(reference){
  if (reference == null) {
    throw toJs(new NullPointerException);
  }
  return reference;
}

function checkCriticalNotNull_0(reference, errorMessage){
  if (reference == null) {
    throw toJs(new NullPointerException_0(errorMessage));
  }
}

function checkCriticalPositionIndex(index_0, size_0){
  if (index_0 < 0 || index_0 > size_0) {
    throw toJs(new IndexOutOfBoundsException_0('Index: ' + index_0 + ', Size: ' + size_0));
  }
}

function checkCriticalState(expression){
  if (!expression) {
    throw toJs(new IllegalStateException);
  }
}

function checkCriticalStringBounds(start_0, end, length_0){
  if (start_0 < 0 || end > length_0 || end < start_0) {
    throw toJs(new StringIndexOutOfBoundsException('fromIndex: ' + start_0 + ', toIndex: ' + end + ', length: ' + length_0));
  }
}

function checkCriticalStringElementIndex(index_0, size_0){
  if (index_0 < 0 || index_0 >= size_0) {
    throw toJs(new StringIndexOutOfBoundsException('Index: ' + index_0 + ', Size: ' + size_0));
  }
}

function checkCriticalType(expression){
  if (!expression) {
    throw toJs(new ClassCastException);
  }
}

defineClass(602, 1, {});
function ResourceBundleHelper$MugenBundle(){
  this.resourcePrefix = 'data/';
}

defineClass(440, 1, {}, ResourceBundleHelper$MugenBundle);
var Lorg_lee_mugen_ResourceBundleHelper$MugenBundle_2_classLit = createForClass('org.lee.mugen', 'ResourceBundleHelper/MugenBundle', 440);
function $getAnimManager(this$static){
  if (!this$static.anim) {
    this$static.anim = new AbstractAnimManager(new HashMap);
    $setAction_0(this$static.anim, this$static.actionno);
  }
  return this$static.anim;
}

function $process(this$static){
  var x_0, y_0;
  if (!this$static.enable_0)
    return;
  x_0 = this$static.velocity.x_0;
  $addX(this$static.pos, x_0);
  y_0 = this$static.velocity.y_0;
  $addY(this$static.pos, y_0);
  this$static.type_0 == ($clinit_BG$Type() , ANIM) && $process_6($getAnimManager(this$static));
  ++this$static.time;
}

function $setActionno(this$static, actionno){
  this$static.actionno = actionno;
}

function $setDelta(this$static, delta){
  this$static.delta = delta;
}

function $setLayerno(this$static, layerno){
  this$static.layerno = layerno;
}

function $setSpriteno(this$static, spriteno){
  this$static.spriteno = spriteno;
}

function $setStart(this$static, start_0){
  this$static.start_0 = start_0;
}

function $setTile(this$static, tile){
  this$static.tile = tile;
}

function $setTrans(this$static, trans){
  this$static.trans = valueOf_6(trans.toUpperCase());
}

function $setType(this$static, type_0){
  this$static.type_0 = valueOf_5(type_0.toUpperCase());
}

function $setVelocity(this$static, velocity){
  this$static.velocity = velocity;
}

function $setYscalestart(this$static, yscalestart){
  this$static.yscalestart = yscalestart;
}

function BG(){
  this.spriteno = new SprGrpNum;
  this.xscale = new PointF_0(1, 1);
  this.trans = ($clinit_Trans() , NONE_1);
  this.type_0 = ($clinit_BG$Type() , NORMAL);
  this.start_0 = new PointF;
  this.tile = new PointF;
  this.velocity = new PointF;
  this.delta = new PointF_0(1, 1);
  this.pos = new PointF;
}

defineClass(91, 1, {3:1, 91:1}, BG);
_.actionno = 0;
_.enable_0 = true;
_.isInit = false;
_.layerno = 0;
_.time = 0;
_.visible = true;
_.yscalestart = 0;
var Lorg_lee_mugen_background_BG_2_classLit = createForClass('org.lee.mugen.background', 'BG', 91);
function $clinit_BG$Type(){
  $clinit_BG$Type = emptyMethod;
  ANIM = new BG$Type('ANIM', 0);
  NORM = new BG$Type('NORM', 1);
  NORMAL = new BG$Type('NORMAL', 2);
  PARALLAX = new BG$Type('PARALLAX', 3);
}

function BG$Type(enum$name, enum$ordinal){
  Enum.call(this, enum$name, enum$ordinal);
}

function valueOf_5(name_0){
  $clinit_BG$Type();
  return valueOf(($clinit_BG$Type$Map() , $MAP), name_0);
}

function values_13(){
  $clinit_BG$Type();
  return stampJavaTypeInfo(getClassLiteralForArray(Lorg_lee_mugen_background_BG$Type_2_classLit, 1), $intern_1, 101, 0, [ANIM, NORM, NORMAL, PARALLAX]);
}

defineClass(101, 8, {3:1, 7:1, 8:1, 101:1}, BG$Type);
var ANIM, NORM, NORMAL, PARALLAX;
var Lorg_lee_mugen_background_BG$Type_2_classLit = createForEnum('org.lee.mugen.background', 'BG/Type', 101, values_13);
function $clinit_BG$Type$Map(){
  $clinit_BG$Type$Map = emptyMethod;
  $MAP = createValueOfMap(($clinit_BG$Type() , stampJavaTypeInfo(getClassLiteralForArray(Lorg_lee_mugen_background_BG$Type_2_classLit, 1), $intern_1, 101, 0, [ANIM, NORM, NORMAL, PARALLAX])));
}

var $MAP;
function $getSpr(this$static){
  var e, path, sffreader;
  if (!this$static.spr && this$static.sprValue != null) {
    try {
      path = $resolveSprPath(this$static);
      sffreader = new SffReader_0(path);
      this$static.spr = new SpriteSFF(sffreader, this$static.sprForceImage);
    }
     catch ($e0) {
      $e0 = toJava($e0);
      if (instanceOf($e0, 6)) {
        e = $e0;
        $printStackTraceImpl(e, ($clinit_System() , err_0), '');
        throw toJs(new IllegalStateException_1(e));
      }
       else 
        throw toJs($e0);
    }
  }
  return this$static.spr;
}

function $parse_0(this$static, root, name_0, value_0){
  this$static.root = root;
  $indexOf_0(value_0, fromCodePoint(92)) != -1 && (value_0 = $replaceAll(value_0, '\\\\', '/'));
  if ($equals_5(name_0, 'spr')) {
    this$static.sprValue = value_0;
    this$static.sprForceImage = false;
  }
   else 
    $equals_5(name_0, 'debugbg')?__parseAndValidateInt(value_0, 10):$equals_5(name_0, 'bgclearcolor') && (this$static.bgclearcolor = castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, Lorg_lee_mugen_renderer_RGB_2_classLit), 13)).convert(value_0), 65));
}

function $resolveSprPath(this$static){
  var candidate, dir_0, file, path, path0, rel_0, suffixlength;
  rel_0 = $replace(this$static.sprValue);
  dir_0 = $replace($getPath(this$static.root.currentDir));
  dir_0.length > 0 && (suffixlength = '/'.length , !$equals_5(dir_0.substr(dir_0.length - suffixlength, suffixlength), '/')) && (dir_0 = dir_0 + '/');
  candidate = dir_0 + ('' + rel_0);
  try {
    return getExistFile_0(candidate);
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 24)) {
      file = new File_0(this$static.root.currentDir, this$static.sprValue);
      $getItem(($clinit_File() , LocalStorage), (path0 = $getPath($getAbsoluteFile($getCanonicalFile(file))) , path0.length == 0?'/':path0)) != null || (file = new File_0(this$static.root.currentDir.parent_0, this$static.sprValue));
      return path = $getPath($getAbsoluteFile(file)) , path.length == 0?'/':path;
    }
     else 
      throw toJs($e0);
  }
}

function $setBgclearcolor(this$static, bgclearcolor){
  this$static.bgclearcolor = bgclearcolor;
}

function BGdef(){
  this.bgclearcolor = new RGB;
}

defineClass(244, 1, $intern_2, BGdef);
_.sprForceImage = false;
var Lorg_lee_mugen_background_BGdef_2_classLit = createForClass('org.lee.mugen.background', 'BGdef', 244);
function $initTitleParseResult(this$static, bgdef, bgs){
  this$static.bgdef = bgdef;
  this$static.bgs = bgs;
}

function $process_0(this$static){
  var bg, bg$iterator;
  for (bg$iterator = new ArrayList$1(this$static.bgs); bg$iterator.i < bg$iterator.this$01.array.length;) {
    bg = castTo($next_7(bg$iterator), 91);
    if (!bg.isInit) {
      bg.pos = new PointF_1(bg.start_0);
      $setAction_0($getAnimManager(bg), bg.actionno);
      bg.isInit = true;
    }
    $process(bg);
  }
}

defineClass(436, 1, $intern_2);
var Lorg_lee_mugen_background_Background_2_classLit = createForClass('org.lee.mugen.background', 'Background', 436);
function eval_0(key, value_0){
  var i, k, nums, out, vals;
  if (value_0 == null || $trim(value_0).length == 0) {
    return stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_Object_2_classLit, 1), $intern_9, 1, 5, [null]);
  }
  k = key.toLowerCase();
  if ($equals_5('type', k) || $equals_5('trans', k) || $equals_5('spr', k)) {
    return stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_Object_2_classLit, 1), $intern_9, 1, 5, [$trim(value_0)]);
  }
  if ($equals_5('spriteno', k)) {
    nums = parseNumberList(value_0);
    if (nums.length >= 2) {
      return stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_Object_2_classLit, 1), $intern_9, 1, 5, [new SprGrpNum_0(round_int(nums[0]), round_int(nums[1]))]);
    }
    if (nums.length == 1) {
      return stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_Object_2_classLit, 1), $intern_9, 1, 5, [new SprGrpNum_0(round_int(nums[0]), 0)]);
    }
    return stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_Object_2_classLit, 1), $intern_9, 1, 5, [$trim(value_0)]);
  }
  vals = evalValueables(value_0);
  out = initUnidimensionalArray(Ljava_lang_Object_2_classLit, $intern_9, 1, vals.length, 5, 1);
  for (i = 0; i < vals.length; i++) {
    out[i] = vals[i].getValue_0(null, stampJavaTypeInfo(getClassLiteralForArray(Lorg_lee_mugen_parser_type_Valueable_2_classLit, 1), $intern_52, 211, 0, []));
  }
  return out;
}

function evalValueables(value_0){
  var i, out, t, tokens;
  tokens = tokenize($trim(value_0));
  out = new ArrayList;
  for (i = 0; i < tokens.length; i++) {
    t = tokens[i];
    if ($equals_5(',', t)) {
      continue;
    }
    $equals_5('-', t) && i + 1 < tokens.length && isNumberToken(tokens[i + 1])?$add_8(out, new FloatValueable(-parseFloat_0(tokens[++i]))):$equals_5('+', t) && i + 1 < tokens.length && isNumberToken(tokens[i + 1])?$add_8(out, new FloatValueable(parseFloat_0(tokens[++i]))):isNumberToken(t)?$add_8(out, new FloatValueable(parseFloat_0(t))):$add_8(out, new StringValueable(t));
  }
  out.array.length == 0 && $add_8(out, ($clinit_IntValueable() , Zero));
  return castTo($toArray_0(out, initUnidimensionalArray(Lorg_lee_mugen_parser_type_Valueable_2_classLit, $intern_52, 211, out.array.length, 0, 1)), 511);
}

function isNumberToken(t){
  var c0;
  if (t == null || t.length == 0) {
    return false;
  }
  c0 = (checkCriticalStringElementIndex(0, t.length) , t.charCodeAt(0));
  return digitRegex == null && (digitRegex = new RegExp('\\d')) , digitRegex.test(String.fromCharCode(c0)) || c0 == 46 || t.length > 1 && isDigit((checkCriticalStringElementIndex(1, t.length) , t.charCodeAt(1)));
}

function parseNumberList(value_0){
  var arr, i, i0, nums, tokens;
  tokens = tokenize($trim(value_0));
  nums = new ArrayList;
  for (i0 = 0; i0 < tokens.length; i0++) {
    if ($equals_5(',', tokens[i0])) {
      continue;
    }
    $equals_5('-', tokens[i0]) && i0 + 1 < tokens.length && isNumberToken(tokens[i0 + 1])?$add_8(nums, new Float(-parseFloat_0(tokens[++i0]))):isNumberToken(tokens[i0]) && $add_8(nums, new Float(parseFloat_0(tokens[i0])));
  }
  arr = initUnidimensionalArray(F_classLit, $intern_10, 5, nums.array.length, 15, 1);
  for (i = 0; i < nums.array.length; i++) {
    arr[i] = (checkCriticalElementIndex(i, nums.array.length) , castTo(nums.array[i], 38)).value_0;
  }
  return arr;
}

function tokenize(exp_0){
  var c, d, i, list, n, start_0;
  list = new ArrayList;
  i = 0;
  n = exp_0.length;
  while (i < n) {
    c = (checkCriticalStringElementIndex(i, exp_0.length) , exp_0.charCodeAt(i));
    if (isWhitespace(String.fromCharCode(c))) {
      ++i;
      continue;
    }
    if (c == 44 || c == 40 || c == 41 || c == 91 || c == 93) {
      $add_8(list, String.fromCharCode(c));
      ++i;
      continue;
    }
    if (c == 43 || c == 45) {
      $add_8(list, String.fromCharCode(c));
      ++i;
      continue;
    }
    leterRegex == null && (leterRegex = new RegExp('[A-Z]', 'i'));
    if (leterRegex.test(String.fromCharCode(c)) || c == 95) {
      start_0 = i;
      ++i;
      while (i < n) {
        d = (checkCriticalStringElementIndex(i, exp_0.length) , exp_0.charCodeAt(i));
        isLeterOrDigitRegex == null && (isLeterOrDigitRegex = new RegExp('[A-Z\\d]', 'i'));
        if (isLeterOrDigitRegex.test(String.fromCharCode(d)) || d == 95 || d == 46) {
          ++i;
        }
         else {
          break;
        }
      }
      $add_8(list, (checkCriticalStringBounds(start_0, i, exp_0.length) , exp_0.substr(start_0, i - start_0)));
      continue;
    }
    digitRegex == null && (digitRegex = new RegExp('\\d'));
    if (digitRegex.test(String.fromCharCode(c)) || c == 46) {
      start_0 = i;
      ++i;
      while (i < n) {
        d = (checkCriticalStringElementIndex(i, exp_0.length) , exp_0.charCodeAt(i));
        digitRegex == null && (digitRegex = new RegExp('\\d'));
        if (digitRegex.test(String.fromCharCode(d)) || d == 46) {
          ++i;
        }
         else {
          break;
        }
      }
      $add_8(list, (checkCriticalStringBounds(start_0, i, exp_0.length) , exp_0.substr(start_0, i - start_0)));
      continue;
    }
    ++i;
  }
  return castTo($toArray_0(list, initUnidimensionalArray(Ljava_lang_String_2_classLit, $intern_11, 2, list.array.length, 6, 1)), 61);
}

function $parse_1(this$static, groups){
  var bg, bgdef, bgs, grp, grp$iterator, key, key$iterator, lastArg, s, section, value_0;
  bgs = new ArrayList;
  bgdef = null;
  for (grp$iterator = new ArrayList$1(groups); grp$iterator.i < grp$iterator.this$01.array.length;) {
    grp = castTo($next_7(grp$iterator), 90);
    section = grp.section;
    if (section == null) {
      continue;
    }
    s = section.toLowerCase();
    if ($equals_5(this$static.bgdefSection, s)) {
      bgdef = new BGdef;
      for (key$iterator = $listIterator(grp.keysOrdered, 0); key$iterator.currentNode != key$iterator.this$01.tail;) {
        key = castToString($next_9(key$iterator));
        value_0 = castToString($getStringValue(grp.keyValues, key));
        $equalsIgnoreCase('bgclearcolor', key)?$setBgclearcolor(bgdef, castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, Lorg_lee_mugen_renderer_RGB_2_classLit), 13)).convert(value_0), 65)):$equalsIgnoreCase('debugbg', key)?__parseAndValidateInt($trim(value_0), 10):$equalsIgnoreCase('spr', key) && $parse_0(bgdef, this$static, key, value_0);
      }
    }
     else if ($startsWith(s, this$static.bgSectionPrefix)) {
      $trim($substring(s, this$static.bgSectionPrefix.length));
      bg = new BG;
      push_1(bgs.array, bg);
      fillBgFields(bg, grp);
    }
  }
  !bgdef && (bgdef = new BGdef);
  $initTitleParseResult(this$static, bgdef, (lastArg = bgs , new AbstractAnimManager_0(new AirParser) , lastArg));
}

function GwtBackground(currentDir, prefix){
  this.bgs = new ArrayList;
  new HashMap;
  this.bgCtrlDefMap = new HashMap;
  this.currentDir = currentDir;
  this.bgdefSection = prefix + 'bgdef';
  this.bgSectionPrefix = prefix + 'bg ';
}

function applyOne(bg, k, value_0){
  if (value_0 == null) {
    return;
  }
  $equals_5('type', k) && instanceOfString(value_0)?$setType(bg, castToString(value_0)):$equals_5('trans', k) && instanceOfString(value_0)?$setTrans(bg, castToString(value_0)):$equals_5('start', k) && instanceOf(value_0, 29)?$setStart(bg, castTo(value_0, 29)):$equals_5('tile', k) && instanceOf(value_0, 29)?$setTile(bg, castTo(value_0, 29)):$equals_5('velocity', k) && instanceOf(value_0, 29)?$setVelocity(bg, castTo(value_0, 29)):$equals_5('delta', k) && instanceOf(value_0, 29)?$setDelta(bg, castTo(value_0, 29)):$equals_5('width', k) && instanceOf(value_0, 10)?castTo(value_0, 10):$equals_5('spriteno', k) && instanceOf(value_0, 86)?$setSpriteno(bg, castTo(value_0, 86)):$equals_5('mask', k) && instanceOf(value_0, 23)?intValue__I__devirtual$(castTo(value_0, 23)):$equals_5('layerno', k) && instanceOf(value_0, 23)?$setLayerno(bg, intValue__I__devirtual$(castTo(value_0, 23))):$equals_5('actionno', k) && instanceOf(value_0, 23)?$setActionno(bg, intValue__I__devirtual$(castTo(value_0, 23))):$equals_5('yscalestart', k) && instanceOf(value_0, 23)?$setYscalestart(bg, floatValue__F__devirtual$(castTo(value_0, 23))):$equals_5('yscaledelta', k) && instanceOf(value_0, 23) && floatValue__F__devirtual$(castTo(value_0, 23));
}

function applyScalar(bg, k, value_0){
  var n;
  if (!instanceOf(value_0, 23)) {
    return false;
  }
  n = floatValue__F__devirtual$(castTo(value_0, 23));
  if ($equals_5('velocity', k)) {
    $setX(bg.velocity, n);
    return true;
  }
  if ($equals_5('tile', k)) {
    $setX(bg.tile, n);
    return true;
  }
  if ($equals_5('start', k)) {
    $setX(bg.start_0, n);
    return true;
  }
  if ($equals_5('delta', k)) {
    $setX(bg.delta, n);
    return true;
  }
  return false;
}

function applyToBg(bg, key, objectValues){
  var k, p, wx, wy;
  if (objectValues.length == 0) {
    return;
  }
  k = key.toLowerCase();
  if (objectValues.length == 1) {
    if (applyScalar(bg, k, objectValues[0])) {
      return;
    }
    applyOne(bg, k, objectValues[0]);
    return;
  }
  if ($equals_5('start', k) || $equals_5('tile', k) || $equals_5('velocity', k) || $equals_5('delta', k)) {
    p = new PointF;
    instanceOf(objectValues[0], 23) && $setX(p, floatValue__F__devirtual$(castTo(objectValues[0], 23)));
    objectValues.length > 1 && instanceOf(objectValues[1], 23) && $setY(p, floatValue__F__devirtual$(castTo(objectValues[1], 23)));
    applyOne(bg, k, p);
  }
   else if ($equals_5('width', k)) {
    wx = instanceOf(objectValues[0], 23)?intValue__I__devirtual$(castTo(objectValues[0], 23)):0;
    wy = objectValues.length > 1 && instanceOf(objectValues[1], 23)?intValue__I__devirtual$(castTo(objectValues[1], 23)):0;
    new MugenPoint_0(wx, wy);
  }
   else 
    $equals_5('spriteno', k) && instanceOf(objectValues[0], 86)?$setSpriteno(bg, castTo(objectValues[0], 86)):applyOne(bg, k, objectValues[0]);
}

function fillBgFields(bg, grp){
  var entry, key, key$iterator, objectValues, outerIter, value_0;
  for (key$iterator = (outerIter = new AbstractHashMap$EntrySetIterator((new AbstractHashMap$EntrySet((new AbstractMap$1(grp.keyValues)).this$01)).this$01) , new AbstractMap$1$1(outerIter)); key$iterator.val$outerIter2.hasNext;) {
    key = (entry = $next_6(key$iterator.val$outerIter2) , castToString(entry.getKey()));
    value_0 = castToString($getStringValue(grp.keyValues, key));
    objectValues = eval_0(key, value_0);
    applyToBg(bg, key, objectValues);
  }
}

defineClass(150, 436, $intern_2, GwtBackground);
var Lorg_lee_mugen_background_GwtBackground_2_classLit = createForClass('org.lee.mugen.background', 'GwtBackground', 150);
function GwtStageRoot(resourcePath){
  var path, slash;
  path = $replace(resourcePath);
  slash = $lastIndexOf(path, fromCodePoint(47));
  this.currentDir = new File_1(slash >= 0?(checkCriticalStringBounds(0, slash, path.length) , path.substr(0, slash)):'');
}

defineClass(264, 1, {}, GwtStageRoot);
var Lorg_lee_mugen_background_GwtStageRoot_2_classLit = createForClass('org.lee.mugen.background', 'GwtStageRoot', 264);
function GwtStageSelectBackground(root){
  GwtBackground.call(this, root.currentDir, '');
}

defineClass(265, 150, $intern_2, GwtStageSelectBackground);
var Lorg_lee_mugen_background_GwtStageSelectBackground_2_classLit = createForClass('org.lee.mugen.background', 'GwtStageSelectBackground', 265);
function $clinit_GameFight(){
  $clinit_GameFight = emptyMethod;
  new GameFight;
}

function GameFight(){
  new GameGlobalEvents;
}

defineClass(486, 1, {}, GameFight);
var Lorg_lee_mugen_core_GameFight_2_classLit = createForClass('org.lee.mugen.core', 'GameFight', 486);
function GameGlobalEvents(){
  new PalFxSub;
}

defineClass(485, 1, {}, GameGlobalEvents);
var Lorg_lee_mugen_core_GameGlobalEvents_2_classLit = createForClass('org.lee.mugen.core', 'GameGlobalEvents', 485);
function $selectNext(this$static){
  var index_0;
  if (this$static.fire) {
    index_0 = getInstance().titleInfo.menu.itemname.currentIndex;
    switch (index_0) {
      case 0:
      case 1:
        this$static.next_0 = ($clinit_GameSelect() , $clinit_GameSelect() , INSTANCE_1);
        break;
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      case 2:
        log_0('not implemented', stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_Object_2_classLit, 1), $intern_9, 1, 5, []));
        break;
      case 10:
        log_0('not implemented', stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_Object_2_classLit, 1), $intern_9, 1, 5, []));
    }
    this$static.fire = false;
  }
}

function GameMenu(){
}

defineClass(139, 1, {}, GameMenu);
_.free = function free(){
}
;
_.getNext = function getNext(){
  return !this.next_0?this:this.next_0;
}
;
_.init = function init_0(container){
  !!audioPlayback && $stopMusic(audioPlayback);
  playMusic(($clinit_JMugenConstant() , RESOURCE) + ('' + getInstance().music.title$bgm));
  this.render = new TitleInfoRender(this);
  this.fire = false;
  this.next_0 = null;
  this.time = 0;
  $init_1(getInstance().titleInfo);
  $setPhase(getInstance().titleInfo, 0);
  container.mugenKeyListeners.array.length = 0;
  $addActionListener(container, new GameMenu$1(this));
}
;
_.render_0 = function render(){
  $render_7(this.render);
}
;
_.update = function update(delta){
  var br, startIndex;
  if (this.fire) {
    if (getInstance().titleInfo.phase != 3) {
      $setPhase(getInstance().titleInfo, 2);
    }
     else {
      $selectNext(this);
      return;
    }
  }
  if (getInstance().titleInfo.phase == -1) {
    getInstance();
    $setPhase(getInstance().titleInfo, 0);
  }
  br = getInstance().titleBackground;
  $process_0(br);
  $process_3(getInstance().titleInfo);
  startIndex = getInstance().titleInfo.menu.itemname.first;
  if (this.lastStartIndex + this.lastIndexForMove < startIndex) {
    this.lastIndexForMove += this.speedSwitch;
    this.incIndexForMove = round_int(this.incIndexForMove - this.speedSwitch * 10);
    if (this.lastStartIndex + this.lastIndexForMove > this.lastStartIndex + 1) {
      this.lastStartIndex = this.lastStartIndex + 1;
      this.lastIndexForMove = 0;
      this.incIndexForMove = 0;
    }
  }
   else if (this.lastStartIndex + this.lastIndexForMove > startIndex) {
    this.lastIndexForMove -= this.speedSwitch;
    this.incIndexForMove = round_int(this.incIndexForMove + this.speedSwitch * 10);
    if (this.lastStartIndex + this.lastIndexForMove < this.lastStartIndex - 1) {
      this.lastStartIndex = this.lastStartIndex - 1;
      this.lastIndexForMove = 0;
      this.incIndexForMove = 0;
    }
  }
   else {
    this.incIndexForMove = 0;
    this.lastIndexForMove = 0;
    this.lastStartIndex = getInstance().titleInfo.menu.itemname.first;
  }
  ++this.time;
}
;
_.fire = false;
_.incIndexForMove = 0;
_.lastIndexForMove = 0;
_.lastStartIndex = 0;
_.speedSwitch = 0.30000001192092896;
_.time = 0;
var instance_0;
var Lorg_lee_mugen_core_GameMenu_2_classLit = createForClass('org.lee.mugen.core', 'GameMenu', 139);
function GameMenu$1(this$0){
  this.this$01 = this$0;
}

defineClass(333, 1, {274:1}, GameMenu$1);
_.action_0 = function action_0(key, isPress){
  var cmdOne, entry, grp, id_0, id$iterator, num, outerIter, result, snd;
  $clinit_System();
  fromDouble_0(Date.now());
  if (!isPress)
    return;
  for (id$iterator = ($clinit_CmdProcDispatcher() , outerIter = new AbstractHashMap$EntrySetIterator((new AbstractHashMap$EntrySet((new AbstractMap$1(spriteDispatcherMap)).this$01)).this$01) , new AbstractMap$1$1(outerIter)); id$iterator.val$outerIter2.hasNext;) {
    id_0 = (entry = $next_6(id$iterator.val$outerIter2) , castToString(entry.getKey()));
    cmdOne = castTo($getStringValue(spriteDispatcherMap, id_0), 77);
    if (cmdOne.down == key) {
      fromDouble_0(Date.now());
      this.this$01.lastStartIndex = getInstance().titleInfo.menu.itemname.first;
      $increaseCurrentIndex(getInstance().titleInfo.menu.itemname);
      grp = getInstance().titleInfo.menu.cursor$move$snd.snd_grp;
      num = getInstance().titleInfo.menu.cursor$move$snd.snd_item;
      snd = $getSound($getGroup(getInstance().files.snd, grp), num);
      !!audioPlayback && $playSfx(audioPlayback, snd);
    }
     else if (cmdOne.up == key) {
      fromDouble_0(Date.now());
      this.this$01.lastStartIndex = getInstance().titleInfo.menu.itemname.first;
      $decreaseCurrentIndex(getInstance().titleInfo.menu.itemname);
      grp = getInstance().titleInfo.menu.cursor$move$snd.snd_grp;
      num = getInstance().titleInfo.menu.cursor$move$snd.snd_item;
      snd = $getSound($getGroup(getInstance().files.snd, grp), num);
      !!audioPlayback && $playSfx(audioPlayback, snd);
    }
     else {
      result = cmdOne.a == key || cmdOne.b == key || cmdOne.b == key || cmdOne.x_0 == key || cmdOne.y_0 == key || cmdOne.z_0 == key || cmdOne.abc == key || cmdOne.xyz == key;
      if (result) {
        this.this$01.fire = true;
        grp = getInstance().titleInfo.menu.cursor$done$snd.snd_grp;
        num = getInstance().titleInfo.menu.cursor$done$snd.snd_item;
        snd = $getSound($getGroup(getInstance().files.snd, grp), num);
        !!audioPlayback && $playSfx(audioPlayback, snd);
      }
    }
  }
}
;
var Lorg_lee_mugen_core_GameMenu$1_2_classLit = createForClass('org.lee.mugen.core', 'GameMenu/1', 333);
function $clampPlayersToScreen(this$static){
  $updateCamera(this$static);
  !!this$static.p1 && $clampToScreen(this$static.p1, this$static.cameraX);
  !!this$static.p2 && $clampToScreen(this$static.p2, this$static.cameraX);
  $updateCamera(this$static);
  !!this$static.p1 && $clampToScreen(this$static.p1, this$static.cameraX);
  !!this$static.p2 && $clampToScreen(this$static.p2, this$static.cameraX);
}

function $loadFighters(this$static){
  var a1, a2, boundL, boundR, chars, cmd1, cmd2, cns1, cns2, f1, f2, life1, life2;
  this$static.p1 = null;
  this$static.p2 = null;
  chars = getInstance().files.gwtSelect.characters;
  if (!chars) {
    return;
  }
  boundL = this$static.stageInfo?this$static.stageInfo.leftbound:-120;
  boundR = this$static.stageInfo?this$static.stageInfo.rightbound:120;
  try {
    f1 = $getFightFiles(chars, this$static.player1Id);
    if (f1 != null) {
      cmd1 = load(this$static.player1Id);
      cns1 = null;
      try {
        cns1 = load_0(this$static.player1Id);
      }
       catch ($e0) {
        $e0 = toJava($e0);
        if (!instanceOf($e0, 6))
          throw toJs($e0);
      }
      a1 = new GwtFightFighterAnim(this$static.player1Id, f1[0], f1[1]);
      life1 = cns1?cns1.life:$getLife(chars, this$static.player1Id);
      this$static.maxLife = life1;
      this$static.p1 = new GwtFightPlayer('1', this$static.stageInfo.p1facing, this$static.stageInfo.p1startx, this$static.stageInfo.p1starty, a1, life1, cmd1, cns1, boundL, boundR);
    }
  }
   catch ($e1) {
    $e1 = toJava($e1);
    if (!instanceOf($e1, 6))
      throw toJs($e1);
  }
  try {
    f2 = $getFightFiles(chars, this$static.player2Id);
    if (f2 != null) {
      cmd2 = load(this$static.player2Id);
      cns2 = null;
      try {
        cns2 = load_0(this$static.player2Id);
      }
       catch ($e2) {
        $e2 = toJava($e2);
        if (!instanceOf($e2, 6))
          throw toJs($e2);
      }
      a2 = new GwtFightFighterAnim(this$static.player2Id, f2[0], f2[1]);
      life2 = cns2?cns2.life:$getLife(chars, this$static.player2Id);
      this$static.maxLife = $wnd.Math.max(this$static.maxLife, life2);
      this$static.p2 = new GwtFightPlayer('2', this$static.stageInfo.p2facing, this$static.stageInfo.p2startx, this$static.stageInfo.p2starty, a2, life2, cmd2, cns2, boundL, boundR);
    }
  }
   catch ($e3) {
    $e3 = toJava($e3);
    if (!instanceOf($e3, 6))
      throw toJs($e3);
  }
}

function $loadStage(this$static){
  var bg, bg$iterator, groups, reader, resourcePath, root, stageBg;
  this$static.stageBackground = null;
  this$static.stageInfo = new GwtStageFightInfo;
  if (this$static.stagePath == null) {
    return;
  }
  try {
    resourcePath = ($clinit_JMugenConstant() , RESOURCE) + ('' + this$static.stagePath);
    this$static.stageInfo = load_2(this$static.stagePath);
    root = new GwtStageRoot(resourcePath);
    reader = openUtf8Reader(resourcePath);
    groups = ($clinit_Parser() , getGroupTextMap(reader, false));
    stageBg = new GwtStageSelectBackground(root);
    $parse_1(stageBg, groups);
    for (bg$iterator = new ArrayList$1(stageBg.bgs); bg$iterator.i < bg$iterator.this$01.array.length;) {
      bg = castTo($next_7(bg$iterator), 91);
      if (!bg.isInit) {
        bg.pos = new PointF_1(bg.start_0);
        $setAction_0($getAnimManager(bg), bg.actionno);
        bg.isInit = true;
      }
    }
    this$static.stageBackground = stageBg;
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 6)) {
      this$static.stageBackground = null;
    }
     else 
      throw toJs($e0);
  }
}

function $onDebugAction(this$static, action){
  var fps;
  switch (action) {
    case 0:
      this$static.debugPlayerIndex = (this$static.debugPlayerIndex + 1) % 3;
      break;
    case 8:
      $beginRound(this$static.rules_0);
      this$static.lastRoundStarted = this$static.rules_0.round_0;
      !!this$static.p1 && $resetForRound(this$static.p1, this$static.stageInfo.p1startx, this$static.stageInfo.p1starty, this$static.maxLife);
      !!this$static.p2 && $resetForRound(this$static.p2, this$static.stageInfo.p2startx, this$static.stageInfo.p2starty, this$static.maxLife);
      break;
    case 1:
      this$static.showCns = !this$static.showCns;
      break;
    case 2:
      this$static.showAttackCns = !this$static.showAttackCns;
      break;
    case 3:
      if (this$static.window_0) {
        fps = this$static.window_0.timer.framerate;
        $setFramerate(this$static.window_0.timer, add_2(fps, 1));
      }

      break;
    case 4:
      if (this$static.window_0) {
        fps = this$static.window_0.timer.framerate;
        $setFramerate(this$static.window_0.timer, lt(sub_1(fps, 1), 0)?0:sub_1(fps, 1));
      }

      break;
    case 5:
      !!this$static.window_0 && (this$static.window_0.timer.framerate = 60 , undefined);
      break;
    case 12:
      this$static.displayHelp = !this$static.displayHelp;
      break;
    case 6:
      this$static.debugPaused = !this$static.debugPaused;
      break;
    case 7:
      this$static.debugPaused && (this$static.forceOneFrame = true);
  }
}

function $renderDebugInfo(this$static){
  var font, line, line$array, line$index, line$max, lines, md, y_0;
  md = getInstance_0();
  font = resolveFont(2);
  !font && (font = resolveFont(3));
  if (!font) {
    return;
  }
  y_0 = 40;
  if (this$static.displayHelp) {
    lines = stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_String_2_classLit, 1), $intern_11, 2, 6, ['F1       : Help', 'CTRL +/- : FPS (or =/-)', 'CTRL *   : Reset FPS', 'Space    : Reset round', 'CTRL-D   : Player debug info', 'CTRL-C   : Clsn2 (hurt) boxes', 'CTRL-X   : Clsn1 (attack) boxes', 'CTRL-P   : Pause', 'CTRL-A   : Step frame (paused)']);
    for (line$array = lines , line$index = 0 , line$max = line$array.length; line$index < line$max; ++line$index) {
      line = line$array[line$index];
      y_0 += 10;
      $drawLeftToRight(font, 8, y_0, md, line, 0, 1);
    }
  }
  this$static.debugPlayerIndex == 1 && !!this$static.p1?$draw_2(font, 0, 8, 220, md, $debugSummary(this$static.p1), 1, 1):this$static.debugPlayerIndex == 2 && !!this$static.p2 && $draw_2(font, 0, 8, 220, md, $debugSummary(this$static.p2), 1, 1);
  this$static.debugPaused && $drawLeftToRight(font, 240, 230, md, 'PAUSE', 0, 1);
}

function $renderStage(this$static, md){
  if (!this$static.stageBackground) {
    return;
  }
  $setColor_3(md, 1, 1, 1, 1);
  $render_3(new GwtStageBackgroundRender(this$static.stageBackground, this$static.cameraX, 0, 160));
  $render_3(new GwtStageBackgroundRender_0(this$static.stageBackground, this$static.cameraX, 0, 160, 1));
}

function $startStageMusic(this$static){
  var path, slash;
  !!audioPlayback && $stopMusic(audioPlayback);
  path = this$static.stageInfo?this$static.stageInfo.bgmusic:null;
  (path == null || path.length == 0) && (path = 'sound/intro.mp3');
  if (!$equals_5(path.substr(0, 6), 'sound/') && !$equals_5(path.substr(0, 5), 'data/')) {
    slash = this$static.stagePath != null?$lastIndexOf(this$static.stagePath, fromCodePoint(47)):-1;
    slash >= 0 && (path = $substring_0(this$static.stagePath, 0, slash + 1) + ('' + path));
  }
  try {
    playMusic(($clinit_JMugenConstant() , RESOURCE) + ('' + path));
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (!instanceOf($e0, 6))
      throw toJs($e0);
  }
}

function $updateCamera(this$static){
  var mid;
  if (!this$static.p1 || !this$static.p2) {
    this$static.cameraX = 0;
    return;
  }
  mid = (this$static.p1.x_0 + this$static.p2.x_0) * 0.5;
  this$static.cameraX = -mid;
  if (this$static.stageInfo) {
    this$static.cameraX < this$static.stageInfo.boundleft && (this$static.cameraX = this$static.stageInfo.boundleft);
    this$static.cameraX > this$static.stageInfo.boundright && (this$static.cameraX = this$static.stageInfo.boundright);
  }
}

function GwtGameFight(stagePath, player1Id, player2Id){
  this.rules_0 = new GwtFightRules;
  this.keysDown = initUnidimensionalArray(Z_classLit, $intern_2, 5, 512, 16, 1);
  this.renderables = new ArrayList;
  this.stagePath = stagePath;
  this.player1Id = player1Id;
  this.player2Id = player2Id;
}

function resolveFont(fontNo){
  var fonts, sys;
  sys = getInstance();
  if (!sys || !sys.files) {
    return null;
  }
  fonts = sys.files.font;
  return castTo($get_8(fonts, valueOf_1(fontNo)), 114);
}

defineClass(206, 1, {553:1, 206:1}, GwtGameFight);
_.free = function free_0(){
  this.stageBackground = null;
  this.fightHud = null;
  this.p1 = null;
  this.p2 = null;
  this.cmdProc1 = null;
  this.cmdProc2 = null;
  this.window_0 = null;
  this.renderables.array.length = 0;
}
;
_.getNext = function getNext_0(){
  return !this.next_0?this:this.next_0;
}
;
_.init = function init_1(container){
  var chars, d1, d2, hud;
  this.window_0 = container;
  this.next_0 = null;
  this.gameTime = 0;
  this.matchEndTimer = 0;
  this.showCns = false;
  this.showAttackCns = false;
  this.displayHelp = false;
  this.debugPaused = false;
  this.forceOneFrame = false;
  this.debugPlayerIndex = 0;
  this.fightAudio = load_1();
  $setAudio_0(this.rules_0, this.fightAudio);
  $startMatch(this.rules_0);
  this.lastRoundStarted = 0;
  $setAlpha_1(getInstance_0(), 1);
  $loadStage(this);
  $startStageMusic(this);
  $loadFighters(this);
  if (this.p1) {
    $setAudio(this.p1, this.fightAudio);
    $setZOffset(this.p1, this.stageInfo?this.stageInfo.zoffset:192);
    !!this.stageInfo && $setShadow(this.p1, this.stageInfo.shadowIntensity, this.stageInfo.shadowYScale);
  }
  if (this.p2) {
    $setAudio(this.p2, this.fightAudio);
    $setZOffset(this.p2, this.stageInfo?this.stageInfo.zoffset:192);
    !!this.stageInfo && $setShadow(this.p2, this.stageInfo.shadowIntensity, this.stageInfo.shadowYScale);
  }
  try {
    this.fightHud = (hud = new GwtFightHud , $parse_2(hud) , hud);
    chars = getInstance().files.gwtSelect.characters;
    if (chars) {
      $setFaces_0(this.fightHud, $getPortraitSprite(chars, this.player1Id), $getPortraitSprite(chars, this.player2Id));
      $setNames(this.fightHud, $getDisplayName(chars, this.player1Id), $getDisplayName(chars, this.player2Id));
    }
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 6)) {
      this.fightHud = null;
    }
     else 
      throw toJs($e0);
  }
  container.mugenKeyListeners.array.length = 0;
  $reset_5(container.spriteCmdProcess);
  d1 = ($clinit_CmdProcDispatcher() , castTo($getStringValue(spriteDispatcherMap, '1'), 77));
  d2 = castTo($getStringValue(spriteDispatcherMap, '2'), 77);
  !!d1 && $reset_5(d1.mugenKeyEvents);
  !!d2 && $reset_5(d2.mugenKeyEvents);
  if (!!this.p1 && !!d1) {
    this.cmdProc1 = new GwtFightCmdProcess(d1, this.p1);
    $addSpriteKeyProcessor(container, this.cmdProc1);
  }
  if (!!this.p2 && !!d2) {
    this.cmdProc2 = new GwtFightCmdProcess(d2, this.p2);
    $addSpriteKeyProcessor(container, this.cmdProc2);
  }
  $addActionListener(container, new GwtGameFight$1(this));
}
;
_.render_0 = function render_0(){
  var md;
  md = getInstance_0();
  $setAlpha_1(md, 1);
  $setColor_3(md, 0, 0, 0, 1);
  $fillRect(md, 0, 0, 320, 240);
  $updateCamera(this);
  $renderStage(this, md);
  !!this.p1 && $drawShadow(this.p1, md, this.cameraX);
  !!this.p2 && $drawShadow(this.p2, md, this.cameraX);
  !!this.p1 && $draw_0(this.p1, md, this.cameraX);
  !!this.p2 && $draw_0(this.p2, md, this.cameraX);
  if (this.showCns) {
    !!this.p1 && $drawDebugCns(this.p1, md, this.cameraX);
    !!this.p2 && $drawDebugCns(this.p2, md, this.cameraX);
  }
  if (this.showAttackCns) {
    !!this.p1 && $drawDebugAttackCns(this.p1, md, this.cameraX);
    !!this.p2 && $drawDebugAttackCns(this.p2, md, this.cameraX);
  }
  if (!!this.fightHud && !!this.p1 && !!this.p2) {
    $render_9(this.fightHud, md, this.p1.life, this.p2.life, this.maxLife, this.p1.power, this.p2.power);
    this.rules_0.phase == 3 && $renderTimer(this.fightHud, md, this.rules_0.roundTimer);
    this.rules_0.phase == 1?$renderBannerText(this.fightHud, md, 'Round ' + this.rules_0.round_0, 100):this.rules_0.phase == 2?$drawBannerSpr(this.fightHud, md, 110, 80):this.rules_0.statusBanner != null && ($equals_5('K.O.', this.rules_0.statusBanner) || $equals_5('D.K.O.', this.rules_0.statusBanner)?$drawBannerSpr(this.fightHud, md, 70, 200):$renderBannerText(this.fightHud, md, this.rules_0.statusBanner, 112));
  }
  $renderDebugInfo(this);
}
;
_.update = function update_0(delta){
  var ctrl, d1, d2;
  !!this.stageBackground && (!this.debugPaused || this.forceOneFrame) && $process_0(this.stageBackground);
  if (!this.p1 || !this.p2) {
    return;
  }
  if (this.rules_0.phase == 6) {
    this.matchEndTimer += delta;
    this.matchEndTimer > 3000 && (this.next_0 = (!instance_0 && (instance_0 = new GameMenu) , instance_0));
    return;
  }
  if (this.debugPaused && !this.forceOneFrame) {
    return;
  }
  this.forceOneFrame = false;
  $tick_0(this.rules_0);
  if (this.rules_0.round_0 != this.lastRoundStarted) {
    this.lastRoundStarted = this.rules_0.round_0;
    !!this.p1 && $resetForRound(this.p1, this.stageInfo.p1startx, this.stageInfo.p1starty, this.maxLife);
    !!this.p2 && $resetForRound(this.p2, this.stageInfo.p2startx, this.stageInfo.p2starty, this.maxLife);
  }
  ctrl = this.rules_0.ctrlActive;
  $setCtrlEnabled(this.p1, ctrl);
  $setCtrlEnabled(this.p2, ctrl);
  this.gameTime = add_2(this.gameTime, 1);
  !!this.cmdProc1 && $setGameTime(this.cmdProc1, this.gameTime);
  !!this.cmdProc2 && $setGameTime(this.cmdProc2, this.gameTime);
  if (ctrl) {
    !!this.cmdProc1 && $process_1(this.cmdProc1);
    !!this.cmdProc2 && $process_1(this.cmdProc2);
  }
  d1 = ($clinit_CmdProcDispatcher() , castTo($getStringValue(spriteDispatcherMap, '1'), 77));
  d2 = castTo($getStringValue(spriteDispatcherMap, '2'), 77);
  $update_6(this.p1, ctrl?d1:null, this.keysDown, this.p2);
  $update_6(this.p2, ctrl?d2:null, this.keysDown, this.p1);
  resolve_0(this.p1, this.p2);
  $clampPlayersToScreen(this);
  resolve_0(this.p1, this.p2);
  $clampPlayersToScreen(this);
  $tryHitOpponent(this.p1, this.p2);
  $tryHitOpponent(this.p2, this.p1);
  this.rules_0.phase == 3 && (this.p1.life <= 0 && this.p2.life <= 0?$onKo(this.rules_0, 0):this.p1.life <= 0?$onKo(this.rules_0, 2):this.p2.life <= 0?$onKo(this.rules_0, 1):this.rules_0.roundTimer <= 0 && (this.p1.life > this.p2.life?$onTimeOver(this.rules_0, 1):this.p2.life > this.p1.life?$onTimeOver(this.rules_0, 2):$onTimeOver(this.rules_0, 0)));
}
;
_.cameraX = 0;
_.debugPaused = false;
_.debugPlayerIndex = 0;
_.displayHelp = false;
_.forceOneFrame = false;
_.gameTime = 0;
_.lastRoundStarted = 0;
_.matchEndTimer = 0;
_.maxLife = 1000;
_.showAttackCns = false;
_.showCns = false;
var Lorg_lee_mugen_core_GwtGameFight_2_classLit = createForClass('org.lee.mugen.core', 'GwtGameFight', 206);
function GwtGameFight$1(this$0){
  this.this$01 = this$0;
}

defineClass(502, 1, {274:1}, GwtGameFight$1);
_.action_0 = function action_1(key, isPress){
  key >= 0 && key < 512 && (this.this$01.keysDown[key] = isPress);
  !isPress && key == 111 && (this.this$01.next_0 = (!instance_0 && (instance_0 = new GameMenu) , instance_0));
}
;
var Lorg_lee_mugen_core_GwtGameFight$1_2_classLit = createForClass('org.lee.mugen.core', 'GwtGameFight/1', 502);
function $clinit_JMugenConstant(){
  $clinit_JMugenConstant = emptyMethod;
  RESOURCE = resolveDataResourcePrefix();
}

var RESOURCE;
function ensureTrailingSlash(override){
  var end;
  end = $charAt(override, override.length - 1);
  if (end != 47) {
    return override + '/';
  }
  return override;
}

function resolveDataResourcePrefix(){
  if (resourcePrefixOverride != null && resourcePrefixOverride.length != 0) {
    return ensureTrailingSlash(resourcePrefixOverride);
  }
  return (new ResourceBundleHelper$MugenBundle).resourcePrefix;
}

var resourcePrefixOverride;
function $clinit_GameSelect(){
  $clinit_GameSelect = emptyMethod;
  INSTANCE_1 = new GameSelect;
}

function GameSelect(){
  this.delegate = ($clinit_GwtGameSelect() , $clinit_GwtGameSelect() , INSTANCE_2);
}

defineClass(488, 1, {}, GameSelect);
_.free = function free_1(){
  $free_0(this.delegate);
}
;
_.getNext = function getNext_1(){
  return $getNext(this.delegate);
}
;
_.init = function init_2(container){
  $init(this.delegate, container);
}
;
_.render_0 = function render_1(){
  $render_2(this.delegate);
}
;
_.update = function update_1(delta){
  $update_5(this.delegate);
}
;
var INSTANCE_1;
var Lorg_lee_mugen_core_gameSelect_GameSelect_2_classLit = createForClass('org.lee.mugen.core.gameSelect', 'GameSelect', 488);
function $clinit_GwtGameSelect(){
  $clinit_GwtGameSelect = emptyMethod;
  INSTANCE_2 = new GwtGameSelect;
}

function $ensureStagePreviewLoaded(this$static){
  var groups, idx, path, reader, resourcePath, root, stageBg;
  idx = shareIndexOfStage;
  if (!this$static.stageChanged && this$static.loadedStageIndex == idx && !!this$static.stagePreviewBackground) {
    return;
  }
  this$static.stageChanged = false;
  this$static.loadedStageIndex = idx;
  this$static.stagePreviewBackground = null;
  path = $getCurrentStagePath();
  if (path == null) {
    return;
  }
  try {
    resourcePath = ($clinit_JMugenConstant() , RESOURCE) + ('' + path);
    root = new GwtStageRoot(resourcePath);
    reader = openUtf8Reader(resourcePath);
    groups = ($clinit_Parser() , getGroupTextMap(reader, false));
    stageBg = new GwtStageSelectBackground(root);
    $parse_1(stageBg, groups);
    initBackgrounds(stageBg);
    this$static.stagePreviewBackground = stageBg;
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 6)) {
      this$static.stagePreviewBackground = null;
    }
     else 
      throw toJs($e0);
  }
}

function $free_0(this$static){
  this$static.stagePreviewBackground = null;
  this$static.loadedStageIndex = -1;
}

function $getCurrentStageDisplayName(){
  var path;
  path = $getCurrentStagePath();
  if (path == null) {
    return '';
  }
  return $getRealName(getInstance().files.gwtSelect.extraStages, path);
}

function $getCurrentStagePath(){
  var idx, select, stages;
  select = getInstance().files.gwtSelect;
  if (!select || select.extraStages.stages.array.length == 0) {
    return null;
  }
  idx = shareIndexOfStage;
  stages = select.extraStages.stages;
  (idx < 0 || idx >= stages.array.length) && (idx = 0);
  return checkCriticalElementIndex(idx, stages.array.length) , castToString(stages.array[idx]);
}

function $getGameFight(this$static){
  var p1, p2, stage;
  p1 = $getSelectedCharacterName(this$static, 0);
  p2 = $getSelectedCharacterName(this$static, 1);
  stage = $getCurrentStagePath();
  return new GwtGameFight(stage, p1 != null?p1:'p1', p2 != null?p2:'p2');
}

function $getNext(this$static){
  return !this$static.next_0?this$static:this$static.next_0;
}

function $getSelectedCharacterName(this$static, playerIndex){
  if (this$static.selectionControllers == null || playerIndex >= this$static.selectionControllers.length) {
    return null;
  }
  return $getSelectedSprite(this$static.selectionControllers[playerIndex].position);
}

function $getSelectedSprite(p){
  var info, select;
  info = getInstance().selectInfo;
  select = getInstance().files.gwtSelect;
  if (!info || !select) {
    return null;
  }
  return $getCharacterAt(select, p.x_0, p.y_0, info.columns);
}

function $getStagePreviewBackground(this$static){
  if (!((shareState & getCharacterBit(1)) == getCharacterBit(1) && (shareState & getCharacterBit(2)) == getCharacterBit(2)) || (shareState & 16) == 16) {
    return null;
  }
  $ensureStagePreviewLoaded(this$static);
  return this$static.stagePreviewBackground;
}

function $init(this$static, container){
  var bgm, info, ms, p1Start, p2Start, sc, sc$array, sc$index, sc$max;
  ms = getInstance();
  !!audioPlayback && $stopMusic(audioPlayback);
  bgm = ms.music.select$bgm;
  bgm != null && bgm.length > 0 && playMusic(($clinit_JMugenConstant() , RESOURCE) + ('' + bgm));
  this$static.next_0 = null;
  this$static.backToMenu = false;
  this$static.versusLaunched = false;
  this$static.stagePreviewBackground = null;
  this$static.loadedStageIndex = -1;
  this$static.stageChanged = true;
  this$static.selectRender = new GwtSelectRender(this$static);
  initBackgrounds(ms.selectBackground);
  shareState = 0;
  shareIndexOfStage = 0;
  info = ms.selectInfo;
  p1Start = new MugenPoint_0(0, 0);
  p2Start = new MugenPoint_0(0, 4);
  if (info) {
    !!info.player1 && !!info.player1.cursor && !!info.player1.cursor.startcell && (p1Start = info.player1.cursor.startcell);
    !!info.player2 && !!info.player2.cursor && !!info.player2.cursor.startcell && (p2Start = info.player2.cursor.startcell);
  }
  this$static.selectionControllers = stampJavaTypeInfo(getClassLiteralForArray(Lorg_lee_mugen_core_gameSelect_GwtSelectionController_2_classLit, 1), $intern_9, 119, 0, [new GwtSelectionController(this$static, '1', p1Start), new GwtSelectionController(this$static, '2', p2Start)]);
  container.mugenKeyListeners.array.length = 0;
  for (sc$array = this$static.selectionControllers , sc$index = 0 , sc$max = sc$array.length; sc$index < sc$max; ++sc$index) {
    sc = sc$array[sc$index];
    !!sc && $add_8(container.mugenKeyListeners, sc);
  }
}

function $render_2(this$static){
  if (!!this$static.selectRender && this$static.selectionControllers != null) {
    $setControllers(this$static.selectRender, this$static.selectionControllers);
    $render_5(this$static.selectRender);
  }
}

function $update_5(this$static){
  var bg;
  if (this$static.backToMenu) {
    this$static.next_0 = (!instance_0 && (instance_0 = new GameMenu) , instance_0);
    return;
  }
  bg = getInstance().selectBackground;
  !!bg && $process_0(bg);
  if ((shareState & getCharacterBit(1)) == getCharacterBit(1) && (shareState & getCharacterBit(2)) == getCharacterBit(2) && (shareState & 16) != 16) {
    $ensureStagePreviewLoaded(this$static);
    !!this$static.stagePreviewBackground && $process_0(this$static.stagePreviewBackground);
  }
  if (this$static.selectionControllers != null && isAllSelectionDone(this$static.selectionControllers.length) && !this$static.versusLaunched) {
    this$static.versusLaunched = true;
    this$static.next_0 = new GwtGameVsScreen(this$static);
  }
}

function GwtGameSelect(){
}

function initBackgrounds(background){
  var bg, bg$iterator;
  if (!background) {
    return;
  }
  for (bg$iterator = new ArrayList$1(background.bgs); bg$iterator.i < bg$iterator.this$01.array.length;) {
    bg = castTo($next_7(bg$iterator), 91);
    if (!bg.isInit) {
      bg.pos = new PointF_1(bg.start_0);
      $setAction_0($getAnimManager(bg), bg.actionno);
      bg.isInit = true;
    }
  }
}

defineClass(498, 1, {}, GwtGameSelect);
_.free = function free_2(){
  $free_0(this);
}
;
_.getNext = function getNext_2(){
  return $getNext(this);
}
;
_.init = function init_3(container){
  $init(this, container);
}
;
_.render_0 = function render_2(){
  $render_2(this);
}
;
_.update = function update_2(delta){
  $update_5(this);
}
;
_.backToMenu = false;
_.loadedStageIndex = -1;
_.stageChanged = false;
_.versusLaunched = false;
var INSTANCE_2;
var Lorg_lee_mugen_core_gameSelect_GwtGameSelect_2_classLit = createForClass('org.lee.mugen.core.gameSelect', 'GwtGameSelect', 498);
function $getPlayerName(this$static, index_0){
  return $getSelectedCharacterName(this$static.gameSelect, index_0);
}

function $launchFight(this$static){
  if (this$static.fightLaunched) {
    return;
  }
  this$static.fightLaunched = true;
  $setAlpha_1(getInstance_0(), 1);
  this$static.next_0 = $getGameFight(this$static.gameSelect);
}

function GwtGameVsScreen(gameSelect){
  this.gameSelect = gameSelect;
}

defineClass(500, 1, {}, GwtGameVsScreen);
_.free = function free_3(){
  this.vsRender = null;
}
;
_.getNext = function getNext_3(){
  return !this.next_0?this:this.next_0;
}
;
_.init = function init_4(container){
  var bg, bg$iterator, bgm, ms, versus, vsScreen;
  this.next_0 = null;
  this.fightLaunched = false;
  ms = getInstance();
  !!audioPlayback && $stopMusic(audioPlayback);
  bgm = ms.music.vs$bgm;
  bgm != null && bgm.length > 0 && playMusic(($clinit_JMugenConstant() , RESOURCE) + ('' + bgm));
  vsScreen = ms.vsScreen;
  !!vsScreen && $resetState(vsScreen);
  versus = ms.versusBackground;
  if (versus) {
    for (bg$iterator = new ArrayList$1(versus.bgs); bg$iterator.i < bg$iterator.this$01.array.length;) {
      bg = castTo($next_7(bg$iterator), 91);
      if (!bg.isInit) {
        bg.pos = new PointF_1(bg.start_0);
        $setAction_0($getAnimManager(bg), bg.actionno);
        bg.isInit = true;
      }
    }
  }
  this.vsRender = new GwtVsScreenRender(this);
}
;
_.render_0 = function render_3(){
  !!this.vsRender && $render_8(this.vsRender);
}
;
_.update = function update_3(delta){
  var ms, versus, vsScreen;
  ms = getInstance();
  vsScreen = ms.vsScreen;
  versus = ms.versusBackground;
  !!versus && $process_0(versus);
  if (!vsScreen) {
    $launchFight(this);
    return;
  }
  if (vsScreen.time <= 0) {
    if (vsScreen.phase != 3) {
      vsScreen.lastPhase = vsScreen.phase;
      vsScreen.phase = 2;
    }
     else {
      $launchFight(this);
      return;
    }
  }
  vsScreen.phase == -1 && (vsScreen.lastPhase = vsScreen.phase , vsScreen.phase = 0);
  $process_4(vsScreen);
}
;
_.fightLaunched = false;
var Lorg_lee_mugen_core_gameSelect_GwtGameVsScreen_2_classLit = createForClass('org.lee.mugen.core.gameSelect', 'GwtGameVsScreen', 500);
function $addToPosition(this$static, dx, dy){
  var cols_0, original, rows_0, si;
  si = getInstance().selectInfo;
  rows_0 = si.rows_0;
  cols_0 = si.columns;
  original = $copy_4(this$static.position);
  this$static.position.x_0 += dx;
  this$static.position.y_0 += dy;
  this$static.position.x_0 < 0 && (this$static.position.x_0 = rows_0 - 1);
  this$static.position.y_0 < 0 && (this$static.position.y_0 = cols_0 - 1);
  this$static.position.x_0 > rows_0 - 1 && (this$static.position.x_0 = 0);
  this$static.position.y_0 > cols_0 - 1 && (this$static.position.y_0 = 0);
  (original.x_0 != this$static.position.x_0 || original.y_0 != this$static.position.y_0) && $playCursorMoveSnd(this$static);
}

function $getPlayerInfo(this$static){
  var si;
  si = getInstance().selectInfo;
  if (!si) {
    return null;
  }
  return $equals_5('1', this$static.id_0)?si.player1:si.player2;
}

function $playCursorMoveSnd(this$static){
  var pi;
  pi = $getPlayerInfo(this$static);
  if (!pi || !pi.cursor || !pi.cursor.move) {
    return;
  }
  playSnd(pi.cursor.move);
}

function $playSndFromCursorType(this$static){
  var pi, type_0;
  pi = $getPlayerInfo(this$static);
  if (!pi || !pi.cursor) {
    return;
  }
  type_0 = pi.cursor.done_0;
  !!type_0 && playSnd(type_0);
}

function GwtSelectionController(gameSelect, id_0, startcell){
  this.position = new MugenPoint;
  this.gameSelect = gameSelect;
  this.id_0 = id_0;
  this.position.x_0 = startcell.x_0;
  this.position.y_0 = startcell.y_0;
}

function decIndexOfStage(){
  var max_0, prev, select, stages;
  stages = (select = getInstance().files.gwtSelect , select?select.extraStages:null);
  if (!stages || stages.stages.array.length == 0) {
    return false;
  }
  max_0 = stages.stages.array.length - 1;
  prev = shareIndexOfStage;
  --shareIndexOfStage;
  shareIndexOfStage < 0 && (shareIndexOfStage = max_0);
  return prev != shareIndexOfStage;
}

function getCharacterBit(playerId){
  switch (playerId) {
    case 1:
      return 1;
    case 2:
      return 2;
    default:throw toJs(new IllegalStateException);
  }
}

function incIndexOfStage(){
  var max_0, prev, select, stages;
  stages = (select = getInstance().files.gwtSelect , select?select.extraStages:null);
  if (!stages || stages.stages.array.length == 0) {
    return false;
  }
  max_0 = stages.stages.array.length - 1;
  prev = shareIndexOfStage;
  ++shareIndexOfStage;
  shareIndexOfStage > max_0 && (shareIndexOfStage = 0);
  return prev != shareIndexOfStage;
}

function isAllSelectionDone(playerCount){
  var i;
  if ((shareState & 16) != 16) {
    return false;
  }
  for (i = 0; i < playerCount; i++) {
    if ((shareState & getCharacterBit(i + 1)) != getCharacterBit(i + 1)) {
      return false;
    }
  }
  return true;
}

function playSnd(type_0){
  var data_0, snd;
  if (!type_0 || !type_0.snd) {
    return;
  }
  try {
    snd = type_0.snd;
    data_0 = $getSound($getGroup(getInstance().files.snd, snd.grp), snd.num);
    !!audioPlayback && $playSfx(audioPlayback, data_0);
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (!instanceOf($e0, 6))
      throw toJs($e0);
  }
}

defineClass(119, 1, {119:1, 274:1}, GwtSelectionController);
_.action_0 = function action_2(key, isPress){
  var cmd, now_0, si, si0, si_0;
  now_0 = ($clinit_System() , fromDouble_0(Date.now()));
  if (!isPress || lt(sub_1(now_0, this.lastPress), 100)) {
    return;
  }
  $getInstanceOfGameWindow(getInstance_0());
  if (111 == key) {
    this.gameSelect.backToMenu = true;
    return;
  }
  this.lastPress = now_0;
  cmd = ($clinit_CmdProcDispatcher() , castTo($getStringValue(spriteDispatcherMap, this.id_0), 77));
  if (!cmd) {
    return;
  }
  if ((shareState & getCharacterBit(__parseAndValidateInt(this.id_0, 10))) == getCharacterBit(__parseAndValidateInt(this.id_0, 10))) {
    if ((shareState & 16) == 16) {
      (cmd.x_0 == key || cmd.y_0 == key || cmd.z_0 == key || cmd.xyz == key) && (shareState &= -17 , si = getInstance().selectInfo , !!si && !!si.cancel && playSnd(si.cancel));
    }
     else {
      if (cmd.back_0 == key) {
        if (decIndexOfStage()) {
          this.gameSelect.stageChanged = true;
          si0 = getInstance().selectInfo;
          !!si0 && !!si0.stage && !!si0.stage.move && playSnd(si0.stage.move);
        }
      }
       else if (cmd.forward_0 == key) {
        if (incIndexOfStage()) {
          this.gameSelect.stageChanged = true;
          si0 = getInstance().selectInfo;
          !!si0 && !!si0.stage && !!si0.stage.move && playSnd(si0.stage.move);
        }
      }
       else 
        cmd.a == key || cmd.b == key || cmd.c == key || cmd.abc == key?(shareState |= 16 , si_0 = getInstance().selectInfo , !!si_0 && !!si_0.stage && !!si_0.stage.done_0 && playSnd(si_0.stage.done_0) , undefined):(cmd.x_0 == key || cmd.y_0 == key || cmd.z_0 == key || cmd.xyz == key) && (shareState &= ~getCharacterBit(__parseAndValidateInt(this.id_0, 10)) , si = getInstance().selectInfo , !!si && !!si.cancel && playSnd(si.cancel));
    }
  }
   else {
    cmd.down == key?$addToPosition(this, 1, 0):cmd.up == key?$addToPosition(this, -1, 0):cmd.back_0 == key?$addToPosition(this, 0, -1):cmd.forward_0 == key?$addToPosition(this, 0, 1):(cmd.a == key || cmd.b == key || cmd.c == key || cmd.abc == key) && $getSelectedSprite(this.position) != null && (shareState |= getCharacterBit(__parseAndValidateInt(this.id_0, 10)) , $playSndFromCursorType(this));
  }
}
;
_.lastPress = 0;
var shareIndexOfStage = 0, shareState = 0;
var Lorg_lee_mugen_core_gameSelect_GwtSelectionController_2_classLit = createForClass('org.lee.mugen.core.gameSelect', 'GwtSelectionController', 119);
function $render_3(this$static){
  var bg, bg$iterator, clear_0, md, moveX, moveY, sffSprite;
  if (!this$static.background) {
    return;
  }
  md = getInstance_0();
  sffSprite = $getSpr(this$static.background.bgdef);
  !sffSprite && (sffSprite = getInstance().files.spr);
  if (!sffSprite) {
    return;
  }
  clear_0 = this$static.background.bgdef.bgclearcolor;
  if (!!clear_0 && (clear_0.r != 255 || clear_0.g != 255 || clear_0.b != 255)) {
    $setColor_2(md, clear_0.r / 255, clear_0.g / 255, clear_0.b / 255);
    $fillRect(md, 0, 0, 320, 240);
    $setColor_3(md, 1, 1, 1, 1);
  }
  $scale_0(md, this$static.stageScaleX, this$static.stageScaleY);
  moveX = this$static.cameraX;
  moveY = this$static.cameraY;
  for (bg$iterator = new ArrayList$1(this$static.background.bgs); bg$iterator.i < bg$iterator.this$01.array.length;) {
    bg = castTo($next_7(bg$iterator), 91);
    if (bg.layerno != this$static.layerDisplay) {
      continue;
    }
    try {
      bg.type_0 == ($clinit_BG$Type() , NORMAL) || bg.type_0 == NORM?drawNormal(md, sffSprite, bg, moveX, moveY, this$static.xStartForAll):bg.type_0 == ANIM?drawAnim(md, sffSprite, bg, moveX, moveY, this$static.xStartForAll):bg.type_0 == PARALLAX && drawParallax(md, sffSprite, bg, moveX, moveY, this$static.xStartForAll);
    }
     catch ($e0) {
      $e0 = toJava($e0);
      if (!instanceOf($e0, 6))
        throw toJs($e0);
    }
  }
  $scale_0(md, 1 / this$static.stageScaleX, 1 / this$static.stageScaleY);
}

function GwtStageBackgroundRender(background, cameraX, cameraY, xStartForAll){
  GwtStageBackgroundRender_0.call(this, background, cameraX, cameraY, xStartForAll, 0);
}

function GwtStageBackgroundRender_0(background, cameraX, cameraY, xStartForAll, layerDisplay){
  this.background = background;
  this.cameraX = cameraX;
  this.cameraY = cameraY;
  this.xStartForAll = xStartForAll;
  this.stageScaleX = 1;
  this.stageScaleY = 1;
  this.layerDisplay = layerDisplay;
}

function drawAnim(md, sffSprite, bg, moveX, moveY, xStartForAll){
  var air, animMng, grpno, img, imgSprSff, imgno, trans, x_0, y_0;
  animMng = $getAnimManager(bg);
  if (!animMng || !$getCurrentImageSprite(animMng)) {
    return;
  }
  air = $getCurrentImageSprite(animMng)._airData;
  grpno = air.grpNum;
  imgno = air.imgNum;
  if (!castTo($get_8(sffSprite._groupMap, valueOf_1(grpno)), 51)) {
    return;
  }
  imgSprSff = $getImgSpr(castTo($get_8(sffSprite._groupMap, valueOf_1(grpno)), 51), imgno);
  if (!imgSprSff) {
    return;
  }
  img = imgSprSff._image;
  x_0 = bg.pos.x_0 - imgSprSff._xAxis + air.xOffSet;
  y_0 = bg.pos.y_0 - imgSprSff._yAxis + air.yOffSet;
  trans = bg.trans;
  air.type_0 == ($clinit_AirData$TypeBlit() , ASD) && (trans = ($clinit_Trans() , ADD));
  drawTileXY(md, img, bg, x_0, y_0, moveX, moveY, xStartForAll, trans, air.isMirrorH, air.isMirrorV);
}

function drawImage(md, trans, img, x_0, y_0, flipH, flipV){
  var dp;
  dp = new DrawProperties_0(x_0, y_0, flipH, flipV, img);
  dp.trans = trans;
  $draw_1(md, dp);
}

function drawImageRegion(md, trans, img, xLeftDst, xRightDst, yTopDst, yBottomDst, xRightSrc, yTopSrc, yBottomSrc){
  var dp;
  dp = new DrawProperties(xLeftDst, xRightDst, yTopDst, yBottomDst, 0, xRightSrc, yTopSrc, yBottomSrc, img);
  dp.trans = trans;
  $draw_1(md, dp);
}

function drawNormal(md, sffSprite, bg, moveX, moveY, xStartForAll){
  var grpno, img, imgSprSff, imgno, x_0, y_0;
  grpno = bg.spriteno.spritegrp;
  imgno = bg.spriteno.spriteno;
  if (!castTo($get_8(sffSprite._groupMap, valueOf_1(grpno)), 51)) {
    return;
  }
  imgSprSff = $getImgSpr(castTo($get_8(sffSprite._groupMap, valueOf_1(grpno)), 51), imgno);
  if (!imgSprSff) {
    return;
  }
  img = imgSprSff._image;
  x_0 = bg.pos.x_0 - imgSprSff._xAxis;
  y_0 = bg.pos.y_0 - imgSprSff._yAxis;
  drawTileXY(md, img, bg, x_0, y_0, moveX, moveY, xStartForAll, bg.trans, false, false);
}

function drawParallax(md, sffSprite, bg, moveX, moveY, xStartForAll){
  var deltaX, grpno, img, imgH, imgSprSff, imgW, imgno, scaleX, startX, stripW, v, x_0, x1, y_0, y2;
  grpno = bg.spriteno.spritegrp;
  imgno = bg.spriteno.spriteno;
  if (!castTo($get_8(sffSprite._groupMap, valueOf_1(grpno)), 51)) {
    return;
  }
  imgSprSff = $getImgSpr(castTo($get_8(sffSprite._groupMap, valueOf_1(grpno)), 51), imgno);
  if (!imgSprSff) {
    return;
  }
  img = imgSprSff._image;
  x_0 = bg.pos.x_0 - imgSprSff._xAxis;
  y_0 = bg.pos.y_0 - imgSprSff._yAxis;
  imgW = img.width_0;
  imgH = img.height_0;
  if (imgW <= 0 || imgH <= 0) {
    return;
  }
  for (v = 0; v < imgH; ++v) {
    scaleX = bg.xscale.x_0 + v * ((bg.xscale.y_0 - bg.xscale.x_0) / imgH);
    deltaX = scaleX * bg.delta.x_0;
    x1 = x_0 + deltaX * moveX + xStartForAll;
    y2 = y_0 + moveY * bg.delta.y_0 + v;
    if (!bg.enable_0) {
      continue;
    }
    stripW = $wnd.Math.max(1, imgW * $wnd.Math.abs(scaleX));
    startX = x1;
    while (startX > 0) {
      startX -= stripW;
    }
    while (startX < 320) {
      drawImageRegion(md, bg.trans, img, startX, startX + stripW, y2, y2 + 1, imgW, v, v + 1);
      startX += stripW;
    }
  }
}

function drawTileXY(md, img, bg, x_0, y_0, moveX, moveY, xStartForAll, trans, flipH, flipV){
  var deltaX, deltaY, startPosX, startPosX0, tileSpacingX, tileSpacingY, xTile, yDraw;
  if (!bg.enable_0) {
    return;
  }
  xTile = round_int(bg.tile.x_0);
  tileSpacingX = img.width_0;
  tileSpacingY = img.height_0;
  tileSpacingX < 1 && (tileSpacingX = $wnd.Math.max(1, img.width_0));
  tileSpacingY < 1 && (tileSpacingY = $wnd.Math.max(1, img.height_0));
  deltaY = bg.delta.y_0;
  deltaX = bg.delta.x_0;
  if (xTile == 0) {
    startPosX0 = x_0 + moveX * deltaX + xStartForAll;
    yDraw = y_0 + moveY * deltaY;
    bg.tile.y_0 > 0 && (yDraw = (y_0 + moveY * deltaY) % tileSpacingY);
    drawImage(md, trans, img, startPosX0, yDraw, flipH, flipV);
    drawTileY(md, img, bg, startPosX0, yDraw, moveY, tileSpacingY, deltaY, trans, flipH, flipV);
    return;
  }
  startPosX = (x_0 + moveX * deltaX + xStartForAll) % tileSpacingX;
  startPosX > 0 && (startPosX -= tileSpacingX);
  while (startPosX < 320) {
    yDraw = y_0 + moveY * deltaY;
    bg.tile.y_0 > 0 && (yDraw = (y_0 + moveY * deltaY) % tileSpacingY);
    drawImage(md, trans, img, startPosX, yDraw, flipH, flipV);
    drawTileY(md, img, bg, startPosX, yDraw, moveY, tileSpacingY, deltaY, trans, flipH, flipV);
    startPosX += tileSpacingX;
  }
}

function drawTileY(md, img, bg, x_0, y_0, moveY, tileSpacingY, deltaY, trans, flipH, flipV){
  var remain, startPosY, yTile;
  yTile = round_int(bg.tile.y_0);
  if (yTile == 1) {
    startPosY = y_0 + tileSpacingY;
    while (startPosY < 240) {
      drawImage(md, trans, img, x_0, startPosY, flipH, flipV);
      startPosY += tileSpacingY;
    }
    startPosY = y_0 - tileSpacingY;
    while (startPosY + img.height_0 > 0) {
      drawImage(md, trans, img, x_0, startPosY, flipH, flipV);
      startPosY -= tileSpacingY;
    }
  }
   else if (yTile > 1) {
    startPosY = (y_0 + moveY) % tileSpacingY;
    remain = yTile - 1;
    while (startPosY < 240 && remain > 0) {
      drawImage(md, trans, img, x_0, startPosY + tileSpacingY * deltaY, flipH, flipV);
      startPosY += img.height_0;
      --remain;
    }
  }
}

defineClass(156, 1, {}, GwtStageBackgroundRender, GwtStageBackgroundRender_0);
_.cameraX = 0;
_.cameraY = 0;
_.layerDisplay = 0;
_.stageScaleX = 0;
_.stageScaleY = 0;
_.xStartForAll = 0;
var Lorg_lee_mugen_core_renderer_game_GwtStageBackgroundRender_2_classLit = createForClass('org.lee.mugen.core.renderer.game', 'GwtStageBackgroundRender', 156);
function $getImageSFF(type_0){
  var spr;
  if (instanceOf(type_0.type_0, 102)) {
    castTo(type_0.type_0, 102);
    return null.$_nullMethod() , null;
  }
   else if (instanceOf(type_0.type_0, 133)) {
    spr = castTo(type_0.type_0, 133);
    return $getImage(spr);
  }
  return null;
}

function $getSpriteDrawProperties(type_0){
  if (instanceOf(type_0.type_0, 102)) {
    castTo(type_0.type_0, 102);
    return null.$_nullField;
  }
  return null;
}

function $render_4(md, pos, type_0){
  var drawProperties, font, fontSens, sff, text_0, xScale, yScale;
  if (type_0.layerno != 0)
    return;
  !pos && (pos = new MugenPoint);
  xScale = 1;
  yScale = 1;
  if (type_0.scale.x_0 != 1 || type_0.scale.y_0 != 1) {
    xScale = type_0.scale.x_0;
    yScale = type_0.scale.y_0;
  }
  if (instanceOf(type_0.type_0, 102) || instanceOf(type_0.type_0, 133)) {
    sff = $getImageSFF(type_0);
    $getSpriteDrawProperties(type_0);
    if (!sff) {
      log_0('sff type %s is null', stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_Object_2_classLit, 1), $intern_9, 1, 5, [$getSimpleName(type_0.type_0.___clazz)]));
      return;
    }
    if (type_0.facing == -1) {
      drawProperties = new DrawProperties_0(pos.x_0 - sff._image.width_0 + sff._xAxis + type_0.offset.x_0, pos.y_0 - sff._yAxis + type_0.offset.y_0, true, false, sff._image);
      drawProperties.xScaleFactor = xScale;
      drawProperties.yScaleFactor = yScale;
      $setAlpha_0(drawProperties, type_0.alpha_0);
      $draw_1(md, drawProperties);
    }
     else if (type_0.facing == 1) {
      drawProperties = new DrawProperties_0(pos.x_0 - sff._xAxis + type_0.offset.x_0, pos.y_0 - sff._yAxis + type_0.offset.y_0, false, false, sff._image);
      drawProperties.xScaleFactor = xScale;
      drawProperties.yScaleFactor = yScale;
      $setAlpha_0(drawProperties, type_0.alpha_0);
      $draw_1(md, drawProperties);
    }
  }
   else if (instanceOf(type_0.type_0, 67)) {
    font = castTo(type_0.type_0, 67);
    text_0 = font.text_0;
    fontSens = valueOf_1(font.alignmt.code_0).value_0;
    xScale = type_0.scale.x_0;
    yScale = type_0.scale.y_0;
    $scale_0(md, xScale, yScale);
    $draw_2(castTo($get_8($getFont(font), valueOf_1(font.fontno)), 114), font.fontbank, round_int(pos.x_0 / xScale) + type_0.offset.x_0, round_int(pos.y_0 / yScale) + type_0.offset.y_0, md, text_0, fontSens, type_0.alpha_0);
    $scale_0(md, 1 / xScale, 1 / yScale);
  }
}

defineClass(544, 1, {});
var Lorg_lee_mugen_core_renderer_game_fight_BaseRender_2_classLit = createForClass('org.lee.mugen.core.renderer.game.fight', 'BaseRender', 544);
function $drawBigPortrait(md, characters, characterId, player){
  var big_0, face_0, facing, pos, scale;
  if (!player || !player.face_0) {
    return;
  }
  big_0 = $getBigPortrait(characters, characterId);
  if (!big_0) {
    return;
  }
  face_0 = player.face_0;
  pos = face_0.offset?$copy_4(face_0.offset):new MugenPoint;
  facing = face_0.facing == -1;
  scale = face_0.scale?face_0.scale:new PointF_0(1, 1);
  facing && (pos.x_0 = round_int(pos.x_0 - big_0.width_0 * scale.x_0));
  drawImage_0(md, pos, big_0, facing, scale.x_0, scale.y_0);
}

function $drawName(md, characters, characterId, player){
  var font, name_0, nameType;
  if (!player || !player.name_0) {
    return;
  }
  nameType = $copy_3(player.name_0);
  if (!instanceOf(nameType.type_0, 67)) {
    return;
  }
  font = castTo(nameType.type_0, 67);
  if (font.fontno < 0) {
    return;
  }
  $setText(font, (name_0 = castToString($getStringValue(characters.displayNames, characterId)) , name_0 != null?name_0:characterId));
  $render_4(md, null, nameType);
}

function $render_5(this$static){
  var c, c0, cell, cellSize, character, characterId, characters, col, cursor, gridCell, gridPos, gwtSelect, iterCharacter, md, ms, p, player, portrait, r, r0, rgb, row, sc, sc$array, sc$array0, sc$index, sc$index0, sc$max, sc$max0, selectInfo, spacing, spr;
  md = getInstance_0();
  ms = getInstance();
  selectInfo = ms.selectInfo;
  if (!selectInfo) {
    return;
  }
  rgb = new RGB_0;
  $setColor_3(md, rgb.r, rgb.g, rgb.b, rgb.a);
  $fillRect(md, 0, 0, 320, 240);
  $render_6(this$static.backgroundRender);
  $setAlpha_1(md, 1);
  gwtSelect = ms.files.gwtSelect;
  if (!gwtSelect) {
    return;
  }
  characters = gwtSelect.characters;
  row = selectInfo.rows_0;
  col = selectInfo.columns;
  cell = selectInfo.cell;
  if (!cell || !selectInfo.pos || !cell.size_0) {
    return;
  }
  cellSize = cell.size_0;
  spacing = cell.spacing;
  gridPos = selectInfo.pos;
  for (c0 = 0; c0 < col; c0++) {
    for (r0 = 0; r0 < row; r0++) {
      p = new MugenPoint_0(gridPos.x_0 + c0 * (cellSize.x_0 + spacing), gridPos.y_0 + r0 * (cellSize.y_0 + spacing));
      !!cell.bg && $render_4(md, p, cell.bg);
    }
  }
  iterCharacter = ($clinit_Collections() , new Collections$UnmodifiableCollectionIterator(new ArrayList$1((new Collections$UnmodifiableRandomAccessList(gwtSelect.charactersOrder)).coll)));
  for (r = 0; r < row; r++) {
    for (c = 0; c < col; c++) {
      p = new MugenPoint_0(gridPos.x_0 + c * (cellSize.x_0 + spacing), gridPos.y_0 + r * (cellSize.y_0 + spacing));
      if (iterCharacter.it.hasNext_0()) {
        character = castToString(iterCharacter.it.next_1());
        portrait = (spr = $getPortraitSprite(characters, character) , spr?spr._image:null);
        !!portrait && drawImage_0(md, p, portrait, false, 1, 1);
      }
    }
  }
  for (sc$array0 = this$static.controllers_0 , sc$index0 = 0 , sc$max0 = sc$array0.length; sc$index0 < sc$max0; ++sc$index0) {
    sc = sc$array0[sc$index0];
    player = $getPlayerInfo(sc);
    if (!player || !player.cursor) {
      continue;
    }
    gridCell = sc.position;
    p = cellPos(gridPos, cellSize, spacing, gridCell.x_0, gridCell.y_0);
    cursor = (shareState & getCharacterBit(__parseAndValidateInt(sc.id_0, 10))) == getCharacterBit(__parseAndValidateInt(sc.id_0, 10))?player.cursor.done_0:player.cursor.active;
    !!cursor && $render_4(md, p, cursor);
  }
  for (sc$array = this$static.controllers_0 , sc$index = 0 , sc$max = sc$array.length; sc$index < sc$max; ++sc$index) {
    sc = sc$array[sc$index];
    characterId = $getCharacterAt(gwtSelect, sc.position.x_0, sc.position.y_0, col);
    if (characterId != null) {
      $drawBigPortrait(md, characters, characterId, $getPlayerInfo(sc));
      $drawName(md, characters, characterId, $getPlayerInfo(sc));
    }
  }
  (shareState & getCharacterBit(1)) == getCharacterBit(1) && (shareState & getCharacterBit(2)) == getCharacterBit(2) && $renderStageSelect(this$static, md, selectInfo);
}

function $renderStageSelect(this$static, md, selectInfo){
  var camY, camera, clip, done, font, height, stageBg, stageDisplay, width_0, x_0, xCoef, xStart, y_0, yCoef;
  if (!selectInfo.stage) {
    return;
  }
  stageDisplay = selectInfo.stagedisplay;
  stageBg = $getStagePreviewBackground(this$static.gameSelect);
  if (!!stageDisplay && stageDisplay.enable_0 && !!stageBg && !!stageDisplay.rectangle && !!stageDisplay.scale && !!stageDisplay.pos) {
    width_0 = stageDisplay.rectangle.width_0;
    height = stageDisplay.rectangle.height_0;
    xCoef = stageDisplay.scale.x_0;
    yCoef = stageDisplay.scale.y_0;
    x_0 = stageDisplay.pos.x_0;
    y_0 = stageDisplay.pos.y_0;
    clip = new Rectangle_1(x_0, y_0, x_0 + round_int(width_0 * xCoef), y_0 + round_int(height * yCoef));
    camera = stageDisplay.camera;
    xStart = camera?camera.x_0:0;
    camY = camera?camera.y_0:0;
    $setClip(md, clip);
    md.uniformScaleAboutClipCorner = true;
    $scale_0(md, xCoef, yCoef);
    $render_3(new GwtStageBackgroundRender(stageBg, 0, camY, xStart));
    $scale_0(md, 1 / xCoef, 1 / yCoef);
    $setClip(md, null);
    md.uniformScaleAboutClipCorner = false;
  }
  if (selectInfo.stage.done_0) {
    done = $copy_3(selectInfo.stage.done_0);
    if (instanceOf(done.type_0, 67)) {
      font = castTo(done.type_0, 67);
      $setText(font, $getCurrentStageDisplayName());
      $render_4(md, selectInfo.stage.pos, done);
    }
  }
}

function $setControllers(this$static, controllers_0){
  this$static.controllers_0 = controllers_0 != null?controllers_0:initUnidimensionalArray(Lorg_lee_mugen_core_gameSelect_GwtSelectionController_2_classLit, $intern_9, 119, 0, 0, 1);
}

function GwtSelectRender(gameSelect){
  this.controllers_0 = initUnidimensionalArray(Lorg_lee_mugen_core_gameSelect_GwtSelectionController_2_classLit, $intern_9, 119, 0, 0, 1);
  this.gameSelect = gameSelect;
  this.backgroundRender = new BackgroundRender(getInstance().selectBackground);
}

function cellPos(gridPos, cellSize, spacing, r, c){
  return new MugenPoint_0(gridPos.x_0 + c * (cellSize.x_0 + spacing), gridPos.y_0 + r * (cellSize.y_0 + spacing));
}

function drawImage_0(md, p, ic, flipH, scaleX, scaleY){
  var dp;
  dp = new DrawProperties_0(p.x_0, p.y_0, flipH, false, ic);
  dp.xScaleFactor = scaleX;
  dp.yScaleFactor = scaleY;
  $draw_1(md, dp);
}

defineClass(499, 544, {}, GwtSelectRender);
var Lorg_lee_mugen_core_renderer_game_select_GwtSelectRender_2_classLit = createForClass('org.lee.mugen.core.renderer.game.select', 'GwtSelectRender', 499);
function $drawImage_0(trans, img, xl, yt, xr, yb, yTopSrc, xrSrc, yBottomSrc){
  var dp;
  $clinit_GameFight();
  dp = new DrawProperties(xl, xr, yt, yb, 0, xrSrc, yTopSrc, yBottomSrc, img);
  dp.trans = trans;
  $draw_1(getInstance_0(), dp);
}

function $drawImage_1(trans, img, x_0, y_0, isHFlip, isVFlip){
  var dp;
  $clinit_GameFight();
  dp = new DrawProperties_0(x_0, y_0, isHFlip, isVFlip, img);
  dp.trans = trans;
  $draw_1(getInstance_0(), dp);
}

function $drawTileXY(img, bg, x_0, y_0, moveX, moveY, trans, isHFlip, isVFlip){
  var deltaX, deltaY, startPosX, tileSpacingX, tileSpacingY, xTile, yDraw;
  if (!bg.enable_0)
    return;
  xTile = round_int(bg.tile.x_0);
  startPosX = x_0;
  tileSpacingX = img.width_0;
  tileSpacingY = img.height_0;
  deltaY = bg.delta.y_0;
  deltaX = bg.delta.x_0;
  if (xTile == 0) {
    startPosX = x_0 + moveX * deltaX;
    yDraw = y_0 + moveY * deltaY;
    bg.tile.y_0 > 0 && (yDraw = (y_0 + moveY * deltaY) % tileSpacingY);
    $drawImage_1(trans, img, startPosX, yDraw, isHFlip, isVFlip);
    $drawTileY(img, bg, startPosX, yDraw + img.height_0, moveY, trans, isHFlip, isVFlip);
    startPosX += tileSpacingX;
  }
   else if (xTile > 1) {
    startPosX = (x_0 + moveX * deltaX) % img.width_0;
    while (startPosX < 320) {
      yDraw = y_0 + moveY * deltaY;
      bg.tile.y_0 > 0 && (yDraw = (y_0 + moveY * deltaY) % tileSpacingY);
      $drawImage_1(trans, img, startPosX, yDraw, isHFlip, isVFlip);
      $drawTileY(img, bg, startPosX, yDraw, moveY, trans, isHFlip, isVFlip);
      startPosX += tileSpacingX;
    }
  }
   else if (bg.tile.x_0 == 1) {
    startPosX = (x_0 + moveX * deltaX) % tileSpacingX;
    while (startPosX < 320) {
      yDraw = y_0 + moveY * deltaY;
      bg.tile.y_0 > 0 && (yDraw = (y_0 + moveY * deltaY) % tileSpacingY);
      $drawImage_1(trans, img, startPosX, yDraw, isHFlip, isVFlip);
      $drawTileY(img, bg, startPosX, yDraw, moveY, trans, isHFlip, isVFlip);
      startPosX += tileSpacingX;
    }
    startPosX = (x_0 + moveX * deltaX) % tileSpacingX;
    startPosX -= tileSpacingX;
    while (startPosX + img.width_0 > 0) {
      yDraw = y_0 + moveY * deltaY;
      bg.tile.y_0 > 0 && (yDraw = (y_0 + moveY * bg.delta.y_0) % tileSpacingY);
      $drawImage_1(trans, img, startPosX, yDraw, isHFlip, isVFlip);
      $drawTileY(img, bg, startPosX, yDraw, moveY, trans, isHFlip, isVFlip);
      startPosX -= tileSpacingX;
    }
  }
}

function $drawTileY(img, bg, x_0, y_0, moveY, trans, isHFlip, isVFlip){
  var deltaY, startPosY, tileSpacingY, tilespacingY, yTile;
  yTile = round_int(bg.tile.y_0);
  startPosY = y_0;
  deltaY = bg.delta.y_0;
  if (yTile == 0)
  ;
  else if (yTile == 1) {
    tileSpacingY = img.height_0;
    startPosY = y_0 + tileSpacingY;
    while (startPosY < 240) {
      $drawImage_1(trans, img, x_0, startPosY, isHFlip, isVFlip);
      startPosY += tileSpacingY;
    }
    startPosY = y_0 - tileSpacingY;
    while (startPosY + img.height_0 > 0) {
      $drawImage_1(trans, img, x_0, startPosY, isHFlip, isVFlip);
      startPosY -= tileSpacingY;
    }
  }
   else {
    tilespacingY = img.height_0;
    startPosY = (y_0 + moveY) % tilespacingY;
    --yTile;
    while (startPosY < 240 && yTile > 0) {
      $drawImage_1(trans, img, x_0, startPosY + tilespacingY * deltaY, isHFlip, isVFlip);
      startPosY += img.height_0;
      --yTile;
    }
  }
}

function $render_6(this$static){
  var air, animMng, bg, bg$iterator, deltaY, e, grpno, img, imgSprSff, imgno, isHFlip, isVFlip, moveX, moveY, percentScaleY, r, rgb, sffSprite, startPosX, trans, v, vOriginal, width_0, x_0, x1, y_0, y2, yAdv;
  sffSprite = $getSpr(this$static.background.bgdef);
  !sffSprite && (sffSprite = getInstance().files.spr);
  if (this$static.background.bgdef.bgclearcolor) {
    rgb = this$static.background.bgdef.bgclearcolor;
    $setColor_2(getInstance_0(), rgb.r, rgb.g, rgb.b);
    $fillRect(getInstance_0(), 0, 0, 640, 480);
  }
  for (bg$iterator = new ArrayList$1(this$static.background.bgs); bg$iterator.i < bg$iterator.this$01.array.length;) {
    bg = castTo($next_7(bg$iterator), 91);
    if (bg.layerno != 0)
      continue;
    moveX = getInstance().titleInfo.menu.pos.x_0;
    moveY = 0;
    r = bg.window_0;
    $setClip(getInstance_0(), r);
    try {
      if (bg.type_0 == ($clinit_BG$Type() , NORMAL) || bg.type_0 == NORM) {
        grpno = 0;
        try {
          grpno = bg.spriteno.spritegrp;
        }
         catch ($e0) {
          $e0 = toJava($e0);
          if (instanceOf($e0, 6)) {
            e = $e0;
            $printStackTraceImpl(e, ($clinit_System() , err_0), '');
          }
           else 
            throw toJs($e0);
        }
        imgno = bg.spriteno.spriteno;
        imgSprSff = $getImgSpr(castTo($get_8(sffSprite._groupMap, valueOf_1(grpno)), 51), imgno);
        img = imgSprSff._image;
        x_0 = bg.pos.x_0 - imgSprSff._xAxis;
        y_0 = bg.pos.y_0 - imgSprSff._yAxis;
        $drawTileXY(img, bg, x_0, y_0, moveX, moveY, bg.trans, false, false);
      }
       else if (bg.type_0 == ANIM) {
        animMng = $getAnimManager(bg);
        if (!$getCurrentImageSprite(animMng))
          continue;
        air = $getCurrentImageSprite(animMng)._airData;
        grpno = air.grpNum;
        imgno = air.imgNum;
        if (!castTo($get_8(sffSprite._groupMap, valueOf_1(grpno)), 51))
          continue;
        imgSprSff = $getImgSpr(castTo($get_8(sffSprite._groupMap, valueOf_1(grpno)), 51), imgno);
        if (!imgSprSff)
          continue;
        img = imgSprSff._image;
        isVFlip = air.isMirrorV;
        isHFlip = air.isMirrorH;
        x_0 = bg.pos.x_0 - imgSprSff._xAxis + air.xOffSet;
        y_0 = bg.pos.y_0 - imgSprSff._yAxis + air.yOffSet;
        trans = bg.trans;
        trans = air.type_0 == ($clinit_AirData$TypeBlit() , ASD)?($clinit_Trans() , ADD):trans;
        $drawTileXY(img, bg, x_0, y_0, moveX, moveY, trans, isHFlip, isVFlip);
      }
       else if (bg.type_0 == ANIM && false) {
        bg = throwClassCastExceptionUnlessNull($get_8(this$static.background.bgCtrlDefMap, bg.id_0)).$_nullField.$_nullMethod();
        if (!bg.visible)
          continue;
        animMng = $getAnimManager(bg);
        if (!$getCurrentImageSprite(animMng))
          continue;
        air = $getCurrentImageSprite(animMng)._airData;
        grpno = air.grpNum;
        imgno = air.imgNum;
        if (!castTo($get_8(sffSprite._groupMap, valueOf_1(grpno)), 51) || !$getImgSpr(castTo($get_8(sffSprite._groupMap, valueOf_1(grpno)), 51), imgno))
          continue;
        imgSprSff = $getImgSpr(castTo($get_8(sffSprite._groupMap, valueOf_1(grpno)), 51), imgno);
        img = imgSprSff._image;
        x_0 = bg.pos.x_0 - imgSprSff._xAxis + air.xOffSet;
        y_0 = bg.pos.y_0 - imgSprSff._yAxis + air.yOffSet;
        isVFlip = air.isMirrorV;
        isHFlip = air.isMirrorH;
        trans = bg.trans;
        trans = air.type_0 == ($clinit_AirData$TypeBlit() , ASD)?($clinit_Trans() , ADD):trans;
        $drawTileXY(img, bg, x_0, y_0, moveX, moveY, trans, isHFlip, isVFlip);
      }
       else if (bg.type_0 == PARALLAX) {
        grpno = bg.spriteno.spritegrp;
        imgno = bg.spriteno.spriteno;
        imgSprSff = $getImgSpr(castTo($get_8(sffSprite._groupMap, valueOf_1(grpno)), 51), imgno);
        img = imgSprSff._image;
        x_0 = getInstance().titleInfo.menu.pos.x_0 - imgSprSff._xAxis;
        y_0 = bg.pos.y_0 - imgSprSff._yAxis;
        moveX = bg.pos.x_0;
        moveY = 0;
        percentScaleY = 100 / bg.yscalestart;
        yAdv = percentScaleY;
        vOriginal = 0;
        for (v = 0; v < img.height_0 * percentScaleY; v += yAdv) {
          if (!bg.enable_0)
            continue;
          deltaY = bg.xscale.x_0 + v * ((bg.xscale.y_0 - bg.xscale.x_0) / img.height_0);
          deltaY = deltaY * bg.delta.x_0;
          x1 = x_0 + deltaY * moveX;
          y2 = y_0 + moveY * bg.delta.y_0 + v;
          width_0 = img.width_0 * deltaY;
          startPosX = x1 % width_0 * 2;
          while (startPosX + width_0 > 0) {
            $drawImage_0(bg.trans, img, startPosX, y2, startPosX + width_0, round_int(y2 + yAdv), vOriginal, round_int(width_0), vOriginal + 1);
            startPosX -= width_0;
          }
          startPosX = x1 % width_0 * 2;
          while (startPosX < 320) {
            $drawImage_0(bg.trans, img, startPosX, y2, startPosX + width_0, y2 + yAdv, vOriginal, round_int(width_0), vOriginal + 1);
            startPosX += width_0;
          }
          ++vOriginal;
        }
      }
    }
     catch ($e1) {
      $e1 = toJava($e1);
      if (instanceOf($e1, 6)) {
        e = $e1;
        $printStackTraceImpl(e, ($clinit_System() , err_0), '');
        continue;
      }
       else 
        throw toJs($e1);
    }
  }
  $setClip(getInstance_0(), null);
}

function BackgroundRender(background){
  this.background = background;
}

defineClass(183, 1, {}, BackgroundRender);
var Lorg_lee_mugen_core_renderer_game_system_BackgroundRender_2_classLit = createForClass('org.lee.mugen.core.renderer.game.system', 'BackgroundRender', 183);
function $render_7(this$static){
  var add_0, alpha_0, base, box, coef, count, fontType, i, itemName, md, menu, ms, pos, strs, titleInfo;
  md = getInstance_0();
  ms = getInstance();
  titleInfo = ms.titleInfo;
  menu = titleInfo.menu;
  if (titleInfo.phase == 0) {
    alpha_0 = ms.titleInfo.fadein.time / ms.titleInfo.fadein.originalTime;
    $setAlpha_1(md, 1 - alpha_0);
  }
   else if (titleInfo.phase == 2) {
    alpha_0 = ms.titleInfo.fadeout.time / ms.titleInfo.fadeout.originalTime;
    $setAlpha_1(md, alpha_0);
  }
   else 
    titleInfo.phase == 1?$setAlpha_1(md, 1):titleInfo.phase == -1?$setAlpha_1(md, 1):titleInfo.phase == 3 && $setAlpha_1(md, 0);
  pos = $copy_4(menu.pos);
  itemName = menu.itemname;
  strs = itemName.list;
  $render_6(this$static.br);
  count = menu.window$visibleitems;
  base = this$static.gameMenu.lastStartIndex;
  add_0 = this$static.gameMenu.incIndexForMove;
  box = $copy_5(menu.boxcursor$coords);
  $setClip(getInstance_0(), new Rectangle_1(pos.x_0 + box.x1, pos.y_0 + box.y1, pos.x_0 + box.x2, pos.y_0 + (-box.y1 + box.y2) * count));
  pos.y_0 += add_0;
  pos = new MugenPoint;
  pos.y_0 += menu.window$margins$y.x_0;
  pos.x_0 = -box.x1;
  pos.y_0 += add_0;
  for (i = base; i < strs.length; i++) {
    if (i == itemName.currentIndex) {
      if (menu.boxcursor$visible != 0) {
        coef = $wnd.Math.abs($wnd.Math.sin(this$static.gameMenu.time / 10));
        $setColor_3(md, $intern_53, $intern_53, $intern_53, coef - 0.20000000298023224);
        $fillRect(md, pos.x_0 + box.x1, pos.y_0 + box.y1, -box.x1 + box.x2, -box.y1 + box.y2);
        $setColor_3(md, 1, 1, 1, 1);
      }
      fontType = castTo(menu.item_0.active.type_0, 67);
      fontType.text_0 = strs[i];
      $render_4(md, pos, menu.item_0.active);
      pos.x_0 += menu.item_0.spacing.x_0;
      pos.y_0 += menu.item_0.spacing.y_0;
    }
     else {
      fontType = castTo(menu.item_0.type_0, 67);
      fontType.text_0 = strs[i];
      $render_4(md, pos, menu.item_0);
      pos.x_0 += menu.item_0.spacing.x_0;
      pos.y_0 += menu.item_0.spacing.y_0;
    }
  }
  $setClip(md, null);
}

function TitleInfoRender(gameMenu){
  this.br = new BackgroundRender(getInstance().titleBackground);
  this.gameMenu = gameMenu;
}

defineClass(422, 544, {}, TitleInfoRender);
var Lorg_lee_mugen_core_renderer_game_system_TitleInfoRender_2_classLit = createForClass('org.lee.mugen.core.renderer.game.system', 'TitleInfoRender', 422);
function $drawPlayer(md, characters, slot, charId){
  var copy, dp, facing, font, fx, fy, name_0, nameType, portrait, pos, scale;
  if (!slot || charId == null) {
    return;
  }
  portrait = $getBigPortrait(characters, charId);
  if (!portrait) {
    return;
  }
  pos = $copy_4(slot.pos);
  facing = slot.facing == -1;
  fy = 140 / portrait.height_0;
  fx = 120 / portrait.width_0;
  scale = $copy_6(slot.scale);
  $setX(scale, scale.x_0 * fx);
  $setY(scale, scale.y_0 * fy);
  facing && (pos.x_0 = round_int(pos.x_0 - portrait.width_0 * scale.x_0));
  dp = new DrawProperties_0(pos.x_0, pos.y_0, facing, false, portrait);
  $setXScaleFactor(dp, scale.x_0);
  $setYScaleFactor(dp, scale.y_0);
  $draw_1(md, dp);
  nameType = slot.name_0;
  if (nameType) {
    copy = $copy_3(nameType);
    if (instanceOf(copy.type_0, 67)) {
      font = castTo(copy.type_0, 67);
      $setText(font, (name_0 = castToString($getStringValue(characters.displayNames, charId)) , name_0 != null?name_0:charId));
      $render_4(md, copy.pos, copy);
    }
  }
}

function $render_8(this$static){
  var alpha_0, characters, md, ms, vsScreen;
  md = getInstance_0();
  ms = getInstance();
  vsScreen = ms.vsScreen;
  if (!vsScreen) {
    return;
  }
  if (vsScreen.phase == 0 && !!vsScreen.fadein) {
    alpha_0 = vsScreen.fadein.time / vsScreen.fadein.originalTime;
    $setAlpha_1(md, 1 - alpha_0);
  }
   else if (vsScreen.phase == 2 && !!vsScreen.fadeout) {
    alpha_0 = vsScreen.fadeout.time / vsScreen.fadeout.originalTime;
    $setAlpha_1(md, alpha_0);
  }
   else 
    vsScreen.phase == 3?$setAlpha_1(md, 0):$setAlpha_1(md, 1);
  $render_6(this$static.backgroundRender);
  characters = ms.files.gwtSelect.characters;
  if (!characters) {
    return;
  }
  $drawPlayer(md, characters, vsScreen.p1, $getPlayerName(this$static.gameVsScreen, 0));
  $drawPlayer(md, characters, vsScreen.p2, $getPlayerName(this$static.gameVsScreen, 1));
}

function GwtVsScreenRender(gameVsScreen){
  this.gameVsScreen = gameVsScreen;
  this.backgroundRender = new BackgroundRender(getInstance().versusBackground);
}

defineClass(501, 544, {}, GwtVsScreenRender);
var Lorg_lee_mugen_core_renderer_game_vsScreen_GwtVsScreenRender_2_classLit = createForClass('org.lee.mugen.core.renderer.game.vsScreen', 'GwtVsScreenRender', 501);
function installAudioPlayback(playback){
  audioPlayback = playback;
}

var audioPlayback;
function playMusic(path){
  !!audioPlayback && $playMusic(audioPlayback, path);
}

function appendReader(reader, out){
  var br, line;
  br = new BufferedReader(reader);
  while ((line = $readLine(br)) != null) {
    $append((out.string += '' + line , out), 10);
  }
}

function getMugenSingleCmd(scm){
  var cmd, cmdType, cmdType$array, cmdType$index, cmdType$max, cmdTypes, dd, dd$array, dd$index, dd$max, isPress, k, k$array, k$index, k$max, keys_0, keysDDir, m, modifiers, selectedKey, timeIfReleased;
  cmdTypes = ($clinit_MugenSingleCmd$CommandType() , stampJavaTypeInfo(getClassLiteralForArray(Lorg_lee_mugen_input_MugenSingleCmd$CommandType_2_classLit, 1), $intern_54, 69, 0, [PRESS_0, HOLD, RELEASED, DIRECTION, SIMULTANEOUS, NO_OTHER_KEY_BEFORE]));
  isPress = true;
  timeIfReleased = 1;
  modifiers = new ArrayList;
  for (cmdType$array = cmdTypes , cmdType$index = 0 , cmdType$max = cmdType$array.length; cmdType$index < cmdType$max; ++cmdType$index) {
    cmdType = cmdType$array[cmdType$index];
    if (cmdType.desc.length > 0 && $indexOf_0(scm, NO_OTHER_KEY_BEFORE.desc) != -1 && NO_OTHER_KEY_BEFORE == cmdType) {
      push_1(modifiers.array, cmdType);
    }
     else if (cmdType.desc.length > 0 && $indexOf_0(scm, DIRECTION.desc) != -1 && DIRECTION == cmdType) {
      push_1(modifiers.array, cmdType);
    }
     else if (cmdType.desc.length > 0 && $indexOf_0(scm, cmdType.desc) != -1) {
      isPress = false;
      push_1(modifiers.array, cmdType);
      m = new Matcher(new Pattern('([0-9].)'), scm);
      $test(m.regExp, m.input_0) && (timeIfReleased = __parseAndValidateInt(m.matchResult[1], 10));
    }
  }
  keysDDir = stampJavaTypeInfo(getClassLiteralForArray(Lorg_lee_mugen_input_Key_2_classLit, 1), $intern_55, 33, 0, [($clinit_Key() , DB), DF, UF, UB]);
  selectedKey = null;
  for (dd$array = keysDDir , dd$index = 0 , dd$max = dd$array.length; dd$index < dd$max; ++dd$index) {
    dd = dd$array[dd$index];
    $indexOf_0(scm, dd.name_0 != null?dd.name_0:'' + dd.ordinal) != -1 && (selectedKey = dd);
  }
  keys_0 = new ArrayList;
  if (!selectedKey) {
    for (k$array = values_16() , k$index = 0 , k$max = k$array.length; k$index < k$max; ++k$index) {
      k = k$array[k$index];
      $indexOf_0(scm, k.name_0 != null?k.name_0:'' + k.ordinal) != -1 && (push_1(keys_0.array, k) , true);
    }
  }
   else {
    push_1(keys_0.array, selectedKey);
  }
  isPress && $add_8(modifiers, PRESS_0);
  cmd = new MugenSingleCmd(castTo($toArray_0(keys_0, initUnidimensionalArray(Lorg_lee_mugen_input_Key_2_classLit, $intern_55, 33, keys_0.array.length, 0, 1)), 159), castTo($toArray_0(modifiers, initUnidimensionalArray(Lorg_lee_mugen_input_MugenSingleCmd$CommandType_2_classLit, $intern_54, 69, modifiers.array.length, 0, 1)), 160));
  cmd.time = timeIfReleased;
  return cmd;
}

function getMugenSingleCmds(cmds){
  var decodeScm, k, k$array, k$index, k$max, mugenSingleCmds, previous, repeat, scm, scm$array, scm$index, scm$max, singleCmds;
  mugenSingleCmds = new ArrayList;
  singleCmds = $split(cmds, ',');
  previous = null;
  for (scm$array = singleCmds , scm$index = 0 , scm$max = scm$array.length; scm$index < scm$max; ++scm$index) {
    scm = scm$array[scm$index];
    repeat = false;
    decodeScm = getMugenSingleCmd($trim(scm));
    if (previous) {
      if (previous.types == ($clinit_MugenSingleCmd$CommandType() , PRESS_0).bit && decodeScm.types == PRESS_0.bit && previous.keys_0 == decodeScm.keys_0) {
        for (k$array = values_16() , k$index = 0 , k$max = k$array.length; k$index < k$max; ++k$index) {
          k = k$array[k$index];
          if (k.bit == previous.keys_0 && k.bit == decodeScm.keys_0) {
            repeat = true;
            $add_8(mugenSingleCmds, new MugenSingleCmd(stampJavaTypeInfo(getClassLiteralForArray(Lorg_lee_mugen_input_Key_2_classLit, 1), $intern_55, 33, 0, [k]), stampJavaTypeInfo(getClassLiteralForArray(Lorg_lee_mugen_input_MugenSingleCmd$CommandType_2_classLit, 1), $intern_54, 69, 0, [NO_OTHER_KEY_BEFORE, RELEASED])));
            $add_8(mugenSingleCmds, new MugenSingleCmd(stampJavaTypeInfo(getClassLiteralForArray(Lorg_lee_mugen_input_Key_2_classLit, 1), $intern_55, 33, 0, [k]), stampJavaTypeInfo(getClassLiteralForArray(Lorg_lee_mugen_input_MugenSingleCmd$CommandType_2_classLit, 1), $intern_54, 69, 0, [NO_OTHER_KEY_BEFORE, PRESS_0])));
            break;
          }
        }
      }
    }
    repeat || (push_1(mugenSingleCmds.array, decodeScm) , true);
    previous = decodeScm;
  }
  return castTo($toArray_0(mugenSingleCmds, initUnidimensionalArray(Lorg_lee_mugen_input_MugenSingleCmd_2_classLit, {3:1, 4:1, 558:1}, 120, mugenSingleCmds.array.length, 0, 1)), 558);
}

function interpretCmd(grp){
  var bufferTime, cmds, iTime, name_0, time;
  if (!$equalsIgnoreCase('command', grp.section)) {
    throw toJs(new IllegalArgumentException_0('not a [Command] section: ' + grp.section));
  }
  cmds = castToString($getStringValue(grp.keyValues, 'command'));
  time = castToString($getStringValue(grp.keyValues, 'time'));
  bufferTime = castToString($getStringValue(grp.keyValues, 'buffer.time'));
  name_0 = castToString($getStringValue(grp.keyValues, 'name'));
  iTime = time == null?15:__parseAndValidateInt(time, 10);
  bufferTime == null?1:__parseAndValidateInt(bufferTime, 10);
  return new MugenCommands(getMugenSingleCmds(cmds), name_0, iTime);
}

function isStateMinusOne(sectionRaw){
  if (sectionRaw == null) {
    return false;
  }
  return $startsWith(sectionRaw.toLowerCase(), 'state -1');
}

function load(characterId){
  var charReader, commonReader, groups, merged;
  merged = new StringBuilder;
  charReader = openUtf8Reader(($clinit_JMugenConstant() , RESOURCE) + 'chars/' + characterId + '/' + characterId + '.cmd');
  appendReader(charReader, merged);
  merged.string += '\n';
  try {
    commonReader = openUtf8Reader(RESOURCE + 'data/common.cmd');
    appendReader(commonReader, merged);
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (!instanceOf($e0, 6))
      throw toJs($e0);
  }
  groups = ($clinit_Parser() , getGroupTextMap(new StringReader(merged.string), true));
  return parseGroups(groups);
}

function parseGroups(groups){
  var data_0, grp, grp$iterator;
  data_0 = new GwtCmdLoader$FightCmdData;
  for (grp$iterator = new ArrayList$1(groups); grp$iterator.i < grp$iterator.this$01.array.length;) {
    grp = castTo($next_7(grp$iterator), 90);
    $equalsIgnoreCase('command', grp.section)?$add_8(data_0.commands, interpretCmd(grp)):isStateMinusOne(grp.sectionRaw.toLowerCase()) && parseStateEntry(grp, data_0.commandToState);
  }
  return data_0;
}

function parseStateEntry(grp, commandToState){
  var commandName, e, e$iterator, eq, k, kv, line, lines, q1, q2, raw, raw$array, raw$index, raw$max, text_0, v, value_0;
  value_0 = null;
  commandName = null;
  text_0 = grp.text_0.string;
  lines = $split(text_0, '\r?\n');
  for (raw$array = lines , raw$index = 0 , raw$max = raw$array.length; raw$index < raw$max; ++raw$index) {
    raw = raw$array[raw$index];
    line = $trim(raw).toLowerCase();
    if (line.length == 0 || $equals_5(line.substr(0, 1), ';')) {
      continue;
    }
    if ($equals_5(line.substr(0, 5), 'value')) {
      eq = $indexOf_0(line, fromCodePoint(61));
      if (eq > 0) {
        try {
          value_0 = valueOf_1(__parseAndValidateInt($split($trim((checkCriticalStringElementIndex(eq + 1, line.length + 1) , line.substr(eq + 1))), '[^0-9-]')[0], 10));
        }
         catch ($e0) {
          $e0 = toJava($e0);
          if (!instanceOf($e0, 6))
            throw toJs($e0);
        }
      }
    }
     else if (line.indexOf('command') != -1) {
      q1 = $indexOf_0(line, fromCodePoint(34));
      q2 = $lastIndexOf(line, fromCodePoint(34));
      q1 >= 0 && q2 > q1 && (commandName = (checkCriticalStringBounds(q1 + 1, q2, line.length) , line.substr(q1 + 1, q2 - (q1 + 1))));
    }
  }
  kv = grp.keyValues;
  if ($contains_5(kv.stringMap, 'value') && !value_0) {
    try {
      value_0 = valueOf_1(__parseAndValidateInt($trim(castToString(kv.stringMap.backingMap.get('value'))), 10));
    }
     catch ($e1) {
      $e1 = toJava($e1);
      if (!instanceOf($e1, 6))
        throw toJs($e1);
    }
  }
  for (e$iterator = new AbstractHashMap$EntrySetIterator((new AbstractHashMap$EntrySet(kv)).this$01); e$iterator.hasNext;) {
    e = $next_6(e$iterator);
    k = castToString(e.getKey()).toLowerCase();
    if (k.indexOf('command') != -1 && commandName == null) {
      v = castToString(e.getValue());
      if (v != null && $indexOf_0(v, fromCodePoint(34)) >= 0) {
        q1 = $indexOf_0(v, fromCodePoint(34));
        q2 = $lastIndexOf(v, fromCodePoint(34));
        q2 > q1 && (commandName = (checkCriticalStringBounds(q1 + 1, q2, v.length) , v.substr(q1 + 1, q2 - (q1 + 1))));
      }
    }
  }
  !!value_0 && commandName != null && !(commandName == null?!!$getEntry(commandToState.hashCodeMap, null):$contains_5(commandToState.stringMap, commandName)) && (commandName == null?$put_6(commandToState.hashCodeMap, null, value_0):$put_7(commandToState.stringMap, commandName, value_0));
}

function GwtCmdLoader$FightCmdData(){
  this.commands = new ArrayList;
  this.commandToState = new HashMap;
}

defineClass(508, 1, {}, GwtCmdLoader$FightCmdData);
var Lorg_lee_mugen_fight_GwtCmdLoader$FightCmdData_2_classLit = createForClass('org.lee.mugen.fight', 'GwtCmdLoader/FightCmdData', 508);
function $getHitDef(this$static, stateId, hitIndex){
  var meta;
  meta = castTo($get_8(this$static.states, valueOf_1(stateId)), 81);
  if (!meta || meta.hitDefs.array.length == 0) {
    return null;
  }
  hitIndex < 0 && (hitIndex = 0);
  if (hitIndex >= meta.hitDefs.array.length) {
    return castTo($get_10(meta.hitDefs, meta.hitDefs.array.length - 1), 157);
  }
  return castTo($get_10(meta.hitDefs, hitIndex), 157);
}

function $getHitDefCount(this$static, stateId){
  var meta;
  meta = castTo($get_8(this$static.states, valueOf_1(stateId)), 81);
  return !meta?0:meta.hitDefs.array.length;
}

function $getState(this$static, stateId){
  return castTo($get_8(this$static.states, valueOf_1(stateId)), 81);
}

function GwtCnsData(){
  this.states = new HashMap;
}

defineClass(207, 1, {}, GwtCnsData);
_.airJuggle = 15;
_.attack = 100;
_.crouchFriction = 0.8199999928474426;
_.defence = 100;
_.groundBack = 15;
_.groundFront = 16;
_.height_0 = 60;
_.jumpBackX = -2.549999952316284;
_.jumpFwdX = 2.5;
_.jumpNeuY = -8.399999618530273;
_.lieDownTime = 60;
_.life = 1000;
_.runFwdX = 4.599999904632568;
_.standFriction = $intern_56;
_.walkBack = -2.200000047683716;
_.walkFwd = $intern_57;
_.xScale = 1;
_.yAccel = 0.4399999976158142;
_.yScale = 1;
var Lorg_lee_mugen_fight_GwtCnsData_2_classLit = createForClass('org.lee.mugen.fight', 'GwtCnsData', 207);
function GwtCnsData$HitDef(){
}

defineClass(157, 1, {157:1}, GwtCnsData$HitDef);
_.airVelocityX = -1.5;
_.airVelocityY = -3;
_.animElem = 0;
_.animType = 0;
_.damage = 0;
_.fall = false;
_.groundHitTime = 10;
_.groundSlideTime = 8;
_.groundVelocityX = -4;
_.guardsoundFromSprite = false;
_.guardsoundGrp = 6;
_.guardsoundNum = 0;
_.hitsoundFromSprite = false;
_.hitsoundGrp = 5;
_.hitsoundNum = 0;
_.pauseP1 = 8;
_.pauseP2 = 8;
_.trip = false;
var Lorg_lee_mugen_fight_GwtCnsData$HitDef_2_classLit = createForClass('org.lee.mugen.fight', 'GwtCnsData/HitDef', 157);
function GwtCnsData$StateCtrl(){
  this.triggerAll = new ArrayList;
  this.triggerGroups = new ArrayList;
  this.params = new HashMap;
}

defineClass(208, 1, {208:1}, GwtCnsData$StateCtrl);
_.ignoreHitPause = false;
_.persistent = 1;
_.type_0 = '';
var Lorg_lee_mugen_fight_GwtCnsData$StateCtrl_2_classLit = createForClass('org.lee.mugen.fight', 'GwtCnsData/StateCtrl', 208);
function GwtCnsData$StateMeta(){
  this.hitDefs = new ArrayList;
  this.controllers_0 = new ArrayList;
}

defineClass(81, 1, {81:1}, GwtCnsData$StateMeta);
_.anim = -1;
_.ctrl = -1;
_.hasVelset = false;
_.velsetX = 0;
_.velsetY = 0;
var Lorg_lee_mugen_fight_GwtCnsData$StateMeta_2_classLit = createForClass('org.lee.mugen.fight', 'GwtCnsData/StateMeta', 81);
function $clinit_GwtCnsLoader(){
  $clinit_GwtCnsLoader = emptyMethod;
  STATEDEF = new Pattern('\\s*statedef\\s*(-?\\d+)\\s*');
  ANIM_ELEM = new Pattern('animelem\\s*=\\s*(\\d+)');
}

function appendCommonCns(stCommon, charBase, out){
  var c, c$array, c$index, c$max, candidates, e, last, name_0;
  name_0 = stCommon != null && stCommon.length != 0?stCommon:'common1.cns';
  name_0 = $replace(name_0);
  candidates = stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_String_2_classLit, 1), $intern_11, 2, 6, [charBase + name_0, ($clinit_JMugenConstant() , RESOURCE) + 'data/' + name_0, RESOURCE + ('' + name_0)]);
  last = null;
  for (c$array = candidates , c$index = 0 , c$max = c$array.length; c$index < c$max; ++c$index) {
    c = c$array[c$index];
    try {
      appendFile(getExistFile_0(c), out);
      out.string += '\n';
      return;
    }
     catch ($e0) {
      $e0 = toJava($e0);
      if (instanceOf($e0, 6)) {
        e = $e0;
        last = e;
      }
       else 
        throw toJs($e0);
    }
  }
  if (last) {
    throw toJs(last);
  }
}

function appendFile(path, out){
  var br, line, reader;
  reader = openUtf8Reader(path);
  br = new BufferedReader(reader);
  while ((line = $readLine(br)) != null) {
    $append((out.string += '' + line , out), 10);
  }
}

function fillData(data_0, kv){
  data_0.life = parseInt_1(getIgnoreCase(kv, 'life'), data_0.life);
  data_0.attack = parseInt_1(getIgnoreCase(kv, 'attack'), data_0.attack);
  data_0.defence = parseInt_1(getIgnoreCase(kv, 'defence'), data_0.defence);
  data_0.airJuggle = parseInt_1(getIgnoreCase(kv, 'airjuggle'), data_0.airJuggle);
  data_0.lieDownTime = parseInt_1(getIgnoreCase(kv, 'liedown.time'), data_0.lieDownTime);
}

function fillMovement(data_0, kv){
  data_0.yAccel = parseFloat_1(getIgnoreCase(kv, 'yaccel'), data_0.yAccel);
  data_0.standFriction = parseFloat_1(getIgnoreCase(kv, 'stand.friction'), data_0.standFriction);
  data_0.crouchFriction = parseFloat_1(getIgnoreCase(kv, 'crouch.friction'), data_0.crouchFriction);
}

function fillSize(data_0, kv){
  data_0.xScale = parseFloat_1(getIgnoreCase(kv, 'xscale'), data_0.xScale);
  data_0.yScale = parseFloat_1(getIgnoreCase(kv, 'yscale'), data_0.yScale);
  data_0.groundBack = parseInt_1(getIgnoreCase(kv, 'ground.back'), data_0.groundBack);
  data_0.groundFront = parseInt_1(getIgnoreCase(kv, 'ground.front'), data_0.groundFront);
  data_0.height_0 = parseInt_1(getIgnoreCase(kv, 'height'), data_0.height_0);
}

function fillStateMeta(meta, kv){
  var mt, p, t, vel, xy;
  t = getIgnoreCase(kv, 'type');
  t != null && $toUpperCase($trim(t), ($clinit_Locale() , ROOT_0));
  mt = getIgnoreCase(kv, 'movetype');
  mt != null && $toUpperCase($trim(mt), ($clinit_Locale() , ROOT_0));
  p = getIgnoreCase(kv, 'physics');
  p != null && $toUpperCase($trim(p), ($clinit_Locale() , ROOT_0));
  meta.anim = parseInt_1(getIgnoreCase(kv, 'anim'), meta.anim);
  meta.ctrl = parseInt_1(getIgnoreCase(kv, 'ctrl'), meta.ctrl);
  vel = getIgnoreCase(kv, 'velset');
  if (vel != null) {
    xy = parseFloats(vel);
    if (xy.length >= 1) {
      meta.velsetX = xy[0];
      meta.hasVelset = true;
    }
    xy.length >= 2 && (meta.velsetY = xy[1]);
  }
}

function fillVelocity(data_0, kv){
  var jumpNeu, runFwd;
  data_0.walkFwd = parseFloat_1(getIgnoreCase(kv, 'walk.fwd'), data_0.walkFwd);
  data_0.walkBack = parseFloat_1(getIgnoreCase(kv, 'walk.back'), data_0.walkBack);
  runFwd = parseFloats(getIgnoreCase(kv, 'run.fwd'));
  runFwd.length >= 1 && (data_0.runFwdX = runFwd[0]);
  parseFloats(getIgnoreCase(kv, 'run.back'));
  jumpNeu = parseFloats(getIgnoreCase(kv, 'jump.neu'));
  jumpNeu.length >= 2 && (data_0.jumpNeuY = jumpNeu[1]);
  data_0.jumpBackX = parseFloat_1(getIgnoreCase(kv, 'jump.back'), data_0.jumpBackX);
  data_0.jumpFwdX = parseFloat_1(getIgnoreCase(kv, 'jump.fwd'), data_0.jumpFwdX);
}

function getIgnoreCase(kv, key){
  var e, e$iterator, want;
  if ($contains_5(kv.stringMap, key)) {
    return castToString($get_12(kv.stringMap, key));
  }
  want = $toLowerCase(key, ($clinit_Locale() , ROOT_0));
  for (e$iterator = new AbstractHashMap$EntrySetIterator((new AbstractHashMap$EntrySet(kv)).this$01); e$iterator.hasNext;) {
    e = $next_6(e$iterator);
    if (e.getKey() != null && $equals_5($toLowerCase(castToString(e.getKey()), ROOT_0), want)) {
      return castToString(e.getValue());
    }
  }
  return null;
}

function load_0(characterId){
  $clinit_GwtCnsLoader();
  var base, extra, extra$iterator, files, groups, merged;
  files = readDefFiles(characterId);
  merged = new StringBuilder;
  base = ($clinit_JMugenConstant() , RESOURCE) + 'chars/' + characterId + '/';
  appendFile(getExistFile_0(base + $replace(files.cns)), merged);
  merged.string += '\n';
  if (files.st != null && !$equalsIgnoreCase(files.st, files.cns)) {
    appendFile(getExistFile_0(base + $replace(files.st)), merged);
    merged.string += '\n';
  }
  for (extra$iterator = new ArrayList$1(files.extraSt); extra$iterator.i < extra$iterator.this$01.array.length;) {
    extra = castToString($next_7(extra$iterator));
    if (extra == null || extra.length == 0) {
      continue;
    }
    if ($equalsIgnoreCase(extra, files.cns) || $equalsIgnoreCase(extra, files.st)) {
      continue;
    }
    try {
      appendFile(getExistFile_0(base + $replace(extra)), merged);
      merged.string += '\n';
    }
     catch ($e0) {
      $e0 = toJava($e0);
      if (!instanceOf($e0, 6))
        throw toJs($e0);
    }
  }
  appendCommonCns(files.stCommon, base, merged);
  groups = ($clinit_Parser() , getGroupTextMap(new StringReader(merged.string), true));
  return parseGroups_0(groups);
}

function maybeParseHitDef(data_0, stateId, grp){
  var gf, gtype, hf, hit, kv, meta, type_0;
  kv = grp.keyValues;
  type_0 = getIgnoreCase(kv, 'type');
  if (type_0 == null || !$equalsIgnoreCase('hitdef', $trim(type_0))) {
    return;
  }
  meta = castTo($get_8(data_0.states, valueOf_1(stateId)), 81);
  if (!meta) {
    meta = new GwtCnsData$StateMeta;
    $put_4(data_0.states, valueOf_1(stateId), meta);
  }
  hit = new GwtCnsData$HitDef;
  parseDamage(hit, getIgnoreCase(kv, 'damage'));
  parsePause(hit, getIgnoreCase(kv, 'pausetime'));
  parseGroundVel(hit, getIgnoreCase(kv, 'ground.velocity'));
  parseAirVel(hit, getIgnoreCase(kv, 'air.velocity'));
  hit.groundSlideTime = parseInt_1(getIgnoreCase(kv, 'ground.slidetime'), hit.groundSlideTime);
  hit.groundHitTime = parseInt_1(getIgnoreCase(kv, 'ground.hittime'), hit.groundHitTime);
  gtype = getIgnoreCase(kv, 'ground.type');
  gtype != null && $equalsIgnoreCase($trim(gtype), 'trip') && (hit.trip = true);
  hit.fall = parseBool(getIgnoreCase(kv, 'fall'));
  gf = getIgnoreCase(kv, 'guardflag');
  gf != null && $trim(gf);
  hf = getIgnoreCase(kv, 'hitflag');
  hf != null && $trim(hf);
  hit.animElem = parseAnimElemTrigger(kv);
  parseSndRef(hit, getIgnoreCase(kv, 'hitsound'), true);
  parseSndRef(hit, getIgnoreCase(kv, 'guardsound'), false);
  hit.animType = parseAnimType(getIgnoreCase(kv, 'animtype'));
  $add_8(meta.hitDefs, hit);
}

function parseAirVel(hit, raw){
  var v;
  v = parseFloats(raw);
  v.length >= 1 && (hit.airVelocityX = $wnd.Math.abs(v[0]));
  v.length >= 2 && (hit.airVelocityY = v[1]);
}

function parseAnimElemTrigger(kv){
  var e, e$iterator, k, m;
  for (e$iterator = new AbstractHashMap$EntrySetIterator((new AbstractHashMap$EntrySet(kv)).this$01); e$iterator.hasNext;) {
    e = $next_6(e$iterator);
    k = $replaceAll($toLowerCase(castToString(e.getKey()), ($clinit_Locale() , ROOT_0)), '\\s+', '');
    if (!$equals_5(k.substr(0, 7), 'trigger')) {
      continue;
    }
    m = new Matcher(ANIM_ELEM, $toLowerCase(castToString(e.getValue()), ROOT_0));
    if ($test(m.regExp, m.input_0)) {
      return __parseAndValidateInt(m.matchResult[1], 10);
    }
  }
  return 0;
}

function parseAnimType(raw){
  var v;
  if (raw == null || $trim(raw).length == 0) {
    return 0;
  }
  v = $toLowerCase($trim(raw), ($clinit_Locale() , ROOT_0));
  if ($equals_5(v.substr(0, 3), 'med') || $equals_5('m', v)) {
    return 1;
  }
  if ($equals_5(v.substr(0, 3), 'har') || $equals_5(v.substr(0, 3), 'hea') || $equals_5('h', v) || $equals_5('2', v)) {
    return 2;
  }
  if ($equals_5(v.substr(0, 3), 'bac') || $equals_5(v.substr(0, 2), 'up') || $equals_5(v.substr(0, 4), 'diag')) {
    return 2;
  }
  return 0;
}

function parseBool(raw){
  var t;
  if (raw == null) {
    return false;
  }
  t = $trim(raw);
  return $equals_5('1', t) || $equalsIgnoreCase('true', t);
}

function parseDamage(hit, raw){
  var v;
  v = parseFloats(raw);
  v.length >= 1 && (hit.damage = round_int(v[0]));
}

function parseFloat_1(raw, def){
  var v;
  v = parseFloats(raw);
  return v.length > 0?v[0]:def;
}

function parseFloats(raw){
  var cleaned, n, out, p, p$array, p$index, p$max, parts, trimmed;
  if (raw == null || $trim(raw).length == 0) {
    return initUnidimensionalArray(F_classLit, $intern_10, 5, 0, 15, 1);
  }
  cleaned = $replaceAll($trim(raw), ' ', '');
  parts = $split(cleaned, ',');
  out = initUnidimensionalArray(F_classLit, $intern_10, 5, parts.length, 15, 1);
  n = 0;
  for (p$array = parts , p$index = 0 , p$max = p$array.length; p$index < p$max; ++p$index) {
    p = p$array[p$index];
    try {
      out[n++] = parseFloat_0(p);
    }
     catch ($e0) {
      $e0 = toJava($e0);
      if (!instanceOf($e0, 6))
        throw toJs($e0);
    }
  }
  if (n == out.length) {
    return out;
  }
  trimmed = initUnidimensionalArray(F_classLit, $intern_10, 5, n, 15, 1);
  arraycopy(out, 0, trimmed, 0, n);
  return trimmed;
}

function parseGroundVel(hit, raw){
  var v;
  v = parseFloats(raw);
  v.length >= 1 && (hit.groundVelocityX = $wnd.Math.abs(v[0]));
}

function parseGroups_0(groups){
  var currentState, data_0, grp, grp$iterator, m, meta, raw, sd, section, sectionLower;
  data_0 = new GwtCnsData;
  currentState = $intern_42;
  for (grp$iterator = new ArrayList$1(groups); grp$iterator.i < grp$iterator.this$01.array.length;) {
    grp = castTo($next_7(grp$iterator), 90);
    section = grp.section != null?$trim(grp.section):'';
    raw = grp.sectionRaw.toLowerCase() != null?grp.sectionRaw.toLowerCase():section;
    sectionLower = $toLowerCase(section, ($clinit_Locale() , ROOT_0));
    if ($equals_5('data', sectionLower)) {
      fillData(data_0, grp.keyValues);
    }
     else if ($equals_5('size', sectionLower)) {
      fillSize(data_0, grp.keyValues);
    }
     else if ($equals_5('velocity', sectionLower)) {
      fillVelocity(data_0, grp.keyValues);
    }
     else if ($equals_5('movement', sectionLower)) {
      fillMovement(data_0, grp.keyValues);
    }
     else {
      sd = new Matcher(STATEDEF, sectionLower);
      if ($test(sd.regExp, sd.input_0) || $find(new Matcher(STATEDEF, $toLowerCase(raw, ROOT_0)))) {
        m = new Matcher(STATEDEF, sectionLower);
        if (!$test(m.regExp, m.input_0)) {
          m = new Matcher(STATEDEF, $toLowerCase(raw, ROOT_0));
          $test(m.regExp, m.input_0);
        }
        currentState = __parseAndValidateInt(m.matchResult[1], 10);
        meta = castTo($get_8(data_0.states, valueOf_1(currentState)), 81);
        if (!meta) {
          meta = new GwtCnsData$StateMeta;
          $put_4(data_0.states, valueOf_1(currentState), meta);
        }
        fillStateMeta(meta, grp.keyValues);
      }
       else 
        $equals_5(sectionLower.substr(0, 5), 'state') && currentState != $intern_42 && parseStateCtrl(data_0, currentState, grp);
    }
  }
  return data_0;
}

function parseInt_1(raw, def){
  if (raw == null || $trim(raw).length == 0) {
    return def;
  }
  try {
    return round_int(parseFloat_0($split($trim(raw), '[^0-9.+\\-eE]')[0]));
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 6)) {
      return def;
    }
     else 
      throw toJs($e0);
  }
}

function parsePause(hit, raw){
  var v;
  v = parseFloats(raw);
  v.length >= 1 && (hit.pauseP1 = round_int(v[0]));
  v.length >= 2?(hit.pauseP2 = round_int(v[1])):v.length >= 1 && (hit.pauseP2 = hit.pauseP1);
}

function parseSndRef(hit, raw, hitNotGuard){
  var fromSprite, grp, num, p, v;
  if (raw == null || $trim(raw).length == 0) {
    return;
  }
  v = $replaceAll($trim(raw), ' ', '');
  fromSprite = false;
  if (v.length > 0 && (checkCriticalStringElementIndex(0, v.length) , v.charCodeAt(0) == 115 || (checkCriticalStringElementIndex(0, v.length) , v.charCodeAt(0) == 83))) {
    fromSprite = true;
    v = (checkCriticalStringElementIndex(1, v.length + 1) , v.substr(1));
  }
  p = $split(v, ',');
  if (p.length < 2) {
    return;
  }
  try {
    grp = __parseAndValidateInt(p[0], 10);
    num = __parseAndValidateInt(p[1], 10);
    if (hitNotGuard) {
      hit.hitsoundGrp = grp;
      hit.hitsoundNum = num;
      hit.hitsoundFromSprite = fromSprite;
    }
     else {
      hit.guardsoundGrp = grp;
      hit.guardsoundNum = num;
      hit.guardsoundFromSprite = fromSprite;
    }
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (!instanceOf($e0, 6))
      throw toJs($e0);
  }
}

function parseStateCtrl(data_0, stateId, grp){
  var always, ctrl, e, e$iterator, group, k, kv, meta, type_0, v;
  kv = grp.keyValues;
  if (kv.hashCodeMap.size_0 + kv.stringMap.size_0 == 0) {
    return;
  }
  type_0 = getIgnoreCase(kv, 'type');
  if (type_0 == null || $trim(type_0).length == 0) {
    return;
  }
  type_0 = $toLowerCase($trim(type_0), ($clinit_Locale() , ROOT_0));
  meta = castTo($get_8(data_0.states, valueOf_1(stateId)), 81);
  if (!meta) {
    meta = new GwtCnsData$StateMeta;
    $put_4(data_0.states, valueOf_1(stateId), meta);
  }
  ctrl = new GwtCnsData$StateCtrl;
  ctrl.type_0 = type_0;
  ctrl.ignoreHitPause = parseBool(getIgnoreCase(kv, 'ignorehitpause'));
  ctrl.persistent = parseInt_1(getIgnoreCase(kv, 'persistent'), 1);
  for (e$iterator = new AbstractHashMap$EntrySetIterator((new AbstractHashMap$EntrySet(kv)).this$01); e$iterator.hasNext;) {
    e = $next_6(e$iterator);
    k = e.getKey() != null?$replaceAll($toLowerCase(castToString(e.getKey()), ROOT_0), '\\s+', ''):'';
    v = e.getValue() != null?castToString(e.getValue()):'';
    if ($equals_5(k, 'triggerall')) {
      $add_8(ctrl.triggerAll, $trim(v));
    }
     else if ($equals_5(k.substr(0, 7), 'trigger') && !$equals_5(k, 'triggerall')) {
      group = new ArrayList;
      $add_8(group, $trim(v));
      $add_8(ctrl.triggerGroups, group);
    }
     else 
      !$equals_5(k, 'type') && !$equals_5(k, 'ignorehitpause') && !$equals_5(k, 'persistent') && $putStringValue(ctrl.params, k, $trim(v));
  }
  if (ctrl.triggerGroups.array.length == 0 && ctrl.triggerAll.array.length == 0) {
    always = new ArrayList;
    always.array.push('1');
    $add_8(ctrl.triggerGroups, always);
  }
  $add_8(meta.controllers_0, ctrl);
  $equals_5('hitdef', type_0) && maybeParseHitDef(data_0, stateId, grp);
}

function readDefFiles(characterId){
  var br, defPath, eq, files, key, line, reader, section, semi, suffixlength, value_0;
  files = new GwtCnsLoader$DefFiles;
  files.cns = characterId + '.cns';
  files.st = characterId + '.cns';
  defPath = getExistFile_0(($clinit_JMugenConstant() , RESOURCE) + 'chars/' + characterId + '/' + characterId + '.def');
  reader = openUtf8Reader(defPath);
  br = new BufferedReader(reader);
  section = '';
  while ((line = $readLine(br)) != null) {
    line = $trim(line);
    if (line.length == 0 || $equals_5(line.substr(0, 1), ';')) {
      continue;
    }
    if ($equals_5(line.substr(0, 1), '[') && (suffixlength = ']'.length , $equals_5(line.substr(line.length - suffixlength, suffixlength), ']'))) {
      section = $toLowerCase($trim($substring_0(line, 1, line.length - 1)), ($clinit_Locale() , ROOT_0));
      continue;
    }
    if (!$equals_5('files', section)) {
      continue;
    }
    eq = $indexOf_0(line, fromCodePoint(61));
    if (eq < 0) {
      continue;
    }
    key = $replaceAll($toLowerCase($trim((checkCriticalStringBounds(0, eq, line.length) , line.substr(0, eq))), ($clinit_Locale() , ROOT_0)), '\\s+', '');
    value_0 = $trim((checkCriticalStringElementIndex(eq + 1, line.length + 1) , line.substr(eq + 1)));
    semi = $indexOf_0(value_0, fromCodePoint(59));
    semi >= 0 && (value_0 = $trim((checkCriticalStringBounds(0, semi, value_0.length) , value_0.substr(0, semi))));
    value_0 = $replace(value_0);
    $equals_5('cns', key)?(files.cns = value_0):$equals_5('st', key) || (new RegExp('^(st\\d+)$')).test(key)?$equals_5('st', key)?(files.st = value_0):$add_8(files.extraSt, value_0):$equals_5('stcommon', key) && (files.stCommon = value_0);
  }
  files.st == null && (files.st = files.cns);
  return files;
}

var ANIM_ELEM, STATEDEF;
function GwtCnsLoader$DefFiles(){
  this.extraSt = new ArrayList;
}

defineClass(509, 1, {}, GwtCnsLoader$DefFiles);
_.cns = 'kfm.cns';
_.stCommon = 'common1.cns';
var Lorg_lee_mugen_fight_GwtCnsLoader$DefFiles_2_classLit = createForClass('org.lee.mugen.fight', 'GwtCnsLoader/DefFiles', 509);
function $clinit_GwtCnsRuntime(){
  $clinit_GwtCnsRuntime = emptyMethod;
  TIME_EQ = new Pattern('^time\\s*=\\s*(-?\\d+)$');
  ANIMTIME_EQ = new Pattern('^animtime\\s*=\\s*(-?\\d+)$');
  ANIMELEM_EQ = new Pattern('^animelem\\s*=\\s*(\\d+)$');
  ANIMELEMTIME = new Pattern('animelemtime\\s*\\(\\s*(\\d+)\\s*\\)\\s*(<=|>=|<|>|=)\\s*(-?\\d+)');
}

function $changeState_0(this$static, newState, host){
  var meta;
  this$static.stateNo = newState;
  this$static.stateTime = 0;
  $reset_4(this$static.firedOnce.map_0);
  meta = $getState(this$static.data_0, newState);
  if (!meta) {
    return;
  }
  meta.anim >= 0?$setAnim(host, meta.anim):newState >= 0 && ($restartAction(host.this$01.anim, newState) , host.this$01.currentAction = newState);
  meta.hasVelset && $setVel(host, meta.velsetX * host.this$01.facing, meta.velsetY);
  meta.ctrl >= 0 && $setCtrl(host, meta.ctrl != 0);
}

function $evalExpr(raw, host){
  var expr, i, parts;
  if (raw == null) {
    return false;
  }
  expr = $toLowerCase($trim(raw), ($clinit_Locale() , ROOT_0));
  if (expr.length == 0 || $equals_5('1', expr) || $equals_5('true', expr)) {
    return true;
  }
  if ($equals_5('0', expr) || $equals_5('false', expr)) {
    return false;
  }
  if (expr.indexOf('&&') >= 0) {
    parts = $split(expr, '&&');
    for (i = 0; i < parts.length; i++) {
      if (!$evalSimple($trim(parts[i]), host)) {
        return false;
      }
    }
    return true;
  }
  return $evalSimple(expr, host);
}

function $evalSimple(expr, host){
  var approx, cur, elem, m, op, rhs;
  if ($equals_5('movecontact', expr)) {
    return host.this$01.attackHitsDone > 0 || host.this$01.attackHit;
  }
  if ($equals_5('movehit', expr)) {
    return host.this$01.attackHitsDone > 0 || host.this$01.attackHit;
  }
  m = new Matcher(TIME_EQ, expr);
  if ($test(m.regExp, m.input_0)) {
    return host.this$01.cnsRuntime.stateTime == __parseAndValidateInt(m.matchResult[1], 10);
  }
  m = new Matcher(ANIMTIME_EQ, expr);
  if ($test(m.regExp, m.input_0)) {
    return host.this$01.anim.anim.animTime == __parseAndValidateInt(m.matchResult[1], 10);
  }
  m = new Matcher(ANIMELEM_EQ, expr);
  if ($test(m.regExp, m.input_0)) {
    return host.this$01.anim.anim._animElem + 1 == __parseAndValidateInt(m.matchResult[1], 10);
  }
  m = new Matcher(ANIMELEMTIME, expr);
  if ($test(m.regExp, m.input_0)) {
    elem = __parseAndValidateInt(m.matchResult[1], 10);
    op = m.matchResult[2];
    rhs = __parseAndValidateInt(m.matchResult[3], 10);
    cur = host.this$01.anim.anim._animElem + 1;
    approx = cur - elem;
    return compare_6(approx, op, rhs);
  }
  return false;
}

function $execute_0(this$static, ctrl, host){
  var ctrlFlag, elem, p, type_0, value_0, vx, vy, x_0, y_0;
  type_0 = ctrl.type_0;
  p = ctrl.params;
  if ($equals_5('changestate', type_0) || $equals_5('selfstate', type_0)) {
    value_0 = parseInt_2(castToString(p.stringMap.backingMap.get('value')), this$static.stateNo);
    ctrlFlag = parseInt_2(castToString(p.stringMap.backingMap.get('ctrl')), -1);
    $changeState_0(this$static, value_0, host);
    $applyChangeState(host, value_0, ctrlFlag);
  }
   else if ($equals_5('changeanim', type_0) || $equals_5('changeanim2', type_0)) {
    value_0 = parseInt_2(castToString(p.stringMap.backingMap.get('value')), -1);
    elem = parseInt_2(castToString(p.stringMap.backingMap.get('elem')), -1);
    value_0 >= 0 && ($restartAction(host.this$01.anim, value_0) , host.this$01.currentAction = value_0);
    elem > 0 && $setAnimElem(host.this$01.anim, elem);
  }
   else if ($equals_5('velset', type_0)) {
    x_0 = parseFloat_2(castToString(p.stringMap.backingMap.get('x')), NaN);
    y_0 = parseFloat_2(castToString(p.stringMap.backingMap.get('y')), NaN);
    vx = isNaN(x_0)?0:x_0 * host.this$01.facing;
    vy = isNaN(y_0)?0:y_0;
    if (isNaN(x_0) && isNaN(y_0)) {
      return;
    }
    isNaN(x_0) && (vx = 0);
    isNaN(y_0) && (vy = 0);
    host.this$01.vx = vx;
    host.this$01.vy = vy;
  }
   else if ($equals_5('veladd', type_0)) {
    x_0 = parseFloat_2(castToString(p.stringMap.backingMap.get('x')), 0) * host.this$01.facing;
    y_0 = parseFloat_2(castToString(p.stringMap.backingMap.get('y')), 0);
    host.this$01.vx = x_0;
    host.this$01.vy = y_0;
  }
   else 
    $equals_5('ctrlset', type_0) && $setCtrl(host, parseInt_2(castToString(p.stringMap.backingMap.get('value')), 0) != 0);
}

function $tick(this$static, host){
  var ctrl, i, meta, paused;
  meta = $getState(this$static.data_0, this$static.stateNo);
  if (!meta) {
    ++this$static.stateTime;
    return;
  }
  paused = host.this$01.hitPause > 0;
  for (i = 0; i < meta.controllers_0.array.length; i++) {
    ctrl = castTo($get_10(meta.controllers_0, i), 208);
    if (paused && !ctrl.ignoreHitPause) {
      continue;
    }
    if (ctrl.persistent == 0 && $contains_4(this$static.firedOnce, valueOf_1(i))) {
      continue;
    }
    if (!$triggersPass(ctrl, host)) {
      continue;
    }
    ctrl.persistent == 0 && $add_10(this$static.firedOnce, valueOf_1(i));
    $execute_0(this$static, ctrl, host);
    if ($equals_5('changestate', ctrl.type_0) || $equals_5('selfstate', ctrl.type_0)) {
      break;
    }
  }
  paused || ++this$static.stateTime;
}

function $triggersPass(ctrl, host){
  var group, group$iterator, ok, t, t$iterator, t$iterator0;
  for (t$iterator0 = new ArrayList$1(ctrl.triggerAll); t$iterator0.i < t$iterator0.this$01.array.length;) {
    t = castToString($next_7(t$iterator0));
    if (!$evalExpr(t, host)) {
      return false;
    }
  }
  if (ctrl.triggerGroups.array.length == 0) {
    return true;
  }
  for (group$iterator = new ArrayList$1(ctrl.triggerGroups); group$iterator.i < group$iterator.this$01.array.length;) {
    group = castTo($next_7(group$iterator), 82);
    ok = true;
    for (t$iterator = group.iterator(); t$iterator.hasNext_0();) {
      t = castToString(t$iterator.next_1());
      if (!$evalExpr(t, host)) {
        ok = false;
        break;
      }
    }
    if (ok) {
      return true;
    }
  }
  return false;
}

function GwtCnsRuntime(data_0){
  $clinit_GwtCnsRuntime();
  this.firedOnce = new HashSet;
  this.data_0 = data_0?data_0:new GwtCnsData;
  this.stateNo = 0;
}

function compare_6(left, op, right){
  if ($equals_5('=', op)) {
    return left == right;
  }
  if ($equals_5('<', op)) {
    return left < right;
  }
  if ($equals_5('>', op)) {
    return left > right;
  }
  if ($equals_5('<=', op)) {
    return left <= right;
  }
  if ($equals_5('>=', op)) {
    return left >= right;
  }
  return false;
}

function parseFloat_2(raw, def){
  if (raw == null || $trim(raw).length == 0) {
    return def;
  }
  try {
    return parseFloat_0($split($trim(raw), '[^0-9.+\\-eE]')[0]);
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 6)) {
      return def;
    }
     else 
      throw toJs($e0);
  }
}

function parseInt_2(raw, def){
  if (raw == null || $trim(raw).length == 0) {
    return def;
  }
  try {
    return round_int(parseFloat_0($split($trim(raw), '[^0-9.+\\-eE]')[0]));
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 6)) {
      return def;
    }
     else 
      throw toJs($e0);
  }
}

defineClass(506, 1, {}, GwtCnsRuntime);
_.stateNo = 0;
_.stateTime = 0;
var ANIMELEMTIME, ANIMELEM_EQ, ANIMTIME_EQ, TIME_EQ;
var Lorg_lee_mugen_fight_GwtCnsRuntime_2_classLit = createForClass('org.lee.mugen.fight', 'GwtCnsRuntime', 506);
function $parseAndLoad(this$static){
  var bis, br, commonSndPath, defPath, eq, fightSndPath, key, line, n, numPart, path, reader, section, semi, sn, suffixlength, suffixlength0, value_0;
  defPath = getExistFile_0(($clinit_JMugenConstant() , RESOURCE) + 'data/fight.def');
  fightSndPath = 'data/fight.snd';
  commonSndPath = 'data/common.snd';
  $put_4(this$static.roundSnds, valueOf_1(1), stampJavaTypeInfo(getClassLiteralForArray(I_classLit, 1), $intern_3, 5, 15, [0, 1]));
  $put_4(this$static.roundSnds, valueOf_1(2), stampJavaTypeInfo(getClassLiteralForArray(I_classLit, 1), $intern_3, 5, 15, [0, 2]));
  $put_4(this$static.roundSnds, valueOf_1(3), stampJavaTypeInfo(getClassLiteralForArray(I_classLit, 1), $intern_3, 5, 15, [0, 3]));
  reader = openUtf8Reader(defPath);
  br = new BufferedReader(reader);
  section = '';
  while ((line = $readLine(br)) != null) {
    line = $trim(line);
    if (line.length == 0 || $equals_5(line.substr(0, 1), ';')) {
      continue;
    }
    if ($equals_5(line.substr(0, 1), '[') && (suffixlength0 = ']'.length , $equals_5(line.substr(line.length - suffixlength0, suffixlength0), ']'))) {
      section = $toLowerCase($trim($substring_0(line, 1, line.length - 1)), ($clinit_Locale() , ROOT_0));
      continue;
    }
    eq = $indexOf_0(line, fromCodePoint(61));
    if (eq < 0) {
      continue;
    }
    key = $replaceAll($toLowerCase($trim((checkCriticalStringBounds(0, eq, line.length) , line.substr(0, eq))), ($clinit_Locale() , ROOT_0)), '\\s+', '');
    value_0 = $trim((checkCriticalStringElementIndex(eq + 1, line.length + 1) , line.substr(eq + 1)));
    semi = $indexOf_0(value_0, fromCodePoint(59));
    semi >= 0 && (value_0 = $trim((checkCriticalStringBounds(0, semi, value_0.length) , value_0.substr(0, semi))));
    value_0 = $replace(value_0);
    if ($equals_5('files', section)) {
      $equals_5('snd', key)?(fightSndPath = $equals_5(value_0.substr(0, 5), 'data/')?value_0:'data/' + value_0):$equals_5('common.snd', key) && (commonSndPath = $equals_5(value_0.substr(0, 5), 'data/')?value_0:'data/' + value_0);
    }
     else if ($equals_5('round', section)) {
      if (key.length > 9 && $equals_5(key.substr(0, 5), 'round') && (suffixlength = '.snd'.length , $equals_5(key.substr(key.length - suffixlength, suffixlength), '.snd'))) {
        numPart = $substring_0(key, 5, key.length - 4);
        try {
          n = __parseAndValidateInt(numPart, 10);
          sn = parseSndPair(value_0);
          sn != null && $put_4(this$static.roundSnds, valueOf_1(n), sn);
        }
         catch ($e0) {
          $e0 = toJava($e0);
          if (!instanceOf($e0, 6))
            throw toJs($e0);
        }
      }
       else if ($equals_5('fight.snd', key)) {
        sn = parseSndPair(value_0);
        sn != null && (this$static.fightCallSnd = sn);
      }
       else if ($equals_5('ko.snd', key)) {
        sn = parseSndPair(value_0);
        sn != null && (this$static.koSnd = sn);
      }
       else if ($equals_5('dko.snd', key)) {
        sn = parseSndPair(value_0);
        sn != null && (this$static.dkoSnd = sn);
      }
       else if ($equals_5('to.snd', key)) {
        sn = parseSndPair(value_0);
        sn != null && (this$static.toSnd = sn);
      }
    }
  }
  try {
    this$static.fightSnd = (path = getExistFile_0(RESOURCE + ('' + fightSndPath)) , bis = new BufferedInputStream(openBinary(path)) , parse_19(bis));
  }
   catch ($e1) {
    $e1 = toJava($e1);
    if (instanceOf($e1, 6)) {
      $clinit_System();
      String.fromCharCode(10);
    }
     else 
      throw toJs($e1);
  }
  try {
    this$static.commonSnd = (path = getExistFile_0(RESOURCE + ('' + commonSndPath)) , bis = new BufferedInputStream(openBinary(path)) , parse_19(bis));
  }
   catch ($e2) {
    $e2 = toJava($e2);
    if (instanceOf($e2, 6)) {
      $clinit_System();
      String.fromCharCode(10);
    }
     else 
      throw toJs($e2);
  }
}

function $playCommon(this$static, grp, num){
  play(this$static.commonSnd, grp, num);
}

function $playDko(this$static){
  $playFight(this$static, this$static.dkoSnd[0], this$static.dkoSnd[1]);
}

function $playFight(this$static, grp, num){
  play(this$static.fightSnd, grp, num);
}

function $playFightCall(this$static){
  $playFight(this$static, this$static.fightCallSnd[0], this$static.fightCallSnd[1]);
}

function $playKo(this$static){
  $playFight(this$static, this$static.koSnd[0], this$static.koSnd[1]);
}

function $playRound(this$static, roundNo){
  var sn;
  sn = castTo($get_8(this$static.roundSnds, valueOf_1(roundNo)), 11);
  sn == null && (sn = castTo($get_8(this$static.roundSnds, valueOf_1(1)), 11));
  sn != null && (play(this$static.fightSnd, sn[0], sn[1]) , undefined);
}

function $playTimeOver(this$static){
  $playFight(this$static, this$static.toSnd[0], this$static.toSnd[1]);
}

function GwtFightAudio(){
  this.roundSnds = new HashMap;
  this.fightCallSnd = stampJavaTypeInfo(getClassLiteralForArray(I_classLit, 1), $intern_3, 5, 15, [1, 0]);
  this.koSnd = stampJavaTypeInfo(getClassLiteralForArray(I_classLit, 1), $intern_3, 5, 15, [2, 0]);
  this.dkoSnd = stampJavaTypeInfo(getClassLiteralForArray(I_classLit, 1), $intern_3, 5, 15, [2, 1]);
  this.toSnd = stampJavaTypeInfo(getClassLiteralForArray(I_classLit, 1), $intern_3, 5, 15, [2, 2]);
}

function load_1(){
  var audio;
  audio = new GwtFightAudio;
  try {
    $parseAndLoad(audio);
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 6)) {
      $clinit_System();
      String.fromCharCode(10);
    }
     else 
      throw toJs($e0);
  }
  return audio;
}

function parseSndPair(value_0){
  var cleaned, p;
  if (value_0 == null || value_0.length == 0) {
    return null;
  }
  cleaned = $replaceAll(value_0, ' ', '');
  p = $split(cleaned, ',');
  if (p.length < 2) {
    return null;
  }
  try {
    return stampJavaTypeInfo(getClassLiteralForArray(I_classLit, 1), $intern_3, 5, 15, [__parseAndValidateInt(p[0], 10), __parseAndValidateInt(p[1], 10)]);
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 6)) {
      return null;
    }
     else 
      throw toJs($e0);
  }
}

function play(snd, grp, num){
  var data_0, group;
  if (!snd || grp < 0) {
    return;
  }
  try {
    group = castTo($get_8(snd._grpSoundMap, valueOf_1(grp)), 138);
    if (!group) {
      $clinit_System();
      String.fromCharCode(10);
      return;
    }
    data_0 = castTo($get_8(group._soundMap, valueOf_1(num)), 21);
    data_0 != null && data_0.length > 0?!!audioPlayback && $playSfx(audioPlayback, data_0):($clinit_System() , String.fromCharCode(10));
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 6)) {
      $clinit_System();
      String.fromCharCode(10);
    }
     else 
      throw toJs($e0);
  }
}

defineClass(507, 1, {}, GwtFightAudio);
var Lorg_lee_mugen_fight_GwtFightAudio_2_classLit = createForClass('org.lee.mugen.fight', 'GwtFightAudio', 507);
function $keyPressed(this$static, keycode){
  !!this$static.dispatcher && !!this$static.player && $press(this$static.dispatcher, keycode, this$static.gameTime, this$static.player.facing == -1);
}

function $keyReleased(this$static, keycode){
  !!this$static.dispatcher && !!this$static.player && $release(this$static.dispatcher, keycode, this$static.gameTime, this$static.player.facing == -1);
}

function $process_1(this$static){
  var cmds, flip, mc, mc$iterator;
  if (!this$static.dispatcher || !this$static.player) {
    return;
  }
  cmds = this$static.player.commands;
  if (!cmds) {
    return;
  }
  flip = this$static.player.facing == -1;
  for (mc$iterator = new ArrayList$1(cmds); mc$iterator.i < mc$iterator.this$01.array.length;) {
    mc = castTo($next_7(mc$iterator), 209);
    $find_0(mc, this$static.dispatcher, this$static.gameTime, flip) && $onCommand(this$static.player, mc._commandName);
  }
}

function $setGameTime(this$static, gameTime){
  this$static.gameTime = gameTime;
}

function GwtFightCmdProcess(dispatcher, player){
  this.dispatcher = dispatcher;
  this.player = player;
}

defineClass(267, 1, {}, GwtFightCmdProcess);
_.gameTime = 0;
var Lorg_lee_mugen_fight_GwtFightCmdProcess_2_classLit = createForClass('org.lee.mugen.fight', 'GwtFightCmdProcess', 267);
function $estimateActionLength(this$static, action){
  var d, e, e$array, e$index, e$max, elems, grp, ticks;
  ticks = 0;
  try {
    grp = $getCurrentGroupSprite(this$static.anim, action);
    if (!grp) {
      return 24;
    }
    elems = grp._ImgSprArray;
    if (elems == null) {
      return 24;
    }
    for (e$array = elems , e$index = 0 , e$max = e$array.length; e$index < e$max; ++e$index) {
      e = e$array[e$index];
      d = e._airData.delayTick;
      ticks += d > 0?d:1;
    }
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 6)) {
      return 24;
    }
     else 
      throw toJs($e0);
  }
  return ticks > 0?ticks:24;
}

function $getCurrentImage(this$static){
  var elem, grp, img, spr;
  elem = $getCurrentImageSprite(this$static.anim);
  if (!elem || !elem._airData) {
    return null;
  }
  grp = elem._airData.grpNum;
  img = elem._airData.imgNum;
  if (grp < 0 || !$getGroupSpr(this$static.sff, grp)) {
    return null;
  }
  spr = $getImgSpr($getGroupSpr(this$static.sff, grp), img);
  if (!spr || !spr._image) {
    return null;
  }
  return spr._image;
}

function $getCurrentImageSpriteSFF(this$static){
  var elem, grp, img;
  elem = $getCurrentImageSprite(this$static.anim);
  if (!elem || !elem._airData) {
    return null;
  }
  grp = elem._airData.grpNum;
  img = elem._airData.imgNum;
  if (grp < 0 || !$getGroupSpr(this$static.sff, grp)) {
    return null;
  }
  return $getImgSpr($getGroupSpr(this$static.sff, grp), img);
}

function $hasAction(this$static, action){
  try {
    return !!$getCurrentGroupSprite(this$static.anim, action);
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 6)) {
      return false;
    }
     else 
      throw toJs($e0);
  }
}

function $process_2(this$static){
  $process_6(this$static.anim);
  this$static.actionTicks > 0 && --this$static.actionTicks;
}

function $restartAction(this$static, action){
  this$static.anim._action != action?$setAction_0(this$static.anim, action):$setAnimElem_0(this$static.anim, 0);
  this$static.actionTicks = $estimateActionLength(this$static, action);
}

function $setAction(this$static, action){
  if (this$static.anim._action != action) {
    $setAction_0(this$static.anim, action);
    this$static.actionTicks = $estimateActionLength(this$static, action);
  }
}

function $setActionIfExists(this$static, action, fallback){
  $hasAction(this$static, action)?$setAction(this$static, action):$hasAction(this$static, fallback)?$setAction(this$static, fallback):$setAction(this$static, 0);
}

function $setAnimElem(this$static, elem1Based){
  elem1Based > 0 && $setAnimElem_0(this$static.anim, elem1Based - 1);
}

function GwtFightFighterAnim(characterId, sffFile, airFile){
  var airPath, sffPath;
  sffPath = getExistFile_0(($clinit_JMugenConstant() , RESOURCE) + 'chars/' + characterId + '/' + sffFile);
  this.sff = new SpriteSFF(new SffReader_0(sffPath), false);
  airPath = getExistFile_0(RESOURCE + 'chars/' + characterId + '/' + airFile);
  this.anim = new SpriteAnimManager(new AirParser_0(airPath));
  $setAction_0(this.anim, 0);
  this.actionTicks = $estimateActionLength(this, 0);
}

defineClass(270, 1, {}, GwtFightFighterAnim);
_.actionTicks = 0;
var Lorg_lee_mugen_fight_GwtFightFighterAnim_2_classLit = createForClass('org.lee.mugen.fight', 'GwtFightFighterAnim', 270);
function attackHits(attackerAnim, attackerX, attackerY, attackerFacing, defenderAnim, defenderX, defenderY, defenderFacing){
  var a, a$array, a$index, a$max, aOrigin, ar, attack, dOrigin, elem, elem0, h, h$array, h$index, h$max, hr, hurt;
  attack = (elem0 = $getCurrentImageSprite(attackerAnim.anim) , elem0?elem0._airData.clsn1:initUnidimensionalArray(Lorg_lee_mugen_object_Rectangle_2_classLit, $intern_9, 25, 0, 0, 1));
  hurt = (elem = $getCurrentImageSprite(defenderAnim.anim) , elem?elem._airData.clsn2:initUnidimensionalArray(Lorg_lee_mugen_object_Rectangle_2_classLit, $intern_9, 25, 0, 0, 1));
  if (attack == null || attack.length == 0 || hurt == null || hurt.length == 0) {
    return false;
  }
  aOrigin = axisOrigin(attackerAnim, attackerX, attackerY, attackerFacing);
  dOrigin = axisOrigin(defenderAnim, defenderX, defenderY, defenderFacing);
  for (a$array = attack , a$index = 0 , a$max = a$array.length; a$index < a$max; ++a$index) {
    a = a$array[a$index];
    if (!a) {
      continue;
    }
    ar = toAxisBox(a, aOrigin[0], aOrigin[1], attackerFacing);
    for (h$array = hurt , h$index = 0 , h$max = h$array.length; h$index < h$max; ++h$index) {
      h = h$array[h$index];
      if (!h) {
        continue;
      }
      hr = toAxisBox(h, dOrigin[0], dOrigin[1], defenderFacing);
      if (ar[0] < hr[2] && ar[2] > hr[0] && ar[1] < hr[3] && ar[3] > hr[1]) {
        return true;
      }
    }
  }
  return false;
}

function axisOrigin(anim, x_0, y_0, facing){
  var elem, elem0, ox, oy;
  ox = x_0 + (elem0 = $getCurrentImageSprite(anim.anim) , (elem0?elem0._airData.xOffSet:0) * facing);
  oy = y_0 + (elem = $getCurrentImageSprite(anim.anim) , elem?elem._airData.yOffSet:0);
  return stampJavaTypeInfo(getClassLiteralForArray(F_classLit, 1), $intern_10, 5, 15, [ox, oy]);
}

function spriteDrawPos(anim, x_0, y_0, facing, cameraX, zoffset){
  var axisScreenX, axisScreenY, drawX, drawY, elem, flipH, h, img, origin_0, sff, w, xAxis, yAxis;
  img = $getCurrentImage(anim);
  sff = $getCurrentImageSpriteSFF(anim);
  w = img?img.width_0:0;
  h = img?img.height_0:0;
  xAxis = sff?sff._xAxis:w / 2 | 0;
  yAxis = sff?sff._yAxis:h;
  flipH = facing == -1 ^ (elem = $getCurrentImageSprite(anim.anim) , !!elem && elem._airData.isMirrorH);
  origin_0 = axisOrigin(anim, x_0, y_0, facing);
  axisScreenX = 160 + origin_0[0] + cameraX;
  axisScreenY = zoffset + origin_0[1];
  drawX = flipH?axisScreenX - (w - xAxis):axisScreenX - xAxis;
  drawY = axisScreenY - yAxis;
  return stampJavaTypeInfo(getClassLiteralForArray(F_classLit, 1), $intern_10, 5, 15, [drawX, drawY, flipH?1:0]);
}

function toAxisBox(r, axisX, axisY, facing){
  var bottom, left, right, top_0, x1, x2;
  x1 = r.x1;
  x2 = r.x2;
  if (facing < 0) {
    x1 = -x1;
    x2 = -x2;
  }
  left = $wnd.Math.min(x1, x2) + axisX;
  right = $wnd.Math.max(x1, x2) + axisX;
  top_0 = $wnd.Math.min(r.y1, r.y2) + axisY;
  bottom = $wnd.Math.max(r.y1, r.y2) + axisY;
  return stampJavaTypeInfo(getClassLiteralForArray(F_classLit, 1), $intern_10, 5, 15, [left, top_0, right, bottom]);
}

function $applyFaceKey(this$static, key, value_0){
  var p;
  if ($equals_5('p1.pos', key)) {
    this$static.p1FacePos = (p = $split($replaceAll(value_0, ' ', ''), ',') , new MugenPoint_0(__parseAndValidateInt(p[0], 10), __parseAndValidateInt(p[1], 10)));
  }
   else if ($equals_5('p2.pos', key)) {
    this$static.p2FacePos = (p = $split($replaceAll(value_0, ' ', ''), ',') , new MugenPoint_0(__parseAndValidateInt(p[0], 10), __parseAndValidateInt(p[1], 10)));
  }
   else if ($equals_5('p1.face.offset', key)) {
    this$static.p1FaceOffset = (p = $split($replaceAll(value_0, ' ', ''), ',') , new MugenPoint_0(__parseAndValidateInt(p[0], 10), __parseAndValidateInt(p[1], 10)));
  }
   else if ($equals_5('p2.face.offset', key)) {
    this$static.p2FaceOffset = (p = $split($replaceAll(value_0, ' ', ''), ',') , new MugenPoint_0(__parseAndValidateInt(p[0], 10), __parseAndValidateInt(p[1], 10)));
  }
   else if ($equals_5('p1.face.facing', key)) {
    this$static.p1FaceFacing = __parseAndValidateInt($trim(value_0), 10);
  }
   else if ($equals_5('p2.face.facing', key)) {
    this$static.p2FaceFacing = __parseAndValidateInt($trim(value_0), 10);
  }
   else if ($equals_5('p1.bg.spr', key) || $equals_5('p2.bg.spr', key)) {
    p = $split($replaceAll(value_0, ' ', ''), ',');
    if (p.length >= 2) {
      this$static.faceBgGrp = __parseAndValidateInt(p[0], 10);
      this$static.faceBgImg = __parseAndValidateInt(p[1], 10);
    }
  }
}

function $applyFilesKey(this$static, key, value_0){
  var in_0, num, resolved, sNum;
  if (!$equals_5(key.substr(0, 4), 'font')) {
    return;
  }
  sNum = key.length > 4?(checkCriticalStringElementIndex(4, key.length + 1) , key.substr(4)):'';
  if (sNum.length == 0) {
    return;
  }
  try {
    num = __parseAndValidateInt(sNum, 10);
    resolved = resolveFightFontPath($replace(value_0));
    if (resolved == null) {
      return;
    }
    $put_4(this$static.fightFonts, valueOf_1(num), ($clinit_FontParser() , in_0 = openBinary(resolved) , parse_20(toByteArray(in_0))));
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (!instanceOf($e0, 6))
      throw toJs($e0);
  }
}

function $applyNameKey(this$static, key, value_0){
  var f, p;
  if ($equals_5('p1.pos', key)) {
    this$static.p1NamePos = (p = $split($replaceAll(value_0, ' ', ''), ',') , new MugenPoint_0(__parseAndValidateInt(p[0], 10), __parseAndValidateInt(p[1], 10)));
  }
   else if ($equals_5('p2.pos', key)) {
    this$static.p2NamePos = (p = $split($replaceAll(value_0, ' ', ''), ',') , new MugenPoint_0(__parseAndValidateInt(p[0], 10), __parseAndValidateInt(p[1], 10)));
  }
   else if ($equals_5('p1.name.font', key)) {
    f = parseFont(value_0);
    this$static.p1NameFontNo = f[0];
    this$static.p1NameFontBank = f[1];
    this$static.p1NameAlign = f[2];
  }
   else if ($equals_5('p2.name.font', key)) {
    f = parseFont(value_0);
    this$static.p2NameFontNo = f[0];
    this$static.p2NameFontBank = f[1];
    this$static.p2NameAlign = f[2];
  }
}

function $applyTimeKey(this$static, key, value_0){
  var p;
  if ($equals_5('pos', key)) {
    this$static.timePos = (p = $split($replaceAll(value_0, ' ', ''), ',') , new MugenPoint_0(__parseAndValidateInt(p[0], 10), __parseAndValidateInt(p[1], 10)));
  }
   else if ($equals_5('counter.font', key)) {
    p = $split($replaceAll(value_0, ' ', ''), ',');
    p.length >= 1 && (this$static.timeFontNo = __parseAndValidateInt(p[0], 10));
    p.length >= 2 && (this$static.timeFontBank = __parseAndValidateInt(p[1], 10));
    p.length >= 3 && (this$static.timeAlign = __parseAndValidateInt(p[2], 10));
  }
   else 
    $equals_5('framespercount', key) && (this$static.framesPerCount = $wnd.Math.max(1, __parseAndValidateInt($trim(value_0), 10)));
}

function $drawBannerSpr(this$static, md, posY, grp){
  var sff, x_0, y_0;
  if (!this$static.fightSff) {
    return;
  }
  sff = $getSpr_0(this$static, grp, 0);
  if (!sff) {
    grp == 80?$renderBannerText(this$static, md, 'FIGHT!', posY):grp == 200 && $renderBannerText(this$static, md, 'K.O.', posY);
    return;
  }
  x_0 = 160 - sff._xAxis;
  y_0 = posY - sff._yAxis;
  $draw_1(md, new DrawProperties_0(x_0, y_0, false, false, sff._image));
}

function $drawFace(this$static, md, pos, offset, facing, portrait, xAxis, yAxis, p2){
  var flipH, x_0, y_0;
  $drawFaceSpr(this$static, md, pos, this$static.faceBgGrp, this$static.faceBgImg, p2);
  if (!portrait) {
    return;
  }
  flipH = facing < 0;
  y_0 = pos.y_0 - yAxis + offset.y_0;
  flipH?(x_0 = pos.x_0 - portrait.width_0 + xAxis + offset.x_0):(x_0 = pos.x_0 - xAxis + offset.x_0);
  $draw_1(md, new DrawProperties_0(x_0, y_0, flipH, false, portrait));
}

function $drawFaceSpr(this$static, md, pos, grp, img, flipH){
  var sff, x_0, y_0;
  sff = $getSpr_0(this$static, grp, img);
  if (!sff) {
    return;
  }
  x_0 = flipH?pos.x_0 - sff._image.width_0 + sff._xAxis:pos.x_0 - sff._xAxis;
  y_0 = pos.y_0 - sff._yAxis;
  $draw_1(md, new DrawProperties_0(x_0, y_0, flipH, false, sff._image));
}

function $drawLifeBar(this$static, md, pos, life, maxLife, rangeMax, flip){
  $drawSpr(this$static, md, pos, 11, flip);
  $drawSpr(this$static, md, pos, 12, flip);
  $drawSprMeter(this$static, md, pos, 13, life, maxLife, rangeMax, flip);
}

function $drawName_0(this$static, md, name_0, pos, fontNo, fontBank, align_0){
  var font;
  if (name_0 == null || name_0.length == 0) {
    return;
  }
  font = $resolveFont(this$static, fontNo);
  !font && (font = $resolveFont(this$static, 2));
  if (!font) {
    return;
  }
  $draw_2(font, fontBank, pos.x_0, pos.y_0, md, name_0, align_0, 1);
}

function $drawPowerBar(this$static, md, pos, power, rangeMax, flip){
  $drawSpr(this$static, md, pos, 41, flip);
  $drawSprMeter(this$static, md, pos, 43, power, 3000, rangeMax, flip);
}

function $drawSpr(this$static, md, pos, grp, flipH){
  var sff, x_0, y_0;
  sff = $getSpr_0(this$static, grp, 0);
  if (!sff) {
    return;
  }
  x_0 = flipH?pos.x_0 + sff._image.width_0 - sff._xAxis:pos.x_0 - sff._xAxis;
  y_0 = pos.y_0 - sff._yAxis;
  $draw_1(md, new DrawProperties_0(x_0, y_0, flipH, false, sff._image));
}

function $drawSprMeter(this$static, md, pos, grp, value_0, maxValue, rangeMax, flipH){
  var delta, dp, fillPx, sff;
  sff = $getSpr_0(this$static, grp, 0);
  if (!sff) {
    return;
  }
  fillPx = maxValue > 0?value_0 * rangeMax / maxValue | 0:0;
  if (flipH) {
    dp = new DrawProperties_0(pos.x_0 + sff._image.width_0 - sff._xAxis, pos.y_0 - sff._yAxis, true, false, sff._image);
    delta = $wnd.Math.min(fillPx, sff._image.width_0);
    $setXRightDst(dp, dp.xLeftDst + sff._image.width_0);
    $setXLeftDst(dp, dp.xRightDst - delta);
    $setXLeftSrc(dp, $getXRightSrc(dp) - delta);
  }
   else {
    dp = new DrawProperties_0(pos.x_0 - sff._xAxis, pos.y_0 - sff._yAxis, false, false, sff._image);
    delta = $wnd.Math.min(fillPx, sff._image.width_0);
    $setXRightDst(dp, dp.xLeftDst + delta);
    $setXRightSrc(dp, $getXLeftSrc(dp) + delta);
  }
  $draw_1(md, dp);
}

function $getSpr_0(this$static, grp, img){
  if (!$getGroupSpr(this$static.fightSff, grp)) {
    return null;
  }
  return $getImgSpr($getGroupSpr(this$static.fightSff, grp), img);
}

function $parse_2(this$static){
  var defPath, sffPath;
  sffPath = getExistFile_0(($clinit_JMugenConstant() , RESOURCE) + 'data/fight.sff');
  this$static.fightSff = new SpriteSFF(new SffReader_0(sffPath), false);
  try {
    defPath = getExistFile_0(RESOURCE + 'data/fight.def');
    parse_0(defPath, this$static);
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 6)) {
      this$static.p1pos = new MugenPoint_0(140, 12);
      this$static.p2pos = new MugenPoint_0(178, 12);
      this$static.p1rangeMax = 127;
      this$static.p2rangeMax = 127;
    }
     else 
      throw toJs($e0);
  }
}

function $render_9(this$static, md, p1Life, p2Life, maxLife, p1Power, p2Power){
  if (!this$static.fightSff) {
    return;
  }
  $drawLifeBar(this$static, md, this$static.p1pos, p1Life, maxLife, this$static.p1rangeMax, false);
  $drawLifeBar(this$static, md, this$static.p2pos, p2Life, maxLife, this$static.p2rangeMax, true);
  $drawPowerBar(this$static, md, this$static.p1powerPos, p1Power, this$static.p1powerRangeMax, false);
  $drawPowerBar(this$static, md, this$static.p2powerPos, p2Power, this$static.p2powerRangeMax, true);
  $drawFace(this$static, md, this$static.p1FacePos, this$static.p1FaceOffset, this$static.p1FaceFacing, this$static.p1Face, this$static.p1FaceXAxis, this$static.p1FaceYAxis, false);
  $drawFace(this$static, md, this$static.p2FacePos, this$static.p2FaceOffset, this$static.p2FaceFacing, this$static.p2Face, this$static.p2FaceXAxis, this$static.p2FaceYAxis, true);
  $drawName_0(this$static, md, this$static.p1Name, this$static.p1NamePos, this$static.p1NameFontNo, this$static.p1NameFontBank, this$static.p1NameAlign);
  $drawName_0(this$static, md, this$static.p2Name, this$static.p2NamePos, this$static.p2NameFontNo, this$static.p2NameFontBank, this$static.p2NameAlign);
}

function $renderBannerText(this$static, md, text_0, y_0){
  var font, i, tx;
  if (text_0 == null || text_0.length == 0) {
    return;
  }
  font = $resolveFont(this$static, 1);
  !font && (font = resolveSystemFont(3));
  !font && (font = resolveSystemFont(2));
  if (font) {
    $drawCenter(font, 160, y_0, md, text_0, 0, 1);
    return;
  }
  $setColor_3(md, 0, 0, 0, 0.5);
  $fillRect(md, 80, y_0 - 14, 160, 28);
  $setColor_3(md, 1, 1, 1, 1);
  tx = 160 - text_0.length * 4;
  for (i = 0; i < text_0.length; i++) {
    $fillRect(md, tx + i * 8, y_0 - 8, 6, 10);
  }
}

function $renderTimer(this$static, md, ticksLeft){
  var font, i, seconds, text_0, x_0;
  if (ticksLeft < 0) {
    return;
  }
  seconds = ticksLeft / this$static.framesPerCount | 0;
  text_0 = '' + seconds;
  font = $resolveFont(this$static, this$static.timeFontNo);
  if (font) {
    $draw_2(font, this$static.timeFontBank, this$static.timePos.x_0, this$static.timePos.y_0, md, text_0, this$static.timeAlign, 1);
    return;
  }
  $setColor_3(md, 1, 1, 1, 1);
  x_0 = this$static.timePos.x_0 - text_0.length * 3;
  $fillRect(md, x_0 - 2, this$static.timePos.y_0 - 10, text_0.length * 7 + 4, 12);
  $setColor_3(md, $intern_8, $intern_8, $intern_8, 1);
  for (i = 0; i < text_0.length; i++) {
    drawDigitFallback(md, x_0 + i * 7, this$static.timePos.y_0 - 8, (checkCriticalStringElementIndex(i, text_0.length) , text_0.charCodeAt(i)));
  }
}

function $resolveFont(this$static, fontNo){
  var fight;
  if (fontNo < 0) {
    return null;
  }
  fight = castTo($get_8(this$static.fightFonts, valueOf_1(fontNo)), 114);
  if (fight) {
    return fight;
  }
  return resolveSystemFont(fontNo);
}

function $setFaces(this$static, p1, p1XAxis, p1YAxis, p2, p2XAxis, p2YAxis){
  this$static.p1Face = p1;
  this$static.p1FaceXAxis = p1XAxis;
  this$static.p1FaceYAxis = p1YAxis;
  this$static.p2Face = p2;
  this$static.p2FaceXAxis = p2XAxis;
  this$static.p2FaceYAxis = p2YAxis;
}

function $setFaces_0(this$static, p1, p2){
  $setFaces(this$static, p1?p1._image:null, p1?p1._xAxis:0, p1?p1._yAxis:0, p2?p2._image:null, p2?p2._xAxis:0, p2?p2._yAxis:0);
}

function $setNames(this$static, p1, p2){
  this$static.p1Name = p1 != null?p1:'';
  this$static.p2Name = p2 != null?p2:'';
}

function GwtFightHud(){
  this.p1pos = new MugenPoint_0(140, 12);
  this.p2pos = new MugenPoint_0(178, 12);
  this.p1powerPos = new MugenPoint_0(140, 22);
  this.p2powerPos = new MugenPoint_0(178, 22);
  this.timePos = new MugenPoint_0(160, 23);
  this.p1FacePos = new MugenPoint_0(2, 12);
  this.p2FacePos = new MugenPoint_0(316, 12);
  this.p1FaceOffset = new MugenPoint_0(0, 10);
  this.p2FaceOffset = new MugenPoint_0(0, 10);
  this.p1NamePos = new MugenPoint_0(14, 10);
  this.p2NamePos = new MugenPoint_0(305, 10);
  this.fightFonts = new HashMap;
}

function drawDigitFallback(md, x_0, y_0, c){
  if (c < 48 || c > 57) {
    return;
  }
  $fillRect(md, x_0, y_0, 5, 8);
}

function parseFont(value_0){
  var align_0, bank, no, p;
  p = $split($replaceAll(value_0, ' ', ''), ',');
  no = p.length >= 1?__parseAndValidateInt(p[0], 10):3;
  bank = p.length >= 2?__parseAndValidateInt(p[1], 10):0;
  align_0 = p.length >= 3?__parseAndValidateInt(p[2], 10):0;
  return stampJavaTypeInfo(getClassLiteralForArray(I_classLit, 1), $intern_3, 5, 15, [no, bank, align_0]);
}

function resolveFightFontPath(relative){
  var c, c$array, c$index, c$max, candidates;
  candidates = stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_String_2_classLit, 1), $intern_11, 2, 6, [($clinit_JMugenConstant() , RESOURCE) + 'data/' + relative, RESOURCE + ('' + relative), relative]);
  for (c$array = candidates , c$index = 0 , c$max = c$array.length; c$index < c$max; ++c$index) {
    c = c$array[c$index];
    try {
      return getExistFile_0(c);
    }
     catch ($e0) {
      $e0 = toJava($e0);
      if (!instanceOf($e0, 6))
        throw toJs($e0);
    }
  }
  return null;
}

function resolveSystemFont(fontNo){
  var fonts, sys;
  if (fontNo < 0) {
    return null;
  }
  sys = getInstance();
  if (!sys || !sys.files) {
    return null;
  }
  fonts = sys.files.font;
  return castTo($get_8(fonts, valueOf_1(fontNo)), 114);
}

defineClass(504, 1, {}, GwtFightHud);
_.faceBgGrp = 50;
_.faceBgImg = 0;
_.framesPerCount = 60;
_.p1FaceFacing = 1;
_.p1FaceXAxis = 0;
_.p1FaceYAxis = 0;
_.p1Name = '';
_.p1NameAlign = 1;
_.p1NameFontBank = 0;
_.p1NameFontNo = 3;
_.p1powerRangeMax = 107;
_.p1rangeMax = 127;
_.p2FaceFacing = -1;
_.p2FaceXAxis = 0;
_.p2FaceYAxis = 0;
_.p2Name = '';
_.p2NameAlign = -1;
_.p2NameFontBank = 0;
_.p2NameFontNo = 3;
_.p2powerRangeMax = 107;
_.p2rangeMax = 127;
_.timeAlign = 0;
_.timeFontBank = 0;
_.timeFontNo = 2;
var Lorg_lee_mugen_fight_GwtFightHud_2_classLit = createForClass('org.lee.mugen.fight', 'GwtFightHud', 504);
function parse_0(path, hud){
  var br, eq, key, line, p, p0, p1, p2, p3, p4, p5, p6, reader, section, semi, suffixlength, value_0;
  reader = openUtf8Reader(path);
  br = new BufferedReader(reader);
  section = '';
  while ((line = $readLine(br)) != null) {
    line = $trim(line);
    if (line.length == 0 || $equals_5(line.substr(0, 1), ';')) {
      continue;
    }
    if ($equals_5(line.substr(0, 1), '[') && (suffixlength = ']'.length , $equals_5(line.substr(line.length - suffixlength, suffixlength), ']'))) {
      section = $trim($substring_0(line, 1, line.length - 1)).toLowerCase();
      continue;
    }
    if (!$equals_5('files', section) && !$equals_5('lifebar', section) && !$equals_5('powerbar', section) && !$equals_5('time', section) && !$equals_5('face', section) && !$equals_5('name', section)) {
      continue;
    }
    eq = $indexOf_0(line, fromCodePoint(61));
    if (eq < 0) {
      continue;
    }
    key = $trim((checkCriticalStringBounds(0, eq, line.length) , line.substr(0, eq))).toLowerCase();
    value_0 = $trim((checkCriticalStringElementIndex(eq + 1, line.length + 1) , line.substr(eq + 1)));
    semi = $indexOf_0(value_0, fromCodePoint(59));
    semi >= 0 && (value_0 = $trim((checkCriticalStringBounds(0, semi, value_0.length) , value_0.substr(0, semi))));
    $equals_5('files', section)?$applyFilesKey(hud, key, value_0):$equals_5('lifebar', section)?$equals_5(key.substr(0, 6), 'p1.pos')?(hud.p1pos = (p0 = $split($replaceAll(value_0, ' ', ''), ',') , new MugenPoint_0(__parseAndValidateInt(p0[0], 10), __parseAndValidateInt(p0[1], 10)))):$equals_5(key.substr(0, 6), 'p2.pos')?(hud.p2pos = (p1 = $split($replaceAll(value_0, ' ', ''), ',') , new MugenPoint_0(__parseAndValidateInt(p1[0], 10), __parseAndValidateInt(p1[1], 10)))):$equals_5(key.substr(0, 10), 'p1.range.x')?(hud.p1rangeMax = (p2 = $split($replaceAll(value_0, ' ', ''), ',') , $wnd.Math.abs(__parseAndValidateInt(p2[1], 10)))):$equals_5(key.substr(0, 10), 'p2.range.x') && (hud.p2rangeMax = (p3 = $split($replaceAll(value_0, ' ', ''), ',') , $wnd.Math.abs(__parseAndValidateInt(p3[1], 10)))):$equals_5('powerbar', section)?$equals_5(key.substr(0, 6), 'p1.pos')?(hud.p1powerPos = (p4 = $split($replaceAll(value_0, ' ', ''), ',') , new MugenPoint_0(__parseAndValidateInt(p4[0], 10), __parseAndValidateInt(p4[1], 10)))):$equals_5(key.substr(0, 6), 'p2.pos')?(hud.p2powerPos = (p5 = $split($replaceAll(value_0, ' ', ''), ',') , new MugenPoint_0(__parseAndValidateInt(p5[0], 10), __parseAndValidateInt(p5[1], 10)))):$equals_5(key.substr(0, 10), 'p1.range.x')?(hud.p1powerRangeMax = (p6 = $split($replaceAll(value_0, ' ', ''), ',') , $wnd.Math.abs(__parseAndValidateInt(p6[1], 10)))):$equals_5(key.substr(0, 10), 'p2.range.x') && (hud.p2powerRangeMax = (p = $split($replaceAll(value_0, ' ', ''), ',') , $wnd.Math.abs(__parseAndValidateInt(p[1], 10)))):$equals_5('time', section)?$applyTimeKey(hud, key, value_0):$equals_5('face', section)?$applyFaceKey(hud, key, value_0):$applyNameKey(hud, key, value_0);
  }
}

function $applyGroundMovement(this$static, moveLeft, moveRight, toward, away, down){
  this$static.running = false;
  if (down) {
    if (this$static.currentAction != 11 && this$static.currentAction != 10) {
      this$static.currentAction = 10;
      $setAction(this$static.anim, 10);
    }
     else 
      this$static.currentAction == 10 && this$static.anim.actionTicks <= 0 && (this$static.currentAction = 11 , $setAction(this$static.anim, 11));
    return;
  }
  if (this$static.currentAction == 11 || this$static.currentAction == 10) {
    this$static.currentAction = 0;
    $setAction(this$static.anim, 0);
  }
  if (moveRight && !moveLeft) {
    this$static.x_0 += this$static.cns.walkFwd > 0?this$static.cns.walkFwd:$intern_57;
    if (toward) {
      if (this$static.currentAction != 20) {
        this$static.currentAction = 20;
        $setAction(this$static.anim, 20);
      }
    }
     else if (away) {
      if (this$static.currentAction != 21) {
        this$static.currentAction = 21;
        $setAction(this$static.anim, 21);
      }
    }
     else if (this$static.currentAction != 20) {
      this$static.currentAction = 20;
      $setAction(this$static.anim, 20);
    }
  }
   else if (moveLeft && !moveRight) {
    this$static.x_0 -= this$static.cns.walkFwd > 0?this$static.cns.walkFwd:$intern_57;
    if (toward) {
      if (this$static.currentAction != 20) {
        this$static.currentAction = 20;
        $setAction(this$static.anim, 20);
      }
    }
     else if (away) {
      if (this$static.currentAction != 21) {
        this$static.currentAction = 21;
        $setAction(this$static.anim, 21);
      }
    }
     else if (this$static.currentAction != 21) {
      this$static.currentAction = 21;
      $setAction(this$static.anim, 21);
    }
  }
   else if (this$static.currentAction != 0 && this$static.currentAction != 5) {
    this$static.currentAction = 0;
    $setAction(this$static.anim, 0);
  }
}

function $applyPhysics(this$static){
  if (this$static.jumpPhase == 1) {
    return;
  }
  if (!this$static.onGround || this$static.jumpPhase == 2) {
    this$static.jumpPhase == 2 && (this$static.x_0 += this$static.vx);
    this$static.vy += this$static.cns.yAccel > 0?this$static.cns.yAccel:0.44999998807907104;
    this$static.y_0 += this$static.vy;
    if (this$static.y_0 >= this$static.groundY) {
      this$static.y_0 = this$static.groundY;
      if (this$static.hitPhase == 3 || this$static.hitPhase == 4 || this$static.hitPhase == 5) {
        this$static.onGround = true;
      }
       else if (this$static.jumpPhase == 2) {
        this$static.onGround = true;
        this$static.vy = 0;
        this$static.vx = 0;
        if (this$static.inAttack && this$static.currentAction >= 600) {
          this$static.jumpPhase = 0;
        }
         else {
          this$static.jumpPhase = 3;
          this$static.inAttack = false;
          if ($hasAction(this$static.anim, 47)) {
            this$static.currentAction = 47;
            $setAction(this$static.anim, 47);
          }
           else {
            this$static.jumpPhase = 0;
            this$static.currentAction = 0;
            $setAction(this$static.anim, 0);
          }
        }
      }
       else {
        this$static.vy = 0;
        this$static.onGround = true;
        if (!this$static.inAttack && this$static.hitPhase == 0 && this$static.currentAction >= 600) {
          this$static.currentAction = 0;
          $setAction(this$static.anim, 0);
        }
      }
    }
  }
}

function $beginJump(this$static, moveLeft, moveRight){
  this$static.jumpPhase = 1;
  this$static.jumpDir = 0;
  moveRight && !moveLeft?(this$static.jumpDir = 1):moveLeft && !moveRight && (this$static.jumpDir = -1);
  this$static.inAttack = false;
  this$static.onGround = true;
  this$static.currentAction = 40;
  $setAction(this$static.anim, 40);
}

function $clampToScreen(this$static, cameraX){
  var screenX_0;
  screenX_0 = 160 + this$static.x_0 + cameraX;
  screenX_0 < 28?(this$static.x_0 += 28 - screenX_0):screenX_0 > 292 && (this$static.x_0 -= screenX_0 - 292);
  this$static.x_0 < this$static.stageLeft && (this$static.x_0 = this$static.stageLeft);
  this$static.x_0 > this$static.stageRight && (this$static.x_0 = this$static.stageRight);
}

function $clearHit(this$static){
  this$static.hitPhase = 0;
  this$static.hitTimer = 0;
  this$static.hitSlideVx = 0;
  this$static.hitSlideTime = 0;
  this$static.vx = 0;
  this$static.currentAction = 0;
  $setAction(this$static.anim, 0);
  this$static.inAttack = false;
  this$static.jumpPhase = 0;
}

function $clearTowardTap(this$static){
  this$static.towardTapArmed = false;
  this$static.towardSawRelease = false;
  this$static.towardTapTimer = 0;
}

function $debugSummary(this$static){
  return 'P' + this$static.cmdId + ' life=' + this$static.life + ' pow=' + this$static.power + ' act=' + this$static.currentAction + ' pos=' + round_int(this$static.x_0) + ',' + round_int(this$static.y_0) + ' face=' + this$static.facing + ' ctrl=' + this$static.ctrlEnabled;
}

function $desiredFacing(this$static, opponent){
  var dx;
  dx = opponent.x_0 - this$static.x_0;
  if (dx > 28) {
    return 1;
  }
  if (dx < -28) {
    return -1;
  }
  return 0;
}

function $draw_0(this$static, md, cameraX){
  var flipH, pos, shakeX, sprite;
  sprite = $getCurrentImage(this$static.anim);
  if (!sprite) {
    return;
  }
  pos = spriteDrawPos(this$static.anim, this$static.x_0, this$static.y_0, this$static.facing, cameraX, this$static.zoffset);
  flipH = pos[2] > 0.5;
  shakeX = 0;
  this$static.hitPhase == 1 && this$static.hitTimer > 0 && (shakeX = (this$static.hitTimer & 1) == 0?1:-1);
  $draw_1(md, new DrawProperties_0(pos[0] + shakeX, pos[1], flipH, false, sprite));
}

function $drawAxisBoxes(this$static, md, boxes, axisX, axisY, face_0, cameraX){
  var b, r, r$array, r$index, r$max, x1, x2, y1, y2;
  if (boxes == null) {
    return;
  }
  for (r$array = boxes , r$index = 0 , r$max = r$array.length; r$index < r$max; ++r$index) {
    r = r$array[r$index];
    if (!r) {
      continue;
    }
    b = toAxisBox(r, axisX, axisY, face_0);
    x1 = 160 + round_int(b[0]) + round_int(cameraX);
    y1 = this$static.zoffset + round_int(b[1]);
    x2 = 160 + round_int(b[2]) + round_int(cameraX);
    y2 = this$static.zoffset + round_int(b[3]);
    $drawRect(md, x1, y1, x2 - x1, y2 - y1);
  }
}

function $drawDebugAttackCns(this$static, md, cameraX){
  var elem, origin_0;
  origin_0 = axisOrigin(this$static.anim, this$static.x_0, this$static.y_0, this$static.facing);
  $setColor_3(md, 1, 0, 0, 1);
  $drawAxisBoxes(this$static, md, (elem = $getCurrentImageSprite(this$static.anim.anim) , elem?elem._airData.clsn1:initUnidimensionalArray(Lorg_lee_mugen_object_Rectangle_2_classLit, $intern_9, 25, 0, 0, 1)), origin_0[0], origin_0[1], this$static.facing, cameraX);
}

function $drawDebugCns(this$static, md, cameraX){
  var elem, origin_0, ox, oy;
  origin_0 = axisOrigin(this$static.anim, this$static.x_0, this$static.y_0, this$static.facing);
  ox = 160 + round_int(origin_0[0]) + round_int(cameraX);
  oy = this$static.zoffset + round_int(origin_0[1]);
  $setColor_3(md, 1, 0, 1, 1);
  $drawLine(md, ox - 5, oy, ox + 5, oy);
  $drawLine(md, ox, oy - 5, ox, oy + 5);
  $setColor_3(md, 0, 1, 0, 1);
  $drawAxisBoxes(this$static, md, (elem = $getCurrentImageSprite(this$static.anim.anim) , elem?elem._airData.clsn2:initUnidimensionalArray(Lorg_lee_mugen_object_Rectangle_2_classLit, $intern_9, 25, 0, 0, 1)), origin_0[0], origin_0[1], this$static.facing, cameraX);
}

function $drawShadow(this$static, md, cameraX){
  var dp, elem, flipH, flipV, fx, mulA, pos, shadowY, sprite;
  if (this$static.shadowIntensity <= 0) {
    return;
  }
  sprite = $getCurrentImage(this$static.anim);
  if (!sprite) {
    return;
  }
  pos = spriteDrawPos(this$static.anim, this$static.x_0, this$static.y_0, this$static.facing, cameraX, this$static.zoffset);
  flipH = pos[2] > 0.5;
  flipV = (elem = $getCurrentImageSprite(this$static.anim.anim) , !(!!elem && elem._airData.isMirrorV));
  shadowY = this$static.zoffset - this$static.y_0 * $intern_8;
  dp = new DrawProperties_0(pos[0], shadowY, flipH, flipV, sprite);
  dp.xScaleFactor = 1;
  $setYScaleFactor(dp, this$static.shadowYScale);
  dp.alpha_0 = 1;
  fx = new PalFxSub;
  mulA = 40 * (this$static.shadowIntensity / 128);
  mulA > 80 && (mulA = 80);
  $setMul(fx, new RGB_1(0, 0, 0, mulA));
  new RGB_1(255, 255, 255, 255);
  dp.palfx = fx;
  if (dp.yTopDst < this$static.zoffset) {
    return;
  }
  $draw_1(md, dp);
}

function $enterAttackState(this$static, state){
  this$static.inAttack = true;
  this$static.attackHit = false;
  this$static.attackHitsDone = 0;
  this$static.lastHitAnimElem = 0;
  this$static.running = false;
  this$static.crouching = state >= 400 && state < 600;
  this$static.currentAction = state;
  $restartAction(this$static.anim, state);
  $changeState_0(this$static.cnsRuntime, state, this$static.cnsHost);
}

function $enterBasicAttack(this$static, punch, strongPunch, kick, strongKick, down){
  if (!punch && !strongPunch && !kick && !strongKick) {
    return;
  }
  if (down && this$static.onGround) {
    strongKick?$enterAttackState(this$static, $resolveAttack(this$static, 'b', 440)):kick?$enterAttackState(this$static, $resolveAttack(this$static, 'a', 430)):strongPunch?$enterAttackState(this$static, $resolveAttack(this$static, 'y', 410)):$enterAttackState(this$static, $resolveAttack(this$static, 'x', 400));
    return;
  }
  if (!this$static.onGround) {
    strongKick?$enterAttackState(this$static, 640):kick?$enterAttackState(this$static, 630):strongPunch?$enterAttackState(this$static, 610):$enterAttackState(this$static, 600);
    return;
  }
  strongKick?$enterAttackState(this$static, $resolveAttack(this$static, 'b', 240)):kick?$enterAttackState(this$static, $resolveAttack(this$static, 'a', 230)):strongPunch?$enterAttackState(this$static, $resolveAttack(this$static, 'y', 210)):$enterAttackState(this$static, $resolveAttack(this$static, 'x', 200));
}

function $enterCrouchIdle(this$static){
  this$static.currentAction = 11;
  $setAction(this$static.anim, 11);
}

function $hitParamsFor(this$static, state, hitIndex){
  var contactElem, hd, maxHits;
  hd = this$static.cns?$getHitDef(this$static.cns, state, hitIndex):null;
  if (hd) {
    maxHits = $wnd.Math.max(1, $getHitDefCount(this$static.cns, state));
    contactElem = state == 210?6:0;
    return new GwtFightPlayer$HitParams_0(hd.damage, hd.pauseP1, hd.pauseP2, hd.groundVelocityX, hd.airVelocityX, hd.airVelocityY, hd.groundSlideTime, hd.groundHitTime, maxHits, hd.animElem, contactElem, hd.trip || hd.fall, hd.animType);
  }
  return hitParamsFallback(state, hitIndex);
}

function $jumpAirAnim(this$static){
  var relative;
  relative = this$static.jumpDir * this$static.facing;
  if (relative > 0) {
    return $hasAction(this$static.anim, 42)?42:41;
  }
  if (relative < 0) {
    return $hasAction(this$static.anim, 43)?43:41;
  }
  return 41;
}

function $maybeAutoTurn(this$static, opponent){
  var want;
  if (!opponent || !this$static.onGround || this$static.running || this$static.turning || this$static.inAttack) {
    return;
  }
  if (this$static.jumpPhase != 0 || this$static.turnCooldown > 0) {
    return;
  }
  if (this$static.currentAction == 11 || this$static.currentAction == 10) {
    return;
  }
  want = $desiredFacing(this$static, opponent);
  want != 0 && want != this$static.facing && $startTurn(this$static);
}

function $onCommand(this$static, commandName){
  if (commandName == null) {
    return;
  }
  if ($equals_5('holdfwd', commandName)) {
    return;
  }
  if ($equals_5('holdback', commandName)) {
    return;
  }
  if ($equals_5('holddown', commandName)) {
    this$static.cmdHoldDown = true;
    return;
  }
  if ($equals_5('holdup', commandName)) {
    this$static.cmdHoldUp = true;
    return;
  }
  if (!this$static.ctrlEnabled || this$static.inAttack || this$static.hitPhase != 0 || this$static.jumpPhase == 1 || this$static.turning) {
    return;
  }
  if ($equals_5('FF', commandName)) {
    this$static.pendingSpecial = 'FF';
    return;
  }
  if ($equals_5('BB', commandName)) {
    this$static.pendingSpecial = 'BB';
    return;
  }
  if ($equals_5('a', commandName) || $equals_5('b', commandName) || $equals_5('c', commandName) || $equals_5('x', commandName) || $equals_5('y', commandName) || $equals_5('z', commandName) || $equals_5('start', commandName) || $equals_5('recovery', commandName) || $equals_5('down_a', commandName) || $equals_5('down_b', commandName)) {
    return;
  }
  this$static.pendingSpecial = commandName;
}

function $playHitSound(this$static, blocked){
  var grp, hd, num;
  if (!this$static.audio) {
    return;
  }
  hd = this$static.cns?$getHitDef(this$static.cns, this$static.currentAction, this$static.attackHitsDone):null;
  if (blocked) {
    grp = hd?hd.guardsoundGrp:6;
    num = hd?hd.guardsoundNum:0;
    (!hd || !hd.guardsoundFromSprite) && $playCommon(this$static.audio, grp, num);
  }
   else {
    grp = hd?hd.hitsoundGrp:5;
    num = hd?hd.hitsoundNum:0;
    (!hd || !hd.hitsoundFromSprite) && $playCommon(this$static.audio, grp, num);
  }
}

function $receiveHit(this$static, damage, attackerFacing, attackState, wasBlocking, hp){
  var airAnim, animType, hardKnockdown;
  this$static.inAttack = false;
  this$static.attackHit = false;
  this$static.attackHitsDone = 0;
  this$static.lastHitAnimElem = 0;
  this$static.pendingSpecial = null;
  this$static.running = false;
  this$static.turning = false;
  if (wasBlocking) {
    this$static.jumpPhase = 0;
    this$static.hitPhase = 8;
    this$static.hitTimer = $wnd.Math.max(8, hp.hitTime / 2 | 0);
    this$static.hitSlideTime = 4;
    this$static.hitSlideVx = attackerFacing * $intern_58;
    this$static.vx = this$static.hitSlideVx;
    this$static.currentAction = 150;
    $setActionIfExists(this$static.anim, 150, 130);
    return;
  }
  hardKnockdown = damage >= 100 || attackState >= 1000 || this$static.life <= 0 || hp.trip;
  if (hardKnockdown) {
    this$static.jumpPhase = 0;
    this$static.hitPhase = 3;
    this$static.hitTimer = $wnd.Math.max(16, hp.hitTime);
    this$static.onGround = false;
    this$static.vx = attackerFacing * $wnd.Math.max(hp.groundVx, 1.5);
    this$static.vy = hp.trip?-2:-6;
    this$static.currentAction = 5030;
    $setActionIfExists(this$static.anim, 5030, 5002);
    return;
  }
  if (!this$static.onGround) {
    this$static.jumpPhase = 0;
    this$static.hitPhase = 3;
    this$static.hitTimer = $wnd.Math.max(10, hp.hitTime);
    this$static.onGround = false;
    this$static.vx = attackerFacing * hp.airVx;
    this$static.vy = hp.airVy;
    airAnim = 5020 + hp.animType;
    this$static.currentAction = airAnim;
    $setActionIfExists(this$static.anim, airAnim, 5030);
    return;
  }
  this$static.jumpPhase = 0;
  this$static.hitPhase = 1;
  this$static.hitTimer = $wnd.Math.max(1, hp.pauseP2);
  this$static.hitGroundTime = $wnd.Math.max(6, hp.hitTime);
  this$static.hitSlideTime = $wnd.Math.max(1, hp.slideTime);
  this$static.hitSlideVx = attackerFacing * hp.groundVx;
  this$static.vx = 0;
  animType = hp.animType;
  if (this$static.crouching) {
    this$static.currentAction = 5010 + animType;
    $setActionIfExists(this$static.anim, 5010 + animType, 5010);
    this$static.hitSlideAnim = 5010 + animType;
  }
   else {
    this$static.currentAction = 5000 + animType;
    $setActionIfExists(this$static.anim, 5000 + animType, 5000);
    this$static.hitSlideAnim = 5005 + animType;
  }
}

function $resetForRound(this$static, startX, startY, life){
  this$static.x_0 = startX;
  this$static.y_0 = startY;
  this$static.groundY = startY;
  this$static.life = life;
  this$static.power = 0;
  this$static.vy = 0;
  this$static.vx = 0;
  this$static.onGround = true;
  this$static.inAttack = false;
  this$static.attackHit = false;
  this$static.attackHitsDone = 0;
  this$static.lastHitAnimElem = 0;
  this$static.pendingSpecial = null;
  this$static.hitPhase = 0;
  this$static.hitTimer = 0;
  this$static.hitSlideVx = 0;
  this$static.hitSlideTime = 0;
  this$static.hitPause = 0;
  this$static.jumpPhase = 0;
  this$static.jumpDir = 0;
  this$static.running = false;
  this$static.turning = false;
  this$static.prevPunch = false;
  this$static.prevKick = false;
  this$static.prevStrongPunch = false;
  this$static.prevStrongKick = false;
  this$static.prevUp = false;
  this$static.prevToward = false;
  this$static.towardTapTimer = 0;
  this$static.towardTapArmed = false;
  this$static.towardSawRelease = false;
  this$static.turnCooldown = 0;
  this$static.currentAction = 0;
  $setAction(this$static.anim, 0);
}

function $resolveAttack(this$static, cmdName, fallback){
  var mapped;
  if (fallback >= 400) {
    return fallback;
  }
  mapped = this$static.commandToState?castTo($getStringValue(this$static.commandToState, cmdName), 44):null;
  if (!!mapped && mapped.value_0 >= 200 && mapped.value_0 < 400) {
    return mapped.value_0;
  }
  return fallback;
}

function $setAudio(this$static, audio){
  this$static.audio = audio;
}

function $setCtrlEnabled(this$static, ctrlEnabled){
  this$static.ctrlEnabled = ctrlEnabled;
}

function $setHitAnim(this$static, preferred){
  this$static.currentAction = preferred;
  $setActionIfExists(this$static.anim, preferred, 5000);
}

function $setShadow(this$static, intensity, yScale){
  var abs_0;
  this$static.shadowIntensity = intensity;
  abs_0 = $wnd.Math.abs(yScale);
  this$static.shadowYScale = abs_0 > $intern_59?abs_0:0.25;
}

function $setZOffset(this$static, zoffset){
  this$static.zoffset = zoffset > 0?zoffset:192;
}

function $startFall(this$static){
  this$static.hitPhase = 4;
  this$static.hitTimer = 90;
  this$static.onGround = false;
  this$static.vy = -4;
  this$static.currentAction = 5050;
  $setActionIfExists(this$static.anim, 5050, 5030);
}

function $startRun(this$static){
  if (!this$static.onGround || this$static.turning) {
    return;
  }
  this$static.running = true;
  this$static.inAttack = false;
  $clearTowardTap(this$static);
  this$static.currentAction = 100;
  $setAction(this$static.anim, $hasAction(this$static.anim, 100)?100:20);
}

function $startRunBack(this$static){
  if (!this$static.onGround || this$static.turning) {
    return;
  }
  this$static.running = false;
  this$static.inAttack = false;
  this$static.onGround = false;
  this$static.jumpPhase = 2;
  this$static.jumpDir = -1;
  this$static.vy = -4;
  this$static.vx = -this$static.facing * (this$static.cns.runFwdX > 0?this$static.cns.runFwdX:$intern_60) * $intern_56;
  this$static.currentAction = 105;
  $setAction(this$static.anim, $hasAction(this$static.anim, 105)?105:43);
}

function $startTurn(this$static){
  this$static.turning = true;
  this$static.running = false;
  this$static.currentAction = 5;
  if ($hasAction(this$static.anim, 5)) {
    $setAction(this$static.anim, 5);
  }
   else {
    this$static.facing = -this$static.facing;
    this$static.turning = false;
    this$static.turnCooldown = 24;
    this$static.currentAction = 0;
    $setAction(this$static.anim, 0);
  }
}

function $tryEnterFromCommand(this$static, commandName, down){
  var state;
  if ($equals_5('FF', commandName)) {
    $startRun(this$static);
    return;
  }
  if ($equals_5('BB', commandName)) {
    $startRunBack(this$static);
    return;
  }
  state = this$static.commandToState?castTo($getStringValue(this$static.commandToState, commandName), 44):null;
  if (!state) {
    return;
  }
  if (state.value_0 == 100 && this$static.onGround) {
    $startRun(this$static);
    return;
  }
  if (state.value_0 == 105 && this$static.onGround) {
    $startRunBack(this$static);
    return;
  }
  state.value_0 >= 600 && !this$static.onGround?$enterAttackState(this$static, state.value_0):state.value_0 >= 400 && state.value_0 < 600 && this$static.onGround?$enterAttackState(this$static, state.value_0):state.value_0 >= 200 && state.value_0 < 400 && this$static.onGround && !down && $enterAttackState(this$static, state.value_0);
}

function $tryHit(this$static, opponent){
  var atk, ax, ay, blocked, damage, dx, dy, elem, elem0, elem1, elem2, hit, hp;
  if (!this$static.inAttack || !opponent) {
    return;
  }
  if (opponent.hitPause > 0) {
    return;
  }
  hp = $hitParamsFor(this$static, this$static.currentAction, this$static.attackHitsDone);
  elem0 = this$static.anim.anim._animElem + 1;
  if (this$static.attackHitsDone >= hp.maxHits) {
    return;
  }
  if (this$static.attackHitsDone > 0 && elem0 <= this$static.lastHitAnimElem) {
    return;
  }
  if (hp.requiredAnimElem > 0 && elem0 < hp.requiredAnimElem) {
    return;
  }
  atk = (elem1 = $getCurrentImageSprite(this$static.anim.anim) , elem1?elem1._airData.clsn1:initUnidimensionalArray(Lorg_lee_mugen_object_Rectangle_2_classLit, $intern_9, 25, 0, 0, 1));
  if (!(atk != null && atk.length > 0) || (elem2 = $getCurrentImageSprite(opponent.anim.anim) , (elem2?elem2._airData.clsn2:initUnidimensionalArray(Lorg_lee_mugen_object_Rectangle_2_classLit, $intern_9, 25, 0, 0, 1)) == null) || (elem = $getCurrentImageSprite(opponent.anim.anim) , elem?elem._airData.clsn2:initUnidimensionalArray(Lorg_lee_mugen_object_Rectangle_2_classLit, $intern_9, 25, 0, 0, 1)).length == 0) {
    return;
  }
  ax = this$static.x_0;
  ay = this$static.y_0;
  dx = opponent.x_0;
  dy = opponent.y_0;
  hit = attackHits(this$static.anim, ax, ay, this$static.facing, opponent.anim, dx, dy, opponent.facing);
  if (!hit) {
    return;
  }
  blocked = opponent.blocking;
  damage = hp.damage;
  blocked && (damage = $wnd.Math.max(1, damage / 4 | 0));
  $playHitSound(this$static, blocked);
  opponent.life = $wnd.Math.max(0, opponent.life - damage);
  this$static.power = $wnd.Math.min(3000, this$static.power + $wnd.Math.max(1, damage / 2 | 0));
  ++this$static.attackHitsDone;
  this$static.lastHitAnimElem = elem0;
  this$static.attackHit = this$static.attackHitsDone >= hp.maxHits;
  this$static.hitPause = hp.pauseP1;
  hp.moveContactElem > 0 && $setAnimElem(this$static.anim, hp.moveContactElem);
  $receiveHit(opponent, damage, this$static.facing, this$static.currentAction, blocked, hp);
}

function $tryHitOpponent(this$static, opponent){
  this$static.inAttack && this$static.hitPause <= 0 && $tryHit(this$static, opponent);
}

function $update_6(this$static, cmd, keysDown, opponent){
  var attackPressed, away, cmdName, down, kick, kickPress, moveLeft, moveRight, punch, punchPress, strongKick, strongKickPress, strongPunch, strongPunchPress, toward, towardPress, up, upPress;
  down = this$static.cmdHoldDown;
  up = this$static.cmdHoldUp;
  this$static.cmdHoldDown = false;
  this$static.cmdHoldUp = false;
  moveLeft = false;
  moveRight = false;
  punch = false;
  kick = false;
  strongPunch = false;
  strongKick = false;
  if (cmd) {
    moveLeft = key_0(keysDown, cmd.back_0);
    moveRight = key_0(keysDown, cmd.forward_0);
    up = up | key_0(keysDown, cmd.up);
    down = down | key_0(keysDown, cmd.down);
    punch = key_0(keysDown, cmd.x_0);
    strongPunch = key_0(keysDown, cmd.y_0);
    kick = key_0(keysDown, cmd.a);
    strongKick = key_0(keysDown, cmd.b);
  }
  toward = this$static.facing == 1 && moveRight || this$static.facing == -1 && moveLeft;
  away = this$static.facing == 1 && moveLeft || this$static.facing == -1 && moveRight;
  punchPress = punch && !this$static.prevPunch;
  kickPress = kick && !this$static.prevKick;
  strongPunchPress = strongPunch && !this$static.prevStrongPunch;
  strongKickPress = strongKick && !this$static.prevStrongKick;
  upPress = up && !this$static.prevUp;
  towardPress = toward && !this$static.prevToward;
  this$static.prevPunch = punch;
  this$static.prevKick = kick;
  this$static.prevStrongPunch = strongPunch;
  this$static.prevStrongKick = strongKick;
  this$static.prevUp = up;
  this$static.prevToward = toward;
  this$static.towardTapTimer > 0 && --this$static.towardTapTimer;
  this$static.turnCooldown > 0 && --this$static.turnCooldown;
  if (down) {
    up = false;
    upPress = false;
  }
  this$static.crouching = down && this$static.onGround && this$static.jumpPhase == 0 && !this$static.running && !this$static.turning;
  this$static.blocking = away && this$static.onGround && this$static.hitPhase == 0 && !this$static.inAttack && !this$static.running;
  if (this$static.hitPause > 0) {
    --this$static.hitPause;
    this$static.inAttack && $tick(this$static.cnsRuntime, this$static.cnsHost);
    this$static.x_0 < this$static.stageLeft && (this$static.x_0 = this$static.stageLeft);
    this$static.x_0 > this$static.stageRight && (this$static.x_0 = this$static.stageRight);
    return;
  }
  if (this$static.hitPhase != 0) {
    this$static.running = false;
    this$static.turning = false;
    this$static.hitPhase != 1 && $process_2(this$static.anim);
    $updateHitReaction(this$static);
    $applyPhysics(this$static);
    this$static.x_0 < this$static.stageLeft && (this$static.x_0 = this$static.stageLeft);
    this$static.x_0 > this$static.stageRight && (this$static.x_0 = this$static.stageRight);
    return;
  }
  $process_2(this$static.anim);
  if (this$static.turning) {
    if (this$static.anim.actionTicks <= 0) {
      this$static.facing = -this$static.facing;
      this$static.turning = false;
      this$static.turnCooldown = 24;
      this$static.currentAction = 0;
      $setAction(this$static.anim, 0);
      toward = this$static.facing == 1 && moveRight || this$static.facing == -1 && moveLeft;
      away = this$static.facing == 1 && moveLeft || this$static.facing == -1 && moveRight;
      this$static.prevToward = toward;
    }
    $applyPhysics(this$static);
    this$static.x_0 < this$static.stageLeft && (this$static.x_0 = this$static.stageLeft);
    this$static.x_0 > this$static.stageRight && (this$static.x_0 = this$static.stageRight);
    return;
  }
  if (this$static.jumpPhase == 1) {
    $updateJumpStart(this$static, moveLeft, moveRight);
    $applyPhysics(this$static);
    this$static.x_0 < this$static.stageLeft && (this$static.x_0 = this$static.stageLeft);
    this$static.x_0 > this$static.stageRight && (this$static.x_0 = this$static.stageRight);
    return;
  }
  if (this$static.jumpPhase == 3) {
    if (this$static.anim.actionTicks <= 0) {
      this$static.jumpPhase = 0;
      this$static.currentAction = 0;
      $setAction(this$static.anim, 0);
    }
    $applyPhysics(this$static);
    this$static.x_0 < this$static.stageLeft && (this$static.x_0 = this$static.stageLeft);
    this$static.x_0 > this$static.stageRight && (this$static.x_0 = this$static.stageRight);
    return;
  }
  if (this$static.inAttack) {
    this$static.running = false;
    $tick(this$static.cnsRuntime, this$static.cnsHost);
    if (!this$static.inAttack) {
      $applyPhysics(this$static);
      this$static.x_0 < this$static.stageLeft && (this$static.x_0 = this$static.stageLeft);
      this$static.x_0 > this$static.stageRight && (this$static.x_0 = this$static.stageRight);
      return;
    }
    if (this$static.anim.actionTicks <= 0) {
      this$static.inAttack = false;
      if (this$static.onGround) {
        if (down) {
          this$static.currentAction = 11;
          $setAction(this$static.anim, 11);
        }
         else {
          this$static.currentAction = 0;
          $setAction(this$static.anim, 0);
        }
      }
       else {
        this$static.currentAction = $jumpAirAnim(this$static);
        $setAction(this$static.anim, this$static.currentAction);
      }
    }
    $applyPhysics(this$static);
    this$static.x_0 < this$static.stageLeft && (this$static.x_0 = this$static.stageLeft);
    this$static.x_0 > this$static.stageRight && (this$static.x_0 = this$static.stageRight);
    return;
  }
  if (this$static.ctrlEnabled) {
    if (this$static.pendingSpecial != null) {
      cmdName = this$static.pendingSpecial;
      this$static.pendingSpecial = null;
      $equals_5('FF', cmdName) && this$static.onGround?$startRun(this$static):$equals_5('BB', cmdName) && this$static.onGround?$startRunBack(this$static):$tryEnterFromCommand(this$static, cmdName, down);
    }
    !toward && this$static.towardTapArmed && (this$static.towardSawRelease = true);
    if (this$static.onGround && !this$static.running && towardPress) {
      if (this$static.towardTapArmed && this$static.towardSawRelease && this$static.towardTapTimer > 0) {
        $startRun(this$static);
        $clearTowardTap(this$static);
      }
       else {
        this$static.towardTapArmed = true;
        this$static.towardSawRelease = false;
        this$static.towardTapTimer = 18;
      }
    }
    this$static.towardTapTimer <= 0 && this$static.towardTapArmed && $clearTowardTap(this$static);
    !this$static.inAttack && !this$static.running && !moveLeft && !moveRight && $maybeAutoTurn(this$static, opponent);
    if (!this$static.inAttack && !this$static.turning) {
      attackPressed = punchPress || strongPunchPress || kickPress || strongKickPress;
      this$static.running?$updateRun(this$static, toward, away, moveLeft, moveRight, upPress, attackPressed, punchPress, strongPunchPress, kickPress, strongKickPress, down):upPress && this$static.onGround && this$static.jumpPhase == 0?$beginJump(this$static, moveLeft, moveRight):attackPressed && this$static.onGround?$enterBasicAttack(this$static, punchPress, strongPunchPress, kickPress, strongKickPress, down):attackPressed && !this$static.onGround?$enterBasicAttack(this$static, punchPress, strongPunchPress, kickPress, strongKickPress, false):this$static.onGround?$applyGroundMovement(this$static, moveLeft, moveRight, toward, away, down):$updateAirAnim(this$static, moveLeft, moveRight);
    }
  }
   else 
    this$static.onGround && !this$static.inAttack && this$static.currentAction == 10 && this$static.anim.actionTicks <= 0 && (this$static.currentAction = 11 , $setAction(this$static.anim, 11));
  $applyPhysics(this$static);
  this$static.x_0 < this$static.stageLeft && (this$static.x_0 = this$static.stageLeft);
  this$static.x_0 > this$static.stageRight && (this$static.x_0 = this$static.stageRight);
}

function $updateAirAnim(this$static, moveLeft, moveRight){
  var want;
  if (this$static.jumpPhase != 2) {
    return;
  }
  moveRight && !moveLeft?(this$static.x_0 += $intern_61):moveLeft && !moveRight && (this$static.x_0 -= $intern_61);
  want = $jumpAirAnim(this$static);
  if (this$static.currentAction != want && this$static.currentAction < 200) {
    this$static.currentAction = want;
    $setAction(this$static.anim, want);
  }
}

function $updateHitReaction(this$static){
  --this$static.hitTimer;
  if (this$static.hitPhase == 1) {
    if (this$static.hitTimer <= 0) {
      if (this$static.life <= 0) {
        $startFall(this$static);
      }
       else {
        this$static.hitPhase = 2;
        this$static.hitTimer = $wnd.Math.max(4, this$static.hitGroundTime);
        this$static.vx = this$static.hitSlideVx;
        $setHitAnim(this$static, this$static.hitSlideAnim);
      }
    }
    return;
  }
  if (this$static.hitPhase == 2 || this$static.hitPhase == 8) {
    this$static.x_0 += this$static.vx;
    this$static.hitSlideTime > 0?--this$static.hitSlideTime:(this$static.vx *= this$static.cns.standFriction > 0?this$static.cns.standFriction:$intern_56);
    this$static.hitTimer <= 0 && (this$static.life <= 0?$startFall(this$static):$clearHit(this$static));
    return;
  }
  if (this$static.hitPhase == 3 || this$static.hitPhase == 4) {
    this$static.x_0 += this$static.vx;
    if (this$static.onGround && this$static.vy >= 0 && this$static.y_0 >= this$static.groundY) {
      this$static.hitPhase = 5;
      this$static.hitTimer = 18;
      this$static.vx *= 0.5;
      this$static.vy = -3;
      this$static.onGround = false;
      this$static.currentAction = 5100;
      $setActionIfExists(this$static.anim, 5100, 5050);
    }
     else if (this$static.hitTimer <= 0 && this$static.anim.actionTicks <= 0 && this$static.hitPhase == 3) {
      this$static.hitPhase = 4;
      this$static.currentAction = 5050;
      $setActionIfExists(this$static.anim, 5050, 5030);
      this$static.hitTimer = 60;
    }
    return;
  }
  if (this$static.hitPhase == 5) {
    this$static.x_0 += this$static.vx;
    if (this$static.onGround && this$static.y_0 >= this$static.groundY) {
      this$static.hitPhase = 6;
      this$static.hitTimer = this$static.life <= 0?90:30;
      this$static.vx = 0;
      this$static.currentAction = 5110;
      $setActionIfExists(this$static.anim, 5110, 5050);
    }
    return;
  }
  if (this$static.hitPhase == 6) {
    if (this$static.hitTimer <= 0) {
      if (this$static.life <= 0) {
        this$static.hitTimer = 1;
      }
       else {
        this$static.hitPhase = 7;
        this$static.hitTimer = 30;
        this$static.currentAction = 5120;
        $setActionIfExists(this$static.anim, 5120, 0);
      }
    }
    return;
  }
  this$static.hitPhase == 7 && (this$static.hitTimer <= 0 || this$static.anim.actionTicks <= 0) && $clearHit(this$static);
}

function $updateJumpStart(this$static, moveLeft, moveRight){
  moveRight && !moveLeft?(this$static.jumpDir = 1):moveLeft && !moveRight && (this$static.jumpDir = -1);
  if (this$static.anim.actionTicks <= 0) {
    this$static.jumpPhase = 2;
    this$static.onGround = false;
    this$static.vy = this$static.cns.jumpNeuY != 0?this$static.cns.jumpNeuY:-8.199999809265137;
    this$static.vx = this$static.jumpDir * (this$static.cns.jumpFwdX > 0?this$static.cns.jumpFwdX:2.799999952316284);
    this$static.currentAction = $jumpAirAnim(this$static);
    $setAction(this$static.anim, this$static.currentAction);
  }
}

function $updateRun(this$static, toward, away, moveLeft, moveRight, upPress, attackPressed, punchPress, strongPunchPress, kickPress, strongKickPress, down){
  if (!toward || away || down) {
    this$static.running = false;
    this$static.currentAction = 0;
    $setAction(this$static.anim, 0);
    return;
  }
  if (upPress) {
    this$static.running = false;
    $beginJump(this$static, moveLeft, moveRight);
    return;
  }
  if (attackPressed) {
    this$static.running = false;
    $enterBasicAttack(this$static, punchPress, strongPunchPress, kickPress, strongKickPress, false);
    return;
  }
  if (this$static.currentAction != 100) {
    this$static.currentAction = 100;
    $setAction(this$static.anim, $hasAction(this$static.anim, 100)?100:20);
  }
  this$static.x_0 += this$static.facing * (this$static.cns.runFwdX > 0?this$static.cns.runFwdX:$intern_60);
}

function GwtFightPlayer(cmdId, facing, startX, startY, anim, life, cmdData, cnsData, stageLeft, stageRight){
  this.cnsHost = new GwtFightPlayer$1(this);
  this.cmdId = cmdId;
  this.facing = facing;
  this.x_0 = startX;
  this.y_0 = startY;
  this.groundY = startY;
  this.anim = anim;
  this.cns = cnsData?cnsData:new GwtCnsData;
  this.cnsRuntime = new GwtCnsRuntime(this.cns);
  this.life = life > 0?life:this.cns.life;
  this.power = 0;
  this.commands = cmdData.commands;
  this.commandToState = cmdData.commandToState;
  this.stageLeft = stageLeft;
  this.stageRight = stageRight;
}

function hitParamsFallback(state, hitIndex){
  switch (state) {
    case 200:
      return new GwtFightPlayer$HitParams_0(23, 8, 8, 4, $intern_62, -3, 5, 10, 1, 3, 0, false, 0);
    case 210:
      return new GwtFightPlayer$HitParams_0(57, 12, 12, 5.5, 2.5, -4, 12, 13, 1, 3, 6, false, 2);
    case 230:
      return new GwtFightPlayer$HitParams_0(26, 12, 12, 5, 2.5, -3.5, 10, 11, 1, 0, 0, false, 0);
    case 240:
      return new GwtFightPlayer$HitParams_0(63, 12, 12, 6, 2.5, -4, 12, 13, 1, 0, 0, false, 2);
    case 400:
      return new GwtFightPlayer$HitParams_0(23, 10, 11, 4, $intern_62, -3, 5, 10, 1, 0, 0, false, 0);
    case 410:
      if (hitIndex == 0) {
        return new GwtFightPlayer$HitParams_0(37, 12, 12, 4, 3, -4, 12, 15, 2, 3, 0, false, 1);
      }

      return new GwtFightPlayer$HitParams_0(36, 12, 12, 7, 3, -4, 12, 15, 2, 4, 0, false, 1);
    case 430:
      return new GwtFightPlayer$HitParams_0(28, 12, 12, 5, 2, -3, 6, 10, 1, 0, 0, false, 0);
    case 440:
      return new GwtFightPlayer$HitParams_0(72, 12, 12, 1.5, $intern_58, -3, 10, 15, 1, 0, 0, true, 2);
    case 600:
      return new GwtFightPlayer$HitParams_0(20, 7, 8, 4, 1.2999999523162842, -3, 5, 5, 1, 0, 0, false, 0);
    case 610:
      return new GwtFightPlayer$HitParams_0(72, 12, 12, 5, 3, -4, 17, 17, 1, 0, 0, false, 2);
    case 630:
      return new GwtFightPlayer$HitParams_0(26, 8, 8, 4, 2, -3, 6, 6, 1, 0, 0, false, 0);
    case 640:
      return new GwtFightPlayer$HitParams_0(70, 12, 12, 5, 3, -4, 15, 15, 1, 0, 0, false, 2);
  }
  if (state >= 1000) {
    return new GwtFightPlayer$HitParams(180, 12, 12, 6, 4, -6, 12, 20);
  }
  if (state >= 400) {
    return new GwtFightPlayer$HitParams(40, 10, 10, 4, 2, -3, 8, 12);
  }
  if (state >= 200) {
    return new GwtFightPlayer$HitParams(40, 8, 8, 4, 2, -3, 8, 10);
  }
  return new GwtFightPlayer$HitParams(30, 8, 8, 3, 1.5, -3, 6, 10);
}

function key_0(keysDown, code_0){
  return code_0 >= 0 && code_0 < keysDown.length && keysDown[code_0];
}

defineClass(266, 1, {}, GwtFightPlayer);
_.attackHit = false;
_.attackHitsDone = 0;
_.blocking = false;
_.cmdHoldDown = false;
_.cmdHoldUp = false;
_.crouching = false;
_.ctrlEnabled = true;
_.currentAction = 0;
_.facing = 0;
_.groundY = 0;
_.hitGroundTime = 0;
_.hitPause = 0;
_.hitPhase = 0;
_.hitSlideAnim = 5005;
_.hitSlideTime = 0;
_.hitSlideVx = 0;
_.hitTimer = 0;
_.inAttack = false;
_.jumpDir = 0;
_.jumpPhase = 0;
_.lastHitAnimElem = 0;
_.life = 0;
_.onGround = true;
_.power = 0;
_.prevKick = false;
_.prevPunch = false;
_.prevStrongKick = false;
_.prevStrongPunch = false;
_.prevToward = false;
_.prevUp = false;
_.running = false;
_.shadowIntensity = 128;
_.shadowYScale = 0.25;
_.stageLeft = 0;
_.stageRight = 0;
_.towardSawRelease = false;
_.towardTapArmed = false;
_.towardTapTimer = 0;
_.turnCooldown = 0;
_.turning = false;
_.vx = 0;
_.vy = 0;
_.x_0 = 0;
_.y_0 = 0;
_.zoffset = 192;
var Lorg_lee_mugen_fight_GwtFightPlayer_2_classLit = createForClass('org.lee.mugen.fight', 'GwtFightPlayer', 266);
function $applyChangeState(this$static, stateNo, ctrl){
  ctrl >= 0 && (this$static.this$01.ctrlEnabled = ctrl != 0);
  if (stateNo == 0) {
    this$static.this$01.inAttack = false;
    this$static.this$01.running = false;
    this$static.this$01.crouching = false;
    this$static.this$01.currentAction = 0;
    $restartAction(this$static.this$01.anim, 0);
    return;
  }
  if (stateNo == 11 || stateNo == 10) {
    this$static.this$01.inAttack = false;
    this$static.this$01.running = false;
    this$static.this$01.crouching = true;
    $enterCrouchIdle(this$static.this$01);
    return;
  }
  if (stateNo >= 200) {
    this$static.this$01.inAttack = true;
    this$static.this$01.currentAction = stateNo;
    this$static.this$01.crouching = stateNo >= 400 && stateNo < 600;
    return;
  }
  this$static.this$01.inAttack = false;
  this$static.this$01.currentAction = stateNo;
  $restartAction(this$static.this$01.anim, stateNo);
}

function $setAnim(this$static, a){
  $restartAction(this$static.this$01.anim, a);
  this$static.this$01.currentAction = a;
}

function $setCtrl(this$static, ctrl){
  this$static.this$01.ctrlEnabled = ctrl;
}

function $setVel(this$static, worldVx, worldVy){
  this$static.this$01.vx = worldVx;
  this$static.this$01.vy = worldVy;
}

function GwtFightPlayer$1(this$0){
  this.this$01 = this$0;
}

defineClass(505, 1, {}, GwtFightPlayer$1);
var Lorg_lee_mugen_fight_GwtFightPlayer$1_2_classLit = createForClass('org.lee.mugen.fight', 'GwtFightPlayer/1', 505);
function GwtFightPlayer$HitParams(damage, pauseP1, pauseP2, groundVx, airVx, airVy, slideTime, hitTime){
  GwtFightPlayer$HitParams_0.call(this, damage, pauseP1, pauseP2, groundVx, airVx, airVy, slideTime, hitTime, 1, 0, 0, false, 0);
}

function GwtFightPlayer$HitParams_0(damage, pauseP1, pauseP2, groundVx, airVx, airVy, slideTime, hitTime, maxHits, requiredAnimElem, moveContactElem, trip, animType){
  this.damage = damage;
  this.pauseP1 = pauseP1;
  this.pauseP2 = pauseP2;
  this.groundVx = groundVx;
  this.airVx = airVx;
  this.airVy = airVy;
  this.slideTime = slideTime;
  this.hitTime = hitTime;
  this.maxHits = maxHits;
  this.requiredAnimElem = requiredAnimElem;
  this.moveContactElem = moveContactElem;
  this.trip = trip;
  this.animType = animType < 0?0:animType > 2?2:animType;
}

defineClass(49, 1, {}, GwtFightPlayer$HitParams, GwtFightPlayer$HitParams_0);
_.airVx = 0;
_.airVy = 0;
_.animType = 0;
_.damage = 0;
_.groundVx = 0;
_.hitTime = 0;
_.maxHits = 0;
_.moveContactElem = 0;
_.pauseP1 = 0;
_.pauseP2 = 0;
_.requiredAnimElem = 0;
_.slideTime = 0;
_.trip = false;
var Lorg_lee_mugen_fight_GwtFightPlayer$HitParams_2_classLit = createForClass('org.lee.mugen.fight', 'GwtFightPlayer/HitParams', 49);
function buildPushBox(worldX, worldY, facing, onGround, hurtBoxes, animXOff, animYOff){
  var back_0, baseX, baseY, front, left, lx1, lx2, ly1, ly2, r, r$array, r$index, r$max, right, t, x1, x2, y1, y2;
  baseX = worldX + animXOff * facing;
  baseY = worldY + animYOff;
  if (hurtBoxes != null && hurtBoxes.length > 0) {
    x1 = null;
    y1 = null;
    x2 = null;
    y2 = null;
    for (r$array = hurtBoxes , r$index = 0 , r$max = r$array.length; r$index < r$max; ++r$index) {
      r = r$array[r$index];
      if (!r) {
        continue;
      }
      lx1 = mapX(r.x1, facing) + round_int(baseX);
      lx2 = mapX(r.x2, facing) + round_int(baseX);
      ly1 = r.y1 + round_int(baseY);
      ly2 = r.y2 + round_int(baseY);
      if (lx1 > lx2) {
        t = lx1;
        lx1 = lx2;
        lx2 = t;
      }
      if (ly1 > ly2) {
        t = ly1;
        ly1 = ly2;
        ly2 = t;
      }
      if (!x1) {
        x1 = new Float(lx1);
        y1 = new Float(ly1);
        x2 = new Float(lx2);
        y2 = new Float(ly2);
      }
       else {
        x1 = new Float($wnd.Math.min(x1.value_0, lx1));
        y1 = new Float($wnd.Math.min(y1.value_0, ly1));
        x2 = new Float($wnd.Math.max(x2.value_0, lx2));
        y2 = new Float($wnd.Math.max(y2.value_0, ly2));
      }
    }
    if (x1) {
      return new GwtFightPush$PushBox(x1.value_0, y1.value_0, x2.value_0, y2.value_0);
    }
  }
  front = onGround?16:12;
  back_0 = onGround?15:12;
  left = facing == 1?worldX - back_0:worldX - front;
  right = facing == 1?worldX + front:worldX + back_0;
  return new GwtFightPush$PushBox(left, worldY - 60, right, worldY);
}

function mapX(localX, facing){
  return facing == -1?-localX:localX;
}

function resolve_0(a, b){
  var elem, elem0, elem1, elem2, elem3, elem4, half, left, leftBefore, leftMoved, leftShort, overlap, ra, rb, right, rightBefore, rightMoved, rightShort;
  if (!a || !b) {
    return;
  }
  ra = buildPushBox(a.x_0, a.y_0, a.facing, a.onGround, (elem0 = $getCurrentImageSprite(a.anim.anim) , elem0?elem0._airData.clsn2:initUnidimensionalArray(Lorg_lee_mugen_object_Rectangle_2_classLit, $intern_9, 25, 0, 0, 1)), (elem1 = $getCurrentImageSprite(a.anim.anim) , elem1?elem1._airData.xOffSet:0), (elem2 = $getCurrentImageSprite(a.anim.anim) , elem2?elem2._airData.yOffSet:0));
  rb = buildPushBox(b.x_0, b.y_0, b.facing, b.onGround, (elem3 = $getCurrentImageSprite(b.anim.anim) , elem3?elem3._airData.clsn2:initUnidimensionalArray(Lorg_lee_mugen_object_Rectangle_2_classLit, $intern_9, 25, 0, 0, 1)), (elem4 = $getCurrentImageSprite(b.anim.anim) , elem4?elem4._airData.xOffSet:0), (elem = $getCurrentImageSprite(b.anim.anim) , elem?elem._airData.yOffSet:0));
  if (!(ra.x1 < rb.x2 && ra.x2 > rb.x1 && ra.y1 < rb.y2 && ra.y2 > rb.y1)) {
    return;
  }
  overlap = $wnd.Math.min(ra.x2, rb.x2) - $wnd.Math.max(ra.x1, rb.x1);
  if (overlap <= 0) {
    return;
  }
  left = a.x_0 <= b.x_0?a:b;
  right = left == a?b:a;
  half = overlap * 0.5;
  leftBefore = left.x_0;
  rightBefore = right.x_0;
  left.x_0 += -half;
  left.x_0 < left.stageLeft && (left.x_0 = left.stageLeft);
  left.x_0 > left.stageRight && (left.x_0 = left.stageRight);
  right.x_0 += half;
  right.x_0 < right.stageLeft && (right.x_0 = right.stageLeft);
  right.x_0 > right.stageRight && (right.x_0 = right.stageRight);
  leftMoved = leftBefore - left.x_0;
  rightMoved = right.x_0 - rightBefore;
  leftShort = half - leftMoved;
  rightShort = half - rightMoved;
  leftShort > $intern_59 && (right.x_0 += leftShort , right.x_0 < right.stageLeft && (right.x_0 = right.stageLeft) , right.x_0 > right.stageRight && (right.x_0 = right.stageRight));
  rightShort > $intern_59 && (left.x_0 += -rightShort , left.x_0 < left.stageLeft && (left.x_0 = left.stageLeft) , left.x_0 > left.stageRight && (left.x_0 = left.stageRight));
}

function GwtFightPush$PushBox(x1, y1, x2, y2){
  this.x1 = x1;
  this.y1 = y1;
  this.x2 = x2;
  this.y2 = y2;
}

defineClass(269, 1, {}, GwtFightPush$PushBox);
_.x1 = 0;
_.x2 = 0;
_.y1 = 0;
_.y2 = 0;
var Lorg_lee_mugen_fight_GwtFightPush$PushBox_2_classLit = createForClass('org.lee.mugen.fight', 'GwtFightPush/PushBox', 269);
function $beginRound(this$static){
  this$static.phase = 0;
  this$static.phaseTimer = 30;
  this$static.roundTimer = 5940;
  this$static.ctrlActive = false;
  this$static.statusBanner = null;
}

function $onKo(this$static, winner){
  if (this$static.phase != 3 && this$static.phase != 4) {
    return;
  }
  this$static.ctrlActive = false;
  if (winner == 1) {
    ++this$static.p1Wins;
    this$static.statusBanner = 'K.O.';
    !!this$static.audio && $playKo(this$static.audio);
  }
   else if (winner == 2) {
    ++this$static.p2Wins;
    this$static.statusBanner = 'K.O.';
    !!this$static.audio && $playKo(this$static.audio);
  }
   else {
    this$static.statusBanner = 'D.K.O.';
    !!this$static.audio && $playDko(this$static.audio);
  }
  this$static.phase = 4;
  this$static.koTimer = 90;
}

function $onTimeOver(this$static, winner){
  this$static.ctrlActive = false;
  !!this$static.audio && $playTimeOver(this$static.audio);
  if (winner == 1) {
    ++this$static.p1Wins;
    this$static.statusBanner = 'TIME OVER - P1';
  }
   else if (winner == 2) {
    ++this$static.p2Wins;
    this$static.statusBanner = 'TIME OVER - P2';
  }
   else {
    this$static.statusBanner = 'TIME OVER - DRAW';
  }
  this$static.phase = 5;
  this$static.phaseTimer = 120;
}

function $setAudio_0(this$static, audio){
  this$static.audio = audio;
}

function $startMatch(this$static){
  this$static.round_0 = 1;
  this$static.p1Wins = 0;
  this$static.p2Wins = 0;
  $beginRound(this$static);
}

function $tick_0(this$static){
  switch (this$static.phase) {
    case 0:
      --this$static.phaseTimer;
      if (this$static.phaseTimer <= 0) {
        this$static.phase = 1;
        this$static.phaseTimer = 60;
        !!this$static.audio && $playRound(this$static.audio, this$static.round_0);
      }

      break;
    case 1:
      --this$static.phaseTimer;
      if (this$static.phaseTimer <= 0) {
        this$static.phase = 2;
        this$static.phaseTimer = 30;
        this$static.statusBanner = 'FIGHT!';
        !!this$static.audio && $playFightCall(this$static.audio);
      }

      break;
    case 2:
      --this$static.phaseTimer;
      if (this$static.phaseTimer <= 0) {
        this$static.phase = 3;
        this$static.ctrlActive = true;
        this$static.statusBanner = null;
      }

      break;
    case 3:
      this$static.roundTimer > 0 && --this$static.roundTimer;
      break;
    case 4:
      --this$static.koTimer;
      if (this$static.koTimer <= 0) {
        this$static.phase = 5;
        this$static.phaseTimer = 120;
      }

      break;
    case 5:
      --this$static.phaseTimer;
      if (this$static.phaseTimer <= 0) {
        if (this$static.p1Wins >= 2 || this$static.p2Wins >= 2 || this$static.round_0 >= 3) {
          this$static.phase = 6;
          this$static.statusBanner = this$static.p1Wins > this$static.p2Wins?'P1 WINS':this$static.p2Wins > this$static.p1Wins?'P2 WINS':'DRAW';
        }
         else {
          ++this$static.round_0;
          $beginRound(this$static);
        }
      }

  }
}

function GwtFightRules(){
}

defineClass(503, 1, {}, GwtFightRules);
_.ctrlActive = false;
_.koTimer = 0;
_.p1Wins = 0;
_.p2Wins = 0;
_.phase = 0;
_.phaseTimer = 30;
_.round_0 = 1;
_.roundTimer = 0;
var Lorg_lee_mugen_fight_GwtFightRules_2_classLit = createForClass('org.lee.mugen.fight', 'GwtFightRules', 503);
function GwtStageFightInfo(){
}

function load_2(stagePath){
  var br, eq, info, key, line, reader, section, semi, suffixlength, value_0;
  info = new GwtStageFightInfo;
  if (stagePath == null) {
    return info;
  }
  reader = openUtf8Reader(($clinit_JMugenConstant() , RESOURCE) + ('' + stagePath));
  br = new BufferedReader(reader);
  section = '';
  while ((line = $readLine(br)) != null) {
    line = $trim(line);
    if (line.length == 0 || $equals_5(line.substr(0, 1), ';')) {
      continue;
    }
    if ($equals_5(line.substr(0, 1), '[') && (suffixlength = ']'.length , $equals_5(line.substr(line.length - suffixlength, suffixlength), ']'))) {
      section = $trim($substring_0(line, 1, line.length - 1)).toLowerCase();
      continue;
    }
    if (!$equals_5('playerinfo', section) && !$equals_5('camera', section) && !$equals_5('music', section) && !$equals_5('stageinfo', section) && !$equals_5('shadow', section)) {
      continue;
    }
    eq = $indexOf_0(line, fromCodePoint(61));
    if (eq < 0) {
      continue;
    }
    key = $trim((checkCriticalStringBounds(0, eq, line.length) , line.substr(0, eq))).toLowerCase();
    value_0 = $trim((checkCriticalStringElementIndex(eq + 1, line.length + 1) , line.substr(eq + 1)));
    semi = $indexOf_0(value_0, fromCodePoint(59));
    semi >= 0 && (value_0 = $trim((checkCriticalStringBounds(0, semi, value_0.length) , value_0.substr(0, semi))));
    if ($equals_5('music', section)) {
      $equals_5('bgmusic', key) && value_0.length > 0 && (info.bgmusic = $replace(value_0));
      continue;
    }
    if ($equals_5('shadow', section)) {
      $equals_5('intensity', key)?(info.shadowIntensity = __parseAndValidateInt(value_0, 10)):$equals_5('yscale', key) && (info.shadowYScale = parseFloat_0(value_0));
      continue;
    }
    if ($equals_5('stageinfo', section)) {
      $equals_5('zoffset', key) && (info.zoffset = __parseAndValidateInt(value_0, 10));
      continue;
    }
    $equals_5('playerinfo', section)?$equals_5('p1startx', key)?(info.p1startx = __parseAndValidateInt(value_0, 10)):$equals_5('p1starty', key)?(info.p1starty = __parseAndValidateInt(value_0, 10)):$equals_5('p1facing', key)?(info.p1facing = __parseAndValidateInt(value_0, 10)):$equals_5('p2startx', key)?(info.p2startx = __parseAndValidateInt(value_0, 10)):$equals_5('p2starty', key)?(info.p2starty = __parseAndValidateInt(value_0, 10)):$equals_5('p2facing', key)?(info.p2facing = __parseAndValidateInt(value_0, 10)):$equals_5('leftbound', key)?(info.leftbound = __parseAndValidateInt(value_0, 10)):$equals_5('rightbound', key) && (info.rightbound = __parseAndValidateInt(value_0, 10)):$equals_5('boundleft', key)?(info.boundleft = __parseAndValidateInt(value_0, 10)):$equals_5('boundright', key) && (info.boundright = __parseAndValidateInt(value_0, 10));
  }
  return info;
}

defineClass(268, 1, {}, GwtStageFightInfo);
_.boundleft = -150;
_.boundright = 150;
_.leftbound = -1000;
_.p1facing = 1;
_.p1startx = -70;
_.p1starty = 0;
_.p2facing = -1;
_.p2startx = 70;
_.p2starty = 0;
_.rightbound = 1000;
_.shadowIntensity = 128;
_.shadowYScale = $intern_53;
_.zoffset = 192;
var Lorg_lee_mugen_fight_GwtStageFightInfo_2_classLit = createForClass('org.lee.mugen.fight', 'GwtStageFightInfo', 268);
function $decrease(this$static){
  this$static.time > 0 && --this$static.time;
}

function $init_0(this$static){
  this$static.time = this$static.originalTime;
}

function $parse_3(this$static, name_0, value_0){
  if ($equals_5(name_0, 'col')) {
    castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, Lorg_lee_mugen_renderer_RGB_2_classLit), 13)).convert(value_0), 65);
  }
   else if ($equals_5(name_0, 'time')) {
    this$static.time = __parseAndValidateInt(value_0, 10);
    this$static.originalTime = this$static.time;
  }
}

function Fade(){
}

defineClass(110, 1, {}, Fade);
_.originalTime = 0;
_.time = 0;
var Lorg_lee_mugen_fight_intro_entity_Fade_2_classLit = createForClass('org.lee.mugen.fight.intro.entity', 'Fade', 110);
defineClass(547, 1, {});
_.copy = function copy_2(){
  return this;
}
;
var Lorg_lee_mugen_fight_section_elem_CommonType_2_classLit = createForClass('org.lee.mugen.fight.section.elem', 'CommonType', 547);
function AnimType(){
}

defineClass(102, 547, {102:1}, AnimType);
_.parse_1 = function parse_1(name_0, value_0){
  $equals_5(name_0, 'anim') && __parseAndValidateInt(value_0, 10);
}
;
var Lorg_lee_mugen_fight_section_elem_AnimType_2_classLit = createForClass('org.lee.mugen.fight.section.elem', 'AnimType', 102);
function $getFont(this$static){
  var files;
  if (!this$static.font) {
    files = $resolveFiles(this$static);
    !!files && (this$static.font = files.font);
  }
  return this$static.font;
}

function $resolveFiles(this$static){
  if (this$static.root) {
    return this$static.root.files;
  }
  return null;
}

function $setAlignmt(this$static, alignmt){
  this$static.alignmt = alignmt;
}

function $setText(this$static, text_0){
  this$static.text_0 = text_0;
}

function FontType(root){
  this.alignmt = ($clinit_FontType$ALIGNMT() , left_0);
  this.root = root;
}

defineClass(67, 547, {67:1}, FontType);
_.parse_1 = function parse_2(name_0, value_0){
  var res;
  if ($equals_5(name_0, 'font')) {
    res = castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, getClassLiteralForArray(I_classLit, 1)), 13)).convert(value_0), 11);
    this.fontno = res[0];
    this.fontbank = res[1];
  }
   else 
    $equals_5(name_0, 'text')?(this.text_0 = value_0):$equals_5(name_0, 'alignmt') && (this.alignmt = getValue_2(__parseAndValidateInt(value_0, 10)));
}
;
_.fontbank = 0;
_.fontno = 0;
var Lorg_lee_mugen_fight_section_elem_FontType_2_classLit = createForClass('org.lee.mugen.fight.section.elem', 'FontType', 67);
function $clinit_FontType$ALIGNMT(){
  $clinit_FontType$ALIGNMT = emptyMethod;
  left_0 = new FontType$ALIGNMT('left', 0, -1);
  center = new FontType$ALIGNMT('center', 1, 0);
  right_0 = new FontType$ALIGNMT('right', 2, 1);
}

function FontType$ALIGNMT(enum$name, enum$ordinal, code_0){
  Enum.call(this, enum$name, enum$ordinal);
  this.code_0 = code_0;
}

function getValue_2(alignmt){
  $clinit_FontType$ALIGNMT();
  switch (alignmt) {
    case -1:
      return left_0;
    case 0:
      return center;
    case 1:
      return right_0;
    default:throw toJs(new IllegalArgumentException);
  }
}

function values_14(){
  $clinit_FontType$ALIGNMT();
  return stampJavaTypeInfo(getClassLiteralForArray(Lorg_lee_mugen_fight_section_elem_FontType$ALIGNMT_2_classLit, 1), $intern_1, 134, 0, [left_0, center, right_0]);
}

defineClass(134, 8, {3:1, 7:1, 8:1, 134:1}, FontType$ALIGNMT);
_.code_0 = 0;
var center, left_0, right_0;
var Lorg_lee_mugen_fight_section_elem_FontType$ALIGNMT_2_classLit = createForEnum('org.lee.mugen.fight.section.elem', 'FontType/ALIGNMT', 134, values_14);
function $copy_2(this$static){
  var c;
  c = new SndType;
  c.grp = this$static.grp;
  c.num = this$static.num;
  return c;
}

function $setGrp(this$static, grp){
  this$static.grp = grp;
}

function $setNum(this$static, num){
  this$static.num = num;
}

function SndType(){
}

defineClass(251, 547, {}, SndType);
_.copy = function copy_3(){
  return $copy_2(this);
}
;
_.parse_1 = function parse_3(name_0, value_0){
  var res;
  if ($equals_5(name_0, 'snd')) {
    res = castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, getClassLiteralForArray(I_classLit, 1)), 13)).convert(value_0), 11);
    this.grp = res[0];
    this.num = res[1];
  }
}
;
_.grp = 0;
_.num = 0;
var Lorg_lee_mugen_fight_section_elem_SndType_2_classLit = createForClass('org.lee.mugen.fight.section.elem', 'SndType', 251);
function $getImage(this$static){
  var sff;
  sff = $resolveSpriteSff(this$static);
  if (!sff || !$getGroupSpr(sff, this$static.spritegrp) || !$getImgSpr($getGroupSpr(sff, this$static.spritegrp), this$static.spriteno)) {
    return null;
  }
  return $getImgSpr($getGroupSpr(sff, this$static.spritegrp), this$static.spriteno);
}

function $resolveSpriteSff(this$static){
  if (this$static.root) {
    return this$static.root.files.spr;
  }
  return null;
}

function SprType(root){
  this.root = root;
}

defineClass(133, 547, {133:1}, SprType);
_.parse_1 = function parse_4(name_0, value_0){
  var res;
  if ($equals_5(name_0, 'spr')) {
    res = castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, getClassLiteralForArray(I_classLit, 1)), 13)).convert(value_0), 11);
    this.spritegrp = res[0];
    this.spriteno = res[1];
  }
}
;
_.spritegrp = 0;
_.spriteno = 0;
var Lorg_lee_mugen_fight_section_elem_SprType_2_classLit = createForClass('org.lee.mugen.fight.section.elem', 'SprType', 133);
function $copy_3(this$static){
  var c;
  c = new Type;
  !!this$static.type_0 && (c.type_0 = this$static.type_0.copy());
  !!this$static.offset && (c.offset = $copy_4(this$static.offset));
  !!this$static.pos && (c.pos = $copy_4(this$static.pos));
  !!this$static.snd && (c.snd = $copy_2(this$static.snd));
  !!this$static.scale && (c.scale = $copy_6(this$static.scale));
  c.displaytime = this$static.displaytime;
  c.originalDisplaytime = this$static.originalDisplaytime;
  c.facing = this$static.facing;
  c.vfacing = this$static.vfacing;
  c.sndtime = this$static.sndtime;
  c.originalSndtime = this$static.originalSndtime;
  c.layerno = this$static.layerno;
  c.starttime = this$static.starttime;
  c.originalStarttime = this$static.originalStarttime;
  c.alpha_0 = this$static.alpha_0;
  return c;
}

function $parse_4(this$static, name_0, value_0){
  var sndGrpNum;
  !!this$static.type_0 && this$static.type_0.parse_1(name_0, value_0);
  if ($equalsIgnoreCase(name_0, 'pos')) {
    this$static.pos = castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, Lorg_lee_mugen_geom_MugenPoint_2_classLit), 13)).convert(value_0), 10);
  }
   else if ($equalsIgnoreCase(name_0, 'offset')) {
    this$static.offset = castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, Lorg_lee_mugen_geom_MugenPoint_2_classLit), 13)).convert(value_0), 10);
  }
   else if ($equalsIgnoreCase(name_0, 'starttime')) {
    $setStarttime(this$static, castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, Ljava_lang_Integer_2_classLit), 13)).convert(value_0), 44).value_0);
    this$static.originalStarttime = this$static.starttime;
  }
   else if ($equalsIgnoreCase(name_0, 'displaytime')) {
    $setDisplaytime(this$static, castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, Ljava_lang_Integer_2_classLit), 13)).convert(value_0), 44).value_0);
    this$static.originalDisplaytime = this$static.displaytime;
  }
   else if ($equalsIgnoreCase(name_0, 'facing')) {
    this$static.facing = castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, Ljava_lang_Integer_2_classLit), 13)).convert(value_0), 44).value_0;
  }
   else if ($equalsIgnoreCase(name_0, 'vfacing')) {
    this$static.vfacing = castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, Ljava_lang_Integer_2_classLit), 13)).convert(value_0), 44).value_0;
  }
   else if ($equalsIgnoreCase(name_0, 'snd')) {
    sndGrpNum = castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, getClassLiteralForArray(I_classLit, 1)), 13)).convert(value_0), 11);
    this$static.snd = new SndType;
    $setGrp(this$static.snd, sndGrpNum[0]);
    $setNum(this$static.snd, sndGrpNum[1]);
  }
   else if ($equalsIgnoreCase(name_0, 'sndtime')) {
    this$static.sndtime = castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, Ljava_lang_Integer_2_classLit), 13)).convert(value_0), 44).value_0;
    this$static.originalSndtime = this$static.sndtime;
  }
   else 
    $equalsIgnoreCase(name_0, 'layerno')?(this$static.layerno = castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, Ljava_lang_Integer_2_classLit), 13)).convert(value_0), 44).value_0):$equalsIgnoreCase(name_0, 'scale')?(this$static.scale = castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, Lorg_lee_mugen_sprite_entity_PointF_2_classLit), 13)).convert(value_0), 29)):$equalsIgnoreCase(name_0, 'alpha') && (this$static.alpha_0 = castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, Ljava_lang_Float_2_classLit), 13)).convert(value_0), 38).value_0);
}

function $setDisplaytime(this$static, displaytime){
  this$static.displaytime = displaytime;
}

function $setStarttime(this$static, starttime){
  this$static.starttime = starttime;
}

function $setType_0(this$static, name_0, value_0, root){
  var alignmt, e, fontbank, fontno, grpNo, nba;
  if ($equalsIgnoreCase(name_0, 'anim')) {
    e = new AnimType;
    __parseAndValidateInt(value_0, 10);
    this$static.type_0 = e;
  }
   else if ($equalsIgnoreCase(name_0, 'spr')) {
    e = new SprType(root);
    grpNo = castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, getClassLiteralForArray(I_classLit, 1)), 13)).convert(value_0), 11);
    e.spritegrp = grpNo[0];
    e.spriteno = grpNo[1];
    this$static.type_0 = e;
  }
   else if ($equalsIgnoreCase(name_0, 'font')) {
    e = new FontType(root);
    nba = castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, getClassLiteralForArray(I_classLit, 1)), 13)).convert(value_0), 11);
    fontno = nba[0];
    e.fontno = fontno;
    if (nba.length > 1) {
      fontbank = nba[1];
      e.fontbank = fontbank;
    }
    if (nba.length > 2) {
      alignmt = nba[2];
      $setAlignmt(e, getValue_2(alignmt));
    }
     else {
      $setAlignmt(e, ($clinit_FontType$ALIGNMT() , center));
    }
    this$static.type_0 = e;
  }
}

function Type(){
  this.offset = new MugenPoint;
  this.pos = new MugenPoint;
  this.scale = new PointF_0(1, 1);
}

defineClass(41, 1, {}, Type);
_.alpha_0 = 1;
_.displaytime = -1;
_.facing = 1;
_.layerno = 0;
_.originalDisplaytime = -1;
_.originalSndtime = 0;
_.originalStarttime = 0;
_.sndtime = 0;
_.starttime = 0;
_.vfacing = 1;
var Lorg_lee_mugen_fight_section_elem_Type_2_classLit = createForClass('org.lee.mugen.fight.section.elem', 'Type', 41);
function $getBigPortrait(this$static, characterId){
  var cached, ic;
  cached = castTo($getStringValue(this$static.bigPortraits, characterId), 76);
  if (cached) {
    return cached;
  }
  ic = $loadPortraitImage(this$static, characterId, 1);
  !!ic && $putStringValue(this$static.bigPortraits, characterId, ic);
  return ic;
}

function $getDisplayName(this$static, characterId){
  var name_0;
  name_0 = castToString($getStringValue(this$static.displayNames, characterId));
  return name_0 != null?name_0:characterId;
}

function $getFightFiles(this$static, characterId){
  var air, sff;
  sff = castToString($getStringValue(this$static.spriteFiles, characterId));
  air = castToString($getStringValue(this$static.animFiles, characterId));
  if (sff == null || air == null) {
    return null;
  }
  return stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_String_2_classLit, 1), $intern_11, 2, 6, [sff, air]);
}

function $getLife(this$static, characterId){
  var life;
  life = castTo($getStringValue(this$static.lifeValues, characterId), 44);
  return life?life.value_0:1000;
}

function $getPortraitSprite(this$static, characterId){
  var cached, fallback, fromSff, ic;
  cached = castTo($getStringValue(this$static.portraitSprites, characterId), 115);
  if (cached) {
    return cached;
  }
  fromSff = $loadPortraitFromSff(this$static, characterId);
  if (fromSff) {
    $putStringValue(this$static.portraitSprites, characterId, fromSff);
    $putStringValue(this$static.portraits, characterId, fromSff._image);
    return fromSff;
  }
  ic = $loadPortraitImage(this$static, characterId, 0);
  if (!ic) {
    return null;
  }
  fallback = new ImageSpriteSFF_0(ic, 0, 0);
  $putStringValue(this$static.portraitSprites, characterId, fallback);
  $putStringValue(this$static.portraits, characterId, ic);
  return fallback;
}

function $loadCachedPcx(characterId, imageIndex){
  var pal, palIn, palPath, palSuffix, raw, rawIn, rawPath, suffix;
  suffix = imageIndex == 1?'braw':'sraw';
  palSuffix = imageIndex == 1?'bpal':'spal';
  rawPath = ($clinit_JMugenConstant() , RESOURCE) + 'cache/char/' + characterId + '/' + characterId + '.' + suffix;
  palPath = RESOURCE + 'cache/char/' + characterId + '/' + characterId + '.' + palSuffix;
  try {
    rawIn = openBinary(rawPath);
    raw = toByteArray(rawIn);
    pal = new PCXPalette;
    try {
      palIn = openBinary(palPath);
      $load_1(pal, palIn);
    }
     catch ($e0) {
      $e0 = toJava($e0);
      if (!instanceOf($e0, 22))
        throw toJs($e0);
    }
    return new RawPCXImage(raw);
  }
   catch ($e1) {
    $e1 = toJava($e1);
    if (instanceOf($e1, 6)) {
      return null;
    }
     else 
      throw toJs($e1);
  }
}

function $loadCharacterMeta(this$static, characterId){
  var br, defPath, eq, key, line, reader, section, suffixlength, value_0;
  defPath = ($clinit_JMugenConstant() , RESOURCE) + 'chars/' + characterId + '/' + characterId + '.def';
  reader = openUtf8Reader(defPath);
  br = new BufferedReader(reader);
  section = '';
  while ((line = $readLine(br)) != null) {
    line = $trim(line);
    if (line.length == 0 || $equals_5(line.substr(0, 1), ';')) {
      continue;
    }
    if ($equals_5(line.substr(0, 1), '[') && (suffixlength = ']'.length , $equals_5(line.substr(line.length - suffixlength, suffixlength), ']'))) {
      section = $trim($substring_0(line, 1, line.length - 1)).toLowerCase();
      continue;
    }
    eq = $indexOf_0(line, fromCodePoint(61));
    if (eq < 0) {
      continue;
    }
    key = $replaceAll($trim((checkCriticalStringBounds(0, eq, line.length) , line.substr(0, eq))).toLowerCase(), '\\s+', '');
    value_0 = stripComment($trim((checkCriticalStringElementIndex(eq + 1, line.length + 1) , line.substr(eq + 1))));
    if ($equals_5('info', section) && $equals_5('displayname', key)) {
      $putStringValue(this$static.displayNames, characterId, unquote(value_0));
    }
     else if ($equals_5('info', section) && $equals_5('life', key)) {
      try {
        $putStringValue(this$static.lifeValues, characterId, valueOf_1(__parseAndValidateInt($trim(value_0), 10)));
      }
       catch ($e0) {
        $e0 = toJava($e0);
        if (!instanceOf($e0, 56))
          throw toJs($e0);
      }
    }
     else 
      $equals_5('files', section) && $equals_5('sprite', key)?$putStringValue(this$static.spriteFiles, characterId, $replace(value_0)):$equals_5('files', section) && $equals_5('anim', key) && $putStringValue(this$static.animFiles, characterId, $replace(value_0));
  }
  $hasStringValue(this$static.displayNames, characterId) || $putStringValue(this$static.displayNames, characterId, characterId);
}

function $loadFromSff(this$static, characterId, imageIndex){
  var data_0, in_0, lastArg, path, sff;
  sff = castToString($getStringValue(this$static.spriteFiles, characterId));
  if (sff == null || sff.length == 0) {
    return null;
  }
  try {
    path = getExistFile_0(($clinit_JMugenConstant() , RESOURCE) + 'chars/' + characterId + '/' + sff);
    in_0 = openBinary(path);
    data_0 = getImage(in_0, imageIndex, null);
    return new RawPCXImage((lastArg = data_0 , new PCXPalette , lastArg));
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 6)) {
      return null;
    }
     else 
      throw toJs($e0);
  }
}

function $loadPortraitFromSff(this$static, characterId){
  var path, sff, spriteSff;
  sff = castToString($getStringValue(this$static.spriteFiles, characterId));
  if (sff == null || sff.length == 0) {
    return null;
  }
  try {
    path = getExistFile_0(($clinit_JMugenConstant() , RESOURCE) + 'chars/' + characterId + '/' + sff);
    spriteSff = new SpriteSFF(new SffReader_0(path), false);
    if (!castTo($get_8(spriteSff._groupMap, valueOf_1(9000)), 51)) {
      return null;
    }
    return $getImgSpr(castTo($get_8(spriteSff._groupMap, valueOf_1(9000)), 51), 0);
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 6)) {
      return null;
    }
     else 
      throw toJs($e0);
  }
}

function $loadPortraitImage(this$static, characterId, imageIndex){
  var pcx;
  pcx = $loadCachedPcx(characterId, imageIndex);
  !pcx && (pcx = $loadFromSff(this$static, characterId, imageIndex));
  if (!pcx) {
    return null;
  }
  return $getImageContainer(getInstance_0(), pcx, 0);
}

function GwtCharacters(characterIds){
  var id_0, id$iterator;
  this.spriteFiles = new HashMap;
  this.animFiles = new HashMap;
  this.displayNames = new HashMap;
  this.portraits = new HashMap;
  this.portraitSprites = new HashMap;
  this.bigPortraits = new HashMap;
  this.lifeValues = new HashMap;
  for (id$iterator = new ArrayList$1(characterIds); id$iterator.i < id$iterator.this$01.array.length;) {
    id_0 = castToString($next_7(id$iterator));
    $loadCharacterMeta(this, id_0);
  }
}

function stripComment(value_0){
  var semi;
  semi = $indexOf_0(value_0, fromCodePoint(59));
  semi >= 0 && (value_0 = $trim((checkCriticalStringBounds(0, semi, value_0.length) , value_0.substr(0, semi))));
  return value_0;
}

function unquote(value_0){
  var suffixlength;
  if (value_0.length >= 2 && $equals_5(value_0.substr(0, 1), '"') && (suffixlength = '"'.length , $equals_5(value_0.substr(value_0.length - suffixlength, suffixlength), '"'))) {
    return $substring_0(value_0, 1, value_0.length - 1);
  }
  return value_0;
}

defineClass(479, 1, {}, GwtCharacters);
var Lorg_lee_mugen_fight_select_GwtCharacters_2_classLit = createForClass('org.lee.mugen.fight.select', 'GwtCharacters', 479);
function $addStage(this$static, path){
  path != null && path.length > 0 && $add_8(this$static.stages, $replace(path));
}

function $getRealName(this$static, path){
  var key, name_0;
  key = $replace(path);
  if ($hasStringValue(this$static.displayNames, key)) {
    return castToString($getStringValue(this$static.displayNames, key));
  }
  name_0 = readStageName(($clinit_JMugenConstant() , RESOURCE) + ('' + key));
  name_0 == null && (name_0 = key);
  $putStringValue(this$static.displayNames, key, name_0);
  return name_0;
}

function GwtExtraStages(){
  this.stages = new ArrayList;
  this.displayNames = new HashMap;
}

function readStageName(resourcePath){
  var br, eq, k, line, reader, section, suffixlength, suffixlength0, value_0;
  reader = null;
  try {
    reader = openUtf8Reader(resourcePath);
    br = new BufferedReader(reader);
    section = '';
    while ((line = $readLine(br)) != null) {
      line = $trim(line);
      if (line.length == 0 || $equals_5(line.substr(0, 1), ';')) {
        continue;
      }
      if ($equals_5(line.substr(0, 1), '[') && (suffixlength0 = ']'.length , $equals_5(line.substr(line.length - suffixlength0, suffixlength0), ']'))) {
        section = $trim($substring_0(line, 1, line.length - 1)).toLowerCase();
        continue;
      }
      if (!$equals_5('info', section)) {
        continue;
      }
      eq = $indexOf_0(line, fromCodePoint(61));
      if (eq < 0) {
        continue;
      }
      k = $trim((checkCriticalStringBounds(0, eq, line.length) , line.substr(0, eq))).toLowerCase();
      if ($equals_5('name', k)) {
        value_0 = $trim((checkCriticalStringElementIndex(eq + 1, line.length + 1) , line.substr(eq + 1)));
        $equals_5(value_0.substr(0, 1), '"') && (suffixlength = '"'.length , $equals_5(value_0.substr(value_0.length - suffixlength, suffixlength), '"')) && value_0.length >= 2 && (value_0 = $substring_0(value_0, 1, value_0.length - 1));
        return value_0;
      }
    }
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 22)) {
      return null;
    }
     else 
      throw toJs($e0);
  }
  return null;
}

defineClass(478, 1, {}, GwtExtraStages);
var Lorg_lee_mugen_fight_select_GwtExtraStages_2_classLit = createForClass('org.lee.mugen.fight.select', 'GwtExtraStages', 478);
function $getCharacterAt(this$static, row, col, columns){
  var index_0;
  index_0 = row * columns + col;
  if (index_0 < 0 || index_0 >= this$static.charactersOrder.array.length) {
    return null;
  }
  return castToString($get_10(this$static.charactersOrder, index_0));
}

function GwtSelect(path){
  var br, comment, line, name_0, reader, section, suffixlength;
  this.charactersOrder = new ArrayList;
  this.extraStages = new GwtExtraStages;
  reader = openUtf8Reader(path);
  br = new BufferedReader(reader);
  section = '';
  while ((line = $readLine(br)) != null) {
    line = $trim(line);
    if (line.length == 0 || $equals_5(line.substr(0, 1), ';')) {
      continue;
    }
    if ($equals_5(line.substr(0, 1), '[') && (suffixlength = ']'.length , $equals_5(line.substr(line.length - suffixlength, suffixlength), ']'))) {
      section = $trim($substring_0(line, 1, line.length - 1)).toLowerCase();
      continue;
    }
    comment = $indexOf_0(line, fromCodePoint(59));
    comment >= 0 && (line = $trim((checkCriticalStringBounds(0, comment, line.length) , line.substr(0, comment))));
    if (line.length == 0) {
      continue;
    }
    if ($equals_5('characters', section)) {
      name_0 = $trim($split(line, ',')[0]);
      name_0.length > 0 && !$equalsIgnoreCase('random', name_0) && $add_8(this.charactersOrder, name_0);
    }
     else 
      $equals_5('extrastages', section) && $addStage(this.extraStages, line);
  }
  this.characters = new GwtCharacters(this.charactersOrder);
}

defineClass(471, 1, {}, GwtSelect);
var Lorg_lee_mugen_fight_select_GwtSelect_2_classLit = createForClass('org.lee.mugen.fight.select', 'GwtSelect', 471);
function Files(){
  this.font = new HashMap;
}

defineClass(442, 1, $intern_2, Files);
_.parse_0 = function parse_5(root, name_0, value_0){
  var bis, in_0, num, sNum, sffReader;
  this.root = root;
  if ($equals_5(name_0, 'spr')) {
    sffReader = new SffReader_0(getExistFile($getCurrentDir(this.root), value_0));
    this.spr = new SpriteSFF(sffReader, false);
  }
   else if ($equals_5(name_0, 'snd')) {
    this.snd = (bis = new BufferedInputStream(openBinary(getExistFile($getCurrentDir(this.root), value_0))) , parse_19(bis));
  }
   else if ($equals_5(name_0, 'select')) {
    this.gwtSelect = new GwtSelect(getExistFile($getCurrentDir(this.root), value_0));
  }
   else if ($equals_5(name_0.substr(0, 4), 'font')) {
    sNum = $indexOf_0(name_0, fromCodePoint(46)) != -1?$substring_0(name_0, 4, $indexOf_0(name_0, fromCodePoint(46))):(checkCriticalStringElementIndex(4, name_0.length + 1) , name_0.substr(4));
    num = sNum.length > 0?__parseAndValidateInt(sNum, 10):0;
    $put_4(this.font, valueOf_1(num), ($clinit_FontParser() , in_0 = openBinary(getExistFile($getCurrentDir(this.root), value_0)) , parse_20(toByteArray(in_0))));
  }
}
;
var Lorg_lee_mugen_fight_system_Files_2_classLit = createForClass('org.lee.mugen.fight.system', 'Files', 442);
function Info(){
}

defineClass(441, 1, $intern_2, Info);
_.parse_0 = function parse_6(root, name_0, value_0){
  $equals_5(name_0, 'name')?(name_0 = value_0):$equals_5(name_0, 'author');
}
;
var Lorg_lee_mugen_fight_system_Info_2_classLit = createForClass('org.lee.mugen.fight.system', 'Info', 441);
function $getCurrentDir(this$static){
  var slash;
  slash = $wnd.Math.max($lastIndexOf(this$static.filename, fromCodePoint(47)), $lastIndexOf(this$static.filename, fromCodePoint(92)));
  if (slash < 0) {
    return new File_1('');
  }
  return new File_1($substring_0(this$static.filename, 0, slash));
}

function $parse_5(this$static){
  var e, groups, grp, grp$iterator, r;
  try {
    r = openUtf8Reader(this$static.filename);
    groups = ($clinit_Parser() , getGroupTextMap(r, false));
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 6)) {
      e = $e0;
      throw toJs(new IOException_0('Failed to read ' + this$static.filename, e));
    }
     else 
      throw toJs($e0);
  }
  try {
    this$static.titleBackground = new GwtBackground($getCurrentDir(this$static), 'title');
    $parse_1(this$static.titleBackground, filterBackgroundGroups(groups, 'titlebg'));
  }
   catch ($e1) {
    $e1 = toJava($e1);
    if (instanceOf($e1, 6)) {
      e = $e1;
      throw toJs(new IOException_0('Failed to parse title background', e));
    }
     else 
      throw toJs($e1);
  }
  try {
    this$static.selectBackground = new GwtBackground($getCurrentDir(this$static), 'select');
    $parse_1(this$static.selectBackground, filterBackgroundGroups(groups, 'selectbg'));
  }
   catch ($e2) {
    $e2 = toJava($e2);
    if (instanceOf($e2, 6)) {
      e = $e2;
      throw toJs(new IOException_0('Failed to parse select background', e));
    }
     else 
      throw toJs($e2);
  }
  try {
    this$static.versusBackground = new GwtBackground($getCurrentDir(this$static), 'versus');
    $parse_1(this$static.versusBackground, filterBackgroundGroups(groups, 'versusbg'));
  }
   catch ($e3) {
    $e3 = toJava($e3);
    if (instanceOf($e3, 6)) {
      e = $e3;
      throw toJs(new IOException_0('Failed to parse versus background', e));
    }
     else 
      throw toJs($e3);
  }
  for (grp$iterator = new ArrayList$1(groups); grp$iterator.i < grp$iterator.this$01.array.length;) {
    grp = castTo($next_7(grp$iterator), 90);
    try {
      if ($equals_5(grp.section, 'info')) {
        this$static.info = new Info;
        $parse_6(this$static, this$static.info, grp);
      }
       else if ($equals_5(grp.section, 'files')) {
        this$static.files = new Files;
        $parse_6(this$static, this$static.files, grp);
      }
       else if ($equals_5(grp.section, 'music')) {
        this$static.music = new Music;
        $parse_6(this$static, this$static.music, grp);
      }
       else if ($equals_5(grp.section, 'title info')) {
        this$static.titleInfo = new TitleInfo;
        $parse_6(this$static, this$static.titleInfo, grp);
      }
       else if ($equals_5(grp.section, 'select info')) {
        this$static.selectInfo = new SelectInfo;
        $parse_6(this$static, this$static.selectInfo, grp);
      }
       else if ($equals_5(grp.section, 'vs screen')) {
        this$static.vsScreen = new VsScreen;
        $parse_6(this$static, this$static.vsScreen, grp);
      }
    }
     catch ($e4) {
      $e4 = toJava($e4);
      if (instanceOf($e4, 6)) {
        e = $e4;
        throw toJs(new IOException_0('Failed to parse section [' + grp.section + ']', e));
      }
       else 
        throw toJs($e4);
    }
  }
}

function $parse_6(this$static, section, grp){
  var key, key$iterator;
  for (key$iterator = $listIterator(grp.keysOrdered, 0); key$iterator.currentNode != key$iterator.this$01.tail;) {
    key = castToString($next_9(key$iterator));
    section.parse_0(this$static, key, castToString($getStringValue(grp.keyValues, key)));
  }
}

function MugenSystem(filename){
  this.filename = filename;
}

function filterBackgroundGroups(groups, prefix){
  var bgPrefix, defSection, grp, grp$iterator, out, s, section;
  out = new ArrayList;
  defSection = prefix + 'def';
  bgPrefix = prefix + ' ';
  for (grp$iterator = new ArrayList$1(groups); grp$iterator.i < grp$iterator.this$01.array.length;) {
    grp = castTo($next_7(grp$iterator), 90);
    section = grp.section;
    if (section == null) {
      continue;
    }
    s = section.toLowerCase();
    ($equals_5(defSection, s) || $equals_5(s.substr(0, bgPrefix.length), bgPrefix)) && (push_1(out.array, grp) , true);
  }
  return out;
}

function getInstance(){
  var e;
  if (!instance_1) {
    instance_1 = new MugenSystem(($clinit_JMugenConstant() , RESOURCE) + 'data/system.def');
    try {
      $parse_5(instance_1);
    }
     catch ($e0) {
      $e0 = toJava($e0);
      if (instanceOf($e0, 6)) {
        e = $e0;
        throw toJs(new IllegalStateException_1(e));
      }
       else 
        throw toJs($e0);
    }
  }
  return instance_1;
}

defineClass(421, 1, {}, MugenSystem);
var instance_1;
var Lorg_lee_mugen_fight_system_MugenSystem_2_classLit = createForClass('org.lee.mugen.fight.system', 'MugenSystem', 421);
function Music(){
}

defineClass(420, 1, $intern_2, Music);
_.parse_0 = function parse_7(root, name_0, value_0){
  $equals_5(name_0, 'title.bgm')?(this.title$bgm = value_0):$equals_5(name_0, 'title.bgm.loop')?__parseAndValidateInt(value_0, 10):$equals_5(name_0, 'select.bgm')?(this.select$bgm = value_0):$equals_5(name_0, 'select.bgm.loop')?__parseAndValidateInt(value_0, 10):$equals_5(name_0, 'vs.bgm')?(this.vs$bgm = value_0):$equals_5(name_0, 'vs.bgm.loop') && __parseAndValidateInt(value_0, 10);
}
;
var Lorg_lee_mugen_fight_system_Music_2_classLit = createForClass('org.lee.mugen.fight.system', 'Music', 420);
function SelectInfo(){
}

defineClass(443, 1, $intern_2, SelectInfo);
_.parse_0 = function parse_8(root, name_0, value_0){
  if ($equals_5(name_0.substr(0, 7), 'fadein.')) {
    !this.fadein && (this.fadein = new Fade);
    $parse_3(this.fadein, $substring(name_0, name_0.indexOf('.') + 1), value_0);
  }
   else if ($equals_5(name_0.substr(0, 8), 'fadeout.')) {
    !this.fadeout && (this.fadeout = new Fade);
    $parse_3(this.fadeout, $substring(name_0, name_0.indexOf('.') + 1), value_0);
  }
   else if ($equals_5(name_0, 'rows')) {
    this.rows_0 = __parseAndValidateInt(value_0, 10);
  }
   else if ($equals_5(name_0, 'columns')) {
    this.columns = __parseAndValidateInt(value_0, 10);
  }
   else if ($equals_5(name_0, 'wrapping')) {
    __parseAndValidateInt(value_0, 10);
  }
   else if ($equals_5(name_0, 'pos')) {
    this.pos = castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, Lorg_lee_mugen_geom_MugenPoint_2_classLit), 13)).convert(value_0), 10);
  }
   else if ($equals_5(name_0, 'showemptyboxes')) {
    __parseAndValidateInt(value_0, 10) != 0;
  }
   else if ($equals_5(name_0, 'moveoveremptyboxes')) {
    __parseAndValidateInt(value_0, 10) != 0;
  }
   else if ($equals_5(name_0.substr(0, 5), 'cell.')) {
    !this.cell && (this.cell = new Cell);
    $parse_7(this.cell, root, $substring(name_0, name_0.indexOf('.') + 1), value_0);
  }
   else if ($equals_5(name_0.substr(0, 3), 'p1.')) {
    !this.player1 && (this.player1 = new PlayerSelectInfo);
    $parse_13(this.player1, root, $substring(name_0, name_0.indexOf('.') + 1), value_0);
  }
   else if ($equals_5(name_0.substr(0, 3), 'p2.')) {
    !this.player2 && (this.player2 = new PlayerSelectInfo);
    $parse_13(this.player2, root, $substring(name_0, name_0.indexOf('.') + 1), value_0);
  }
   else if ($equals_5(name_0, 'random.move.snd.cancel')) {
    __parseAndValidateInt(value_0, 10) != 0;
  }
   else if ($equals_5(name_0.substr(0, 6), 'stage.')) {
    !this.stage && (this.stage = new Stage);
    $parse_14(this.stage, root, $substring(name_0, name_0.indexOf('.') + 1), value_0);
  }
   else if ($equals_5(name_0.substr(0, 13), 'stagedisplay.')) {
    !this.stagedisplay && (this.stagedisplay = new StageDisplay);
    $parse_15(this.stagedisplay, $substring(name_0, name_0.indexOf('.') + 1), value_0);
  }
   else if ($equals_5(name_0.substr(0, 6), 'cancel')) {
    !this.cancel && (this.cancel = new Type);
    $setType_0(this.cancel, $substring(name_0, name_0.indexOf('.') + 1), value_0, root);
    $parse_4(this.cancel, $substring(name_0, name_0.indexOf('.') + 1), value_0);
  }
   else if ($equals_5(name_0.substr(0, 8), 'portrait')) {
    !this.cancel && (this.cancel = new Type);
    $setType_0(this.cancel, $substring(name_0, name_0.indexOf('.') + 1), value_0, root);
    $parse_4(this.cancel, $substring(name_0, name_0.indexOf('.') + 1), value_0);
  }
   else if ($equals_5(name_0.substr(0, 5), 'title')) {
    !this.title_0 && (this.title_0 = new Type);
    $setType_0(this.title_0, $substring(name_0, name_0.indexOf('.') + 1), value_0, root);
    $parse_4(this.title_0, $substring(name_0, name_0.indexOf('.') + 1), value_0);
  }
   else 
    $equals_5(name_0, 'teammenu.move.wrapping') && __parseAndValidateInt(value_0, 10);
}
;
_.columns = 0;
_.rows_0 = 0;
var Lorg_lee_mugen_fight_system_SelectInfo_2_classLit = createForClass('org.lee.mugen.fight.system', 'SelectInfo', 443);
function $init_1(this$static){
  $init_0(this$static.fadein);
  $init_0(this$static.fadeout);
}

function $process_3(this$static){
  this$static.phase == -1 && (this$static.lastPhase == 3 || this$static.lastPhase == -1) && ($init_0(this$static.fadein) , $init_0(this$static.fadeout));
  if (this$static.phase == 0) {
    $decrease(this$static.fadein);
    this$static.fadein.time <= 0 && (this$static.lastPhase = this$static.phase , this$static.phase = 1);
  }
  if (this$static.phase == 2) {
    $decrease(this$static.fadeout);
    this$static.fadeout.time <= 0 && (this$static.lastPhase = this$static.phase , this$static.phase = 3);
  }
}

function $setPhase(this$static, phase){
  this$static.lastPhase = this$static.phase;
  this$static.phase = phase;
}

function TitleInfo(){
}

defineClass(423, 1, $intern_2, TitleInfo);
_.parse_0 = function parse_9(root, name_0, value_0){
  if ($equals_5(name_0.substr(0, 7), 'fadein.')) {
    !this.fadein && (this.fadein = new Fade);
    $parse_3(this.fadein, $substring(name_0, name_0.indexOf('.') + 1), value_0);
  }
   else if ($equals_5(name_0.substr(0, 8), 'fadeout.')) {
    !this.fadeout && (this.fadeout = new Fade);
    $parse_3(this.fadeout, $substring(name_0, name_0.indexOf('.') + 1), value_0);
  }
   else if ($equals_5(name_0.substr(0, 5), 'menu.') || $equals_5(name_0.substr(0, 7), 'cursor.')) {
    !this.menu && (this.menu = new Menu);
    $equals_5(name_0.substr(0, 7), 'cursor.')?$parse_11(this.menu, root, name_0, value_0):$parse_11(this.menu, root, $substring(name_0, name_0.indexOf('.') + 1), value_0);
  }
}
;
_.lastPhase = -1;
_.phase = -1;
var Lorg_lee_mugen_fight_system_TitleInfo_2_classLit = createForClass('org.lee.mugen.fight.system', 'TitleInfo', 423);
function $process_4(this$static){
  this$static.phase == -1 && (this$static.lastPhase == 3 || this$static.lastPhase == -1) && $resetState(this$static);
  if (this$static.phase == 0) {
    if (this$static.fadein) {
      $decrease(this$static.fadein);
      this$static.fadein.time <= 0 && (this$static.lastPhase = this$static.phase , this$static.phase = 1);
    }
     else {
      this$static.lastPhase = this$static.phase;
      this$static.phase = 1;
    }
  }
  this$static.phase == 1 && --this$static.time;
  if (this$static.phase == 2) {
    if (this$static.fadeout) {
      $decrease(this$static.fadeout);
      this$static.fadeout.time <= 0 && (this$static.lastPhase = this$static.phase , this$static.phase = 3);
    }
     else {
      this$static.lastPhase = this$static.phase;
      this$static.phase = 3;
    }
  }
}

function $resetState(this$static){
  this$static.time = this$static.originalTime > 0?this$static.originalTime:180;
  !!this$static.fadein && $init_0(this$static.fadein);
  !!this$static.fadeout && $init_0(this$static.fadeout);
  this$static.phase = -1;
  this$static.lastPhase = -1;
}

function VsScreen(){
}

defineClass(444, 1, $intern_2, VsScreen);
_.parse_0 = function parse_10(root, name_0, value_0){
  if ($equals_5(name_0.substr(0, 7), 'fadein.')) {
    !this.fadein && (this.fadein = new Fade);
    $parse_3(this.fadein, $substring(name_0, name_0.indexOf('.') + 1), value_0);
  }
   else if ($equals_5(name_0.substr(0, 8), 'fadeout.')) {
    !this.fadeout && (this.fadeout = new Fade);
    $parse_3(this.fadeout, $substring(name_0, name_0.indexOf('.') + 1), value_0);
  }
   else if ($equalsIgnoreCase('time', name_0)) {
    this.originalTime = __parseAndValidateInt($trim(value_0), 10);
    this.time = this.originalTime;
  }
   else if ($equals_5(name_0.substr(0, 3), 'p1.')) {
    !this.p1 && (this.p1 = new GwtPlayerVsScreen);
    $parse_8(this.p1, root, $substring(name_0, name_0.indexOf('.') + 1), value_0);
  }
   else if ($equals_5(name_0.substr(0, 3), 'p2.')) {
    !this.p2 && (this.p2 = new GwtPlayerVsScreen);
    $parse_8(this.p2, root, $substring(name_0, name_0.indexOf('.') + 1), value_0);
  }
}
;
_.lastPhase = -1;
_.originalTime = 180;
_.phase = -1;
_.time = 0;
var Lorg_lee_mugen_fight_system_VsScreen_2_classLit = createForClass('org.lee.mugen.fight.system', 'VsScreen', 444);
function $parse_7(this$static, root, name_0, value_0){
  var dim;
  if ($equals_5(name_0, 'size')) {
    dim = castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, getClassLiteralForArray(I_classLit, 1)), 13)).convert(value_0), 11);
    this$static.size_0 = new MugenPoint_0(dim[0], dim[1]);
  }
   else if ($equals_5(name_0, 'spacing')) {
    this$static.spacing = __parseAndValidateInt(value_0, 10);
  }
   else if ($equals_5(name_0.substr(0, 2), 'bg')) {
    !this$static.bg && (this$static.bg = new Type);
    $setType_0(this$static.bg, $substring(name_0, name_0.indexOf('.') + 1), value_0, root);
    $parse_4(this$static.bg, $substring(name_0, name_0.indexOf('.') + 1), value_0);
  }
   else if ($equals_5(name_0, 'random')) {
    !this$static.random && (this$static.random = new Type);
    $setType_0(this$static.random, $substring(name_0, name_0.indexOf('.') + 1), value_0, root);
    $parse_4(this$static.random, $substring(name_0, name_0.indexOf('.') + 1), value_0);
  }
   else 
    $equals_5(name_0, 'random.switchtime') && __parseAndValidateInt(value_0, 10);
}

function Cell(){
}

defineClass(473, 1, $intern_2, Cell);
_.parse_0 = function parse_11(root, name_0, value_0){
  $parse_7(this, root, name_0, value_0);
}
;
_.spacing = 0;
var Lorg_lee_mugen_fight_system_elem_Cell_2_classLit = createForClass('org.lee.mugen.fight.system.elem', 'Cell', 473);
function $parse_8(this$static, root, name_0, value_0){
  if ($equals_5(name_0.substr(0, 5), 'name.')) {
    !this$static.name_0 && (this$static.name_0 = new Type);
    $setType_0(this$static.name_0, $substring(name_0, name_0.indexOf('.') + 1), value_0, root);
    $parse_4(this$static.name_0, $substring(name_0, name_0.indexOf('.') + 1), value_0);
  }
   else 
    $equals_5('pos', name_0)?(this$static.pos = castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, Lorg_lee_mugen_geom_MugenPoint_2_classLit), 13)).convert(value_0), 10)):$equals_5('facing', name_0)?(this$static.facing = __parseAndValidateInt($trim(value_0), 10)):$equals_5('scale', name_0) && (this$static.scale = castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, Lorg_lee_mugen_sprite_entity_PointF_2_classLit), 13)).convert(value_0), 29));
}

function GwtPlayerVsScreen(){
  this.pos = new MugenPoint;
  this.scale = new PointF_0(1, 1);
}

defineClass(248, 1, $intern_2, GwtPlayerVsScreen);
_.parse_0 = function parse_12(root, name_0, value_0){
  $parse_8(this, root, name_0, value_0);
}
;
_.facing = 1;
var Lorg_lee_mugen_fight_system_elem_GwtPlayerVsScreen_2_classLit = createForClass('org.lee.mugen.fight.system.elem', 'GwtPlayerVsScreen', 248);
function $parse_9(this$static, name_0, value_0){
  $parse_4(this$static, name_0, value_0);
  if ($equalsIgnoreCase(name_0, 'spacing')) {
    this$static.spacing = castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, Lorg_lee_mugen_geom_MugenPoint_2_classLit), 13)).convert(value_0), 10);
  }
   else if ($equals_5(name_0.substr(0, 7), 'active.')) {
    !this$static.active && (this$static.active = new Type);
    $setType_0(this$static.active, $substring(name_0, name_0.indexOf('.') + 1), value_0, this$static.ms);
  }
   else if ($equals_5(name_0.substr(0, 8), 'active2.')) {
    !this$static.active2 && (this$static.active2 = new Type);
    $setType_0(this$static.active2, $substring(name_0, name_0.indexOf('.') + 1), value_0, this$static.ms);
  }
   else if ($equals_5(name_0.substr(0, 6), 'cursor')) {
    !this$static.cursor && (this$static.cursor = new Type);
    $setType_0(this$static.cursor, $substring(name_0, name_0.indexOf('.') + 1), value_0, this$static.ms);
  }
}

function Item(ms){
  Type.call(this);
  this.spacing = new MugenPoint;
  this.ms = ms;
}

defineClass(480, 41, {}, Item);
var Lorg_lee_mugen_fight_system_elem_Item_2_classLit = createForClass('org.lee.mugen.fight.system.elem', 'Item', 480);
function $decreaseCurrentIndex(this$static){
  --this$static.currentIndex;
  while (this$static.currentIndex < this$static.first)
    --this$static.first;
  this$static.currentIndex < 0 && $setCurrentIndex(this$static, this$static.list.length - 1);
}

function $getGoodString(word){
  var suffixlength;
  if ($equals_5(word.substr(0, 1), '"') && (suffixlength = '"'.length , $equals_5(word.substr(word.length - suffixlength, suffixlength), '"')))
    return $substring_0(word, 1, word.length - 1);
  return word;
}

function $increaseCurrentIndex(this$static){
  ++this$static.currentIndex;
  while (this$static.currentIndex > this$static.first + getInstance().titleInfo.menu.window$visibleitems - 1)
    ++this$static.first;
  this$static.currentIndex > this$static.list.length - 1 && $setCurrentIndex(this$static, 0);
}

function $parse_10(this$static, name_0, value_0){
  $equals_5(name_0, 'arcade')?(this$static.list[0] = $getGoodString(value_0)):$equals_5(name_0, 'versus')?(this$static.list[1] = $getGoodString(value_0)):$equals_5(name_0, 'teamarcade')?(this$static.list[2] = $getGoodString(value_0)):$equals_5(name_0, 'teamversus')?(this$static.list[3] = $getGoodString(value_0)):$equals_5(name_0, 'teamcoop')?(this$static.list[4] = $getGoodString(value_0)):$equals_5(name_0, 'survival')?(this$static.list[5] = $getGoodString(value_0)):$equals_5(name_0, 'survivalcoop')?(this$static.list[6] = $getGoodString(value_0)):$equals_5(name_0, 'training')?(this$static.list[7] = $getGoodString(value_0)):$equals_5(name_0, 'watch')?(this$static.list[8] = $getGoodString(value_0)):$equals_5(name_0, 'options')?(this$static.list[9] = $getGoodString(value_0)):$equals_5(name_0, 'exit') && (this$static.list[10] = $getGoodString(value_0));
}

function $setCurrentIndex(this$static, currentIndex){
  if (this$static.currentIndex == currentIndex)
    return;
  if (currentIndex < 0 || currentIndex > this$static.list.length)
    return;
  this$static.currentIndex = 0;
  this$static.first = 0;
  while (this$static.currentIndex != currentIndex)
    $increaseCurrentIndex(this$static);
}

function ItemName(){
  this.list = initUnidimensionalArray(Ljava_lang_String_2_classLit, $intern_11, 2, 11, 6, 1);
}

defineClass(481, 1, $intern_2, ItemName);
_.parse_0 = function parse_13(root, name_0, value_0){
  $parse_10(this, name_0, value_0);
}
;
_.currentIndex = 0;
_.first = 0;
var Lorg_lee_mugen_fight_system_elem_ItemName_2_classLit = createForClass('org.lee.mugen.fight.system.elem', 'ItemName', 481);
function $parse_11(this$static, root, name_0, value_0){
  var res;
  this$static.ms = root;
  if ($equals_5(name_0.substr(0, 5), 'item.')) {
    !this$static.item_0 && (this$static.item_0 = new Item(this$static.ms));
    $setType_0(this$static.item_0, $substring(name_0, name_0.indexOf('.') + 1), value_0, this$static.ms);
    $parse_9(this$static.item_0, $substring(name_0, name_0.indexOf('.') + 1), value_0);
  }
   else if ($equals_5(name_0, 'pos')) {
    this$static.pos = castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, Lorg_lee_mugen_geom_MugenPoint_2_classLit), 13)).convert(value_0), 10);
  }
   else if ($equals_5(name_0.substr(0, 8), 'itemname')) {
    !this$static.itemname && (this$static.itemname = new ItemName);
    $parse_10(this$static.itemname, $substring(name_0, name_0.indexOf('.') + 1), value_0);
  }
   else if ($equals_5(name_0, 'window.margins.y')) {
    this$static.window$margins$y = castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, Lorg_lee_mugen_geom_MugenPoint_2_classLit), 13)).convert(value_0), 10);
  }
   else if ($equals_5(name_0, 'window.visibleitems')) {
    this$static.window$visibleitems = __parseAndValidateInt(value_0, 10);
  }
   else if ($equals_5(name_0, 'boxcursor.visible')) {
    this$static.boxcursor$visible = __parseAndValidateInt(value_0, 10);
  }
   else if ($equals_5(name_0, 'boxcursor.coords')) {
    res = castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, getClassLiteralForArray(I_classLit, 1)), 13)).convert(value_0), 11);
    this$static.boxcursor$coords = new Rectangle_1(res[0], res[1], res[2], res[3]);
  }
   else 
    $equals_5(name_0, 'cursor.move.snd')?(this$static.cursor$move$snd = castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, Lorg_lee_mugen_sprite_entity_SndGrpNum_2_classLit), 13)).convert(value_0), 129)):$equals_5(name_0, 'cursor.done.snd')?(this$static.cursor$done$snd = castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, Lorg_lee_mugen_sprite_entity_SndGrpNum_2_classLit), 13)).convert(value_0), 129)):$equals_5(name_0, 'cancel.snd') && castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, Lorg_lee_mugen_sprite_entity_SndGrpNum_2_classLit), 13)).convert(value_0), 129);
}

function Menu(){
}

defineClass(472, 1, $intern_2, Menu);
_.parse_0 = function parse_14(root, name_0, value_0){
  $parse_11(this, root, name_0, value_0);
}
;
_.boxcursor$visible = 0;
_.window$visibleitems = 0;
var Lorg_lee_mugen_fight_system_elem_Menu_2_classLit = createForClass('org.lee.mugen.fight.system.elem', 'Menu', 472);
function $parse_12(this$static, root, name_0, value_0){
  if ($equals_5(name_0, 'startcell')) {
    this$static.startcell = castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, Lorg_lee_mugen_geom_MugenPoint_2_classLit), 13)).convert(value_0), 10);
  }
   else if ($equals_5(name_0.substr(0, 6), 'active')) {
    !this$static.active && (this$static.active = new Type);
    $setType_0(this$static.active, $substring(name_0, name_0.indexOf('.') + 1), value_0, root);
    $parse_4(this$static.active, $substring(name_0, name_0.indexOf('.') + 1), value_0);
  }
   else if ($equals_5(name_0.substr(0, 4), 'done')) {
    !this$static.done_0 && (this$static.done_0 = new Type);
    $setType_0(this$static.done_0, $substring(name_0, name_0.indexOf('.') + 1), value_0, root);
    $parse_4(this$static.done_0, $substring(name_0, name_0.indexOf('.') + 1), value_0);
  }
   else if ($equals_5(name_0.substr(0, 4), 'move')) {
    !this$static.move && (this$static.move = new Type);
    $setType_0(this$static.move, $substring(name_0, name_0.indexOf('.') + 1), value_0, root);
    $parse_4(this$static.move, $substring(name_0, name_0.indexOf('.') + 1), value_0);
  }
}

function PlayerCursor(){
}

defineClass(482, 1, $intern_2, PlayerCursor);
_.parse_0 = function parse_15(root, name_0, value_0){
  $parse_12(this, root, name_0, value_0);
}
;
var Lorg_lee_mugen_fight_system_elem_PlayerCursor_2_classLit = createForClass('org.lee.mugen.fight.system.elem', 'PlayerCursor', 482);
function $parse_13(this$static, root, name_0, value_0){
  if ($equals_5(name_0.substr(0, 7), 'cursor.')) {
    !this$static.cursor && (this$static.cursor = new PlayerCursor);
    $parse_12(this$static.cursor, root, $substring(name_0, name_0.indexOf('.') + 1), value_0);
  }
   else if ($equals_5(name_0.substr(0, 12), 'random.move.')) {
    !this$static.random$move && (this$static.random$move = new Type);
    $setType_0(this$static.random$move, $substring(name_0, name_0.indexOf('.') + 1), value_0, root);
    $parse_4(this$static.random$move, $substring(name_0, name_0.indexOf('.') + 1), value_0);
  }
   else if ($equals_5(name_0.substr(0, 5), 'face.')) {
    !this$static.face_0 && (this$static.face_0 = new Type);
    $setType_0(this$static.face_0, $substring(name_0, name_0.indexOf('.') + 1), value_0, root);
    $parse_4(this$static.face_0, $substring(name_0, name_0.indexOf('.') + 1), value_0);
  }
   else if ($equals_5(name_0.substr(0, 5), 'name.')) {
    !this$static.name_0 && (this$static.name_0 = new Type);
    $setType_0(this$static.name_0, $substring(name_0, name_0.indexOf('.') + 1), value_0, root);
    $parse_4(this$static.name_0, $substring(name_0, name_0.indexOf('.') + 1), value_0);
  }
}

function PlayerSelectInfo(){
}

defineClass(247, 1, $intern_2, PlayerSelectInfo);
_.parse_0 = function parse_16(root, name_0, value_0){
  $parse_13(this, root, name_0, value_0);
}
;
var Lorg_lee_mugen_fight_system_elem_PlayerSelectInfo_2_classLit = createForClass('org.lee.mugen.fight.system.elem', 'PlayerSelectInfo', 247);
function $parse_14(this$static, root, name_0, value_0){
  if ($equals_5(name_0, 'pos')) {
    this$static.pos = castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, Lorg_lee_mugen_geom_MugenPoint_2_classLit), 13)).convert(value_0), 10);
  }
   else if ($equals_5(name_0.substr(0, 5), 'move.')) {
    !this$static.move && (this$static.move = new Type);
    $setType_0(this$static.move, $substring(name_0, name_0.indexOf('.') + 1), value_0, root);
    $parse_4(this$static.move, $substring(name_0, name_0.indexOf('.') + 1), value_0);
  }
   else if ($equals_5(name_0.substr(0, 5), 'done.')) {
    !this$static.done_0 && (this$static.done_0 = new Type);
    $setType_0(this$static.done_0, $substring(name_0, name_0.indexOf('.') + 1), value_0, root);
    $parse_4(this$static.done_0, $substring(name_0, name_0.indexOf('.') + 1), value_0);
  }
   else if ($equals_5(name_0.substr(0, 7), 'active.')) {
    !this$static.active && (this$static.active = new Type);
    $setType_0(this$static.active, $substring(name_0, name_0.indexOf('.') + 1), value_0, root);
    $parse_4(this$static.active, $substring(name_0, name_0.indexOf('.') + 1), value_0);
  }
   else if ($equals_5(name_0.substr(0, 8), 'active2.')) {
    !this$static.active2 && (this$static.active2 = new Type);
    $setType_0(this$static.active2, $substring(name_0, name_0.indexOf('.') + 1), value_0, root);
    $parse_4(this$static.active2, $substring(name_0, name_0.indexOf('.') + 1), value_0);
  }
}

function Stage(){
}

defineClass(474, 1, $intern_2, Stage);
_.parse_0 = function parse_17(root, name_0, value_0){
  $parse_14(this, root, name_0, value_0);
}
;
var Lorg_lee_mugen_fight_system_elem_Stage_2_classLit = createForClass('org.lee.mugen.fight.system.elem', 'Stage', 474);
function $parse_15(this$static, name_0, value_0){
  $equals_5('enable', name_0)?(this$static.enable_0 = __parseAndValidateInt(value_0, 10) != 0):$equals_5('pos', name_0)?(this$static.pos = castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, Lorg_lee_mugen_geom_MugenPoint_2_classLit), 13)).convert(value_0), 10)):$equals_5('rectangle', name_0)?(this$static.rectangle = castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, Lorg_lee_mugen_geom_MugenRect_2_classLit), 13)).convert(value_0), 151)):$equals_5('scale', name_0)?(this$static.scale = castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, Lorg_lee_mugen_sprite_entity_PointF_2_classLit), 13)).convert(value_0), 29)):$equals_5('camera', name_0) && (this$static.camera = castTo(($clinit_BeanTools() , castTo($get_8(convertersMap, Lorg_lee_mugen_geom_MugenPoint_2_classLit), 13)).convert(value_0), 10));
}

function StageDisplay(){
}

defineClass(475, 1, $intern_2, StageDisplay);
_.parse_0 = function parse_18(root, name_0, value_0){
  $parse_15(this, name_0, value_0);
}
;
_.enable_0 = false;
var Lorg_lee_mugen_fight_system_elem_StageDisplay_2_classLit = createForClass('org.lee.mugen.fight.system.elem', 'StageDisplay', 475);
function $copy_4(this$static){
  return new MugenPoint_0(this$static.x_0, this$static.y_0);
}

function MugenPoint(){
}

function MugenPoint_0(x_0, y_0){
  this.x_0 = x_0;
  this.y_0 = y_0;
}

defineClass(10, 1, {3:1, 10:1}, MugenPoint, MugenPoint_0);
_.equals_0 = function equals_36(o){
  var m;
  if (this === o) {
    return true;
  }
  if (!instanceOf(o, 10)) {
    return false;
  }
  m = castTo(o, 10);
  return this.x_0 == m.x_0 && this.y_0 == m.y_0;
}
;
_.hashCode_0 = function hashCode_39(){
  return 31 * this.x_0 + this.y_0;
}
;
_.x_0 = 0;
_.y_0 = 0;
var Lorg_lee_mugen_geom_MugenPoint_2_classLit = createForClass('org.lee.mugen.geom', 'MugenPoint', 10);
function MugenRect(){
}

defineClass(151, 1, {3:1, 151:1}, MugenRect);
_.height_0 = 0;
_.width_0 = 0;
var Lorg_lee_mugen_geom_MugenRect_2_classLit = createForClass('org.lee.mugen.geom', 'MugenRect', 151);
function PCXHeader(data_0){
  var in_0;
  this.colormap = initUnidimensionalArray(B_classLit, $intern_6, 5, 48, 15, 1);
  in_0 = new LittleEndianDataInputStream(new DataInputStream(new ByteArrayInputStream(data_0)));
  in_0.in_0.read() << 24 >> 24;
  in_0.in_0.read() << 24 >> 24;
  in_0.in_0.read() << 24 >> 24;
  in_0.in_0.read() << 24 >> 24;
  this.xmin = $readUnsignedShort(in_0);
  this.ymin = $readUnsignedShort(in_0);
  this.xmax = $readUnsignedShort(in_0);
  this.ymax = $readUnsignedShort(in_0);
  $readUnsignedShort(in_0);
  $readUnsignedShort(in_0);
  $read_0(in_0, this.colormap);
  in_0.in_0.read() << 24 >> 24;
  in_0.in_0.read() << 24 >> 24;
  this.bytesPerLine = $readUnsignedShort(in_0);
  in_0.in_0.close_0();
}

defineClass(250, 1, {}, PCXHeader);
_.bytesPerLine = 0;
_.xmax = 0;
_.xmin = 0;
_.ymax = 0;
_.ymin = 0;
var Lorg_lee_mugen_imageIO_PCXHeader_2_classLit = createForClass('org.lee.mugen.imageIO', 'PCXHeader', 250);
function $getColor(this$static, i){
  i < 0 && (i = 255 + i);
  return -16777216 + (this$static.r[i] << 16) + (this$static.g[i] << 8) + this$static.b[i];
}

function $load_1(this$static, in_0){
  var i;
  $skip(in_0, in_0.count - in_0.pos - 768);
  for (i = 0; i < 256; i++) {
    this$static.r[i] = (in_0.pos < in_0.count?in_0.buf[in_0.pos++] & 255:-1) << 24 >> 24;
    this$static.g[i] = (in_0.pos < in_0.count?in_0.buf[in_0.pos++] & 255:-1) << 24 >> 24;
    this$static.b[i] = (in_0.pos < in_0.count?in_0.buf[in_0.pos++] & 255:-1) << 24 >> 24;
  }
}

function PCXPalette(){
  this.r = initUnidimensionalArray(B_classLit, $intern_6, 5, 256, 15, 1);
  this.g = initUnidimensionalArray(B_classLit, $intern_6, 5, 256, 15, 1);
  this.b = initUnidimensionalArray(B_classLit, $intern_6, 5, 256, 15, 1);
}

defineClass(132, 1, {}, PCXPalette);
var Lorg_lee_mugen_imageIO_PCXPalette_2_classLit = createForClass('org.lee.mugen.imageIO', 'PCXPalette', 132);
function RawPCXImage(byteArray){
  this.buffer_0 = new ReadWriteHeapByteBuffer(byteArray);
}

defineClass(93, 1, {93:1}, RawPCXImage);
var Lorg_lee_mugen_imageIO_RawPCXImage_2_classLit = createForClass('org.lee.mugen.imageIO', 'RawPCXImage', 93);
function AbstractCommand(key, tick, kind){
  this.key = key;
  this.tick = tick;
  this.kind = kind;
}

defineClass(272, 1, {});
_.toString_0 = function toString_56(){
  return this.kind + ' ' + this.key + ' @t ' + toString_29(this.tick);
}
;
_.key = 0;
_.tick = 0;
var Lorg_lee_mugen_input_AbstractCommand_2_classLit = createForClass('org.lee.mugen.input', 'AbstractCommand', 272);
function $clinit_AbstractCommand$Kind(){
  $clinit_AbstractCommand$Kind = emptyMethod;
  PRESS = new AbstractCommand$Kind('PRESS', 0);
  RELEASE = new AbstractCommand$Kind('RELEASE', 1);
}

function AbstractCommand$Kind(enum$name, enum$ordinal){
  Enum.call(this, enum$name, enum$ordinal);
}

function values_15(){
  $clinit_AbstractCommand$Kind();
  return stampJavaTypeInfo(getClassLiteralForArray(Lorg_lee_mugen_input_AbstractCommand$Kind_2_classLit, 1), $intern_1, 158, 0, [PRESS, RELEASE]);
}

defineClass(158, 8, {3:1, 7:1, 8:1, 158:1}, AbstractCommand$Kind);
var PRESS, RELEASE;
var Lorg_lee_mugen_input_AbstractCommand$Kind_2_classLit = createForEnum('org.lee.mugen.input', 'AbstractCommand/Kind', 158, values_15);
function $clinit_CmdProcDispatcher(){
  $clinit_CmdProcDispatcher = emptyMethod;
  spriteDispatcherMap = new HashMap;
  inverseMap = new HashMap;
  $put_4(inverseMap, ($clinit_Key() , B), F_0);
  $put_4(inverseMap, F_0, B);
  $put_4(inverseMap, DB, DF);
  $put_4(inverseMap, UB, UF);
  $put_4(inverseMap, DF, DB);
  $put_4(inverseMap, UF, UB);
}

function $getDirKeyLocked(this$static){
  var result;
  result = null;
  if ((result = castTo($get_11(this$static.keyLockFactory, ($clinit_Key() , B)), 16)).token.value_0 == ($clinit_KeyLockCommand$State() , LOCK)) {
    return result;
  }
  if ((result = castTo($get_11(this$static.keyLockFactory, DB), 16)).token.value_0 == LOCK) {
    return result;
  }
  if ((result = castTo($get_11(this$static.keyLockFactory, D), 16)).token.value_0 == LOCK) {
    return result;
  }
  if ((result = castTo($get_11(this$static.keyLockFactory, DF), 16)).token.value_0 == LOCK) {
    return result;
  }
  if ((result = castTo($get_11(this$static.keyLockFactory, F_0), 16)).token.value_0 == LOCK) {
    return result;
  }
  if ((result = castTo($get_11(this$static.keyLockFactory, UF), 16)).token.value_0 == LOCK) {
    return result;
  }
  if ((result = castTo($get_11(this$static.keyLockFactory, U), 16)).token.value_0 == LOCK) {
    return result;
  }
  if ((result = castTo($get_11(this$static.keyLockFactory, UB), 16)).token.value_0 == LOCK) {
    return result;
  }
  return null;
}

function $getKeys(this$static){
  this$static.keys_0 == null && (this$static.keys_0 = stampJavaTypeInfo(getClassLiteralForArray(I_classLit, 1), $intern_3, 5, 15, [this$static.up, this$static.down, this$static.back_0, this$static.forward_0, this$static.a, this$static.b, this$static.c, this$static.x_0, this$static.y_0, this$static.z_0, this$static.abc, this$static.xyz]));
  return this$static.keys_0;
}

function $getSequence(this$static, gameTime, timeBufferToSearch){
  var entry, klc, klc$iterator, klc$iterator0, klcs, kp, outerIter, remove, result, sc, sc$array, sc$index, sc$max, time;
  time = sub_1(gameTime, timeBufferToSearch);
  result = new LinkedList;
  klcs = new LinkedList;
  for (klc$iterator0 = (outerIter = (new AbstractMap$2(this$static.keyLockFactory)).this$01.entrySet().iterator() , new AbstractMap$2$1(outerIter)); klc$iterator0.val$outerIter2.hasNext_0();) {
    klc = (entry = castTo(klc$iterator0.val$outerIter2.next_1(), 43) , castTo(entry.getValue(), 16));
    klc.isPress && lt(klc.pressTick, time) && (klc.token.value_0 == ($clinit_KeyLockCommand$State() , LOCK) || klc.token.value_0 == HIDDEN) && ($addNode(klcs, klc, klcs.tail.prev, klcs.tail) , true);
  }
  $clinit_Collections();
  $sort(klcs, new CmdProcDispatcher$1);
  for (klc$iterator = $listIterator(klcs, 0); klc$iterator.currentNode != klc$iterator.this$01.tail;) {
    klc = castTo($next_9(klc$iterator), 16);
    sc = new PressCommand(klc.key.bit, klc.pressTick);
    kp = new KeyProc(sc, klc.token.value_0);
    $add_11(result, new SingleCmdProcessor(klc.pressTick, stampJavaTypeInfo(getClassLiteralForArray(Lorg_lee_mugen_input_KeyProc_2_classLit, 1), $intern_63, 40, 0, [kp])));
  }
  remove = new ArrayList;
  for (sc$array = castTo($toArray(this$static.mugenKeyEvents, initUnidimensionalArray(Lorg_lee_mugen_input_SingleCmdProcessor_2_classLit, $intern_64, 70, 0, 0, 1)), 275) , sc$index = 0 , sc$max = sc$array.length; sc$index < sc$max; ++sc$index) {
    sc = sc$array[sc$index];
    gte_0(sc.tick, time) && ($addNode(result, sc, result.tail.prev, result.tail) , true);
    lt(sc.tick, sub_1(gameTime, 50)) && (push_1(remove.array, sc) , true);
  }
  $removeAll(this$static.mugenKeyEvents, remove);
  return castTo($toArray(result, initUnidimensionalArray(Lorg_lee_mugen_input_SingleCmdProcessor_2_classLit, $intern_64, 70, result.size_0, 0, 1)), 275);
}

function $isCombinedDirKey(key){
  return key != ($clinit_Key() , U) && key != D && key != B && key != F_0;
}

function $press(this$static, keyCode, tick, isFlip){
  var key;
  $pressDir(this$static, keyCode, tick);
  if (this$static.abc == keyCode) {
    $press(this$static, this$static.a, tick, isFlip);
    $press(this$static, this$static.b, tick, isFlip);
    return true;
  }
  if (this$static.xyz == keyCode) {
    $press(this$static, this$static.x_0, tick, isFlip);
    $press(this$static, this$static.y_0, tick, isFlip);
    return true;
  }
  key = castTo($get_8(this$static.keyCodeToKeyMap, valueOf_1(keyCode)), 33);
  if (!key)
    return true;
  (key.bit & (($clinit_Key() , U).bit | D.bit | B.bit | F_0.bit)) != key.bit && $pressButton(this$static, key, tick);
  return true;
}

function $pressButton(this$static, key, tick){
  var keyProcNew, newKlc, scp;
  if (this$static.diseable)
    return;
  if (!key)
    return;
  if ((key.bit & (($clinit_Key() , U).bit | D.bit | B.bit | F_0.bit)) == key.bit)
    return;
  newKlc = castTo($get_11(this$static.keyLockFactory, key), 16);
  if (newKlc.token.value_0 == ($clinit_KeyLockCommand$State() , LOCK)) {
    return;
  }
  if (newKlc.token.value_0 == HIDDEN) {
    return;
  }
  tick = sub_1(tick, 1);
  $setPressTick(newKlc, tick);
  keyProcNew = new KeyProc(newKlc.cmd, newKlc.token.value_0);
  scp = new SingleCmdProcessor(tick, stampJavaTypeInfo(getClassLiteralForArray(Lorg_lee_mugen_input_KeyProc_2_classLit, 1), $intern_63, 40, 0, [keyProcNew]));
  $add_11(this$static.mugenKeyEvents, scp);
}

function $pressDir(this$static, keyCode, tick){
  var key;
  if (this$static.diseable)
    return;
  key = castTo($get_8(this$static.keyCodeToKeyMap, valueOf_1(keyCode)), 33);
  $pressDir_0(this$static, key, tick);
}

function $pressDir_0(this$static, key, tick){
  var combinedKey, combinedKeyLock, keyProcCombinedKeyLock, keyProcKlc, keyProcNew, keyProcNew0, klc, moveDirection, newKlc, scp, scp0;
  if (this$static.diseable)
    return;
  if (!key)
    return;
  if ((key.bit & (($clinit_Key() , U).bit | D.bit | B.bit | F_0.bit)) != key.bit)
    return;
  (key.bit & U.bit) == U.bit && $releaseDir_0(this$static, D, tick);
  (key.bit & D.bit) == D.bit && $releaseDir_0(this$static, U, tick);
  (key.bit & F_0.bit) == F_0.bit && $releaseDir_0(this$static, B, tick);
  (key.bit & B.bit) == B.bit && $releaseDir_0(this$static, F_0, tick);
  newKlc = castTo($get_11(this$static.keyLockFactory, key), 16);
  if (newKlc.token.value_0 == ($clinit_KeyLockCommand$State() , LOCK)) {
    return;
  }
  if (newKlc.token.value_0 == HIDDEN) {
    return;
  }
  klc = $getDirKeyLocked(this$static);
  if (!klc) {
    $setPressTick(newKlc, tick);
    keyProcNew = new KeyProc(newKlc.cmd, newKlc.token.value_0);
    scp = new SingleCmdProcessor(tick, stampJavaTypeInfo(getClassLiteralForArray(Lorg_lee_mugen_input_KeyProc_2_classLit, 1), $intern_63, 40, 0, [keyProcNew]));
    $add_11(this$static.mugenKeyEvents, scp);
    return;
  }
   else {
    if (key != U && key != D && key != B && key != F_0)
    ;
    else {
      combinedKey = null;
      moveDirection = klc.key.bit | newKlc.key.bit;
      if (moveDirection == DB.bit)
        combinedKey = DB;
      else if (moveDirection == UB.bit)
        combinedKey = UB;
      else if (moveDirection == UF.bit)
        combinedKey = UF;
      else if (moveDirection == DF.bit)
        combinedKey = DF;
      else {
        if ($isCombinedDirKey(klc.key))
          return;
        $setPressTick(newKlc, tick);
        keyProcNew0 = new KeyProc(newKlc.cmd, newKlc.token.value_0);
        scp0 = new SingleCmdProcessor(tick, stampJavaTypeInfo(getClassLiteralForArray(Lorg_lee_mugen_input_KeyProc_2_classLit, 1), $intern_63, 40, 0, [keyProcNew0]));
        $add_11(this$static.mugenKeyEvents, scp0);
        return;
      }
      combinedKeyLock = castTo($get_11(this$static.keyLockFactory, combinedKey), 16);
      if (combinedKeyLock.token.value_0 == LOCK)
        return;
      $setPressTick(newKlc, tick);
      $setHidden(newKlc);
      $setHidden(klc);
      $setPressTick(combinedKeyLock, tick);
      keyProcKlc = new KeyProc(klc.cmd, klc.token.value_0);
      keyProcNew = new KeyProc(newKlc.cmd, newKlc.token.value_0);
      keyProcCombinedKeyLock = new KeyProc(combinedKeyLock.cmd, combinedKeyLock.token.value_0);
      scp = new SingleCmdProcessor(tick, stampJavaTypeInfo(getClassLiteralForArray(Lorg_lee_mugen_input_KeyProc_2_classLit, 1), $intern_63, 40, 0, [keyProcKlc, keyProcNew, keyProcCombinedKeyLock]));
      $add_11(this$static.mugenKeyEvents, scp);
    }
  }
}

function $release(this$static, keyCode, tick, isFlip){
  var key;
  tick = sub_1(tick, 1);
  tick = sub_1(tick, 1);
  $releaseDir(this$static, keyCode, tick);
  if (this$static.abc == keyCode) {
    $release(this$static, this$static.a, tick, isFlip);
    $release(this$static, this$static.b, tick, isFlip);
    $release(this$static, this$static.c, tick, isFlip);
    return true;
  }
  if (this$static.xyz == keyCode) {
    $release(this$static, this$static.x_0, tick, isFlip);
    $release(this$static, this$static.y_0, tick, isFlip);
    $release(this$static, this$static.z_0, tick, isFlip);
    return true;
  }
  key = castTo($get_8(this$static.keyCodeToKeyMap, valueOf_1(keyCode)), 33);
  if (!key)
    return true;
  (key.bit & (($clinit_Key() , U).bit | D.bit | B.bit | F_0.bit)) != key.bit && $releaseButton(this$static, key, tick);
  return true;
}

function $releaseButton(this$static, key, tick){
  var keyProcNew, newKlc, scp;
  if (this$static.diseable)
    return;
  if (!key)
    return;
  if ((key.bit & (($clinit_Key() , U).bit | D.bit | B.bit | F_0.bit)) == key.bit)
    return;
  newKlc = castTo($get_11(this$static.keyLockFactory, key), 16);
  if (newKlc.token.value_0 != ($clinit_KeyLockCommand$State() , LOCK) && newKlc.token.value_0 != HIDDEN)
    return;
  $setReleasedTick(newKlc, tick);
  keyProcNew = new KeyProc(newKlc.cmd, newKlc.token.value_0);
  scp = new SingleCmdProcessor(tick, stampJavaTypeInfo(getClassLiteralForArray(Lorg_lee_mugen_input_KeyProc_2_classLit, 1), $intern_63, 40, 0, [keyProcNew]));
  $add_11(this$static.mugenKeyEvents, scp);
}

function $releaseDir(this$static, keyCode, tick){
  var key;
  if (this$static.diseable)
    return;
  key = castTo($get_8(this$static.keyCodeToKeyMap, valueOf_1(keyCode)), 33);
  $releaseDir_0(this$static, key, tick);
}

function $releaseDir_0(this$static, key, tick){
  var k, k$iterator, keyProcKlc, keyProcNew, klc, lst, newKlc, scp, list, result;
  if (this$static.diseable)
    return;
  if (!key)
    return;
  if ((key.bit & (($clinit_Key() , U).bit | D.bit | B.bit | F_0.bit)) != key.bit)
    return;
  newKlc = castTo($get_11(this$static.keyLockFactory, key), 16);
  if (newKlc.token.value_0 != ($clinit_KeyLockCommand$State() , LOCK) && newKlc.token.value_0 != HIDDEN)
    return;
  klc = $getDirKeyLocked(this$static);
  if (!klc)
    return;
  if ($isCombinedDirKey(klc.key)) {
    $setReleasedTick(klc, tick);
    $unHidden(newKlc);
    $setReleasedTick(newKlc, tick);
    keyProcNew = new KeyProc(newKlc.cmd, newKlc.token.value_0);
    keyProcKlc = new KeyProc(klc.cmd, klc.token.value_0);
    lst = new ArrayList;
    push_1(lst.array, keyProcNew);
    push_1(lst.array, keyProcKlc);
    for (k$iterator = new ArrayList$1((list = new ArrayList , result = null , (result = castTo($get_11(this$static.keyLockFactory, B), 16)).token.value_0 == HIDDEN && (push_1(list.array, result) , true) , (result = castTo($get_11(this$static.keyLockFactory, DB), 16)).token.value_0 == HIDDEN && (push_1(list.array, result) , true) , (result = castTo($get_11(this$static.keyLockFactory, D), 16)).token.value_0 == HIDDEN && (push_1(list.array, result) , true) , (result = castTo($get_11(this$static.keyLockFactory, DF), 16)).token.value_0 == HIDDEN && (push_1(list.array, result) , true) , (result = castTo($get_11(this$static.keyLockFactory, F_0), 16)).token.value_0 == HIDDEN && (push_1(list.array, result) , true) , (result = castTo($get_11(this$static.keyLockFactory, UF), 16)).token.value_0 == HIDDEN && (push_1(list.array, result) , true) , (result = castTo($get_11(this$static.keyLockFactory, U), 16)).token.value_0 == HIDDEN && (push_1(list.array, result) , true) , (result = castTo($get_11(this$static.keyLockFactory, UB), 16)).token.value_0 == HIDDEN && (push_1(list.array, result) , true) , list)); k$iterator.i < k$iterator.this$01.array.length;) {
      k = castTo($next_7(k$iterator), 16);
      $unHidden(k);
      $add_8(lst, new KeyProc(k.cmd, k.token.value_0));
    }
    scp = new SingleCmdProcessor(tick, castTo($toArray_0(lst, initUnidimensionalArray(Lorg_lee_mugen_input_KeyProc_2_classLit, $intern_63, 40, lst.array.length, 0, 1)), 106));
    $add_11(this$static.mugenKeyEvents, scp);
  }
   else {
    $setReleasedTick(newKlc, tick);
    keyProcNew = new KeyProc(newKlc.cmd, newKlc.token.value_0);
    scp = new SingleCmdProcessor(tick, stampJavaTypeInfo(getClassLiteralForArray(Lorg_lee_mugen_input_KeyProc_2_classLit, 1), $intern_63, 40, 0, [keyProcNew]));
    $add_11(this$static.mugenKeyEvents, scp);
  }
}

function CmdProcDispatcher(up, down, back_0, forward_0, a, b, c, x_0, y_0, z_0, abc, xyz){
  $clinit_CmdProcDispatcher();
  this.mugenKeyEvents = ($clinit_Collections() , new LinkedList);
  this.up = up;
  this.down = down;
  this.back_0 = back_0;
  this.forward_0 = forward_0;
  this.a = a;
  this.b = b;
  this.c = c;
  this.x_0 = x_0;
  this.y_0 = y_0;
  this.z_0 = z_0;
  this.abc = abc;
  this.xyz = xyz;
  this.keyCodeToKeyMap = new HashMap;
  $put_4(this.keyCodeToKeyMap, valueOf_1(up), ($clinit_Key() , U));
  $put_4(this.keyCodeToKeyMap, valueOf_1(down), D);
  $put_4(this.keyCodeToKeyMap, valueOf_1(back_0), B);
  $put_4(this.keyCodeToKeyMap, valueOf_1(forward_0), F_0);
  this.keyLockFactory = new EnumMap(Lorg_lee_mugen_input_Key_2_classLit);
  $put_4(this.keyCodeToKeyMap, valueOf_1(a), a_0);
  $put_4(this.keyCodeToKeyMap, valueOf_1(b), b_0);
  $put_4(this.keyCodeToKeyMap, valueOf_1(c), c_0);
  $put_4(this.keyCodeToKeyMap, valueOf_1(x_0), x_1);
  $put_4(this.keyCodeToKeyMap, valueOf_1(y_0), y_1);
  $put_4(this.keyCodeToKeyMap, valueOf_1(z_0), z_1);
  $put_5(this.keyLockFactory, U, new KeyLockCommand(U));
  $put_5(this.keyLockFactory, D, new KeyLockCommand(D));
  $put_5(this.keyLockFactory, B, new KeyLockCommand(B));
  $put_5(this.keyLockFactory, F_0, new KeyLockCommand(F_0));
  $put_5(this.keyLockFactory, DB, new KeyLockCommand(DB));
  $put_5(this.keyLockFactory, UB, new KeyLockCommand(UB));
  $put_5(this.keyLockFactory, DF, new KeyLockCommand(DF));
  $put_5(this.keyLockFactory, UF, new KeyLockCommand(UF));
  $put_5(this.keyLockFactory, a_0, new KeyLockCommand(a_0));
  $put_5(this.keyLockFactory, b_0, new KeyLockCommand(b_0));
  $put_5(this.keyLockFactory, c_0, new KeyLockCommand(c_0));
  $put_5(this.keyLockFactory, x_1, new KeyLockCommand(x_1));
  $put_5(this.keyLockFactory, y_1, new KeyLockCommand(y_1));
  $put_5(this.keyLockFactory, z_1, new KeyLockCommand(z_1));
}

defineClass(77, 1, {77:1}, CmdProcDispatcher);
_.a = 0;
_.abc = 0;
_.b = 0;
_.back_0 = 0;
_.c = 0;
_.diseable = false;
_.down = 0;
_.forward_0 = 0;
_.keys_0 = null;
_.up = 0;
_.x_0 = 0;
_.xyz = 0;
_.y_0 = 0;
_.z_0 = 0;
var inverseMap, spriteDispatcherMap;
var Lorg_lee_mugen_input_CmdProcDispatcher_2_classLit = createForClass('org.lee.mugen.input', 'CmdProcDispatcher', 77);
function $compare_0(o1, o2){
  return toInt_0(sub_1(o1.pressTick, o2.pressTick));
}

function CmdProcDispatcher$1(){
}

defineClass(419, 1, {}, CmdProcDispatcher$1);
_.compare = function compare_7(o1, o2){
  return $compare_0(castTo(o1, 16), castTo(o2, 16));
}
;
_.equals_0 = function equals_37(other){
  return this === other;
}
;
var Lorg_lee_mugen_input_CmdProcDispatcher$1_2_classLit = createForClass('org.lee.mugen.input', 'CmdProcDispatcher/1', 419);
function $clinit_Key(){
  $clinit_Key = emptyMethod;
  B = new Key('B', 0, 'BACK', 1);
  D = new Key('D', 1, 'DOWN', 2);
  F_0 = new Key('F', 2, 'FORWARD', 4);
  U = new Key('U', 3, 'UP', 8);
  DB = new Key('DB', 4, 'BACK_DOWN', 3);
  DF = new Key('DF', 5, 'FORWARD_DOWN', 6);
  UF = new Key('UF', 6, 'FORWARD_UP', 12);
  UB = new Key('UB', 7, 'BACK_UP', 9);
  a_0 = new Key('a', 8, 'a', 16);
  b_0 = new Key('b', 9, 'b', 32);
  c_0 = new Key('c', 10, 'c', 64);
  x_1 = new Key('x', 11, 'x', 128);
  y_1 = new Key('y', 12, 'y', 256);
  z_1 = new Key('z', 13, 'z', 512);
}

function Key(enum$name, enum$ordinal, desc, bit){
  Enum.call(this, enum$name, enum$ordinal);
  this.bit = bit;
  this.desc = desc;
}

function getKey_2(bit){
  $clinit_Key();
  var k, k$array, k$index, k$max;
  for (k$array = values_16() , k$index = 0 , k$max = k$array.length; k$index < k$max; ++k$index) {
    k = k$array[k$index];
    if (bit == k.bit)
      return k;
  }
  return null;
}

function values_16(){
  $clinit_Key();
  return stampJavaTypeInfo(getClassLiteralForArray(Lorg_lee_mugen_input_Key_2_classLit, 1), $intern_55, 33, 0, [B, D, F_0, U, DB, DF, UF, UB, a_0, b_0, c_0, x_1, y_1, z_1]);
}

defineClass(33, 8, {3:1, 7:1, 8:1, 33:1}, Key);
_.bit = 0;
var B, D, DB, DF, F_0, U, UB, UF, a_0, b_0, c_0, x_1, y_1, z_1;
var Lorg_lee_mugen_input_Key_2_classLit = createForEnum('org.lee.mugen.input', 'Key', 33, values_16);
function $setHidden(this$static){
  if (this$static.token.value_0 != ($clinit_KeyLockCommand$State() , LOCK))
    return;
  $set_8(this$static.token, HIDDEN);
}

function $setPressTick(this$static, pressTick){
  $setTokenTaken(this$static, ($clinit_KeyLockCommand$State() , LOCK));
  this$static.pressTick = pressTick;
  this$static.isPress = true;
  this$static.cmd = new PressCommand(this$static.key.bit, pressTick);
}

function $setReleasedTick(this$static, releasedTick){
  var hold, pressTick;
  $setTokenTaken(this$static, ($clinit_KeyLockCommand$State() , NONE_0));
  this$static.isPress = false;
  this$static.releasedTick = releasedTick;
  pressTick = this$static.cmd.tick;
  hold = sub_1(releasedTick, pressTick);
  this$static.cmd = new ReleaseCommand(this$static.key.bit, releasedTick, hold);
}

function $setTokenTaken(this$static, state){
  $set_8(this$static.token, state);
}

function $unHidden(this$static){
  if (this$static.token.value_0 != ($clinit_KeyLockCommand$State() , HIDDEN))
    return;
  $set_8(this$static.token, LOCK);
}

function KeyLockCommand(key){
  this.token = new AtomicReference(($clinit_KeyLockCommand$State() , NONE_0));
  this.key = key;
}

defineClass(16, 1, {16:1}, KeyLockCommand);
_.toString_0 = function toString_57(){
  if (this.token.value_0 == ($clinit_KeyLockCommand$State() , NONE_0))
    return '';
  return this.key.desc + ' is ' + (this.isPress?'Pressed at ' + toString_29(this.pressTick):'Released at ' + toString_29(this.releasedTick));
}
;
_.isPress = false;
_.pressTick = 0;
_.releasedTick = 0;
var Lorg_lee_mugen_input_KeyLockCommand_2_classLit = createForClass('org.lee.mugen.input', 'KeyLockCommand', 16);
function $clinit_KeyLockCommand$State(){
  $clinit_KeyLockCommand$State = emptyMethod;
  NONE_0 = new KeyLockCommand$State('NONE', 0);
  LOCK = new KeyLockCommand$State('LOCK', 1);
  HIDDEN = new KeyLockCommand$State('HIDDEN', 2);
}

function KeyLockCommand$State(enum$name, enum$ordinal){
  Enum.call(this, enum$name, enum$ordinal);
}

function values_17(){
  $clinit_KeyLockCommand$State();
  return stampJavaTypeInfo(getClassLiteralForArray(Lorg_lee_mugen_input_KeyLockCommand$State_2_classLit, 1), $intern_1, 128, 0, [NONE_0, LOCK, HIDDEN]);
}

defineClass(128, 8, {3:1, 7:1, 8:1, 128:1}, KeyLockCommand$State);
var HIDDEN, LOCK, NONE_0;
var Lorg_lee_mugen_input_KeyLockCommand$State_2_classLit = createForEnum('org.lee.mugen.input', 'KeyLockCommand/State', 128, values_17);
function KeyProc(key, state){
  this.key = key;
  this.state_0 = state;
}

defineClass(40, 1, {40:1}, KeyProc);
_.toString_0 = function toString_58(){
  return this.key + ' ' + this.state_0;
}
;
var Lorg_lee_mugen_input_KeyProc_2_classLit = createForClass('org.lee.mugen.input', 'KeyProc', 40);
function $find_0(this$static, cpd, gameTime, isFlip){
  var aPosCmd, aPosSeq, posCmd, posSeq, seq;
  posCmd = 0;
  posSeq = 0;
  seq = $getSequence(cpd, gameTime, this$static._time);
  while (posCmd < this$static._cmds.length && posSeq < seq.length) {
    aPosCmd = new WrapInt(valueOf_1(posCmd));
    aPosSeq = new WrapInt(valueOf_1(posSeq));
    if ($isMatch(this$static._cmds, aPosCmd, seq, aPosSeq, this$static._time, isFlip, gameTime)) {
      posCmd = valueOf_1(aPosCmd.value_0).value_0;
      posSeq = valueOf_1(aPosSeq.value_0).value_0;
      if (posCmd > this$static._cmds.length - 1) {
        return true;
      }
    }
     else {
      ++posSeq;
    }
  }
  return false;
}

function $incerseKey(key, isFlip){
  var k, k0;
  if (isFlip) {
    k0 = getKey_2(key);
    if (k0) {
      k = castTo($get_8(($clinit_CmdProcDispatcher() , inverseMap), k0), 33);
      if (k) {
        return k.bit;
      }
    }
    return key;
  }
   else {
    return key;
  }
}

function $isMatch(cmds, aPosCmd, seqFromBuffer, aPosSeqFromBuffer, maxTimeToexec, isFlip, gameTime){
  var aKey, bufferedWhenEqual, i, isFirst, isIncSeq, keyFromCmd, keysFromBuffer, kp, kp$array, kp$index, kp$max, kpFromBuffer, kpFromBuffer$array, kpFromBuffer$index, kpFromBuffer$max, kpMenosOne, lastTimeKeyProcBuffer, posCmd, posSeq, rc, result, thisResult, timeDelta, typesFromCmd;
  result = false;
  isIncSeq = true;
  posCmd = valueOf_1(aPosCmd.value_0).value_0;
  posSeq = valueOf_1(aPosSeqFromBuffer.value_0).value_0;
  typesFromCmd = cmds[posCmd].types;
  keyFromCmd = cmds[posCmd].keys_0;
  keysFromBuffer = seqFromBuffer[posSeq].keys_0;
  if ((typesFromCmd & ($clinit_MugenSingleCmd$CommandType() , NO_OTHER_KEY_BEFORE).bit) == NO_OTHER_KEY_BEFORE.bit) {
    if (posCmd > 0 && posSeq > 0) {
      kpMenosOne = seqFromBuffer[posSeq - 1].keys_0;
      aKey = 0;
      for (kp$array = kpMenosOne , kp$index = 0 , kp$max = kp$array.length; kp$index < kp$max; ++kp$index) {
        kp = kp$array[kp$index];
        aKey |= kp.key.key;
      }
      if (aKey != $incerseKey(cmds[posCmd - 1].keys_0, isFlip)) {
        result = false;
        $setValue(aPosSeqFromBuffer, valueOf_1(seqFromBuffer.length));
        return false;
      }
    }
    aKey = 0;
    for (kp$array = keysFromBuffer , kp$index = 0 , kp$max = kp$array.length; kp$index < kp$max; ++kp$index) {
      kp = kp$array[kp$index];
      aKey |= kp.key.key;
    }
    if ($incerseKey(keyFromCmd, isFlip) != aKey) {
      result = false;
      $setValue(aPosSeqFromBuffer, valueOf_1(seqFromBuffer.length));
      return false;
    }
  }
  if ((typesFromCmd & HOLD.bit) == HOLD.bit) {
    if ((typesFromCmd & DIRECTION.bit) == DIRECTION.bit) {
      for (kp$array = keysFromBuffer , kp$index = 0 , kp$max = kp$array.length; kp$index < kp$max; ++kp$index) {
        kp = kp$array[kp$index];
        if (($incerseKey(keyFromCmd, isFlip) & kp.key.key) == $incerseKey(keyFromCmd, isFlip)) {
          result = true;
          break;
        }
      }
    }
     else {
      for (kp$array = keysFromBuffer , kp$index = 0 , kp$max = kp$array.length; kp$index < kp$max; ++kp$index) {
        kp = kp$array[kp$index];
        if ($incerseKey(keyFromCmd, isFlip) == kp.key.key) {
          result = true;
          break;
        }
      }
    }
  }
   else if ((typesFromCmd & RELEASED.bit) == RELEASED.bit) {
    if ((typesFromCmd & DIRECTION.bit) == DIRECTION.bit) {
      for (kp$array = keysFromBuffer , kp$index = 0 , kp$max = kp$array.length; kp$index < kp$max; ++kp$index) {
        kp = kp$array[kp$index];
        if (($incerseKey(keyFromCmd, isFlip) & kp.key.key) == $incerseKey(keyFromCmd, isFlip) && kp.key.kind == ($clinit_AbstractCommand$Kind() , RELEASE)) {
          rc = castTo(kp.key, 210);
          if (gte_0(rc.holdTick, cmds[posCmd].time)) {
            result = true;
            break;
          }
        }
        if (($incerseKey(keyFromCmd, isFlip) & kp.key.key) == $incerseKey(keyFromCmd, isFlip) && kp.state_0 == ($clinit_KeyLockCommand$State() , HIDDEN) && kp.key.kind == ($clinit_AbstractCommand$Kind() , PRESS)) {
          result = true;
          break;
        }
      }
    }
     else {
      for (kp$array = keysFromBuffer , kp$index = 0 , kp$max = kp$array.length; kp$index < kp$max; ++kp$index) {
        kp = kp$array[kp$index];
        if ($incerseKey(keyFromCmd, isFlip) == kp.key.key && kp.key.kind == ($clinit_AbstractCommand$Kind() , RELEASE)) {
          result = true;
          break;
        }
        if (($incerseKey(keyFromCmd, isFlip) & kp.key.key) == $incerseKey(keyFromCmd, isFlip) && kp.state_0 == ($clinit_KeyLockCommand$State() , HIDDEN) && kp.key.kind == ($clinit_AbstractCommand$Kind() , PRESS)) {
          isIncSeq = false;
          result = true;
          break;
        }
      }
    }
  }
   else if ((typesFromCmd & PRESS_0.bit) == PRESS_0.bit) {
    if ((typesFromCmd & DIRECTION.bit) == DIRECTION.bit) {
      for (kp$array = keysFromBuffer , kp$index = 0 , kp$max = kp$array.length; kp$index < kp$max; ++kp$index) {
        kp = kp$array[kp$index];
        if (($incerseKey(keyFromCmd, isFlip) & kp.key.key) == $incerseKey(keyFromCmd, isFlip) && kp.key.kind == ($clinit_AbstractCommand$Kind() , PRESS)) {
          timeDelta = toInt_0(abs_1(sub_1(kp.key.tick, gameTime)));
          if (timeDelta <= 3) {
            result = true;
            break;
          }
        }
      }
    }
     else {
      for (kpFromBuffer$array = keysFromBuffer , kpFromBuffer$index = 0 , kpFromBuffer$max = kpFromBuffer$array.length; kpFromBuffer$index < kpFromBuffer$max; ++kpFromBuffer$index) {
        kpFromBuffer = kpFromBuffer$array[kpFromBuffer$index];
        if ($incerseKey(keyFromCmd, isFlip) == kpFromBuffer.key.key && kpFromBuffer.state_0 != ($clinit_KeyLockCommand$State() , HIDDEN) && kpFromBuffer.key.kind == ($clinit_AbstractCommand$Kind() , PRESS) && lte(sub_1(gameTime, kpFromBuffer.key.tick), maxTimeToexec)) {
          result = true;
          break;
        }
      }
    }
  }
   else if ((typesFromCmd & SIMULTANEOUS.bit) == SIMULTANEOUS.bit) {
    isFirst = true;
    lastTimeKeyProcBuffer = 0;
    thisResult = false;
    bufferedWhenEqual = 0;
    for (i = posSeq; i < seqFromBuffer.length && !thisResult; ++i) {
      for (kpFromBuffer$array = seqFromBuffer[i].keys_0 , kpFromBuffer$index = 0 , kpFromBuffer$max = kpFromBuffer$array.length; kpFromBuffer$index < kpFromBuffer$max; ++kpFromBuffer$index) {
        kpFromBuffer = kpFromBuffer$array[kpFromBuffer$index];
        if (isFirst && (keyFromCmd & kpFromBuffer.key.key) == kpFromBuffer.key.key && kpFromBuffer.key.kind == ($clinit_AbstractCommand$Kind() , PRESS) && lte(sub_1(gameTime, kpFromBuffer.key.tick), maxTimeToexec)) {
          bufferedWhenEqual |= kpFromBuffer.key.key;
          isFirst = false;
          lastTimeKeyProcBuffer = kpFromBuffer.key.tick;
        }
         else if (!isFirst && (bufferedWhenEqual & kpFromBuffer.key.key) == 0 && lt(sub_1(kpFromBuffer.key.tick, lastTimeKeyProcBuffer), 5) && (keyFromCmd & kpFromBuffer.key.key) == kpFromBuffer.key.key && lte(sub_1(gameTime, kpFromBuffer.key.tick), add_2(maxTimeToexec, 5))) {
          bufferedWhenEqual |= kpFromBuffer.key.key;
          if ((bufferedWhenEqual & keyFromCmd) == bufferedWhenEqual) {
            thisResult = true;
            break;
          }
        }
         else if (!isFirst && gt(sub_1(kpFromBuffer.key.tick, lastTimeKeyProcBuffer), 5)) {
          break;
        }
      }
    }
    result = result || thisResult;
  }
  if (result) {
    ++posCmd;
    isIncSeq && ++posSeq;
  }
  $setValue(aPosCmd, valueOf_1(posCmd));
  $setValue(aPosSeqFromBuffer, valueOf_1(posSeq));
  return result;
}

function MugenCommands(cmds, cmdName, time){
  this._cmds = cmds;
  this._time = time;
  this._commandName = cmdName;
}

defineClass(209, 1, {3:1, 209:1}, MugenCommands);
_._time = 1;
var Lorg_lee_mugen_input_MugenCommands_2_classLit = createForClass('org.lee.mugen.input', 'MugenCommands', 209);
function $computeKeys(keys_0){
  var res, t, t$array, t$index, t$max;
  res = 0;
  for (t$array = keys_0 , t$index = 0 , t$max = t$array.length; t$index < t$max; ++t$index) {
    t = t$array[t$index];
    res |= t.bit;
  }
  return res;
}

function $computeTypes(types){
  var res, t, t$array, t$index, t$max;
  res = 0;
  for (t$array = types , t$index = 0 , t$max = t$array.length; t$index < t$max; ++t$index) {
    t = t$array[t$index];
    res |= t.bit;
  }
  return res;
}

function MugenSingleCmd(keys_0, types){
  this.time = 0;
  this.keys_0 = $computeKeys(keys_0);
  this.types = $computeTypes(types);
}

defineClass(120, 1, {3:1, 120:1}, MugenSingleCmd);
_.keys_0 = 0;
_.time = 0;
_.types = 0;
var Lorg_lee_mugen_input_MugenSingleCmd_2_classLit = createForClass('org.lee.mugen.input', 'MugenSingleCmd', 120);
function $clinit_MugenSingleCmd$CommandType(){
  $clinit_MugenSingleCmd$CommandType = emptyMethod;
  PRESS_0 = new MugenSingleCmd$CommandType('PRESS', 0, 1, '');
  HOLD = new MugenSingleCmd$CommandType('HOLD', 1, 2, '/');
  RELEASED = new MugenSingleCmd$CommandType('RELEASED', 2, 4, '~');
  DIRECTION = new MugenSingleCmd$CommandType('DIRECTION', 3, 8, '$');
  SIMULTANEOUS = new MugenSingleCmd$CommandType('SIMULTANEOUS', 4, 16, '+');
  NO_OTHER_KEY_BEFORE = new MugenSingleCmd$CommandType('NO_OTHER_KEY_BEFORE', 5, 32, '>');
}

function MugenSingleCmd$CommandType(enum$name, enum$ordinal, bit, desc){
  Enum.call(this, enum$name, enum$ordinal);
  this.bit = bit;
  this.desc = desc;
}

function values_18(){
  $clinit_MugenSingleCmd$CommandType();
  return stampJavaTypeInfo(getClassLiteralForArray(Lorg_lee_mugen_input_MugenSingleCmd$CommandType_2_classLit, 1), $intern_54, 69, 0, [PRESS_0, HOLD, RELEASED, DIRECTION, SIMULTANEOUS, NO_OTHER_KEY_BEFORE]);
}

defineClass(69, 8, {3:1, 7:1, 8:1, 69:1}, MugenSingleCmd$CommandType);
_.bit = 0;
var DIRECTION, HOLD, NO_OTHER_KEY_BEFORE, PRESS_0, RELEASED, SIMULTANEOUS;
var Lorg_lee_mugen_input_MugenSingleCmd$CommandType_2_classLit = createForEnum('org.lee.mugen.input', 'MugenSingleCmd/CommandType', 69, values_18);
function PressCommand(key, tick){
  AbstractCommand.call(this, key, tick, ($clinit_AbstractCommand$Kind() , PRESS));
}

defineClass(273, 272, {}, PressCommand);
var Lorg_lee_mugen_input_PressCommand_2_classLit = createForClass('org.lee.mugen.input', 'PressCommand', 273);
function ReleaseCommand(key, tick, holdTick){
  AbstractCommand.call(this, key, tick, ($clinit_AbstractCommand$Kind() , RELEASE));
  this.holdTick = holdTick;
}

defineClass(210, 272, {210:1}, ReleaseCommand);
_.toString_0 = function toString_59(){
  return this.kind + ' ' + this.key + ' @t ' + toString_29(this.tick) + ' hold : ' + toString_29(this.holdTick);
}
;
_.holdTick = 0;
var Lorg_lee_mugen_input_ReleaseCommand_2_classLit = createForClass('org.lee.mugen.input', 'ReleaseCommand', 210);
function SingleCmdProcessor(tick, keys_0){
  this.keys_0 = keys_0;
  this.tick = tick;
}

defineClass(70, 1, {70:1}, SingleCmdProcessor);
_.tick = 0;
var Lorg_lee_mugen_input_SingleCmdProcessor_2_classLit = createForClass('org.lee.mugen.input', 'SingleCmdProcessor', 70);
function checkChunkSize(size_0, fileLength, what){
  if (size_0 < 0 || size_0 > fileLength || size_0 > $intern_39) {
    throw toJs(new IOException('Invalid ' + what + ' length ' + size_0 + ' (file ' + fileLength + ' bytes)'));
  }
}

function checkRange(offset, size_0, fileLength, what){
  if (offset < 0 || size_0 < 0 || offset > fileLength || size_0 > fileLength - offset) {
    throw toJs(new IOException('Invalid ' + what + ' range offset=' + offset + ' size=' + size_0 + ' file=' + fileLength));
  }
  checkChunkSize(size_0, fileLength, what);
}

function copy_4(input_0, output){
  var buffer, count, n;
  buffer = initUnidimensionalArray(B_classLit, $intern_6, 5, 2048, 15, 1);
  count = 0;
  n = 0;
  while (-1 != (n = (checkCriticalNotNull(buffer) , input_0.read_0(buffer, 0, buffer.length)))) {
    $write_0(output, buffer, n);
    count += n;
  }
  return count;
}

function toByteArray(input_0){
  var out;
  out = new ByteArrayOutputStream;
  copy_4(input_0, out);
  return $toByteArray(out);
}

function $readChars(this$static, signature){
  var c, i;
  for (i = 0; i < signature.length; i++) {
    c = this$static.din.is.read();
    signature[i] = c & $intern_5;
  }
}

function $readInt(this$static){
  var i, res;
  res = initUnidimensionalArray(I_classLit, $intern_3, 5, 4, 15, 1);
  for (i = 3; i >= 0; i--)
    res[i] = this$static.din.is.read();
  return (res[0] & 255) << 24 | (res[1] & 255) << 16 | (res[2] & 255) << 8 | res[3] & 255;
}

function $readShort(this$static){
  var high, low;
  low = this$static.din.is.read();
  high = this$static.din.is.read();
  return (high << 8 | low & 255) << 16 >> 16;
}

function $readUnsignedShort(this$static){
  var high, low;
  low = this$static.din.is.read();
  high = this$static.din.is.read();
  return (high & 255) << 8 | low & 255;
}

function LittleEndianDataInputStream(in_0){
  FilterInputStream.call(this, in_0);
  this.din = new DataInputStream(in_0);
}

defineClass(117, 197, {}, LittleEndianDataInputStream);
var Lorg_lee_mugen_io_LittleEndianDataInputStream_2_classLit = createForClass('org.lee.mugen.io', 'LittleEndianDataInputStream', 117);
function install(utf8, binary){
  utf8ReaderOpener = utf8;
  binaryOpener = binary;
}

function openBinary(path){
  if (!binaryOpener) {
    throw toJs(new IOException('MugenDataStreams not installed (web/desktop must call install first)'));
  }
  return openBinary_0(path);
}

function openUtf8Reader(path){
  if (!utf8ReaderOpener) {
    throw toJs(new IOException('MugenDataStreams not installed (web/desktop must call install first)'));
  }
  return new InputStreamReader(openBinary_0(path));
}

var binaryOpener, utf8ReaderOpener;
function $setValue(this$static, o){
  this$static.value_0 = o.value_0;
}

function WrapInt(o){
  this.value_0 = o.value_0;
}

defineClass(271, 1, $intern_2, WrapInt);
_.value_0 = 0;
var Lorg_lee_mugen_lang_WrapInt_2_classLit = createForClass('org.lee.mugen.lang', 'WrapInt', 271);
function $setValue_0(this$static, o){
  this$static.spec = o;
}

function Wrapper(){
}

defineClass(153, 1, $intern_2, Wrapper);
var Lorg_lee_mugen_lang_Wrapper_2_classLit = createForClass('org.lee.mugen.lang', 'Wrapper', 153);
function $copy_5(this$static){
  return new Rectangle_1(this$static.x1, this$static.y1, this$static.x2, this$static.y2);
}

function $setX1(this$static, x1){
  this$static.x1 = x1;
}

function $setX2(this$static, x2){
  this$static.x2 = x2;
}

function $setY1(this$static, y1){
  this$static.y1 = y1;
}

function $setY2(this$static, y2){
  this$static.y2 = y2;
}

function Rectangle_0(){
}

function Rectangle_1(x1, y1, x2, y2){
  this.x1 = x1;
  this.y1 = y1;
  this.x2 = x2;
  this.y2 = y2;
}

defineClass(25, 1, {3:1, 25:1}, Rectangle_0, Rectangle_1);
_.equals_0 = function equals_38(obj){
  var r;
  if (obj == null)
    return false;
  if (!instanceOf(obj, 25))
    return false;
  r = castTo(obj, 25);
  return this.x1 == r.x1 && this.y1 == r.y1 && this.x2 == r.x2 && this.y2 == r.y2;
}
;
_.x1 = 0;
_.x2 = 0;
_.y1 = 0;
_.y2 = 0;
var Lorg_lee_mugen_object_Rectangle_2_classLit = createForClass('org.lee.mugen.object', 'Rectangle', 25);
defineClass(549, 1, $intern_2);
_.delayTick = 0;
_.grpNum = 0;
_.imgNum = 0;
_.xOffSet = 0;
_.yOffSet = 0;
var Lorg_lee_mugen_parser_air_BasicActionData_2_classLit = createForClass('org.lee.mugen.parser.air', 'BasicActionData', 549);
function AirData(){
  this.clsn1 = initUnidimensionalArray(Lorg_lee_mugen_object_Rectangle_2_classLit, $intern_9, 25, 0, 0, 1);
  this.clsn2 = initUnidimensionalArray(Lorg_lee_mugen_object_Rectangle_2_classLit, $intern_9, 25, 0, 0, 1);
}

defineClass(201, 549, {3:1, 201:1}, AirData);
_.isMirrorH = false;
_.isMirrorV = false;
var Lorg_lee_mugen_parser_air_AirData_2_classLit = createForClass('org.lee.mugen.parser.air', 'AirData', 201);
function $clinit_AirData$TypeBlit(){
  $clinit_AirData$TypeBlit = emptyMethod;
  ASD = new AirData$TypeBlit('ASD', 0);
  A = new AirData$TypeBlit('A', 1);
  S = new AirData$TypeBlit('S', 2);
}

function AirData$TypeBlit(enum$name, enum$ordinal){
  Enum.call(this, enum$name, enum$ordinal);
}

function values_19(){
  $clinit_AirData$TypeBlit();
  return stampJavaTypeInfo(getClassLiteralForArray(Lorg_lee_mugen_parser_air_AirData$TypeBlit_2_classLit, 1), $intern_1, 137, 0, [ASD, A, S]);
}

defineClass(137, 8, {3:1, 7:1, 8:1, 137:1}, AirData$TypeBlit);
var A, ASD, S;
var Lorg_lee_mugen_parser_air_AirData$TypeBlit_2_classLit = createForEnum('org.lee.mugen.parser.air', 'AirData/TypeBlit', 137, values_19);
function AirGroup(){
  this.airDataList = new ArrayList;
}

defineClass(194, 1, {3:1, 194:1}, AirGroup);
_.action = 0;
_.loopStart = -1;
var Lorg_lee_mugen_parser_air_AirGroup_2_classLit = createForClass('org.lee.mugen.parser.air', 'AirGroup', 194);
function $clinit_AirParser(){
  $clinit_AirParser = emptyMethod;
  _AIR_DATA_PATTERN = new Pattern($toLowerCase('((?:\\+|-)?(?:(?:\\.\\d+)|(?:\\d+(?:\\.\\d*)?))) *, *((?:\\+|-)?(?:(?:\\.\\d+)|(?:\\d+(?:\\.\\d*)?))) *, *((?:\\+|-)?(?:(?:\\.\\d+)|(?:\\d+(?:\\.\\d*)?))) *, *((?:\\+|-)?(?:(?:\\.\\d+)|(?:\\d+(?:\\.\\d*)?))) *, *((?:\\+|-)?(?:(?:\\.\\d+)|(?:\\d+(?:\\.\\d*)?)))(?: *, *((?:h?v?)|(?:v?h?)))?(?: *, *(\\w*)?)?,*.*', ($clinit_Locale() , ROOT_0)));
  grpActionPattern = new Pattern($toLowerCase(' *\\[ *begin action *(\\d*) *\\](?:(?: *;.*$)|(?: *$))', ROOT_0));
  P_COMMENT_OR_EMPTY_REGEX = new Pattern($toLowerCase('^;.*$|^ *$', ROOT_0));
  P_CLSN1DEFAULT_REGEX = new Pattern($toLowerCase(' *clsn1default *: *(\\d+)(?:(?: *;.*$)|(?: *$))', ROOT_0));
  P_CLSN2DEFAULT_REGEX = new Pattern($toLowerCase(' *clsn2default *: *(\\d+)(?:(?: *;.*$)|(?: *$))', ROOT_0));
  P_CLSN1_REGEX = new Pattern($toLowerCase(' *clsn1 *: *(\\d+)(?:(?: *;.*$)|(?: *$))', ROOT_0));
  P_CLSN2_REGEX = new Pattern($toLowerCase(' *clsn2 *: *(\\d+)(?:(?: *;.*$)|(?: *$))', ROOT_0));
  P_CLSN1_RECT_REGEX = new Pattern($toLowerCase(' *clsn[0-9].* *\\[ *(\\d+) *\\] *= *((?:\\+|-)?(?:(?:\\.\\d+)|(?:\\d+(?:\\.\\d*)?))) *, *((?:\\+|-)?(?:(?:\\.\\d+)|(?:\\d+(?:\\.\\d*)?))) *, *((?:\\+|-)?(?:(?:\\.\\d+)|(?:\\d+(?:\\.\\d*)?))) *, *((?:\\+|-)?(?:(?:\\.\\d+)|(?:\\d+(?:\\.\\d*)?))).*(?:(?: *;.*$)|(?: *$))', ROOT_0));
  P_CLSN2_RECT_REGEX = new Pattern($toLowerCase(' *clsn[0-9].* *\\[ *(\\d+) *\\] *= *((?:\\+|-)?(?:(?:\\.\\d+)|(?:\\d+(?:\\.\\d*)?))) *, *((?:\\+|-)?(?:(?:\\.\\d+)|(?:\\d+(?:\\.\\d*)?))) *, *((?:\\+|-)?(?:(?:\\.\\d+)|(?:\\d+(?:\\.\\d*)?))) *, *((?:\\+|-)?(?:(?:\\.\\d+)|(?:\\d+(?:\\.\\d*)?))).*(?:(?: *;.*$)|(?: *$))', ROOT_0));
  P_LOOP_START_REGEX = new Pattern($toLowerCase(' *(loopstart)(?:(?: *;.*$)|(?: *$))', ROOT_0));
  P_A = new Pattern($toLowerCase('.*a$', ROOT_0));
  P_AS09d09 = new Pattern($toLowerCase('.*as([0-9]+)d([0-9]+).*', ROOT_0));
  P_A09 = new Pattern($toLowerCase('.*a([0-9]+).*', ROOT_0));
  P_S09 = new Pattern($toLowerCase('.*s([0-9]+).*', ROOT_0));
  P_S = new Pattern($toLowerCase('.*s$', ROOT_0));
}

function $$init_0(this$static){
  this$static.airGroupMap = new HashMap;
}

function $getLine(br){
  var line;
  line = null;
  if (!$hasMoreTokens(br))
    return null;
  line = $nextToken(br);
  line = line == null?null:line.toLowerCase();
  return line;
}

function $isArrayEmpty(array){
  return array == null || array.length == 0;
}

function $parseClsn(line, strToken, clsnWrap, clsnHeadPattern, clsnRectPattern){
  var clsn, e, i, jr, matcher, rectCount, x1, x2, xTopLeft, xTopRight, y1, y2, yTopLeft, yTopRight;
  line = $trim(line.toLowerCase());
  matcher = new Matcher(clsnHeadPattern, line);
  if (line == null || !$test(matcher.regExp, matcher.input_0)) {
    return;
  }
  rectCount = __parseAndValidateInt(matcher.matchResult[1], 10);
  if (rectCount < 0 || rectCount > 256) {
    throw toJs(new IOException('Invalid clsn count ' + rectCount));
  }
  clsn = initUnidimensionalArray(Lorg_lee_mugen_object_Rectangle_2_classLit, $intern_9, 25, rectCount, 0, 1);
  for (i = 0; i < clsn.length; i++) {
    line = $getLine(strToken);
    line = $trim(line.toLowerCase());
    matcher = new Matcher(clsnRectPattern, line);
    $test(matcher.regExp, matcher.input_0);
    try {
      __parseAndValidateInt(matcher.matchResult[1], 10);
    }
     catch ($e0) {
      $e0 = toJava($e0);
      if (instanceOf($e0, 6)) {
        e = $e0;
        $printStackTraceImpl(e, ($clinit_System() , err_0), '');
      }
       else 
        throw toJs($e0);
    }
    x1 = __parseAndValidateInt(matcher.matchResult[2], 10);
    y1 = __parseAndValidateInt(matcher.matchResult[3], 10);
    x2 = __parseAndValidateInt(matcher.matchResult[4], 10);
    y2 = __parseAndValidateInt(matcher.matchResult[5], 10);
    xTopLeft = $wnd.Math.min(x1, x2);
    yTopLeft = $wnd.Math.min(y1, y2);
    xTopRight = $wnd.Math.max(x1, x2);
    yTopRight = $wnd.Math.max(y1, y2);
    $wnd.Math.abs(xTopLeft) + $wnd.Math.abs(xTopRight);
    $wnd.Math.abs(yTopLeft - yTopRight);
    jr = new Rectangle_1(x1, y1, x2, y2);
    clsn[i] = jr;
  }
  clsnWrap.spec = clsn;
}

function $parseGroup(this$static, grp){
  var actionId, airGrp, clsn1InOut, clsn1defaultInOut, clsn2InOut, clsn2defaultInOut, line, matcher, strToken;
  strToken = new StringTokenizer(grp);
  line = $nextToken(strToken);
  airGrp = new AirGroup;
  matcher = new Matcher(grpActionPattern, line);
  if (!$test(matcher.regExp, matcher.input_0)) {
    return false;
  }
  actionId = matcher.matchResult[1];
  airGrp.action = __parseAndValidateInt(actionId, 10);
  if ($containsKey(this$static.airGroupMap, valueOf_1(airGrp.action)))
    return false;
  $put_4(this$static.airGroupMap, valueOf_1(airGrp.action), airGrp);
  clsn1defaultInOut = new Wrapper;
  $setValue_0(clsn1defaultInOut, initUnidimensionalArray(Lorg_lee_mugen_object_Rectangle_2_classLit, $intern_9, 25, 0, 0, 1));
  clsn2defaultInOut = new Wrapper;
  $setValue_0(clsn2defaultInOut, initUnidimensionalArray(Lorg_lee_mugen_object_Rectangle_2_classLit, $intern_9, 25, 0, 0, 1));
  clsn1InOut = new Wrapper;
  clsn2InOut = new Wrapper;
  do {
    line = $getLine(strToken);
    if (line == null)
      break;
    if ($matches(new Matcher(P_COMMENT_OR_EMPTY_REGEX, line)))
    ;
    else if ($matches(new Matcher(P_CLSN1DEFAULT_REGEX, line))) {
      $setValue_0(clsn1InOut, initUnidimensionalArray(Lorg_lee_mugen_object_Rectangle_2_classLit, $intern_9, 25, 0, 0, 1));
      $parseClsn(line, strToken, clsn1defaultInOut, P_CLSN1DEFAULT_REGEX, P_CLSN1_RECT_REGEX);
    }
     else if ($matches(new Matcher(P_CLSN2DEFAULT_REGEX, line))) {
      $setValue_0(clsn2InOut, initUnidimensionalArray(Lorg_lee_mugen_object_Rectangle_2_classLit, $intern_9, 25, 0, 0, 1));
      $parseClsn(line, strToken, clsn2defaultInOut, P_CLSN2DEFAULT_REGEX, P_CLSN2_RECT_REGEX);
    }
     else if ($matches(new Matcher(P_CLSN1_REGEX, line))) {
      $setValue_0(clsn1InOut, initUnidimensionalArray(Lorg_lee_mugen_object_Rectangle_2_classLit, $intern_9, 25, 0, 0, 1));
      $parseClsn(line, strToken, clsn1InOut, P_CLSN1_REGEX, P_CLSN1_RECT_REGEX);
    }
     else if ($matches(new Matcher(P_CLSN2_REGEX, line))) {
      $setValue_0(clsn2InOut, initUnidimensionalArray(Lorg_lee_mugen_object_Rectangle_2_classLit, $intern_9, 25, 0, 0, 1));
      $parseClsn(line, strToken, clsn2InOut, P_CLSN2_REGEX, P_CLSN2_RECT_REGEX);
    }
     else if ($matches(new Matcher(P_CLSN1_RECT_REGEX, line))) {
      $parseClsn(line, strToken, clsn1InOut, P_CLSN1_REGEX, P_CLSN1_RECT_REGEX);
    }
     else if ($matches(new Matcher(P_CLSN2_RECT_REGEX, line))) {
      $parseClsn(line, strToken, clsn2InOut, P_CLSN2_REGEX, P_CLSN2_RECT_REGEX);
    }
     else if ($matches(new Matcher(P_LOOP_START_REGEX, line))) {
      parseLoopStart(line, airGrp);
    }
     else if ($find(new Matcher(_AIR_DATA_PATTERN, line))) {
      parseAirData(line, airGrp, $isArrayEmpty(clsn1InOut.spec)?clsn1defaultInOut:clsn1InOut, $isArrayEmpty(clsn2InOut.spec)?clsn2defaultInOut:clsn2InOut);
      $setValue_0(clsn1InOut, initUnidimensionalArray(Lorg_lee_mugen_object_Rectangle_2_classLit, $intern_9, 25, 0, 0, 1));
      $setValue_0(clsn2InOut, initUnidimensionalArray(Lorg_lee_mugen_object_Rectangle_2_classLit, $intern_9, 25, 0, 0, 1));
    }
     else {
      break;
    }
  }
   while ($hasMoreTokens(strToken));
  return true;
}

function AirParser(){
  $clinit_AirParser();
  $$init_0(this);
}

function AirParser_0(sFilename){
  $clinit_AirParser();
  var in_0;
  AirParser_1.call(this, (in_0 = openBinary(sFilename) , getGroupText(getText(in_0))));
}

function AirParser_1(groups){
  var grp, grp$array, grp$index, grp$max;
  $$init_0(this);
  for (grp$array = groups , grp$index = 0 , grp$max = grp$array.length; grp$index < grp$max; ++grp$index) {
    grp = grp$array[grp$index];
    $parseGroup(this, grp);
  }
}

function parseAirData(line, aGrp, clsn1, clsn2){
  var aPattern, adPattern, airData, asdPattern, matcher, mirror, sPattern, sdPattern;
  line = $replaceAll(line == null?null:line.toLowerCase(), ' ', '');
  if (line == null || !$find(new Matcher(_AIR_DATA_PATTERN, line)))
    return false;
  matcher = new Matcher(_AIR_DATA_PATTERN, line);
  $test(matcher.regExp, matcher.input_0);
  airData = new AirData;
  airData.grpNum = __parseAndValidateInt(matcher.matchResult[1], 10);
  airData.imgNum = __parseAndValidateInt(matcher.matchResult[2], 10);
  airData.xOffSet = __parseAndValidateInt(matcher.matchResult[3], 10);
  airData.yOffSet = __parseAndValidateInt(matcher.matchResult[4], 10);
  airData.delayTick = __parseAndValidateInt(matcher.matchResult[5], 10);
  if (matcher.matchResult[6] != null) {
    mirror = matcher.matchResult[6];
    airData.isMirrorH = mirror.indexOf('h') != -1;
    airData.isMirrorV = mirror.indexOf('v') != -1;
  }
  airData.clsn1 = clsn1.spec;
  airData.clsn2 = clsn2.spec;
  $add_8(aGrp.airDataList, airData);
  adPattern = new Matcher(P_A, line);
  asdPattern = new Matcher(P_AS09d09, line);
  aPattern = new Matcher(P_A09, line);
  sPattern = new Matcher(P_S09, line);
  sdPattern = new Matcher(P_S, line);
  if ($test(adPattern.regExp, adPattern.input_0) || $test(sdPattern.regExp, sdPattern.input_0)) {
    airData.type_0 = ($clinit_AirData$TypeBlit() , ASD);
    valueOf_1(256);
    valueOf_1(256);
    valueOf_1(256);
  }
   else if ($test(asdPattern.regExp, asdPattern.input_0)) {
    airData.type_0 = ($clinit_AirData$TypeBlit() , ASD);
    valueOf_1(__parseAndValidateInt(asdPattern.matchResult[1], 10));
    valueOf_1(__parseAndValidateInt(asdPattern.matchResult[1], 10));
    valueOf_1(__parseAndValidateInt(asdPattern.matchResult[2], 10));
  }
   else if ($test(aPattern.regExp, aPattern.input_0)) {
    airData.type_0 = ($clinit_AirData$TypeBlit() , A);
    valueOf_1(__parseAndValidateInt(aPattern.matchResult[1], 10) * 256);
    valueOf_1(__parseAndValidateInt(aPattern.matchResult[1], 10) * 256);
    valueOf_1(128);
  }
   else if ($test(sPattern.regExp, sPattern.input_0)) {
    airData.type_0 = ($clinit_AirData$TypeBlit() , S);
    valueOf_1(__parseAndValidateInt(sPattern.matchResult[1], 10));
  }
  return true;
}

function parseLoopStart(line, aGrp){
  line = line == null?null:line.toLowerCase();
  if (!$matches(new Matcher(P_LOOP_START_REGEX, line)))
    return false;
  aGrp.loopStart = aGrp.airDataList.array.length;
  return true;
}

defineClass(190, 1, {}, AirParser, AirParser_0);
var P_A, P_A09, P_AS09d09, P_CLSN1DEFAULT_REGEX, P_CLSN1_RECT_REGEX, P_CLSN1_REGEX, P_CLSN2DEFAULT_REGEX, P_CLSN2_RECT_REGEX, P_CLSN2_REGEX, P_COMMENT_OR_EMPTY_REGEX, P_LOOP_START_REGEX, P_S, P_S09, _AIR_DATA_PATTERN, grpActionPattern;
var Lorg_lee_mugen_parser_air_AirParser_2_classLit = createForClass('org.lee.mugen.parser.air', 'AirParser', 190);
var Lorg_lee_mugen_parser_type_Valueable_2_classLit = createForInterface('org.lee.mugen.parser.type', 'Valueable');
function FloatValueable(value_0){
  this.value_0 = value_0;
}

defineClass(195, 1, $intern_65, FloatValueable);
_.getValue_0 = function getValue_3(spriteId, params){
  return new Float(this.value_0);
}
;
_.value_0 = 0;
var Lorg_lee_mugen_parser_type_FloatValueable_2_classLit = createForClass('org.lee.mugen.parser.type', 'FloatValueable', 195);
function $clinit_IntValueable(){
  $clinit_IntValueable = emptyMethod;
  Zero = new IntValueable;
}

function IntValueable(){
  this.value_0 = 0;
}

defineClass(476, 1, $intern_65, IntValueable);
_.getValue_0 = function getValue_4(spriteId, params){
  return valueOf_1(this.value_0);
}
;
_.value_0 = 0;
var Zero;
var Lorg_lee_mugen_parser_type_IntValueable_2_classLit = createForClass('org.lee.mugen.parser.type', 'IntValueable', 476);
function StringValueable(value_0){
  this.value_0 = value_0;
}

defineClass(468, 1, $intern_65, StringValueable);
_.getValue_0 = function getValue_5(spriteId, params){
  return this.value_0;
}
;
var Lorg_lee_mugen_parser_type_StringValueable_2_classLit = createForClass('org.lee.mugen.parser.type', 'StringValueable', 468);
function ImageContainer(img, width_0, height){
  this.img = img;
  this.width_0 = width_0;
  this.height_0 = height;
}

defineClass(76, 1, $intern_66);
_.getImg = function getImg(){
  return this.img;
}
;
_.height_0 = 0;
_.width_0 = 0;
var Lorg_lee_mugen_renderer_ImageContainer_2_classLit = createForClass('org.lee.mugen.renderer', 'ImageContainer', 76);
function DeferedImageLoader(img, width_0, height){
  ImageContainer.call(this, img, width_0, height);
}

defineClass(477, 76, $intern_66, DeferedImageLoader);
_.getImg = function getImg_0(){
  return this.img;
}
;
var Lorg_lee_mugen_renderer_DeferedImageLoader_2_classLit = createForClass('org.lee.mugen.renderer', 'DeferedImageLoader', 477);
function $$init_1(this$static){
  this$static.trans = ($clinit_Trans() , NONE_1);
}

function $getXLeftSrc(this$static){
  if (this$static.isFlipH) {
    return this$static.ic.width_0;
  }
  return this$static.xLeftSrc;
}

function $getXRightSrc(this$static){
  if (this$static.isFlipH) {
    return 0;
  }
  return this$static.xRightSrc;
}

function $getYBottomSrc(this$static){
  if (this$static.isFlipV) {
    return 0;
  }
  return this$static.yBottomSrc;
}

function $getYTopSrc(this$static){
  if (this$static.isFlipV) {
    return this$static.ic.height_0;
  }
  return this$static.yTopSrc;
}

function $setAlpha_0(this$static, alpha_0){
  this$static.alpha_0 = alpha_0;
}

function $setXLeftDst(this$static, leftDst){
  this$static.xLeftDst = leftDst;
}

function $setXLeftSrc(this$static, leftSrc){
  this$static.xLeftSrc = leftSrc;
}

function $setXRightDst(this$static, rightDst){
  this$static.xRightDst = rightDst;
}

function $setXRightSrc(this$static, rightSrc){
  this$static.xRightSrc = rightSrc;
}

function $setXScaleFactor(this$static, scaleFactor){
  this$static.xScaleFactor = scaleFactor;
}

function $setYScaleFactor(this$static, scaleFactor){
  this$static.yScaleFactor = scaleFactor;
}

function DrawProperties(xLeftDst, xRightDst, yTopDst, yBottomDst, xLeftSrc, xRightSrc, yTopSrc, yBottomSrc, ic){
  $$init_1(this);
  this.xLeftDst = xLeftDst;
  this.yTopDst = yTopDst;
  this.xRightDst = xRightDst;
  this.yBottomDst = yBottomDst;
  this.xLeftSrc = xLeftSrc;
  this.xRightSrc = xRightSrc;
  this.yTopSrc = yTopSrc;
  this.yBottomSrc = yBottomSrc;
  this.isFlipH = false;
  this.isFlipV = false;
  this.ic = ic;
}

function DrawProperties_0(x_0, y_0, isFlipH, isFlipV, ic){
  $$init_1(this);
  this.xLeftDst = x_0;
  this.yTopDst = y_0;
  this.xRightDst = x_0 + ic.width_0;
  this.yBottomDst = y_0 + ic.height_0;
  this.xLeftSrc = 0;
  this.xRightSrc = ic.width_0;
  this.yTopSrc = 0;
  this.yBottomSrc = ic.height_0;
  this.isFlipH = isFlipH;
  this.isFlipV = isFlipV;
  this.ic = ic;
}

defineClass(48, 1, {}, DrawProperties, DrawProperties_0);
_.alpha_0 = 1;
_.isFlipH = false;
_.isFlipV = false;
_.xLeftDst = 0;
_.xLeftSrc = 0;
_.xRightDst = 0;
_.xRightSrc = 0;
_.xScaleFactor = 1;
_.yBottomDst = 0;
_.yBottomSrc = 0;
_.yScaleFactor = 1;
_.yTopDst = 0;
_.yTopSrc = 0;
var Lorg_lee_mugen_renderer_DrawProperties_2_classLit = createForClass('org.lee.mugen.renderer', 'DrawProperties', 48);
function getInstance_0(){
  if (!instanceOfMugenDrawer) {
    throw toJs(new RuntimeException_0('Not initialize'));
  }
  return instanceOfMugenDrawer;
}

function installDrawer(drawer){
  if (!drawer) {
    throw toJs(new IllegalArgumentException_0('drawer'));
  }
  instanceOfMugenDrawer = drawer;
}

var instanceOfMugenDrawer;
function $drawRect(this$static, x_0, y_0, width_0, height){
  if (width_0 < 0 || height < 0) {
    return;
  }
  if (height == 0 || width_0 == 0) {
    $drawLine(this$static, x_0, y_0, x_0 + width_0, y_0 + height);
  }
   else {
    $drawLine(this$static, x_0, y_0, x_0 + width_0 - 1, y_0);
    $drawLine(this$static, x_0 + width_0, y_0, x_0 + width_0, y_0 + height - 1);
    $drawLine(this$static, x_0 + width_0, y_0 + height, x_0 + 1, y_0 + height);
    $drawLine(this$static, x_0, y_0 + height, x_0, y_0 + 1);
  }
}

defineClass(537, 1, {});
var Lorg_lee_mugen_renderer_MugenDrawer_2_classLit = createForClass('org.lee.mugen.renderer', 'MugenDrawer', 537);
function $setMul(this$static, mul){
  this$static.mul = mul;
}

function PalFxSub(){
  this.mul = new RGB_1(256, 256, 256, 256);
  new RGB_1(0, 0, 0, 256);
}

defineClass(256, 1, $intern_2, PalFxSub);
var Lorg_lee_mugen_renderer_PalFxSub_2_classLit = createForClass('org.lee.mugen.renderer', 'PalFxSub', 256);
function $$init_2(this$static){
}

function $setA(this$static, a){
  this$static.a = a;
}

function $setB(this$static, b){
  this$static.b = b;
}

function $setG(this$static, g){
  this$static.g = g;
}

function $setR(this$static, r){
  this$static.r = r;
}

function RGB(){
  $$init_2(this);
}

function RGB_0(){
  RGB_1.call(this, 1, 1, 1, 255);
}

function RGB_1(r, g, b, a){
  $$init_2(this);
  this.r = r;
  this.g = g;
  this.b = b;
  this.a = a;
}

defineClass(65, 1, {3:1, 65:1}, RGB, RGB_0, RGB_1);
_.toString_0 = function toString_60(){
  return 'r=' + this.r + ';b=' + this.b + ';g=' + this.g + ';a=' + this.a;
}
;
_.a = 255;
_.b = 255;
_.g = 255;
_.r = 255;
var Lorg_lee_mugen_renderer_RGB_2_classLit = createForClass('org.lee.mugen.renderer', 'RGB', 65);
function $clinit_Trans(){
  $clinit_Trans = emptyMethod;
  NONE_1 = new Trans('NONE', 0);
  ADD = new Trans('ADD', 1);
  ADD1 = new Trans('ADD1', 2);
  SUB = new Trans('SUB', 3);
  ADDALPHA = new Trans('ADDALPHA', 4);
}

function Trans(enum$name, enum$ordinal){
  Enum.call(this, enum$name, enum$ordinal);
}

function valueOf_6(name_0){
  $clinit_Trans();
  return valueOf(($clinit_Trans$Map() , $MAP_0), name_0);
}

function values_20(){
  $clinit_Trans();
  return stampJavaTypeInfo(getClassLiteralForArray(Lorg_lee_mugen_renderer_Trans_2_classLit, 1), $intern_1, 92, 0, [NONE_1, ADD, ADD1, SUB, ADDALPHA]);
}

defineClass(92, 8, {3:1, 7:1, 8:1, 92:1}, Trans);
var ADD, ADD1, ADDALPHA, NONE_1, SUB;
var Lorg_lee_mugen_renderer_Trans_2_classLit = createForEnum('org.lee.mugen.renderer', 'Trans', 92, values_20);
function $clinit_Trans$Map(){
  $clinit_Trans$Map = emptyMethod;
  $MAP_0 = createValueOfMap(($clinit_Trans() , stampJavaTypeInfo(getClassLiteralForArray(Lorg_lee_mugen_renderer_Trans_2_classLit, 1), $intern_1, 92, 0, [NONE_1, ADD, ADD1, SUB, ADDALPHA])));
}

var $MAP_0;
function $computeClipScreenBounds(this$static, minWx, minWy, maxWx, maxWy){
  var base, corners, maxPx, maxPy, minPx, minPy, p, p$array, p$index, p$max, px, py, vp, vph, vpw, vpx, vpy;
  vp = this$static.renderContext.viewport_0;
  base = this$static.renderContext.worldProjectionSnapshot;
  vpx = vp.screenX_0;
  vpy = vp.screenY_0;
  vpw = vp.screenWidth;
  vph = vp.screenHeight;
  minPx = $intern_43;
  maxPx = $intern_44;
  minPy = $intern_43;
  maxPy = $intern_44;
  corners = stampJavaTypeInfo(getClassLiteralForArray(F_classLit, 2), $intern_9, 35, 0, [stampJavaTypeInfo(getClassLiteralForArray(F_classLit, 1), $intern_10, 5, 15, [minWx, minWy]), stampJavaTypeInfo(getClassLiteralForArray(F_classLit, 1), $intern_10, 5, 15, [maxWx, minWy]), stampJavaTypeInfo(getClassLiteralForArray(F_classLit, 1), $intern_10, 5, 15, [maxWx, maxWy]), stampJavaTypeInfo(getClassLiteralForArray(F_classLit, 1), $intern_10, 5, 15, [minWx, maxWy])]);
  for (p$array = corners , p$index = 0 , p$max = p$array.length; p$index < p$max; ++p$index) {
    p = p$array[p$index];
    $set_5(this$static.clipProjTmp, p[0], p[1], 0);
    $prj(this$static.clipProjTmp, base);
    px = vpw * (this$static.clipProjTmp.x_0 + 1) / 2 + vpx;
    py = vph * (this$static.clipProjTmp.y_0 + 1) / 2 + vpy;
    minPx = $wnd.Math.min(minPx, px);
    maxPx = $wnd.Math.max(maxPx, px);
    minPy = $wnd.Math.min(minPy, py);
    maxPy = $wnd.Math.max(maxPy, py);
  }
  this$static.tmpClipScreen.x_0 = minPx;
  this$static.tmpClipScreen.y_0 = minPy;
  this$static.tmpClipScreen.width_0 = maxPx - minPx;
  this$static.tmpClipScreen.height_0 = maxPy - minPy;
}

function $draw_1(this$static, drawProperties){
  var batch, height, imageContainer, mulA, palfx, prevDst, prevSrc, region, restoreBlend, sprite, srcHeight, srcWidth, srcX, srcY, sx, sy, texture, th, trans, tw, width_0, x_0, y_0;
  imageContainer = drawProperties.ic;
  if (!imageContainer || imageContainer.getImg() == null) {
    return;
  }
  texture = $resolveTextureForDraw(this$static, imageContainer);
  if (!texture) {
    return;
  }
  batch = (!this$static.batch && !!this$static.renderContext && (this$static.batch = this$static.renderContext.batch) , this$static.batch);
  if (!batch) {
    return;
  }
  $setColor_0(batch, this$static.currentColor);
  trans = drawProperties.trans;
  restoreBlend = false;
  prevSrc = 770;
  prevDst = 771;
  if (trans == ($clinit_Trans() , SUB)) {
    $flush(batch);
    prevSrc = batch.blendSrcFunc;
    prevDst = batch.blendDstFunc;
    $setBlendFunctionSeparate(batch, 770, 1, 770, 1);
    restoreBlend = true;
  }
   else if (trans == ADD || trans == ADDALPHA || trans == ADD1) {
    $flush(batch);
    prevSrc = batch.blendSrcFunc;
    prevDst = batch.blendDstFunc;
    $setBlendFunctionSeparate(batch, 770, 1, 770, 1);
    restoreBlend = true;
  }
  x_0 = $wnd.Math.min(drawProperties.xLeftDst, drawProperties.xRightDst);
  y_0 = $wnd.Math.min(drawProperties.yTopDst, drawProperties.yBottomDst);
  width_0 = $wnd.Math.abs(drawProperties.xRightDst - drawProperties.xLeftDst);
  height = $wnd.Math.abs(drawProperties.yBottomDst - drawProperties.yTopDst);
  sx = drawProperties.xScaleFactor;
  sy = drawProperties.yScaleFactor;
  srcX = $wnd.Math.min(round_int($getXLeftSrc(drawProperties)), round_int($getXRightSrc(drawProperties)));
  srcY = $wnd.Math.min(round_int($getYTopSrc(drawProperties)), round_int($getYBottomSrc(drawProperties)));
  srcWidth = $wnd.Math.abs(round_int($getXRightSrc(drawProperties) - $getXLeftSrc(drawProperties)));
  srcHeight = $wnd.Math.abs(round_int($getYBottomSrc(drawProperties) - $getYTopSrc(drawProperties)));
  if (srcWidth == 0 || srcHeight == 0) {
    return;
  }
  tw = texture.data_0.pixmap.width_0;
  th = texture.data_0.pixmap.height_0;
  if (srcX >= tw || srcY >= th) {
    return;
  }
  srcX + srcWidth > tw && (srcWidth = tw - srcX);
  srcY + srcHeight > th && (srcHeight = th - srcY);
  if (srcWidth <= 0 || srcHeight <= 0) {
    return;
  }
  region = new TextureRegion(texture, srcX, srcY, srcWidth, srcHeight);
  $flip(region, false, true);
  drawProperties.isFlipH && $flip(region, true, false);
  drawProperties.isFlipV && $flip(region, false, true);
  sprite = new Sprite(region);
  $setPosition(sprite, x_0, y_0);
  $setSize(sprite, width_0, height);
  $setOrigin(sprite, 0, 0);
  $setAlpha(sprite, this$static.currentAlpha * drawProperties.alpha_0);
  sprite.scaleX = sx;
  sprite.scaleY = sy;
  sprite.dirty = true;
  palfx = drawProperties.palfx;
  if (palfx) {
    mulA = palfx.mul.a / 255;
    mulA < $intern_67 && (mulA = $intern_67);
    mulA > $intern_68 && (mulA = $intern_68);
    $setColor(sprite, 0, 0, 0, this$static.currentAlpha * drawProperties.alpha_0 * mulA);
  }
  $draw(batch, sprite.texture, $getVertices(sprite), 0, 20);
  if (restoreBlend) {
    $flush(batch);
    $setBlendFunctionSeparate(batch, prevSrc, prevDst, prevSrc, prevDst);
  }
}

function $drawLine(this$static, x1, y1, x2, y2){
  var batch, batch0, batch1, wasDrawing;
  batch0 = (!this$static.batch && !!this$static.renderContext && (this$static.batch = this$static.renderContext.batch) , this$static.batch);
  if (!batch0) {
    return;
  }
  wasDrawing = $pauseBatchIfNecessary(this$static);
  $ensureRendererResources(this$static);
  if (!this$static.shapeRenderer) {
    batch1 = (!this$static.batch && !!this$static.renderContext && (this$static.batch = this$static.renderContext.batch) , this$static.batch);
    !!batch1 && wasDrawing && $begin(batch1);
    return;
  }
  $setProjectionMatrix_0(this$static.shapeRenderer, batch0.projectionMatrix);
  $begin_1(this$static.shapeRenderer, ($clinit_ShapeRenderer$ShapeType() , Line));
  $setColor_1(this$static.shapeRenderer, this$static.currentColor);
  $line(this$static.shapeRenderer, x1, y1, x2, y2);
  $end_0(this$static.shapeRenderer);
  batch = (!this$static.batch && !!this$static.renderContext && (this$static.batch = this$static.renderContext.batch) , this$static.batch);
  !!batch && wasDrawing && $begin(batch);
}

function $ensureRendererResources(this$static){
  if (!this$static.projectionMatrix) {
    if (this$static.renderContext) {
      this$static.projectionMatrix = $setToOrtho2D(new Matrix4, this$static.renderContext.width_0, this$static.renderContext.height_0);
      $scale($translate(new Matrix4, 0, this$static.renderContext.height_0, 0), 1, -1);
    }
     else {
      this$static.projectionMatrix = new Matrix4;
      new Matrix4;
    }
  }
  if (!this$static.shapeRenderer) {
    try {
      this$static.shapeRenderer = new ShapeRenderer;
    }
     catch ($e0) {
      $e0 = toJava($e0);
      if (instanceOf($e0, 6)) {
        this$static.shapeRenderer = null;
      }
       else 
        throw toJs($e0);
    }
  }
}

function $fillRect(this$static, x_0, y_0, width_0, height){
  var batch, batch0, batch1, rh, rw, vh, vp, vw, wasDrawing;
  batch0 = (!this$static.batch && !!this$static.renderContext && (this$static.batch = this$static.renderContext.batch) , this$static.batch);
  if (!batch0) {
    return;
  }
  wasDrawing = $pauseBatchIfNecessary(this$static);
  $ensureRendererResources(this$static);
  if (!this$static.shapeRenderer) {
    batch1 = (!this$static.batch && !!this$static.renderContext && (this$static.batch = this$static.renderContext.batch) , this$static.batch);
    !!batch1 && wasDrawing && $begin(batch1);
    return;
  }
  $setProjectionMatrix_0(this$static.shapeRenderer, batch0.projectionMatrix);
  $begin_1(this$static.shapeRenderer, ($clinit_ShapeRenderer$ShapeType() , Filled));
  $setColor_1(this$static.shapeRenderer, this$static.currentColor);
  rw = width_0;
  rh = height;
  if (!!this$static.renderContext && !!this$static.renderContext.viewport_0 && x_0 <= 0 && y_0 <= 0) {
    vp = this$static.renderContext.viewport_0;
    vw = vp.worldWidth;
    vh = vp.worldHeight;
    if (rw >= vw && rh >= vh) {
      rw = vw;
      rh = vh;
    }
  }
  $rect(this$static.shapeRenderer, x_0, y_0, rw, rh);
  $end_0(this$static.shapeRenderer);
  batch = (!this$static.batch && !!this$static.renderContext && (this$static.batch = this$static.renderContext.batch) , this$static.batch);
  !!batch && wasDrawing && $begin(batch);
}

function $getImageContainer(this$static, imageData, colors){
  var container, texture;
  if (imageData == null) {
    return null;
  }
  if ($containsKey(this$static.imageCache, imageData)) {
    return castTo($get_8(this$static.imageCache, imageData), 76);
  }
  if (instanceOf(imageData, 78)) {
    texture = castTo(imageData, 78);
    container = new LGDXMugenDrawer$LGDXImageContainer(texture, texture.data_0.pixmap.width_0, texture.data_0.pixmap.height_0);
    $put_4(this$static.imageCache, imageData, container);
    return container;
  }
  if (this$static.renderContext) {
    container = $getImageContainer_0(imageData, colors);
    if (container) {
      $put_4(this$static.imageCache, imageData, container);
      return container;
    }
  }
  return null;
}

function $getInstanceOfGameWindow(this$static){
  return !this$static.renderContext?null:this$static.renderContext;
}

function $pauseBatchIfNecessary(this$static){
  var batch;
  batch = (!this$static.batch && !!this$static.renderContext && (this$static.batch = this$static.renderContext.batch) , this$static.batch);
  if (!batch) {
    return false;
  }
  if (batch.drawing) {
    $end(batch);
    return true;
  }
  return false;
}

function $resolveTextureForDraw(this$static, imageContainer){
  var converted, imgObj;
  imgObj = imageContainer.getImg();
  if (instanceOf(imgObj, 78)) {
    return castTo(imgObj, 78);
  }
  converted = $getImageContainer(this$static, imgObj, 0);
  if (!converted || converted.getImg() == null) {
    return null;
  }
  if (instanceOf(converted.getImg(), 78)) {
    return castTo(converted.getImg(), 78);
  }
  return null;
}

function $restoreDefaultViewportAndCamera(this$static, batch){
  var cam, vp;
  gl_0.gl.disable(3089);
  if (!this$static.renderContext || !this$static.renderContext.viewport_0 || !this$static.renderContext.camera) {
    this$static.clipActive = false;
    this$static.clipPivotX = 0;
    this$static.clipPivotY = 0;
    return;
  }
  vp = this$static.renderContext.viewport_0;
  cam = this$static.renderContext.camera;
  $apply_0(vp, false);
  $setToOrtho(cam, 320, 240);
  $update_2(cam);
  !!batch && $setProjectionMatrix(batch, cam.combined);
  this$static.clipActive = false;
  this$static.clipPivotX = 0;
  this$static.clipPivotY = 0;
}

function $scale_0(this$static, x_0, y_0){
  var batch, m;
  batch = (!this$static.batch && !!this$static.renderContext && (this$static.batch = this$static.renderContext.batch) , this$static.batch);
  if (!batch) {
    return;
  }
  m = batch.transformMatrix;
  if (this$static.clipActive && this$static.uniformScaleAboutClipCorner && shouldScaleAboutClipPivot(x_0, y_0)) {
    $translate(m, this$static.clipPivotX, this$static.clipPivotY, 0);
    $scale(m, x_0, y_0);
    $translate(m, -this$static.clipPivotX, -this$static.clipPivotY, 0);
  }
   else {
    $scale(m, x_0, y_0);
  }
}

function $setAlpha_1(this$static, a){
  var na;
  na = a > 1?a / 255:a;
  this$static.currentAlpha = na;
  this$static.currentColor.a = na;
}

function $setClip(this$static, r){
  var batch, batch0, cam, clipH, clipW, maxWx, maxWy, minWx, minWy, sh, sw, sx, sy, wasDrawing, wx1, wx2, wy1, wy2;
  batch0 = (!this$static.batch && !!this$static.renderContext && (this$static.batch = this$static.renderContext.batch) , this$static.batch);
  wasDrawing = $pauseBatchIfNecessary(this$static);
  try {
    if (!r) {
      this$static.clipActive && $restoreDefaultViewportAndCamera(this$static, batch0);
      this$static.clipPivotX = 0;
      this$static.clipPivotY = 0;
      this$static.uniformScaleAboutClipCorner = false;
      return;
    }
    this$static.uniformScaleAboutClipCorner = false;
    if (!this$static.renderContext || !this$static.renderContext.viewport_0 || !this$static.renderContext.camera) {
      return;
    }
    wx1 = r.x1;
    wy1 = r.y1;
    wx2 = r.x2;
    wy2 = r.y2;
    minWx = $wnd.Math.min(wx1, wx2);
    maxWx = $wnd.Math.max(wx1, wx2);
    minWy = $wnd.Math.min(wy1, wy2);
    maxWy = $wnd.Math.max(wy1, wy2);
    clipW = maxWx - minWx;
    clipH = maxWy - minWy;
    if (clipW < $intern_69 || clipH < $intern_69) {
      this$static.clipActive && $restoreDefaultViewportAndCamera(this$static, batch0);
      return;
    }
    $computeClipScreenBounds(this$static, minWx, minWy, maxWx, maxWy);
    sx = round_int($wnd.Math.round(this$static.tmpClipScreen.x_0));
    sy = round_int($wnd.Math.round(this$static.tmpClipScreen.y_0));
    sw = round_int($wnd.Math.round(this$static.tmpClipScreen.width_0));
    sh = round_int($wnd.Math.round(this$static.tmpClipScreen.height_0));
    if (sw < 1 || sh < 1) {
      this$static.clipActive && $restoreDefaultViewportAndCamera(this$static, batch0);
      return;
    }
    cam = this$static.renderContext.camera;
    graphics.canvas.width != graphics.canvas.width || graphics.canvas.height != graphics.canvas.height?$glViewport(gl_0, round_int(sx * graphics.canvas.width / graphics.canvas.width), round_int(sy * graphics.canvas.height / graphics.canvas.height), round_int(sw * graphics.canvas.width / graphics.canvas.width), round_int(sh * graphics.canvas.height / graphics.canvas.height)):$glViewport(gl_0, sx, sy, sw, sh);
    $setToOrtho(cam, clipW, clipH);
    $update_2(cam);
    !!batch0 && $setProjectionMatrix(batch0, cam.combined);
    this$static.clipActive = true;
    this$static.clipPivotX = minWx;
    this$static.clipPivotY = minWy;
  }
   finally {
    batch = (!this$static.batch && !!this$static.renderContext && (this$static.batch = this$static.renderContext.batch) , this$static.batch);
    !!batch && wasDrawing && $begin(batch);
  }
}

function $setColor_2(this$static, r, g, b){
  $setColor_3(this$static, r, g, b, this$static.currentAlpha);
}

function $setColor_3(this$static, r, g, b, a){
  var na, nb, ng, nr;
  nr = r > 1?r / 255:r;
  ng = g > 1?g / 255:g;
  nb = b > 1?b / 255:b;
  na = a > 1?a / 255:a;
  $set(this$static.currentColor, nr, ng, nb, na);
  this$static.currentAlpha = na;
}

function shouldScaleAboutClipPivot(x_0, y_0){
  if ($wnd.Math.abs(x_0 - 1) < $intern_70 && $wnd.Math.abs(y_0 - 1) < $intern_70) {
    return false;
  }
  return x_0 < 1 && y_0 < 1 || x_0 > 1 && y_0 > 1;
}

defineClass(375, 537, {});
_.clipActive = false;
_.clipPivotX = 0;
_.clipPivotY = 0;
_.currentAlpha = 1;
_.uniformScaleAboutClipCorner = false;
var Lorg_lee_mugen_renderer_libgdx_core_LGDXMugenDrawer_2_classLit = createForClass('org.lee.mugen.renderer.libgdx.core', 'LGDXMugenDrawer', 375);
function LGDXMugenDrawer$LGDXImageContainer(texture, width_0, height){
  ImageContainer.call(this, texture, width_0, height);
}

defineClass(377, 76, $intern_66, LGDXMugenDrawer$LGDXImageContainer);
var Lorg_lee_mugen_renderer_libgdx_core_LGDXMugenDrawer$LGDXImageContainer_2_classLit = createForClass('org.lee.mugen.renderer.libgdx.core', 'LGDXMugenDrawer/LGDXImageContainer', 377);
function $setFramerate(this$static, framerate){
  this$static.framerate = framerate;
}

function LGDXMugenTimer(){
  $clinit_System();
  fromDouble_0(Date.now());
}

defineClass(392, 1, {}, LGDXMugenTimer);
_.framerate = 60;
var Lorg_lee_mugen_renderer_libgdx_core_LGDXMugenTimer_2_classLit = createForClass('org.lee.mugen.renderer.libgdx.core', 'LGDXMugenTimer', 392);
function decode_0(data_0, colorDepth){
  var argb, arrays, count, header, height, i, idx, in_0, index_0, lastArg, pal, pixmap, skipped, value_0, width_0, x_0, xp, y_0, yp, a, r, g, b;
  header = new PCXHeader(data_0);
  width_0 = header.xmax - header.xmin + 1;
  height = header.ymax - header.ymin + 1;
  pal = new PCXPalette;
  $load_1(pal, new ByteArrayInputStream(data_0));
  in_0 = new ByteArrayInputStream(data_0);
  skipped = $skip(in_0, 128);
  if (compare_0(skipped, 128) < 0) {
    throw toJs(new IOException('PCX truncated header'));
  }
  xp = 0;
  yp = 0;
  arrays = initUnidimensionalArray(B_classLit, $intern_6, 5, width_0 * height, 15, 1);
  while (yp < height) {
    value_0 = in_0.pos < in_0.count?in_0.buf[in_0.pos++] & 255:-1;
    if (value_0 < 0) {
      throw toJs(new IOException('Unexpected EOF in PCX payload'));
    }
    if (value_0 >= 192) {
      count = value_0 - 192;
      value_0 = in_0.pos < in_0.count?in_0.buf[in_0.pos++] & 255:-1;
      if (value_0 < 0) {
        throw toJs(new IOException('Unexpected EOF in PCX RLE'));
      }
    }
     else {
      count = 1;
    }
    for (i = 0; i < count; i++) {
      if (xp < width_0) {
        index_0 = value_0 - colorDepth < 0?0:value_0 - colorDepth;
        value_0 != 0 && (arrays[xp + yp * width_0] = index_0 << 24 >> 24);
      }
      ++xp;
      if (xp == header.bytesPerLine) {
        xp = 0;
        ++yp;
        break;
      }
    }
  }
  pixmap = new Pixmap(width_0, (lastArg = height , $clinit_Pixmap$Format() , lastArg));
  for (y_0 = 0; y_0 < height; y_0++) {
    for (x_0 = 0; x_0 < width_0; x_0++) {
      idx = arrays[x_0 + y_0 * width_0] & 255;
      argb = idx == 0?0:$getColor(pal, idx);
      $drawPixel(pixmap, x_0, y_0, (a = argb >> 24 & 255 , r = argb >> 16 & 255 , g = argb >> 8 & 255 , b = argb & 255 , r << 24 | g << 16 | b << 8 | a));
    }
  }
  return pixmap;
}

function LGDXMugenDrawer(gameWindow){
  this.currentColor = new Color(1, 1, 1, 1);
  new RGB;
  new ArrayList;
  new AtomicInteger;
  this.imageCache = new HashMap;
  this.clipProjTmp = new Vector3;
  this.tmpClipScreen = new Rectangle;
  this.renderContext = gameWindow;
}

defineClass(376, 375, {}, LGDXMugenDrawer);
var Lorg_lee_mugen_renderer_libgdx_web_LGDXMugenDrawer_2_classLit = createForClass('org.lee.mugen.renderer.libgdx.web', 'LGDXMugenDrawer', 376);
function LGDXWebApplication(){
  this.runnables = new Array_0;
  this.runnablesHelper = new Array_0;
  this.lifecycleListeners = new Array_0;
  new ObjectMap;
  $clinit_System();
  mul_1(fromDouble_0(Date.now()), $intern_4);
}

defineClass(276, 279, {}, LGDXWebApplication);
var Lorg_lee_mugen_renderer_libgdx_web_LGDXWebApplication_2_classLit = createForClass('org.lee.mugen.renderer.libgdx.web', 'LGDXWebApplication', 276);
function $decodeSfx(this$static, ctx, data_0, key){
  var self_0 = this$static;
  ctx.decodeAudioData(data_0, function(buffer){
    self_0.onSfxDecoded(key, buffer);
  }
  , function(err){
    self_0.onSfxDecodeError(key);
  }
  );
}

function $disposeMusic(this$static){
  if (this$static.music) {
    try {
      $stop(this$static.music);
    }
     catch ($e0) {
      $e0 = toJava($e0);
      if (!instanceOf($e0, 9))
        throw toJs($e0);
    }
    try {
      $dispose(this$static.music);
    }
     catch ($e1) {
      $e1 = toJava($e1);
      if (!instanceOf($e1, 9))
        throw toJs($e1);
    }
    this$static.music = null;
  }
}

function $ensureSfxContext(this$static){
  if (this$static.sfxContext) {
    return;
  }
  this$static.sfxContext = createAudioContext();
  !!this$static.sfxContext && (this$static.sfxGain = createGain(this$static.sfxContext, this$static.musicVolume));
}

function $evictOneSfxBuffer(this$static){
  var entry, first, outerIter;
  if ($size(this$static.sfxBuffers) == 0) {
    return;
  }
  first = (entry = $next_6((outerIter = new AbstractHashMap$EntrySetIterator((new AbstractHashMap$EntrySet((new AbstractMap$1(this$static.sfxBuffers)).this$01)).this$01) , new AbstractMap$1$1(outerIter)).val$outerIter2) , castToString(entry.getKey()));
  $removeStringValue(this$static.sfxBuffers, first);
}

function $lambda$0(this$static, internal_1){
  var fb, fh;
  $disposeMusic(this$static);
  fh = $internal(files_0, internal_1);
  if (!$contains(fh.preloader, fh.file)) {
    $error(app_0, 'JMugenWebAudio', 'BGM missing: ' + internal_1);
    if (!$equals_5('data/sound/intro.mp3', internal_1)) {
      fb = $internal(files_0, 'data/sound/intro.mp3');
      if ($contains(fb.preloader, fb.file)) {
        this$static.pendingMusicPath = 'data/sound/intro.mp3';
        $playMusicHandle(this$static, fb, 'data/sound/intro.mp3');
      }
    }
    return;
  }
  $playMusicHandle(this$static, fh, internal_1);
}

function $lambda$2(this$static, data_2){
  var t;
  try {
    $playSfxWebAudio(this$static, data_2);
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 9)) {
      t = $e0;
      $error(app_0, 'JMugenWebAudio', 'SFX failed: ' + t);
    }
     else 
      throw toJs($e0);
  }
}

function $onUserGesture(this$static){
  var pending;
  if (this$static.unlocked) {
    !!this$static.sfxContext && resumeContext(this$static.sfxContext);
    return;
  }
  this$static.unlocked = true;
  $log(app_0, 'JMugenWebAudio', 'Audio unlocked by user gesture');
  !!this$static.sfxContext && resumeContext(this$static.sfxContext);
  pending = this$static.pendingMusicPath;
  pending != null && $postRunnable(app_0, new LGDXWebAudioPlayback$lambda$0$Type(this$static, pending));
}

function $playMusic(this$static, path){
  var internal;
  if (!app_0) {
    return;
  }
  internal = resolveMusicPath(path);
  if (internal == null) {
    return;
  }
  this$static.pendingMusicPath = internal;
  this$static.unlocked || $log(app_0, 'JMugenWebAudio', 'Queue BGM until user gesture: ' + internal);
  $postRunnable(app_0, new LGDXWebAudioPlayback$lambda$0$Type(this$static, internal));
}

function $playMusicHandle(this$static, fh, label_0){
  var t;
  try {
    this$static.music = $newMusic(audio_0, fh);
    $setLoop(this$static.music.audio);
    $setVolume(this$static.music, this$static.musicVolume);
    $play(this$static.music.audio);
    $log(app_0, 'JMugenWebAudio', 'Playing BGM: ' + label_0);
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 9)) {
      t = $e0;
      $error(app_0, 'JMugenWebAudio', 'BGM failed: ' + label_0 + ' \u2014 ' + t);
      $disposeMusic(this$static);
    }
     else 
      throw toJs($e0);
  }
}

function $playSfx(this$static, soundData){
  var data_0;
  if (soundData == null || soundData.length == 0 || !app_0) {
    return;
  }
  data_0 = soundData;
  $postRunnable(app_0, new LGDXWebAudioPlayback$lambda$2$Type(this$static, data_0));
}

function $playSfxWebAudio(this$static, data_0){
  var ab, buffer, key;
  $ensureSfxContext(this$static);
  if (!this$static.sfxContext) {
    $error(app_0, 'JMugenWebAudio', 'No AudioContext for SFX');
    return;
  }
  !!this$static.sfxContext && resumeContext(this$static.sfxContext);
  key = sfxKey(data_0);
  buffer = castToJso($getStringValue(this$static.sfxBuffers, key));
  if (buffer) {
    playDecodedBuffer(this$static.sfxContext, this$static.sfxGain, buffer, this$static.musicVolume);
    return;
  }
  buffer = createBufferFromPcmWav(this$static.sfxContext, data_0);
  if (buffer) {
    $size(this$static.sfxBuffers) >= 96 && $evictOneSfxBuffer(this$static);
    $putStringValue(this$static.sfxBuffers, key, buffer);
    playDecodedBuffer(this$static.sfxContext, this$static.sfxGain, buffer, this$static.musicVolume);
    return;
  }
  if ($hasStringValue(this$static.sfxPendingDecode, key)) {
    return;
  }
  $size(this$static.sfxBuffers) >= 96 && $evictOneSfxBuffer(this$static);
  $putStringValue(this$static.sfxPendingDecode, key, ($clinit_Boolean() , TRUE));
  ab = toArrayBuffer(data_0);
  $decodeSfx(this$static, this$static.sfxContext, ab, key);
}

function $stopMusic(this$static){
  this$static.pendingMusicPath = null;
  if (!app_0) {
    return;
  }
  $postRunnable(app_0, new LGDXWebAudioPlayback$0methodref$disposeMusic$Type(this$static));
}

function LGDXWebAudioPlayback(){
  this.sfxBuffers = new HashMap;
  this.sfxPendingDecode = new HashMap;
}

function createAudioContext(){
  var AC = $wnd.AudioContext || $wnd.webkitAudioContext;
  if (!AC) {
    return null;
  }
  return new AC;
}

function createBufferFromPcmWav(ctx, wav){
  var audioFormat, bits, bytesPerSample, c0, c1, c2, c3, channels, chunkSize, dataLen, dataOff, frameCount, frameSize, hi, i, isData, isFmt, lo, pos, s, sampleRate, samples, unsigned, buffer;
  if (!ctx || wav == null || wav.length < 44) {
    return null;
  }
  if (wav[0] != 82 || wav[1] != 73 || wav[2] != 70 || wav[3] != 70) {
    return null;
  }
  if (wav[8] != 87 || wav[9] != 65 || wav[10] != 86 || wav[11] != 69) {
    return null;
  }
  pos = 12;
  audioFormat = -1;
  channels = 0;
  sampleRate = 0;
  bits = 0;
  dataOff = -1;
  dataLen = 0;
  while (pos + 8 <= wav.length) {
    c0 = wav[pos] & 255 & $intern_5;
    c1 = wav[pos + 1] & 255 & $intern_5;
    c2 = wav[pos + 2] & 255 & $intern_5;
    c3 = wav[pos + 3] & 255 & $intern_5;
    chunkSize = wav[pos + 4] & 255 | (wav[pos + 4 + 1] & 255) << 8 | (wav[pos + 4 + 2] & 255) << 16 | (wav[pos + 4 + 3] & 255) << 24;
    if (chunkSize < 0 || pos + 8 + chunkSize > wav.length + 1) {
      break;
    }
    isFmt = c0 == 102 && c1 == 109 && c2 == 116 && c3 == 32;
    isData = c0 == 100 && c1 == 97 && c2 == 116 && c3 == 97;
    if (isFmt && chunkSize >= 16) {
      audioFormat = wav[pos + 8] & 255 | (wav[pos + 8 + 1] & 255) << 8;
      channels = wav[pos + 10] & 255 | (wav[pos + 10 + 1] & 255) << 8;
      sampleRate = wav[pos + 12] & 255 | (wav[pos + 12 + 1] & 255) << 8 | (wav[pos + 12 + 2] & 255) << 16 | (wav[pos + 12 + 3] & 255) << 24;
      bits = wav[pos + 22] & 255 | (wav[pos + 22 + 1] & 255) << 8;
    }
     else if (isData) {
      dataOff = pos + 8;
      dataLen = chunkSize;
      dataOff + dataLen > wav.length && (dataLen = wav.length - dataOff);
      break;
    }
    pos += 8 + chunkSize;
    (chunkSize & 1) != 0 && ++pos;
  }
  if (audioFormat != 1 || channels < 1 || sampleRate < 1 || dataOff < 0 || dataLen < 1) {
    return null;
  }
  if (bits != 8 && bits != 16) {
    return null;
  }
  bytesPerSample = bits / 8 | 0;
  frameSize = bytesPerSample * channels;
  if (frameSize <= 0) {
    return null;
  }
  frameCount = dataLen / frameSize | 0;
  if (frameCount < 1) {
    return null;
  }
  samples = new Float32Array(frameCount);
  if (bits == 8) {
    for (i = 0; i < frameCount; i++) {
      unsigned = wav[dataOff + i * frameSize] & 255;
      samples[i] = (unsigned - 128) / 128;
    }
  }
   else {
    for (i = 0; i < frameCount; i++) {
      lo = wav[dataOff + i * frameSize] & 255;
      hi = wav[dataOff + i * frameSize + 1];
      s = (lo | hi << 8) << 16 >> 16;
      samples[i] = s / $intern_34;
    }
  }
  return buffer = ctx.createBuffer(1, samples.length, sampleRate) , buffer.getChannelData(0).set(samples) , buffer;
}

function createGain(ctx, volume){
  var gain = ctx.createGain?ctx.createGain():ctx.createGainNode();
  gain.gain.value = volume;
  gain.connect(ctx.destination);
  return gain;
}

function playDecodedBuffer(ctx, gain, buffer, volume){
  if (!ctx || !buffer) {
    return;
  }
  ctx.state === 'suspended' && ctx.resume && ctx.resume();
  var src_0 = ctx.createBufferSource();
  src_0.buffer = buffer;
  var g = ctx.createGain?ctx.createGain():ctx.createGainNode();
  g.gain.value = volume;
  src_0.connect(g);
  gain?g.connect(gain):g.connect(ctx.destination);
  src_0.start?src_0.start(0):src_0.noteOn(0);
}

function resolveMusicPath(path){
  var lower, p, suffixlength, suffixlength0, suffixlength1;
  p = $replace($trim(path));
  if (p.length == 0 || (suffixlength0 = '/'.length , $equals_5(p.substr(p.length - suffixlength0, suffixlength0), '/'))) {
    return 'data/sound/intro.mp3';
  }
  lower = $toLowerCase(p, ($clinit_Locale() , ROOT_0));
  suffixlength1 = '.adx'.length;
  if ($equals_5(lower.substr(lower.length - suffixlength1, suffixlength1), '.adx') || (suffixlength = '.bin'.length , $equals_5(lower.substr(lower.length - suffixlength, suffixlength), '.bin'))) {
    $log(app_0, 'JMugenWebAudio', 'ADX/BIN unsupported on web, using data/sound/intro.mp3');
    return 'data/sound/intro.mp3';
  }
  return toInternalPath(p);
}

function resumeContext(ctx){
  ctx && ctx.state === 'suspended' && ctx.resume && ctx.resume();
}

function sfxKey(data_0){
  var h, i;
  h = data_0.length;
  for (i = 0; i < data_0.length; i++) {
    h = add_2(mul_1(h, 31), data_0[i] & 255);
  }
  return toPowerOfTwoUnsignedString(h) + '_' + data_0.length;
}

function toArrayBuffer(data_0){
  var arr, i;
  arr = create_6(data_0.length);
  for (i = 0; i < data_0.length; i++) {
    arr[i] = data_0[i];
  }
  return arr.buffer;
}

defineClass(414, 1, {}, LGDXWebAudioPlayback);
_.onSfxDecodeError = function onSfxDecodeError(key){
  $removeStringValue(this.sfxPendingDecode, key);
  $error(app_0, 'JMugenWebAudio', 'SFX decodeAudioData failed key=' + key);
}
;
_.onSfxDecoded = function onSfxDecoded(key, buffer){
  $removeStringValue(this.sfxPendingDecode, key);
  if (!buffer) {
    $error(app_0, 'JMugenWebAudio', 'SFX decode returned null for key ' + key);
    return;
  }
  $putStringValue(this.sfxBuffers, key, buffer);
  playDecodedBuffer(this.sfxContext, this.sfxGain, buffer, this.musicVolume);
}
;
_.musicVolume = 1;
_.unlocked = false;
var Lorg_lee_mugen_renderer_libgdx_web_LGDXWebAudioPlayback_2_classLit = createForClass('org.lee.mugen.renderer.libgdx.web', 'LGDXWebAudioPlayback', 414);
function LGDXWebAudioPlayback$0methodref$disposeMusic$Type($$outer_0){
  this.$$outer_0 = $$outer_0;
}

defineClass(415, 1, {215:1}, LGDXWebAudioPlayback$0methodref$disposeMusic$Type);
_.run = function run(){
  $disposeMusic(this.$$outer_0);
}
;
var Lorg_lee_mugen_renderer_libgdx_web_LGDXWebAudioPlayback$0methodref$disposeMusic$Type_2_classLit = createForClass('org.lee.mugen.renderer.libgdx.web', 'LGDXWebAudioPlayback/0methodref$disposeMusic$Type', 415);
function LGDXWebAudioPlayback$lambda$0$Type($$outer_0, internal_1){
  this.$$outer_0 = $$outer_0;
  this.internal_1 = internal_1;
}

defineClass(239, 1, {215:1}, LGDXWebAudioPlayback$lambda$0$Type);
_.run = function run_0(){
  $lambda$0(this.$$outer_0, this.internal_1);
}
;
var Lorg_lee_mugen_renderer_libgdx_web_LGDXWebAudioPlayback$lambda$0$Type_2_classLit = createForClass('org.lee.mugen.renderer.libgdx.web', 'LGDXWebAudioPlayback/lambda$0$Type', 239);
function LGDXWebAudioPlayback$lambda$2$Type($$outer_0, data_2){
  this.$$outer_0 = $$outer_0;
  this.data_2 = data_2;
}

defineClass(416, 1, {215:1}, LGDXWebAudioPlayback$lambda$2$Type);
_.run = function run_1(){
  $lambda$2(this.$$outer_0, this.data_2);
}
;
var Lorg_lee_mugen_renderer_libgdx_web_LGDXWebAudioPlayback$lambda$2$Type_2_classLit = createForClass('org.lee.mugen.renderer.libgdx.web', 'LGDXWebAudioPlayback/lambda$2$Type', 416);
function $addActionListener(this$static, al){
  $add_8(this$static.mugenKeyListeners, al);
}

function $addSpriteKeyProcessor(this$static, scp){
  !!scp && $add_11(this$static.spriteCmdProcess, new LGDXWebGameWindow$SprCmdProcessListenerAction(scp));
}

function $create_0(this$static){
  var e, p1, p2, systemKeys;
  this$static.batch = new SpriteBatch;
  this$static.camera = new OrthographicCamera;
  this$static.viewport_0 = new FitViewport(this$static.camera);
  $setToOrtho(this$static.camera, 320, 240);
  $apply_0(this$static.viewport_0, false);
  $setProjectionMatrix(this$static.batch, this$static.camera.combined);
  $set_1(this$static.worldProjectionSnapshot, this$static.camera.combined.val_0);
  installAudioPlayback(new LGDXWebAudioPlayback);
  $reset_5(this$static.cmdProcess);
  $reset_5(this$static.spriteCmdProcess);
  $clinit_CmdProcDispatcher();
  $reset_4(spriteDispatcherMap);
  p1 = new CmdProcDispatcher(51, 47, 29, 32, 38, 39, 40, 49, 37, 43, 74, 44);
  $putStringValue(spriteDispatcherMap, '1', p1);
  $putStringValue(spriteDispatcherMap, 'menu', p1);
  $registerDispatcherKeys(this$static, '1');
  p2 = new CmdProcDispatcher(19, 20, 21, 22, 7, 146, 147, 148, 149, 150, 152, 151);
  $putStringValue(spriteDispatcherMap, '2', p2);
  $registerDispatcherKeys(this$static, '2');
  systemKeys = new LGDXWebGameWindow$CmdProcessListener;
  $setKeys(systemKeys, stampJavaTypeInfo(getClassLiteralForArray(I_classLit, 1), $intern_3, 5, 15, [111, 66]));
  $add_11(this$static.cmdProcess, systemKeys);
  if (!this$static.callback) {
    this$static.finishInit = true;
    return;
  }
  try {
    this$static.callback.init(this$static);
    this$static.finishInit = true;
    $scheduleDeferred(($clinit_SchedulerImpl() , INSTANCE), new LGDXWebGameWindow$1);
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 6)) {
      e = $e0;
      $error_0(app_0, 'JMugenWeb', 'Error initializing web game', e);
      throw toJs(new RuntimeException_1('Failed to initialize web game callback', e));
    }
     else 
      throw toJs($e0);
  }
}

function $keyManagementExecute(this$static){
  var action, action$iterator, cmd, cmd$iterator, i, key;
  !!this$static.callback && $process_5(this$static.debugEventManager, this$static.callback);
  for (cmd$iterator = $listIterator(this$static.cmdProcess, 0); cmd$iterator.currentNode != cmd$iterator.this$01.tail;) {
    cmd = castTo($next_9(cmd$iterator), 145);
    for (i = 0; i < cmd.keys_0.length; ++i) {
      key = cmd.keys_0[i];
      if (!cmd.areKeysPress[i] && $isKeyPressed(input_1, key)) {
        cmd.areKeysPress[i] = true;
        $notifyKeyListeners(this$static, key, true);
      }
       else if (cmd.areKeysPress[i] && !$isKeyPressed(input_1, key)) {
        cmd.areKeysPress[i] = false;
        $notifyKeyListeners(this$static, key, false);
      }
    }
  }
  for (action$iterator = $listIterator(this$static.spriteCmdProcess, 0); action$iterator.currentNode != action$iterator.this$01.tail;) {
    action = castTo($next_9(action$iterator), 171);
    for (i = 0; i < action.keys_0.length; ++i) {
      key = action.keys_0[i];
      if (!action.areKeysPress[i] && $isKeyPressed(input_1, key)) {
        action.areKeysPress[i] = true;
        $keyPressed(action.scp, key);
        $notifyKeyListeners(this$static, key, true);
      }
       else if (action.areKeysPress[i] && !$isKeyPressed(input_1, key)) {
        action.areKeysPress[i] = false;
        $keyReleased(action.scp, key);
        $notifyKeyListeners(this$static, key, false);
      }
    }
  }
}

function $notifyKeyListeners(this$static, key, isPress){
  var listener, listener$iterator;
  for (listener$iterator = new ArrayList$1(this$static.mugenKeyListeners); listener$iterator.i < listener$iterator.this$01.array.length;) {
    listener = castTo($next_7(listener$iterator), 274);
    listener.action_0(key, isPress);
  }
}

function $registerDispatcherKeys(this$static, id_0){
  var dispatcher, listener;
  dispatcher = ($clinit_CmdProcDispatcher() , castTo($getStringValue(spriteDispatcherMap, id_0), 77));
  if (!dispatcher) {
    return;
  }
  listener = new LGDXWebGameWindow$CmdProcessListener;
  $setKeys(listener, (dispatcher.keys_0 == null && (dispatcher.keys_0 = stampJavaTypeInfo(getClassLiteralForArray(I_classLit, 1), $intern_3, 5, 15, [dispatcher.up, dispatcher.down, dispatcher.back_0, dispatcher.forward_0, dispatcher.a, dispatcher.b, dispatcher.c, dispatcher.x_0, dispatcher.y_0, dispatcher.z_0, dispatcher.abc, dispatcher.xyz])) , dispatcher.keys_0));
  $add_11(this$static.cmdProcess, listener);
}

function $render_10(this$static){
  var another, delta, e;
  gl_0.gl.clearColor(0, 0, 0, 1);
  gl_0.gl.clear($intern_15);
  if (!this$static.viewport_0 || !this$static.batch || !this$static.callback || !this$static.finishInit || !this$static.gameRunning) {
    return;
  }
  $unlockAudioIfNeeded();
  $keyManagementExecute(this$static);
  $apply_0(this$static.viewport_0, false);
  try {
    delta = round_int(graphics.deltaTime * 1000);
    delta <= 0 && (delta = 16);
    this$static.callback.update(delta);
    another = this$static.callback.getNext();
    if (another != this$static.callback) {
      another.init(this$static);
      this$static.callback.free();
      this$static.callback = another;
    }
    $setProjectionMatrix(this$static.batch, this$static.camera.combined);
    $set_1(this$static.worldProjectionSnapshot, this$static.camera.combined.val_0);
    $idt(this$static.batchTransformIdentity);
    $setTransformMatrix(this$static.batch, this$static.batchTransformIdentity);
    $begin(this$static.batch);
    try {
      this$static.callback.render_0();
    }
     finally {
      this$static.batch.drawing && $end(this$static.batch);
    }
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 6)) {
      e = $e0;
      $error_0(app_0, 'JMugenWeb', 'Error updating web game', e);
    }
     else 
      throw toJs($e0);
  }
}

function $resize_5(this$static, width_0, height){
  if (!this$static.viewport_0 || !this$static.camera || !this$static.batch) {
    return;
  }
  $update_4(this$static.viewport_0, width_0, height);
  $setToOrtho(this$static.camera, 320, 240);
  $setProjectionMatrix(this$static.batch, this$static.camera.combined);
  $set_1(this$static.worldProjectionSnapshot, this$static.camera.combined.val_0);
}

function $setGameWindowCallback(this$static, callback){
  this$static.callback = callback;
}

function $unlockAudioIfNeeded(){
  var gesture, k, playback, web;
  playback = audioPlayback;
  if (!playback) {
    return;
  }
  web = playback;
  if (web.unlocked) {
    return;
  }
  gesture = $isTouched(input_1);
  if (!gesture) {
    for (k = 0; k < 256; k++) {
      if ($isKeyPressed(input_1, k)) {
        gesture = true;
        break;
      }
    }
  }
  if (gesture) {
    $onUserGesture(web);
    $scheduleDeferred(($clinit_SchedulerImpl() , INSTANCE), new LGDXWebGameWindow$2);
  }
}

function LGDXWebGameWindow(){
  this.timer = new LGDXMugenTimer;
  $clinit_Vector3();
  this.batchTransformIdentity = new Matrix4;
  this.worldProjectionSnapshot = new Matrix4;
  this.cmdProcess = new LinkedList;
  this.spriteCmdProcess = new LinkedList;
  this.mugenKeyListeners = new ArrayList;
  this.debugEventManager = new LGDXWebGameWindow$DebugEventManager;
  this.width_0 = 640;
  this.height_0 = 480;
}

function isDebugKeyDown(key){
  if (key == 129) {
    return $isKeyPressed(input_1, 129) || $isKeyPressed(input_1, 130);
  }
  return $isKeyPressed(input_1, key);
}

defineClass(329, 1, {}, LGDXWebGameWindow);
_.finishInit = false;
_.gameRunning = true;
_.height_0 = 0;
_.width_0 = 0;
var Lorg_lee_mugen_renderer_libgdx_web_LGDXWebGameWindow_2_classLit = createForClass('org.lee.mugen.renderer.libgdx.web', 'LGDXWebGameWindow', 329);
function LGDXWebGameWindow$1(){
}

defineClass(331, 1, {}, LGDXWebGameWindow$1);
_.execute_1 = function execute_7(){
  var el;
  el = $doc.getElementById('jmugen-status');
  !!el && (el.style['display'] = ($clinit_Style$Display() , 'none') , undefined);
}
;
var Lorg_lee_mugen_renderer_libgdx_web_LGDXWebGameWindow$1_2_classLit = createForClass('org.lee.mugen.renderer.libgdx.web', 'LGDXWebGameWindow/1', 331);
function LGDXWebGameWindow$2(){
}

defineClass(332, 1, {}, LGDXWebGameWindow$2);
_.execute_1 = function execute_8(){
  var el;
  el = $doc.getElementById('jmugen-audio-hint');
  !!el && (el.style['display'] = ($clinit_Style$Display() , 'none') , undefined);
}
;
var Lorg_lee_mugen_renderer_libgdx_web_LGDXWebGameWindow$2_2_classLit = createForClass('org.lee.mugen.renderer.libgdx.web', 'LGDXWebGameWindow/2', 332);
function $setKeys(this$static, keys_0){
  this$static.keys_0 = keys_0;
  this$static.areKeysPress = initUnidimensionalArray(Z_classLit, $intern_2, 5, keys_0.length, 16, 1);
}

function LGDXWebGameWindow$CmdProcessListener(){
}

defineClass(145, 1, {145:1}, LGDXWebGameWindow$CmdProcessListener);
var Lorg_lee_mugen_renderer_libgdx_web_LGDXWebGameWindow$CmdProcessListener_2_classLit = createForClass('org.lee.mugen.renderer.libgdx.web', 'LGDXWebGameWindow/CmdProcessListener', 145);
function LGDXWebGameWindow$DebugChord(action, keys_0, allowRepeat){
  this.action = action;
  this.keys_0 = keys_0;
  this.allowRepeat = allowRepeat;
}

defineClass(144, 1, {144:1}, LGDXWebGameWindow$DebugChord);
_.action = 0;
_.allowRepeat = false;
_.pressed = false;
var Lorg_lee_mugen_renderer_libgdx_web_LGDXWebGameWindow$DebugChord_2_classLit = createForClass('org.lee.mugen.renderer.libgdx.web', 'LGDXWebGameWindow/DebugChord', 144);
function $add_13(this$static, action, keys_0){
  $add_8(this$static.chords, new LGDXWebGameWindow$DebugChord(action, keys_0, false));
}

function $addRepeat(this$static, action, keys_0){
  $add_8(this$static.chords, new LGDXWebGameWindow$DebugChord(action, keys_0, true));
}

function $process_5(this$static, cb){
  var all, chord, chord$iterator, fight, key, key$array, key$index, key$max;
  if (!instanceOf(cb, 206)) {
    return;
  }
  fight = castTo(cb, 553);
  for (chord$iterator = new ArrayList$1(this$static.chords); chord$iterator.i < chord$iterator.this$01.array.length;) {
    chord = castTo($next_7(chord$iterator), 144);
    all = true;
    for (key$array = chord.keys_0 , key$index = 0 , key$max = key$array.length; key$index < key$max; ++key$index) {
      key = key$array[key$index];
      if (!isDebugKeyDown(key)) {
        all = false;
        break;
      }
    }
    if (chord.allowRepeat) {
      all && $onDebugAction(fight, chord.action);
      continue;
    }
    if (all) {
      chord.pressed = true;
    }
     else if (chord.pressed) {
      chord.pressed = false;
      $onDebugAction(fight, chord.action);
    }
  }
}

function LGDXWebGameWindow$DebugEventManager(){
  this.chords = new ArrayList;
  $add_13(this, 0, stampJavaTypeInfo(getClassLiteralForArray(I_classLit, 1), $intern_3, 5, 15, [129, 32]));
  $add_13(this, 13, stampJavaTypeInfo(getClassLiteralForArray(I_classLit, 1), $intern_3, 5, 15, [129, 33]));
  $add_13(this, 8, stampJavaTypeInfo(getClassLiteralForArray(I_classLit, 1), $intern_3, 5, 15, [62]));
  $add_13(this, 1, stampJavaTypeInfo(getClassLiteralForArray(I_classLit, 1), $intern_3, 5, 15, [129, 31]));
  $add_13(this, 2, stampJavaTypeInfo(getClassLiteralForArray(I_classLit, 1), $intern_3, 5, 15, [129, 52]));
  $addRepeat(this, 3, stampJavaTypeInfo(getClassLiteralForArray(I_classLit, 1), $intern_3, 5, 15, [129, 157]));
  $addRepeat(this, 4, stampJavaTypeInfo(getClassLiteralForArray(I_classLit, 1), $intern_3, 5, 15, [129, 156]));
  $addRepeat(this, 3, stampJavaTypeInfo(getClassLiteralForArray(I_classLit, 1), $intern_3, 5, 15, [129, 70]));
  $addRepeat(this, 4, stampJavaTypeInfo(getClassLiteralForArray(I_classLit, 1), $intern_3, 5, 15, [129, 69]));
  $add_13(this, 5, stampJavaTypeInfo(getClassLiteralForArray(I_classLit, 1), $intern_3, 5, 15, [129, 155]));
  $add_13(this, 6, stampJavaTypeInfo(getClassLiteralForArray(I_classLit, 1), $intern_3, 5, 15, [129, 44]));
  $add_13(this, 7, stampJavaTypeInfo(getClassLiteralForArray(I_classLit, 1), $intern_3, 5, 15, [129, 29]));
  $add_13(this, 12, stampJavaTypeInfo(getClassLiteralForArray(I_classLit, 1), $intern_3, 5, 15, [131]));
}

defineClass(330, 1, {}, LGDXWebGameWindow$DebugEventManager);
var Lorg_lee_mugen_renderer_libgdx_web_LGDXWebGameWindow$DebugEventManager_2_classLit = createForClass('org.lee.mugen.renderer.libgdx.web', 'LGDXWebGameWindow/DebugEventManager', 330);
function LGDXWebGameWindow$SprCmdProcessListenerAction(scp){
  this.scp = scp;
  this.keys_0 = scp.dispatcher?$getKeys(scp.dispatcher):initUnidimensionalArray(I_classLit, $intern_3, 5, 0, 15, 1);
  this.areKeysPress = initUnidimensionalArray(Z_classLit, $intern_2, 5, this.keys_0.length, 16, 1);
}

defineClass(171, 1, {171:1}, LGDXWebGameWindow$SprCmdProcessListenerAction);
var Lorg_lee_mugen_renderer_libgdx_web_LGDXWebGameWindow$SprCmdProcessListenerAction_2_classLit = createForClass('org.lee.mugen.renderer.libgdx.web', 'LGDXWebGameWindow/SprCmdProcessListenerAction', 171);
function openBinary_0(path){
  var e, fh;
  fh = $internal(files_0, toInternalPath(path));
  if (!$contains(fh.preloader, fh.file)) {
    throw toJs(new IOException('Asset not found: ' + path + ' -> ' + fh.file));
  }
  try {
    return new ByteArrayInputStream($readBytes(fh));
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 26)) {
      e = $e0;
      throw toJs(new IOException_0('Failed to read asset: ' + path + ' -> ' + fh.file, e));
    }
     else 
      throw toJs($e0);
  }
}

function toInternalPath(path){
  var p;
  if (path == null || path.length == 0) {
    return '';
  }
  p = $replace(path);
  while ($equals_5(p.substr(0, 2), './')) {
    p = (checkCriticalStringElementIndex(2, p.length + 1) , p.substr(2));
  }
  $equals_5(p.substr(0, 1), '/') && (p = (checkCriticalStringElementIndex(1, p.length + 1) , p.substr(1)));
  return p;
}

function LGDXWebGdxData$0methodref$openUtf8Reader$Type(){
}

defineClass(373, 1, {}, LGDXWebGdxData$0methodref$openUtf8Reader$Type);
var Lorg_lee_mugen_renderer_libgdx_web_LGDXWebGdxData$0methodref$openUtf8Reader$Type_2_classLit = createForClass('org.lee.mugen.renderer.libgdx.web', 'LGDXWebGdxData/0methodref$openUtf8Reader$Type', 373);
function LGDXWebGdxData$1methodref$openBinary$Type(){
}

defineClass(374, 1, {}, LGDXWebGdxData$1methodref$openBinary$Type);
var Lorg_lee_mugen_renderer_libgdx_web_LGDXWebGdxData$1methodref$openBinary$Type_2_classLit = createForClass('org.lee.mugen.renderer.libgdx.web', 'LGDXWebGdxData/1methodref$openBinary$Type', 374);
function $getImageContainer_0(imageData, colors){
  var e, h, pcxImage, pixmap, texture, w;
  if (instanceOf(imageData, 93)) {
    pcxImage = castTo(imageData, 93);
    try {
      pixmap = decode_0(pcxImage.buffer_0.backingArray, colors);
      w = pixmap.width_0;
      h = pixmap.height_0;
      texture = new Texture_0(pixmap);
      $remove_6(($clinit_Pixmap() , pixmaps), valueOf_1(pixmap.id_0));
      return new LGDXWebImageLoader$1(texture, w, h);
    }
     catch ($e0) {
      $e0 = toJava($e0);
      if (instanceOf($e0, 6)) {
        e = $e0;
        error_0('Web PCX decode failed: %s', stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_Object_2_classLit, 1), $intern_9, 1, 5, [e]));
        return null;
      }
       else 
        throw toJs($e0);
    }
  }
  error_0('Web image decode not implemented for type: %s', stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_Object_2_classLit, 1), $intern_9, 1, 5, [imageData != null?$getName(getClass__Ljava_lang_Class___devirtual$(imageData)):'null']));
  return null;
}

function LGDXWebImageLoader$1($anonymous0, $anonymous1, $anonymous2){
  ImageContainer.call(this, $anonymous0, $anonymous1, $anonymous2);
}

defineClass(394, 76, $intern_66, LGDXWebImageLoader$1);
var Lorg_lee_mugen_renderer_libgdx_web_LGDXWebImageLoader$1_2_classLit = createForClass('org.lee.mugen.renderer.libgdx.web', 'LGDXWebImageLoader/1', 394);
function prepare(){
  if (prepared) {
    return;
  }
  resourcePrefixOverride = 'data/';
  install(new LGDXWebGdxData$0methodref$openUtf8Reader$Type, new LGDXWebGdxData$1methodref$openBinary$Type);
  installDrawer((initialized || initialize() , !drawerInstance && (drawerInstance = new LGDXMugenDrawer(windowInstance)) , drawerInstance));
  prepared = true;
}

var prepared = false;
function initialize(){
  if (initialized) {
    $clinit_System();
    String.fromCharCode(10);
    return;
  }
  windowInstance = new LGDXWebGameWindow;
  initialized = true;
  $clinit_System();
  String.fromCharCode(10);
}

var drawerInstance, initialized = false, windowInstance;
function SffReader(in_0, useThisPal){
  var br, bytes, enter, fileData, fs, iRead, isForceUSeDefPal, len, next, prevPalette, subFileHead;
  this.SubFileList = new ArrayList;
  isForceUSeDefPal = useThisPal != null && useThisPal.length == 768;
  fileData = toByteArray(in_0);
  expectSignature(fileData);
  fs = new ByteArrayInputStream(fileData);
  br = new LittleEndianDataInputStream(fs);
  len = fileData.length;
  if (compare_0(len, 512) < 0) {
    throw toJs(new IOException('SFF file too small: ' + toString_29(len)));
  }
  new SffReader$SffHeader(br);
  fs.pos = fs.mark;
  $skip(fs, 512);
  br = new LittleEndianDataInputStream(fs);
  subFileHead = new SffReader$SubFileHeader(br);
  prevPalette = isForceUSeDefPal?useThisPal:initUnidimensionalArray(B_classLit, $intern_6, 5, 768, 15, 1);
  bytes = initUnidimensionalArray(B_classLit, $intern_6, 5, 128, 15, 1);
  if (subFileHead.subFileLen > 0) {
    checkCriticalNotNull(bytes);
    $read_2(br, bytes, 0, bytes.length);
    $write(subFileHead.pcxFile.pcxStream, bytes);
    iRead = sliceLength(subFileHead.nextPosition, len, fs);
    bytes = initUnidimensionalArray(B_classLit, $intern_6, 5, iRead, 15, 1);
    checkCriticalNotNull(bytes);
    $read_2(br, bytes, 0, bytes.length);
    isForceUSeDefPal && bytes.length >= 768 && arraycopy(prevPalette, 0, bytes, bytes.length - 768, 768);
    $write(subFileHead.pcxFile.pcxStream, bytes);
    bytes = $toByteArray(subFileHead.pcxFile.pcxStream);
    bytes.length >= 768 && arraycopy(bytes, bytes.length - 768, prevPalette, 0, 768);
  }
  useThisPal == null && (useThisPal = copyOf(prevPalette, prevPalette.length));
  next = subFileHead.nextPosition;
  enter = false;
  while (next != 0 && compare_0(next, len) < 0) {
    enter = true;
    $add_8(this.SubFileList, subFileHead);
    fs.pos = fs.mark;
    $skip(fs, next);
    br = new LittleEndianDataInputStream(fs);
    subFileHead = new SffReader$SubFileHeader(br);
    if (subFileHead.subFileLen > 0) {
      iRead = sliceLength(subFileHead.nextPosition, len, fs);
      bytes = initUnidimensionalArray(B_classLit, $intern_6, 5, iRead, 15, 1);
      checkCriticalNotNull(bytes);
      $read_2(br, bytes, 0, bytes.length);
      if (subFileHead.isSamePalAsPrev) {
        $write(subFileHead.pcxFile.pcxStream, bytes);
        $write(subFileHead.pcxFile.pcxStream, prevPalette);
      }
       else {
        isForceUSeDefPal && bytes.length >= 768 && arraycopy(prevPalette, 0, bytes, bytes.length - 768, 768);
        !isForceUSeDefPal && subFileHead.grpNumber != 9000 && bytes.length >= 768 && arraycopy(bytes, bytes.length - 768, prevPalette, 0, 768);
        $write(subFileHead.pcxFile.pcxStream, bytes);
      }
    }
     else 
      subFileHead.subFileLen == 0 && (subFileHead.pcxFile = null);
    next = subFileHead.nextPosition;
  }
  enter && $add_8(this.SubFileList, subFileHead);
}

function SffReader_0(filename){
  SffReader.call(this, openBinary(filename), null);
}

function expectSignature(fileData){
  var i, sig;
  if (fileData.length < 12) {
    throw toJs(new IOException('SFF file too small'));
  }
  sig = stampJavaTypeInfo(getClassLiteralForArray(B_classLit, 1), $intern_6, 5, 15, [69, 108, 101, 99, 98, 121, 116, 101, 83, 112, 114, 0]);
  for (i = 0; i < sig.length; i++) {
    if (fileData[i] !== sig[i]) {
      throw toJs(new IOException('Not a Mugen SFF (bad signature, ' + fileData.length + ' bytes)'));
    }
  }
}

function getImage(in_0, num, useThisPal){
  var SubFileList, br, bytes, enter, fileData, fs, iRead, isForceUSeDefPal, len, next, prevPalette, subFileHead;
  isForceUSeDefPal = useThisPal != null && useThisPal.length == 768;
  fileData = toByteArray(in_0);
  fs = new ByteArrayInputStream(fileData);
  br = new LittleEndianDataInputStream(fs);
  len = fileData.length;
  SubFileList = new ArrayList;
  new SffReader$SffHeader(br);
  fs.pos = fs.mark;
  $skip(fs, 512);
  subFileHead = new SffReader$SubFileHeader(br);
  prevPalette = isForceUSeDefPal?useThisPal:initUnidimensionalArray(B_classLit, $intern_6, 5, 768, 15, 1);
  bytes = initUnidimensionalArray(B_classLit, $intern_6, 5, 128, 15, 1);
  if (subFileHead.subFileLen > 0) {
    checkCriticalNotNull(bytes);
    $read_2(br, bytes, 0, bytes.length);
    $write(subFileHead.pcxFile.pcxStream, bytes);
    iRead = sliceLength(subFileHead.nextPosition, len, fs);
    bytes = initUnidimensionalArray(B_classLit, $intern_6, 5, iRead, 15, 1);
    checkCriticalNotNull(bytes);
    $read_2(br, bytes, 0, bytes.length);
    isForceUSeDefPal && arraycopy(prevPalette, 0, bytes, bytes.length - 768, 768);
    $write(subFileHead.pcxFile.pcxStream, bytes);
    if (9000 == subFileHead.grpNumber && num == subFileHead.imgNumber) {
      return $toByteArray(subFileHead.pcxFile.pcxStream);
    }
    bytes = $toByteArray(subFileHead.pcxFile.pcxStream);
    arraycopy(bytes, bytes.length - 768, prevPalette, 0, 768);
  }
  useThisPal == null && (useThisPal = copyOf(prevPalette, prevPalette.length));
  next = subFileHead.nextPosition;
  enter = false;
  while (next != 0 && compare_0(next, len) < 0) {
    enter = true;
    push_1(SubFileList.array, subFileHead);
    fs.pos = fs.mark;
    $skip(fs, next);
    subFileHead = new SffReader$SubFileHeader(br);
    if (subFileHead.subFileLen > 0) {
      iRead = sliceLength(subFileHead.nextPosition, len, fs);
      bytes = initUnidimensionalArray(B_classLit, $intern_6, 5, iRead, 15, 1);
      checkCriticalNotNull(bytes);
      $read_2(br, bytes, 0, bytes.length);
      if (subFileHead.isSamePalAsPrev) {
        $write(subFileHead.pcxFile.pcxStream, bytes);
        $write(subFileHead.pcxFile.pcxStream, prevPalette);
        if (9000 == subFileHead.grpNumber && num == subFileHead.imgNumber) {
          return $toByteArray(subFileHead.pcxFile.pcxStream);
        }
      }
       else {
        isForceUSeDefPal?arraycopy(prevPalette, 0, bytes, bytes.length - 768, 768):subFileHead.grpNumber != 9000 && arraycopy(bytes, bytes.length - 768, prevPalette, 0, 768);
        $write(subFileHead.pcxFile.pcxStream, bytes);
        if (9000 == subFileHead.grpNumber && num == subFileHead.imgNumber) {
          return $toByteArray(subFileHead.pcxFile.pcxStream);
        }
      }
    }
     else 
      subFileHead.subFileLen == 0 && (subFileHead.pcxFile = null);
    next = subFileHead.nextPosition;
  }
  enter && (push_1(SubFileList.array, subFileHead) , true);
  return null;
}

function sliceLength(nextPosition, len, fs){
  var iRead, read;
  read = toInt_0(sub_1(len, fs.count - fs.pos));
  iRead = nextPosition - read;
  checkChunkSize(iRead, toInt_0(len), 'SFF PCX slice');
  if (iRead < 0) {
    throw toJs(new IOException('Invalid SFF slice at offset ' + read + ': next=' + nextPosition + ' len=' + toString_29(len)));
  }
  return iRead;
}

defineClass(112, 1, {}, SffReader_0);
var Lorg_lee_mugen_sff_SffReader_2_classLit = createForClass('org.lee.mugen.sff', 'SffReader', 112);
function SffReader$PcxFile(){
  this.pcxStream = new ByteArrayOutputStream;
}

defineClass(469, 1, {}, SffReader$PcxFile);
var Lorg_lee_mugen_sff_SffReader$PcxFile_2_classLit = createForClass('org.lee.mugen.sff', 'SffReader/PcxFile', 469);
function SffReader$SffHeader(br){
  this.signature = initUnidimensionalArray(C_classLit, $intern_2, 5, 12, 15, 1);
  this.blank = initUnidimensionalArray(C_classLit, $intern_2, 5, 3, 15, 1);
  this.comment = initUnidimensionalArray(C_classLit, $intern_2, 5, 476, 15, 1);
  $readChars(br, this.signature);
  br.in_0.read();
  br.in_0.read();
  br.in_0.read();
  br.in_0.read();
  $readInt(br);
  $readInt(br);
  $readInt(br);
  $readInt(br);
  br.in_0.read();
  $readChars(br, this.blank);
  $readChars(br, this.comment);
}

defineClass(246, 1, {}, SffReader$SffHeader);
var Lorg_lee_mugen_sff_SffReader$SffHeader_2_classLit = createForClass('org.lee.mugen.sff', 'SffReader/SffHeader', 246);
function SffReader$SubFileHeader(br){
  this.comment = initUnidimensionalArray(C_classLit, $intern_2, 5, 13, 15, 1);
  this.pcxFile = new SffReader$PcxFile;
  this.nextPosition = $readInt(br);
  this.subFileLen = $readInt(br);
  this.xAxis = $readShort(br);
  this.yAxis = $readShort(br);
  this.grpNumber = $readShort(br);
  this.imgNumber = $readShort(br);
  this.indexPreviousCopySprite = $readShort(br);
  this.isSamePalAsPrev = $readByte(br.din) != 0;
  $readChars(br, this.comment);
}

defineClass(113, 1, {113:1}, SffReader$SubFileHeader);
_.grpNumber = 0;
_.imgNumber = 0;
_.indexPreviousCopySprite = 0;
_.isSamePalAsPrev = false;
_.nextPosition = 0;
_.subFileLen = 0;
_.xAxis = 0;
_.yAxis = 0;
var Lorg_lee_mugen_sff_SffReader$SubFileHeader_2_classLit = createForClass('org.lee.mugen.sff', 'SffReader/SubFileHeader', 113);
function $getSound(this$static, key){
  return castTo($get_8(this$static._soundMap, valueOf_1(key)), 21);
}

function GroupSnd(){
  this._soundMap = new HashMap;
}

defineClass(138, 1, {3:1, 138:1}, GroupSnd);
var Lorg_lee_mugen_snd_GroupSnd_2_classLit = createForClass('org.lee.mugen.snd', 'GroupSnd', 138);
function $addSound(this$static, grp, sample, data_0){
  var groupSnd;
  groupSnd = castTo($get_8(this$static._grpSoundMap, valueOf_1(grp)), 138);
  if (!groupSnd) {
    groupSnd = new GroupSnd;
    $put_4(this$static._grpSoundMap, valueOf_1(grp), groupSnd);
  }
  $put_4(groupSnd._soundMap, valueOf_1(sample), data_0);
  ++this$static.count;
}

function $getGroup(this$static, key){
  return castTo($get_8(this$static._grpSoundMap, valueOf_1(key)), 138);
}

function Snd(){
  this._grpSoundMap = new HashMap;
}

defineClass(470, 1, $intern_2, Snd);
_.count = 0;
var Lorg_lee_mugen_snd_Snd_2_classLit = createForClass('org.lee.mugen.snd', 'Snd', 470);
function parse_19(in_0){
  var count, data_0, dis, fileData, fileLength, fs, grpNumber, i, length_0, offset, sampleNumber, snd, sndSig, soundCount, temp;
  fileData = toByteArray(in_0);
  if (fileData.length < 512) {
    throw toJs(new IOException('SND file too small: ' + fileData.length));
  }
  sndSig = stampJavaTypeInfo(getClassLiteralForArray(B_classLit, 1), $intern_6, 5, 15, [69, 108, 101, 99, 98, 121, 116, 101, 83, 110, 100, 0]);
  for (i = 0; i < sndSig.length; i++) {
    if (fileData[i] !== sndSig[i]) {
      throw toJs(new IOException('Not a Mugen SND (bad signature, ' + fileData.length + ' bytes)'));
    }
  }
  fs = new ByteArrayInputStream(fileData);
  dis = new LittleEndianDataInputStream(fs);
  snd = new Snd;
  fileLength = fileData.length;
  temp = initUnidimensionalArray(B_classLit, $intern_6, 5, 12, 15, 1);
  checkCriticalNotNull(temp);
  $read_2(dis, temp, 0, temp.length);
  $read_0(dis, temp = initUnidimensionalArray(B_classLit, $intern_6, 5, 4, 15, 1));
  soundCount = $readInt(dis);
  offset = $readInt(dis);
  $read_0(dis, temp = initUnidimensionalArray(B_classLit, $intern_6, 5, 488, 15, 1));
  temp = null;
  count = 0;
  while (compare_0(offset, 0) > 0) {
    ++count;
    if (count > soundCount)
      break;
    fs.pos = fs.mark;
    $skip(fs, offset);
    offset = $readInt(dis);
    if (compare_0(offset, -1) == 0)
      break;
    length_0 = $readInt(dis);
    grpNumber = $readInt(dis);
    sampleNumber = $readInt(dis);
    checkChunkSize(length_0, fileLength, 'SND sample');
    data_0 = initUnidimensionalArray(B_classLit, $intern_6, 5, length_0, 15, 1);
    checkCriticalNotNull(data_0);
    $read_2(dis, data_0, 0, data_0.length);
    $addSound(snd, grpNumber, sampleNumber, data_0);
  }
  return snd;
}

function $$init_3(this$static){
  this$static._drawProperties = new AbstractAnimManager$SpriteDrawProperties;
}

function $build(this$static, airParser){
  var airGrp, airGrp$iterator, entry, grpSprite, outerIter;
  this$static.groupSpriteMap = new HashMap;
  for (airGrp$iterator = (outerIter = (new AbstractMap$2(airParser.airGroupMap)).this$01.entrySet().iterator() , new AbstractMap$2$1(outerIter)); airGrp$iterator.val$outerIter2.hasNext_0();) {
    airGrp = (entry = castTo(airGrp$iterator.val$outerIter2.next_1(), 43) , castTo(entry.getValue(), 194));
    grpSprite = new AnimGroup(airGrp);
    $containsKey(this$static.groupSpriteMap, valueOf_1(airGrp.action)) || $put_4(this$static.groupSpriteMap, valueOf_1(airGrp.action), grpSprite);
  }
}

function $getAnimTimeCount(this$static){
  var ae, ae$array, ae$index, ae$max, animeElems, count, grpSrp;
  grpSrp = $getCurrentGroupSprite(this$static, this$static._action);
  animeElems = grpSrp._ImgSprArray;
  count = 0;
  for (ae$array = animeElems , ae$index = 0 , ae$max = ae$array.length; ae$index < ae$max; ++ae$index) {
    ae = ae$array[ae$index];
    count += ae._airData.delayTick == -1?1:ae._airData.delayTick;
  }
  return count;
}

function $getAnimTimeCount_0(this$static, img){
  var ae, ae$array, ae$index, ae$max, animeElems, count;
  if (img == 0)
    return 0;
  if (img == -1)
    return 0;
  animeElems = $getCurrentGroupSprite(this$static, this$static._action)._ImgSprArray;
  count = 0;
  for (ae$array = animeElems , ae$index = 0 , ae$max = ae$array.length; ae$index < ae$max; ++ae$index) {
    ae = ae$array[ae$index];
    --img;
    if (img < 0)
      return count;
    count += ae._airData.delayTick == -1?1:ae._airData.delayTick;
  }
  return count;
}

function $getAnimTimeReal(this$static){
  var loop;
  loop = $getCurrentGroupSprite(this$static, this$static._action)._imgLoopStart;
  loop = -$getAnimTimeCount_0(this$static, loop) + $getAnimTimeCount(this$static) - 1;
  return this$static._animElemTimeCount - loop;
}

function $getAnimeTimeCount(this$static, animElem){
  var animTime, i, imgSpr;
  animTime = 0;
  for (i = 0; i < animElem; i++) {
    imgSpr = $getCurrentGroupSprite(this$static, this$static._action)._ImgSprArray[i];
    animTime += imgSpr._airData.delayTick == -1?1:imgSpr._airData.delayTick;
  }
  return animTime;
}

function $getCurrentGroupSprite(this$static, action){
  return castTo($get_8(this$static.groupSpriteMap, valueOf_1(action)), 154);
}

function $getCurrentImageSprite(this$static){
  var ag, elem, imgSpr, index_0;
  imgSpr = null;
  ag = null;
  try {
    ag = $getCurrentGroupSprite(this$static, this$static._action);
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 6)) {
      $clinit_System();
      String.fromCharCode(10);
    }
     else 
      throw toJs($e0);
  }
  if (!ag)
    return null;
  elem = $getCurrentGroupSprite(this$static, this$static._action)._ImgSprArray;
  index_0 = this$static._animElem > elem.length - 1?elem.length - 1:this$static._animElem;
  imgSpr = elem[index_0];
  return imgSpr;
}

function $process_6(this$static){
  var grpSrp;
  if (this$static.changeToNewAnimElem) {
    this$static._animElem = this$static.animElemeTochange;
    this$static.changeToNewAnimElem = false;
    this$static._imgCount = 0;
  }
  if (this$static._action < 0)
    return;
  if (!$getCurrentGroupSprite(this$static, this$static._action))
    return;
  this$static.animTime = $getAnimTimeReal(this$static);
  grpSrp = null;
  try {
    grpSrp = $getCurrentGroupSprite(this$static, this$static._action);
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 6)) {
      $clinit_System();
      String.fromCharCode(10);
    }
     else 
      throw toJs($e0);
  }
  if (!grpSrp)
    return;
  if ($getCurrentImageSprite(this$static)._airData.delayTick == -1) {
    --this$static._animElemTimeCount;
    ++this$static._animElemTime;
    ++this$static._imgCount;
  }
   else if (this$static._imgCount >= $getCurrentImageSprite(this$static)._airData.delayTick - 1) {
    ++this$static._animElem;
    if (this$static._animElem > grpSrp._ImgSprArray.length - 1) {
      if (grpSrp._imgLoopStart != -1) {
        this$static._animElem = grpSrp._imgLoopStart;
        this$static._animElem = this$static._animElem < 0?0:this$static._animElem;
        this$static._animElemTime = $getAnimTimeCount_0(this$static, this$static._animElem);
        this$static._animElemTimeCount = $getAnimTimeCount_0(this$static, this$static._animElem);
      }
       else {
        this$static.animElemeTochange = 0;
        this$static.changeToNewAnimElem = true;
        this$static._animElemTime = 0;
        this$static._animElemTimeCount = 0;
        --this$static._animElem;
      }
    }
     else {
      this$static._imgCount = 0;
      ++this$static._animElemTimeCount;
    }
  }
   else {
    ++this$static._imgCount;
    ++this$static._animElemTime;
    ++this$static._animElemTimeCount;
  }
  $deactivateNext(this$static._drawProperties);
  this$static.lastAction = this$static._action;
}

function $setAction_0(this$static, value_0){
  if (value_0 == this$static._action)
    return;
  !castTo($get_8(this$static.groupSpriteMap, valueOf_1(value_0)), 154) && value_0 != -1;
  this$static.lastAction != this$static._action && (this$static.lastAction = this$static._action);
  this$static._action = value_0;
  this$static._animElem = 0;
  this$static._imgCount = 0;
  this$static._animElemTime = 0;
  this$static._animElemTimeCount = 0;
  this$static.animTime = $getAnimTimeReal(this$static);
}

function $setAnimElem_0(this$static, value_0){
  this$static._animElem = value_0;
  this$static._imgCount = 0;
  this$static._animElemTime = $getAnimeTimeCount(this$static, value_0);
  this$static._animElemTimeCount = $getAnimTimeCount_0(this$static, this$static._animElem);
  this$static.animTime = $getAnimTimeReal(this$static);
}

function AbstractAnimManager(groupSpriteMap){
  $$init_3(this);
  this.groupSpriteMap = groupSpriteMap;
}

function AbstractAnimManager_0(airParser){
  $$init_3(this);
  $build(this, airParser);
}

defineClass(191, 1, $intern_2, AbstractAnimManager, AbstractAnimManager_0);
_._action = 0;
_._animElem = 0;
_._animElemTime = 0;
_._animElemTimeCount = 0;
_._imgCount = 0;
_.animElemeTochange = 0;
_.animTime = 0;
_.changeToNewAnimElem = false;
_.lastAction = 0;
var Lorg_lee_mugen_sprite_base_AbstractAnimManager_2_classLit = createForClass('org.lee.mugen.sprite.base', 'AbstractAnimManager', 191);
function $deactivateNext(this$static){
  this$static.nextSetDrawPropertiesToFalse?(this$static.nextSetDrawPropertiesToFalse = false):(this$static.nextSetDrawPropertiesToFalse = true);
}

function AbstractAnimManager$SpriteDrawProperties(){
}

defineClass(450, 1, $intern_2, AbstractAnimManager$SpriteDrawProperties);
_.nextSetDrawPropertiesToFalse = false;
var Lorg_lee_mugen_sprite_base_AbstractAnimManager$SpriteDrawProperties_2_classLit = createForClass('org.lee.mugen.sprite.base', 'AbstractAnimManager/SpriteDrawProperties', 450);
function $add_14(this$static, key, imgSpr){
  if ($containsKey(this$static._imageMap, valueOf_1(key)))
    return;
  $put_4(this$static._imageMap, valueOf_1(key), imgSpr);
}

function $getImgSpr(this$static, key){
  return castTo($get_8(this$static._imageMap, valueOf_1(key)), 115);
}

function GroupSpriteSFF(){
  this._imageMap = new HashMap;
}

defineClass(51, 1, {3:1, 51:1}, GroupSpriteSFF);
var Lorg_lee_mugen_sprite_baseForParse_GroupSpriteSFF_2_classLit = createForClass('org.lee.mugen.sprite.baseForParse', 'GroupSpriteSFF', 51);
function ImageSpriteSFF(grpNum, imgNum, pcx, xAxis, yAxis){
  this._xAxis = xAxis;
  this._yAxis = yAxis;
  this._image = $getImageContainer(getInstance_0(), pcx, 0);
  if (!this._image) {
    throw toJs(new IOException('PCX image could not be decoded (grp=' + grpNum + ' img=' + imgNum + ')'));
  }
  new Rectangle_1(0, 0, this._image.width_0, this._image.height_0);
}

function ImageSpriteSFF_0(image, xAxis, yAxis){
  this._xAxis = xAxis;
  this._yAxis = yAxis;
  this._image = image;
  if (!this._image) {
    throw toJs(new IllegalArgumentException_0('image'));
  }
  new Rectangle_1(0, 0, this._image.width_0, this._image.height_0);
}

defineClass(115, 1, {3:1, 115:1}, ImageSpriteSFF, ImageSpriteSFF_0);
_._xAxis = 0;
_._yAxis = 0;
var Lorg_lee_mugen_sprite_baseForParse_ImageSpriteSFF_2_classLit = createForClass('org.lee.mugen.sprite.baseForParse', 'ImageSpriteSFF', 115);
function $getGroupSpr(this$static, key){
  return castTo($get_8(this$static._groupMap, valueOf_1(key)), 51);
}

function SpriteSFF(sffReader, useBufferedImage){
  var bitmap, copyFrom, countImage, grpSpr, header, height, imgContainer, imgSpr, imgSprList, memStream, rawPCXImage, subFile, subFile$iterator, time, time0, timeToLoadPcx, timeToLoadTExture, width_0;
  this._groupMap = new HashMap;
  new PCXPalette;
  imgSprList = new ArrayList;
  timeToLoadPcx = 0;
  timeToLoadTExture = 0;
  countImage = 0;
  log_0('Enter to process SFF File', stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_Object_2_classLit, 1), $intern_9, 1, 5, []));
  for (subFile$iterator = new ArrayList$1(sffReader.SubFileList); subFile$iterator.i < subFile$iterator.this$01.array.length;) {
    subFile = castTo($next_7(subFile$iterator), 113);
    ++countImage;
    grpSpr = $getGroupSpr(this, subFile.grpNumber);
    if (!grpSpr) {
      grpSpr = new GroupSpriteSFF;
      $put_4(this._groupMap, valueOf_1(subFile.grpNumber), grpSpr);
    }
    if (subFile.subFileLen > 0) {
      memStream = subFile.pcxFile.pcxStream;
      time0 = ($clinit_System() , fromDouble_0(Date.now()));
      rawPCXImage = new RawPCXImage($toByteArray(memStream));
      bitmap = rawPCXImage;
      time0 = sub_1(fromDouble_0(Date.now()), time0);
      timeToLoadPcx = add_2(timeToLoadPcx, time0);
    }
     else {
      copyFrom = subFile.indexPreviousCopySprite;
      if (copyFrom < 0 || copyFrom >= imgSprList.array.length) {
        throw toJs(new IOException('SFF linked sprite index ' + copyFrom + ' out of range (have ' + imgSprList.array.length + ')'));
      }
      bitmap = (checkCriticalElementIndex(copyFrom, imgSprList.array.length) , castTo(imgSprList.array[copyFrom], 93));
    }
    push_1(imgSprList.array, bitmap);
    time = ($clinit_System() , fromDouble_0(Date.now()));
    if (useBufferedImage) {
      memStream = subFile.pcxFile.pcxStream;
      header = new PCXHeader($toByteArray(memStream));
      width_0 = header.xmax - header.xmin + 1;
      height = header.ymax - header.ymin + 1;
      imgContainer = new DeferedImageLoader(bitmap, width_0, height);
      imgSpr = new ImageSpriteSFF_0(imgContainer, subFile.xAxis, subFile.yAxis);
    }
     else {
      imgSpr = new ImageSpriteSFF(subFile.grpNumber, subFile.imgNumber, bitmap, subFile.xAxis, subFile.yAxis);
    }
    time = sub_1(fromDouble_0(Date.now()), time);
    timeToLoadTExture = add_2(timeToLoadTExture, time);
    $add_14(grpSpr, subFile.imgNumber, imgSpr);
  }
  log_0('End process SFF File', stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_Object_2_classLit, 1), $intern_9, 1, 5, []));
  log_0('Number of image = ' + countImage, stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_Object_2_classLit, 1), $intern_9, 1, 5, []));
  log_0('Time to load PCX = ' + toString_29(timeToLoadPcx), stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_Object_2_classLit, 1), $intern_9, 1, 5, []));
  log_0('Time to load PCX = ' + toString_29(timeToLoadTExture), stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_Object_2_classLit, 1), $intern_9, 1, 5, []));
}

defineClass(130, 1, {}, SpriteSFF);
var Lorg_lee_mugen_sprite_baseForParse_SpriteSFF_2_classLit = createForClass('org.lee.mugen.sprite.baseForParse', 'SpriteSFF', 130);
function AnimElement(airData){
  this._airData = airData;
}

defineClass(196, 1, {3:1, 196:1}, AnimElement);
var Lorg_lee_mugen_sprite_character_AnimElement_2_classLit = createForClass('org.lee.mugen.sprite.character', 'AnimElement', 196);
function AnimGroup(airGrp){
  var ImgSprList, airData, airData$iterator, imgSprInfo;
  this._imgLoopStart = airGrp.loopStart;
  ImgSprList = new ArrayList;
  for (airData$iterator = new ArrayList$1(airGrp.airDataList); airData$iterator.i < airData$iterator.this$01.array.length;) {
    airData = castTo($next_7(airData$iterator), 201);
    imgSprInfo = new AnimElement(airData);
    push_1(ImgSprList.array, imgSprInfo);
  }
  this._ImgSprArray = castTo($toArray_0(ImgSprList, initUnidimensionalArray(Lorg_lee_mugen_sprite_character_AnimElement_2_classLit, {3:1, 4:1, 557:1}, 196, 0, 0, 1)), 557);
}

defineClass(154, 1, {3:1, 154:1}, AnimGroup);
_._imgLoopStart = 0;
var Lorg_lee_mugen_sprite_character_AnimGroup_2_classLit = createForClass('org.lee.mugen.sprite.character', 'AnimGroup', 154);
function SpriteAnimManager(airParser){
  $$init_3(this);
  this.groupSpriteMap = new HashMap;
  this.groupSpriteMap = new HashMap;
  $build(this, airParser);
}

defineClass(262, 191, $intern_2, SpriteAnimManager);
var Lorg_lee_mugen_sprite_character_SpriteAnimManager_2_classLit = createForClass('org.lee.mugen.sprite.character', 'SpriteAnimManager', 262);
function Desc(x_0, width_0){
  this.x_0 = x_0;
  this.width_0 = width_0;
}

defineClass(103, 1, {103:1}, Desc);
_.width_0 = 0;
_.x_0 = 0;
var Lorg_lee_mugen_sprite_common_resource_Desc_2_classLit = createForClass('org.lee.mugen.sprite.common.resource', 'Desc', 103);
function $clinit_FontParser(){
  $clinit_FontParser = emptyMethod;
  SIGNATURE = stampJavaTypeInfo(getClassLiteralForArray(B_classLit, 1), $intern_6, 5, 15, [69, 108, 101, 99, 98, 121, 116, 101, 70, 110, 116, 0]);
}

function parse_20(fileData){
  $clinit_FontParser();
  var c, count, decode, fileLength, fontProducer, grp, grp$iterator, grpsText, i, lastArg, lastArg0, line, offsetPcx, offsetText, pcxBytes, pcxImage, pcxLength, strColors, strToken, strType, strsOffset, strsSize, strsSpacing, text_0, textBytes, textLength, tokens;
  fileLength = fileData.length;
  if (fileLength < 32) {
    throw toJs(new IOException('Font file too small (' + fileLength + ' bytes)'));
  }
  for (i = 0; i < SIGNATURE.length; i++) {
    if (fileData[i] !== SIGNATURE[i]) {
      throw toJs(new IOException('Not a Mugen font (missing ElecbyteFnt signature)'));
    }
  }
  offsetPcx = fileData[16] & 255 | (fileData[17] & 255) << 8 | (fileData[18] & 255) << 16 | (fileData[19] & 255) << 24;
  pcxLength = fileData[20] & 255 | (fileData[21] & 255) << 8 | (fileData[22] & 255) << 16 | (fileData[23] & 255) << 24;
  offsetText = fileData[24] & 255 | (fileData[25] & 255) << 8 | (fileData[26] & 255) << 16 | (fileData[27] & 255) << 24;
  textLength = fileData[28] & 255 | (fileData[29] & 255) << 8 | (fileData[30] & 255) << 16 | (fileData[31] & 255) << 24;
  checkRange(offsetPcx, pcxLength, fileLength, 'font PCX');
  checkRange(offsetText, textLength, fileLength, 'font text');
  pcxBytes = (checkCopyOfRange(fileData, offsetPcx, offsetPcx + pcxLength) , copyPrimitiveArray(fileData, initUnidimensionalArray(B_classLit, $intern_6, 5, offsetPcx + pcxLength - offsetPcx, 15, 1), offsetPcx, offsetPcx + pcxLength));
  textBytes = (checkCopyOfRange(fileData, offsetText, offsetText + textLength) , copyPrimitiveArray(fileData, initUnidimensionalArray(B_classLit, $intern_6, 5, offsetText + textLength - offsetText, 15, 1), offsetText, offsetText + textLength));
  fontProducer = new FontProducer;
  text_0 = valueOf_3($decodeString(textBytes, (lastArg0 = textBytes.length , $clinit_EmulatedCharset() , lastArg0)));
  grpsText = ($clinit_Parser() , getGroupTextMap(new StringReader(text_0), true));
  for (grp$iterator = new ArrayList$1(grpsText); grp$iterator.i < grp$iterator.this$01.array.length;) {
    grp = castTo($next_7(grp$iterator), 90);
    if ($equalsIgnoreCase(grp.section, 'def')) {
      strsSize = $split($replaceAll(castToString($getStringValue(grp.keyValues, 'size')), ' ', ''), ',');
      $setSize_0(fontProducer, new MugenPoint_0(__parseAndValidateInt(strsSize[0], 10), __parseAndValidateInt(strsSize[1], 10)));
      strsSpacing = $split($replaceAll(castToString($getStringValue(grp.keyValues, 'spacing')), ' ', ''), ',');
      $setSpacing(fontProducer, new MugenPoint_0(__parseAndValidateInt(strsSpacing[0], 10), strsSpacing.length > 1?__parseAndValidateInt(strsSpacing[1], 10):0));
      strColors = castToString($getStringValue(grp.keyValues, 'colors'));
      $setColors(fontProducer, __parseAndValidateInt(strColors, 10));
      strsOffset = $split($replaceAll(castToString($getStringValue(grp.keyValues, 'offset')), ' ', ''), ',');
      new MugenPoint_0(__parseAndValidateInt(strsOffset[0], 10), __parseAndValidateInt(strsOffset[1], 10));
      strType = castToString($getStringValue(grp.keyValues, 'type'));
      fontProducer.type_0 = strType;
    }
     else if ($equalsIgnoreCase(grp.section, 'map')) {
      strToken = new StringTokenizer(grp.text_0.string);
      count = 0;
      while ($hasMoreTokens(strToken)) {
        line = $nextToken(strToken);
        tokens = $split($replaceAll(line, '( )|(\t)|(  )', ' '), ' ');
        c = 0;
        if (tokens[0].length > 0 && tokens[0].length > 1) {
          try {
            c = valueOf_1((decode = __decodeNumberString(tokens[0]) , __parseAndValidateInt(decode.payload, decode.radix))).value_0 & $intern_5;
          }
           catch ($e0) {
            $e0 = toJava($e0);
            if (instanceOf($e0, 6)) {
              c = $toCharArray(tokens[0])[0];
            }
             else 
              throw toJs($e0);
          }
        }
         else 
          tokens[0].length > 0 && (c = $charAt(tokens[0], 0));
        if ($equalsIgnoreCase(fontProducer.type_0, 'Fixed')) {
          $put_4(fontProducer.map_0, valueOf_0(c), new Desc((count + (count > 0?fontProducer.spacing.x_0:0)) * fontProducer.size_0.x_0, fontProducer.size_0.x_0));
        }
         else if ($equalsIgnoreCase(fontProducer.type_0, 'Variable')) {
          $put_4(fontProducer.map_0, valueOf_0(c), new Desc(__parseAndValidateInt(tokens[1], 10), __parseAndValidateInt(tokens[2], 10)));
        }
         else {
          throw toJs(new IllegalArgumentException_0('Unknow Type'));
        }
        ++count;
      }
    }
  }
  pcxImage = new RawPCXImage((lastArg = pcxBytes , new PCXPalette , lastArg));
  fontProducer.image = pcxImage;
  return fontProducer;
}

var SIGNATURE;
function $computeString(this$static, line){
  var c, c$array, c$index, c$max, desc, len;
  len = 0;
  for (c$array = $toCharArray(line) , c$index = 0 , c$max = c$array.length; c$index < c$max; ++c$index) {
    c = c$array[c$index];
    desc = castTo($get_8(this$static.map_0, valueOf_0(c)), 103);
    desc?(len += desc.width_0):(len += this$static.size_0.x_0);
    len += this$static.spacing.x_0;
  }
  return len;
}

function $draw_2(this$static, bankno, xpos, ypos, md, s, sens, alpha_0){
  sens > 0?$drawLeftToRight(this$static, xpos, ypos, md, s, bankno, alpha_0):sens < 0?$drawRightToLeft(this$static, xpos, ypos, md, s, bankno, alpha_0):$drawCenter(this$static, xpos, ypos, md, s, bankno, alpha_0);
}

function $drawCenter(this$static, x_0, y_0, md, s, bankno, alpha_0){
  var c, c$array, c$index, c$max, dp, line, line$array, line$index, line$max, lines, pt, str, width_0, xposTemp;
  s == null && (s = '');
  lines = $split(s, '\n');
  width_0 = $getMaxWidth(this$static, lines);
  x_0 -= width_0 / 2 | 0;
  for (line$array = lines , line$index = 0 , line$max = line$array.length; line$index < line$max; ++line$index) {
    line = line$array[line$index];
    str = $toCharArray(line);
    xposTemp = x_0;
    for (c$array = str , c$index = 0 , c$max = c$array.length; c$index < c$max; ++c$index) {
      c = c$array[c$index];
      pt = castTo($get_8(this$static.map_0, valueOf_0(c)), 103);
      if (!pt) {
        xposTemp += this$static.size_0.x_0 + this$static.spacing.x_0;
        continue;
      }
      dp = new DrawProperties(xposTemp, xposTemp + pt.width_0, y_0, y_0 - this$static.size_0.y_0, pt.x_0, pt.x_0 + pt.width_0, this$static.size_0.y_0, 0, $getImageByBankno(this$static, bankno));
      dp.alpha_0 = alpha_0;
      $draw_1(md, dp);
      xposTemp += pt.width_0 + this$static.spacing.x_0;
    }
    y_0 += this$static.size_0.y_0 + this$static.spacing.y_0;
  }
}

function $drawLeftToRight(this$static, x_0, y_0, md, s, bankno, alpha_0){
  var c, c$array, c$index, c$max, dp, line, line$array, line$index, line$max, lines, pt, str, xposTemp;
  lines = $split(s, '\n');
  $getMaxWidth(this$static, lines);
  for (line$array = lines , line$index = 0 , line$max = line$array.length; line$index < line$max; ++line$index) {
    line = line$array[line$index];
    str = $toCharArray(line);
    xposTemp = x_0;
    for (c$array = str , c$index = 0 , c$max = c$array.length; c$index < c$max; ++c$index) {
      c = c$array[c$index];
      pt = castTo($get_8(this$static.map_0, valueOf_0(c)), 103);
      if (!pt) {
        xposTemp += this$static.size_0.x_0 + this$static.spacing.x_0;
        continue;
      }
      dp = new DrawProperties(xposTemp, xposTemp + pt.width_0, y_0, y_0 - this$static.size_0.y_0, pt.x_0, pt.x_0 + pt.width_0, this$static.size_0.y_0, 0, $getImageByBankno(this$static, bankno));
      dp.alpha_0 = alpha_0;
      $draw_1(md, dp);
      xposTemp += pt.width_0 + this$static.spacing.x_0;
    }
    y_0 += this$static.size_0.y_0 + this$static.spacing.y_0;
  }
}

function $drawRightToLeft(this$static, x_0, y_0, md, s, bankno, alpha_0){
  var c, c$array, c$index, c$max, dp, line, line$array, line$index, line$max, lines, pt, str, width_0, xposTemp;
  lines = $split(s, '\n');
  width_0 = $getMaxWidth(this$static, lines);
  x_0 -= width_0;
  for (line$array = lines , line$index = 0 , line$max = line$array.length; line$index < line$max; ++line$index) {
    line = line$array[line$index];
    str = $toCharArray(line);
    xposTemp = x_0;
    for (c$array = str , c$index = 0 , c$max = c$array.length; c$index < c$max; ++c$index) {
      c = c$array[c$index];
      pt = castTo($get_8(this$static.map_0, valueOf_0(c)), 103);
      if (!pt) {
        xposTemp += this$static.size_0.x_0 + this$static.spacing.x_0;
        continue;
      }
      dp = new DrawProperties(xposTemp, xposTemp + pt.width_0, y_0, y_0 - this$static.size_0.y_0, pt.x_0, pt.x_0 + pt.width_0, this$static.size_0.y_0, 0, $getImageByBankno(this$static, bankno));
      dp.alpha_0 = alpha_0;
      $draw_1(md, dp);
      xposTemp += pt.width_0 + this$static.spacing.x_0;
    }
    y_0 += this$static.size_0.y_0 + this$static.spacing.y_0;
  }
}

function $getImageByBankno(this$static, bankno){
  var ic;
  ic = castTo($get_8(this$static.fontBankMap, valueOf_1(bankno)), 76);
  if (!ic) {
    ic = $getImageContainer(getInstance_0(), this$static.image, this$static.colors * bankno - (this$static.colors * bankno > 0?0:0));
    $put_4(this$static.fontBankMap, valueOf_1(bankno), ic);
  }
  return ic;
}

function $getMaxWidth(this$static, lines){
  var line, line$array, line$index, line$max, max_0;
  max_0 = 0;
  for (line$array = lines , line$index = 0 , line$max = line$array.length; line$index < line$max; ++line$index) {
    line = line$array[line$index];
    max_0 < line.length && (max_0 = $computeString(this$static, line));
  }
  return max_0;
}

function $setColors(this$static, colors){
  this$static.colors = colors;
}

function $setSize_0(this$static, size_0){
  this$static.size_0 = size_0;
}

function $setSpacing(this$static, spacing){
  this$static.spacing = spacing;
}

function FontProducer(){
  this.fontBankMap = new HashMap;
  this.map_0 = new HashMap;
}

defineClass(114, 1, {114:1}, FontProducer);
_.colors = 0;
var Lorg_lee_mugen_sprite_common_resource_FontProducer_2_classLit = createForClass('org.lee.mugen.sprite.common.resource', 'FontProducer', 114);
function $addX(this$static, x2){
  this$static.x_0 += x2;
  this$static.xSetted = true;
}

function $addY(this$static, y2){
  this$static.y_0 += y2;
  this$static.ySetted = true;
}

function $copy_6(this$static){
  var p;
  p = new PointF;
  p.x_0 = this$static.x_0;
  p.y_0 = this$static.y_0;
  p.z_0 = this$static.z_0;
  p.xSetted = this$static.xSetted;
  p.ySetted = this$static.ySetted;
  p.zSetted = this$static.zSetted;
  return p;
}

function $setX(this$static, x_0){
  this$static.x_0 = x_0;
  this$static.xSetted = true;
}

function $setY(this$static, y_0){
  this$static.y_0 = y_0;
  this$static.ySetted = true;
}

function PointF(){
}

function PointF_0(x_0, y_0){
  this.x_0 = x_0;
  this.y_0 = y_0;
}

function PointF_1(pt){
  this.x_0 = pt.x_0;
  this.y_0 = pt.y_0;
  this.xSetted = true;
  this.ySetted = true;
}

defineClass(29, 1, {3:1, 29:1}, PointF, PointF_0, PointF_1);
_.x_0 = 0;
_.xSetted = false;
_.y_0 = 0;
_.ySetted = false;
_.z_0 = 0;
_.zSetted = false;
var Lorg_lee_mugen_sprite_entity_PointF_2_classLit = createForClass('org.lee.mugen.sprite.entity', 'PointF', 29);
function $setSnd_grp(this$static, snd_grp){
  this$static.snd_grp = snd_grp;
}

function $setSnd_item(this$static, snd_item){
  this$static.snd_item = snd_item;
}

function SndGrpNum(){
}

defineClass(129, 1, {3:1, 129:1}, SndGrpNum);
_.snd_grp = -1;
_.snd_item = 0;
var Lorg_lee_mugen_sprite_entity_SndGrpNum_2_classLit = createForClass('org.lee.mugen.sprite.entity', 'SndGrpNum', 129);
function SprGrpNum(){
}

function SprGrpNum_0(spritegrp, spriteno){
  this.spritegrp = spritegrp;
  this.spriteno = spriteno;
}

defineClass(86, 1, {3:1, 86:1}, SprGrpNum, SprGrpNum_0);
_.spritegrp = 0;
_.spriteno = 0;
var Lorg_lee_mugen_sprite_entity_SprGrpNum_2_classLit = createForClass('org.lee.mugen.sprite.entity', 'SprGrpNum', 86);
function $clinit_Parser(){
  $clinit_Parser = emptyMethod;
  P_COMMENT_OR_EMPTY_REGEX_0 = new Pattern('^\\s*;.*$|^\\s*$');
  new Pattern('(?:(?:\\s*;.*$)|(?:\\s*$))');
  P_SECTION_REGEX = new Pattern('^\\s*\\[(.*)\\](?:(?:\\s*;.*$)|(?:\\s*$))');
}

function existsViaDataStreams(path){
  try {
    openBinary(path);
    return true;
  }
   catch ($e0) {
    $e0 = toJava($e0);
    if (instanceOf($e0, 22)) {
      return false;
    }
     else 
      throw toJs($e0);
  }
}

function getExistFile(currentDir, filename){
  $clinit_Parser();
  var base, base$array, base$index, base$max, candidate, path, path0, path1, result;
  candidate = joinResourcePath($getPath(currentDir), filename);
  if (existsViaDataStreams(candidate)) {
    return candidate;
  }
  result = new File_0(currentDir, filename);
  if ($getItem(($clinit_File() , LocalStorage), (path0 = $getPath($getAbsoluteFile($getCanonicalFile(result))) , path0.length == 0?'/':path0)) != null) {
    return path = $getPath($getAbsoluteFile(result)) , path.length == 0?'/':path;
  }
  for (base$array = stampJavaTypeInfo(getClassLiteralForArray(Ljava_io_File_2_classLit, 1), $intern_9, 28, 0, [new File_1('.'), new File_1('resource'), new File_1(($clinit_JMugenConstant() , RESOURCE) + 'data'), new File_1(RESOURCE), new File_1('data')]) , base$index = 0 , base$max = base$array.length; base$index < base$max; ++base$index) {
    base = base$array[base$index];
    result = new File_0(base, filename);
    if ($getItem(LocalStorage, (path1 = $getPath($getAbsoluteFile($getCanonicalFile(result))) , path1.length == 0?'/':path1)) != null) {
      return path = $getPath($getAbsoluteFile(result)) , path.length == 0?'/':path;
    }
    candidate = joinResourcePath($getPath(base), filename);
    if (existsViaDataStreams(candidate)) {
      return candidate;
    }
  }
  throw toJs(new IllegalArgumentException_0('File not exist: ' + filename));
}

function getExistFile_0(filename){
  $clinit_Parser();
  var base, base$array, base$index, base$max, candidate, path, path0, path1, result;
  if (existsViaDataStreams(filename)) {
    return $replace(filename);
  }
  result = new File_1(filename);
  if ($getItem(($clinit_File() , LocalStorage), (path0 = $getPath($getAbsoluteFile($getCanonicalFile(result))) , path0.length == 0?'/':path0)) != null) {
    return path = $getPath($getAbsoluteFile(result)) , path.length == 0?'/':path;
  }
  for (base$array = stampJavaTypeInfo(getClassLiteralForArray(Ljava_io_File_2_classLit, 1), $intern_9, 28, 0, [new File_1('.'), new File_1('resource'), new File_1(($clinit_JMugenConstant() , RESOURCE) + 'data'), new File_1(RESOURCE), new File_1('data')]) , base$index = 0 , base$max = base$array.length; base$index < base$max; ++base$index) {
    base = base$array[base$index];
    result = new File_0(base, filename);
    if ($getItem(LocalStorage, (path1 = $getPath($getAbsoluteFile($getCanonicalFile(result))) , path1.length == 0?'/':path1)) != null) {
      return path = $getPath($getAbsoluteFile(result)) , path.length == 0?'/':path;
    }
    candidate = joinResourcePath($getPath(base), filename);
    if (existsViaDataStreams(candidate)) {
      return candidate;
    }
  }
  throw toJs(new IllegalArgumentException_0('File not exist: ' + filename));
}

function getGroupText(src_0){
  $clinit_Parser();
  var line, processLine, strBuilder, strToken, stringList;
  src_0 = $replaceAll(src_0, '\\t', ' ');
  strToken = new StringTokenizer(src_0);
  stringList = new ArrayList;
  line = '';
  processLine = true;
  while ($hasMoreTokens(strToken)) {
    strBuilder = new StringBuilder;
    processLine && (line = $nextToken(strToken));
    if ($find(new Matcher(P_COMMENT_OR_EMPTY_REGEX_0, line))) {
      processLine = true;
      strBuilder.string += line + '\n';
      continue;
    }
    if ($find(new Matcher(P_SECTION_REGEX, line))) {
      do {
        $matches(new Matcher(P_COMMENT_OR_EMPTY_REGEX_0, line)) || (strBuilder.string += line + '\n' , strBuilder);
        line = $nextToken(strToken);
      }
       while (!$find(new Matcher(P_SECTION_REGEX, line)) && $hasMoreTokens(strToken));
      $hasMoreTokens(strToken)?(processLine = false):(strBuilder.string += line + '\n' , strBuilder);
      $add_8(stringList, strBuilder.string);
    }
  }
  return castTo($toArray_0(stringList, initUnidimensionalArray(Ljava_lang_String_2_classLit, $intern_11, 2, 0, 6, 1)), 61);
}

function getGroupTextMap(reader, valueCaseSensitive){
  var suffixlength;
  $clinit_Parser();
  var e, grp, kv, line, m, r, result;
  r = null;
  instanceOf(reader, 63)?(r = castTo(reader, 63)):(r = new BufferedReader(reader));
  result = new ArrayList;
  grp = null;
  while ((line = $readLine(r)) != null) {
    if ($find(new Matcher(P_COMMENT_OR_EMPTY_REGEX_0, line))) {
      continue;
    }
    m = new Matcher(P_SECTION_REGEX, line);
    line = $replaceAll(line, '(?:(?:\\s*;.*$)|(?:\\s*$))', '');
    if ($test(m.regExp, m.input_0)) {
      grp = new Parser$GroupText;
      $setSection(grp, m.matchResult[1].toLowerCase());
      grp.sectionRaw = line;
      push_1(result.array, grp);
    }
     else {
      try {
        line = $replaceAll(line, '(?:(?:\\s*;.*$)|(?:\\s*$))', '');
        kv = getSeparateKeyValue(line);
        $append_3(grp.text_0, line + '\n');
        if (kv != null && kv.length != 0) {
          $add_11(grp.originalKeysOrdered, kv[0]);
          kv[0] = kv[0].toLowerCase();
          kv[1] = kv[1] == null?'':kv[1];
          (!$startsWith(kv[1], '"') || !(suffixlength = '"'.length , $equals_5(kv[1].substr(kv[1].length - suffixlength, suffixlength), '"'))) && (valueCaseSensitive || (kv[1] = kv[1].toLowerCase()));
          $add_11(grp.keysOrdered, kv[0]);
          $putStringValue(grp.keyValues, kv[0], kv[1]);
        }
      }
       catch ($e0) {
        $e0 = toJava($e0);
        if (instanceOf($e0, 6)) {
          e = $e0;
          $printStackTraceImpl(e, ($clinit_System() , err_0), '');
        }
         else 
          throw toJs($e0);
      }
    }
  }
  return result;
}

function getSeparateKeyValue(line){
  var indexEnd, indexEqual, keyValue;
  keyValue = initUnidimensionalArray(Ljava_lang_String_2_classLit, $intern_11, 2, 2, 6, 1);
  if ($find(new Matcher(P_COMMENT_OR_EMPTY_REGEX_0, line)))
    return null;
  line = line;
  indexEnd = line.indexOf(';');
  indexEnd = indexEnd == -1?line.length - 1:indexEnd - 1;
  indexEqual = line.indexOf('=');
  keyValue[0] = '';
  keyValue[1] = '';
  if (-1 == indexEqual)
    return null;
  keyValue[0] = $trim((checkCriticalStringBounds(0, indexEqual, line.length) , line.substr(0, indexEqual)));
  keyValue[1] = $trim((checkCriticalStringBounds(indexEqual + 1, indexEqual + 1 + indexEnd - indexEqual, line.length) , line.substr(indexEqual + 1, indexEqual + 1 + indexEnd - indexEqual - (indexEqual + 1))));
  return keyValue;
}

function getText(in_0){
  $clinit_Parser();
  var br, isr, line, strBuild;
  isr = new InputStreamReader(in_0);
  br = new BufferedReader(isr);
  line = null;
  strBuild = new StringBuilder;
  while ((line = $readLine(br)) != null) {
    $append_3(strBuild, $replaceAll($replaceAll(line, '\t', ''), '\r', '').toLowerCase() + '\n');
  }
  return strBuild.string;
}

function joinResourcePath(dir_0, filename){
  var d;
  d = $replace(dir_0);
  d.length != 0 && $charAt(d, d.length - 1) != 47 && (d += '/');
  return d + ('' + $replace(filename));
}

var P_COMMENT_OR_EMPTY_REGEX_0, P_SECTION_REGEX;
function $setSection(this$static, section){
  this$static.section = section;
}

function Parser$GroupText(){
  this.keysOrdered = new LinkedList;
  this.originalKeysOrdered = new LinkedList;
  this.keyValues = new HashMap;
  this.text_0 = new StringBuilder;
}

defineClass(90, 1, {3:1, 90:1}, Parser$GroupText);
_.toString_0 = function toString_61(){
  var buffer;
  buffer = new StringBuilder;
  $append_3(buffer, this.sectionRaw.toLowerCase() + '\n');
  $append_1(buffer, this.text_0);
  return buffer.string;
}
;
var Lorg_lee_mugen_sprite_parser_Parser$GroupText_2_classLit = createForClass('org.lee.mugen.sprite.parser', 'Parser/GroupText', 90);
function $clinit_BeanTools(){
  $clinit_BeanTools = emptyMethod;
  convertersMap = new HashMap;
  pointConverter = new BeanTools$1;
  intArrayConverter = new BeanTools$2;
  integerConverter = new BeanTools$3;
  floatConverter = new BeanTools$4;
  floatPrimiArrayConverter = new BeanTools$5;
  pointfConverter = new BeanTools$6;
  mugenRectConverter = new BeanTools$7;
  mugenRectangleConverter = new BeanTools$8;
  rgbConverter = new BeanTools$9;
  soundConvertor = new BeanTools$10;
  sprConverter = new BeanTools$11;
  bgTypeConverter = new BeanTools$12;
  stringConverter = new BeanTools$13;
  transConverter = new BeanTools$14;
  objectArrayConverter = new BeanTools$15;
  $put_4(convertersMap, Lorg_lee_mugen_geom_MugenPoint_2_classLit, pointConverter);
  $put_4(convertersMap, Lorg_lee_mugen_geom_MugenRect_2_classLit, mugenRectConverter);
  $put_4(convertersMap, Lorg_lee_mugen_object_Rectangle_2_classLit, mugenRectangleConverter);
  $put_4(convertersMap, getClassLiteralForArray(I_classLit, 1), intArrayConverter);
  $put_4(convertersMap, Ljava_lang_Integer_2_classLit, integerConverter);
  $put_4(convertersMap, I_classLit, integerConverter);
  $put_4(convertersMap, Ljava_lang_Float_2_classLit, floatConverter);
  $put_4(convertersMap, F_classLit, floatConverter);
  $put_4(convertersMap, getClassLiteralForArray(F_classLit, 1), floatPrimiArrayConverter);
  $put_4(convertersMap, Lorg_lee_mugen_sprite_entity_PointF_2_classLit, pointfConverter);
  $put_4(convertersMap, Lorg_lee_mugen_renderer_RGB_2_classLit, rgbConverter);
  $put_4(convertersMap, Lorg_lee_mugen_sprite_entity_SndGrpNum_2_classLit, soundConvertor);
  $put_4(convertersMap, Lorg_lee_mugen_sprite_entity_SprGrpNum_2_classLit, sprConverter);
  $put_4(convertersMap, Lorg_lee_mugen_background_BG$Type_2_classLit, bgTypeConverter);
  $put_4(convertersMap, Ljava_lang_String_2_classLit, stringConverter);
  $put_4(convertersMap, Lorg_lee_mugen_renderer_Trans_2_classLit, transConverter);
  $put_4(convertersMap, getClassLiteralForArray(Ljava_lang_Object_2_classLit, 1), objectArrayConverter);
  new Pattern('.*(\\d+)');
}

var bgTypeConverter, convertersMap, floatConverter, floatPrimiArrayConverter, intArrayConverter, integerConverter, mugenRectConverter, mugenRectangleConverter, objectArrayConverter, pointConverter, pointfConverter, rgbConverter, soundConvertor, sprConverter, stringConverter, transConverter;
function $convert(o){
  var objects, value_0;
  if (o != null) {
    value_0 = $split($replaceAll(o, ' ', ''), ',');
    return new MugenPoint_0(__parseAndValidateInt(value_0[0], 10), __parseAndValidateInt(value_0[1], 10));
  }
  objects = throwClassCastExceptionUnlessNull(o);
  return new MugenPoint_0(objects[0].$_nullMethod(), objects[1].$_nullMethod());
}

function BeanTools$1(){
}

defineClass(453, 1, $intern_71, BeanTools$1);
_.convert = function convert(o){
  return $convert(o);
}
;
var Lorg_lee_mugen_util_BeanTools$1_2_classLit = createForClass('org.lee.mugen.util', 'BeanTools/1', 453);
function $convert_0(o){
  var objects, sound, str;
  sound = new SndGrpNum;
  if (instanceOfString(o)) {
    str = $split($replaceAll($replaceAll(castToString(o), ' ', ''), '\t', ''), ',');
    o = stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_Object_2_classLit, 1), $intern_9, 1, 5, [valueOf_1(__parseAndValidateInt(str[0], 10)), valueOf_1(__parseAndValidateInt(str[1], 10))]);
  }
  objects = castToArray(o);
  objects.length > 2 && $booleanValue(castToBoolean(objects[2]));
  $setSnd_grp(sound, intValue__I__devirtual$(castTo(objects[0], 23)));
  $setSnd_item(sound, intValue__I__devirtual$(castTo(objects[1], 23)));
  return sound;
}

function BeanTools$10(){
}

defineClass(462, 1, $intern_71, BeanTools$10);
_.convert = function convert_0(o){
  return $convert_0(o);
}
;
var Lorg_lee_mugen_util_BeanTools$10_2_classLit = createForClass('org.lee.mugen.util', 'BeanTools/10', 462);
function BeanTools$11(){
}

defineClass(463, 1, $intern_71, BeanTools$11);
_.convert = function convert_1(o){
  var objects;
  return objects = throwClassCastExceptionUnlessNull(o) , new SprGrpNum_0(objects[0].$_nullMethod(), objects[1].$_nullMethod());
}
;
var Lorg_lee_mugen_util_BeanTools$11_2_classLit = createForClass('org.lee.mugen.util', 'BeanTools/11', 463);
function BeanTools$12(){
}

defineClass(464, 1, $intern_71, BeanTools$12);
_.convert = function convert_2(o){
  return valueOf_5(o.toUpperCase());
}
;
var Lorg_lee_mugen_util_BeanTools$12_2_classLit = createForClass('org.lee.mugen.util', 'BeanTools/12', 464);
function BeanTools$13(){
}

defineClass(465, 1, $intern_71, BeanTools$13);
_.convert = function convert_3(o){
  return o == null?'':o;
}
;
var Lorg_lee_mugen_util_BeanTools$13_2_classLit = createForClass('org.lee.mugen.util', 'BeanTools/13', 465);
function BeanTools$14(){
}

defineClass(466, 1, $intern_71, BeanTools$14);
_.convert = function convert_4(o){
  return valueOf_6(o.toUpperCase());
}
;
var Lorg_lee_mugen_util_BeanTools$14_2_classLit = createForClass('org.lee.mugen.util', 'BeanTools/14', 466);
function $convert_1(o){
  if (o == null) {
    return initUnidimensionalArray(Ljava_lang_Object_2_classLit, $intern_9, 1, 0, 5, 1);
  }
  if ((Ljava_lang_String_2_classLit.modifiers & 4) != 0) {
    return throwClassCastExceptionUnlessNull(o);
  }
  return stampJavaTypeInfo(getClassLiteralForArray(Ljava_lang_Object_2_classLit, 1), $intern_9, 1, 5, [o]);
}

function BeanTools$15(){
}

defineClass(467, 1, $intern_71, BeanTools$15);
_.convert = function convert_5(o){
  return $convert_1(o);
}
;
var Lorg_lee_mugen_util_BeanTools$15_2_classLit = createForClass('org.lee.mugen.util', 'BeanTools/15', 467);
function $convert_2(o){
  return $convert_3(o);
}

function $convert_3(o){
  var arr, array, farray, i, i0, iarr;
  if (instanceOf(o, 11)) {
    return castTo(o, 11);
  }
  if (instanceOfString(o)) {
    arr = $split($replaceAll($trim(toString_30(o)), ' ', ''), ',');
    iarr = initUnidimensionalArray(I_classLit, $intern_3, 5, arr.length, 15, 1);
    for (i0 = 0; i0 < arr.length; i0++) {
      iarr[i0] = __parseAndValidateInt(arr[i0], 10);
    }
    return iarr;
  }
  array = castToArray(o);
  farray = initUnidimensionalArray(I_classLit, $intern_3, 5, array.length, 15, 1);
  for (i = 0; i < farray.length; i++) {
    farray[i] = intValue__I__devirtual$(castTo(array[i], 23));
  }
  return farray;
}

function BeanTools$2(){
}

defineClass(454, 1, $intern_71, BeanTools$2);
_.convert = function convert_6(o){
  return $convert_2(o);
}
;
var Lorg_lee_mugen_util_BeanTools$2_2_classLit = createForClass('org.lee.mugen.util', 'BeanTools/2', 454);
function $convert_4(o){
  var obs;
  if (o == null) {
    return valueOf_1(0);
  }
  if ((Ljava_lang_String_2_classLit.modifiers & 4) != 0) {
    obs = throwClassCastExceptionUnlessNull(o);
    return valueOf_1(obs.length == 0?0:valueOf_1(__parseAndValidateInt(obs[0].$_nullMethod(), 10)).value_0);
  }
  return valueOf_1(__parseAndValidateInt(o, 10));
}

function BeanTools$3(){
}

defineClass(455, 1, $intern_71, BeanTools$3);
_.convert = function convert_7(o){
  return $convert_4(o);
}
;
var Lorg_lee_mugen_util_BeanTools$3_2_classLit = createForClass('org.lee.mugen.util', 'BeanTools/3', 455);
function $convert_5(o){
  var obs;
  if (o == null) {
    return new Float(0);
  }
  if ((Ljava_lang_String_2_classLit.modifiers & 4) != 0) {
    obs = throwClassCastExceptionUnlessNull(o);
    return new Float(obs.length == 0?0:(new Float_0(obs[0].$_nullMethod())).value_0);
  }
  return new Float_0(o);
}

function $convert_6(o){
  return $convert_5(o);
}

function BeanTools$4(){
}

defineClass(456, 1, $intern_71, BeanTools$4);
_.convert = function convert_8(o){
  return $convert_6(o);
}
;
var Lorg_lee_mugen_util_BeanTools$4_2_classLit = createForClass('org.lee.mugen.util', 'BeanTools/4', 456);
function $convert_7(o){
  return $convert_8(o);
}

function $convert_8(o){
  var array, farray, i, value_0;
  if (o != null) {
    value_0 = $split($replaceAll(o, ' ', ''), ',');
    return stampJavaTypeInfo(getClassLiteralForArray(F_classLit, 1), $intern_10, 5, 15, [parseFloat_0(value_0[0]), parseFloat_0(value_0[1])]);
  }
  if (o == null) {
    return stampJavaTypeInfo(getClassLiteralForArray(F_classLit, 1), $intern_10, 5, 15, [0]);
  }
  if ((Ljava_lang_String_2_classLit.modifiers & 4) != 0) {
    array = throwClassCastExceptionUnlessNull(o);
    farray = initUnidimensionalArray(F_classLit, $intern_10, 5, array.length, 15, 1);
    for (i = 0; i < farray.length; i++) {
      farray[i] = $convert_6(($clinit_BeanTools() , floatConverter , array[i])).value_0;
    }
    return farray;
  }
  return stampJavaTypeInfo(getClassLiteralForArray(F_classLit, 1), $intern_10, 5, 15, [parseFloat_0(o)]);
}

function BeanTools$5(){
}

defineClass(457, 1, $intern_71, BeanTools$5);
_.convert = function convert_9(o){
  return $convert_7(o);
}
;
var Lorg_lee_mugen_util_BeanTools$5_2_classLit = createForClass('org.lee.mugen.util', 'BeanTools/5', 457);
function BeanTools$6(){
}

defineClass(458, 1, $intern_71, BeanTools$6);
_.convert = function convert_10(o){
  var values, p;
  return values = $convert_7(($clinit_BeanTools() , o)) , p = new PointF , values.length >= 1 && $setX(p, values[0]) , values.length >= 2 && $setY(p, values[1]) , p;
}
;
var Lorg_lee_mugen_util_BeanTools$6_2_classLit = createForClass('org.lee.mugen.util', 'BeanTools/6', 458);
function $convert_9(o){
  var params, params0, result, result0;
  if (o != null) {
    params0 = $split($replaceAll(o, ' ', ''), ',');
    result0 = new MugenRect;
    __parseAndValidateInt(params0[0], 10);
    __parseAndValidateInt(params0[1], 10);
    result0.width_0 = __parseAndValidateInt(params0[2], 10);
    result0.height_0 = __parseAndValidateInt(params0[3], 10);
    return result0;
  }
  params = throwClassCastExceptionUnlessNull(o);
  result = new MugenRect;
  params[0].$_nullMethod();
  params[1].$_nullMethod();
  result.width_0 = params[2].$_nullMethod();
  result.height_0 = params[3].$_nullMethod();
  return result;
}

function BeanTools$7(){
}

defineClass(459, 1, $intern_71, BeanTools$7);
_.convert = function convert_11(o){
  return $convert_9(o);
}
;
var Lorg_lee_mugen_util_BeanTools$7_2_classLit = createForClass('org.lee.mugen.util', 'BeanTools/7', 459);
function $convert_10(o){
  var params, params0, result, result0;
  if (o != null) {
    params0 = $split($replaceAll(o, ' ', ''), ',');
    result0 = new Rectangle_0;
    $setX1(result0, __parseAndValidateInt(params0[0], 10));
    $setY1(result0, __parseAndValidateInt(params0[1], 10));
    $setX2(result0, __parseAndValidateInt(params0[2], 10));
    $setY2(result0, __parseAndValidateInt(params0[3], 10));
    return result0;
  }
  params = throwClassCastExceptionUnlessNull(o);
  result = new Rectangle_0;
  $setX1(result, params[0].$_nullMethod());
  $setY1(result, params[1].$_nullMethod());
  $setX2(result, params[2].$_nullMethod());
  $setY2(result, params[3].$_nullMethod());
  return result;
}

function BeanTools$8(){
}

defineClass(460, 1, $intern_71, BeanTools$8);
_.convert = function convert_12(o){
  return $convert_10(o);
}
;
var Lorg_lee_mugen_util_BeanTools$8_2_classLit = createForClass('org.lee.mugen.util', 'BeanTools/8', 460);
function $convert_11(o){
  var objects, objects0, pos, pos0, rgb;
  rgb = new RGB;
  instanceOfString(o) && (o = $convert_2(($clinit_BeanTools() , intArrayConverter , o)));
  if (instanceOf(o, 11)) {
    objects0 = castTo(o, 11);
    pos0 = 0;
    $setR(rgb, objects0[pos0++]);
    $setG(rgb, objects0[pos0++]);
    $setB(rgb, objects0[pos0++]);
    objects0.length == 4 && (rgb.a = objects0[pos0] , undefined);
    return rgb;
  }
  objects = castToArray(o);
  pos = 0;
  $setR(rgb, floatValue__F__devirtual$(castTo(objects[pos++], 23)));
  $setG(rgb, floatValue__F__devirtual$(castTo(objects[pos++], 23)));
  $setB(rgb, floatValue__F__devirtual$(castTo(objects[pos++], 23)));
  objects.length == 4 && $setA(rgb, floatValue__F__devirtual$(castTo(objects[pos], 23)));
  return rgb;
}

function BeanTools$9(){
}

defineClass(461, 1, $intern_71, BeanTools$9);
_.convert = function convert_13(o){
  return $convert_11(o);
}
;
var Lorg_lee_mugen_util_BeanTools$9_2_classLit = createForClass('org.lee.mugen.util', 'BeanTools/9', 461);
function error_0(str, args){
  var d;
  $clinit_System();
  d = new Date_0;
  pad2(d.jsdate.getHours()) + ':' + pad2(d.jsdate.getMinutes()) + ':' + pad2(d.jsdate.getSeconds()) + ' - ' + format_0(str, args);
  String.fromCharCode(10);
}

function format_0(str, args){
  var arg, arg$array, arg$index, arg$max, i, out;
  if (args.length == 0) {
    return str;
  }
  out = str;
  for (arg$array = args , arg$index = 0 , arg$max = arg$array.length; arg$index < arg$max; ++arg$index) {
    arg = arg$array[arg$index];
    i = out.indexOf('%s');
    if (i < 0) {
      break;
    }
    out = (checkCriticalStringBounds(0, i, out.length) , out.substr(0, i) + ('' + (arg == null?'null':toString_30(arg))) + (checkCriticalStringElementIndex(i + 2, out.length + 1) , out.substr(i + 2)));
  }
  return out;
}

function log_0(str, args){
  var d;
  $clinit_System();
  d = new Date_0;
  pad2(d.jsdate.getHours()) + ':' + pad2(d.jsdate.getMinutes()) + ':' + pad2(d.jsdate.getSeconds()) + ' - ' + format_0(str, args);
  String.fromCharCode(10);
}

function pad2(n){
  return n < 10?'0' + n:'' + n;
}

var C_classLit = createForPrimitive('char', 'C');
var B_classLit = createForPrimitive('byte', 'B');
var I_classLit = createForPrimitive('int', 'I');
var Z_classLit = createForPrimitive('boolean', 'Z');
var J_classLit = createForPrimitive('long', 'J');
var D_classLit = createForPrimitive('double', 'D');
var F_classLit = createForPrimitive('float', 'F');
var S_classLit = createForPrimitive('short', 'S');
var $entry = ($clinit_Impl() , entry_0);
var gwtOnLoad = gwtOnLoad = gwtOnLoad_0;
addInitFunctions(init);
setGwtProperty('permProps', [[['locale', 'default'], ['user.agent', 'gecko1_8']]]);
$sendStats('moduleStartup', 'moduleEvalEnd');
gwtOnLoad(__gwtModuleFunction.__errFn, __gwtModuleFunction.__moduleName, __gwtModuleFunction.__moduleBase, __gwtModuleFunction.__softPermutationId,__gwtModuleFunction.__computePropValue);
$sendStats('moduleStartup', 'end');
$gwt && $gwt.permProps && __gwtModuleFunction.__moduleStartupDone($gwt.permProps);
//# sourceURL=jmugen-0.js

