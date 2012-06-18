package ${packagePath}.service;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

<#if sessionTypeName == "Local">
/**
 * The utility for the ${entity.humanName} local service. This utility wraps {@link ${packagePath}.service.impl.${entity.name}LocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author ${author}
 * @see ${entity.name}LocalService
 * @see ${packagePath}.service.base.${entity.name}LocalServiceBaseImpl
 * @see ${packagePath}.service.impl.${entity.name}LocalServiceImpl
 * @generated
 */
<#else>
/**
 * The utility for the ${entity.humanName} remote service. This utility wraps {@link ${packagePath}.service.impl.${entity.name}ServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author ${author}
 * @see ${entity.name}Service
 * @see ${packagePath}.service.base.${entity.name}ServiceBaseImpl
 * @see ${packagePath}.service.impl.${entity.name}ServiceImpl
 * @generated
 */
</#if>
public class ${entity.name}${sessionTypeName}ServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link ${packagePath}.service.impl.${entity.name}${sessionTypeName}ServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	<#list methods as method>
		<#if !method.isConstructor() && !method.isStatic() && method.isPublic() && serviceBuilder.isCustomMethod(method)>
			${serviceBuilder.getJavadocComment(method)}

			<#if method.name = "dynamicQuery">
				@SuppressWarnings("rawtypes")
			</#if>

			public static ${serviceBuilder.getTypeGenericsName(method.returns)} ${method.name}(

			<#list method.parameters as parameter>
				${serviceBuilder.getTypeGenericsName(parameter.type)} ${parameter.name}

				<#if parameter_has_next>
					,
				</#if>
			</#list>

			)

			<#list method.exceptions as exception>
				<#if exception_index == 0>
					throws
				</#if>

				${exception.value}

				<#if exception_has_next>
					,
				</#if>
			</#list>

			{
				<#if method.returns.value != "void">
					return
				</#if>

				getService().${method.name}(

				<#list method.parameters as parameter>
					${parameter.name}

					<#if parameter_has_next>
						,
					</#if>
				</#list>

				);
			}
		</#if>
	</#list>

	<#if pluginName != "">
		public static void clearService() {
			_service = null;
		}
	</#if>

	public static ${entity.name}${sessionTypeName}Service getService() {
		if (_service == null) {
			<#if pluginName != "">
				Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(), ${entity.name}${sessionTypeName}Service.class.getName());
				ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(), "portletClassLoader");

				ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object, ${entity.name}${sessionTypeName}Service.class.getName(), portletClassLoader);

				_service = new ${entity.name}${sessionTypeName}ServiceClp(classLoaderProxy);

				ClpSerializer.setClassLoader(portletClassLoader);
			<#else>
				_service = (${entity.name}${sessionTypeName}Service)PortalBeanLocatorUtil.locate(${entity.name}${sessionTypeName}Service.class.getName());
			</#if>

			ReferenceRegistry.registerReference(${entity.name}${sessionTypeName}ServiceUtil.class, "_service");
			MethodCache.remove(${entity.name}${sessionTypeName}Service.class);
		}

		return _service;
	}

	public void setService(${entity.name}${sessionTypeName}Service service) {
		MethodCache.remove(${entity.name}${sessionTypeName}Service.class);

		_service = service;

		ReferenceRegistry.registerReference(${entity.name}${sessionTypeName}ServiceUtil.class, "_service");
		MethodCache.remove(${entity.name}${sessionTypeName}Service.class);
	}

	private static ${entity.name}${sessionTypeName}Service _service;

}