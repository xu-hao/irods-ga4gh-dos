SELECT r_data_main.data_id, r_data_main.coll_id, r_coll_main.coll_name, r_data_main.data_name,r_data_main.data_checksum, r_meta_main.meta_id, r_meta_main.meta_attr_name, r_meta_main.meta_attr_value
FROM public.r_data_main, public.r_meta_main, public.r_objt_metamap, public.r_coll_main where r_data_main.data_id = r_objt_metamap.object_id and r_objt_metamap.meta_id = r_meta_main.meta_id and r_coll_main.coll_id = r_data_main.coll_id
and r_meta_main.meta_attr_name = 'GUID' and r_coll_main.coll_name like '/zone1/trash/home/test1/jargon-scratch.1446679108/GuidServiceImplTest%';
