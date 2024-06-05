package cdm.product.asset;

import cdm.base.staticdata.party.EntityTypeEnum;
import cdm.base.staticdata.party.LegalEntity;
import cdm.base.staticdata.party.metafields.FieldWithMetaEntityTypeEnum;
import cdm.base.staticdata.party.metafields.FieldWithMetaEntityTypeEnum.FieldWithMetaEntityTypeEnumBuilder;
import cdm.product.asset.ReferenceObligation;
import cdm.product.asset.ReferencePair;
import cdm.product.asset.ReferencePair.ReferencePairBuilder;
import cdm.product.asset.ReferencePair.ReferencePairBuilderImpl;
import cdm.product.asset.ReferencePair.ReferencePairImpl;
import cdm.product.asset.meta.ReferencePairMeta;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaAttribute;
import com.rosetta.model.lib.annotations.RosettaDataType;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;
import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * @version ${project.version}
 */
@RosettaDataType(value="ReferencePair", builder=ReferencePair.ReferencePairBuilderImpl.class, version="${project.version}")
public interface ReferencePair extends RosettaModelObject {

	ReferencePairMeta metaData = new ReferencePairMeta();

	/*********************** Getter Methods  ***********************/
	/**
	 * The corporate or sovereign entity on which you are buying or selling protection and any successor that assumes all or substantially all of its contractual and other obligations. It is vital to use the correct legal name of the entity and to be careful not to choose a subsidiary if you really want to trade protection on a parent company. Please note, Reference Entities cannot be senior or subordinated. It is the obligations of the Reference Entities that can be senior or subordinated. ISDA 2003 Term: Reference Entity.
	 */
	LegalEntity getReferenceEntity();
	/**
	 * The Reference Obligation is a financial instrument that is either issued or guaranteed by the reference entity. It serves to clarify the precise reference entity protection is being offered upon, and its legal position with regard to other related firms (parents/subsidiaries). Furthermore the Reference Obligation is ALWAYS deliverable and establishes the Pari Passu ranking (as the deliverable bonds must rank equal to the reference obligation). ISDA 2003 Term: Reference Obligation.
	 */
	ReferenceObligation getReferenceObligation();
	/**
	 * Used to indicate that there is no Reference Obligation associated with this Credit Default Swap and that there will never be one.
	 */
	Boolean getNoReferenceObligation();
	/**
	 * Defines the reference entity types corresponding to a list of types in the ISDA First to Default documentation.
	 */
	FieldWithMetaEntityTypeEnum getEntityType();

	/*********************** Build Methods  ***********************/
	ReferencePair build();
	
	ReferencePair.ReferencePairBuilder toBuilder();
	
	static ReferencePair.ReferencePairBuilder builder() {
		return new ReferencePair.ReferencePairBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends ReferencePair> metaData() {
		return metaData;
	}
	
	@Override
	default Class<? extends ReferencePair> getType() {
		return ReferencePair.class;
	}
	
	
	@Override
	default void process(RosettaPath path, Processor processor) {
		processRosetta(path.newSubPath("referenceEntity"), processor, LegalEntity.class, getReferenceEntity());
		processRosetta(path.newSubPath("referenceObligation"), processor, ReferenceObligation.class, getReferenceObligation());
		processor.processBasic(path.newSubPath("noReferenceObligation"), Boolean.class, getNoReferenceObligation(), this);
		processRosetta(path.newSubPath("entityType"), processor, FieldWithMetaEntityTypeEnum.class, getEntityType());
	}
	

	/*********************** Builder Interface  ***********************/
	interface ReferencePairBuilder extends ReferencePair, RosettaModelObjectBuilder {
		LegalEntity.LegalEntityBuilder getOrCreateReferenceEntity();
		LegalEntity.LegalEntityBuilder getReferenceEntity();
		ReferenceObligation.ReferenceObligationBuilder getOrCreateReferenceObligation();
		ReferenceObligation.ReferenceObligationBuilder getReferenceObligation();
		FieldWithMetaEntityTypeEnum.FieldWithMetaEntityTypeEnumBuilder getOrCreateEntityType();
		FieldWithMetaEntityTypeEnum.FieldWithMetaEntityTypeEnumBuilder getEntityType();
		ReferencePair.ReferencePairBuilder setReferenceEntity(LegalEntity referenceEntity);
		ReferencePair.ReferencePairBuilder setReferenceObligation(ReferenceObligation referenceObligation);
		ReferencePair.ReferencePairBuilder setNoReferenceObligation(Boolean noReferenceObligation);
		ReferencePair.ReferencePairBuilder setEntityType(FieldWithMetaEntityTypeEnum entityType0);
		ReferencePair.ReferencePairBuilder setEntityTypeValue(EntityTypeEnum entityType1);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("referenceEntity"), processor, LegalEntity.LegalEntityBuilder.class, getReferenceEntity());
			processRosetta(path.newSubPath("referenceObligation"), processor, ReferenceObligation.ReferenceObligationBuilder.class, getReferenceObligation());
			processor.processBasic(path.newSubPath("noReferenceObligation"), Boolean.class, getNoReferenceObligation(), this);
			processRosetta(path.newSubPath("entityType"), processor, FieldWithMetaEntityTypeEnum.FieldWithMetaEntityTypeEnumBuilder.class, getEntityType());
		}
		

