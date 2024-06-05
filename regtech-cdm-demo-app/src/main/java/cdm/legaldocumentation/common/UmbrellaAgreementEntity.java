package cdm.legaldocumentation.common;

import cdm.base.staticdata.party.LegalEntity;
import cdm.base.staticdata.party.LegalEntity.LegalEntityBuilder;
import cdm.base.staticdata.party.LegalEntity.LegalEntityBuilderImpl;
import cdm.base.staticdata.party.LegalEntity.LegalEntityImpl;
import cdm.legaldocumentation.common.UmbrellaAgreementEntity;
import cdm.legaldocumentation.common.UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder;
import cdm.legaldocumentation.common.UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilderImpl;
import cdm.legaldocumentation.common.UmbrellaAgreementEntity.UmbrellaAgreementEntityImpl;
import cdm.legaldocumentation.common.meta.UmbrellaAgreementEntityMeta;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

/**
 * A class to specify the legal entities that are part of the umbrella agreement.
 * @version ${project.version}
 */
@RosettaDataType(value="UmbrellaAgreementEntity", builder=UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilderImpl.class, version="${project.version}")
public interface UmbrellaAgreementEntity extends LegalEntity {

	UmbrellaAgreementEntityMeta metaData = new UmbrellaAgreementEntityMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The terms that might be associated with each party to the umbrella agreement.
	 */
	String getTerms();

	/*********************** Build Methods  ***********************/
	UmbrellaAgreementEntity build();
	
	UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder toBuilder();
	
	static UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder builder() {
		return new UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends UmbrellaAgreementEntity> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends UmbrellaAgreementEntity> getType() {
		return UmbrellaAgreementEntity.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("entityId"), processor, FieldWithMetaString.class, getEntityId());
		processRosetta(path.newSubPath("name"), processor, FieldWithMetaString.class, getName());
		processRosetta(path.newSubPath("meta"), processor, MetaFields.class, getMeta());
		processor.processBasic(path.newSubPath("terms"), String.class, getTerms(), this);
	}
	

	/*********************** Builder Interface  ***********************/
	interface UmbrellaAgreementEntityBuilder extends UmbrellaAgreementEntity, LegalEntity.LegalEntityBuilder, RosettaModelObjectBuilder {
		UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder addEntityId(FieldWithMetaString entityId0);
		UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder addEntityId(FieldWithMetaString entityId1, int _idx);
		UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder addEntityIdValue(String entityId2);
		UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder addEntityIdValue(String entityId3, int _idx);
		UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder addEntityId(List<? extends FieldWithMetaString> entityId4);
		UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder setEntityId(List<? extends FieldWithMetaString> entityId5);
		UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder addEntityIdValue(List<? extends String> entityId6);
		UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder setEntityIdValue(List<? extends String> entityId7);
		UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder setName(FieldWithMetaString name0);
		UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder setNameValue(String name1);
		UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder setMeta(MetaFields meta);
		UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder setTerms(String terms);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("entityId"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getEntityId());
			processRosetta(path.newSubPath("name"), processor, FieldWithMetaString.FieldWithMetaStringBuilder.class, getName());
			processRosetta(path.newSubPath("meta"), processor, MetaFields.MetaFieldsBuilder.class, getMeta());
			processor.processBasic(path.newSubPath("terms"), String.class, getTerms(), this);
		}
		

		UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder prune();
	}

	/*********************** Immutable Implementation of UmbrellaAgreementEntity  ***********************/
	class UmbrellaAgreementEntityImpl extends LegalEntity.LegalEntityImpl implements UmbrellaAgreementEntity {
		private final String terms;
		
		protected UmbrellaAgreementEntityImpl(UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder builder) {
			super(builder);
			this.terms = builder.getTerms();
		}
		
		@Override
		@RosettaAttribute("terms")
		public String getTerms() {
			return terms;
		}
		
		@Override
		public UmbrellaAgreementEntity build() {
			return this;
		}
		
		@Override
		public UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder toBuilder() {
			UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder builder) {
			super.setBuilderFields(builder);
			ofNullable(getTerms()).ifPresent(builder::setTerms);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			UmbrellaAgreementEntity _that = getType().cast(o);
		
			if (!Objects.equals(terms, _that.getTerms())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (terms != null ? terms.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "UmbrellaAgreementEntity {" +
				"terms=" + this.terms +
			'}' + " " + super.toString();
		}
	}

	/*********************** Builder Implementation of UmbrellaAgreementEntity  ***********************/
	class UmbrellaAgreementEntityBuilderImpl extends LegalEntity.LegalEntityBuilderImpl  implements UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder {
	
		protected String terms;
	
		public UmbrellaAgreementEntityBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("terms")
		public String getTerms() {
			return terms;
		}
		
	
		@Override
		public UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder addEntityId(FieldWithMetaString entityId) {
			if (entityId!=null) this.entityId.add(entityId.toBuilder());
			return this;
		}
		
		@Override
		public UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder addEntityId(FieldWithMetaString entityId, int _idx) {
			getIndex(this.entityId, _idx, () -> entityId.toBuilder());
			return this;
		}
		
		@Override
		public UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder addEntityIdValue(String entityId) {
			this.getOrCreateEntityId(-1).setValue(entityId);
			return this;
		}
		
		@Override
		public UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder addEntityIdValue(String entityId, int _idx) {
			this.getOrCreateEntityId(_idx).setValue(entityId);
			return this;
		}
		@Override 
		public UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder addEntityId(List<? extends FieldWithMetaString> entityIds) {
			if (entityIds != null) {
				for (FieldWithMetaString toAdd : entityIds) {
					this.entityId.add(toAdd.toBuilder());
				}
			}
			return this;
		}
		
		@Override 
		@RosettaAttribute("entityId")
		public UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder setEntityId(List<? extends FieldWithMetaString> entityIds) {
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
		public UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder addEntityIdValue(List<? extends String> entityIds) {
			if (entityIds != null) {
				for (String toAdd : entityIds) {
					this.addEntityIdValue(toAdd);
				}
			}
			return this;
		}
		
		@Override
		public UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder setEntityIdValue(List<? extends String> entityIds) {
			this.entityId.clear();
			if (entityIds!=null) {
				entityIds.forEach(this::addEntityIdValue);
			}
			return this;
		}
		
		@Override
		@RosettaAttribute("name")
		public UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder setName(FieldWithMetaString name) {
			this.name = name==null?null:name.toBuilder();
			return this;
		}
		@Override
		public UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder setNameValue(String name) {
			this.getOrCreateName().setValue(name);
			return this;
		}
		@Override
		@RosettaAttribute("meta")
		public UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder setMeta(MetaFields meta) {
			this.meta = meta==null?null:meta.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("terms")
		public UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder setTerms(String terms) {
			this.terms = terms==null?null:terms;
			return this;
		}
		
		@Override
		public UmbrellaAgreementEntity build() {
			return new UmbrellaAgreementEntity.UmbrellaAgreementEntityImpl(this);
		}
		
		@Override
		public UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder prune() {
			super.prune();
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (super.hasData()) return true;
			if (getTerms()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			super.merge(other, merger);
			
			UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder o = (UmbrellaAgreementEntity.UmbrellaAgreementEntityBuilder) other;
			
			
			merger.mergeBasic(getTerms(), o.getTerms(), this::setTerms);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
			if (!super.equals(o)) return false;
		
			UmbrellaAgreementEntity _that = getType().cast(o);
		
			if (!Objects.equals(terms, _that.getTerms())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = super.hashCode();
			_result = 31 * _result + (terms != null ? terms.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "UmbrellaAgreementEntityBuilder {" +
				"terms=" + this.terms +
			'}' + " " + super.toString();
		}
	}
}
