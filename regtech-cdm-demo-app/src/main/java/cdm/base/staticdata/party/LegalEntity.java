package cdm.base.staticdata.party;

import cdm.base.staticdata.party.LegalEntity;
import cdm.base.staticdata.party.LegalEntity.LegalEntityBuilder;
import cdm.base.staticdata.party.LegalEntity.LegalEntityBuilderImpl;
import cdm.base.staticdata.party.LegalEntity.LegalEntityImpl;
import cdm.base.staticdata.party.meta.LegalEntityMeta;
import com.google.common.collect.ImmutableList;
import com.rosetta.model.lib.GlobalKey;
import com.rosetta.model.lib.GlobalKey.GlobalKeyBuilder;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import com.rosetta.model.metafields.FieldWithMetaString;
import com.rosetta.model.metafields.FieldWithMetaString.FieldWithMetaStringBuilder;
import com.rosetta.model.metafields.MetaFields;
import com.rosetta.util.ListEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * A class to specify a legal entity, with a required name and an optional entity identifier (such as the LEI).
 * @version ${project.version}
 */
@RosettaDataType(value="LegalEntity", builder=LegalEntity.LegalEntityBuilderImpl.class, version="${project.version}")
public interface LegalEntity extends RosettaModelObject, GlobalKey {

	LegalEntityMeta metaData = new LegalEntityMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * A legal entity identifier (e.g. RED entity code).
	 */
	List<? extends FieldWithMetaString> getEntityId();
	/**
	 * The legal entity name.
	 */
	FieldWithMetaString getName();
	/**
	 */
	MetaFields getMeta();

	/*********************** Build Methods  ***********************/
	LegalEntity build();
	
	LegalEntity.LegalEntityBuilder toBuilder();
	
	static LegalEntity.LegalEntityBuilder builder() {
		return new LegalEntity.LegalEntityBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends LegalEntity> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends LegalEntity> getType() {
		return LegalEntity.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("entityId"), processor, FieldWithMetaString.class, getEntityId());
		processRosetta(path.newSubPath("name"), processor, FieldWithMetaString.class, getName());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
	}
	

	/*********************** Builder Interface  ***********************/
	interface LegalEntityBuilder extends LegalEntity, RosettaModelObjectBuilder {
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateEntityId(int _index);
		List<? extends FieldWithMetaString.FieldWithMetaStringBuilder> getEntityId();
		FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateName();
		FieldWithMetaString.FieldWithMetaStringBuilder getName();
		MetaFields.MetaFieldsBuilder getOrCreateMeta();
		MetaFields.MetaFieldsBuilder getMeta();
		LegalEntity.LegalEntityBuilder addEntityId(FieldWithMetaString entityId0);
		LegalEntity.LegalEntityBuilder addEntityId(FieldWithMetaString entityId1, int _idx);
		LegalEntity.LegalEntityBuilder addEntityIdValue(String entityId2);
		LegalEntity.LegalEntityBuilder addEntityIdValue(String entityId3, int _idx);
		LegalEntity.LegalEntityBuilder addEntityId(List<? extends FieldWithMetaString> entityId4);
		LegalEntity.LegalEntityBuilder setEntityId(List<? extends FieldWithMetaString> entityId5);
		LegalEntity.LegalEntityBuilder addEntityIdValue(List<? extends String> entityId6);
		LegalEntity.LegalEntityBuilder setEntityIdValue(List<? extends String> entityId7);
		LegalEntity.LegalEntityBuilder setName(FieldWithMetaString name0);
		LegalEntity.LegalEntityBuilder setNameValue(String name1);
		LegalEntity.LegalEntityBuilder setMeta(MetaFields meta);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("entityId"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getEntityId());
			processRosetta(path.newSubPath("name"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getName());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
		}
		

		LegalEntity.LegalEntityBuilder prune();
	}

	/*********************** Immutable Implementation of LegalEntity  ***********************/
	class LegalEntityImpl implements LegalEntity {
		private final List<? extends FieldWithMetaString> entityId;
		private final FieldWithMetaString name;
		private final MetaFields meta;
		