		ReferencePair.ReferencePairBuilder prune();
	}

	/*********************** Immutable Implementation of ReferencePair  ***********************/
	class ReferencePairImpl implements ReferencePair {
		private final LegalEntity referenceEntity;
		private final ReferenceObligation referenceObligation;
		private final Boolean noReferenceObligation;
		private final FieldWithMetaEntityTypeEnum entityType;
		
		protected ReferencePairImpl(ReferencePair.ReferencePairBuilder builder) {
			this.referenceEntity = ofNullable(builder.getReferenceEntity()).map(f->f.build()).orElse(null);
			this.referenceObligation = ofNullable(builder.getReferenceObligation()).map(f->f.build()).orElse(null);
			this.noReferenceObligation = builder.getNoReferenceObligation();
			this.entityType = ofNullable(builder.getEntityType()).map(f->f.build()).orElse(null);
		}
		
		@Override
		@RosettaAttribute("referenceEntity")
		public LegalEntity getReferenceEntity() {
			return referenceEntity;
		}
		
		@Override
		@RosettaAttribute("referenceObligation")
		public ReferenceObligation getReferenceObligation() {
			return referenceObligation;
		}
		
		@Override
		@RosettaAttribute("noReferenceObligation")
		public Boolean getNoReferenceObligation() {
			return noReferenceObligation;
		}
		
		@Override
		@RosettaAttribute("entityType")
		public FieldWithMetaEntityTypeEnum getEntityType() {
			return entityType;
		}
		
		@Override
		public ReferencePair build() {
			return this;
		}
		
		@Override
		public ReferencePair.ReferencePairBuilder toBuilder() {
			ReferencePair.ReferencePairBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(ReferencePair.ReferencePairBuilder builder) {
			ofNullable(getReferenceEntity()).ifPresent(builder::setReferenceEntity);
			ofNullable(getReferenceObligation()).ifPresent(builder::setReferenceObligation);
			ofNullable(getNoReferenceObligation()).ifPresent(builder::setNoReferenceObligation);
			ofNullable(getEntityType()).ifPresent(builder::setEntityType);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReferencePair _that = getType().cast(o);
		
			if (!Objects.equals(referenceEntity, _that.getReferenceEntity())) return false;
			if (!Objects.equals(referenceObligation, _that.getReferenceObligation())) return false;
			if (!Objects.equals(noReferenceObligation, _that.getNoReferenceObligation())) return false;
			if (!Objects.equals(entityType, _that.getEntityType())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (referenceEntity != null ? referenceEntity.hashCode() : 0);
			_result = 31 * _result + (referenceObligation != null ? referenceObligation.hashCode() : 0);
			_result = 31 * _result + (noReferenceObligation != null ? noReferenceObligation.hashCode() : 0);
			_result = 31 * _result + (entityType != null ? entityType.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ReferencePair {" +
				"referenceEntity=" + this.referenceEntity + ", " +
				"referenceObligation=" + this.referenceObligation + ", " +
				"noReferenceObligation=" + this.noReferenceObligation + ", " +
				"entityType=" + this.entityType +
			'}';
		}
	}

	/*********************** Builder Implementation of ReferencePair  ***********************/
	class ReferencePairBuilderImpl implements ReferencePair.ReferencePairBuilder {
	
		protected LegalEntity.LegalEntityBuilder referenceEntity;
		protected ReferenceObligation.ReferenceObligationBuilder referenceObligation;
		protected Boolean noReferenceObligation;
		protected FieldWithMetaEntityTypeEnum.FieldWithMetaEntityTypeEnumBuilder entityType;
	
		public ReferencePairBuilderImpl() {
		}
	
		@Override
		@RosettaAttribute("referenceEntity")
		public LegalEntity.LegalEntityBuilder getReferenceEntity() {
			return referenceEntity;
		}
		
		@Override
		public LegalEntity.LegalEntityBuilder getOrCreateReferenceEntity() {
			LegalEntity.LegalEntityBuilder result;
			if (referenceEntity!=null) {
				result = referenceEntity;
			}
			else {
				result = referenceEntity = LegalEntity.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("referenceObligation")
		public ReferenceObligation.ReferenceObligationBuilder getReferenceObligation() {
			return referenceObligation;
		}
		
		@Override
		public ReferenceObligation.ReferenceObligationBuilder getOrCreateReferenceObligation() {
			ReferenceObligation.ReferenceObligationBuilder result;
			if (referenceObligation!=null) {
				result = referenceObligation;
			}
			else {
				result = referenceObligation = ReferenceObligation.builder();
			}
			
			return result;
		}
		@Override
		@RosettaAttribute("noReferenceObligation")
		public Boolean getNoReferenceObligation() {
			return noReferenceObligation;
		}
		
		@Override
		@RosettaAttribute("entityType")
		public FieldWithMetaEntityTypeEnum.FieldWithMetaEntityTypeEnumBuilder getEntityType() {
			return entityType;
		}
		
		@Override
		public FieldWithMetaEntityTypeEnum.FieldWithMetaEntityTypeEnumBuilder getOrCreateEntityType() {
			FieldWithMetaEntityTypeEnum.FieldWithMetaEntityTypeEnumBuilder result;
			if (entityType!=null) {
				result = entityType;
			}
			else {
				result = entityType = FieldWithMetaEntityTypeEnum.builder();
			}
			
			return result;
		}
	
		@Override
		@RosettaAttribute("referenceEntity")
		public ReferencePair.ReferencePairBuilder setReferenceEntity(LegalEntity referenceEntity) {
			this.referenceEntity = referenceEntity==null?null:referenceEntity.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("referenceObligation")
		public ReferencePair.ReferencePairBuilder setReferenceObligation(ReferenceObligation referenceObligation) {
			this.referenceObligation = referenceObligation==null?null:referenceObligation.toBuilder();
			return this;
		}
		@Override
		@RosettaAttribute("noReferenceObligation")
		public ReferencePair.ReferencePairBuilder setNoReferenceObligation(Boolean noReferenceObligation) {
			this.noReferenceObligation = noReferenceObligation==null?null:noReferenceObligation;
			return this;
		}
		@Override
		@RosettaAttribute("entityType")
		public ReferencePair.ReferencePairBuilder setEntityType(FieldWithMetaEntityTypeEnum entityType) {
			this.entityType = entityType==null?null:entityType.toBuilder();
			return this;
		}
		@Override
		public ReferencePair.ReferencePairBuilder setEntityTypeValue(EntityTypeEnum entityType) {
			this.getOrCreateEntityType().setValue(entityType);
			return this;
		}
		
		@Override
		public ReferencePair build() {
			return new ReferencePair.ReferencePairImpl(this);
		}
		
		@Override
		public ReferencePair.ReferencePairBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReferencePair.ReferencePairBuilder prune() {
			if (referenceEntity!=null && !referenceEntity.prune().hasData()) referenceEntity = null;
			if (referenceObligation!=null && !referenceObligation.prune().hasData()) referenceObligation = null;
			if (entityType!=null && !entityType.prune().hasData()) entityType = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getReferenceEntity()!=null && getReferenceEntity().hasData()) return true;
			if (getReferenceObligation()!=null && getReferenceObligation().hasData()) return true;
			if (getNoReferenceObligation()!=null) return true;
			if (getEntityType()!=null) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public ReferencePair.ReferencePairBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			ReferencePair.ReferencePairBuilder o = (ReferencePair.ReferencePairBuilder) other;
			
			merger.mergeRosetta(getReferenceEntity(), o.getReferenceEntity(), this::setReferenceEntity);
			merger.mergeRosetta(getReferenceObligation(), o.getReferenceObligation(), this::setReferenceObligation);
			merger.mergeRosetta(getEntityType(), o.getEntityType(), this::setEntityType);
			
			merger.mergeBasic(getNoReferenceObligation(), o.getNoReferenceObligation(), this::setNoReferenceObligation);
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			ReferencePair _that = getType().cast(o);
		
			if (!Objects.equals(referenceEntity, _that.getReferenceEntity())) return false;
			if (!Objects.equals(referenceObligation, _that.getReferenceObligation())) return false;
			if (!Objects.equals(noReferenceObligation, _that.getNoReferenceObligation())) return false;
			if (!Objects.equals(entityType, _that.getEntityType())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (referenceEntity != null ? referenceEntity.hashCode() : 0);
			_result = 31 * _result + (referenceObligation != null ? referenceObligation.hashCode() : 0);
			_result = 31 * _result + (noReferenceObligation != null ? noReferenceObligation.hashCode() : 0);
			_result = 31 * _result + (entityType != null ? entityType.getClass().getName().hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "ReferencePairBuilder {" +
				"referenceEntity=" + this.referenceEntity + ", " +
				"referenceObligation=" + this.referenceObligation + ", " +
				"noReferenceObligation=" + this.noReferenceObligation + ", " +
				"entityType=" + this.entityType +
			'}';
		}
	}
}
