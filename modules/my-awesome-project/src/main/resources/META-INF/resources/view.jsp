<%@ include file="/init.jsp" %>

<p>
	<b> <liferay-ui:message key="my-awesome-project.caption" />
		</b> <b> <aui:button name="name" value="Press ME" />
	</b>

	<b>
		<liferay-ui:tabs names="tab1,tab2,tab3" refresh="false" tabsValues="tab1,tab2,tab3">
			<liferay-ui:section>
				Text for Tab 1.
			</liferay-ui:section>

			<liferay-ui:section>
				Text for Tab 2.
			</liferay-ui:section>

			<liferay-ui:section>
				<%@ include file="sample.jsp" %>
			</liferay-ui:section>
		</liferay-ui:tabs>
	</b>

	<b>
		<liferay-ui:icon image="add" message="click here" url="http://www.liferay.com" />
	</b>

	<b>
		<liferay-ui:icon-menu>
			<liferay-ui:icon iconCssClass="icon-user" message="Use" url="www.liferay.com" />
			<liferay-ui:icon iconCssClass="icon-film" message="Film" url="www.liferay.com" />
			<liferay-ui:icon iconCssClass="icon-edit" message="Edit" url="www.liferay.com" />
			<liferay-ui:icon iconCssClass="icon-trash" message="Trash" url="www.liferay.com" />
		</liferay-ui:icon-menu>
	</b>

	<b>
		<liferay-ui:icon-list>
			<liferay-ui:icon image="status_online" message="Sign In" url="www.liferay.com" />
			<liferay-ui:icon image="edit" message="edit" url="www.liferay.com" />
			<liferay-ui:icon image="add_article" message="Add Article" url="www.liferay.com" />
		</liferay-ui:icon-list>
	</b>
</p>