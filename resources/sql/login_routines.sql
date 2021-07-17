DROP PROCEDURE IF EXISTS `getNumResources`;;
DROP PROCEDURE IF EXISTS `getUserAuthData`;;

CREATE DEFINER=`root`@`localhost` PROCEDURE `getNumResources`(OUT resources int)
BEGIN
	SELECT COUNT(*) INTO resources FROM resource;
END ;;

CREATE DEFINER=`root`@`localhost` PROCEDURE `getUserAuthData`(IN userName char(32))
BEGIN
	SELECT user.id, user.name, role.id, role.name, resource.id, resource.name, action.id, action.name 
    FROM USER, ROLE, USER_ROLE, RESOURCE, ACTION, ROLE_RESOURCE_ACTION WHERE 
    user.name=userName and
    user.id=user_role.user_id and 
    role.id=user_role.role_id and
    role.id=role_resource_action.role_id and
    resource.id=role_resource_action.resource_id and
    action.id=role_resource_action.action_id
    order by resource.name, action.name;
END ;;