		protected LegalEntityImpl(LegalEntity.LegalEntityBuilder builder) {
			this.entityId = ofNullable(builder.getEntityId()).filter(_l->!_l.isEmpty()).map(list -> list.stream().filter(Objects::nonNull).map(f->f.build()).filter(Objects::nonNull).collect(ImmutableList.toImmutableList())).orElse(null);
			this.name = ofNullable(builder.getName()).map(f->f.build()).orElse(null);
			this.meta = ofNullable(builder.getMeta()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("entityId")
		public List<? extends FieldWithMetaString> getEntityId() {
			return entityId;
		}
		
		@Override
		@RosettaAttribute("name")
		public FieldWithMetaString getName() {
			return name;
		}
		
		@Override
		@RosettaAttribute("meta")
		public MetaFields getMeta() {
			return meta;
		}
		
		@Override
		public LegalEntity build() {
			return this;
		}
		
		@Override
		public LegalEntity.LegalEntityBuilder toBuilder() {
			LegalEntity.LegalEntityBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(LegalEntity.LegalEntityBuilder builder) {
			ofNullable(getEntityId()).ifPresent(builder::setEntityId);
			ofNullable(getName()).ifPresent(builder::setName);
			ofNullable(getMeta()).ifPresent(builder::setMeta);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			LegalEntity _that = getType().cast(o);
		
			if (!ListEquals.listEquals(entityId, _that.getEntityId())) return false;
			if (!Objects.equals(name, _that.getName())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (entityId != null ? entityId.hashCode() : 0);
			_result = 31 * _result + (name != null ? name.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "LegalEntity {" +
				"entityId=" + this.entityId + ", " +
				"name=" + this.name + ", " +
				"meta=" + this.meta +
			'}';
		}
	}

	/*********************** Builder Implementation of LegalEntity  ***********************/
	class LegalEntityBuilderImpl implements LegalEntity.LegalEntityBuilder, GlobalKeyBuilder {
	
		protected List<FieldWithMetaString.FieldWithMetaStringBuilder> entityId = new ArrayList<>();
		protected FieldWithMetaString.FieldWithMetaStringBuilder name;
		protected MetaFields.MetaFieldsBuilder meta;
	
		public LegalEntityBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("entityId")
		public List<? extends FieldWithMetaString.FieldWithMetaStringBuilder> getEntityId() {
			return entityId;
		}
		
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateEntityId(int _index) {
		
			if (entityId==null) {
				this.entityId = new ArrayList<>();
			}
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			return getIndex(entityId, _index, () -> {
						FieldWithMetaString.FieldWithMetaStringBuilder newEntityId = FieldWithMetaString.builder();
						return newEntityId;
					});
		}
		
		@Override
		@RosettaAttribute("name")
		public FieldWithMetaString.FieldWithMetaStringBuilder getName() {
			return name;
		}
		
		@Override
		public FieldWithMetaString.FieldWithMetaStringBuilder getOrCreateName() {
			FieldWithMetaString.FieldWithMetaStringBuilder result;
			if (name!=null) {
				result = name;
			}
			else {
				result = name = FieldWithMetaString.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("meta")
		public MetaFields.MetaFieldsBuilder getMeta() {
			return meta;
		}
		
		@Override
		public MetaFields.MetaFieldsBuilder getOrCreateMeta() {
			MetaFields.MetaFieldsBuilder result;
			if (meta!=null) {
				result = meta;
			}
			else {
				result = meta = MetaFields.builder();
			}
			
			return result;
		}
	
		@Override
		public LegalEntity.LegalEntityBuilder addEntityId(FieldWithMetaString entityId) {
			if (entityId!=null) this.entityId.add(entityId.toBuilder());
			return this;
		}
		
		@Override
		public LegalEntity.LegalEntityBuilder addEntityId(FieldWithMetaString entityId, int _idx) {
			getIndex(this.entityId, _idx, () -> entityId.toBuilder());
			return this;
		}
		
		@Override
		public LegalEntity.LegalEntityBuilder addEntityIdValue(String entityId) {
			this.getOrCreateEntityId(-1).setValue(entityId);
			return this;
		}
		
		@Override
		public LegalEntity.LegalEntityBuilder addEntityIdValue(String entityId, int _idx) {
			this.getOrCreateEntityId(_idx).setValue(entityId);
			return this;
		}
		@Override 
		public LegalEntity.LegalEntityBuilder addEntityId(List<? extends FieldWithMetaString> entityIds) {
			if (entityIds != null) {
				for (FieldWithMetaString toAdd : entityIds) {
					this.entityId.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("entityId")
		public LegalEntity.LegalEntityBuilder setEntityId(List<? extends FieldWithMetaString> entityIds) {
			if (entityIds == null)  {
				this.entityId = new ArrayList<>();
			}
			else {
				this.entityId = entityIds.stream()
					.map(_a->_a.toBuilder())
					.collect(Collectors.toCollection(()->new ArrayList<>()));
			}
			return this;
		}
		
		@Override
		public LegalEntity.LegalEntityBuilder addEntityIdValue(List<? extends String> entityIds) {
			if (entityIds != null) {
				for (String toAdd : entityIds) {
					this.addEntityIdValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public LegalEntity.LegalEntityBuilder setEntityIdValue(List<? extends String> entityIds) {
			this.entityId.clear();
			if (entityIds!=null) {
				entityIds.forEach(this::addEntityIdValue);
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("name")
		public LegalEntity.LegalEntityBuilder setName(FieldWithMetaString name) {
			this.name = name==null?null:name.toBuilder();
			return this;
		}
		@Override
		public LegalEntity.LegalEntityBuilder setNameValue(String name) {
			this.getOrCreateName().setValue(name);
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public LegalEntity.LegalEntityBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		
		@Override
		public LegalEntity build() {
			return new LegalEntity.LegalEntityImpl(this);
		}
		
		@Override
		public LegalEntity.LegalEntityBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public LegalEntity.LegalEntityBuilder prune() {
			entityId = entityId.stream().filter(b->b!=null).<FieldWithMetaString.FieldWithMetaStringBuilder>map(b->b.prune()).filter(b->b.hasData()).collect(Collectors.toList());
			if (name!=null && !name.prune().hasData()) name = null;
			if (meta!=null && !meta.prune().hasData()) meta = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getEntityId()!=null && !getEntityId().isEmpty()) return true;
			if (getName()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public LegalEntity.LegalEntityBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			LegalEntity.LegalEntityBuilder o = (LegalEntity.LegalEntityBuilder) other;
			
			merger.mergeRosetta(getEntityId(), o.getEntityId(), this::getOrCreateEntityId);
			merger.mergeRosetta(getName(), o.getName(), this::setName);
			merger.mergeRosetta(getMeta(), o.getMeta(), this::setMeta);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			LegalEntity _that = getType().cast(o);
		
			if (!ListEquals.listEquals(entityId, _that.getEntityId())) return false;
			if (!Objects.equals(name, _that.getName())) return false;
			if (!Objects.equals(meta, _that.getMeta())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (entityId != null ? entityId.hashCode() : 0);
			_result = 31 * _result + (name != null ? name.hashCode() : 0);
			_result = 31 * _result + (meta != null ? meta.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "LegalEntityBuilder {" +
				"entityId=" + this.entityId + ", " +
				"name=" + this.name + ", " +
				"meta=" + this.meta +
			'}';
		}
	}
}
